package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.SchoolModel;
import com.xiyuan.orange.Service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {
    @Resource
    SchoolService schoolService;
    @GetMapping("/school")
    public R getSchoolList() {
        List<SchoolModel> schoolList = schoolService.getSchoolList();
        return R.success(schoolList).setMsg("获取学校列表成功");
    }
}
