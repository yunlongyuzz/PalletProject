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
              <el-form-item label="司机" :label-width="labelWidth">
                <el-input type="text" placeholder="司机" v-model="curInfo.name"></el-input>
              </el-form-item>
            </el-col>
            <!-- 单行文本 -->
            <el-col :md="24">
              <el-form-item label="车牌号" :label-width="labelWidth">
                <el-input type="text" placeholder="车牌号" v-model="curInfo.no"></el-input>
              </el-form-item>
            </el-col>
            <!-- 单行文本 -->
            <el-col :md="24">
              <el-form-item label="联系方式" :label-width="labelWidth">
                <el-input type="text" placeholder="联系方式" v-model="curInfo.contact"></el-input>
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
    tmp.id = ''
    tmp.name = ''
    tmp.no = ''
    this.curInfo = _.clone(tmp)
  },
  methods: {
    showAddInfo () {
    this.curVisible = true
    },
    handleAddInfo () {
    this.$api.table.addInfo('Default_Car', {
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
