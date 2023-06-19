package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/api")
public class UploadController {
    @Value("${spring.resources.static-locations}")
    private String FILE_PATH;
    @Resource
    UploadService uploadService;
    @PostMapping("/upload")
    public R uploadPicture(@RequestParam("file") MultipartFile uploadFile) {
        String url = uploadService.uploadFile(uploadFile);
        if (url.equals("")) {
            return R.error("上传失败");
        }
        return R.success(url).setMsg("上传成功");
    }

    @DeleteMapping("/image/{imageName}")
    public R deletePicture(@PathVariable String imageName) {
        File image = new File(FILE_PATH+imageName);
        if (image.delete()) {
            return R.success().setMsg("删除成功");
        }
        return R.error("删除失败");
    }
}
