package com.bjksrs.whitelist.utils;

import com.bjksrs.whitelist.mapper.Mappers;
import com.bjksrs.whitelist.pojo.ProvinceCity;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

public class IsCopy {

    public static Boolean isCopy(ProvinceCity p , Mappers mapper){
        Map<String,String> map = new HashMap<String,String>();
        boolean companyNameFlag = p.getCompanyName().trim().isEmpty();
        boolean creditCodeFlag = p.getCreditCode().trim().isEmpty();
        boolean orgNoFlag = p.getOrgNo().trim().isEmpty();
        boolean regNoFlag = p.getRegNo().trim().isEmpty();

        if(!companyNameFlag){
            System.out.println(p.toString());
            System.out.println("公司名称存在");
            if ( mapper.checkCompanyName(p) == 0 ) {
                System.out.println("白名单内无该公司");
                return true;
            } else {
                ProvinceCity provinceCity = null;
                if(!creditCodeFlag || !regNoFlag || !orgNoFlag){
                    provinceCity = mapper.getPCByCompanyName(p.getCompanyName());
                }
                if (!creditCodeFlag && !regNoFlag) {
                    if(mapper.checkCreditCodeRegNo(p.getCreditCode(),p.getRegNo()) == 1 ){
                        //合并
                        ProvinceCity pp = mergeData(provinceCity,p);
                        mapper.updateAllData(pp);
                        return false;
                    }
                }

                if (!creditCodeFlag) {
                    if(mapper.checkCreditCode(p.getCreditCode()) == 1){
                        //合并
                        ProvinceCity pp = mergeData(provinceCity,p);
                        mapper.updateAllData(pp);
                        return false;
                    }
                }
                if (!regNoFlag) {
                    if(mapper.checkRegNo(p.getRegNo()) == 1){
                        //合并
                        ProvinceCity pp = mergeData(provinceCity,p);
                        mapper.updateAllData(pp);
                        return false;
                    }
                }
                if (!orgNoFlag) {
                    if(mapper.checkOrgNo(p.getOrgNo()) == 1){
                        //合并
                        ProvinceCity pp = mergeData(provinceCity,p);
                        mapper.updateAllData(pp);
                        return false;
                    }
                }
                if (!p.getTelephone().trim().isEmpty()) {

                    String[] telArr = getNum(p.getTelephone());
                    for (int i = 0; i < telArr.length ; i++) {
                        if (mapper.checkTelphone(telArr[i],p.getCompanyName()) == 1) {
                            //合并
                            ProvinceCity pc = mapper.getTelephoneData(telArr[i],p.getCompanyName());
                            mapper.updateAllData(mergeData(pc,p));
                            return false;
                        }
                    }

                }

                if (!p.getAddress().trim().isEmpty()) {
                    if(mapper.checkAddress(p.getAddress()) == 1){
                        //合并
                        ProvinceCity pp = mergeData(provinceCity,p);
                        mapper.updateAllData(pp);
                        return false;
                    }
                }
                if (!p.getMobile().trim().isEmpty()) {
                    String[] mobArr = getNum(p.getMobile());
                    for (int i = 0; i < mobArr.length ; i++) {
                        if (mapper.checkMobile(mobArr[i],p.getCompanyName()) == 1) {
                            //合并
                            ProvinceCity pc = mapper.getMobileData(mobArr[i],p.getCompanyName());
                            mapper.updateAllData(mergeData(pc,p));
                            return false;
                        }
                    }
                }
                //是合并还是插入
                return true;
            }
        }else{
            map.put("msg","请输入公司名称");
            return false;
        }
    }

    private static ProvinceCity mergeData(ProvinceCity p1,ProvinceCity p2){
        //p2进表的，p1是数据库的
        System.out.println("合并");
        if (p1.getOrgNo() == null || p1.getOrgNo().trim().isEmpty()){
            p1.setOrgNo(p2.getOrgNo());
        }
        if (p1.getCreditCode() == null || p1.getCreditCode().trim().isEmpty()){
            p1.setCreditCode(p2.getCreditCode());
        }
        if (p1.getRegNo() == null || p1.getRegNo().trim().isEmpty()){
            p1.setRegNo(p2.getRegNo());
        }
        if (p2.getErrorProvince() == null || p1.getErrorCity().trim().isEmpty()){
            p1.setErrorCity(p2.getErrorCity());
        }

        if (p1.getErrorCity() == null || p1.getErrorProvince().trim().isEmpty()){
            p1.setErrorProvince(p2.getErrorProvince());
        }

        p1.setTelephone(getTel(getNum(p1.getTelephone()),getNum(p2.getTelephone())));

        p1.setMobile(getTel(getNum(p1.getMobile()),getNum(p2.getMobile())));

        if (p1.getAddress().trim().isEmpty()){
            p1.setAddress(p2.getAddress());
        }
        return p1;
    }

    private static String[] getNum(String num){
        String[] arr = new String[1];
        if (num.contains("|")){
            arr = num.split("\\|");
        }else{
            arr[0] = num;
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }
    private static String getTel(String[] arr1, String[] arr2){
        System.out.println("getTel");
        Set<String> set = new HashSet<String>();
        for (int i = 0 ; i < arr1.length ;i++){
            System.out.println(arr1[i]);
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        String newTel = "";
        for(String str : set){
            newTel += str + "|";
        }
        return newTel.substring(0,newTel.length() - 1);
    }
}
