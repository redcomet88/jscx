package jssvc.base.model;

public class Constant {
    private Long id;

    private String type;

    private String enKey;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getEnKey() {
        return enKey;
    }

    public void setEnKey(String enKey) {
        this.enKey = enKey == null ? null : enKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}