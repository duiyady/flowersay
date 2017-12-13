$(document).ready(function () {  
	var reg = new RegExp("(^|&)" + "addressId" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null){
    	getAdd(unescape(r[2]))
    }else{
    	addAdd();
    }
    $(".formLastBtn").click(function(){
    	if($(this).attr("func") == 0){
    		ajaxAdd();
    	}else{
    		ajaxUpdate();
    	}
    });
});

function ajaxAdd(){
	var addressReceiver = $(".username").val();
	var addressPhone = $(".userphone").val();
	var addressProvince = $(".province").val();
	var addressCity = $(".city").val();
	var addressArea = $(".area").val();
	var addressAddress = $(".address").val();
	if(addressReceiver == null || addressReceiver == ""
		||addressPhone == null || addressPhone == ""
		||addressProvince == null || addressProvince == ""
		||addressCity == null || addressCity == ""
		||addressArea == null || addressArea == ""
		||addressAddress == null || addressAddress == ""){
		alert("不能为空");
	}else{
		$.ajax({  
			type : "POST",  
			url : baseUrl + "/uaddress/addAddress", 
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			data: {"addressReceiver":addressReceiver
				,"addressPhone":addressPhone
				,"addressProvince":addressProvince
				,"addressCity":addressCity
				,"addressArea":addressArea
				,"addressAddress":addressAddress},
			dataType:"json", 
			success : function(result) {  
				var message = eval(result);
				var code = message.code;
				if(code == 1){
					window.location.href = "javascript:history.go(-1);";
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
}

function ajaxUpdate(){
	var addressReceiver = $(".username").val();
	var addressPhone = $(".userphone").val();
	var addressProvince = $(".province").val();
	var addressCity = $(".city").val();
	var addressArea = $(".area").val();
	var addressAddress = $(".address").val();
	var addressId = $(".formLastBtn").attr("addressid");
	if(addressReceiver == null || addressReceiver == ""
		||addressPhone == null || addressPhone == ""
		||addressProvince == null || addressProvince == ""
		||addressCity == null || addressCity == ""
		||addressArea == null || addressArea == ""
		||addressAddress == null || addressAddress == ""){
		alert("不能为空");
	}else{
		$.ajax({  
			type : "POST",  
			url : baseUrl + "/uaddress/updateAddress", 
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			data: {"addressId":addressId
				,"addressReceiver":addressReceiver
				,"addressPhone":addressPhone
				,"addressProvince":addressProvince
				,"addressCity":addressCity
				,"addressArea":addressArea
				,"addressAddress":addressAddress},
			dataType:"json", 
			success : function(result) {  
				var message = eval(result);
				var code = message.code;
				if(code == 1){
					window.location.href = "javascript:history.go(-1);";
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
}

function getAdd(addressId){
	$.ajax({  
		type : "POST",  
		url : baseUrl + "/uaddress/getAddressById", 
		xhrFields: {
			withCredentials: true
	   	},
	    crossDomain: true,
	 	data: {"addressId":addressId},
		dataType:"json", 
	 	success : function(result) {  
	 		var message = eval(result);
	 		var code = message.code;
	 		if(code == 1){
	 			init(message.data)
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

function addAdd(){
	$(".formLastBtn").val("添加地址");
	$(".formLastBtn").attr("func","0");
}
function init(result){
	$(".username").val(result.addressReceiver);
	$(".userphone").val(result.addressPhone);
	$(".province").val(result.addressProvince);
	$(".city").val(result.addressCity);
	$(".area").val(result.addressArea);
	$(".address").val(result.addressAddress);
	$(".formLastBtn").attr("addressid",result.addressId);
	
}