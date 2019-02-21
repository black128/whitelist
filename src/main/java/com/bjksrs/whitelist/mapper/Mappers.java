package com.bjksrs.whitelist.mapper;

import com.bjksrs.whitelist.pojo.ProvinceCity;
import com.bjksrs.whitelist.pojo.SearchBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Mappers {

    List<ProvinceCity> list(@Param("rows") Integer rows, @Param("offset") Integer offset);

    void delete(String id);

    List<ProvinceCity> query(SearchBean search);

    Integer getTotal();

    void updateData(ProvinceCity provinceCity);

    void saveExcelData(ProvinceCity p);

    void insertData(ProvinceCity p);

    Integer getSearchTotal(SearchBean search);

    int checkCompanyName(ProvinceCity p);

    ProvinceCity getPCByCompanyName(String companyName);

    int checkCreditCodeRegNo(@Param("creditCode") String creditCode, @Param("regNo") String regNo);

    int checkCreditCode(@Param("creditCode") String creditCode);

    int checkRegNo(String regNo);

    int checkOrgNo(String orgNo);

    int checkAddress(String address);

    int checkMobile(@Param("s") String number, @Param("companyName") String companyName);

    ProvinceCity getMobileData(@Param("s") String number, @Param("companyName") String companyName);

    void updateAllData(ProvinceCity pp);

    int checkTelphone(String s, String companyName);

    ProvinceCity getTelephoneData(String s, String companyName);
}
