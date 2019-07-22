package com.joyance.demo.base.excel;


import java.io.Serializable;

/**
 * excel 基础导出类
 *
 * @author weilai
 * @since 1.5.3
 */
public class ExportModel implements Serializable {

    /**
     * 导出子路径
     */
    private String subPath;

    /**
     * 文件名
     */
    private String subFilename;

    /**
     * 指定目录
     */
    private String designDir;


    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public String getSubFilename() {
        return subFilename;
    }

    public void setSubFilename(String subFilename) {
        this.subFilename = subFilename;
    }

    public String getDesignDir() {
        return designDir;
    }

    public void setDesignDir(String designDir) {
        this.designDir = designDir;
    }
}

