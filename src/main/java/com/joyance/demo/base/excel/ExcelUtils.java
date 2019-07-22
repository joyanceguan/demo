package com.joyance.demo.base.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joyance.demo.utils.FileUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel工具类
 *
 * @author weilai
 * @since 1.5.3
 */
public class ExcelUtils {


    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);


    /**
     * 生成Workbook
     * @param model 导出的配置类
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
	public static XSSFWorkbook generateWorkbook(ExcelModel model) throws NoSuchFieldException, IllegalAccessException {
        /**
         * step 1: 校验数据
         */

        if (isEmpty(model.getData())) {
            throw new IllegalArgumentException("export args error, data is empty");
        }


        Map<Integer, Field> fieldMap = parseExportData(model.getData().get(0).getClass());
        if ((fieldMap == null || fieldMap.size() == 0) && !(model.getData().get(0) instanceof List)) {
            throw new IllegalArgumentException("export args error, please add ColumnNum annotation to class");
        }

        /**
         * step 2: 创建excel
         */
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet(model.getSheet());

        int rowNum = 0;

        XSSFRow row;

        /**
         * step 3: 创建标题
         */
        if (model.getTitle() != null && model.getTitle().length > 0) {
            row = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (String title : model.getTitle()) {
                XSSFCell cell = row.createCell(cellNum++);
                cell.setCellValue(title);
            }
        }else {
            Map<Integer, String> titleMap = parseExportTitle(model.getData().get(0).getClass());
            if (titleMap != null && titleMap.size() > 0) {
                row = sheet.createRow(rowNum++);
                for (Integer cellNum : titleMap.keySet()) {
                    XSSFCell cell = row.createCell(cellNum);
                    cell.setCellValue(titleMap.get(cellNum));
                }
            }
        }

        /**
         * step 4: 填充数据
         */
        if (model.getData().get(0) instanceof List) {
            for (List one : (List<List>) model.getData()) {
                row = sheet.createRow(rowNum++);
                for (int cellNum = 0; cellNum < one.size(); ++cellNum) {
                    XSSFCell cell = row.createCell(cellNum);
                    setValue(cell, one.get(cellNum));
                }
            }
        } else {
            for (int i = 0;i < model.getData().size(); i++) {
                row = sheet.createRow(rowNum++);
                assert fieldMap != null;
                for (Integer cellNum : fieldMap.keySet()) {
                    XSSFCell cell = row.createCell(cellNum);
                    Field field = model.getData().get(i).getClass().getDeclaredField(fieldMap.get(cellNum).getName());
                    field.setAccessible(true);
                    setValue(cell, field.get(model.getData().get(i)));
                }
            }
        }
        return workbook;
    }

    /**
     * 导出excel
     * @param model 导出的配置类
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void export(ExcelModel model) throws Exception {
        XSSFWorkbook workbook = generateWorkbook(model);

        /**
         * step 5: 生成文件
         */
        FileUtils.createDirWhenNotExists(model.getDesignDir());
        String filename = model.getFilename();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(String.format("%s/%s",model.getDesignDir(),filename));
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            throw new Exception("生成文件异常", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    log.error("关闭导出文件流失败", e);
                }
            }
        }
//        正式操作时，要返回导出的文件名，操作要用到
//        return filename.replaceFirst(ROOT_PATH, "");
    }

    /**
     * 导入
     * @param excelImportModel 导入的模板类配置
     * @param type 导入数据对应的java类
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static <T extends Serializable> List<T> from(ExcelModel excelImportModel, Class<T> type) throws NoSuchFieldException, IOException, InvalidFormatException {
        /**
         * step 1: 校验参数
         */
        if (excelImportModel.getFile() == null && StringUtils.isEmpty(excelImportModel.getFilename())) {
            throw new IllegalArgumentException("file and filename is null");
        }

        File file = excelImportModel.getFile() == null ? new File(String.format("%s/%s", excelImportModel.getDesignDir(), excelImportModel.getFilename())) : excelImportModel.getFile();

        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("no such file or file is directory");
        }

        Workbook workbook = create(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);

        ExcelParseImportData excelParseData = parseImportData(type, sheet);

        /**
         * step 2: 循环每一个sheet
         */
        List<T> data = new ArrayList<>();

        int lastRow = excelImportModel.getEndRow() > 0 ? excelImportModel.getEndRow() > sheet.getLastRowNum() ? sheet.getLastRowNum() : excelImportModel.getEndRow() : sheet.getLastRowNum() + excelImportModel.getEndRow();

        /**
         * step 3: 循环每一行
         */
        for (int rowIndex = excelImportModel.getStartRow(); rowIndex <= lastRow; rowIndex += excelImportModel.getRowInterval()) {
            Row row = sheet.getRow(rowIndex);

            /**
             * step 4: 循环每一列
             */
            JSONObject json = JSON.parseObject(excelParseData.getInitJson().toJSONString());
            if (excelParseData.getRowNum() != null) {
                json.put(excelParseData.getRowNum().getName(), rowIndex);
            }
            for (Integer colIndex : excelParseData.getFieldMap().keySet()) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    log.warn(String.format("请检查数据格式, 行 = %s , 列 = %s", rowIndex + 1, colIndex + 1));
                    continue;
                }
                Field field = excelParseData.getFieldMap().get(colIndex);
                json.put(field.getName(), getValue(field.getType(), cell));
            }

            T item = JSON.parseObject(json.toJSONString(), type);

            // 校验值是否合法

            data.add(item);
        }


        if (isEmpty(data)) {
            throw new IllegalArgumentException("parse data is empty");
        }

        // 正式使用时，要删除文件
