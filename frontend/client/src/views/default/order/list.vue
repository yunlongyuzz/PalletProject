<template>
  <d2-container>
    <div class="header-container">
      <el-form :model="listQuery" v-show="where==null" label-width="80px">
        <el-row :gutter="20">
          <!-- 下拉框 单选框 搜索 -->
          <el-col :md="6">
            <el-form-item label="费用类型">
              <input-select v-model="listQuery.query.eq_fee_type" controller="Default_Order" field="fee_type" placeholder="费用类型"/>
            </el-form-item>
          </el-col>
          <!-- LookUp单选 搜索 -->
          <el-col :md="6">
            <el-form-item label="支付方">
              <default-client-lookup v-model="listQuery.query.eq_pay_client_id" keyField="id" valueField="name" placeholder/>
            </el-form-item>
          </el-col>
          <!-- LookUp单选 搜索 -->
          <el-col :md="6">
            <el-form-item label="收款方">
              <default-client-lookup v-model="listQuery.query.eq_collect_client_id" keyField="id" valueField="name" placeholder/>
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
              <default-order-add class="filter-item" @addDone="getListItems"/>
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
        <el-table-column align="center" label="名称" prop="name">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="费用类型" prop="fee_type">
          <template slot-scope="scope">
            <span>{{ feeTypeParam[scope.row.fee_type] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="金额" prop="fee_money">
          <template slot-scope="scope">
            <span>{{ scope.row.fee_money }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="支付方" prop="pay_client_id">
          <template slot-scope="scope">
            <span>{{ payClientIdParam[scope.row.pay_client_id] }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="收款方" prop="collect_client_id">
          <template slot-scope="scope">
            <span>{{ collectClientIdParam[scope.row.collect_client_id] }}</span>
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
    <default-order-edit :info="infoEdit" @editDone="getListItems" @editClosed="infoEdit=null"/>
    -->
    <default-order-view :id="infoViewId" @viewClosed="infoViewId=null"/>
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
import DefaultOrderAdd from './add'
import DefaultOrderEdit from './edit'
import DefaultOrderView from './view'
export default {
  name: 'Default_Order_List',
  components: {
    DefaultClientLookup,
    DefaultOrderAdd,
    DefaultOrderEdit,
    DefaultOrderView
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
      collectClientIdParam: {},
      feeTypeParam: {},
      payClientIdParam: {},
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
      this.$api.table.getList('Default_Order', this.listQuery).then(response => {
        this.listItems = response.data.records
        this.collectClientIdParam = response.data.collect_client_id_param
        this.feeTypeParam = response.data.fee_type_param
        this.payClientIdParam = response.data.pay_client_id_param
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
        that.$api.table.delInfo('Default_Order', { id }).then((response) => {
          that.getListItems()
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
