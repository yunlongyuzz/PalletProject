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
              <el-form-item label="名称" :label-width="labelWidth">
                <el-input type="text" placeholder="名称" v-model="curInfo.name"></el-input>
              </el-form-item>
            </el-col>
            <!-- 单选框 -->
            <el-col :md="24">
              <el-form-item label="费用类型" :label-width="labelWidth">
                <input-radio v-model="curInfo.fee_type" controller="Default_Order" field="fee_type" placeholder="费用类型"/>
              </el-form-item>
            </el-col>
            <!-- 单行文本 -->
            <el-col :md="24">
              <el-form-item label="金额" :label-width="labelWidth">
                <el-input type="text" placeholder="金额" v-model="curInfo.fee_money"></el-input>
              </el-form-item>
            </el-col>
            <!-- LookUp弹出框 -->
            <el-col :md="12">
              <el-form-item label="支付方" :label-width="labelWidth">
              <default-client-lookup v-model="curInfo.pay_client_id" :span="curInfo.v_pay_client_id" keyField="id" valueField="name" placeholder/>
              </el-form-item>
            </el-col>
            <!-- LookUp弹出框 -->
            <el-col :md="12">
              <el-form-item label="收款方" :label-width="labelWidth">
              <default-client-lookup v-model="curInfo.collect_client_id" :span="curInfo.v_collect_client_id" keyField="id" valueField="name" placeholder/>
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
import DefaultClientLookup from '@/views/default/client/lookup'
export default {
  name: 'InputAdd',
  components: {
    DefaultClientLookup,
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
    tmp.collect_client_id = ''
    tmp.fee_money = ''
    tmp.fee_type = ''
    tmp.id = ''
    tmp.name = ''
    tmp.pay_client_id = ''
    tmp.remark = ''
    this.curInfo = _.clone(tmp)
  },
  methods: {
    showAddInfo () {
    this.curVisible = true
    },
    handleAddInfo () {
    this.$api.table.addInfo('Default_Order', {
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
