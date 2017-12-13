$(document).ready(function () {  
	getOrderlist(0,1);
	$(".orderSift a").click(function(){
		$(this).addClass("currStyle").siblings().removeClass("currStyle");
		$(".orderList").empty();
		getOrderlist($(this).attr("state"),1);
	});
	$(".more_btn").click(function(){
		var state = $(".currStyle").attr("state");
		var page = $(this).attr("page");
		getOrderlist(state,parseInt(page)+1);
	});
	
});

//异步请求数据 0待付款 1待发货 2待收货 3已完成
function getOrderlist(state,page){
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/uorder/getOrderList", 
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {"orderState":state,"page":page},
        success : function(result) {
        	var message = eval(result);
	       	var code = message.code;
	       	if(code == 1){
	       		initOrder(message.data,state);
	       	}else if(code == -1){
	       		window.location.href = "login.html";
	       	}else{
	       		window.location.href = "404.html";
	       	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}
//填充数据
function initOrder(result,state){
	var page = result.page;
	var allPage = result.allPage;
	var datalist = result.dataList;
	if(page == 1){
		$(".orderList").empty();
	}
	if(page == allPage || allPage == 0){
		$(".more_btn").hide();
	}else{
		$(".more_btn").show();
		$(".more_btn").attr("page",page);
		$(".more_btn").attr("allPage",allPage);
	}
	
	var ulTag = $(".orderList");
	var stateName = "待付款";
	if(state == 1){
		stateName = "待发货";
	}else if(state == 2){
		stateName = "待收货";
	}else{
		stateName = "已完成";
	}
	for(var i = 0;i<datalist.length;i++){
		var pro = datalist[i];
		var dlTag = $('<dl></dl>');
		var dtTag = $('<dt></dt>').append($('<span>订单：'+pro.orderMId+'</span>')).append($('<span>'+stateName+'</span>'));
		dlTag.append(dtTag);
		var flowerlist = pro.flowerList;
		for(var j = 0;j<flowerlist.length;j++){
			var pro2 = flowerlist[j];
			var ddTag = $('<dd></dd>').append($('<h2>' + pro2.flowerName + '</h2>')).append($('<strong></strong>').append($('<em>'+pro2.flowerPrice+'</em>')).append($('<span>'+pro2.flowerCount+'</span>')));
			dlTag.append(ddTag);
		}
		var dd1Tag = $('<dd></dd>').append($('<span>商品数量：</span>').append($('<b>'+flowerlist.length+'</b>'))).append($('<span>实付：</span>').append($('<b>'+pro.orderPrice+'</b>')));
		dlTag.append(dd1Tag);
		if(state == 0){
			var dd2Tag = $('<dd></dd>').append($('<a class="order_delBtn">删除订单</a>')).append($('<a class="order_payBtn">付款</a>'));
			dlTag.append(dd2Tag);
		}else if(state == 1){
			var dd2Tag = $('<dd></dd>').append($('<a class="order_delBtn">取消订单</a>')).append($('<a class="order_payBtn">提醒</a>'));
			dlTag.append(dd2Tag);
		}else if(state == 2){
			var dd2Tag = $('<dd></dd>').append($('<a class="order_payBtn">确认收货</a>'));
			dlTag.append(dd2Tag);
		}else{
			var dd2Tag = $('<dd></dd>').append($('<a class="order_payBtn">删除订单</a>'));
			dlTag.append(dd2Tag);
		}
		var liTag = $('<li orderid="'+pro.orderId+'"></li>').append(dlTag);
		ulTag.append(liTag);
	}
	$(".order_payBtn").click(function(){
		var liTag = $(this).parent().parent().parent();
		var orderId = liTag.attr("orderid");
		var state = $(".currStyle").attr("state");
		if(state == 0){
			//支付订单
			updateState(liTag,1);
		}else if(state == 1){
			//提醒发货
			remindSale(liTag);
		}else if(state == 2){
			//订单收货
			updateState(liTag,3);
		}else if(state == 3){
			//删除订单
			deleteOrder(liTag,state);
		}
		
	});
	$(".order_delBtn").click(function(){
		var liTag = $(this).parent().parent().parent();
		var orderId = liTag.attr("orderid");
		var state = $(".currStyle").attr("state");
		if(state == 0){
			//删除订单
			deleteOrder(liTag,state);
			
		}else if(state == 1){
			//取消订单
			deleteOrder(liTag,state);
		}
	});
}

function updateState(liTag,orderState){
	var orderId = liTag.attr("orderid");
	 $.ajax({  
	 	type : "POST",  
	 	url : baseUrl + "/uorder/updateState",
	 	xhrFields: {
	 		withCredentials: true
	 	},
	 	crossDomain: true,
	 	data: {"orderId":orderId,"orderState":orderState},
	 	dataType:"json", 
	 	success : function(result) {  
	 		var message = eval(result);
	       	var code = message.code;
	      	if(code == 1){
	      		delTag(liTag);
	      	}else if(code == -1){
	      		window.location.href = "login.html";
	      	}else{
	      		window.location.href = "404.html";
	      	}
	 	},
	 	error: function(str) {
	 		window.location.href = "404.html";
	 	}
	});
}

function deleteOrder(liTag,orderState){
	var orderId = liTag.attr("orderid");
	$.ajax({  
	 	type : "POST",  
	 	url : baseUrl + "/uorder/deleteOrder",
	 	xhrFields: {
	 		withCredentials: true
	 	},
	 	crossDomain: true,
	 	data: {"orderId":orderId,"orderState":orderState},
	 	dataType:"json", 
	 	success : function(result) {  
	 		var message = eval(result);
	       	var code = message.code;
	      	if(code == 1){
	      		alert("第二次");
	      		delTag(liTag);
	      	}else if(code == -1){
	      		window.location.href = "login.html";
	      	}else{
	      		window.location.href = "404.html";
	      	}	
	 	},
	 	error: function(str) {
	 		window.location.href = "404.html";
	 	}
	});
}

function remindSale(liTag){
	var orderId = liTag.attr("orderid");
	$.ajax({  
	 	type : "POST",  
	 	url : baseUrl + "/uorder/remindSale",
	 	xhrFields: {
	 		withCredentials: true
	 	},
	 	crossDomain: true,
	 	data: {"orderId":orderId},
	 	dataType:"json", 
	 	success : function(result) {  
	 		var message = eval(result);
	       	var code = message.code;
	      	if(code == 1){
	      		alert("已提醒卖家尽快发货了");
	      	}else if(code == -1){
	      		window.location.href = "login.html";
	      	}else{
	      		window.location.href = "404.html";
	      	}	
	 	},
	 	error: function(str) {
	 		window.location.href = "404.html";
	 	}
	});
}

function delTag(liTag){
	alert(liTag);
	var orderId = liTag.attr("orderid");
	alert(orderId);
	liTag.remove();
	
}