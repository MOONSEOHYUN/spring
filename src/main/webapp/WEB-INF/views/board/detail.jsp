<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeBoard.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
<!-- 핸들바 템플릿 cdn추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
 <!-- 탬플릿 소스 :게시판 리스트-->
 <script id="template_source" type="text/x-handlebars-template">
    {{#each .}}
		{{level relevel}}
		<div>
			<input type="hidden" class="restep" value="{{restep}}">
			<input type="hidden" class="relevel" value="{{relevel}}">
			<input type="hidden" class="rnum" value="{{rnum}}">
			<input type="text" class="id" value="{{id}}">  <br>
			<textarea rows="3" cols="20" class="content">{{content}}</textarea><br>
			<button class="btnReplyUpdate">수정</button>
			<button class="btnReplyDelete">삭제</button>
			<button class="btnReply">댓글</button> |
			<button class="btnRLike">좋아요</button> <span class="RLikecnt">${resultMap.rdto.likecnt}</span> 
			<button class="btnRDislike">싫어요</button> <span class="RDislikecnt">${resultMap.rdto.dislikecnt}</span><br>
			<div class='replyAdd'></div>
		</div>

    {{/each}}

</script>

<!-- 탬플릿 소스 : 댓글 추가 -->
 <script id="template_source_reply" type="text/x-handlebars-template">
	<div>
		<input type="hidden" id="restep" value="{{restep}}">
		<input type="hidden" id="relevel" value="{{relevel}}"><br>
		<textarea rows="5" cols="20" id="replycontent"></textarea><br>
		<button class="btnReplyAdd">추가</button>
	</div>
</script>

<script type="text/javascript">
	$(function() {
		//좋아요 버튼
		$('#btnLike').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			
			$.ajax({
				type:'get',
				url:'${path}/board/likecnt/'+bnum,
				dataType:'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		//싫어요 버튼
		$('#btnDislike').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			
			$.ajax({
				type:'get',
				url:'${path}/board/dislikecnt/'+bnum,
				dataType:'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		
		//수정버튼을 눌럿을때
		$('#btnModify').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			//userid체크
			const session_id = '${sessionScope.id}';
			const id = $('#id').val();
			//alert(userid);
			if (id != session_id){
				alert('수정권한이 없습니다');
				return ;				
			}
			
			location.href="${path}/board/modify/"+bnum;	
		});
		
		/* -------------------댓글처리------------------------- */
		
		//댓글추가 버튼을 눌렀을때
		$('body').on('click', '.btnReplyAdd', function() {
			//id체크
			const session_id = '${sessionScope.id}';
			if (session_id==''){
				alert('로그인 후 추가하세요');
				return ;				
			}			
			
			const bnum = $('#bnum').val();
			const replycontent = $(this).parent().find('#replycontent').val(); 
			const restep = $(this).parent().find('#restep').val();
			const relevel = $(this).parent().find('#relevel').val();
			
			if (replycontent==''){
				alert('댓글 내용을 입력해주세요');
				$('#replycontent').focus();
				return;
			}
			
			$.ajax({
				type:'post',
				contentType: "application/json",
				url: '${path}/reply/',
				data : JSON.stringify({bnum:bnum,content:replycontent,restep:restep,relevel:relevel}),//json문자열
				dataType: 'text',
				success: function(result) {
					//원본 댓글추가 html삭제
					$('#replyAdd').html('');
					//댓글의 댓글 추가 삭제
					console.log($(this).parent().parent().html());
				},
				error: function(result) {
					alert('error');
				}
				
			});
			
		});
		
		//원본의 댓글 버튼을 눌렀을때
		$('#btnReply').on('click',function() {
						
			const data = {'restep':0, 'relevel':0}; 
			
			//탬플릿을 이용하여 화면에 출력
			var source = $('#template_source_reply').html();
            var template = Handlebars.compile(source);
            $('#replyAdd').html(template(data));				
			
		});

		//댓글의 댓글 버튼을 눌렀을때
		$('#replyList').on('click', '.btnReply', function() {
			const restep = $(this).parent().find('.restep').val();
			const relevel = $(this).parent().find('.relevel').val();
			const data = {restep, relevel}; 
			
			//탬플릿을 이용하여 화면에 출력
			var source = $('#template_source_reply').html();
            var template = Handlebars.compile(source);
            $(this).parent().find('.replyAdd').html(template(data));
			
		});
		
		//댓글의 수정 버튼을 눌렀을때
		$('#replyList').on('click', '.btnReplyUpdate', function() {
			const rnum = $(this).parent().find('.rnum').val();
			const id = $(this).parent().find('.id').val();
			const content = $(this).parent().find('.content').val();
			console.log(rnum);
			console.log(id);
			console.log(content);
			
			//userid체크
			const session_id = '${sessionScope.id}';
			if (id != session_id){
				alert('수정권한이 없습니다');
				return ;				
			}
			
			$.ajax({
				type:'put', //수정
				contentType: "application/json",
				url: '${path}/reply/',
				data : JSON.stringify({rnum:rnum,id:id,content:content}),//json문자열
				dataType: 'text',
				success: function(result) {
					alert(result);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		
		//댓글의 삭제 버튼을 눌렀을때
		$('#replyList').on('click', '.btnReplyDelete', function() {
			const rnum = $(this).parent().find('.rnum').val();
			const id = $(this).parent().find('.id').val();
			console.log(rnum);
			console.log(id);
			
			//id체크
			const session_id = '${sessionScope.id}';
			if (id != session_id){
				alert('삭제권한이 없습니다');
				return ;				
			}
			
			$.ajax({
				type:'delete', //삭제
				url: '${path}/reply/'+rnum,
				dataType: 'text',
				success: function(result) {
					alert(result);
					replyList() ; //댓글 리스트 
				},
				error: function(result) {
					alert('error');
				}
			});
		});
		
		//댓글 좋아요
		$('#replyList').on('click','.btnRLike',function(){
			const rnum=$(this).parent().find('.rnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/reply/ReplyLikecnt/'+rnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('.RLikecnt').html(result.likecnt);
					$('.RDislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		//댓글 싫어요
		$('#replyList').on('click','.btnRDislike',function(){
			const rnum=$(this).parent().find('.rnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/reply/ReplyDislikecnt/'+rnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('.RLikecnt').html(result.likecnt);
					$('.RDislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		//댓글 리스트 조회
		function replyList() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			$.ajax({
				type:'get',
				url: '${path}/reply/'+bnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					console.log(result);
					//핸들바 헬퍼 작성
					Handlebars.registerHelper('level', function(relevel) {
						var result = '';
						for(i=0; i<relevel; i++){
							result += '@';
						}
						return result;
					});
					
					//탬플릿을 이용하여 화면에 출력
					var source = $('#template_source').html();
		            var template = Handlebars.compile(source);
		            $('#replyList').html(template(result));	
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		}		
		
		replyList() ; //댓글 리스트 
		
	});
	
</script>
</head>
<body>
	<section style="overflow:auto; height:750px;">
		<h2>상세조회</h2>
		<br>
		번호 <input type="text" id="bnum" value="${resultMap.bdto.bnum }" readonly="readonly"><br>
		아이디 <input type="text" id="id" readonly="readonly" value="${resultMap.bdto.id }"> <br>
		제목 ${resultMap.bdto.subject }<br>
		내용 <textarea rows="5" cols="40" readonly="readonly">${resultMap.bdto.content }</textarea><br>
		<hr>
		파일<c:forEach var="file" items="${resultMap.bflist }">
			${file.filename }<br>
		</c:forEach>
		<hr>
		조회수 ${resultMap.bdto.readcnt }
		좋아요 <span id="likecnt">${resultMap.bdto.likecnt}</span> 
		싫어요 <span id="dislikecnt">${resultMap.bdto.dislikecnt}</span><br>
		<button id="btnLike">좋아요</button>
		<button id="btnDislike">싫어요</button>
		<hr>
		등록일자 ${resultMap.bdto.regdate } |
		수정일자 ${resultMap.bdto.modifydate}<br>
		<button id="btnModify">수정</button>
		<button id="btnReply">댓글</button>
		<hr>
		<!-- 댓글 추가 -->
		<div id="replyAdd"></div>
		<!-- 댓글 리스트 -->
		<div id="replyList">
			
		</div>
	</section>



<%-- <!-- 템플릿 소스 -->
<script id="tsource" type="text/x-handlebars-template">
	{{#each.}}
		{{level relevel}}
		<div>
			<input type="hidden" class="restep" value={{restep}}>
			<input type="hidden" class="relevel" value={{relevel}}>
			<input type="hidden" class="rnum" value="{{rnum}}">
			<input type="text" class="id" value="{{id}}">
			좋아요 <span class="RLikecnt">${resultMap.rdto.likecnt}</span> 
			싫어요 <span class="RDislikecnt">${resultMap.rdto.dislikecnt}</span><br>
			<textarea rows="2" cols="60" class="content">{{content}}</textarea><br>
			<button class="btnReplyUpdate">수정</button>
			<button class="btnReplyDelete">삭제</button>
			<button class="btnReply">댓글</button> | 
			<button class="btnRLike">좋아요</button>
			<button class="btnRDislike">싫어요</button>
			<div class='replyAdd'></div>
		</div>
	{{/each}}

</script>

<!-- 탬플릿 소스 : 댓글 추가 -->
 <script id="template_source_reply" type="text/x-handlebars-template">
	<div>
		<input type="hidden" id="restep" value="{{restep}}">
		<input type="hidden" id="relevel" value="{{relevel}}"><br>
		<textarea rows="5" cols="20" id="replycontent"></textarea><br>
		<button class="btnReplyAdd">추가</button>
	</div>
</script>

<script type="text/javascript">
	$(function(){
		$('#btnLike').on('click',function(){
			const bnum=$('#bnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/board/likecnt/'+bnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		$('#btnDislike').on('click',function(){
			const bnum=$('#bnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/board/dislikecnt/'+bnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		$('#btnModify').on('click',function(){
			const bnum=$('#bnum').val();
			//id 체크
			const sessionId='${sessionScope.id}';
			const id=$('#userId').val();
			if(sessionId!=id){
				alert('수정권한이 없습니다.');
				return;
			}
			location.href='${path}/board/modify/'+bnum;
			
		});
		
		$('#btnList').on('click',function(){
			location.href='${path}/board/';
		});
		
		//댓글저장
		//$('#btnReplyAdd').on('click',function(){
		$('body').on('click', '.btnReplyAdd', function() {
			const bnum=$('#bnum').val();
//			const rcontent=$('#rcontent').val();
			const replycontent=$(this).parent().find('#replycontent').val();
			const restep=$('#restep').val();
			const relevel=$('#relevel').val();
			
			//if(rcontent==''){
			if(replycontent==''){
				alert('댓글 내용을 입력해 주세요.');
				$('#rcontent').focus();
				return;
			}
			$.ajax({
				type: 'post',
				contentType: "application/json",
				url: '${path}/reply/',
				data: JSON.stringify({bnum:bnum,content:replycontent,restep:restep,relevel:relevel}),
				dataType: 'text',
				success: function(result){
					replyList();
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
			
		});
		
		//본문의 댓글
		$('#btnReply').on('click',function(){
			/* $('#restep').val('0');
			$('#relevel').val('0'); */
			
			const data = {'restep':0, 'relevel':0}; 
			
			//탬플릿을 이용하여 화면에 출력
			var source = $('#template_source_reply').html();
            var template = Handlebars.compile(source);
            $('#replyAdd').html(template(data));
			
		});
		
		//댓글의 댓글 버튼
		$('#replyList').on('click','.btnReply',function(){
			//댓글 버튼 누른 댓글의 restep과 relevel을 가져옴 
			const restep=$(this).parent().find('.restep').val();
			const relevel=$(this).parent().find('.relevel').val();
			
/*  			$('#restep').val(restep);
			$('#relevel').val(relevel);  */
			
			const data = {restep, relevel}; 
			
 			//탬플릿을 이용하여 화면에 출력
			var source = $('#template_source_reply').html();
            var template = Handlebars.compile(source);
            $(this).parent().find('.replyAdd').html(template(data)); 
		});
		
		//댓글 수정
		$('#replyList').on('click','.btnReplyUpdate',function(){
			const rnum=$(this).parent().find('.rnum').val();
			const id=$(this).parent().find('.id').val();
			const content=$(this).parent().find('.content').val();
			//id 체크
			const sessionId='${sessionScope.id}';
			if(sessionId!=id){
				alert('수정권한이 없습니다.');
				return;
			}
			console.log(rnum);
			console.log(id);
			console.log(content);
			
			$.ajax({
				type: 'put',
				contentType: "application/json",
				url: '${path}/reply/',
				data: JSON.stringify({rnum:rnum,id:id,content:content}),
				dataType: 'text',
				success: function(result){
					alert(result);
					console.log(result); 
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		//댓글 삭제
		$('#replyList').on('click','.btnReplyDelete',function(){
			const rnum=$(this).parent().find('.rnum').val();
			const id=$(this).parent().find('.id').val();
			//id 체크
			const sessionId='${sessionScope.id}';
			if(sessionId!=id){
				alert('수정권한이 없습니다.');
				return;
			}
			
			console.log(rnum);
			console.log(id);
			
			$.ajax({
				type: 'delete',
				url: '${path}/reply/'+rnum,
				dataType: 'text',
				success: function(result){
					alert(result);
					console.log(result); 
					replyList();
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		//댓글 좋아요
		$('#replyList').on('click','.btnRLike',function(){
			const rnum=$(this).parent().find('.rnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/reply/ReplyLikecnt/'+rnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('.RLikecnt').html(result.likecnt);
					$('.RDislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		//댓글 싫어요
		$('#replyList').on('click','.btnRDislike',function(){
			const rnum=$(this).parent().find('.rnum').val();

			$.ajax({
				type: 'get',
				url: '${path}/reply/ReplyDislikecnt/'+rnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					$('.RLikecnt').html(result.likecnt);
					$('.RDislikecnt').html(result.dislikecnt);
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
			
		});
		
		
		//댓글 리스트 조회 함수
		function replyList(){
			const bnum=$('#bnum').val();
			
			$.ajax({
				type: 'get',
				url: '${path}/reply/'+bnum,
				dataType: 'json',
				success: function(result){
					console.log(result); 
					//핸들바 헬퍼 작성
					Handlebars.registerHelper('level', function(relevel) {
						var result = '';
						for(i=0; i<relevel; i++){
							result += '@';
						}
						return result;
					});
					var source=$('#tsource').html();
					var template = Handlebars.compile(source);
					$('#replyList').html(template(result));
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
			});
		}
		
		//댓글 리스트
		replyList();
		
		
	});
</script>
</head>
<body>
	<section>
		<h2>상세조회</h2>
		<br>
		번호 <input type="text" id="bnum" value="${resultMap.bdto.bnum }" readonly="readonly"><br>
		아이디 <input type="text" id="userId" readonly="readonly" value="${resultMap.bdto.id }"> <br>
		제목 ${resultMap.bdto.subject }<br>
		내용 <textarea rows="5" cols="40" readonly="readonly">${resultMap.bdto.content }</textarea><br>
		<hr>
		파일<c:forEach var="file" items="${resultMap.bflist }">
			${file.filename }<br>
		</c:forEach>
		<hr>
		조회수 ${resultMap.bdto.readcount }
		좋아요 <span id="likecnt">${resultMap.bdto.likecnt}</span> 
		싫어요 <span id="dislikecnt">${resultMap.bdto.dislikecnt}</span><br>
		<button id="btnLike">좋아요</button>
		<button id="btnDislike">싫어요</button>
		<hr>
		등록일자 ${resultMap.bdto.regdate } |
		수정일자 ${resultMap.bdto.modifydate}<br>
		<button id="btnModify">수정</button>
		<button id="btnList">목록</button>
		<button id="btnReply">댓글</button>
		
		<hr>
		<!-- 댓글 -->
		<input type="hidden" id="restep" value="0">
		<input type="hidden" id="relevel" value="0">
		<textarea rows="2" cols="55" id="rcontent"></textarea>
		<button id="btnReplyAdd">댓글 등록</button>
		<hr>
		<!-- 댓글 추가 -->
		<div id="replyAdd"></div>
		<!-- 댓글 리스트 -->
		<div id="replyList">
			
		</div>
	</section> --%>
</body>
</html>
