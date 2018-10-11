package jssvc.user.model;

public class Menu {
    private String id;

    private Integer sort;

    private Integer level;

    private String pId;

    private String name;

    private String url;

    private String action;

    private String icon;

    public Menu(String id, Integer sort, Integer level, String pId, String name, String url, String action, String icon) {
        this.id = id;
        this.sort = sort;
        this.level = level;
        this.pId = pId;
        this.name = name;
        this.url = url;
        this.action = action;
        this.icon = icon;
    }

    public Menu() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}