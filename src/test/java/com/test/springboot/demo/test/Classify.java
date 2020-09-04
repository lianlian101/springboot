package com.test.springboot.demo.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import com.test.springboot.demo.pojo.AuthOperation;

public class Classify {

    // 返回结果参考"classify.json"
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> group(List<AuthOperation> ops) {
        Set<String> fclass = new HashSet<>();
        Set<String> sclass = new HashSet<>();
        Set<String> pname = new HashSet<>();
        for (AuthOperation ao : ops) {
            if (StringUtils.isNotBlank(ao.getClassificationName())) {
                fclass.add(ao.getClassificationName());
            }
            if (StringUtils.isNotBlank(ao.getFunction())) {
                sclass.add(ao.getFunction());
            }
            if (StringUtils.isNotBlank(ao.getFunctionChild())) {
                pname.add(ao.getFunctionChild());
            }
        }
        
        // 三级
        List<Map<String, Object>> pnameList = new ArrayList<>();
        for (String name : pname) {
            Map<String, Object> map = new HashMap<>();
            List<AuthOperation> pclassList = new ArrayList<>();
            for(AuthOperation ao : ops){
                if (name.equals(ao.getFunctionChild())) {
                    pclassList.add(ao);
                }
            }
            map.put("fucntionChildName", name);
            map.put("fucntionChildNameMapList", pclassList);
            pnameList.add(map);
        }
        
        // 二级
        List<Map<String, Object>> snameList = new ArrayList<>();
        for (String sname : sclass) {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> fclassList = new ArrayList<>();
            for (Map<String, Object> kv : pnameList) {
                List<AuthOperation> st = (List<AuthOperation>) kv.get("fucntionChildNameMapList");
                
                HashMap<String, Object> hm = new HashMap<>();
                ArrayList<Object> al = new ArrayList<>();
                for(AuthOperation ao : st){
                    if(sname.equals(ao.getFunction())){
                        al.add(ao);
                    }
                }
                if(CollectionUtils.isEmpty(al)){
                    continue;
                }
                hm.put("fucntionChildName",kv.get("fucntionChildName"));
                hm.put("fucntionChildNameMapList", al);
                fclassList.add(hm);
            }
            
            if(CollectionUtils.isEmpty(fclassList)){
                continue;
            }
            
            map.put("functionName", sname);
            map.put("fucntionNameMapList", fclassList);
            snameList.add(map);
        }

        
        // 一级
        List<Map<String, Object>> fnameList = new ArrayList<>();
        for (String fname : fclass) {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> fclassList = new ArrayList<>();
            
            // 此处使用序列话的方式实现深度拷贝的效果
            byte[] bytes = SerializationUtils.serialize((Serializable) snameList);
            List<Map<String, Object>> copySnameList = SerializationUtils.deserialize(bytes);
            
            for (int k = copySnameList.size() - 1; k >= 0; k--) {
                Map<String, Object> kv = copySnameList.get(k);
                List<Map<String, Object>> st = (List<Map<String, Object>>) kv.get("fucntionNameMapList");
                
                for (int i = st.size() - 1; i >= 0; i--) {
                    Map<String, Object> kv2 = st.get(i);
                    List<AuthOperation> st2 = (List<AuthOperation>) kv2.get("fucntionChildNameMapList");
                    
                    for(int j = st2.size() - 1; j >= 0; j--){
                        AuthOperation ao = st2.get(j);
                        if(!fname.equals(ao.getClassificationName())){
                            st2.remove(j);
                        }
                    }
                    
                    if(CollectionUtils.isEmpty(st2)){
                        st.remove(i);
                    }
                }
                
                if(CollectionUtils.isEmpty(st)){
                    copySnameList.remove(k);
                }else{
                   fclassList.add(kv); 
                }
            }
            
            if(CollectionUtils.isEmpty(fclassList)){
                continue;
            }
            map.put("name", fname);
            map.put("list", fclassList);
            fnameList.add(map);
        }
        return fnameList;
    }
    
}
