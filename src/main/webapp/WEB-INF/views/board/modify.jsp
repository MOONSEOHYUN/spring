<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeBoard.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물수정</title>
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
	
	$('#btnModify').on('click',function(e){
		e.preventDefault();
		
		const subject=frmModify.subject.value;
		const content=frmModify.content.value;
		if(subject==''){
			alert('제목을 입력해 주세요.');
			frmModify.subject.focus();
			return;
		}else if(content==''){
			alert('내용을 입력해 주세요.');
			frmModify.content.focus();
			return;
		}
		
		//form 속성 변경
		$('#frmModify').attr('action','${path}/board/modify');
		$('#frmModify').attr('method','post');
		$('#frmModify').attr('enctype','multipart/form-data');
		$('#frmModify').submit(); 
	});
	
	//삭제
	$('#btnDelete').on('click',function(e){
		e.preventDefault();
		
		const bnum=$('#bnum').val();
		const result=confirm('삭제하겠습니까?');
		if(!result){
			return;
		}
		location.href='${path}/board/delete/'+bnum;
	});	
	
	
	
	
	
	
});
</script>

</head>
<body>
	<section>
		<h2>게시물 수정</h2>
		<br>
		<form id="frmModify" name="frmModify">
			번호 <input type="text" name="bnum" id="bnum" value="${resultMap.bdto.bnum }" readonly="readonly"><br>
			제목 <input type="text" name="subject" id="subject" value="${resultMap.bdto.subject }"> <br>
			내용 <textarea rows="5" cols="40" id="content" name="content">${resultMap.bdto.content }</textarea><br>
			<hr>
			파일<br>
			<c:forEach var="file" items="${resultMap.bflist }">
				<div>
					<input type="hidden" value="${file.fnum }" name="fnum"> ${file.filename }
					<input type="checkbox" value="${file.fnum }" name="fileDelete">삭제 <br>
				</div>
			</c:forEach>
			<button id="btnFileAdd">추가</button>
			<ol id="fileGroup">
				<li><input type="file" name="uploadfiles"><button class="btnFileRemove">삭제</button></li>
			</ol>
			<hr>
			<button id="btnModify">수정</button>
			<input type="reset" value="취소">
			<button id="btnDelete">삭제</button>
		</form>
	</section>
</body>
</html>
