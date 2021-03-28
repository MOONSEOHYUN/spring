<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeBoard.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물추가</title>
<script type="text/javascript">
	$(function(){
		$('#btnFileAdd').on('click',function(e){
			e.preventDefault();
			var appendHtml='<li><input type="file" name="uploadfiles">'+
							'<button class="btnFileRemove">삭제</button></li>';
			$('#fileGroup').append(appendHtml);
		});
		
		//파일 삭제
		$('#fileGroup').on('click','button',function(e){	//fileGroup에 있는 button을 클릭하면 function 실행
			e.preventDefault();
			//this: 객체 자신 (사용자가 선택한 것)
			$(this).parent().remove();	//this의 부모 삭제 (this의 부모는 li)
		});
		
		//등록
		$('#btnAdd').on('click',function(e){
			e.preventDefault();
			
			const subject=frmAdd.subject.value;
			const content=frmAdd.content.value;
			if(subject==''){
				alert('제목을 입력해 주세요.');
				frmAdd.subject.focus();
				return;
			}else if(content==''){
				alert('내용을 입력해 주세요.');
				frmAdd.content.focus();
				return;
			}
			//form 속성 변경
			$('#frmAdd').attr('action','${path}/board/add');
			$('#frmAdd').attr('method','post');
			$('#frmAdd').attr('enctype','multipart/form-data');
			$('#frmAdd').submit();
		});
		
		
	});
</script>
</head>
<body>
	<section>
		<h2>게시물추가</h2>
		<br>
		<form id="frmAdd" name="frmAdd">
			제목 <input type="text" name="subject" id="subject"><br>
			내용 <textarea rows="5" cols="40" name="content" id="content"></textarea><br>
			파일 <button id="btnFileAdd">추가</button> 
			<ol id="fileGroup">
				<li><input type="file" name="uploadfiles"><button class="btnFileRemove">삭제</button></li>
			</ol> 
			
			<button id="btnAdd">등록</button>
			<input type="reset" value="취소">		
		</form>
	</section>
</body>
</html>
