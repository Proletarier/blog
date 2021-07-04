package com.atguigu.export.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;

import com.atguigu.export.handle.MergeHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Component
public class EasyExcelUtil {


    /**
     * 简单的导出
     * @param response
     * @param templateFileName
     * @param fileName
     * @param obj
     * @throws IOException
     */
    public void simpleFill(HttpServletResponse response,String templateFileName, String fileName,Object obj) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(obj);

    }




    /**
     * 带有列表的导出
     * @param response
     * @param templateFileName
     * @param obj
     */
    public void complexFill(HttpServletResponse response,String templateFileName, String fileName,Object obj) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().registerWriteHandler(new MergeHandler()).build();

        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

        if(obj instanceof List){
            excelWriter.fill(obj, fillConfig, writeSheet);
        }else if(obj instanceof Map){
            excelWriter.fill(obj ,writeSheet);
            Map map= (Map) obj;

            map.keySet().forEach(k ->{
                if (map.get(k) instanceof  List){
                    excelWriter.fill(map.get(k), fillConfig, writeSheet);
                }
                else {
                    if(!isBaseType(map.get(k)))
                       excelWriter.fill(obj ,writeSheet);
                }
            });
        }
        excelWriter.finish();
    }


    public  boolean isBaseType(Object object) {
        Class className = object.getClass();
        if (className.equals(java.lang.Integer.class) ||
                className.equals(java.lang.Byte.class) ||
                className.equals(java.lang.Long.class) ||
                className.equals(java.lang.Double.class) ||
                className.equals(java.lang.Float.class) ||
                className.equals(java.lang.Character.class) ||
                className.equals(java.lang.Short.class) ||
                className.equals(java.lang.Boolean.class) ||
                className.equals(java.lang.String.class)) {
            return true;
        }
        return false;
    }


}
