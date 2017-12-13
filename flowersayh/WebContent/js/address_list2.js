$(document).ready(function () {  
	var reg1 = new RegExp("(^|&)" + "lastPrice" + "=([^&]*)(&|$)");
    var r1 = window.location.search.substr(1).match(reg1);
    if (r1 != null){
    	lastPrice = unescape(r1[2]);
    }
    
    var reg2 = new RegExp("(^|&)" + "flowerArray" + "=([^&]*)(&|$)");
    var r2 = window.location.search.substr(1).match(reg2);
    if (r2 != null){
    	flowerArray = unescape(r2[2]);
    }
    
    var reg3 = new RegExp("(^|&)" + "message" + "=([^&]*)(&|$)");
    var r3 = encodeURI(window.location.search).substr(1).match(reg3);
    if (r3 != null){
    	message = decodeURI(unescape(r3[2]));
    }
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
		var divTag = $('<div class="updateAddr" addressid="' + pro.addressId + '"></div');
		divTag.append(pTag).append(addTag);
		var aTag = $('<a href="address.html?addressId='+pro.addressId+'" class="iconfont">&#60;</a>');
		var asideTag = $('<aside class="confirmAddr"></aside>');
		asideTag.append(divTag).append(aTag);
		addressTag.append(asideTag);
	}
	$(".updateAddr").click(function(){
		var aid = $(this).attr("addressid");
		addressId = aid;
		window.location.href = "confirm_order.html?addressId="+addressId+"&flowerArray="+flowerArray+"&lastPrice="+lastPrice+"&message="+message;
	});
	$(".backIcon").click(function(){
		window.location.href = "confirm_order.html?flowerArray="+flowerArray+"&lastPrice="+lastPrice+"&message="+message;
	});
}