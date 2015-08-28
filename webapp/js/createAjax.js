function createAjax(){
	var ajax;
	try{
		ajax = new XMLHttpRequest();
	}catch(e){
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return ajax;
}