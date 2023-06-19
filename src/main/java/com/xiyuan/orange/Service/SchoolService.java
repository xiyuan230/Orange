package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.SchoolMapper;
import com.xiyuan.orange.Model.SchoolModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolService {
    @Resource
    SchoolMapper schoolMapper;
    public List<SchoolModel> getSchoolList() {
        List<SchoolModel> schoolList = schoolMapper.getSchoolList();
        return schoolList;
    }
}
