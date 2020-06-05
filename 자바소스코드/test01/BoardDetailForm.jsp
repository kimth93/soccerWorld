<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
 <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
        }
    
        #detailBoard{
            text-align :center;
        }
        
        #title{
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
        }
    </style>
    
    <script type="text/javascript">
        function changeView(value)
        {
            if(value == 0)
            	location.href="BoardListAction.bo?page=${pageNum}";
            else if(value == 1){
                location.href='BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}';
            }
                
        }
        
       	function doAction(value){
       		if(value==0){
       			//location.href="BoardUpdateForm.bo?num=${board.board_num}&page=${pageNum}";
       			location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
       		}
       		else if(value == 1){
       			location.href="BoardDeleteAction.bo?num=${board.board_num}";
       		}	//삭제
       	}
    </script>

</head>
<body>
<c:set var="login" value="${sessionScope.login}"/>

<div id="wrap">
    <br><br>
    <div id="board">
        <table id="detailBoard" width="800" border="3" bordercolor="lightgray">
        
            <tr>
                <td id="title">작성일</td>
                <td>${board.board_date}</td>
            </tr>
            <tr>
                <td id="title">작성자</td>
                <td>${board.board_id}</td>
            </tr>
            <tr>
                <td id="title">
                    제 목
                </td>
                <td>
                    ${board.board_subject}
                </td>        
            </tr>
            <tr>
                <td id="title">
                    내 용
                </td>
                <td>
                    ${board.board_content}
                </td>        
            </tr>
 <!--           <tr>
                <td id="title">
                    첨부파일
                </td>
                <td>
                    <a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a>
                </td>
            </tr> -->
    
            <tr align="center" valign="middle">
                <td colspan="5">
                <c:if test="${sessionScope.login !=null}">
                    <c:if test="${sessionScope.login == board.board_id}">
                        <input type="button" value="수정" onclick="doAction(0)" >
                        <input type="button" value="삭제" onclick="doAction(1)" >
                    </c:if>
                     <!-- <input type="button" value="답글" onclick="changeView(1)" > -->   
                </c:if>        
                    <input type="button" value="목록" onclick="changeView(0)">   

                </td>
            </tr>
        </table>
    </div>
</div>    

</body>
</html>