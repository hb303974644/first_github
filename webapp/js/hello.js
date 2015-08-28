function hello(){
	//创建ajax(XMLHttpRequest)对象
	var ajax;
	try{
		//这种创建方式ie6/ie7不兼容
		ajax =  new XMLHttpRequest();
	}catch(e){
		//ie6/ie7兼容
		ajax=new ActiveXObject("Microsoft.XMLHTTP");
	}
	//ajax对象已经创建好了,不能直接发送消息
	//准备一个HTTP请求
	ajax.open("get", "/hello", true);
	
	//注册用于监听HTTP请求状态变化的函数
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status==200){
			var responseText = ajax.responseText;
			document.getElementById("msg").innerHTML = responseText;
		}else if(ajax.readyState==4 ){
			document.getElementById("msg").innerHTML="出错!";
		}
	};
	//发送请求
	ajax.send();
	//
}