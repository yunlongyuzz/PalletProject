<template>
  <div class="upload-container">
    <div class="image-preview">
      <div v-show="prevUrl.length>1" class="image-preview-wrapper">
        <img :src="prevUrl">
        <div class="image-preview-action">
          <i class="el-icon-delete" @click="rmImage"/>
        </div>
      </div>
    </div>
    <el-upload
      :multiple="false"
      :show-file-list="false"
      :on-success="handleImageSuccess"
      class="image-uploader"
      drag
      :action="postUrl"
      :headers="header"
    >
      <i class="el-icon-plus"/>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: 'SingleImageUpload',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      tempUrl: '',
      prevUrl: process.env.VUE_APP_UPLOAD + '/no.jpg',
      postUrl: process.env.VUE_APP_API + 'file/upload',
      header: {
      // token: sessionStorage.token
      },
    }
  },
  mounted () {
    this.prevUrl = process.env.VUE_APP_UPLOAD + this.value
  },
  watch: {
    value (val) {
      this.prevUrl = process.env.VUE_APP_UPLOAD + this.value
    }
  },
  methods: {
    rmImage () {
      this.emitInput('')
    },
    emitInput (val) {
      this.$emit('input', val)
    },
    handleImageSuccess (response, file, fileList) {
      console.log(process.env.VUE_APP_UPLOAD);
      if (response.code === 1) {
        this.tempUrl =
          '/' + response.data.file.savepath + response.data.file.savename
        this.emitInput(this.tempUrl)
        this.prevUrl = process.env.VUE_APP_UPLOAD + this.tempUrl
      }
    },
    beforeUpload () {
      // const _self = this;
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.upload-container {
  width: 100%;
  position: relative;
  .image-uploader {
    width: 300px;
    height: 80px;
    margin-left: 20px;
    float: left;
  }
  .image-preview {
    width: 80px;
    height: 80px;
    position: relative;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    float: left;
    overflow: hidden;
    // margin-left: 50px;
    .image-preview-wrapper {
      position: relative;
      width: 100%;
      height: 100%;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .image-preview-action {
      position: absolute;
      width: 100%;
      height: 100%;
      left: 0;
      top: 0;
      cursor: default;
      text-align: center;
      color: #fff;
      opacity: 0;
      font-size: 20px;
      background-color: rgba(0, 0, 0, 0.5);
      transition: opacity 0.3s;
      cursor: pointer;
      text-align: center;
      line-height: 80px;
      .el-icon-delete {
        font-size: 36px;
      }
    }
    &:hover {
      .image-preview-action {
        opacity: 1;
      }
    }
  }
}
</style>
