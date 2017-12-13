$(document).ready(function () {  
	createType(1);
});
//分类初始化
function createType(flag){
	 $.ajax({  
	        type : "POST",  
	        url : baseUrl + "/base/allType",  
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
	        dataType:"json", 
	        data: {"flag":flag},
	        success : function(result) {  
	        	initType(result,flag);
	        },
	        error: function(str) {
	        	window.location.href = "404.html";
	        }
	    });
}
//填充类型
function initType(result,flag){
	var message = eval(result);
	var code = message.code;
	if(code == 1 || code == 2){
		var list = message.data;
		var ulTag = $(".category_cont");
		var temp = ulTag.children().eq(0);
		ulTag.empty();
		ulTag.append(temp);
		for(var i = 0;i<list.length;i++){
			var pro = list[i];
			if(pro.typeName != "常购品"){
				if(flag == 1){
					var liTag = $('<li></li>').append($('<a href="product_list.html?flowerClassify='+pro.typeName+'">'+pro.typeName+'</a>'));
				}else{
					var liTag = $('<li></li>').append($('<a href="product_list.html?flowerUse='+pro.typeName+'">'+pro.typeName+'</a>'));	
				}
				ulTag.append(liTag);
			}
		}
	}else{
		window.location.href = "404.html";
	}
}
