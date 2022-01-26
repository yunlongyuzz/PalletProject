<template>
  <el-select
    v-model="curValue"
    :placeholder="placeholder"
    style="width:100%;"
    @change="handleModelChange"
  >
    <el-option v-for="(item, key) in paramList" :key="key" :label="item" :value="key"/>
  </el-select>
</template>
<script>
export default {
  name: 'InputSelect',
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
      type: [String, Number, Boolean],
      default: function () { return '' }
    },
    auto: {
      type: Boolean,
      default: true
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
    },
    query (newValue) {
      this.query = newValue
      this.getParamList()
    }
  },
  created () {
    if (this.auto) {
      this.getParamList()
    }
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
