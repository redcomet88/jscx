package jssvc.base.model;

public class ApproveOption {
    private Long id;

    private String optionCode;

    private String optionName;

    private String optionUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode == null ? null : optionCode.trim();
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public String getOptionUser() {
        return optionUser;
    }

    public void setOptionUser(String optionUser) {
        this.optionUser = optionUser == null ? null : optionUser.trim();
    }
}