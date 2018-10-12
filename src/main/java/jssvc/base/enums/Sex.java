package jssvc.base.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Sex {

    M(0, "男"), F(1, "女");

    private int id;

    private String name;

    Sex(int id, String name) {
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

    public static Sex getSex(int value) {
        if (0 == value) {
            return M;
        } else {
            return F;
        }
    }

    public static List<HashMap<String, String>> getSexList() {
        List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "M");
        map.put("text", "男");
        maps.add(map);
        map = new HashMap<String, String>();
        map.put("id", "F");
        map.put("text", "女");
        maps.add(map);
        return maps;
    }
}
