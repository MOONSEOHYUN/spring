<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includeMain.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<style type="text/css">
img{
	filter: brightness(40%);
}
</style>

<script type="text/javascript">
	//이메일 인증 후 창닫기
	//alert('${param.auth}');
	if ('${param.auth}'=='1'){
		window.close();
	}
</script>
</head>
<body>
	<img alt="main" src="${path }/resources/img/about-video.jpg" width=100%>
	<%@include file="footer.jsp" %>
</body>
</html>
