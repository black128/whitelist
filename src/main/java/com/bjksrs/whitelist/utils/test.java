package com.bjksrs.whitelist.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjksrs.whitelist.pojo.ProvinceCity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        String json = "[{'id':'36','createTime':'2018-11-27 16:44:44','companyName':'二万人热热e','regNo':'微软','creditCode':'为r','orgNo':'为','province':'Empty','errorProvince':'','city':'Empty','errorCity':'','state':0,'comments':'Empty','address':'','telephone':'23|456','mobile':'96|123|456'},{'id':'35','createTime':'2018-11-27 15:31:24','companyName':'阿瑟东','regNo':'安定s','creditCode':'阿三d','orgNo':'阿三','province':'Empty正确的省 ','errorProvince':'阿瑟东','city':'Empty','errorCity':'啊','state':0,'comments':'Empty','address':'阿瑟东','telephone':'阿三','mobile':'阿三d'},{'id':'34','createTime':'2018-11-27 14:35:09','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':'3001','province':'士大夫是士大夫','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':'-','address':'-','telephone':'-','mobile':'-'},{'id':'33','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':'3001','province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':'-','address':'-','telephone':'-','mobile':'-'},{'id':'31','createTime':'2018-11-27 11:09:53','companyName':'上地d','regNo':'','creditCode':'','orgNo':'','province':'','errorProvince':'安定','city':'','errorCity':'','state':0,'comments':'','address':'','telephone':'','mobile':''},{'id':'30','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':null,'province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':'-','address':'-','telephone':'-','mobile':'-'},{'id':'29','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':null,'province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':'-','address':'-','telephone':'-','mobile':'-'},{'id':'25','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':'3001','province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':null,'address':null,'telephone':null,'mobile':null},{'id':'24','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':'3001','province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':null,'address':null,'telephone':null,'mobile':null},{'id':'23','createTime':'2018-11-27 11:09:53','companyName':'滨州恩之嘉贸易有限公司','regNo':'1001','creditCode':'2001','orgNo':'3001','province':'山东省','errorProvince':'山西省','city':'滨州市','errorCity':'忻州市','state':0,'comments':null,'address':null,'telephone':null,'mobile':null}]";

        List<ProvinceCity> list = JSONArray.parseArray(json, ProvinceCity.class);

        System.out.println(list);
    }

}
