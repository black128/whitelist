<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>白名单</title>

<!-- Bootstrap Core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<#--<link href="/css/dataTables.bootstrap.css" rel="stylesheet">-->
<!-- bootstrapTable CSS -->
    <link href="/css/bootstrap-editable.css" rel="stylesheet">
<link href="/css/bootstrap-table.min.css" rel="stylesheet">

    <link href="/css/tableexport.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="/css/sb-admin-2.css" rel="stylesheet">
	<!-- upload CSS -->
    <link href="/bootstrap-fileinput-master/css/fileinput.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


    <![endif]-->
    <script type="text/javascript" src="/js/province_city.js"></script>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/index">白名单管理系统 v1.0</a>
		</div>
		<!-- /.navbar-header -->


		<!-- /.navbar-top-links -->

		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="panel panel-default">
				<div class="panel-body" align="center">
					<form class="form-inline" id="formSearch">

						<div class="form-group">
							<label for="customerFrom">正确的省</label>
							<select	class="form-control" id="cmbProvince" name="cmbProvince"></select>
						</div>
						<div class="form-group">
							<label for="custIndustry">正确的市</label>
							<select	class="form-control" id="cmbCity"  name="cmbCity""></select>
						</div>
						<div class="form-group">
							<label for="condition">其他条件</label>
							<select class="form-control" id="condition" name="condition">
								<option value="">--请选择--</option>
                                <option value="companyName">公司名称</option>
                                <option value="regNo">注册号</option>
								<option value="creditCode">信用代码</option>
								<option value="orgNo">组织机构代码</option>
								<option value="gmt_create">修改时间</option>

							</select>
						</div>
                        <div class="form-group">
                            <label for="customerName">筛选值</label>
                            <input type="text" class="form-control" id="content" value="" name="content">
                        </div>
						<button type="button" class="btn btn-primary" onclick="search()">查询</button>
					</form>
				</div>
			</div>

			<#--<div class="row">-->
				<!-- /.col-lg-12 -->
                <!--导入数据操作层-->
                <div id="import" class="modal fade bs-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header bg-primary">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">文件导入</h4>
                            </div>
                            <div class="modal-body">
                                <div style="text-align:right;padding:5px">
                                    <a href="/tableExport.xlsx" onclick="javascript:void(0);">
									<#--<img alt="测试用户信息-模板" src="~/Content/images/ico_excel.png" />-->
                                        <span style="font-size:larger;font-weight:200;color:red">导入模板.xlsx</span>
                                    </a>
                                </div>
                                <hr/>
                                <form id="ffImport">
                                    <div title="Excel导入操作" style="padding: 5px">
                                        <input id="excelFile" name="excelFile" type="file" multiple class="file-loading">
                                    </div>
                                    <#--<button type="submit" class="btn btn-default" >上传</button>-->
                                </form>

                                <!--数据显示表格-->
                                <table id="gridImport" class="table table-striped table-bordered table-hover" cellpadding="0" cellspacing="0" border="0">
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" id="close" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="SaveImport()">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div id = "row_table">
                    <div id="toolbar">


                        <button id="fileLoad" class="btn btn-default" data-toggle="modal" data-target="#import">导入</button>
                        <button id="button" class="btn btn-default">新增</button>
                        <button id="getTableData" class="btn btn-default">保存</button>
                        <button id="delete" class="btn btn-default">删除</button>
                    </div>
                    <table id="table"></table>
                    </div>
                </div>
			<#--</div>-->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- 客户编辑对话框 -->

	<!-- /#wrapper -->
    <script type="text/javascript">
        addressInit('cmbProvince', 'cmbCity', 'cmbArea');
    </script>
    <!-- jQuery -->
	<script src="/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="/js/jquery.dataTables.min.js"></script>
	<script src="/js/dataTables.bootstrap.min.js"></script>
    <!-- bootstrapTable JavaScript -->
    <script src="/js/bootstrap-editable.min.js"></script>

    <script src="/js/bootstrap-table.min.js"></script>

    <script src="/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/js/extensions/editable/bootstrap-table-editable.min.js"></script>
    <!-- bootstrapTable------upload --- JavaScript -->
    <script src="/bootstrap-fileinput-master/js/fileinput.js"></script>
    <script src="/bootstrap-fileinput-master/js/locales/zh.js"></script>
	<#--导出文件-->

    <script src="/js/xlsx.core.min.js"></script>
    <script src="/js/Blob.js"></script>
	<script src="/js/FileSaver.min.js"></script>
    <#--<script src="/js/tableexport-2.1.min.js"></script>-->
    <script src="/js/tableExport.js"></script>
    <script src="/js/extensions/export/bootstrap-table-export.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="/js/sb-admin-2.js"></script>
    <script src="/js/jquery.tips.js"></script>
	<script type="text/javascript">

        $(function() {
            let $table = $('#table');
            let $button = $('#button');
            let $getTableData = $('#getTableData');
            let flag = false;
            let bool = false;
            InitExcelFile();
            var rowValue = "";
            $button.click(function() {
                $table.bootstrapTable('insertRow', {
                    index: 0,
                    row: {
                        id: '',
                        companyName: '',
                        regNo: '',
						creditCode:'',
						orgNo:'',
						errorProvince:'',
						errorCity:'',
						address:'',
						telephone:'',
						mobile:'',
						createTime:'',
						province:'',
						city:'',
						comments:''
                    }
                });

            });

            $table.bootstrapTable({
                url: '/showList',
				// method:'post',
                toolbar: '#toolbar',
                clickEdit: true,
                showToggle: false,
                pagination: true,       //显示分页条
                showColumns: true,
                // clickToSelect:true,
                search:false,
				sortTable:true,
				sortOrder:"desc",
				sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 10,                     //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                showPaginationSwitch: false,     //显示切换分页按钮
                showRefresh: true,      //显示刷新按钮
                showExport: true,                     //是否显示导出
                uniqueId: "ID",
				exportDataType: "basic",              //basic', 'all', 'selected'.
                exportTypes:['xlsx'],  //导出文件类型 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'
                exportOptions:{
                    ignoreColumn: [0],  //忽略某一列的索引
                    // fileName: questionNaireName,  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    // tableName: questionNaireName,
                    // excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], 设置格式
                },
                //clickToSelect: true,  //点击row选中radio或CheckBox
                queryParams : function (params) {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var temp = {
                        rows: params.limit,                         //页面大小
                        offset:params.offset,
						page: (params.offset / params.limit) + 1,   //页码
                        sort: 'id',      //排序列名
                        sortOrder: 'desc' //排位命令（desc，asc）
                    };
                    return temp;
                },
                columns: [{
                    checkbox: true
                },  {
					field: 'id',
                    title: 'ID'
        		}, {
					field: 'companyName',
					title: '公司名称'
				}, {
					field: 'regNo',
					title: '注册号'
				}, {
					field: 'creditCode',
					title: '信用代码'
				}, {
					field: 'orgNo',
					title: '组织机构代码'
				},{
					field: 'address',
					title: '地址'
				},{
					field: 'telephone',
					title: '座机号'
				},{
					field: 'mobile',
					title: '手机号'
				},{
                    field: 'errorProvince',
                    title: '错误的省'
                },{
                    field: 'errorCity',
                    title: '错误的市'
                },{
					field: 'province',
					title: '正确的省',
                    cellStyle:function cellStyle(value, row, index, field) {
                        return {
                            // classes: 'text-nowrap another-class',
                            css: {color: "blue"}
                        };
                    }
				},{
					field: 'city',
					title: '正确的市',
                    cellStyle:function cellStyle(value, row, index, field) {
                        return {
                            // classes: 'text-nowrap another-class',
                            css: {"color": "blue"}
                        };
                    }
				},{
                    class: 'comment',
					field: 'comments',
					title: '备注',
                    cellStyle:function cellStyle(value, row, index, field) {
                        return {
                            // classes: 'text-nowrap another-class',
                            css: {color: "blue"}
                        };
                    },
                    // width:100
				},{
                    field: 'createTime',
                    title: '修改时间'
                }],
                onEditableSave: function (field, row, oldValue, $el) {
                    $el.blur(function(){
                        $table.bootstrapTable('updateRow', {
                            index:row.id,
                            row:row
                        })
                    })
                },
                /**
                 * @param {点击列的 field 名称} field
                 * @param {点击列的 value 值} value
                 * @param {点击列的整行数据} row
                 * @param {td 元素} $element
                 */
                // onClickCell: function(field, value, row, $element) {
                //
                //     if(field == "province" || field == "city" || field == "comments") {
                //         $element.attr('contenteditable', true);
                //         $element.blur(function () {
                //             let index = $element.parent().data('index');
                //             let tdValue = $element.html();
                //             saveData(index, field, tdValue);
                //         })
                //     }
                // },

                onClickRow:function(row, $element){
                    if(row.id == "") {
                        $element.attr('contenteditable', true);
                        $element.children("td").each(function(j){
                            if(j < 2 || j > 13){
                                $(this).attr('contenteditable', false)
                            }
                        });

                        $element.children("td:eq(7)").click(function(){
                            $(this).tips({
                                side : 3,
                                msg : '多个座机号之间以‘|’分隔',
                                bg : '#1d21ff',
                                time : 3
                            });
                        });
                        $element.children("td:eq(8)").click(function(){
                            $(this).tips({
                                side : 3,
                                msg : '多个手机号之间以‘|’分隔',
                                bg : '#1d21ff',
                                time : 3
                            });
                        });
                        $element.blur(function(){
                            //第二列单元格的值eq(索引)
                            row.companyName = $element.children('td:eq(2)').text();
                            row.regNo = $element.children('td:eq(3)').text();
                            row.creditCode = $element.children('td:eq(4)').text();
                            row.orgNo = $element.children('td:eq(5)').text();
                            row.address = $element.children('td:eq(6)').text();
                            row.telephone = $element.children('td:eq(7)').text();
                            row.mobile = $element.children('td:eq(8)').text();
                            row.errorProvince = $element.children('td:eq(9)').text();
                            row.errorCity = $element.children('td:eq(10)').text();
                            row.province = $element.children('td:eq(11)').text();
                            row.city = $element.children('td:eq(12)').text();
                            row.comments = $element.children('td:eq(13)').text();
                            // console.log(row);
                            if(!checkCompanyName(row,$element)){
                                bool = true;
                            }
                            // checkErrorProvince(row,$element) && checkErrorCity(row,$element) &&
                            if(!checkProvince(row,$element) || !checkCity(row,$element)){
                                flag = true;
                            }
                            $table.bootstrapTable('updateRow', {
                                index: $element.data('index'),       //行索引
                                row:row
                            });

                        })
                    }else{

                        $element.attr('contenteditable', true);
                        $element.children("td").each(function(j){
                            if(j < 11 || j > 13){
                                $(this).attr('contenteditable', false)
                            }
                        });

                        $element.blur(function(){
                            row.province = $element.children('td:eq(11)').text();
                            row.city = $element.children('td:eq(12)').text();
                            row.comments = $element.children('td:eq(13)').text();
                            if(!checkCompanyName(row,$element)){
                                bool = true;
                            }
                            if(!checkProvince(row,$element) || !checkCity(row,$element)){
                                flag = true;
                            }
                            $table.bootstrapTable('updateRow', {
                                index: $element.data('index'),       //行索引
                                row: row
                            });
                        })
                    }
                }
            });

            $getTableData.click(function() {
                if(bool){
                    alert("请输入公司名称");
                    bool = false;
                    return;
                }
                if(flag){
                    alert("请输入正确的省市");
                    flag = false;
                    return;
                }
                //保存
                $.ajax({
					url:'/saveData',
					type:'post',
					data:{
					    "json":JSON.stringify($table.bootstrapTable('getData')),
					},
					success:function (data) {
					    if(data.success){
                            alert(data.success);
						}else if(data.error){
					        alert(data.error);
						}
                        $table.bootstrapTable("refresh")
                    }
				});
                console.log(JSON.stringify($table.bootstrapTable('getData')))
            });

            function saveData(index, field, value) {
                $table.bootstrapTable('updateCell', {
                    index: index,       //行索引
                    field: field,       //列名
                    value: value        //cell值
                })
            }

        });

        function checkCompanyName(row,$element){
            if(row.companyName == null || row.companyName == ""){
                $element.children('td:eq(2)').tips({
                    side : 1,
                    msg : '请输入公司名称',
                    bg : '#1d21ff',
                    time : 3
                });
                $element.focus();
                return false;
            }else{
                return true;
            }
        }

        function checkErrorProvince(row,$element){
            provinces = ["北京市","河北省","天津市","山西省", "内蒙古自治区","辽宁省", "吉林省","黑龙江省",
                "上海市", "江苏省","浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省","广东省","广西壮族自治区",
                "海南省","重庆市","四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省","青海省",
                "宁夏回族自治区","新疆维吾尔自治区", "湖南省","湖北省"];
            if (provinces.indexOf(row.errorProvince) < 0){
                console.log(row.errorProvince);
                $element.children('td:eq(9)').tips({
                    side : 1,
                    msg : '请输入正确格式的省',
                    bg : '#1d21ff',
                    time : 3
                });
                $element.focus();
                return false;
            }else{
                return true;
            }
        }

        function checkProvince(row,$element){
            provinces = ["北京市","河北省","天津市","山西省", "内蒙古自治区","辽宁省", "吉林省","黑龙江省",
                "上海市", "江苏省","浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省","广东省","广西壮族自治区",
                "海南省","重庆市","四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省","青海省",
                "宁夏回族自治区","新疆维吾尔自治区", "湖南省","湖北省"];
            if (provinces.indexOf(row.province) < 0){
                console.log(row.province);
                $element.children('td:eq(11)').tips({
                    side : 1,
                    msg : '请输入正确格式的省',
                    bg : '#1d21ff',
                    time : 1
                });
                $element.focus();
                return false;
            }else{
                return true;
            }
        }
        function checkCity(row,$element){
            cities = ['东城区','西城区','崇文区','宣武区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','怀柔区','平谷区',
                '黄浦区','卢湾区','徐汇区','长宁区','静安区','普陀区','闸北区','虹口区','杨浦区','闵行区','宝山区','嘉定区','浦东新区','金山区','松江区','青浦区','南汇区','奉贤区','崇明县',
                '和平区','河东区','河西区','南开区','河北区','红桥区','塘沽区','汉沽区','大港区','东丽区','西青区','津南区','北辰区','武清区','宝坻区','宁河县','静海县','蓟县',
                '万州区','涪陵区','渝中区','大渡口区','江北区','沙坪坝区','九龙坡区','南岸区','北碚区','万盛区','双桥区','渝北区','巴南区','黔江区','长寿区','綦江县','潼南县','铜梁县','大足县','荣昌县','璧山县','梁平县','城口县','丰都县','垫江县','武隆县','忠县','开县','云阳县','奉节县','巫山县','巫溪县','石柱土家族自治县','秀山土家族苗族自治县','酉阳土家族苗族自治县','彭水苗族土家族自治县','江津市','合川市','永川市','南川市',
                '成都市','自贡市','攀枝花市','泸州市','德阳市','绵阳市','广元市','遂宁市','内江市','乐山市','南充市','眉山市','宜宾市','广安市','达州市','雅安市','巴中市','资阳市','阿坝藏族羌族自治州','甘孜藏族自治州','凉山彝族自治州','贵阳市','六盘水市','遵义市','安顺市','铜仁地区','黔西南布依族苗族自治州','毕节地区','黔东南苗族侗族自治州','黔南布依族苗族自治州','昆明市','曲靖市','玉溪市','保山市','昭通市','丽江市','思茅市','临沧市','楚雄彝族自治州','红河哈尼族彝族自治州','文山壮族苗族自治州','西双版纳傣族自治州','大理白族自治州','德宏傣族景颇族自治州','怒江傈僳族自治州','迪庆藏族自治州','拉萨市','昌都地区','山南地区','日喀则地区','那曲地区','阿里地区','林芝地区','郑州市','开封市','洛阳市','平顶山市','安阳市','鹤壁市','新乡市','焦作市','濮阳市','许昌市','漯河市','三门峡市','南阳市','商丘市','信阳市','周口市','驻马店市','武汉市','黄石市','十堰市','宜昌市','襄樊市','鄂州市','荆门市','孝感市','荆州市','黄冈市','咸宁市','随州市','恩施土家族苗族自治州','长沙市','株洲市','湘潭市','衡阳市','邵阳市','岳阳市','常德市','张家界市','益阳市','郴州市','永州市','怀化市','娄底市','湘西土家族苗族自治州','广州市','韶关市','深圳市','珠海市','汕头市','佛山市','江门市','湛江市','茂名市','肇庆市','惠州市','梅州市','汕尾市','河源市','阳江市','清远市','东莞市','中山市','潮州市','揭阳市','云浮市','南宁市','柳州市','桂林市','梧州市','北海市','防城港市','钦州市','贵港市','玉林市','百色市','贺州市','河池市','来宾市','崇左市','西安市','铜川市','宝鸡市','咸阳市','渭南市','延安市','汉中市','榆林市','安康市','商洛市','兰州市','嘉峪关市','金昌市','白银市','天水市','武威市','张掖市','平凉市','酒泉市','庆阳市','定西市','陇南市','临夏回族自治州','甘南藏族自治州','西宁市','海东地区','海北藏族自治州','黄南藏族自治州','海南藏族自治州','果洛藏族自治州','玉树藏族自治州','海西蒙古族藏族自治州','银川市','石嘴山市','吴忠市','固原市','中卫市','乌鲁木齐市','克拉玛依市','吐鲁番地区','哈密地区','昌吉回族自治州','博尔塔拉蒙古自治州','巴音郭楞蒙古自治州','阿克苏地区','克孜勒苏柯尔克孜自治州','喀什地区','和田地区','伊犁哈萨克自治州','塔城地区','阿勒泰地区','省直辖行政单位','石家庄市','唐山市','秦皇岛市','邯郸市','邢台市','保定市','张家口市','承德市','沧州市','廊坊市','衡水市',
                '太原市','大同市','阳泉市','长治市','晋城市','朔州市','晋中市','运城市','忻州市','临汾市','吕梁市','呼和浩特市','包头市','乌海市','赤峰市','通辽市','鄂尔多斯市','呼伦贝尔市','巴彦淖尔市','乌兰察布市','兴安盟','锡林郭勒盟','阿拉善盟','南京市','无锡市','徐州市','常州市','苏州市','南通市','连云港市','淮安市','盐城市','扬州市','镇江市','泰州市','宿迁市','杭州市','宁波市','温州市','嘉兴市','湖州市','绍兴市','金华市','衢州市','舟山市','台州市','丽水市','合肥市','芜湖市','蚌埠市','淮南市','马鞍山市','淮北市','铜陵市','安庆市','黄山市','滁州市','阜阳市','宿州市','巢湖市','六安市','亳州市','池州市','宣城市','福州市','厦门市','莆田市','三明市','泉州市','漳州市','南平市','龙岩市','宁德市','南昌市','景德镇市','萍乡市','九江市','新余市','鹰潭市','赣州市','吉安市','宜春市','抚州市','上饶市','济南市','青岛市','淄博市','枣庄市','东营市','烟台市','潍坊市','济宁市','泰安市','威海市','日照市','莱芜市','临沂市','德州市','聊城市','滨州市','荷泽市','沈阳市','大连市','鞍山市','抚顺市','本溪市','丹东市','锦州市','营口市','阜新市','辽阳市','盘锦市','铁岭市','朝阳市','葫芦岛市',
                '长春市','吉林市','四平市','辽源市','通化市','白山市','松原市','延边朝鲜族自治州','哈尔滨市','齐齐哈尔市','鸡西市','鹤岗市','双鸭山市','大庆市','伊春市','佳木斯市','七台河市','牡丹江市','黑河市','绥化市',
                '海口市','三亚市'];
            if (cities.indexOf(row.city) < 0){
              //  console.log(row.province);
                $element.children('td:eq(12)').tips({
                    side : 1,
                    msg : '请输入正确格式的市',
                    bg : '#1d21ff',
                    time : 3
                });
                $element.focus();
                return false;
            }else{
                return true;
            }
        }
        function checkErrorCity(row,$element){
            cities = ['东城区','西城区','崇文区','宣武区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','怀柔区','平谷区',
                '黄浦区','卢湾区','徐汇区','长宁区','静安区','普陀区','闸北区','虹口区','杨浦区','闵行区','宝山区','嘉定区','浦东新区','金山区','松江区','青浦区','南汇区','奉贤区','崇明县',
                '和平区','河东区','河西区','南开区','河北区','红桥区','塘沽区','汉沽区','大港区','东丽区','西青区','津南区','北辰区','武清区','宝坻区','宁河县','静海县','蓟县',
                '万州区','涪陵区','渝中区','大渡口区','江北区','沙坪坝区','九龙坡区','南岸区','北碚区','万盛区','双桥区','渝北区','巴南区','黔江区','长寿区','綦江县','潼南县','铜梁县','大足县','荣昌县','璧山县','梁平县','城口县','丰都县','垫江县','武隆县','忠县','开县','云阳县','奉节县','巫山县','巫溪县','石柱土家族自治县','秀山土家族苗族自治县','酉阳土家族苗族自治县','彭水苗族土家族自治县','江津市','合川市','永川市','南川市',
                '成都市','自贡市','攀枝花市','泸州市','德阳市','绵阳市','广元市','遂宁市','内江市','乐山市','南充市','眉山市','宜宾市','广安市','达州市','雅安市','巴中市','资阳市','阿坝藏族羌族自治州','甘孜藏族自治州','凉山彝族自治州','贵阳市','六盘水市','遵义市','安顺市','铜仁地区','黔西南布依族苗族自治州','毕节地区','黔东南苗族侗族自治州','黔南布依族苗族自治州','昆明市','曲靖市','玉溪市','保山市','昭通市','丽江市','思茅市','临沧市','楚雄彝族自治州','红河哈尼族彝族自治州','文山壮族苗族自治州','西双版纳傣族自治州','大理白族自治州','德宏傣族景颇族自治州','怒江傈僳族自治州','迪庆藏族自治州','拉萨市','昌都地区','山南地区','日喀则地区','那曲地区','阿里地区','林芝地区','郑州市','开封市','洛阳市','平顶山市','安阳市','鹤壁市','新乡市','焦作市','濮阳市','许昌市','漯河市','三门峡市','南阳市','商丘市','信阳市','周口市','驻马店市','武汉市','黄石市','十堰市','宜昌市','襄樊市','鄂州市','荆门市','孝感市','荆州市','黄冈市','咸宁市','随州市','恩施土家族苗族自治州','长沙市','株洲市','湘潭市','衡阳市','邵阳市','岳阳市','常德市','张家界市','益阳市','郴州市','永州市','怀化市','娄底市','湘西土家族苗族自治州','广州市','韶关市','深圳市','珠海市','汕头市','佛山市','江门市','湛江市','茂名市','肇庆市','惠州市','梅州市','汕尾市','河源市','阳江市','清远市','东莞市','中山市','潮州市','揭阳市','云浮市','南宁市','柳州市','桂林市','梧州市','北海市','防城港市','钦州市','贵港市','玉林市','百色市','贺州市','河池市','来宾市','崇左市','西安市','铜川市','宝鸡市','咸阳市','渭南市','延安市','汉中市','榆林市','安康市','商洛市','兰州市','嘉峪关市','金昌市','白银市','天水市','武威市','张掖市','平凉市','酒泉市','庆阳市','定西市','陇南市','临夏回族自治州','甘南藏族自治州','西宁市','海东地区','海北藏族自治州','黄南藏族自治州','海南藏族自治州','果洛藏族自治州','玉树藏族自治州','海西蒙古族藏族自治州','银川市','石嘴山市','吴忠市','固原市','中卫市','乌鲁木齐市','克拉玛依市','吐鲁番地区','哈密地区','昌吉回族自治州','博尔塔拉蒙古自治州','巴音郭楞蒙古自治州','阿克苏地区','克孜勒苏柯尔克孜自治州','喀什地区','和田地区','伊犁哈萨克自治州','塔城地区','阿勒泰地区','省直辖行政单位','石家庄市','唐山市','秦皇岛市','邯郸市','邢台市','保定市','张家口市','承德市','沧州市','廊坊市','衡水市',
                '太原市','大同市','阳泉市','长治市','晋城市','朔州市','晋中市','运城市','忻州市','临汾市','吕梁市','呼和浩特市','包头市','乌海市','赤峰市','通辽市','鄂尔多斯市','呼伦贝尔市','巴彦淖尔市','乌兰察布市','兴安盟','锡林郭勒盟','阿拉善盟','南京市','无锡市','徐州市','常州市','苏州市','南通市','连云港市','淮安市','盐城市','扬州市','镇江市','泰州市','宿迁市','杭州市','宁波市','温州市','嘉兴市','湖州市','绍兴市','金华市','衢州市','舟山市','台州市','丽水市','合肥市','芜湖市','蚌埠市','淮南市','马鞍山市','淮北市','铜陵市','安庆市','黄山市','滁州市','阜阳市','宿州市','巢湖市','六安市','亳州市','池州市','宣城市','福州市','厦门市','莆田市','三明市','泉州市','漳州市','南平市','龙岩市','宁德市','南昌市','景德镇市','萍乡市','九江市','新余市','鹰潭市','赣州市','吉安市','宜春市','抚州市','上饶市','济南市','青岛市','淄博市','枣庄市','东营市','烟台市','潍坊市','济宁市','泰安市','威海市','日照市','莱芜市','临沂市','德州市','聊城市','滨州市','荷泽市','沈阳市','大连市','鞍山市','抚顺市','本溪市','丹东市','锦州市','营口市','阜新市','辽阳市','盘锦市','铁岭市','朝阳市','葫芦岛市',
                '长春市','吉林市','四平市','辽源市','通化市','白山市','松原市','延边朝鲜族自治州','哈尔滨市','齐齐哈尔市','鸡西市','鹤岗市','双鸭山市','大庆市','伊春市','佳木斯市','七台河市','牡丹江市','黑河市','绥化市',
                '海口市','三亚市'];
            if (cities.indexOf(row.errorCity) < 0){
                // console.log(row.province);
                $element.children('td:eq(10)').tips({
                    side : 1,
                    msg : '请输入正确格式的市',
                    bg : '#1d21ff',
                    time : 3
                });
                $element.focus();
                return false;
            }else{
                return true;
            }
        }

        $("#close").click(function(){
            $('#gridImport').bootstrapTable("destroy");
        });

        //初始化Excel导入的文件
        function InitExcelFile() {
            $("#excelFile").fileinput({
                uploadUrl: "/upload",//上传的地址
                uploadAsync: true,              //异步上传
                language: "zh",                 //设置语言
                showCaption: true,              //是否显示标题
                showUpload: true,               //是否显示上传按钮
                showRemove: true,               //是否显示移除按钮
                showPreview : true,             //是否显示预览按钮
                browseClass: "btn btn-primary", //按钮样式
                dropZoneEnabled: false,         //是否显示拖拽区域
                allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
                maxFileCount: 1,                        //最大上传文件数限制
                previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
                allowedPreviewTypes: null,
                previewFileIconSettings: {
                    'docx': '<i class="glyphicon glyphicon-file"></i>',
                    'xlsx': '<i class="glyphicon glyphicon-file"></i>',
                    'pptx': '<i class="glyphicon glyphicon-file"></i>',
                    'jpg': '<i class="glyphicon glyphicon-picture"></i>',
                    'pdf': '<i class="glyphicon glyphicon-file"></i>',
                    'zip': '<i class="glyphicon glyphicon-file"></i>',
                },
                uploadExtraData: {  //上传的时候，增加的附加参数
                    folder: '数据导入文件'
                }
            });
			//文件上传完成后的事件
            $("#excelFile").on('fileuploaded', function (event, data, previewId, index) {
				var res = data.response; //返回结果
				if (res.status === "success") {
					// showTips('上传成功');
					//提示用户Excel格式是否正常，如果正常加载数据
					$.ajax({
						url: '/CheckExcelColumns',
						type: 'post',
						dataType: 'json',
						data:{
						    'path':res.path,
						},
						success: function (data) {
							if (data.Success) {
								InitImport(res.path); //重新刷新表格数据
								alert("文件已上传，数据加载完毕！");
							}
							else {
								alert("上传的Excel文件检查不通过。请根据页面右上角的Excel模板格式进行数据录入。", "error");
							}
						}
					});
				} else {
					alert("上传失败");
				}
			});
        }

        //根据条件查询并绑定结果
        var $import;
        function InitImport(note) {
            $import = $('#gridImport').bootstrapTable({
                url: '/GetExcelData',                           //请求后台的URL（*）
                // method: 'get',                     //请求方式（*）
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                  //是否显示分页（*）
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 100,                     //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                      //是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,               //是否启用点击选中行
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                queryParams: function (params) {
                    return {
                        file:note,
					}
				},
                columns: [{
                    checkbox: true
                }, {
                    field: 'companyName',
                    title: '公司名称'
                }, {
                    field: 'regNo',
                    title: '注册号'
                }, {
                    field: 'creditCode',
                    title: '信用代码'
                }, {
                    field: 'orgNo',
                    title: '组织机构代码'
                },{
                    field: 'address',
                    title: '地址'
                },{
                    field: 'telephone',
                    title: '座机号'
                },{
                    field: 'mobile',
                    title: '手机号'
                },{
                    field: 'errorProvince',
                    title: '错误的省'
                },{
                    field: 'errorCity',
                    title: '错误的市'
                },{
                    field: 'province',
                    title: '正确的省'
                },{
                    field: 'city',
                    title: '正确的市'
                },{
                    field: 'comments',
                    title: '备注'
                }],
                onLoadSuccess: function () {
                },
                onLoadError: function () {
                    alert("数据加载失败！");
                },
            });
        }

        //保存导入的数据
        function SaveImport() {

            var list = [];//构造集合对象
            var rows = $import.bootstrapTable('getSelections');
            for (var i = 0; i < rows.length; i++) {
                list.push({
                    'companyName': rows[i].companyName, 'regNo': rows[i].regNo, 'creditCode': rows[i].creditCode, 'orgNo': rows[i].orgNo,'province': rows[i].province,
                    'city': rows[i].city, 'errorProvince': rows[i].errorProvince, 'errorCity': rows[i].errorCity, 'comments': rows[i].comments,
                    'address': rows[i].address, 'telephone': rows[i].telephone, 'mobile': rows[i].mobile
                });
            }

            if (list.length === 0) {
                alert("请选择一条记录", "warning");
                return;
            }

            // var postData = { 'list': list };//可以增加其他参数，如{ 'list': list, 'Rucanghao': $("#Rucanghao").val() };
            postData = JSON.stringify(list);
			// alert(postData);
            $.ajax({
                url: '/SaveExcelData',
                type: 'post',
                dataType: 'json',
                data:{
                    "postData":postData,
				},
                success: function (data) {
                    if (data.success) {
                        //保存成功  1.关闭弹出层，2.清空记录显示 3.刷新主列表
                        alert(data.success);
                        $('#gridImport').bootstrapTable("destroy");
                        $("#import").modal("hide");
                    }
                    else {
                        alert("保存失败:有重复数据");
                    }
                    $("table").bootstrapTable("refresh")
                },
            });
        }


		$("#delete").click(function(){
            var rows = $("#table").bootstrapTable('getSelections');
            if (rows.length === 0) {// rows 主要是为了判断是否选中，下面的else内容才是主要
                alert("请先选择要删除的记录!");
                return;
            } else {
                if(confirm("确定要删除吗？")) {
                }else{
                    return false;
                }
                var arrays = new Array();// 声明一个数组
                $(rows).each(function () {// 通过获得别选中的来进行遍历
                    arrays.push(this.id);// id为获得到的整条数据中的一列
                });
                var idcard = arrays.join(','); // 获得要删除的id

                $.ajax({
					type: "post",
					url: "/delete",
					data: {"ids":idcard},
					success:function (data) {
						if (data === '1') {
						    alert("成功");
                        }else{
						    alert("失败");
						}
                        $("#table").bootstrapTable('refresh');
                    }
				})
            }
		});


		function search(){
			var page = $("#table").bootstrapTable('getOptions').pageNumber;
			var size = $("#table").bootstrapTable('getOptions').pageSize;
			var offect = (page-1)*size;
		    $.ajax({
				type:"post",
				url:"/search",
				datatype:'json',
				data:{
                    "content":$("#content").val(),
                    "cmbProvince":$("#cmbProvince").val(),
                    "cmbCity":$("#cmbCity").val(),
                    "condition":$("#condition").val(),
					"offset":offect,
					"rows":size
				},
				success:function(data){

					$("#table").bootstrapTable("load",data);
                    console.log(data);
				}
			})
		}
	</script>
</body>
</html>
