<template>
  <d2-container>
    <div class="header-container">
      <el-form :model="listQuery" v-show="where==null" label-width="80px">
        <el-row :gutter="20">
          <!-- 单行文本 多行文本 富文本 搜索 -->
          <el-col :md="6">
            <el-form-item label="托盘">
              <el-input v-model="listQuery.query.lk_pallet_id" @keyup.enter.native="handleFilter" placeholder="托盘"/>
            </el-form-item>
          </el-col>
          <!-- 下拉框 单选框 搜索 -->
          <el-col :md="6">
            <el-form-item label="流转类型">
              <input-select v-model="listQuery.query.eq_trans_type" controller="Default_Pallet_Log" field="trans_type" placeholder="流转类型"/>
            </el-form-item>
          </el-col>
          <!-- LookUp单选 搜索 -->
          <el-col :md="6">
            <el-form-item label="起点">
              <default-client-lookup v-model="listQuery.query.eq_from_client_id" keyField="id" valueField="name" placeholder/>
            </el-form-item>
          </el-col>
          <!-- LookUp单选 搜索 -->
          <el-col :md="6">
            <el-form-item label="终点">
              <default-client-lookup v-model="listQuery.query.eq_to_client_id" keyField="id" valueField="name" placeholder/>
            </el-form-item>
          </el-col>
          <!-- 单行文本 多行文本 富文本 搜索 -->
          <el-col :md="6">
            <el-form-item label="车牌号">
              <el-input v-model="listQuery.query.lk_car_no" @keyup.enter.native="handleFilter" placeholder="车牌号"/>
            </el-form-item>
          </el-col>
          <!-- 日期 时间 搜索 -->
          <el-col :md="6">
            <el-form-item label="创建时间">
              <el-date-picker v-model="listQuery.query.bw_created_at" type="datetimerange" range-separator="-" start-placeholder="开始" end-placeholder="结束"  value-format="yyyy-MM-dd HH:mm:ss"  style="width:100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :md="6">
            <div>
              <div class="filter-item">
                <el-button type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
              </div>
              <!--
              <default-pallet-log-add class="filter-item" @addDone="getListItems"/>
              -->
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
        <el-table-column align="center" label="托盘" prop="pallet_id">
          <template slot-scope="scope">
            <span>{{ scope.row.pallet_id }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="流转类型" prop="trans_type">
          <template slot-scope="scope">
            <span>{{ transTypeParam[scope.row.trans_type] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="起点" prop="from_client_id">
          <template slot-scope="scope">
            <span>{{ fromClientIdParam[scope.row.from_client_id] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="终点" prop="to_client_id">
          <template slot-scope="scope">
            <span>{{ toClientIdParam[scope.row.to_client_id] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="车牌号" prop="car_no">
          <template slot-scope="scope">
            <span>{{ scope.row.car_no }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="经度" prop="longitude">
          <template slot-scope="scope">
            <span>{{ scope.row.longitude }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="维度" prop="latitude">
          <template slot-scope="scope">
            <span>{{ scope.row.latitude }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="用户" prop="user_id">
          <template slot-scope="scope">
            <span>{{ userIdParam[scope.row.user_id] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="创建时间" prop="created_at">
          <template slot-scope="scope">
            <span>{{ scope.row.created_at }}</span>
          </template>
        </el-table-column>
          <el-table-column align="center" label="操作" width="150" fixed="right">
          <template slot-scope="scope">
            <!--
            <el-button type="text" size="mini" icon="el-icon-edit" @click="infoEdit=scope.row">编辑</el-button>
            -->
            <el-button type="text" size="mini" icon="el-icon-view" @click="infoViewId=scope.row.id">浏览</el-button>
            <!--
            <el-button type="text" size="mini" icon="el-icon-delete" @click="handleDelete(scope.row.id)">删除</el-button>
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
    <!--
    <default-pallet-log-edit :info="infoEdit" @editDone="getListItems" @editClosed="infoEdit=null"/>
    -->
    <default-pallet-log-view :id="infoViewId" @viewClosed="infoViewId=null"/>
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
import DefaultClientUserLookup from '@/views/default/client/user/lookup'
import DefaultPalletLogAdd from './add'
import DefaultPalletLogEdit from './edit'
import DefaultPalletLogView from './view'
export default {
  name: 'Default_Pallet_Log_List',
  components: {
    DefaultClientLookup,
    DefaultClientUserLookup,
    DefaultPalletLogAdd,
    DefaultPalletLogEdit,
    DefaultPalletLogView
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
      fromClientIdParam: {},
      toClientIdParam: {},
      transTypeParam: {},
      userIdParam: {},
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
      this.$api.table.getList('Default_Pallet_Log', this.listQuery).then(response => {
        this.listItems = response.data.records
        this.fromClientIdParam = response.data.from_client_id_param
        this.toClientIdParam = response.data.to_client_id_param
        this.transTypeParam = response.data.trans_type_param
        this.userIdParam = response.data.user_id_param
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
        that.$api.table.delInfo('Default_Pallet_Log', { id }).then((response) => {
          that.getListItems()
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
