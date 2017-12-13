$(document).ready(function () {
	getshopcar();
});


function getshopcar(){
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/ushopcar/getShopcar", 
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {},
        success : function(result) {  
        	var message = eval(result);
        	var code = message.code;
        	if(code == -1){
        		window.location.href = "login.html";
        	}else if(code == 1){
        		initShopcar(message.data);
        	}else{
        		window.location.href = "404.html";
        	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}
function initShopcar(list){
	var dlTag = $(".cart");
	for(var i=0;i<list.length;i++){
		var pro = list[i];
		var imputTag = $('<input type="checkbox" name="flowerCheck" value="'+ pro.shopcarId + '"/>');
		var aTag = $('<a href="product.html?flowerId='+pro.flowerId+'" class="goodsPic"></a>').append($('<img src="' + pro.flowerPicture +'"/>'));
		var h2Tag = $('<h2></h2>').append($('<a href="'+'product.html?flowerId='+pro.flowerId+'">'+pro.flowerName+'</a>')).append($('<span>'+pro.flowerCount+'</spam>'));
		if(pro.dFlowerPrice != null){
			var pdivTag = $('<div class="priceArea"></div>').append($('<strong>' + pro.dFlowerPrice + '</strong>')).append($('<del>' + pro.flaowerPrice + '</del>'));
		}else{
			var pdivTag = $('<div class="priceArea"></div>').append($('<strong>' + pro.flaowerPrice + '</strong>'));
		}
		var ndivTag = $('<div class="numberWidget"></div>').append($('<input type="button" value="-" class="minus" shopcarid="'+pro.shopcarId+'"/>')).append($('<input type="text" value="' + pro.flowerCount +'" disabled class="number"/>')).append($('<input type="button" value="+" class="plus" shopcarid="'+pro.shopcarId+'"/>'))
		var gdivTag = $('<div class="goodsInfor"></div>').append(h2Tag).append(pdivTag).append(ndivTag);
		var dbTag = $('<a class="delBtn" shopcarid="'+  pro.shopcarId + '">删除</a>');
		var ddTag = $('<dd></dd>');
		ddTag.append(imputTag).append(aTag).append(gdivTag).append(dbTag);
		dlTag.append(ddTag);
	}
	init();
}

function init(){
	$(".edit").toggle(function(){
		$(this).parent().siblings("dd").find(".delBtn").fadeIn();
		$(this).html("完成");
		$(".numberWidget").show();
		$(".priceArea").hide();
	},function(){
		$(this).parent().siblings("dd").find(".delBtn").fadeOut();
		$(this).html("编辑");
		$(".numberWidget").hide();
		$(".priceArea").show();
	});
	//minus
	$(".minus").click(function(){
		var currNum=$(this).siblings(".number");
		var shopcarid=$(this).attr("shopcarid");
		var thisTag = $(this);
		var goodsInforTag = $(this).parent().parent(".goodsInfor").eq(0);
		if(currNum.val()<=1){
			$.ajax({  
		        type : "POST",  
		        url : baseUrl + "/ushopcar/delShopcar", 
		        xhrFields: {
		            withCredentials: true
		        },
		        crossDomain: true,
		        dataType:"json", 
		        data: {"shopcarId":shopcarid},
		        success : function(result) {
		        	var message = eval(result);
			       	var code = message.code;
			       	if(code == 1){
			       		thisTag.parents("dd").remove();
			       		nullTips();
			       	}else if(code == -1){
			       		window.location.href = "login.html";
			       	}else{
			       	 alert(message.msg);
			       	}
		        },
		        error: function(str) {
		        	window.location.href = "404.html";
		        }
		    });
		}else{
			var count=parseInt(currNum.val())-1;
			$.ajax({  
		        type : "POST",  
		        url : baseUrl + "/ushopcar/updateShopcar", 
		        xhrFields: {
		            withCredentials: true
		        },
		        crossDomain: true,
		        dataType:"json", 
		        data: {"shopcarId":shopcarid,"count":count},
		        success : function(result) {
		        	var message = eval(result);
			       	var code = message.code;
			       	if(code == 1){
			       		goodsInforTag.children("h2").children("span").eq(0).text(parseInt(currNum.val())-1);
			       		currNum.val(parseInt(currNum.val())-1);
			       	}else if(code == -1){
			       		window.location.href = "login.html";
			       	}else{
			       	 alert(message.msg);
			       	}
		        },
		        error: function(str) {
		        	window.location.href = "404.html";
		        }
		    });
		}
	});
	//plus
	$(".plus").click(function(){
		var currNum=$(this).siblings(".number");
		var count = parseInt(currNum.val())+1;
		var shopcarid = $(this).attr("shopcarid");
		var goodsInforTag = $(this).parent().parent(".goodsInfor").eq(0);
		$.ajax({  
	        type : "POST",  
	        url : baseUrl + "/ushopcar/updateShopcar", 
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
	        dataType:"json", 
	        data: {"shopcarId":shopcarid,"count":count},
	        success : function(result) {
	        	var message = eval(result);
		       	var code = message.code;
		       	if(code == 1){
		       		goodsInforTag.children("h2").children("span").eq(0).text(parseInt(currNum.val())+1);
		       		currNum.val(parseInt(currNum.val())+1);
		       	}else if(code == -1){
		       		window.location.href = "login.html";
		       	}else{
			        alert(message.msg);
		       	}
	        },
	        error: function(str) {
	        	window.location.href = "404.html";
	        }
	    });
	});
	//delBtn
	$(".delBtn").click(function(){
		var shopcarid = $(this).attr("shopcarid");
		var thisTag = $(this);
		$.ajax({  
			type : "POST",  
		  	url : baseUrl + "/ushopcar/delShopcar", 
		 	xhrFields: {
		   		withCredentials: true
		  	},
		   	crossDomain: true,
		   	dataType:"json", 
		   	data: {"shopcarId":shopcarid},
		  	success : function(result) {  
		     	var message = eval(result);
		       	var code = message.code;
		      	if(code == -1){
		        	window.location.href = "login.html";
		       	}else if(code == 1){
		        	thisTag.parent().remove();
		        	nullTips();
		       	}else{
		        	alert(message.msg);
		      	}
		  	},
		   	error: function(str) {
		   		window.location.href = "404.html";
		   	}
		});
	});
	//isNull->tips
	function nullTips(){
		if($(".cart dd").length==0){
			var tipsCont="<mark style='display:block;background:none;text-align:center;color:grey;'>购物车为空！</mark>";
			$(".cart").remove();
			$("body").append(tipsCont);
		}
	}
	
	function getAllPrice(){
		var allPrice = 0.0;
		var $checkB = $("input[name='flowerCheck']").each(
			function(){
				if($(this).is(":checked")){
					var goodInfoTag = $(this).siblings(".goodsInfor");
					var num = goodInfoTag.find("span").text();
					var price = goodInfoTag.find("strong").text();
					allPrice += (num*price);
				}
			});
		lastPrice = allPrice.toFixed(2);
		$(".btmNav a:first").text("合计：￥" + allPrice.toFixed(2));
	}
	
	$("#checkAll").click(function() {  
        $('input[name="flowerCheck"]').attr("checked",this.checked);   
        getAllPrice();
    });  
    var $subBox = $("input[name='flowerCheck']");  
    $subBox.click(function(){  
        $("#checkAll").attr("checked",$subBox.length == $("input[name='flowerCheck']:checked").length ? true : false);  
        getAllPrice();
    });
    
    $("#startOrder").click(function(){
		var array = new Array();
		var i = 0;
		var allPrice = 0.0;
		var $checkB = $("input[name='flowerCheck']").each(
			function(){
				if($(this).is(":checked")){
					array[i] = $(this).val();
					i++;
					var goodInfoTag = $(this).siblings(".goodsInfor");
					var num = goodInfoTag.find("span").text();
					var price = goodInfoTag.find("strong").text();
					allPrice += (num*price);
				}
			});
		if(i > 0){
			flowerArray = array;
			//lastPrice = allPrice;
			window.location.href = "confirm_order.html?lastPrice="+lastPrice+"&flowerArray="+flowerArray;
		}
	
	});
}
