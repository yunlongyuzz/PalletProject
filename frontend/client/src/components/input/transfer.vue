<template>
  <el-transfer v-model="curValue" filterable :titles="titles" :data="paramList" @change="handleModelChange"></el-transfer>
</template>

<script>
export default {
  name: 'InputTransfer',
  props: {
    controller: {
      type: String,
      default: ''
    },
    field: {
      type: String,
      default: ''
    },
    query: {
      type: Object,
      default: function () { return {} }
    },
    placeholder: {
      type: String,
      default: ''
    },
    value: {
      type: [Array],
      default: function () { return [] }
    },
    titles: {
      type: [String, Array],
      default: function () { return ['待选', '已选'] }
    }
  },
  data() {
    return {
      curValue: this.value,
      paramList: []
    }
  },
  watch: {
    value(newValue) {
      if (typeof (newValue) == 'string') {
        if (newValue == '') {
          newValue = '[]'
        }
        newValue = JSON.parse(newValue)
      }
      this.curValue = newValue
    }
  },
  created() {
    this.getParamList()
  },
  methods: {
    getParamList() {
      this.$api.table.getFieldParam(this.controller, {
        field: this.field,
        query: this.query
      }).then(response => {
        var pList = response.data
        for (var i in pList) {
          this.paramList.push({
            key: i,
            label: pList[i]
          })
        }
      })
    },
    handleModelChange(event) {
      const sValue = event
      this.$emit('input', sValue)
    }
  }
}
</script>
