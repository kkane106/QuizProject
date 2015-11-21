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
	<h1>${quiz.getName()}</h1>
	<form action="evaluateQuiz.do" method="POST">
		<input type="hidden" name="quiz" value="${quiz.getId()}" />
		<table>
			<c:forEach var="question" items="${quiz.getQuestions()}">
				<tr>
					<td><h4>${question.getText()}</h4></td>
				</tr>

				<c:forEach var="answer" items="${question.getAnswers()}">
					<tr>
						<td><label>${answer.getText()}</label></td>
						<td><input type="radio" name="${question.getId()}"
							value="${answer.getId()}" /></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>