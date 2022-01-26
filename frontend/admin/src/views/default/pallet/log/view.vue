<template>
  <div>
    <el-dialog title="浏览数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">托盘 :</el-col>
        <el-col :md="20">{{ curInfo.pallet_id }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">流转类型 :</el-col>
        <el-col :md="20">{{ transTypeParam[curInfo.trans_type] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">起点 :</el-col>
        <el-col :md="20">{{ fromClientIdParam[curInfo.from_client_id] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">终点 :</el-col>
        <el-col :md="20">{{ toClientIdParam[curInfo.to_client_id] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">车牌号 :</el-col>
        <el-col :md="20">{{ curInfo.car_no }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">经度 :</el-col>
        <el-col :md="20">{{ curInfo.longitude }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">维度 :</el-col>
        <el-col :md="20">{{ curInfo.latitude }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">地址 :</el-col>
        <el-col :md="20">{{ curInfo.address }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">用户 :</el-col>
        <el-col :md="20">{{ userIdParam[curInfo.user_id] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">创建时间 :</el-col>
        <el-col :md="20">{{ curInfo.created_at }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">删除时间 :</el-col>
        <el-col :md="20">{{ curInfo.deleted_at }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">编号 :</el-col>
        <el-col :md="20">{{ curInfo.id }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">更新时间 :</el-col>
        <el-col :md="20">{{ curInfo.updated_at }}</el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button @click="curVisible = false;handleClosed();">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'InputView',
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
  },
  data () {
    return {
      curVisible: false,
      curInfo: {},
      curId: '',
      fromClientIdParam: {},
      toClientIdParam: {},
      transTypeParam: {},
      userIdParam: {},
      curLabelWidth: '120px'
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
    handleClosed () {
      this.$emit('viewClosed', {})
    }
  }
}
</script>
<style lang="scss" scoped>
.view-line {
  line-height: 1.8em;
}
.view-label {
  text-align: right;
}
</style>
