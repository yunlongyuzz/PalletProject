<template>
  <el-radio-group v-model="curValue" @change="handleModelChange">
    <el-radio v-for="(item, key) in paramList" :key="key" :label="key">{{ item }}</el-radio>
  </el-radio-group>
</template>
<script>
export default {
  name: 'InputRadio',
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
    value: {
      type: [String, Number, Boolean],
      default: function () { return '' }
    }
  },
  data () {
    return {
      curValue: this.value,
      paramList: []
    }
  },
  computed: {},
  watch: {
    value (newValue) {
      this.curValue = newValue + ''
    }
  },
  created () {
    this.getParamList()
  },
  methods: {
    getParamList () {
      this.$api.table.getFieldParam(this.controller, {
        field: this.field,
        query: this.query
      }).then(response => {
        this.paramList = response.data
      })
    },
    handleModelChange (event) {
      const sValue = event
      this.$emit('input', sValue)
      if (this.$parent.$options.componentName === 'ElFormItem') {
        if (this.validateEvent) {
          this.$parent.$emit('el.form.change', [sValue])
        }
      }
      this.$emit('change', sValue)
    }
  }
}
</script>
<style>
</style>
