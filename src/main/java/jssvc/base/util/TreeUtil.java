package jssvc.base.util;

import jssvc.base.constant.SystemConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList ToList(List tree, String parentId, String childrenField, String idField,
            String parentIdField) {
        ArrayList list = new ArrayList();
        for (int i = 0, len = tree.size(); i < len; i++) {
            Map task = (Map) tree.get(i);

            task.put(parentIdField, parentId);
            list.add(task);

            List children = (List) task.get(childrenField);

            if (children != null && children.size() > 0) {
                String id;
                if (task.get(idField) == null) {
                    id = SystemConstant.BLANK;
                } else {
                    id = task.get(idField).toString();
                }
                List list2 = ToList(children, id, childrenField, idField, parentIdField);
                list.addAll(list2);
            }
        }
        return list;
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    public static ArrayList ToTree(List<Map> menusTable, String childrenField, String idField, String parentIdField) {
        ArrayList tree = new ArrayList();
        
        Map hash = new HashMap(); 
        //menu列表中的每个menu转化为map形式存储（hash<id：map<menu>>）
        for (int i = 0, l = menusTable.size(); i < l; i++) {
            Map t = menusTable.get(i);
            hash.put(t.get(idField), t);
        }
        //遍历所有节点
        for (int i = 0, l = menusTable.size(); i < l; i++) {
            Map t = menusTable.get(i);
            Object parentID = t.get(parentIdField);
            
            //如果是顶级父节点添加到tree列表中
            if (parentID == null || parentID.toString().trim().equals("-1")) {
                tree.add(t);
                continue;
            }
            //获取该节点的父节点
            Map parent = (Map) hash.get(parentID);
            //如果该节点的pId没有找到对应的父节点，则添加到tree列表中作为顶级父节点
            if (parent == null) {
                tree.add(t);
                continue;
            }
            //如果该节点的父节点含有孩子节点 则为该父节点添加孩子列表（如果没有）或者直接将该节点加入到孩子列表
            List children = (List) parent.get(childrenField);
            if (children == null) {
                children = new ArrayList();
                parent.put(childrenField, children);
            }
            children.add(t);
        }

        return tree;
    }

  
}
