<template>
  <div>
    <el-dialog title="浏览数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">所属商户 :</el-col>
        <el-col :md="20">{{ clientIdParam[curInfo.client_id] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">手机 :</el-col>
        <el-col :md="20">{{ curInfo.mobile }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">昵称 :</el-col>
        <el-col :md="20">{{ curInfo.nickname }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">用户名 :</el-col>
        <el-col :md="20">{{ curInfo.username }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">密码 :</el-col>
        <el-col :md="20">{{ curInfo.password }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">状态 :</el-col>
        <el-col :md="20">{{ statusParam[curInfo.status] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">创建时间 :</el-col>
        <el-col :md="20">{{ curInfo.ctime }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">编号 :</el-col>
        <el-col :md="20">{{ curInfo.id }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">角色 :</el-col>
        <el-col :md="20">{{ curInfo.role }}</el-col>
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
      clientIdParam: {},
      statusParam: {},
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
      this.$api.table.getInfo('Default_Client_User', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.clientIdParam = response.data.client_id_param
        this.statusParam = response.data.status_param
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
