<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Question</title>
</head>
<body>
	<h1>Edit Question</h1>
	<form action="updateQuestion.do" method="POST">
		<input type="hidden" name="questionId" value="${question.getId()}" />
		<input type="hidden" name="quizId" value="${quizId}" />
		<table>
			<tr>
				<td><label>Question Text</label></td>
				<td><input type="text" size="70" name="question"
					value="${question.getText()}" /></td>
			</tr>

			<c:forEach var="answer" items="${question.getAnswers()}"
				varStatus="loop">
				<tr>
					<td><label>Answer #${loop.index+1}</label></td>
					<td><input type="text" size="70" name="answers"
						value="${answer.getText()}" /></td>
					<c:choose>
						<c:when test="${answer.isCorrect()}">
							<td><input type="radio" name="correct"
								value="${answer.getId()}" checked="checked" /></td>
						</c:when>
						<c:otherwise>
							<td><input type="radio" name="correct"
								value="${answer.getId()}" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Update Question" />
	</form>
</body>
</html>