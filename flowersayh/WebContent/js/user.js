$(document).ready(function () { 
	$.ajax({  
		type : "POST",  
		url : baseUrl + "/user/getName", 
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
	 			$(".userIcon img").attr("src",message.data.userPicture);
	 			$(".userInfor h2").html(message.data.username);
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
});