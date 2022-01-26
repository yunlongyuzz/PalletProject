<template>
  <div>
    <el-dialog title="浏览数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">昵称 :</el-col>
        <el-col :md="20">{{ curInfo.nickname }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">用户名 :</el-col>
        <el-col :md="20">{{ curInfo.user_name }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">密码 :</el-col>
        <el-col :md="20">{{ curInfo.password }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">EMail :</el-col>
        <el-col :md="20">{{ curInfo.email }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">电话 :</el-col>
        <el-col :md="20">{{ curInfo.phone }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">是否有效 :</el-col>
        <el-col :md="20">{{ isValidParam[curInfo.is_valid] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">上次登录IP :</el-col>
        <el-col :md="20">{{ curInfo.last_login_ip }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">上次登录时间 :</el-col>
        <el-col :md="20">{{ curInfo.last_login_time }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">创建时间 :</el-col>
        <el-col :md="20">{{ curInfo.ctime }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">修改时间 :</el-col>
        <el-col :md="20">{{ curInfo.mtime }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">权限 :</el-col>
        <el-col :md="20">{{ curInfo.role }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">用户ID :</el-col>
        <el-col :md="20">{{ curInfo.user_id }}</el-col>
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
      isValidParam: {},
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
      this.$api.table.getInfo('Default_Admin', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.isValidParam = response.data.is_valid_param
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
