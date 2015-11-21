<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${quiz.getName()}</title>
</head>
<body>
	<form action="updateQuiz.do" method="POST">
		<input type="hidden" name="quizId" value="${quiz.getId()}" />
		<table>
			<tr>
				<td><b>Quiz Name</b></td>
			</tr>
			<tr>
				<td><input type="text" size="70" name="quizName"
					value="${quiz.getName()}" /></td>
			</tr>

			<c:forEach var="question" items="${quiz.getQuestions()}"
				varStatus="loop">
				<tr>
					<td><b>Question &#35;${loop.index + 1}</b></td>
				</tr>
				<tr>
					<td><input type="text" size="70" name="questions"
						value="${question.getText()}" /></td>
				</tr>
				<c:forEach var="answer" items="${question.getAnswers()}">
					<tr>
						<td><input type="text" size="70" name="answers"
							value="${answer.getText()}" /></td>
						<td><input type="radio" name="${answer.getId()}"
							value="${answer.getId()}" /></td>
					</tr>

				</c:forEach>
			</c:forEach>
		</table>
		<input type="submit" value="Update" />
	</form>
</body>
</html>