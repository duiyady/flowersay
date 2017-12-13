$("#fasong").click(function(){
	var userphone = $("#userphone").val();
	if(userphone == null || userphone == ""){
		alert("请输入电话号码");
	}else{
		$.ajax({  
	        type : "POST",  
	        url : baseUrl + "/user/getCode", 
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
	        dataType:"json", 
	        data: {"userphone":userphone,"codeType":1},
	        success : function(result) {  
	        	var message = eval(result);
	        	if(message.code == 1){
	        		alert("发送验证码成功");
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
$("#xiugai").click(function(){
	var userphone = $("#userphone").val();
	var password = $("#password").val();
	var rpassword = $("#rpassword").val();
	var code = $("#code").val();
	if(password != rpassword){
		alert("两次密码不一样");
	}else{
		if(userphone == null || userphone == ""
			|| code == null || code == ""
			|| password == null || password == ""
			|| rpassword == null || rpassword == ""){
			alert("不能留空哦");
		}else{
			$.ajax({  
		        type : "POST",  
		        url : baseUrl + "/user/findPwd", 
		        xhrFields: {
		            withCredentials: true
		        },
		        crossDomain: true,
		        dataType:"json", 
		        data: {"userphone":userphone,
		        	"password":password,
		        	"code":code},
		        success : function(result) {  
		        	var message = eval(result);
		        	if(message.code == 1){
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
	}
});