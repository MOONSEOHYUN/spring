<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./include/includeCompany.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아오시는 길</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=x8sq4qxs5j"></script>
<script type="text/javascript">
	$(function () {
		//회사주소 알기
		var x= 126.9455910;
		var y= 37.4696874;
		
		//맵 옵션
		var mapOptions = {
			    center: new naver.maps.LatLng(y, x),	// 키: 값 형태임 (전체는 맵형태)
			    zoom: 16												// 키: 값
			};
		//맵 생성
		var map = new naver.maps.Map('map', mapOptions);	//map 만들어서 위에 div에 넣음
			
		//맵 마커
		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(y, x),
		    map: map
		});
	});
</script>
</head>
<body>
	<section>
		<h2>회사 위치</h2>
		<br>
		<div id="map" style="width:80%;height:500px;"></div>
	</section>
</body>
</html>
