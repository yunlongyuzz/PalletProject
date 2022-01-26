<template>
  <div>
    <el-dialog title="编辑数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag >
      <el-form :model="curInfo">
        <el-tabs v-model="curActiveTab" v-tab-hide-head>
          <el-tab-pane label=" 常用 " name="first">
            <el-row :gutter="20">
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="托盘" :label-width="labelWidth">
                  <el-input type="text" placeholder="托盘" v-model="curInfo.pallet_id"></el-input>
                </el-form-item>
              </el-col>
              <!-- 下拉框 -->
              <el-col :md="12">
                <el-form-item label="流转类型" :label-width="labelWidth">
                  <input-select v-model="curInfo.trans_type" controller="Default_Pallet_Log" field="trans_type" placeholder="流转类型"/>
                </el-form-item>
              </el-col>
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
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="车牌号" :label-width="labelWidth">
                  <el-input type="text" placeholder="车牌号" v-model="curInfo.car_no"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="经度" :label-width="labelWidth">
                  <el-input type="text" placeholder="经度" v-model="curInfo.longitude"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="维度" :label-width="labelWidth">
                  <el-input type="text" placeholder="维度" v-model="curInfo.latitude"></el-input>
                </el-form-item>
              </el-col>
              <!-- 单行文本 -->
              <el-col :md="24">
                <el-form-item label="地址" :label-width="labelWidth">
                  <el-input type="text" placeholder="地址" v-model="curInfo.address"></el-input>
                </el-form-item>
              </el-col>
              <!-- LookUp弹出框 -->
              <el-col :md="12">
                <el-form-item label="用户" :label-width="labelWidth">
                  <default-client-user-lookup v-model="curInfo.user_id" :span="userIdParam[curInfo.user_id]" keyField="id" valueField="nickname" placeholder />
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
import DefaultClientUserLookup from '@/views/default/client/user/lookup'
export default {
  name: 'InputSave',
  components: {
    DefaultClientLookup,
    DefaultClientUserLookup,
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
      fromClientIdParam: {},
      toClientIdParam: {},
      transTypeParam: {},
      userIdParam: {},
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
      this.$api.table.getInfo('Default_Pallet_Log', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.fromClientIdParam = response.data.from_client_id_param
        this.toClientIdParam = response.data.to_client_id_param
        this.transTypeParam = response.data.trans_type_param
        this.userIdParam = response.data.user_id_param
      })
    },
    handleSaveInfo () {
      this.$api.table.saveInfo('Default_Pallet_Log', {
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
