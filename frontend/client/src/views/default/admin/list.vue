<template>
  <d2-container>
    <div class="header-container">
      <el-form :model="listQuery" v-show="where==null" label-width="80px">
        <el-row :gutter="20">
          <!-- 单行文本 多行文本 富文本 搜索 -->
          <el-col :md="6">
            <el-form-item label="用户名">
              <el-input v-model="listQuery.query.lk_user_name" @keyup.enter.native="handleFilter" placeholder="用户名"/>
            </el-form-item>
          </el-col>
          <!-- 单行文本 多行文本 富文本 搜索 -->
          <el-col :md="6">
            <el-form-item label="电话">
              <el-input v-model="listQuery.query.lk_phone" @keyup.enter.native="handleFilter" placeholder="电话"/>
            </el-form-item>
          </el-col>
          <el-col :md="6">
            <div>
              <div class="filter-item">
                <el-button type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
              </div>
              <default-admin-add class="filter-item" @addDone="getListItems"/>
                </div>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="body-container">
      <el-table v-loading="listLoading" :data="listItems" fit border highlight-current-row @sort-change="handleSortChange" :default-sort="{prop: 'id', order: 'descending'}" style="width: 100%" height="500" v-table-auto-height="85">
        <el-table-column align="center" label="用户ID" prop="user_id" sortable="custom">
          <template slot-scope="scope">
            <span>{{ scope.row.userId }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="用户名" prop="user_name">
          <template slot-scope="scope">
            <span>{{ scope.row.user_name }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="EMail" prop="email">
          <template slot-scope="scope">
            <span>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="电话" prop="phone">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="昵称" prop="nickname">
          <template slot-scope="scope">
            <span>{{ scope.row.nickname }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="创建时间" prop="ctime">
          <template slot-scope="scope">
            <span>{{ scope.row.ctime }}</span>
          </template>
        </el-table-column>
          <el-table-column align="center" label="操作" width="150" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" size="mini" icon="el-icon-edit" @click="infoEditId=scope.row.userId">编辑</el-button>
            <el-button type="text" size="mini" icon="el-icon-view" @click="infoViewId=scope.row.userId">浏览</el-button>
            <!--
            <el-button type="text" size="mini" icon="el-icon-delete" @click="handleDelete(scope.row.userId)">删除</el-button>
            -->
            <!--
            <el-button type="text" size="mini" icon="el-icon-view" @click="listDialogId=scope.row.id;listDialogVisible=true;">子列表</el-button>
            -->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="footer-container">
      <pagination v-show="listTotal>0" :total="listTotal" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getListItems"/>
    </div>
    <default-admin-edit :id="infoEditId" @editDone="getListItems" @editClosed="infoEditId=null"/>
    <default-admin-view :id="infoViewId" @viewClosed="infoViewId=null"/>
    <!--
    <el-dialog title="子列表" :visible.sync="listDialogVisible" append-to-body v-dialog-drag>
      <default-list :where="{eq_id: listDialogId}"/>
    </el-dialog>
    -->
  </d2-container>
</template>
<script>
import util from '@/libs/util'
import { MessageBox } from 'element-ui'
import DefaultAdminAdd from './add'
import DefaultAdminEdit from './edit'
import DefaultAdminView from './view'
export default {
  name: 'Default_Admin_List',
  components: {
    DefaultAdminAdd,
    DefaultAdminEdit,
    DefaultAdminView
  },
  filters: {
    parseTime: util.parseTime,
    thumb: util.thumb
  },
  props: {
    where: null
  },
  data () {
    return {
      infoEditId: null,
      infoViewId: null,
      listItems: [],
      isValidParam: {},
      listTotal: 0,
      listLoading: true,
      listQuery: {
        query: {
        },
        order: [],
        page: 1,
        limit: 20
      },
      // 子列表
      // listDialogVisible: false,
      // listDialogId: ''
      // 子列表
      tmp: null
    }
  },
  computed: {
  },
  watch: {
    where (newValue) {
      this.listQuery.query = newValue
      this.getListItems()
    }
  },
  created () {
    if (this.where != null) {
      this.listQuery.query = this.where
    }
    this.getListItems()
  },
  show () {
  },
  methods: {
    getListItems () {
      this.listLoading = true
      this.$api.table.getList('Default_Admin', this.listQuery).then(response => {
        this.listItems = response.data.records
        this.isValidParam = response.data.is_valid_param
        this.listTotal = parseInt(response.data.total)
        this.listLoading = false
      })
    },
    handleSizeChange (val) {
      this.listQuery.limit = val
      this.getListItems()
    },
    handleCurrentChange (val) {
      this.listQuery.page = val
      this.getListItems()
    },
    handleFilter () {
      this.listQuery.page = 1
      this.getListItems()
    },
    handleSortChange (data) {
      const { prop, order } = data
      if (order === 'ascending') {
        this.listQuery.order = [prop, 'ASC']
      } else {
        this.listQuery.order = [prop, 'DESC']
      }
      this.handleFilter()
    },
    handleDelete (id) {
      const that = this
      MessageBox.confirm(
        '您确定要删除记录吗?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        that.$api.table.delInfo('Default_Admin', { id }).then((response) => {
          that.getListItems()
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
