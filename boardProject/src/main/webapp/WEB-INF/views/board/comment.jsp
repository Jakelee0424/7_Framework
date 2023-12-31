<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<div id="commentArea">
    <!-- 댓글 목록 -->
    <div class="comment-list-area">
        
       
        <ul id="commentList">

		<c:forEach items="${board.commentList}" var="comment">
	            <!-- 부모/자식 댓글 -->
				<input type="hidden" value="${comment.commentNo}"></input>
	            <li class="comment-row <c:if test='${comment.parentNo != 0}'>child-comment</c:if> ">
	
	                <p class="comment-writer">
					

		 				 <c:choose>
	                    	
	                    	<c:when test="${empty comment.profileImage}" >
		                    	<img src="/resources/images/user.png">
	                    	</c:when>
							
							<c:otherwise>
	                   		 	<img src="${comment.profileImage}">
							</c:otherwise>
	                    
	                    </c:choose>
	
	                    <span>${comment.memberNickname}</span>
	                    
	                    <span class="comment-date">${comment.commentCreateDate}</span>
	                </p>
	                
	                <p class="comment-content">${comment.commentContent}</p>
	
	
	                <div class="comment-btn-area">
	                    <button onclick="showInsertComment(`${comment.commentNo}`, this)">답글</button>   
	                   	
	                   	<c:if test="${loginMember.memberNo == comment.memberNo}">
	                    	<button onclick="showUpdateComment('${comment.commentNo}', this)">수정</button>     
	                    	<button onclick="deleteComment('${comment.commentNo}')">삭제</button>
	                	</c:if>
	                	
	                </div>
	            </li>
	            
		</c:forEach>
	
	           

        </ul>
    </div>
    

    <!-- 댓글 작성 부분 -->
    <div class="comment-write-area">
        <textarea id="commentContent"></textarea>
        <button id="addComment">
            댓글<br>
            등록
        </button>
 
    </div>

</div>
