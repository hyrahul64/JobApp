<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>All Jobs</title>
</head>
	<div style="text-align:center">
	<h2>All Jobs</h2>
	
	<table border="1" align="center">
	<tr>
		<td>ID</td>
		<td>Company</td>
		<td>Title</td>
		<td>Location</td>
		<td>Posted On</td>
		<td>Technologies</td>
		<td>Min Exp</td>
		<td>Max Exp</td>
	</tr>
	<c:forEach items="${jobs}" var="job">
        <tr><td><a href="${pageContext.request.contextPath}/job/${job.id}">${job.id}</a></td>
        <td>${job.company}</td>
        <td>${job.title}</td>
        <td>${job.location}</td>
        <td>${job.postedOn}</td>
        <td>${job.technologies}</td>
        <td>${job.minExp}</td>
        <td>${job.maxExp}</td></tr>
	</c:forEach>
	</table>
	</div>
<body>

</body>
</html>