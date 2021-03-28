<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/includeChatbot.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챗봇</title>
<!--     <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet"> -->
    <link href="${path}/resources/css/chatstyle.css" rel="stylesheet">
<script>
	//메시지 보내는 함수
	var sendmsg = function(){
		var msg = $('#txtMsg').val();
		var date = getToday();
		var appendMsg = '<div class="outgoing_msg"> ' + 
	    				'<div class="sent_msg"> ' +
	    				'<p>' + msg + '</p> ' +
	    				'<span class="time_date"> '+ date +'</span> </div> ' +
						'</div> ';    
		
		$('#divHistory').append(appendMsg);
		$('#divHistory').scrollTop($('#divHistory').height());
		
		$.ajax({
			type:'post',
			contentType:'application/json',
			url : '${path}/chat/sendMsg',
			data : JSON.stringify({msg:msg}), //json문자열 표기법으로 변환
			dataType : 'text',  //결과값의 타입
			success : function(result){
				console.log(result);
				receiveMsgAppend(result);
				
			},
			error:function(result){
				alert("error");
				console.log(result);
			}
		});
		
		//받은메시지 추가
		function receiveMsgAppend(msg){
			var date = getToday();
			var appendMsg = '<div class="incoming_msg"> ' +
		                    '<div class="incoming_msg_img"> <img src="${path}/resources/img/chatCat.png" width="30" alt="cat"> </div> ' +
		                    '<div class="received_msg"> ' +
		                    '<div class="received_withd_msg"> ' +
		                    '    <p>' + msg + '</p> ' +
		                    '    <span class="time_date"> '+ date +'</span></div> ' +
		                    '</div> ' +
		                	'</div>';
		                
			$('#divHistory').append(appendMsg);
			$('#divHistory').scrollTop($('#divHistory').height());    				
		}
		
		//시스템 날짜 구하기
		function getToday(){
			var date = new Date();
			return date.getFullYear() + "-" + 
					("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + (date.getDate() + 1)).slice(-2) +  ' | ' +
					date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
		}
		
	}

	$(function(){
		//버튼 클릭했을때
		$('#btnMsg').on('click',sendmsg );
		//메시지 변경됐을때
		$('.write_msg').on('change',sendmsg );
	
	});
    
</script>
    
</head>
<body>
	<section >
	   <div class="container">
	        <h3 class=" text-center">Messaging</h3>
	        <div class="messaging">
	            <div class="mesgs">
	                <div id = "divHistory" class="msg_history" style="overflow:auto; height:500px;">
	                </div>
	                <div class="type_msg">
	                <div class="input_msg_write">
	                    <input id="txtMsg" type="text" class="write_msg" placeholder="Type a message" />
	                    <button id="btnMsg" class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
	                </div>
	            	</div>
	       		</div>
			</div>
	  	</div> 
	</section> 
</body>
</html>
