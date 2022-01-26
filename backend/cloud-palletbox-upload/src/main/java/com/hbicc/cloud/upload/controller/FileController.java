package com.hbicc.cloud.upload.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import com.hbicc.cloud.common.utils.ResponseUtil;
import com.hbicc.cloud.common.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 文件上传功能
 */
@RestController
public class FileController {

    @Value("${server.base-path}")
    String basePath;

    @PostMapping(value = "/file/upload", produces = {"application/json;charset=UTF-8"})
    public ResponseUtil FileUpLoad(@RequestParam("file") MultipartFile file, Model model) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return ResponseUtil.error(-404, "文件为空", null);
        }
        // 创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // 指定上传的位置, 相对地址
            String path = DateUtil.format(DateUtil.date(), "yy/MM/dd/");
            // 获取文件的输入流
            inputStream = file.getInputStream();
            // 获取上传时的文件名
            String fileName = file.getOriginalFilename();
            // 获取上传文件的扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf("."));
            // 生成新的文件名
            String newFilename = StrUtil.format("{}{}", IdUtil.objectId(), fileExt);
            // 注意是路径+文件名
            File targetFile = new File(StrUtil.format("{}{}{}", basePath, path, newFilename));
            // 如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"
            // 判断文件父目录是否存在
            if (!targetFile.getParentFile().exists()) {
                // 不存在就创建一个
                ToolUtil.mkDir(targetFile.getParentFile());
            }
            // 获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            // 最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);
            Map<String, Object> f = new LinkedHashMap<String, Object>();
            f.put("savepath", path);
            f.put("savename", newFilename);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("file", f);
            return ResponseUtil.success(1, "上传成功", result);
        } catch (IOException e) {
            e.printStackTrace();
            // 出现异常，则告诉页面失败
            return ResponseUtil.error(-404, "上传失败", null);
        } finally {
            // 无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
