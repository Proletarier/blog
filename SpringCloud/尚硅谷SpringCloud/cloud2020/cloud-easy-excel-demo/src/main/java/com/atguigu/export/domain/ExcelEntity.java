package com.atguigu.export.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelEntity {

    private  String  name;

    private String time;

    private String  address;

    private String  business;

    private Integer age;

    private String sex;

    private String valid;

    private String farmClass;

    List<Shareholder> shareholderList;

}
