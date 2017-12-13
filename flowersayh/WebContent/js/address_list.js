$(document).ready(function () {  
	getAddress();
});

function getAddress(){
	$.ajax({  
		type : "POST",  
		url : baseUrl + "/uaddress/getAllAddress", 
		xhrFields: {
			withCredentials: true
	   	},
	    crossDomain: true,
	 	data: {},
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
function init(data){
	var addressTag = $(".addresslist");
	for(var i = 0; i<data.length; i++){
		var pro = data[i];
		var name = pro.addressReceiver;
		var phone = pro.addressPhone;
		var address = pro.addressProvince + pro.addressCity + pro.addressArea + pro.addressAddress;
		var pTag = $('<p></p>').append($('<span>收货人:'+name+'</span>')).append($('<span>'+phone+'</span>'));
		var addTag = $('<address>'+address+'</address>');
		var aTag = $('<a href="address.html?addressId='+pro.addressId+'" class="iconfont">&#60;</a>');
		var asideTag = $('<aside class="confirmAddr"></aside>');
		asideTag.append(pTag).append(addTag).append(aTag);
		addressTag.append(asideTag);
	}
}