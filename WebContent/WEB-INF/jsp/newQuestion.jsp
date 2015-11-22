<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Question</title>
<style>
td.answer {
	text-indent: 40px;
}

td.question {
	text-indent: 20px;
}

td.correct, td.edit {
	text-align: center;
}
</style>
</head>
<body>
	<h1>New Question</h1>
	<form action="createQuestion.do" method="POST">
	<input type="hidden" name="quizId" value="${quiz.getId()}" />
		<table>
			<tr>
				<td><label>Question</label></td>
			</tr>
			<tr>
				<td><input type="text" size="50" name="question" /></td>
				<td class="correct">Correct?</td>
			</tr>
			<tr>
				<td><label>Answer #1:</label><input type="text" size="50"
					name="answers" /></td>
				<td class="correct"><input type="radio" name="correct" value="0" /></td>
			</tr>
			<tr>
				<td><label>Answer #2:</label><input type="text" size="50"
					name="answers" /></td>
				<td class="correct"><input type="radio" name="correct" value="1" /></td>
			</tr>
			<tr>
				<td><label>Answer #3:</label><input type="text" size="50"
					name="answers" /></td>
				<td class="correct"><input type="radio" name="correct" value="2" /></td>
			</tr>
			<tr>
				<td><label>Answer #4:</label><input type="text" size="50"
					name="answers" /></td>
				<td class="correct"><input type="radio" name="correct" value="3" /></td>
			</tr>
		</table>
		<input type="submit" value="Create Question" />
	</form>
</body>
</html>