<template>
  <el-checkbox-group v-model="curValue" @change="handleModelChange">
    <el-checkbox v-for="(item, key) in curParamList" :key="key" :label="key" :value="key">{{ item }}</el-checkbox>
  </el-checkbox-group>
</template>
<script>
export default {
  name: 'FormCheckbox',
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
      type: Array,
      default: function () { return [] }
    }
  },
  data () {
    return {
      curValue: this.value,
      curParamList: []
    }
  },
  computed: {},
  watch: {
    value (newValue) {
      if (typeof (newValue) == 'string') {
        if (newValue == '') {
          newValue = '[]'
        }
        newValue = JSON.parse(newValue)
      }
      this.curValue = newValue
    }
  },
  created () {
    this.getParamList()
  },
  methods: {
    getParamList () {
      this.$api.table
        .getFieldParam(this.controller, {
          field: this.field,
          query: this.query
        })
        .then((response) => {
          this.curParamList = response.data
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
<style lang="scss" scoped>
</style>
