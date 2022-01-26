<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import {
  debounce
} from './util'
const echarts = require('echarts')
require('echarts/theme/macarons')

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      chart: null,
      sidebarElm: null,
      datatime: []
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },

  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })

    if (this.autoResize) {
      this.__resizeHandler = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHandler)
    }

    // 监听侧边栏的变化
    this.sidebarElm = document.getElementsByClassName('d2-theme-container-aside')[0]
    this.sidebarElm && this.sidebarElm.addEventListener('transitionend', this.sidebarResizeHandler)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    if (this.autoResize) {
      window.removeEventListener('resize', this.__resizeHandler)
    }

    this.sidebarElm && this.sidebarElm.removeEventListener('transitionend', this.sidebarResizeHandler)

    this.chart.dispose()
    this.chart = null
  },
  methods: {
    sidebarResizeHandler(e) {
      if (e.propertyName === 'width') {
        this.__resizeHandler()
      }
    },
    setOptions({
      expectedData,
      xlist,
      yunit,
      xunit,
      mname,
      xfontcolor,
      xcolor
    } = {}) {
      this.chart.setOption({
        xAxis: {
          type: 'category',
          data: xlist,
          boundaryGap: false,
          axisTick: {
            show: true
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          },
          axisLabel: {
            formatter: '{value}' + yunit
          }
        },
        series: [{
          name: mname,
          itemStyle: {
            normal: {
              color: xfontcolor,
              lineStyle: {
                color: xcolor,
                width: 2
              }
            }
          },
          smooth: false,
          polyline: false,
          showAllSymbol: true,
          label: {
            normal: {
              show: true,
              position: 'top',
              formatter: '{c}'
            }
          },
          type: 'line',
          data: expectedData,
          markLine: false,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }]
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'default')
      this.setOptions(this.chartData)
    }
  }
}
</script>
