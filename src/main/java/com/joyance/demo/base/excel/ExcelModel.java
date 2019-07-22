package com.joyance.demo.base.excel;

import java.io.File;
import java.util.List;

/**
 * 导出excel类
 *
 * @author weilai
 * @since 1.5.3
 */
public class ExcelModel<T> extends ExportModel implements ImportModel {
    /**
     * 表头,比如 title = ["列1","列2","列3","列4"]
     */
    private String[] title;
    /**
     * 工作表
     */
    private String sheet;

    /**
     * 导入的文件名
     */
    private String filename;
    /**
     * 导入的文件
     */
    private File file;

    /**
     * 开始行
     */
    private int startRow = 1;

    /**
     * 结束行, 如果 endRow <= 0 , 则代表倒数第几行
     */
    private int endRow = 0;

    /**
     * 每次间隔几行获取一次
     */
    private int rowInterval = 1;

    /**
     * 数据
     */
    private List<T> data;


    public ExcelModel(String filename) {
        this.filename = filename;
    }

    public ExcelModel(File file) {
        this.file = file;
    }

    public ExcelModel(String[] title, List<T> data) {
        this.title = title;
        this.data = data;
    }

    public ExcelModel( List<T> data) {
        this.data = data;
    }

    public String getSheet() {
        return sheet == null ? "DATA" : sheet;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getRowInterval() {
        return rowInterval;
    }

    public void setRowInterval(int rowInterval) {
        this.rowInterval = rowInterval;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
