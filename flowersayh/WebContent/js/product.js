$(document).ready(function () {
	 flowerInfo();
	 createShopcar();
     $(".btmNav a:first").click(function(){
    	var flowerId = $(this).attr("flowerid");
 		$.ajax({  
 			type : "POST",  
 			url : baseUrl + "/ushopcar/addShopcar",
 			 xhrFields: {
 	            withCredentials: true
 	        },
 	        crossDomain: true,
 		 	data: {"flowerId":flowerId},
 			dataType:"json", 
 		 	success : function(result) {  
 		 		var message = eval(result);
 		 		var code = message.code;
 		 		if(code == 1){
 		 			$(".topCart em").html(parseInt($(".topCart em").html())+1);
 		 		}else if(code == -1){
 		 			window.location.href = "login.html";
 		 		}else{
 		 			alert("不要重复添加哦");
 		 		}
 		   	},
 		   	error: function(str) {
 		   	window.location.href = "404.html";
 		  	}
 		});
 	});
     
     $(".btmNav a:last").click(function(){
     	var flowerId = $(this).attr("flowerid");
     	$.ajax({  
 			type : "POST",  
 			url : baseUrl + "/ushopcar/addOftenbuy",
 			 xhrFields: {
 	            withCredentials: true
 	        },
 	        crossDomain: true,
 		 	data: {"flowerId":flowerId},
 			dataType:"json", 
 		 	success : function(result) {  
 		 		var message = eval(result);
 		 		var code = message.code;
 		 		if(code == 1){
 		 			alert("成功");
 		 		}else if(code == -1){
 		 			window.location.href = "login.html";
 		 		}else{
 		 			alert("不要重复添加哦");
 		 		}
 		   	},
 		   	error: function(str) {
 		   	window.location.href = "404.html";
 		  	}
 		});
  	});
});

//鲜花展示初始化
function flowerInfo(){
	var reg = new RegExp("(^|&)" + "flowerId" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    var flowerId = 1;
    if (r != null){
   	 flowerId = unescape(r[2]);
    }
    $.ajax({  
        type : "POST",  
        url : baseUrl + "/search/searchFlowerById",  
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {"flowerId":flowerId},
        success : function(result) {  
       	var message = eval(result);
        	var code = message.code;
        	if(code == 1){
        		initFlower(message.data);
        	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}
//购物车初始化
function createShopcar() {
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/ushopcar/shopcarCount", 
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
        		$(".topCart em").html(message.data);
        	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}

function initFlower(result){
	$(".pro_bigImg img").attr("src",result.flowerPicture);
	$(".pro_baseInfor h2").html(result.flowerName);
	if(result.flowerDiscountPrice != null){
		$(".pro_baseInfor p strong").html(result.flowerDiscountPrice);
		$(".pro_baseInfor p del").html(result.flowerPrice);
	}else{
		$(".pro_baseInfor p strong").html(result.flowerPrice);
		$(".pro_baseInfor p del").remove();
	}
	var liTag = $(".pro_attr dd ul li");
	var time = new Date(result.flowerAddtime);
	var year = time.getFullYear();
	var month = time.getMonth()+1;
	liTag.eq(0).children("em").html(year+"."+month);
	liTag.eq(1).children("em").html(result.flowerCount);
	liTag.eq(2).children("em").html(result.flowerNorms);
	var state = "售完";
	if(result.flowerState == 1){
		state = "在售";
	}
	liTag.eq(3).children("em").html(state);
	liTag.eq(4).children("em").html(result.flowerUse);
	liTag.eq(5).children("em").html(result.flowerDescribe);
	$(".btmNav a").attr("flowerid",result.flowerId);
}