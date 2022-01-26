<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="昵称" :label-width="labelWidth">
                  <el-input type="text" placeholder="昵称" v-model="curInfo.nickname"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="用户名" :label-width="labelWidth">
                  <el-input type="text" placeholder="用户名" v-model="curInfo.user_name"></el-input>
                </el-form-item>
              </el-col>
              <!-- 密码框 -->
              <el-col :md="24">
                <el-form-item label="密码" :label-width="labelWidth">
                  <el-input type="password" placeholder="密码" v-model="curInfo.password" ></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="EMail" :label-width="labelWidth">
                  <el-input type="text" placeholder="EMail" v-model="curInfo.email"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="电话" :label-width="labelWidth">
                  <el-input type="text" placeholder="电话" v-model="curInfo.phone"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单选框 -->
              <el-col :md="24">
                <el-form-item label="是否有效" :label-width="labelWidth">
                  <input-radio v-model="curInfo.is_valid" controller="Default_Admin" field="is_valid" placeholder="是否有效" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label=" 附加 " name="second">
            <el-row :gutter="20">
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="上次登录IP" :label-width="labelWidth">
                  <el-input type="text" placeholder="上次登录IP" v-model="curInfo.last_login_ip"></el-input>
                </el-form-item>
              </el-col>
              <!-- 日期 -->
              <el-col :md="12">
                <el-form-item label="上次登录时间" :label-width="labelWidth">
                  <el-date-picker v-model="curInfo.last_login_time" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间" style="width:100%;" ></el-date-picker>
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
    isValidParam: {},
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
      this.$api.table.getInfo('Default_Admin', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.isValidParam = response.data.is_valid_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Admin', {
        id: this.curInfo.userId,
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
