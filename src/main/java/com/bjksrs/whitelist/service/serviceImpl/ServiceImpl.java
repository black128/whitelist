package com.bjksrs.whitelist.service.serviceImpl;

import com.bjksrs.whitelist.mapper.Mappers;
import com.bjksrs.whitelist.pojo.ProvinceCity;
import com.bjksrs.whitelist.pojo.SearchBean;
import com.bjksrs.whitelist.service.Services;
import com.bjksrs.whitelist.utils.IsCopy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceImpl implements Services {

    @Autowired
    private Mappers mapper;

    @Override
    public List<ProvinceCity> list(Integer rows,Integer offset) {
        return mapper.list(rows,offset);
    }

    @Override
    @Transactional
    public String delete(String ids) {
        List<String> idArr = new ArrayList<String>();
        if (ids.length() > 1) {
            String[] arr = ids.split(",");
            for (int i = 0; i < arr.length; i++) {
                idArr.add(arr[i]);
            }
        }else {
            idArr.add(ids);
        }
        try{
            for (int i = 0 ; i < idArr.size() ; i++){
                mapper.delete(idArr.get(i));
            }
        }catch (Exception e){
            return "0";
        }
        return "1";
    }

    @Override
    public List<ProvinceCity> query(SearchBean search) {
        search.setContent("%"+search.getContent()+"%");
        return mapper.query(search);
    }

    @Override
    public Integer getTotal() {
        return mapper.getTotal();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateData(List<ProvinceCity> list){
        Map<String,String> map = new HashMap<String,String>();
        for(ProvinceCity p : list){
            if(p.getId().trim().isEmpty()){
                if(IsCopy.isCopy(p,mapper)) {
                    mapper.saveExcelData(p);
                }
            }else {
                mapper.updateData(p);
            }
        }
    }

    @Override
    public void saveExcelData(List<ProvinceCity> list) {
        for(ProvinceCity p : list){
            if(IsCopy.isCopy(p,mapper)) {
                mapper.saveExcelData(p);
            }
        }
    }

    @Override
    public Integer getSearchTotal(SearchBean search) {
        search.setContent("%"+search.getContent()+"%");
        return mapper.getSearchTotal(search);
    }
}
