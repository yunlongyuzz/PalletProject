<template>
  <div>
    <el-dialog title="浏览数据" :visible.sync="curVisible" @closed="handleClosed()" append-to-body v-dialog-drag>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">方法 :</el-col>
        <el-col :md="20">{{ curInfo.action }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">控制器 :</el-col>
        <el-col :md="20">{{ curInfo.controller }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">创建时间 :</el-col>
        <el-col :md="20">{{ curInfo.ctime }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">分组 :</el-col>
        <el-col :md="20">{{ curInfo.group }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">ID :</el-col>
        <el-col :md="20">{{ curInfo.id }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">名称 :</el-col>
        <el-col :md="20">{{ curInfo.name }}</el-col>
      </el-row>
      <el-row :gutter="20" class="view-line">
        <el-col :md="4" class="view-label">参数 :</el-col>
        <el-col :md="20">{{ curInfo.param }}</el-col>
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
      this.$api.table.getInfo('Default_Client_User_Node', {
        id: this.curId
      }).then(response => {
        this.curInfo = response.data.info
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
