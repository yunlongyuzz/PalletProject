<template>
  <d2-container class="page">
    <div class="dashboard">
      <div slot="header" class="clearfix filter-container">
        <h2 class="title">今日实时数据</h2>
        <el-row class="list">
          <el-col :span="6" class="listdetail">
            <el-card :body-style="{ padding: '10px' }">
              <div style="padding: 10px;">
                <h3 class="ycolor">今日销售额</h3>
                <div class="bottom clearfix">
                  <p>
                    <span class="nums">{{ todayOrderData.total_amount }}</span>元
                  </p>
                  <p>
                    <span>昨日销售额：</span>
                    <span>{{ yesterdayOrderData.total_amount }}</span>元
                  </p>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6" class="listdetail">
            <el-card :body-style="{ padding: '10px' }">
              <div style="padding: 10px;">
                <h3 class="ycolor">今日订单量</h3>
                <div class="bottom clearfix">
                  <p>
                    <span class="nums">{{ todayOrderData.order_num }}</span>单
                  </p>
                  <p>
                    <span>昨日订单量：</span>
                    <span>{{ yesterdayOrderData.order_num }}</span>单
                  </p>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6" class="listdetail">
            <el-card :body-style="{ padding: '10px' }">
              <div style="padding: 10px;">
                <h3 class="ycolor">今日客单价</h3>
                <div class="bottom clearfix">
                  <p>
                    <span class="nums">{{ todayOrderData.price }}</span>元
                  </p>
                  <p>
                    <span>昨日客单价：</span>
                    <span>{{ yesterdayOrderData.price }}</span>元
                  </p>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6" class="listdetail">
            <el-card :body-style="{ padding: '10px' }">
              <div style="padding: 10px;">
                <h3 class="ycolor">不在线设备数</h3>
                <div class="bottom clearfix">
                  <p>
                    <span class="nums">{{ deviceCount.offline_count }}</span>台
                  </p>
                  <p>
                    <span>在线设备数：</span>
                    <span>{{ deviceCount.online_count }}</span>台
                  </p>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div class="body">
        <h2>历史统计分析</h2>
        <div class="sizes">
          累计销售额：
          <span class="datainfo">{{totalOrderData.total_amount}}</span>元
          <span class="datainfo">累计订单量：</span>
          <span class="datainfo">{{totalOrderData.order_num}}</span>单
          <!-- <span class="datainfo">累计用户数：</span><span>{{totalInfo.totalCount}}</span>人 -->
        </div>
        <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="昨日" name="1day">
            <el-col :span="6">
              <h3 class="ycolor">销售额</h3>
              <div>
                <span class="money">{{compareInfo.total_amount}}</span>元
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">订单量</h3>
              <div>
                <span class="money">{{compareInfo.order_num}}</span>单
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">客单价</h3>
              <div>
                <span class="money">{{compareInfo.price}}</span>元
              </div>
            </el-col>
          </el-tab-pane>
          <el-tab-pane label="本周" name="week">
            <el-col :span="6">
              <h3 class="ycolor">销售额</h3>
              <div>
                <span class="money">{{compareInfo.total_amount}}</span>元
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">订单量</h3>
              <div>
                <span class="money">{{compareInfo.order_num}}</span>单
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">客单价</h3>
              <div>
                <span class="money">{{compareInfo.price}}</span>元
              </div>
            </el-col>
          </el-tab-pane>
          <el-tab-pane label="本月" name="month">
            <el-col :span="6">
              <h3 class="ycolor">销售额</h3>
              <div>
                <span class="money">{{compareInfo.total_amount}}</span>元
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">订单量</h3>
              <div>
                <span class="money">{{compareInfo.order_num}}</span>单
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">客单价</h3>
              <div>
                <span class="money">{{compareInfo.price}}</span>元
              </div>
            </el-col>
          </el-tab-pane>
          <el-tab-pane label="近7日" name="7day">
            <el-col :span="6">
              <h3 class="ycolor">销售额</h3>
              <div>
                <span class="money">{{compareInfo.total_amount}}</span>元
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">订单量</h3>
              <div>
                <span class="money">{{compareInfo.order_num}}</span>单
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">客单价</h3>
              <div>
                <span class="money">{{compareInfo.price}}</span>元
              </div>
            </el-col>
          </el-tab-pane>
          <el-tab-pane label="近30日" name="30day">
            <el-col :span="6">
              <h3 class="ycolor">销售额</h3>
              <div>
                <span class="money">{{compareInfo.total_amount}}</span>元
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">订单量</h3>
              <div>
                <span class="money">{{compareInfo.order_num}}</span>单
              </div>
            </el-col>
            <el-col :span="6">
              <h3 class="ycolor">客单价</h3>
              <div>
                <span class="money">{{compareInfo.price}}</span>元
              </div>
            </el-col>
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-row style="padding:30px 0px 0;margin-bottom:0px;">
        <el-card shadow="always">
          <h4 style='text-align: center;'>最近30天销售额变化</h4>
          <line-chart :chartData="lineChartData" />
        </el-card>
      </el-row>
      <el-row style="padding:30px 0px 0;margin-bottom:0px;">
        <el-card shadow="always">
          <h4 style='text-align: center;'>最近30天订单数变化</h4>
          <line-chart :chartData="lineChartData1" />
        </el-card>
      </el-row>
      <el-row style="padding:30px 0px 0;margin-bottom:0px;">
        <el-card shadow="always">
          <h4 style='text-align: center;'>最近30天客单价变化</h4>
          <line-chart :chartData="lineChartData2" />
        </el-card>
      </el-row>

    </div>
  </d2-container>
