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
				<td><input type="text" size="50" name="quizName"
					value="${quiz.getName()}" /></td>
			</tr>

			<c:forEach var="question" items="${quiz.getQuestions()}"
				varStatus="loop">
				<tr>
					<td><b>Question &#35;${loop.index + 1}</b></td>
					<td class="edit"><a
						href="editQuestion.do?id=${question.getId()}&quizId=${quiz.getId()}">Edit</a></td>
				</tr>
				<tr>
					<td class="question">${question.getText()}</td>
					<td>Correct?</td>
				</tr>
				<c:forEach var="answer" items="${question.getAnswers()}">
					<tr>
						<td class="answer">${answer.getText()}</td>
						<c:choose>
							<c:when test="${answer.isCorrect()}">
								<td class="correct">Y</td>
							</c:when>
							<c:otherwise>
								<td class="correct">N</td>
							</c:otherwise>
						</c:choose>
					</tr>

				</c:forEach>
			</c:forEach>
		</table>
		<input type="submit" value="Update Quiz" />
	</form>
	<form action="newQuestion.do" method="GET">
		<input type="hidden" name="quizId" value="${quiz.getId()}" />
		<input type="submit" value="Add New Question" />
	</form>
</body>
</html>