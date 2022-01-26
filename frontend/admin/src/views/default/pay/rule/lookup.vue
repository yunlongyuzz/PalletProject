<template>
  <div>
    {{ placeholder }} {{ curSpan }}
    <el-button slot="append" icon="el-icon-more" @click="handleShowLookup"></el-button>
    <el-dialog title="选择数据" :visible.sync="curVisible" append-to-body v-dialog-drag>
      <div class="filter-container">
        <el-form :model="curListQuery" label-width="80px">
          <el-row :gutter="20">
            <el-col :md="6">
              <el-form-item label="编号">
                <el-input
                  v-model="curListQuery.query.lk_id"
                  @keyup.enter.native="getDataList"
                  placeholder="编号"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :md="6">
              <div>
                <el-button
                  class="filter-item"
                  type="primary"
                  icon="el-icon-search"
                  @click="getDataList"
                >搜索</el-button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <el-table
        v-loading="curListLoading"
        :data="curListItems"
        border
        fit
        height="400"
        highlight-current-row
        @sort-change="handleSortChange"
        :default-sort="{prop: 'id', order: 'descending'}"
        style="width: 100%"
        v-table-auto-height="85"
      >
      <el-table-column align="center" label="编号" prop="id" sortable="custom">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="托盘方" prop="pallet_client_id">
        <template slot-scope="scope">
          <span>{{ palletClientIdParam[scope.row.pallet_client_id] }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="费用类型" prop="fee_type">
        <template slot-scope="scope">
          <span>{{ feeTypeParam[scope.row.fee_type] }}</span>
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
      <el-table-column align="center" label="支付方" prop="pay_client_id">
        <template slot-scope="scope">
          <span>{{ payClientIdParam[scope.row.pay_client_id] }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="支付金额" prop="pay_money">
        <template slot-scope="scope">
          <span>{{ scope.row.pay_money }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="created_at">
        <template slot-scope="scope">
          <span>{{ scope.row.created_at }}</span>
        </template>
      </el-table-column>
          <el-table-column align="center" label="操作" width="80" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleSelect(scope.row)">选择</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="curListTotal>0"
        :total="curListTotal"
        :page.sync="curListQuery.page"
        :limit.sync="curListQuery.limit"
        layout="total, sizes, prev, next, jumper"
        @pagination="getDataList"
      />
    </el-dialog>
  </div>
</template>
<script>
import util from '@/libs/util'
import DefaultClientLookup from '@/views/default/client/lookup'
export default {
  name: 'Default_Pay_Rule_List',
  components: {
    DefaultClientLookup,
  },
  filters: {
    parseTime: util.parseTime,
    thumb: util.thumb
  },
  props: {
    keyField: { type: String, default: 'id' },
    valueField: { type: String, default: 'id' },
    placeholder: { type: String, default: '' },
    query: {
      type: Object,
      default: function () {
        return {
          query: {},
          order: [],
          page: 1,
          limit: 10
        }
      }
    },
    value: {
      type: [String, Number, Array],
      default: function () {
        return ''
      }
    },
    span: { type: String, default: '' }
  },
  data () {
    return {
      curValue: {},
      curSpan: this.span,
      curListItems: [],
      palletClientIdParam: {},
      feeTypeParam: {},
      payClientIdParam: {},
      fromClientIdParam: {},
      toClientIdParam: {},
      curListTotal: 0,
      curListLoading: true,
      curListQuery: this.query,
      curVisible: false
    }
  },
  computed: {
  },
  watch: {
    value (newValue) {
      this.curValue = newValue
    },
    span (newValue) {
      this.curSpan = newValue
    }
  },
  methods: {
    getDataList () {
      this.curListLoading = true
      this.$api.table.getList('Default_Pay_Rule', this.curListQuery).then(response => {
        this.curListItems = response.data.records
        this.palletClientIdParam = response.data.pallet_client_id_param
        this.feeTypeParam = response.data.fee_type_param
        this.payClientIdParam = response.data.pay_client_id_param
        this.fromClientIdParam = response.data.from_client_id_param
        this.toClientIdParam = response.data.to_client_id_param
        this.curListTotal = parseInt(response.data.total)
        this.curListLoading = false
      })
    },
    handleSizeChange (val) {
      this.curListQuery.limit = val
      this.getDataList()
    },
    handleCurrentChange (val) {
      this.getDataList.page = val
      this.getDataList()
    },
    handleShowLookup () {
      this.curVisible = true
      this.getDataList()
    },
    handleSortChange (data) {
      const { prop, order } = data
      if (order === 'ascending') {
        this.curListQuery.order = [prop, 'ASC']
      } else {
        this.curListQuery.order = [prop, 'DESC']
      }
      this.getDataList()
    },
    handleSelect (row) {
      this.curValue = row[this.keyField]
      this.curSpan = row[this.valueField]
      this.$emit('input', this.curValue)
      if (this.$parent.$options.componentName === 'ElFormItem') {
        if (this.validateEvent) {
          this.$parent.$emit('el.form.change', [this.curValue])
        }
      }
      this.$emit('change', this.curValue)
      this.curVisible = false
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
