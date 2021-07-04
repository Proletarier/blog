package com.atguigu.export.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.export.domain.ExcelEntity;
import com.atguigu.export.domain.Shareholder;
import com.atguigu.export.util.EasyExcelUtil;
import com.atguigu.export.util.Symbolic;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExportController {

    @Autowired
    private EasyExcelUtil easyExcelUtil;


    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman

        Object result =  get();

        easyExcelUtil.complexFill(response,"C:\\Users\\wh\\Desktop\\template.xlsx","尼玛斯了",JSONObject.parseObject(JSONObject.toJSONString(result)));
    }

    Object get(){
        ExcelEntity excelEntity=new ExcelEntity();
        List<Shareholder> list=new ArrayList<>();

        excelEntity.setName("温衡");
        excelEntity.setAddress("石洞沟");
        excelEntity.setAge(20);
        excelEntity.setTime("2004-4-4");
        excelEntity.setSex("男");
        excelEntity.setShareholderList(list);

        excelEntity.setValid("否");
        excelEntity.setFarmClass("1");

        //设置特殊字符  是（ √）否（ ）
        if("是".equals(excelEntity.getValid())){
            excelEntity.setValid("是 ( √ ) "+ " 否 ( )");
        } else {
            excelEntity.setValid("是 ( ) "+ " 否("+new String(Character.toChars(Symbolic.TICK.getValue()))+")");
        }

        if("1".equals(excelEntity.getFarmClass())){
            excelEntity.setFarmClass("定栏栓养▇  圈内散养□");
        }


        for (;;){
            Shareholder shareholder=new Shareholder();

            shareholder.setMoney("5000");
            shareholder.setZhanbi("40");
            shareholder.setZhiwei("螺丝工");
            shareholder.setShareholder("傻逼");
            list.add(shareholder);
            if (list.size() == 10){
                break;
            }
        }
        return  excelEntity;

    }

}
