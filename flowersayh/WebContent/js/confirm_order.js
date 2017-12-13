$(document).ready(function () {  
	getMessage();
	getPrice();
	getFlowerArray();
	getAddress();
	$(".tijiao").click(function(){
		var fa = flowerArray;
		var ad = addressId;
		var lp = lastPrice;
		var me = $(".message").val();
		$.ajax({  
			type : "POST",  
			url : baseUrl + "/uorder/addOrder",  
			xhrFields: {
				withCredentials: true
		 	},
		 	crossDomain: true,
		 	dataType:"json", 
			data: {"addressId":ad,"orderPrice":lp,"orderMess":me,"buycarIdList":flowerArray,"orderState":"1"},
			success : function(result) {  
				var message = eval(result);
				var code = message.code;
				if(code == 1){
					window.location.href="return_state.html?orderMid=" + message.data +"&lastPrice="+lastPrice;
				}else if(code == -1){
					window.location.href="login.html";
				}else{
					window.location.href="404.html";
				}
		  	},
		 	error: function(str) {
		 		window.location.href = "404.html";
		 	}
		});
	});
});

//获取地址
function getAddress(){
	var reg = new RegExp("(^|&)" + "addressId" + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
    	addressId = unescape(r[2]);
    }
	if(addressId != null){
		 $.ajax({  
			type : "POST",  
			url : baseUrl + "/uaddress/getAddressById",  
			xhrFields: {
				withCredentials: true
		 	},
		 	crossDomain: true,
		 	dataType:"json", 
			data: {"addressId":addressId},
			success : function(result) {  
				initAddress(result);
		  	},
		 	error: function(str) {
		 		window.location.href = "404.html";
		 	}
		});
	}else{
		$.ajax({  
			type : "POST",  
			url : baseUrl + "/uaddress/getAddress",  
			xhrFields: {
				withCredentials: true
		 	},
		 	crossDomain: true,
		 	dataType:"json", 
			data: {},
			success : function(result) {  
				initAddress(result);
		  	},
		 	error: function(str) {
		 		window.location.href = "404.html";
		 	}
		});
	}
	
}

//获取留言
function getMessage(){
	var reg = new RegExp("(^|&)" + "message" + "=([^&]*)(&|$)");
    var r = encodeURI(window.location.search).substr(1).match(reg);
    if (r != null){
    	message = decodeURI(unescape(r[2]));
		$(".message").val(message);
	}else{
		message = null;
	}
}

//获取总价
function getPrice(){
	var reg = new RegExp("(^|&)" + "lastPrice" + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
    	lastPrice = unescape(r[2]);
	$(".btmNav a:first").text("合计：￥" + lastPrice);
}

function getFlowerArray(){
	var reg = new RegExp("(^|&)" + "flowerArray" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null)
    	flowerArray = unescape(r[2]);
}

//填充地址
function initAddress(result){
	var message = eval(result);
	var code = result.code;
	if(code == 1){
		//成功
		var pro = result.data;
		if(pro != null){
			addressId = pro.addressId;
			var name = pro.addressReceiver;
			var phone = pro.addressPhone;
			var address = pro.addressProvince + pro.addressCity + pro.addressArea + pro.addressAddress;
			var pTag = $('<p></p>').append($('<span>收货人:'+name+'</span>')).append($('<span>'+phone+'</span>'));
			var addTag = $('<address>'+address+'</address>');
			var aTag = $('<a class="iconfont address">&#60;</a>');
			var asideTag = $(".confirmAddr").eq(0);
			asideTag.empty();
			asideTag.append(pTag).append(addTag).append(aTag);
		}else{
			var pTag = $('<p></p>').append($('<span></span>')).append($('<span></span>'));
			var addTag = $('<address>请添加收货地址</address>');
			var aTag = $('<a href="address_list2.html" class="iconfont">&#60;</a>');
			var asideTag = $(".confirmAddr").eq(0);
			asideTag.empty();
			asideTag.append(pTag).append(addTag).append(aTag);
		}
	}else if(code == -1){
		window.location.href = "login.html";
	}else{
		window.location.href = "404.html";
	}
	
	$(".address").click(function(){
		var lmessage = $(".message").val();
		var h = "address_list2.html?message="+lmessage+"&lastPrice="+lastPrice+"&flowerArray="+flowerArray;
		window.location.href=h;
	});
	$('textarea').bind('input propertychange', function(){  
		message = $(".message").val();
	});
}

