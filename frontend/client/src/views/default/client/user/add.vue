<template>
  <div>
    <el-button :type="type" :icon="icon" @click="showAddInfo">{{ title }}</el-button>
    <el-dialog title="添加数据" :visible.sync="curVisible" append-to-body v-dialog-drag>
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
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
                <el-input type="password" placeholder="密码" v-model="curInfo.password"></el-input>
              </el-form-item>
            </el-col>
            <!-- 单选框 -->
            <el-col :md="24">
              <el-form-item label="状态" :label-width="labelWidth">
                <input-radio v-model="curInfo.status" controller="Default_Client_User" field="status" placeholder="状态"/>
              </el-form-item>
            </el-col>
            </el-row>
          </el-tab-pane>
          </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="curVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAddInfo()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import util from '@/libs/util'
import _ from 'lodash'
export default {
  name: 'InputAdd',
  components: {
  },
  filters: {
    parseTime: util.parseTime,
    thumb: util.thumb
  },
  props: {
    info: {
      type: Object,
      default: function () {
        return {}
      }
    },
    title: {
      type: String,
      default: '增加'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'el-icon-plus'
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
      curInfo: {}
    }
  },
  computed: {
  },
  watch: {
    info (newValue) {
      this.curInfo = _.clone(newValue)
    }
  },
  created () {
    const tmp = {}
    tmp.client_id = ''
    tmp.id = ''
    tmp.mobile = ''
    tmp.nickname = ''
    tmp.password = ''
    tmp.status = '1'
    tmp.username = ''
    this.curInfo = _.clone(tmp)
  },
  methods: {
    showAddInfo () {
    this.curVisible = true
    },
    handleAddInfo () {
    this.$api.table.addInfo('Default_Client_User', {
      info: this.curInfo
    }).then(response => {
      this.curVisible = false
      this.$emit('addDone', {})
    })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
