<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Job App</title>
</head>
<body style="background: cyan">
	<h1 align="center">New Job Posting</h1>
	<%@ page import="com.rs.jobapp.Utils,java.util.List" %>
<%
	List<String>  allTechnologies = Utils.getTechnologies(); 
	request.setAttribute("allTechnologies",allTechnologies);
	
	List<String> allQualifications = Utils.getQualifications();
	request.setAttribute("allQualifications", allQualifications);
%>
	
	<form name="jobPosting" action="add" method="post">
		<label>Company:</label>
		<input type="text" id="company" name="company">
		
		<label>Job Title:</label>
		<input type="text"  id="title" name="title">
		
		<label>Reference ID:</label>
		<input type="text" id="referenceId" name="referenceId">
		
		<label for="postedOn">Posted on:</label>
		<input type="date" id="postedOn" name="postedOn">
		
		<label for="location">Location:</label>
		<input type="text" id="location" name="location">
		
		<br/><br/>
		
		<label for="minExp">Minimum Experience:</label>
		<input type="number" min="1" max="20" id="minExp" name="minExp">
		
		<label for="maxExp">Maximum Experience:</label>
		<input type="number" min="1" max="20" id="maxExp" name="maxExp">
		
	    <input type="checkbox" name="deleted" value="deleted" id = "deleted">
  		<label for="deleted">deleted</label>
  		
		<label for="contact">Contact:</label>
		<input id="contact" name="contact" type="text"/>
		
		<label for="mobile">Mobile:</label>
		<input id="mobile" name="mobile" type="text"/>

		<label for="email">Email:</label>
		<input id="email" name="email" type="text"/>
		<br/><br/>
		
		<label>Skills</label><br/>
		<textarea rows="10" cols="" name="skills" id="skills" style="min-width:100%"></textarea>
		<br/><br/>

		<label>Roles</label><br/>
		<textarea rows="10" cols="" name="roles" id="roles" style="min-width:100%"></textarea>
		<br/><br/>

		<label for="qualifications">Qualification:</label>
		<select id="qualifications" multiple name="qualifications">
		  <c:forEach var="qualification" items="${allQualifications}">
        	<option value="${qualification}">${qualification}</option>
    	 </c:forEach>
		</select>
		
		<label for="technologies">Technologies:</label>
		<select id="technologies" name="technologies" multiple>
		 <c:forEach var="technology" items="${allTechnologies}">
        	<option value="${technology}">${technology}</option>
    	 </c:forEach>
		</select>
		
		<label for="apply">Apply:</label>
		<input id="apply" name="apply" type="text" size="100"/>
		<br/><br/>
		
		<div style="text-align:center;width:100%"><input type="submit"></div>
	</form>
</body>
</html>