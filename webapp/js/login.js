function createAjax(){
	var ajax;
	try{
		ajax = new XMLHttpRequest();
	}catch(e){
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return ajax;
}

function login(){
	var ajax = new createAjax();
	var name = document.getElementById("name").value;
	var password= document.getElementById("password").value;
	
	ajax.open("post", "/login", true);
	//注册用于监听HTTP请求状态变化的函数
	
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status==200){
			var responseText = ajax.responseText;
			if("true"==responseText){
				//界面跳转
				window.location.href="/success.jsp";
			}else{
				document.getElementById("msg").innerHTML="账号或密码错误!";
			}
		}
	};
	//发送请求
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("name="+name+"&password="+password);
}