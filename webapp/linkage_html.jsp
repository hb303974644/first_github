<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>linkage-html</title>
<script type="text/javascript" src="/js/createAjax.js"></script>
<script type="text/javascript">
	//页面加载完成之后,使用Ajax去加载省份下拉列表里面的内容
	window.onload = function(){
		var ajax =createAjax();
		var provice = document.getElementById("provice");
		ajax.open("get","/linkage_html?cmd=listProvices",true);
		ajax.onreadystatechange=function(){
			if(ajax.readyState == 4 && ajax.status == 200){
				var responseText = ajax.responseText;
				provice.innerHTML += responseText;
			}
		};
		ajax.send();
	};
	function selectCities(){
		var provice = document.getElementById("provice");
		var city = document.getElementById("city");
		city.innerHTML ="<option>--请选择城市--</option>";
		var pid = provice.value;
		if(pid != -1){
			var ajax = createAjax();
			ajax.open("get","/linkage_html?cmd=listCitiesByProviceId&proviceId="+pid,true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState==4 && ajax.status==200){
					var responseText = ajax.responseText;
					city.innerHTML += responseText;
				}
			};
			ajax.send();
		}
	}

</script>
</head>
<body>
	省份:<select id="provice" onchange="selectCities();">
			<option value="-1">--请选择省份--</option>
		</select>
	城市:<select id="city">
			<option>--请选择城市--</option>
		</select>
</body>
</html>