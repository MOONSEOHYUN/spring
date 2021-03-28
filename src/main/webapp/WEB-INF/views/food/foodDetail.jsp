<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFood.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식품 원재료</title>
<style type="text/css">
#mtrlDiv{
	width: 500px;
	height: 200px;
	border: 1px solid black;
}
#arrSpan{
	color: red;
}
</style>
</head>
<body>
 	<section>
		품목제조번호 <input type="text" value='${fmap.get("PRDLST_REPORT_NO") }'><br>
		업소명 <input type="text" value='${fmap.get("BSSH_NM") }' size="30"><br>
		제품명 <input type="text" value='${fmap.get("PRDLST_NM") }' size="30"><br>
		유형 <input type="text" value='${fmap.get("PRDLST_DCNM") }'><br>
		원재료&nbsp;&nbsp;&nbsp;&nbsp; 식품첨가물 개수<span id="arrSpan">${cnt }</span>개<br>
	  	<div id="mtrlDiv" style="overflow:auto; height:200px;">
			<c:forEach var="food" items="${flist}" varStatus="i">
				<c:choose>
					<c:when test="${i.first }">
						${food }
					</c:when>
					<c:otherwise>
						,${food }
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:forEach var="fa" items="${falist }" >
<%-- 				<c:if test="${!i.last }">
					<span id="arrSpan">${fa }</span>
				</c:if>  --%>
				,<span id="arrSpan">${fa }</span>
			</c:forEach>
		</div>	
	</section>
	
	


</body>
</html>
