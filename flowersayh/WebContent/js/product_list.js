var flowerClassify = null;
var flowerUse = null;
var message = null;
var price = null;
var sale = null;
$(document).ready(function(){
	createShopcar();
	var reg1 = new RegExp("(^|&)" + "flowerClassify" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var reg2 = new RegExp("(^|&)" + "flowerUse" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var reg3 = new RegExp("(^|&)" + "message" + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	
	var r1 = window.location.search.substr(1).match(reg1);
	var r2 = window.location.search.substr(1).match(reg2);
	var r3 = window.location.search.substr(1).match(reg3);
	
    flowerClassify = r1?decodeURIComponent(r1[2]):null;
    flowerUse = r2?decodeURIComponent(r2[2]):null;
    message = r3?decodeURIComponent(r3[2]):null;
    getMess(1,price,sale);
    
    $(".more_btn").click(function(){
		var page = $(this).attr("page");
		getMess(parseInt(page)+1,price,sale);
	});
    
    $(".drop_icon").click(function(){
  	  $(".drop_list").toggle();
  	  $(".drop_list li a").click(function(){
  		  $(this).parents(".drop_list").hide();
  		  });
  	  });
    $("#price").click(function(){
    	var cl = $(this).attr('class');
    	if(cl == null || cl == "undefined"){
    		$(this).text("价格优先");
    		$(this).attr("class", "asc_icon");
    		$("#sale").text("销量");
    		$("#sale").removeAttr("class");
    		price = "asc";
    		sale = null;
    		$(".more_btn").attr("page","1");
    		getMess(1,price,sale);
    	}else{
    		if(cl == "des_icon"){
    			$(this).removeClass("des_icon");
    			$(this).attr("class", "asc_icon");
    			price = "asc";
    			$(".more_btn").attr("page","1");
        		getMess(1,price,sale);
    		}else{
    			$(this).removeClass("asc_icon");
    			$(this).attr("class", "des_icon");
    			price = "desc";
    			$(".more_btn").attr("page","1");
        		getMess(1,price,sale);
    		}
    	}
    });
    $("#sale").click(function(){
    	var cl = $(this).attr('class');
    	if(cl == null || cl == "undefined"){
    		$(this).text("销量优先");
    		$(this).attr("class", "asc_icon");
    		$("#price").text("价格");
    		$("#price").removeAttr("class");
    		price = null;
    		sale = "asc";
    		$(".more_btn").attr("page","1");
    		getMess(1,price,sale);
    	}else{
    		if(cl == "des_icon"){
    			$(this).removeClass("des_icon");
    			$(this).attr("class", "asc_icon");
    			sale = "asc";
    			$(".more_btn").attr("page","1");
        		getMess(1,price,sale);
    		}else{
    			$(this).removeClass("asc_icon");
    			$(this).attr("class", "des_icon");
    			sale = "desc";
    			$(".more_btn").attr("page","1");
        		getMess(1,price,sale);
    		}
    	}
    });
});

function getMess(page,price,sale){
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/search/searchFlower",  
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {"flowerClassify":flowerClassify,"flowerUse":flowerUse,"message":message,"page":page,"price":price,"sale":sale},
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
        		var aTag = $(".hoverCart a:first");
        		aTag.text(message.data);
        	}
        },
        error: function(str) {
        	window.location.href = "404.html";
        }
    });
}

//填充鲜花
function initFlower(result){
	if(result != null){
	var ulTag = $(".productList ul").eq(0);
	var page = result.page;
	var allPage = result.allPage;
	var datalist = result.dataList;
	if(page == 1){
		ulTag.empty();
	}
	if(page == allPage || allPage == 0){
		$(".more_btn").hide();
	}else{
		$(".more_btn").show();
		$(".more_btn").attr("page",page);
		$(".more_btn").attr("allPage",allPage);
	}
	for(var i = 0;i<datalist.length;i++){
		var pro = datalist[i];
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
	}else{
		$(".more_btn").hide();
	}
}