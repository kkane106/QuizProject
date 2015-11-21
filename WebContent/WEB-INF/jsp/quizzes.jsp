<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quizzes</title>
</head>
<body>
	<h1>Quizzes</h1>
	<table>
		<c:forEach var="quiz" items="${quizzes}">
			<tr>
				<td><a href="showQuiz.do?id=${quiz.getId()}">${quiz.getName()}</a></td>
				<td><a href="editQuiz.do?id=${quiz.getId()}"><button>Edit</button></a></td>
				<td>
					<form action="deleteQuiz.do" method="POST">
						<input type="hidden" name="id" value="${quiz.getId()}" /> <input
							type="submit" value="Delete" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="newQuiz.do"><button>Create a Quiz</button></a>
</body>
</html>