<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="英文名" :label-width="labelWidth">
                  <el-input type="text" placeholder="英文名" v-model="curInfo.enname"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="名称" :label-width="labelWidth">
                  <el-input type="text" placeholder="名称" v-model="curInfo.name"></el-input>
                </el-form-item>
              </el-col>
              <!-- SPAN -->
              <el-col :md="12">
                <el-form-item label="余额" :label-width="labelWidth" >
                  {{ curInfo.money }}
                </el-form-item>
              </el-col>
              <!-- 多选框 -->
              <el-col :md="24">
                <el-form-item label="角色" :label-width="labelWidth">
                  <input-checkbox v-model="curInfo.roles" controller="Default_Client" field="roles" placeholder="角色"/>
                </el-form-item>
              </el-col>
              <!-- 单选框 -->
              <el-col :md="24">
                <el-form-item label="状态" :label-width="labelWidth">
                  <input-radio v-model="curInfo.status" controller="Default_Client" field="status" placeholder="状态" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label=" 附加 " name="second">
            <el-row :gutter="20">
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="超管名" :label-width="labelWidth">
                  <el-input type="text" placeholder="超管名" v-model="curInfo.supername"></el-input>
                </el-form-item>
              </el-col>
              <!-- 密码框 -->
              <el-col :md="24">
                <el-form-item label="超管密码" :label-width="labelWidth">
                  <el-input type="password" placeholder="超管密码" v-model="curInfo.superpwd" ></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="联系人" :label-width="labelWidth">
                  <el-input type="text" placeholder="联系人" v-model="curInfo.contact"></el-input>
                </el-form-item>
              </el-col>
              <!-- 多行文本 -->
              <el-col :md="24">
                <el-form-item label="备注" :label-width="labelWidth">
                  <el-input type="textarea" placeholder="备注" v-model="curInfo.remark" ></el-input>
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
export default {
  name: 'InputSave',
  components: {
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
      rolesParam: {},
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
      this.$api.table.getInfo('Default_Client', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.rolesParam = response.data.roles_param
        this.curInfo['superpwd'] = ''
        this.statusParam = response.data.status_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Client', {
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
