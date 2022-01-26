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
              <!-- 文本框 -->
              <el-col :md="24">
                <el-form-item label="余额" :label-width="labelWidth">
                  <el-input type="text" placeholder="余额" v-model="curInfo.money"></el-input>
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
                  <input-radio v-model="curInfo.status" controller="Default_Client" field="status" placeholder="状态"/>
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
                  <el-input type="password" placeholder="超管密码" v-model="curInfo.superpwd"></el-input>
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
                  <el-input type="textarea" placeholder="备注" v-model="curInfo.remark"></el-input>
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
    tmp.contact = ''
    tmp.enname = ''
    tmp.id = ''
    tmp.money = '0'
    tmp.name = ''
    tmp.remark = ''
    tmp.roles = []
    tmp.supername = ''
    tmp.superpwd = ''
    tmp.status = '1'
    this.curInfo = _.clone(tmp)
  },
  methods: {
    showAddInfo () {
      this.curVisible = true
    },
    handleAddInfo () {
      this.$api.table.addInfo('Default_Client', {
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
