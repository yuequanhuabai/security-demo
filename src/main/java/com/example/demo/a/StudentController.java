package com.example.demo.a;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class StudentController<T> {

    @Resource
    private StudentMapper studentMapper;

//    @Resource
//    private ApplicationEventPublisher eventPublisher;

    @Operation(description = "查詢Student數據")
    @PostMapping("query")
    public T getStudent(@RequestBody Map<String, Object> reqeustParam) {
        Integer pageNo = (Integer) reqeustParam.get("pageNo");
        Integer pageSize = (Integer) reqeustParam.get("pageSize");

        Page<Student> page = new Page<>(pageNo, pageSize);

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        Page<Student> studentPage = studentMapper.selectPage(page, wrapper);
        List<Student> records = studentPage.getRecords();
        Map<String, Object> data = new HashMap<>();
        data.put("records", records);
        data.put("total", studentPage.getTotal());
        return (T) data;
    }


    @RequestMapping("insert")
    public int insertStudent(@RequestBody Student student) {
        String id = UUID.randomUUID().toString();
        student.setId(id);

//        eventPublisher.publishEvent(student);


        return studentMapper.insert(student);
    }

    @RequestMapping("update")
    public int updateStudent(@RequestBody Student student) {
        return studentMapper.updateById(student);
    }

}
