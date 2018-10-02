package jssvc.user.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum UserStatus {

    start(0, "启用"), stop(1, "停用");

    private int id;

    private String name;

    UserStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserStatus getUserStatus(int value) {
        if (0 == value) {
            return start;
        } else {
            return stop;
        }
    }

    public static List<HashMap<String, String>> getUserStatusList() {
        List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "0");
        map.put("text", "启用");
        maps.add(map);
        map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("text", "停用");
        maps.add(map);
        return maps;
    }
}
