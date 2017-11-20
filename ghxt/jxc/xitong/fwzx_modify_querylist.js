/*******************************************************************************
 * 公用查询：服务中心信息列表查询
 ******************************************************************************/
$(document).ready(function() {
	var config = {
		serviceid : "queryFwzx.action",
		perPage : 10,
		ajaxParam : {},
		async : false,
	SuccessFunName : "querySucc",		
	ErrorFunName : "queryError"
	};
	$("#pageGrxx").pagination(config);
    pageGrxx.query();
});

/**
 * 查询成功回调函数
 * 
 * @param {}
 *            msg
 * @return {}
 */
function querySucc(res) {
	var trs = "",//数据记录对应HTML字符串
		trClass, //样式类名
       //单元格		
		aab001, //编号
		aab004, //名称
		aab008, //人数
		aae036, //日期
		aae013, //备
		aab052,
		aab053,
        //单元格	
		datas;
	if (res.tipInfo =="success") {
		datas = res.message.datas;
		if(datas.length) {
			$.each(datas, function(i, data){
				trClass = (i % 2) ? "even" : "odd";
				//xh = "<td>" + (i + 1) + "</td>";
				aab001 = "<td>" + data.aab001 + "</td>";
				aab004 = "<td>" + data.aab004 + "</td>";
				aab052 = "<td>" + data.aab052 + "</td>";
				aab053 = "<td>" + data.aab053 + "</td>";
				aae013 = "<td>" + data.aae013 + "</td>";
				operate = "<td><a href='#' onclick=\"detailShow('"+ data.aab001 + "')\">修改</a>" + "</td>";
				trs += "<tr class='"+ trClass+ "' onmouseover=\"this.className='highlight'\" ondblclick=\"detailShow('"+ data.aab001 + "')\" onmouseout=\"this.className='" +trClass+ "'\">" 
					 + aab001 + aab004 + aab052 + aab053+ aae013 + operate
					 + "</tr>";
			});
		} else {
			trs += "<tr><td colspan='5'>对不起，查询不到符合条件的记录！</td></tr>";
		}
	} else {
		trs += "<tr><td colspan='5'>查询发生错误，错误信息为:" + res.message+ "</td></tr>";
	}
	return trs;
}

/**
 * 查询失败回调函数
 * 
 * @param res
 */
function queryError(res) {
	$.jBox.info("查询失败：" + res.message);
}
/**
 * 条件查询
 */
function query(){
	var param=CRUD.getValues('#form1');
	pageGrxx.query(param);
}
/**
 * 查看操作
 * 
 * @param aac005
 * @return
 */
function detailShow(aab001) {
	$.jBox("iframe:./fwzx_view.html?aab001=" + aab001, {
		title : "服务中心信息修改",
		width : ($(window).width() / 10) * 9,
		height : ($(window).height() / 10) * 9.5,
		buttons : {
			'关闭' : true
		},
		closed : function() {
			query();
		},
		top : '0%'
	});
}

