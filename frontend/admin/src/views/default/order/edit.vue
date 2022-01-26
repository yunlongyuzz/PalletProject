<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
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
                  <input-radio v-model="curInfo.fee_type" controller="Default_Order" field="fee_type" placeholder="费用类型" />
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
                  <default-client-lookup v-model="curInfo.pay_client_id" :span="payClientIdParam[curInfo.pay_client_id]" keyField="id" valueField="name" placeholder />
                </el-form-item>
              </el-col>
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="收款方" :label-width="labelWidth">
                  <default-client-lookup v-model="curInfo.collect_client_id" :span="collectClientIdParam[curInfo.collect_client_id]" keyField="id" valueField="name" placeholder />
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
      collectClientIdParam: {},
      feeTypeParam: {},
      payClientIdParam: {},
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
      this.$api.table.getInfo('Default_Order', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.collectClientIdParam = response.data.collect_client_id_param
        this.feeTypeParam = response.data.fee_type_param
        this.payClientIdParam = response.data.pay_client_id_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Order', {
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
