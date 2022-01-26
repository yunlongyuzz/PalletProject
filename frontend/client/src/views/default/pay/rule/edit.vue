<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="起点" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.from_client_id" :span="fromClientIdParam[curInfo.from_client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="终点" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.to_client_id" :span="toClientIdParam[curInfo.to_client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="托盘方" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.pallet_client_id" :span="palletClientIdParam[curInfo.pallet_client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="支付方" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.pay_client_id" :span="payClientIdParam[curInfo.pay_client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- 下拉框 -->
              <el-col :md="12">
                <el-form-item label="费用类型" :label-width="labelWidth">
                  <input-select v-model="curInfo.fee_type" controller="Default_Pay_Rule" field="fee_type" placeholder="费用类型"/>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="支付金额" :label-width="labelWidth">
                  <el-input type="text" placeholder="支付金额" v-model="curInfo.pay_money"></el-input>
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
    palletClientIdParam: {},
    feeTypeParam: {},
    payClientIdParam: {},
    fromClientIdParam: {},
    toClientIdParam: {},
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
      this.$api.table.getInfo('Default_Pay_Rule', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.palletClientIdParam = response.data.pallet_client_id_param
        this.feeTypeParam = response.data.fee_type_param
        this.payClientIdParam = response.data.pay_client_id_param
        this.fromClientIdParam = response.data.from_client_id_param
        this.toClientIdParam = response.data.to_client_id_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Pay_Rule', {
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
