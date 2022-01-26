<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="所属商户" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.client_id" :span="clientIdParam[curInfo.client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="手机" :label-width="labelWidth">
                  <el-input type="text" placeholder="手机" v-model="curInfo.mobile"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="昵称" :label-width="labelWidth">
                  <el-input type="text" placeholder="昵称" v-model="curInfo.nickname"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="用户名" :label-width="labelWidth">
                  <el-input type="text" placeholder="用户名" v-model="curInfo.username"></el-input>
                </el-form-item>
              </el-col>
              <!-- 密码框 -->
              <el-col :md="24">
                <el-form-item label="密码" :label-width="labelWidth">
                  <el-input type="password" placeholder="密码" v-model="curInfo.password" ></el-input>
                </el-form-item>
              </el-col>
              <!-- 单选框 -->
              <el-col :md="24">
                <el-form-item label="状态" :label-width="labelWidth">
                  <input-radio v-model="curInfo.status" controller="Default_Client_User" field="status" placeholder="状态" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="curVisible = false;handleClosed();">取 消</el-button>
        <el-button type="primary" @click="handleSaveInfo()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import util from '@/libs/util'
import _ from 'lodash'
import DefaultClientLookup from '@/views/default/client/lookup'
export default {
  name: 'InputSave',
  components: {
    DefaultClientLookup,
  },
  filters: {
    parseTime: util.parseTime,
    thumb: util.thumb
  },
  props: {
    id: {
      type: String,
      default: ''
    },
    labelWidth: {
      type: String,
      default: '120px'
    }
  },
  data () {
    return {
      curVisible: false,
      curActiveTab: 'first',
      curInfo: {},
      curId: '',
      clientIdParam: {},
      statusParam: {},
    }
  },
  computed: {
  },
  watch: {
    id (newValue) {
      if (newValue != null) {
        this.curVisible = true
        this.curId = newValue
        this.handleGetInfo()
      }
    }
  },
  created () {
  },
  methods: {
    handleGetInfo () {
      this.$api.table.getInfo('Default_Client_User', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.clientIdParam = response.data.client_id_param
        this.curInfo['password'] = ''
        this.statusParam = response.data.status_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Client_User', {
        id: this.curInfo.id,
        info: this.curInfo
      }).then(response => {
        this.curVisible = false
        this.$emit('editDone', {})
      })
    },
    handleClosed () {
      this.$emit('editClosed', {})
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
