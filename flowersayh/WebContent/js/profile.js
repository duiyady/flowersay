$(document).ready(function () {  
	$.ajax({  
        type : "POST",  
        url : baseUrl + "/user/getName",  
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType:"json", 
        data: {},
        success : function(result) {  
        	var message = eval(result);
        	if(message.code == 1){
        		var aTag = $(".isNext");
        		aTag.eq(0).children("span").eq(1).html(message.data.username);
        		aTag.eq(0).attr("href","change_name.html?username="+message.data.username); 
        		aTag.eq(1).children("span").eq(1).html(message.data.userphone);
        	}else if(messgae.code == -1){
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