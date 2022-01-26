<template>
  <div>
    <el-dialog title="浏览数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">编号 :</el-col>
        <el-col :md="20">{{ curInfo.id }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">英文名 :</el-col>
        <el-col :md="20">{{ curInfo.enname }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">名称 :</el-col>
        <el-col :md="20">{{ curInfo.name }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">余额 :</el-col>
        <el-col :md="20">{{ curInfo.money }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">角色 :</el-col>
        <el-col :md="20">{{ curInfo.roles }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">状态 :</el-col>
        <el-col :md="20">{{ statusParam[curInfo.status] }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">超管名 :</el-col>
        <el-col :md="20">{{ curInfo.supername }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">超管密码 :</el-col>
        <el-col :md="20">{{ curInfo.superpwd }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">联系人 :</el-col>
        <el-col :md="20">{{ curInfo.contact }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">备注 :</el-col>
        <el-col :md="20">{{ curInfo.remark }}</el-col>
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
        <el-col :md="4" class="view-label">修改时间 :</el-col>
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
      rolesParam: {},
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
      this.$api.table.getInfo('Default_Client', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
        this.rolesParam = response.data.roles_param
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
