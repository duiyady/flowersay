$(".lastBtn").click(function(){
	$.ajax({  
		type : "POST",  
		url : baseUrl + "/user/logout", 
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
	 			window.location.href = "index.html";
	 		}else{
	 			window.location.href = "index.html";
	 		}
	   	},
	   	error: function(str) {
	   		window.location.href = "404.html";
	  	}
	});
});

$(".userForm input[type='button']").click(function(){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var rPassword = $("#rPassword").val();
	var flag = true;
	if(oldPassword == null || oldPassword == ""
		|| newPassword == null || newPassword == ""
		|| rPassword == null || rPassword == ""){
		flag = false;
	}
	if(flag == true){
		if(newPassword == rPassword){
			$.ajax({  
				type : "POST",  
				url : baseUrl + "/user/updatePassword", 
				xhrFields: {
					withCredentials: true
			   	},
			    crossDomain: true,
			 	data: {"oldPassword":oldPassword,"newPassword":newPassword},
				dataType:"json", 
			 	success : function(result) {  
			 		var message = eval(result);
			 		var code = message.code;
			 		if(code == 1){
			 			window.location.href = "javascript:history.go(-1);";
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
		
		}else{
			alert("两次输入不一样");
		}
	}else{
		alert("不能为空");
	}
});	