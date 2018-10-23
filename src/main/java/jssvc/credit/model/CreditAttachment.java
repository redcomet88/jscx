package jssvc.credit.model;

import java.util.Date;

public class CreditAttachment {
    private Long id;

    private String uploaddah;

    private String description;

    private Date uploadtime;

    private String filepath;

    private String filename;

    private String webfilename;

    private String suggestbh;

    private String uploadname;

    public CreditAttachment(Long id, String uploaddah, String description, Date uploadtime, String filepath, String filename, String webfilename, String suggestbh, String uploadname) {
        this.id = id;
        this.uploaddah = uploaddah;
        this.description = description;
        this.uploadtime = uploadtime;
        this.filepath = filepath;
        this.filename = filename;
        this.webfilename = webfilename;
        this.suggestbh = suggestbh;
        this.uploadname = uploadname;
    }

    public CreditAttachment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploaddah() {
        return uploaddah;
    }

    public void setUploaddah(String uploaddah) {
        this.uploaddah = uploaddah == null ? null : uploaddah.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getWebfilename() {
        return webfilename;
    }

    public void setWebfilename(String webfilename) {
        this.webfilename = webfilename == null ? null : webfilename.trim();
    }

    public String getSuggestbh() {
        return suggestbh;
    }

    public void setSuggestbh(String suggestbh) {
        this.suggestbh = suggestbh == null ? null : suggestbh.trim();
    }

    public String getUploadname() {
        return uploadname;
    }

    public void setUploadname(String uploadname) {
        this.uploadname = uploadname == null ? null : uploadname.trim();
    }
}