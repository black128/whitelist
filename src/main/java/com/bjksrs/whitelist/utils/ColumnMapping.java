package com.bjksrs.whitelist.utils;

import java.util.HashMap;
import java.util.Map;

public class ColumnMapping {

    public final static Map<String,String> getColumn(){
        Map<String,String> mm = new HashMap<String,String>();

        mm.put("ID","id");
        mm.put("公司名称","companyName");
        mm.put("注册号","regNo");
        mm.put("信用代码","creditCode");
        mm.put("组织机构代码","orgNo");


        mm.put("地址","address");
        mm.put("座机号","telephone");
        mm.put("手机号","mobile");

        mm.put("错误的省","errorProvince");
        mm.put("错误的市","errorCity");
        mm.put("正确的省","province");
        mm.put("正确的市","city");
        mm.put("备注","comments");

        mm.put("插入时间","createTime");

        return mm;
    }


}