//        file.delete();

        return data;
    }


    public static Workbook create(InputStream in) throws
            IOException, InvalidFormatException {
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return new HSSFWorkbook(in);
        }
        if (POIXMLDocument.hasOOXMLHeader(in)) {
            return new XSSFWorkbook(OPCPackage.open(in));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }

    public static ExcelParseImportData parseImportData(Class<?> type, Sheet sheet) {
        Field[] declaredFields = type.getDeclaredFields();

        Map<Integer, Field> fieldMap = new HashMap<>();

        JSONObject initJson = new JSONObject();

        Field rowNum = null;

        int maxCol = 0;
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                if (columnNum.value() <= 0) {
                    throw new IllegalArgumentException(String.format("%s column num must more than 0, the column num start 1", field.getName()));
                }
                Integer num = columnNum.value() - 1;
                if (fieldMap.containsKey(num)) {
                    throw new IllegalArgumentException(String.format("%s and %s column num is duplicate", fieldMap.get(num).getName(), field.getName()));
                }
                fieldMap.put(num, field);

                if (num > maxCol) {
                    maxCol = num;
                }
            } else if (field.isAnnotationPresent(RowNum.class)) {
                rowNum = field;
            } else if (field.isAnnotationPresent(ColumnRowValue.class)) {
                ColumnRowValue columnRowValue = field.getAnnotation(ColumnRowValue.class);
                if (sheet.getLastRowNum() < columnRowValue.row() - 1) {
                    throw new IllegalArgumentException(String.format("%s row is not exists , at row = %s , col = %s", field.getName(), columnRowValue.row(), columnRowValue.col()));
                }
                Row row = sheet.getRow(columnRowValue.row() - 1);
                if (row.getLastCellNum() < columnRowValue.col() - 1) {
                    throw new IllegalArgumentException(String.format("%s col is not exists , at row = %s , col = %s", field.getName(), columnRowValue.row(), columnRowValue.col()));
                }
                initJson.put(field.getName(), getValue(field.getType(), row.getCell(columnRowValue.col() - 1)));
            }
        }

        if (fieldMap.size() == 0) {
            throw new IllegalArgumentException("please add ColumnNum annotation to class " + type.getTypeName());
        }

        ExcelParseImportData excelParseData = new ExcelParseImportData();
        excelParseData.setFieldMap(fieldMap);
        excelParseData.setInitJson(initJson);
        excelParseData.setMaxCol(maxCol);
        excelParseData.setRowNum(rowNum);
        return excelParseData;
    }

    public static Map<Integer, Field> parseExportData(Class<?> type) {
        Map<Integer, Field> fieldMap = new HashMap<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                Integer num = columnNum.value() - 1;
                fieldMap.put(num, field);
            }
        }
        return fieldMap;
    }

    public static Map<Integer, String> parseExportTitle(Class<?> type) {
        Map<Integer, String> fieldMap = new HashMap<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ColumnNum.class)) {
                ColumnNum columnNum = field.getAnnotation(ColumnNum.class);
                Integer num = columnNum.value() - 1;
                fieldMap.put(num, columnNum.title());
            }
        }
        return fieldMap;
    }


    /**
     * 根据值的类型设置单元格的值
     *
     * @param cell  单元格
     * @param value 值
     */
    public static void setValue(XSSFCell cell, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Date) {
            cell.setCellValue(formatDateToString((Date) value));
        } else if (value instanceof Double) {
            cell.setCellValue(Double.parseDouble(getStrValue(value)));
        } else if (value instanceof Boolean) {
            cell.setCellValue(Boolean.parseBoolean(getStrValue(value)));
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(Integer.parseInt(getStrValue(value)));
        } else if (value instanceof Long) {
            cell.setCellValue(Long.parseLong(getStrValue(value)));
        } else {
            cell.setCellValue(getStrValue(value));
        }
    }


    /**
     * 根据值的类型设置单元格的值
     */
    public static Object getValue(Class<?> type, Cell cell) {
        try {
            if (type.isAssignableFrom(Date.class)) {
                return cell.getDateCellValue();
            } else if (type.isAssignableFrom(Double.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Double.parseDouble(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Boolean.class)) {
                return cell.getBooleanCellValue();
            } else if (type.isAssignableFrom(BigDecimal.class)) {
                try {
                    return new BigDecimal(cell.getNumericCellValue());
                } catch (Exception e) {
                    return new BigDecimal(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Integer.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Integer.parseInt(cell.getStringCellValue());
                }
            } else if (type.isAssignableFrom(Long.class)) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return Long.parseLong(cell.getStringCellValue());
                }
            }
        } catch (Exception e) {
            log.error("解析错误", e);
        }
        return getCellValue(cell);
    }

    public static class ExcelParseImportData {
        private Map<Integer, Field> fieldMap;

        private JSONObject initJson;

        private Field rowNum;

        private int maxCol;

        public Map<Integer, Field> getFieldMap() {
            return fieldMap;
        }

        public void setFieldMap(Map<Integer, Field> fieldMap) {
            this.fieldMap = fieldMap;
        }

        public JSONObject getInitJson() {
            return initJson;
        }

        public void setInitJson(JSONObject initJson) {
            this.initJson = initJson;
        }

        public Field getRowNum() {
            return rowNum;
        }

        public void setRowNum(Field rowNum) {
            this.rowNum = rowNum;
        }

        public int getMaxCol() {
            return maxCol;
        }

        public void setMaxCol(int maxCol) {
            this.maxCol = maxCol;
        }
    }

    public static class ExcelParseExportData {
        private Map<Integer, Field> fieldMap;

        private JSONObject initJson;

        private Field rowNum;

        private int maxCol;

        public Map<Integer, Field> getFieldMap() {
            return fieldMap;
        }

        public void setFieldMap(Map<Integer, Field> fieldMap) {
            this.fieldMap = fieldMap;
        }

        public JSONObject getInitJson() {
            return initJson;
        }

        public void setInitJson(JSONObject initJson) {
            this.initJson = initJson;
        }

        public Field getRowNum() {
            return rowNum;
        }

        public void setRowNum(Field rowNum) {
            this.rowNum = rowNum;
        }

        public int getMaxCol() {
            return maxCol;
        }

        public void setMaxCol(int maxCol) {
            this.maxCol = maxCol;
        }
    }

    public static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();

            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();

            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
        }

        return null;
    }

    /**
     * 获取str值
     * @param object 获取值得对象
     * @return str值
     */
    public static String getStrValue(Object object){
        return object == null ? null : String.valueOf(object);
    }

    /**
     * 日期转换
     * @param date 需要转换的日期
     * @return 转换格式后的日期
     */
    public static String formatDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private static <T> boolean isEmpty(List<T> list){
    	if(list == null || list.isEmpty()){
    		return true;
    	}
    	return false;
    }
    
    public static void main(String[] args) throws NoSuchFieldException, InvalidFormatException, IOException {
    	List<MixLoanUserHistoryExcel> dataList = new ArrayList<>();
        ExcelModel<MixLoanUserHistoryExcel> excelExcelModel = new ExcelModel<MixLoanUserHistoryExcel>("test003.xlsx");
        excelExcelModel.setDesignDir("/Users/joyance/Desktop");
        dataList = ExcelUtils.from(excelExcelModel,MixLoanUserHistoryExcel.class);
        for(MixLoanUserHistoryExcel d : dataList){
        	System.out.println(d.getLoanerId() + ":"+d.getFirstCashSuccessTime());
        }
        System.out.println("==================");
        System.out.println(JSON.toJSONString(dataList));
	}
}
