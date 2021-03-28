<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeMember.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	//주소찾기
	function goPopup() {
		var pop = window.open("${path}/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frmAdd.addr1.value = roadAddrPart1;
		document.frmAdd.addr2.value = addrDetail;
		document.frmAdd.zip.value = zipNo;
	}

	
	$(function () {
		$('#btnIdCheck').on('click',function(){
			const id=frmAdd.id.value;
			console.log(id);
			if(id==''){
				alert('아이디를 입력해 주세요.');
				return;
			}
			
			$.ajax({
				type: 'post',
				url: '${path}/member/idCheck',
				data: {id:id},
				dataType: 'json',
				success: function(result) {
					alert(result.msg);
					if(result.yn=='y'){
						$('#idCheckYn').val('y');
					}else{
						$('#idCheckYn').val('n');
					}
				},
				error: function(result) {
					alert('error');
					console.log(result);
				}
				
			});
		});
		//가입버튼 클릭
		$('#btnAdd').on('click', function(e){
			e.preventDefault();		//기본 이벤트 제거 -> submit 일어나지 않음
			const id=frmAdd.id.value;
			console.log(id);
			const passwd=frmAdd.passwd.value;
			console.log(passwd);
			const email=frmAdd.email.value;
			const idCheckYn=frmAdd.idCheckYn.value;
			
			if(id==''){
				alert('아이디를 입력해 주세요.');
				frmAdd.id.focus();
			}else if(passwd==''){
				alert('비밀번호를 입력해 주세요.');
				frmAdd.passwd.focus();
			}else if(email==''){
				alert('이메일을 입력해 주세요.');
				frmAdd.email.focus();
			}else if(idCheckYn!='y'){
				alert('아이디 중복 체크해 주세요.');
				frmAdd.btnIdCheck.focus();
			}else{
				frmAdd.submit();
			}
		});
		
		//id change이벤트
		$('#id').change(function(){
			$('#idCheckYn').val('n');	//중복체크 해제
		});
		
		$('#btnAddr').on('click', function(e){
			e.preventDefault();
			goPopup();
		});
		
		$('#btnMain').on('click',function(e){
			e.preventDefault();
			$(location).attr('href','${path}/main');
		});

		
	});
</script>
</head>
<body>
	<section>
		<h2>회원가입</h2>
		<br>
		<form action="${path }/member/add" method="post" enctype="multipart/form-data" name="frmAdd">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" size="10" id="id">
						<input type="hidden" id="idCheckYn">	<!-- value가 y이면 체크완료 -->
						<input type="button" value="중복체크" id="btnIdCheck">		<!-- submit 기능 없음 -->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG" alt="네이버"/></a>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="passwd"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<th rowspan="3">주소</th>
					<td><input type="text" name="zip" size="5"> <button id="btnAddr">주소찾기</button> </td>
				</tr>
				<tr>
					<td><input type="text" name="addr1" size="30"></td>
				</tr>
				<tr>
					<td><input type="text" name="addr2" size="30"></td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input type="file" name="imgfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="btnAdd">가입</button>
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>
