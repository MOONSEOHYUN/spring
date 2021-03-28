<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeMember.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
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

	$(function(){
		$('#btnModify').on('click',function(e){
			e.preventDefault();
			
			const oldpasswd=frmModify.oldpasswd.value;
			const email=frmModify.email.value;
			
			if(oldpasswd==''){
				alert('기존 비밀번호를 입력하세요.');		//프로젝트할 때는 회원수정 팝업창 띄워서 기존 비번 체크하기
				frmModify.oldpasswd.focus();
			}else if(email==''){
				alert('이메일을 입력하세요.');
				frmModify.oldpasswd.focus();
			}else{
				frmModify.submit();
			}
			
		});
		
		$('#btnAddr').on('click',function(e){
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
	<!-- 기존 비밀번호 반드시 입력: 기존 비번 불일치시 수정 불가
		 변경 비번은 선택: 기존 비번 그대로 저장 -->
	<section>
		<h2>수정</h2>
		<br>
		<form action="${path }/member/modify" name="frmModify" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" size="10" id="id" value="${mdto.id}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>기존 비밀번호</th>
					<td><input type="password" name="oldpasswd"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="passwd"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" value="${mdto.email}"></td>
				</tr>
				<tr>
					<th rowspan="3">주소</th>
					<td><input type="text" name="zip" size="5" value="${mdto.zip}"> <button id="btnAddr">주소찾기</button> </td>
				</tr>
				<tr>
					<td><input type="text" name="addr1" size="30" value="${mdto.addr1 }"></td>
				</tr>
				<tr>
					<td><input type="text" name="addr2" size="30" value="${mdto.addr2 }"></td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input type="text" name="filename" value="${mdto.filename }" readonly="readonly"></td>
					<td><input type="file" name="imgfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="btnModify">수정</button>
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
	
		</form>
	</section>
</body>
</html>
