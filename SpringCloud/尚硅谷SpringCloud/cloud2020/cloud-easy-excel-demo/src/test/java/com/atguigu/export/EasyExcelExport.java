package com.atguigu.export;


import com.atguigu.export.domain.ExcelEntity;
import com.atguigu.export.domain.Shareholder;
import com.atguigu.export.util.EasyExcelUtil;
import com.atguigu.export.util.Symbolic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EasyExcelExport {


    private EasyExcelUtil excelUtil =new EasyExcelUtil();

    @Test
    public  void  exportTest01(){

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



    }


    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}
