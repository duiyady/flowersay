$(document).ready(function () {  
	createType();
	createShopcar();
	createFlower(0);
	
});
//分类初始化
function createType(){
	 $.ajax({  
	        type : "POST",  
	        url : baseUrl + "/base/allType",  
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
	        dataType:"json", 
	        data: {"flag":"1"},
	        success : function(result) {  
	        	initType(result,1);
	        },
	        error: function(str) {
	        	window.location.href = "404.html";
	        }
	    });
	    $.ajax({  
	        type : "POST",  
	        url : baseUrl + "/base/allType", 
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
	        data: {"flag":"2"},
	        dataType:"json", 
	        success : function(result) {  
	        	initType(result,2);
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
        		var aTag = $(".hoverCart a:first");
        		aTag.text(message.data);
        	}
        },
        error: function(str) {
        	
        }
    });
}
//商品展示初始化
function createFlower(option){
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/search/searchFlowerIndex",  
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        data: {"option":option},
        dataType:"json", 
        success : function(result) {  
        	var message = eval(result);
        	var code = message.code;
        	if(code == 1){
        		initFlower(message.data,option);
        	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}

//填充鲜花
function initFlower(result,option){
	var ddTag = $(".tab_proList dd");
	var ulTag = ddTag.eq(option).children("ul").eq(0);
	ulTag.empty();
	for(var i = 0;i<result.length;i++){
		var pro = result[i];
		var aTag = $('<a href="product.html?flowerId='+pro.flowerId+'" class="goodsPic"></a>').append($('<img src="'+pro.flowerPicture +'"/>'));
		var divTag = $('<div class="goodsInfor"></div>');
		var h2Tag = $('<h2></h2>').append($('<a href="product.html?flowerId='+pro.flowerId+'">'+pro.flowerName+'</a>'));
		if(pro.flowerDiscountPrice != null){
			var p1Tag = $('<p></p>').append($('<del>'+pro.flowerPrice+'</del>'));
			var p2Tag = $('<p></p>').append($('<strong class="price">'+pro.flowerDiscountPrice+'</strong>'));
			var a2Tag = $('<a class="addToCart" flowerid="'+pro.flowerId+'">&#126;</a>');
			divTag.append(h2Tag).append(p1Tag).append(p2Tag).append(a2Tag);
		}else{
			var p2Tag = $('<p></p>').append($('<strong class="price">'+pro.flowerPrice+'</strong>'));
			var a2Tag = $('<a class="addToCart" flowerid="'+pro.flowerId+'">&#126;</a>');
			divTag.append(h2Tag).append(p2Tag).append(a2Tag);
		}
		var liTag = $('<li></li>');
		liTag.append(aTag).append(divTag);
		ulTag.append(liTag);
	}
	$(".addToCart").click(function(){
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
		 			$(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*测试+1*/
		 			var shopOffset = $(".hoverCart").offset();
		 			var cloneDiv = $(this).parent().siblings(".goodsPic").clone();
		 			var proOffset = $(this).parent().siblings(".goodsPic").offset();
		 			cloneDiv.css({ "position": "absolute", "top": proOffset.top, "left": proOffset.left });
		 			$(this).parent().siblings(".goodsPic").parent().append(cloneDiv);
		 			cloneDiv.animate({
		 				width:0,
		 				height:0,
		 				left: shopOffset.left,
		 				top: shopOffset.top,
		 				opacity:1
		 			},"slow");
		 		}else if(code == -1){
		 			window.location.href = "login.html";
		 		}else{
		 			alert("添加失败");
		 		}
		   	},
		   	error: function(str) {
		   		window.location.href = "404.html";
		  	}
		});
	});
}
//填充类型
function initType(result,flag){
	var message = eval(result);
	var code = message.code;
	if(code == 1 || code == 2){
		var list = message.data;
		for(var i = 0;i<list.length&&i<4;i++){
			var pro = list[i];
			if(flag == 1){
				var aTag = $('<a href="product_list.html?flowerClassify='+pro.typeName+'"></a>').append($('<img src="'+pro.typePicture+'"/>')).append($('<em>'+pro.typeName+'</em>'));
			}else{
				var aTag = $('<a href="product_list.html?flowerUse='+pro.typeName+'"></a>').append($('<img src="'+pro.typePicture+'"/>')).append($('<em>'+pro.typeName+'</em>'));
				if(i == 3){
					if(code == 1){
						aTag = $('<a href="product_list.html"></a>').append($('<img src="'+pro.typePicture+'"/>')).append($('<em>'+pro.typeName+'</em>'));
					}else{
						aTag = $('<a href="favorite.html"></a>').append($('<img src="'+pro.typePicture+'"/>')).append($('<em>'+pro.typeName+'</em>'));
					}
				}
			}
			var liTag = $('<li></li>').append(aTag);
			$(".categoryLiIcon").append(liTag);
		}
	}else{
		alert("获取信息失败");
	}
}

