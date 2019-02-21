package com.bjksrs.whitelist.controller;

import com.alibaba.fastjson.JSONArray;
import com.bjksrs.whitelist.pojo.PageBean;
import com.bjksrs.whitelist.pojo.ProvinceCity;
import com.bjksrs.whitelist.pojo.SearchBean;
import com.bjksrs.whitelist.service.Services;

import com.bjksrs.whitelist.utils.ColumnMapping;
import com.bjksrs.whitelist.utils.ExcelUtils;

import org.apache.ibatis.annotations.Param;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author zhang
 * @date 2018/11/19
 */

@Controller
public class Controllers {

    @Autowired
    private Services service;

    @Value("${file.uploadFolder}")
    private String savePath;

    private final static Logger log = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("show")
    public String show(){
        return "customer";
    }

    @RequestMapping("index")
    public String list(){
        return "customer";
    }
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String ids){
        return service.delete(ids);
    }
    @RequestMapping("search")
    @ResponseBody
    public PageBean<ProvinceCity> search(SearchBean search){
        if(search.getCmbProvince().equals("请选择省份")){
            search.setCmbProvince("");
        }
        if(search.getCmbCity().equals("请选择城市")){
            search.setCmbCity("");
        }
        search.setCondition(search.getCondition().trim());
        search.setContent(search.getContent().trim());
        System.out.println(search.toString());

        PageBean<ProvinceCity> pageBean = new PageBean<ProvinceCity>();
        // 统计总记录数
        Integer total = service.getSearchTotal(search);
        pageBean.setTotal(total);

        // 查询当前页实体对象
        List<ProvinceCity> list = service.query(search);
        pageBean.setRows(list);

        return pageBean;

    }

    @RequestMapping("showList")
    @ResponseBody
    public PageBean<ProvinceCity> showList(SearchBean search,@Param("offset") Integer rows, @Param("offset") Integer offset){

        PageBean<ProvinceCity> pageBean = new PageBean<ProvinceCity>();
        // 统计总记录数
        Integer total = service.getTotal();
        pageBean.setTotal(total);

        // 查询当前页实体对象
        List<ProvinceCity> list = service.list(rows,offset);
        pageBean.setRows(list);

        return pageBean;

    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> m = new HashMap<String,Object>();
        try{
            request.setCharacterEncoding("UTF-8");

            Map<String, Object> json = new HashMap<String, Object>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            //页面控件的文件流
            MultipartFile multipartFile = null;
            Map map =multipartRequest.getFileMap();
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
                multipartFile=(MultipartFile) map.get(obj);
            }
            if(multipartFile == null){
                m.put("error","请检查文件！");
            }else {
                /* 获取文件的后缀 */
                String fileName = multipartFile.getOriginalFilename();
                String exeName = fileName.substring(fileName.indexOf("."));
                String newName = UUID.randomUUID().toString().replace("-", "");
                //            String savePath = "D:\\bjksrs-work\\java-workpace\\whitelist\\src\\main\\resources\\static\\upload\\";
                //            String savePath = ;

                File file = new File(savePath + newName + exeName);
                multipartFile.transferTo(file);
                m.put("path", savePath + newName + exeName);
                m.put("status", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.put("response","Error");
        }
        return m;
    }

    @ResponseBody
    @RequestMapping(value="CheckExcelColumns")
    public Map<String, Object> checkExcel(@Param("path") String path){
        Map<String, Object> m = new HashMap<String,Object>();
        try {
            ExcelUtils.checkExcelVaild(new File(path));
        }catch (Exception e){
            e.printStackTrace();
            m.put("error","文件不是execl文件");
        }

        m.put("Success","yes");
        return m;
    }
    @ResponseBody
    @RequestMapping("GetExcelData")
    public List<Map<Object,Object>> getExcelData(@Param("file") String file) {

        List<Object> list = new ArrayList<Object>();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        //获取总列数
        int columnTotalNum = 0;
        //获取总行数
        int rowNum = 0;
        File excelFile = new File(file);
        try {
            // 同时支持Excel 2003、2007
             // 创建文件对象
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            ExcelUtils.checkExcelVaild(excelFile);
            Workbook workbook = ExcelUtils.getWorkbok(in, excelFile);

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

             // 设置当前excel中sheet的下标：0开始
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet
            //获取总行数
            rowNum = sheet.getLastRowNum();

            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                //如果当前行没有数据，跳出循环
                if (("").equals(row.getCell(0).toString())) {
                    break;
                }
                int end = row.getLastCellNum();
                System.out.println(end);
                for (int i = 0; i < end; i++) {
                    Cell cell = row.getCell(i);
                    while(end<14){
                        Cell c = row.createCell(end);
                        c.setCellValue("");
                        end++;
                    }
                    System.out.print(cell + "___");
                    Object obj = null;
                    if (cell == null) {
                        obj = "";
                    }else {
                        obj = ExcelUtils.getValue(cell);
                    }
                    list.add(obj);
                }
                System.out.println(end);
                //总列数
//                columnTotalNum = row.getPhysicalNumberOfCells();
                columnTotalNum = 14;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object> mList = new ArrayList<Object>();
        List<Map<Object,Object>> nList = new ArrayList<Map<Object,Object>>();
        for (int i = 0; i < 14 ; i++) {
            mList.add(list.get(i));
        }
        System.out.println(columnTotalNum);
        System.out.println(rowNum);
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(mList);
        for (int j = 1; j < rowNum + 1; j++) {
            Map<Object,Object> map = new HashMap<Object,Object>();
            for (int i = columnTotalNum * j ; i < (j + 1) * columnTotalNum; i++) {
                map.put(ColumnMapping.getColumn().get(mList.get(i%columnTotalNum)),list.get(i));
            }
            nList.add(map);
        }
        return nList;
    }

    @RequestMapping("saveData")
    @ResponseBody
    public Map<String, String> saveData(String json){

        Map<String,String> map = new HashMap<String,String>();
        List<ProvinceCity> list = JSONArray.parseArray(json,ProvinceCity.class);
//        System.out.println(list);
        List<ProvinceCity> nList = new ArrayList<ProvinceCity>();

        Iterator<ProvinceCity> it = list.iterator();
        while(it.hasNext()){
            ProvinceCity p = it.next();
            System.out.println(p);
            if("-".equals(p.getCompanyName()) || "".equals(p.getCompanyName()) || p.getCompanyName() == null){
                map.put("errorCompanyName","公司名不能为空");
                it.remove();
                continue;
            }
            if(!isExistProvince(p.getProvince()) || !isExistCity(p.getCity())){
                map.put("errorProvinceCity",p.getCompanyName()+"省市格式错误");
                it.remove();
                continue;
            }
            nList.add(formatParam(p));
        }
        System.out.println(list);
        System.out.println(nList);
        try {
            service.updateData(nList);
            map.put("success","保存成功！");
        }catch (Exception e){
            map.put("error","保存失败！");
        }
        return map;
    }

    @RequestMapping("SaveExcelData")
    @ResponseBody
    public Map<String, String> saveExcelData(String postData){

        Map<String,String> map = new HashMap<String,String>();
        List<ProvinceCity> list = JSONArray.parseArray(postData,ProvinceCity.class);
        List<ProvinceCity> nList = new ArrayList<ProvinceCity>();

        Iterator<ProvinceCity> it = list.iterator();
        while(it.hasNext()){
            ProvinceCity p = it.next();
            if("-".equals(p.getCompanyName()) || "".equals(p.getCompanyName()) || p.getCompanyName() == null){
                log.info("公司名不能为空-{}",p.getCompanyName());
                list.remove(p);
                continue;
            }
            if(!isExistProvince(p.getProvince()) || !isExistCity(p.getCity())){
                map.put("errorProvinceCity",p.getCompanyName()+"省市格式错误");
                list.remove(p);
                continue;
            }
            nList.add(formatParam(p));
        }
        try {
            service.saveExcelData(nList);
            map.put("success","保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","保存失败！");
        }
        return map;
    }
    private Boolean isExistProvince(String province){
        System.out.println(province);
        String[] str = {"北京市","河北省","天津市","山西省", "内蒙古自治区","辽宁省", "吉林省","黑龙江省","上海市", "江苏省","浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省","广东省","广西壮族自治区", "海南省","重庆市","四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省","青海省", "宁夏回族自治区","新疆维吾尔自治区", "湖南省","湖北省"};
        for (String s : str){
            if(s.equals(province)){
                System.out.println(province+":"+s);
                return true;
            }
        }
        return false;
    }

    private Boolean isExistCity(String city){
        String[] str = {"东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区",
                "黄浦区","卢湾区","徐汇区","长宁区","静安区","普陀区","闸北区","虹口区","杨浦区","闵行区","宝山区","嘉定区","浦东新区","金山区","松江区","青浦区","南汇区","奉贤区","崇明县",
                "和平区","河东区","河西区","南开区","河北区","红桥区","塘沽区","汉沽区","大港区","东丽区","西青区","津南区","北辰区","武清区","宝坻区","宁河县","静海县","蓟县",
                "万州区","涪陵区","渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","万盛区","双桥区","渝北区","巴南区","黔江区","长寿区","綦江县","潼南县","铜梁县","大足县","荣昌县","璧山县","梁平县","城口县","丰都县","垫江县","武隆县","忠县","开县","云阳县","奉节县","巫山县","巫溪县","石柱土家族自治县","秀山土家族苗族自治县","酉阳土家族苗族自治县","彭水苗族土家族自治县","江津市","合川市","永川市","南川市",
                "成都市","自贡市","攀枝花市","泸州市","德阳市","绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","达州市","雅安市","巴中市","资阳市","阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州","贵阳市","六盘水市","遵义市","安顺市","铜仁地区","黔西南布依族苗族自治州","毕节地区","黔东南苗族侗族自治州","黔南布依族苗族自治州","昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","思茅市","临沧市","楚雄彝族自治州","红河哈尼族彝族自治州","文山壮族苗族自治州","西双版纳傣族自治州","大理白族自治州","德宏傣族景颇族自治州","怒江傈僳族自治州","迪庆藏族自治州","拉萨市","昌都地区","山南地区","日喀则地区","那曲地区","阿里地区","林芝地区","郑州市","开封市","洛阳市","平顶山市","安阳市","鹤壁市","新乡市","焦作市","濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市","信阳市","周口市","驻马店市","武汉市","黄石市","十堰市","宜昌市","襄樊市","鄂州市","荆门市","孝感市","荆州市","黄冈市","咸宁市","随州市","恩施土家族苗族自治州","长沙市","株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市","张家界市","益阳市","郴州市","永州市","怀化市","娄底市","湘西土家族苗族自治州","广州市","韶关市","深圳市","珠海市","汕头市","佛山市","江门市","湛江市","茂名市","肇庆市","惠州市","梅州市","汕尾市","河源市","阳江市","清远市","东莞市","中山市","潮州市","揭阳市","云浮市","南宁市","柳州市","桂林市","梧州市","北海市","防城港市","钦州市","贵港市","玉林市","百色市","贺州市","河池市","来宾市","崇左市","西安市","铜川市","宝鸡市","咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市","兰州市","嘉峪关市","金昌市","白银市","天水市","武威市","张掖市","平凉市","酒泉市","庆阳市","定西市","陇南市","临夏回族自治州","甘南藏族自治州","西宁市","海东地区","海北藏族自治州","黄南藏族自治州","海南藏族自治州","果洛藏族自治州","玉树藏族自治州","海西蒙古族藏族自治州","银川市","石嘴山市","吴忠市","固原市","中卫市","乌鲁木齐市","克拉玛依市","吐鲁番地区","哈密地区","昌吉回族自治州","博尔塔拉蒙古自治州","巴音郭楞蒙古自治州","阿克苏地区","克孜勒苏柯尔克孜自治州","喀什地区","和田地区","伊犁哈萨克自治州","塔城地区","阿勒泰地区","省直辖行政单位","石家庄市","唐山市","秦皇岛市","邯郸市","邢台市","保定市","张家口市","承德市","沧州市","廊坊市","衡水市",
                "太原市","大同市","阳泉市","长治市","晋城市","朔州市","晋中市","运城市","忻州市","临汾市","吕梁市","呼和浩特市","包头市","乌海市","赤峰市","通辽市","鄂尔多斯市","呼伦贝尔市","巴彦淖尔市","乌兰察布市","兴安盟","锡林郭勒盟","阿拉善盟","南京市","无锡市","徐州市","常州市","苏州市","南通市","连云港市","淮安市","盐城市","扬州市","镇江市","泰州市","宿迁市","杭州市","宁波市","温州市","嘉兴市","湖州市","绍兴市","金华市","衢州市","舟山市","台州市","丽水市","合肥市","芜湖市","蚌埠市","淮南市","马鞍山市","淮北市","铜陵市","安庆市","黄山市","滁州市","阜阳市","宿州市","巢湖市","六安市","亳州市","池州市","宣城市","福州市","厦门市","莆田市","三明市","泉州市","漳州市","南平市","龙岩市","宁德市","南昌市","景德镇市","萍乡市","九江市","新余市","鹰潭市","赣州市","吉安市","宜春市","抚州市","上饶市","济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市","荷泽市","沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市","锦州市","营口市","阜新市","辽阳市","盘锦市","铁岭市","朝阳市","葫芦岛市",
                "长春市","吉林市","四平市","辽源市","通化市","白山市","松原市","延边朝鲜族自治州","哈尔滨市","齐齐哈尔市","鸡西市","鹤岗市","双鸭山市","大庆市","伊春市","佳木斯市","七台河市","牡丹江市","黑河市","绥化市",
                "海口市","三亚市"};
        for (String s : str){
            if(s.equals(city)){
                return true;
            }
        }
        return false;
    }

    private ProvinceCity formatParam(ProvinceCity p){
        if(p.getRegNo() != null && !"".equals(p.getRegNo().trim())){
            p.setRegNo(p.getRegNo().replace("-",""));
        }
        if(p.getOrgNo() != null && !"".equals(p.getOrgNo().trim())) {
            p.setOrgNo(p.getOrgNo().replace("-", ""));
        }
        if(p.getCreditCode() != null && !"".equals(p.getCreditCode().trim())){
            p.setCreditCode(p.getCreditCode().replace("-",""));
        }
        if(p.getAddress() != null && !"".equals(p.getAddress().trim())) {
            p.setAddress(p.getAddress().replace("-", ""));
        }
        if(p.getMobile() != null && !"".equals(p.getMobile().trim())){
            p.setMobile(p.getMobile().replace("-",""));
        }
        if(p.getTelephone() != null && !"".equals(p.getTelephone().trim())){
            p.setTelephone(p.getTelephone().replace("-",""));
        }
        if(p.getComments() != null && !"".equals(p.getComments().trim())) {
            p.setComments(p.getComments().replace("-", ""));
        }
        if(p.getErrorProvince() != null && !"".equals(p.getErrorProvince().trim())){
            p.setErrorProvince(p.getErrorProvince().replace("-",""));
        }
        if(p.getErrorCity() != null && !"".equals(p.getErrorCity().trim())){
            p.setErrorCity(p.getErrorCity().replace("-",""));
        }
        return p;
    }
}
