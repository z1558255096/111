package com.zyk.weinxin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class ExcelUtils {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int DEFAULT_COLOUMN_WIDTH = 50;

    /**
     * 不带表头的Excel
     *
     * @param headMap
     * @param jsonArray
     * @param datePattern
     * @param colWidth
     * @param out
     */
    public static void exportToExcel(Map<String, String> headMap, JSONArray jsonArray, String datePattern, int colWidth, OutputStream out) {
        if (datePattern == null) datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);//缓存
        //设置单元格风格，居左对齐. 垂直对齐
        CellStyle cs = workbook.createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);

        // 生成一个表格
        SXSSFSheet sheet = workbook.createSheet();
        //设置列宽
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext(); ) {
            String fieldName = iter.next();
            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);
            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 1024);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if (rowIndex == 65535 || rowIndex == 0) {
                if (rowIndex != 0) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示

                SXSSFRow headerRow = sheet.createRow(0); //列头 rowIndex =1
                for (int i = 0; i < headers.length; i++) {
                    SXSSFCell newCell = headerRow.createCell(i);
                    newCell.setCellValue(headers[i]);
                    newCell.setCellStyle(cs);
                }
                rowIndex = 1;//数据内容从 rowIndex=1开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            SXSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++) {
                SXSSFCell newCell = dataRow.createCell(i);

                Object o = jo.get(properties[i]);
                String cellValue = "";
                if (o == null) {
                    cellValue = "";
                } else if (o instanceof Date) {
                    cellValue = new SimpleDateFormat(datePattern).format(o);
                } else if (o instanceof Float || o instanceof Double) {
                    cellValue = new BigDecimal(o.toString()).setScale(2, RoundingMode.HALF_UP).toString();
                } else if (o instanceof TemporalAccessor) {
                    cellValue = STANDARD_DATE_TIME_FORMATTER.format((TemporalAccessor) o);
                } else {
                    cellValue = o.toString();
                }
                if(i==1){
                Integer score= (Integer)jo.get(properties[1]);
                    Font font=workbook.createFont();
                    font.setBold(true); //是否加粗
                if(score>=90){
                    font.setColor(XSSFFont.COLOR_RED); //红色
                }else if(score>=80){
                    font.setColor(IndexedColors.GOLD.getIndex());//橙色
                }else if(score>=60){
                    font.setColor(IndexedColors.SKY_BLUE.getIndex());//橙色
                }else if(score<60){
                    font.setColor(IndexedColors.GREEN.getIndex());//橙色
                }
                    cs.setFont(font);
                }
                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cs);
            }
            rowIndex++;
        }
        // 自动调整宽度
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
            int width = Math.max(15 * 256, Math.min(255 * 256, sheet.getColumnWidth(i) * 12 / 10));
            sheet.setColumnWidth(i, width);
        }
        try {
            workbook.write(out);
            workbook.close();
            boolean flag = workbook.dispose();//释放磁盘空间。处理在磁盘上支持这个工作簿的临时文件。调用该方法将使工作簿不可用。
            System.out.println(flag);//如果所有临时文件都被成功删除，则为真。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //用于转换excel值 王恒 加
    public static String stringValue(XSSFCell cell) {
//        if (CellType.NUMERIC.equals(cell.getCellType())) {
//            return cell.getNumericCellValue() + "";
//        }
        if (null == cell) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public static double doubleValue(XSSFCell cell) {
        return cell.getNumericCellValue();
    }

    public static Long longValue(XSSFCell cell) {
        return Long.valueOf(cell.getRawValue());
    }

    //存在坑   导出的  excel中是数字文本左对齐 转换失败 用string的方法 不要用这个
    public static Integer integerValue(XSSFCell cell) {
        return Integer.valueOf(cell.getRawValue());
    }

    public static BigDecimal bigDecimalValue(XSSFCell cell) {
        return new BigDecimal(cell.getRawValue());
    }


    /**
     * 验证excel是否全部为空
     *
     * @param row      当前行
     * @param firstRow 第一行标题行
     * @return
     */
    public static boolean isAllRowEmpty(Row row, Row firstRow) {
        int count = 0;
        //单元格数量
        int rowCount = firstRow.getLastCellNum() - firstRow.getFirstCellNum();
        //判断多少个单元格为空
        for (int c = 0; c < rowCount; c++) {
            Cell cell = row.getCell(c);
            if (cell == null || cell.getCellType() == CellType.BLANK || isEmpty((cell + "").trim())) {
                count += 1;
            }
        }
        return count == rowCount;
    }

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }
}
