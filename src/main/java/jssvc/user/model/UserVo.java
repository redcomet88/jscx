package jssvc.user.model;

public class UserVo extends User {

    private String jgh;

    private String jgmc;

    private String dahJgh;

    private String sexName;

    private String flagName;

    private String gwdjName;

    public String getJgh() {
        return jgh;
    }

    public void setJgh(String jgh) {
        this.jgh = jgh;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getDahJgh() {
        return dahJgh;
    }

    public void setDahJgh(String dahJgh) {
        this.dahJgh = dahJgh;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getGwdjName() {
        return gwdjName;
    }

    public void setGwdjName(String gwdjName) {
        this.gwdjName = gwdjName;
    }
}