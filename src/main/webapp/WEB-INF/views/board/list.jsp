<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeBoard.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체조회</title>
<!-- 핸들바 템플릿 cdn추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<!-- 템플릿 소스 -->
<script id="tsource" type="text/x-handlebars-template">
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>좋아요</th>
            <th>싫어요</th>
            <th>등록일자</th>
		</tr>
		{{#each.}}
			<tr>
				<td>{{bnum}}</td>
				<td>{{id}}</td>
				<td><a href='${path}/board/detail/{{bnum}}'>{{subject}}</td>
				<td>{{readcount}}</td>
				<td>{{likecnt}}</td>
				<td>{{dislikecnt}}</td>
				<td>{{regdate}}</td>
			</tr>
		{{/each}}
	</table>
</script>

<script type="text/javascript">
	$(function () {
		//조회, 하이퍼링크 클릭했을 때 처리할 함수
		function pageList(curPage) {
			const findKey=frmList.findKey.value;
			const findValue=frmList.findValue.value;
			console.log(findKey);
			console.log(findValue);
			
			//ajax 비동기방식
			$.ajax({
				type: 'get',
				url: '${path}/board/list/',
				data: {findKey:findKey,findValue:findValue,curPage:curPage},
				dataType: 'json',
				success: function(result) {
					//alert('success');
					console.log(result);
					var source=$('#tsource').html();
					var template = Handlebars.compile(source);
					$('#boardList').html(template(result.blist));
					
					//페이징처리
					$('#paging').html(''); //2번 조회해도 중복으로 안뜨게 (append라 한번까진 괜찮은데 2번이상 조회하면 또 중복으로 추가가 됨)
					const startPage=result.pdto.startPage;
					const endPage=result.pdto.endPage;
					const totPage=result.pdto.totPage;

					if(startPage!=1){
						$('#paging').append('<a href="'+(startPage-1)+'" class="aPage">prev</a> ');
					}
					for(var i=startPage; i<=endPage; i++){
						$('#paging').append('<a href="'+i+'" class="aPage">'+i+'</a> ');
					}
					if(endPage<totPage){
						$('#paging').append('<a href="'+(endPage+1)+'" class="aPage">next</a> ');
					}
					
				},
				error: function(result) {
					alert('error');
					console.log(result);
				},
			});
		}
		
		$('#btnList').on('click',function(e){
			e.preventDefault();
			
			pageList(1);
		});
		
		//페이지 클릭했을 때 (aPage)
		$('#paging').on('click','.aPage',function(e){
			e.preventDefault();
			const pageNo=$(this).attr('href');
			pageList(pageNo);
		});
		
		//게시글 추가 폼으로 이동
		$('#btnAdd').on('click',function(e){
			e.preventDefault();
			location.href='${path}/board/add';
			
		});
		
		//$('#btnList').trigger('click');
		pageList();	//보던 게시판 페이지 초기화되지 않게
		
	});

</script>
</head>
<body>
	<section>
		<h2>전체조회</h2>
		<br>
		<form action="" name="frmList">
			<select name="findKey">
				<option value="id">아이디</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="subcon">제목+내용</option>
			</select>
			<input type="text" name="findValue"> 
			<button id="btnList">조회</button>
			<button id="btnAdd">게시글 추가</button>
		</form>
		<div id="boardList"></div>
		<div id="paging"></div>
	</section>
</body>
</html>
