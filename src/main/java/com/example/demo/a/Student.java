package com.example.demo.a;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("student")
public class Student {
    private String id;

    private String name;
}
