$("#login").click(function(){
	var phone = $("#phone").val();
	var password=$("#password").val();
	var flag = true;
	if (phone == null || phone == "") {
		flag = false;
	}
	if (password == null || password == "") {
		flag = false;
	}
	if (flag == true) {
		 $.ajax({
		        type : "POST",  
		        url : baseUrl + "/user/login",  
		        xhrFields: {
		            withCredentials: true
		        },
		        crossDomain: true,
		        dataType:"json", 
		        data: {"userphone":phone,"password":password},
		        success : function(result) {  
		        	var message = eval(result);
		        	if(message.code == 1){
		        		window.location.href = "index.html";
		        	}else{
		        		alert(message.msg);
		        	}
		        },
		        error: function(str) {
		        	window.location.href = "404.html";
                }
		    });
	}else{
		alert("请正确输入");
	}	
});

