package jssvc.credit.model;

import java.util.Date;

public class CreditAttachment {
    private Long id;

    private String uploadDah;

    private String description;

    private Date uploadTime;

    private String filePath;

    private String fileName;

    private String webFileName;

    private String suggestbh;

    private String uploadName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadDah() {
        return uploadDah;
    }

    public void setUploadDah(String uploadDah) {
        this.uploadDah = uploadDah == null ? null : uploadDah.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getWebFileName() {
        return webFileName;
    }

    public void setWebFileName(String webFileName) {
        this.webFileName = webFileName;
    }

    public String getSuggestbh() {
        return suggestbh;
    }

    public void setSuggestbh(String suggestbh) {
        this.suggestbh = suggestbh == null ? null : suggestbh.trim();
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName == null ? null : uploadName.trim();
    }

}