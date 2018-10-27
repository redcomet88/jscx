package jssvc.credit.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum CreditProcessStatus {
    suggestionApplyStart("suggestionApplyStart", "提交诚信事件开始"),suggestionRecall("suggestionRecall", "撤回成功"), departmentHandle("departmentHandle", "经办人员办理"),
    managerReview("managerReview", "主管领导复审"), processEnd("processEnd", "流程结束");

    private String id;
    private String name;

    CreditProcessStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<HashMap<String, String>> getSuggestProcessStatusList() {
        List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", CreditProcessStatus.suggestionApplyStart.getId());
        map.put("text", CreditProcessStatus.suggestionApplyStart.getName());
        maps.add(map);
        map = new HashMap<String, String>();
        map.put("id", CreditProcessStatus.departmentHandle.getId());
        map.put("text", CreditProcessStatus.departmentHandle.getName());
        maps.add(map);
        map = new HashMap<String, String>();
        map.put("id", CreditProcessStatus.managerReview.getId());
        map.put("text", CreditProcessStatus.managerReview.getName());
        maps.add(map);

        map = new HashMap<String, String>();
        map.put("id", CreditProcessStatus.processEnd.getId());
        map.put("text", CreditProcessStatus.processEnd.getName());
        maps.add(map);
        return maps;
    }
}
