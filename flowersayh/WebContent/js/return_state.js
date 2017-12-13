$(document).ready(function () {
	var reg = new RegExp("(^|&)" + "orderMid" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    var orderMid = null;
    if (r != null)
    	orderMid = unescape(r[2]);
    var reg1 = new RegExp("(^|&)" + "lastPrice" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r1 = window.location.search.substr(1).match(reg1);  //匹配目标参数
    var lastPrice = null;
    if (r1 != null)
    	lastPrice = unescape(r1[2]);
    $(".ddbh").text("订单编号："+orderMid);
    $(".jiage").text(lastPrice);
	
});