</template>
<script>
import LineChart from '@/components/Charts/LineChart'

export default {
  name: 'indexx',
  components: {
    LineChart
  },
  data() {
    return {
      todayOrderData: {
        client_num: 0,
        device_num: 0,
        order_num: 0,
        total_amount: 0,
        user_num: 0,
        price: 0
      },
      yesterdayOrderData: {
        client_num: 0,
        device_num: 0,
        order_num: 0,
        total_amount: 0,
        user_num: 0,
        price: 0
      },
      totalOrderData: {
        client_num: 0,
        device_num: 0,
        user_num: 0,
        total_amount: 0,
        order_num: 0
      },
      deviceCount: { offline_count: 0, online_count: 0 },
      activeName: '1day',
      compareInfo: {},

      lineChartData: {},
      lineChartData1: {},
      lineChartData2: {}
    }
  },

  mounted() {
    this.getData()
    this.getTab()
  },
  methods: {
    getData() {
      this.$api.post.postData('report/profile', {
      }).then(response => {
        this.todayOrderData = response.data.todayOrderData
        this.yesterdayOrderData = response.data.yesterdayOrderData
        this.deviceCount = response.data.deviceCount
        this.totalOrderData = response.data.totalOrderData

        const xlist = response.data.dayThirty
        const lineChartData = {
          expectedData: response.data.totalAmountThirty,
          xlist: xlist,
          xunit: '元',
          yunit: '元',
          mname: '销售额',
          xfontcolor: '#48b',
          xcolor: '#48b'
        }
        const lineChartData1 = {
          expectedData: response.data.orderNumThirty,
          xlist: xlist,
          xunit: '单',
          yunit: '单',
          mname: '订单数',
          xfontcolor: '#d87a80',
          xcolor: '#d87a80'
        }
        const lineChartData2 = {
          expectedData: response.data.priceAmountThirty,
          xlist: xlist,
          xunit: '元',
          yunit: '元',
          mname: '客单价',
          xfontcolor: '#2ec7c9',
          xcolor: '#2ec7c9'
        }
        this.lineChartData = lineChartData
        this.lineChartData1 = lineChartData1
        this.lineChartData2 = lineChartData2
      })
    },
    created() {
    },
    getTab() {
      this.$api.post.postData('report/profile', {
        type: this.activeName
      }).then(response => {
        this.compareInfo = response.data.orderData
      })
    },
    handleClick(tab, event) {
      this.getTab()
    },
    handleSetLineChartData(type) {
      // this.lineChartData = lineChartData[type]
    }
  }
}
</script>
<style lang="scss" scoped>
.dashboard {
  padding: 10px;
  padding-top: 10px;

  .title {
    margin: 10px 0;
  }

  .list {
    margin: -20px;
    margin-top: 10px;
  }

  .listdetail {
    padding: 20px;
  }

  .nums {
    font-size: 50px;
    color: #555a6e;
    font-weight: bold;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .sizes {
    font-weight: bold;
    color: #555a6e;
    margin-bottom: 15px;
  }

  .datainfo {
    padding-left: 10px;
  }

  .money {
    font-size: 40px;
    color: #555a6e;
    font-weight: bold;
  }

  .top {
    margin-top: 10px;
    font-weight: bold;
    color: #555a6e;
  }

  .ycolor {
    color: #555a6e;
  }

  .lcolor {
    color: #0077aa;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both;
  }
}
</style>
