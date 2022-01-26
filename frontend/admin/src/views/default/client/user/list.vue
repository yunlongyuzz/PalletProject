<template>
  <d2-container>
    <div class="header-container">
      <el-form :model="listQuery" v-show="where==null" label-width="80px">
        <el-row :gutter="20">
          <!-- LookUp单选 搜索 -->
          <el-col :md="6">
            <el-form-item label="所属商户">
              <default-client-lookup v-model="listQuery.query.eq_client_id" keyField="id" valueField="name" placeholder/>
            </el-form-item>
          </el-col>
          <!-- 单行文本 多行文本 富文本 搜索 -->
          <el-col :md="6">
            <el-form-item label="手机">
              <el-input v-model="listQuery.query.lk_mobile" @keyup.enter.native="handleFilter" placeholder="手机"/>
            </el-form-item>
          </el-col>
          <!-- 下拉框 单选框 搜索 -->
          <el-col :md="6">
            <el-form-item label="状态">
              <input-select v-model="listQuery.query.eq_status" controller="Default_Client_User" field="status" placeholder="状态"/>
            </el-form-item>
          </el-col>
          <el-col :md="6">
            <div>
              <div class="filter-item">
                <el-button type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
              </div>
              <default-client-user-add class="filter-item" @addDone="getListItems"/>
              </div>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="body-container">
      <el-table v-loading="listLoading" :data="listItems" fit border highlight-current-row @sort-change="handleSortChange" :default-sort="{prop: 'id', order: 'descending'}" style="width: 100%" height="500" v-table-auto-height="85">
        <el-table-column align="center" label="编号" prop="id" sortable="custom">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="所属商户" prop="client_id">
          <template slot-scope="scope">
            <span>{{ clientIdParam[scope.row.client_id] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="手机" prop="mobile">
          <template slot-scope="scope">
            <span>{{ scope.row.mobile }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="昵称" prop="nickname">
          <template slot-scope="scope">
            <span>{{ scope.row.nickname }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="用户名" prop="username">
          <template slot-scope="scope">
            <span>{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="状态" prop="status">
          <template slot-scope="scope">
            <span>{{ statusParam[scope.row.status] }}</span>
          </template>
        </el-table-column>
          <el-table-column align="center" label="操作" width="150" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" size="mini" icon="el-icon-edit" @click="infoEditId=scope.row.id">编辑</el-button>
            <el-button type="text" size="mini" icon="el-icon-view" @click="infoViewId=scope.row.id">浏览</el-button>
            <el-button type="text" size="mini" icon="el-icon-delete" @click="handleDelete(scope.row.id)">删除</el-button>
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
    <default-client-user-edit :id="infoEditId" @editDone="getListItems" @editClosed="infoEditId=null"/>
    <default-client-user-view :id="infoViewId" @viewClosed="infoViewId=null"/>
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
import DefaultClientLookup from '@/views/default/client/lookup'
import DefaultClientUserAdd from './add'
import DefaultClientUserEdit from './edit'
import DefaultClientUserView from './view'
export default {
  name: 'Default_Client_User_List',
  components: {
    DefaultClientLookup,
    DefaultClientUserAdd,
    DefaultClientUserEdit,
    DefaultClientUserView
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
      clientIdParam: {},
      statusParam: {},
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
      this.$api.table.getList('Default_Client_User', this.listQuery).then(response => {
        this.listItems = response.data.records
        this.clientIdParam = response.data.client_id_param
        this.statusParam = response.data.status_param
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
        that.$api.table.delInfo('Default_Client_User', { id }).then((response) => {
          that.getListItems()
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
