<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<!-- jstl로 변수 만들기 -->
<c:set var="path" value="${pageContext.request.contextPath }"/>

<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>

<script type="text/javascript">
	if('${param.msg}'!=''){
		alert('${param.msg}');
	}
	
	if('${msg}'!=''){
		alert('${msg}');
	}
	
	if('${resultMap.msg}'!=''){
		alert('${resultMap.msg}');
	}
</script>

<!-- 부트스트랩 -->
    <meta charset="UTF-8">
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="iE=edge,chrome=1">
    <title>식품</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700;800&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${path }/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/flaticon.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/barfiller.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${path }/resources/css/style.css" type="text/css">
    
    <!-- 로그인 -->    
<script type="text/javascript">
	$(function() {
 		if ('${sessionScope.id}' ==''){
			$('#aLogin').show();
			$('#aJoin').show();
			$('#aLogout').hide();
		}else{
			$('#aLogin').hide();
			$('#aJoin').hide();
			$('#aLogout').show();			
		}
		
		//게시판목록
		$('#aBoardList').on('click', function(e) {
			if ('${sessionScope.id}' ==''){
				alert('로그인을 먼저 하세요!');
				e.preventDefault(); //객체의 기본기능을 소멸
			}else{
				$(this).attr('href', '${path}/board/');
			}
		});

		//로그아웃
		$('#aLogout').on('click', function(e) {
			e.preventDefault();
			var result = confirm('로그아웃 하시겠습니까?');
			if (result){
				$(location).attr('href', '${path}/logout');
			}
		});
		
		//로그인
		$('#btnLogin').on('click', function(e) {
			//아이디/패스워드 체크
			e.preventDefault(); //객체의 기본기능을 소멸
			$('#loginForm').attr('action','${path}/login');
			$('#loginForm').attr('method','post');
			$('#loginForm').submit();
		});
	
	
		//로그인 취소
		$('#btnLoginCancel').on('click', function(e) {
			e.preventDefault(); //객체의 기본기능을 소멸
			$('#loginModal').modal('hide');
		});
		

	});

</script>
    
<style type="text/css">
section{
	position: relative;
	top: 150px;
}
</style>
</head>

<body class="ov-hid">
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header header--normal">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
                        <a href="./index.html"><img src="" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="header__nav">
                        <nav class="header__menu mobile-menu">
                            <ul>
                                <li><a href="${path }/main">HOME</a></li>
                                <li><a href="${path }/company">COMPANY</a></li>
                                <li><a href="#">FOOD</a>
                                    <ul class="dropdown">
                                        <li><a href="${path }/food/">식품첨가물</a></li>
                                        <li><a href="./listing-details.html">해외직구 차단 식품</a></li>
                                    </ul>
                                </li>
                                <li ><a href="${path }/board/" id="aBoardList">BOARD</a></li>
                                <li class="active"><a href="${path }/chatbot" id="aBoardList">CHATBOT</a></li>
                            </ul>
                        </nav>
                        <div class="header__menu__right">
                            <a href="${path }/member/add" class="primary-btn" id="aJoin"><i class="fa fa-plus"></i>SIGN IN</a>
                            <a href="#" data-toggle="modal" data-target="#loginModal" id="aLogin"><i class="fa fa-user" ></i></a>
                            <a href="#" id="aLogout"><i class="fa fa-user"></i></a>
                            <a href="${path}/member/modify" id="aMyinfo" target="myframe">&nbsp;&nbsp;&nbsp; ${sessionScope.id}</a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="mobile-menu-wrap"></div>
        </div>
    </header>
    <!-- Header Section End -->
    
        <!-- 로그인 Modal -->
  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
		<div class="modal-header">
	 		<h4 class="modal-title">로그인</h4>
	    </div>
        <div class="modal-body">	    
			<form id ="loginForm">
			  	<div class="input-group">
			    	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			    	<input id="id" type="text" class="form-control" name="id" placeholder="id">
			  	</div>
			  	<div class="input-group">
			    	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			    	<input id="password" type="password" class="form-control" name="passwd" placeholder="Password">
			  	</div>
			</form>
			
		</div>
 		<div class="modal-footer">
 			<a href=""  class="btn btn-primary" id="btnLogin">로그인</a>
 			<a href="" class="btn btn-success" id="btnLoginCancel">취소</a>
		</div>      
 		<div class="modal-footer">
 			<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG" alt="네이버"/></a>
		</div>			

      </div>
    </div>
  </div>
    
     <!-- Js Plugins -->
    <%-- <script src="${path }/resources/js/jquery-3.3.1.min.js"></script> --%>
    <script src="${path }/resources/js/bootstrap.min.js"></script>
    <script src="${path }/resources/js/jquery.nice-select.min.js"></script>
    <script src="${path }/resources/js/jquery-ui.min.js"></script>
    <script src="${path }/resources/js/jquery.nicescroll.min.js"></script>
    <script src="${path }/resources/js/jquery.barfiller.js"></script>
    <script src="${path }/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${path }/resources/js/jquery.slicknav.js"></script>
    <script src="${path }/resources/js/owl.carousel.min.js"></script>
    <script src="${path }/resources/js/main.js"></script>

</body>
</html>
