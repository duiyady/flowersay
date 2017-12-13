$(document).ready(function () {  
	 $(".searchBtn").click(function(){
			var message = $(".searcharea").val();
			if(message!=null &&message!=""){
				window.location.href = "product_list.html?message=" + message;
			}
		});
});