$(document).ready(function(){
	createShopcar();
	getMess(1);
    $(".more_btn").click(function(){
		var page = $(this).attr("page");
		getMess(parseInt(page)+1);
	});
    
    $(".drop_icon").click(function(){
  	  $(".drop_list").toggle();
  	  $(".drop_list li a").click(function(){
  		  $(this).parents(".drop_list").hide();
  		  });
  	  });
});

function getMess(page){
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/search/searchFlower",  
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {"page":page,"flag":"1"},
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
		 		}
		   	},
		   	error: function(str) {
		   		window.location.href = "404.html";
		  	}
		});
	});
}