package com.atguigu.export;


import com.atguigu.export.domain.ExcelEntity;
import com.atguigu.export.domain.Shareholder;
import com.atguigu.export.util.EasyExcelUtil;
import com.atguigu.export.util.Symbolic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.rmi.CORBA.Util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EasyExcelExport {


    private EasyExcelUtil excelUtil = new EasyExcelUtil();

    @Test
    public void exportTest01() {

        ExcelEntity excelEntity = new ExcelEntity();
        List<Shareholder> list = new ArrayList<>();

        excelEntity.setName("温衡");
        excelEntity.setAddress("石洞沟");
        excelEntity.setAge(20);
        excelEntity.setTime("2004-4-4");
        excelEntity.setSex("男");
        excelEntity.setShareholderList(list);

        excelEntity.setValid("否");
        excelEntity.setFarmClass("1");

        //设置特殊字符  是（ √）否（ ）
        if ("是".equals(excelEntity.getValid())) {
            excelEntity.setValid("是 ( √ ) " + " 否 ( )");
        } else {
            excelEntity.setValid("是 ( ) " + " 否(" + new String(Character.toChars(Symbolic.TICK.getValue())) + ")");
        }

        if ("1".equals(excelEntity.getFarmClass())) {
            excelEntity.setFarmClass("定栏栓养▇  圈内散养□");
        }


        for (; ; ) {
            Shareholder shareholder = new Shareholder();

            shareholder.setMoney("5000");
            shareholder.setZhanbi("40");
            shareholder.setZhiwei("螺丝工");
            shareholder.setShareholder("傻逼");
            list.add(shareholder);
            if (list.size() == 10) {
                break;
            }
        }

    }

    @Test
    public void test2() throws IOException {
        File fIle = new File("C:\\Users\\wh\\Desktop\\template.xlsx");
        File file = new File("C:\\Users\\wh\\Desktop\\test.xlsx");
        System.out.println(file.createNewFile());

        FileInputStream fileInputStream = new FileInputStream(fIle);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet4");

        sheet.shiftRows(13, sheet.getLastRowNum(), 10, true, false);
        XSSFCellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);

        for (int i = 13; i < 13 + 10; i++) {
            Row row = sheet.createRow(i);
            row.createCell(3).setCellStyle(cellStyle);

            CellRangeAddress cellRange = new CellRangeAddress(i, i, 1, 2);
            CellRangeAddress cellRange2 = new CellRangeAddress(i, i, 4, 7);
            CellRangeAddress cellRange3 = new CellRangeAddress(i, i, 8, 10);
            sheet.addMergedRegion(cellRange);
            sheet.addMergedRegion(cellRange2);
            sheet.addMergedRegion(cellRange3);

            //添加边框
            RegionUtil.setBorderBottom(BorderStyle.THIN, cellRange, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, cellRange, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, cellRange, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, cellRange, sheet);

            //添加边框
            RegionUtil.setBorderBottom(BorderStyle.THIN, cellRange2, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, cellRange2, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, cellRange2, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, cellRange2, sheet);

            //添加边框
            RegionUtil.setBorderBottom(BorderStyle.THIN, cellRange3, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, cellRange3, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, cellRange3, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, cellRange3, sheet);

        }


        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

    }

    public void merge(Sheet sheet, Cell cell) {

        int rowIndex = cell.getRowIndex();
        int colIndex = cell.getColumnIndex();
        sheet = cell.getSheet();
        Row preRow = sheet.getRow(rowIndex - 1);
        Cell preCell = preRow.getCell(colIndex);//获取上一行的该格
        List<CellRangeAddress> list = sheet.getMergedRegions();
        CellStyle cs = cell.getCellStyle();
        cell.setCellStyle(cs);
        for (int i = 0; i < list.size(); i++) {
            CellRangeAddress cellRangeAddress = list.get(i);
            if (cellRangeAddress.containsRow(preCell.getRowIndex()) && cellRangeAddress.containsColumn(preCell.getColumnIndex())) {
                int lastColIndex = cellRangeAddress.getLastColumn();
                int firstColIndex = cellRangeAddress.getFirstColumn();
                CellRangeAddress cra = new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(), firstColIndex, lastColIndex);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet);
                return;
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
