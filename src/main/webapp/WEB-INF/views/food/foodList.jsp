<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFood.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식품 리스트</title>
<!-- <style type="text/css">
</style> -->
<!-- 핸들바 템플릿 cdn추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<!-- 템플릿 소스 -->
<script id="tsource" type="text/x-handlebars-template">
	<table border="1">
		<tr>
			<th>품목제조번호</th>
			<th>업소명</th>
			<th>제품명</th>
            <th>유형</th>
		</tr>
		{{#each.}}
			<tr>
				<td>{{PRDLST_REPORT_NO}}</td>
				<td>{{BSSH_NM}}</td>
				<td><a href='${path}/food/detail/{{PRDLST_REPORT_NO}}'>{{PRDLST_NM}}</td>
				<td>{{PRDLST_DCNM}}</td>
			</tr>
		{{/each}}
	</table>
</script>

<script type="text/javascript">
	$(function(){
		$('#btnFList').on('click',function(e){
			e.preventDefault();
			
			const key=frmFList.key.value;
			const value=frmFList.value.value;
			console.log(key);
			console.log(value);
			
			$.ajax({
				type: 'get',
				url: '${path}/food/foodList',
				data: {key:key,value:value},
				dataType: 'json',
				success: function(result){
					console.log(result);
					
					var source=$('#tsource').html();
					var template= Handlebars.compile(source);
					$('#foodList').html(template(result));
				},
				error: function(result){
					alert('error');
					console.log(result);
				}
				
			});
			
		});
		
		$('#btnDB').on('click', function(e){
			e.preventDefault();
			
			location.href='${path}/food/db';
		});
		
		
		
	});
</script>

</head>
<body>
	<section style="overflow:auto; height:800px;">
		<h2>식품 첨가물</h2>
		<h5>✨데이터량이 많으므로 제품명으로 검색하는 것을 추천합니다!</h5>
		<br>
		<form action="" name="frmFList">
			<select name="key">
				<option value="PRDLST_NM">제품명</option>
				<option value="BSSH_NM">업소명</option>
			</select>
			<input type="text" name="value" size="50">
			<button id="btnFList">검색</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="btnDB">DB저장</button>
		</form>
		
		<div id="foodList"></div>
		<div id="paging"></div>
	</section>
</body>
</html>
