package com.corewell.study.utils;/*
package com.corewell.study.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class TreeToolUtil {
    private List<DepartmentNode> rootList; //根节点对象存放到这里

    private List<DepartmentNode> bodyList; //其他节点存放到这里，可以包含根节点

    public TreeToolUtil(List<DepartmentNode> rootList, List<DepartmentNode> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    public List<DepartmentNode> getTree() {   //调用的方法入口
        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree, map));
            return rootList;
        }
        return null;
    }

    public void getChild(DepartmentNode departmentNode, Map<String, String> map) {
        List<DepartmentNode> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c -> c.getDepartmentParentId().equals(departmentNode.getId()))
                .forEach(c -> {
                    map.put(c.getId(), c.getDepartmentParentId());
                    getChild(c, map);
                    childList.add(c);
                });
        departmentNode.setChildDepartmentNode(childList);

    }
}
*/
