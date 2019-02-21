package com.bjksrs.whitelist.service;

import com.bjksrs.whitelist.pojo.ProvinceCity;
import com.bjksrs.whitelist.pojo.SearchBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface Services {

    List<ProvinceCity> list(Integer rows,Integer offset);

    String delete(String ids);

    List<ProvinceCity> query(SearchBean search);

    Integer getTotal();

    void updateData(List<ProvinceCity> list);

    void saveExcelData(List<ProvinceCity> list);

    Integer getSearchTotal(SearchBean search);
}
