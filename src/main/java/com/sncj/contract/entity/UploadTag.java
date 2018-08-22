package com.sncj.contract.entity;

import java.io.Serializable;
import java.util.UUID;

public class UploadTag implements Serializable {

    // 上传后文件名生成策略
    public enum NamePolicy {
        ALL,
        UUID,
        UUID_AND_ORIGINAL_FILENAME,
    }

    private NamePolicy namePolicy;
    private String originalFilename = "";
    private String fullname = ""; // 包含路径文件名扩展名完整信息
    private String domain = "";
    private String path = "";
    private String filename = "";
    private String ext = "";
    private int size = 0;

    public UploadTag(String originalFilename, String domain, String path) {

        this(NamePolicy.UUID, originalFilename, domain, path);
    }

    public UploadTag(NamePolicy namePolicy, String originalFilename, String domain, String path) {

        if (null != originalFilename) {

            this.namePolicy = namePolicy;
            this.domain = domain;
            this.path = path;
            this.originalFilename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            String[] str = this.originalFilename.split("\\.");

            if (str.length >= 1) {
                switch (namePolicy) {

                    case UUID_AND_ORIGINAL_FILENAME:
                        this.filename = UUID.randomUUID().toString() + "_" + str[0];
                        break;

                    case UUID:
                    default:
                        this.filename = UUID.randomUUID().toString();
                        break;
                }

                if (str.length >= 2) {
                    this.ext = str[1];
                    if (!this.ext.equals("")) this.filename += "." + this.ext;
                }
            }
        }
    }

    public NamePolicy getNamePolicy() {
        return namePolicy;
    }

    public void setNamePolicy(NamePolicy namePolicy) {
        this.namePolicy = namePolicy;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
