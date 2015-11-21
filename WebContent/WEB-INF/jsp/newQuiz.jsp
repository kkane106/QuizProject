<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Quiz</title>
</head>
<body>
	<h1>New Quiz</h1>
	<form action="createQuiz.do" method="POST">
		<table>
			<c:if test="${newQuiz == null}">
				<tr>
					<td><input type="text" name="quizName" size="70"
						placeholder="Quiz Name" /></td>
				</tr>
			</c:if>
			<tr>
				<td><input type="text" name="question" size="70"
					placeholder="Question text" /></td>
				<td>Correct?</td>
			</tr>
			<tr>
				<td><input type="text" name="answer" size="70"
					placeholder="Answer" /> <input type="radio" name="correct"
					value="0" /></td>
			</tr>
			<tr>
				<td><input type="text" name="answer" size="70"
					placeholder="Answer" /> <input type="radio" name="correct"
					value="1" /></td>
			</tr>
			<tr>
				<td><input type="text" name="answer" size="70"
					placeholder="Answer" /> <input type="radio" name="correct"
					value="2" /></td>
			</tr>
			<tr>
				<td><input type="text" name="answer" size="70"
					placeholder="Answer" /> <input type="radio" name="correct"
					value="3" /></td>
			</tr>
		</table>
		<input type="submit" name="continue" value="Add Another Question" />
		<input type="submit" name="complete" value="Create Quiz" />
	</form>
</body>
</html>