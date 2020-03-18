<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script type="text/javascript" src="script.js"></script> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>Insert title here</title>
</head>
<body style="background: cyan" onLoad = "onLoad()">

	<script type="text/javascript">
	
		function onEdit() {
			console.log("edit button clicked");
			document.getElementById("company").disabled = false;
			document.getElementById("title").disabled = false;
			document.getElementById("referenceId").disabled = false;
			document.getElementById("postedOn").disabled = false;
			document.getElementById("location").disabled = false;
			document.getElementById("minExp").disabled = false;
			document.getElementById("maxExp").disabled = false;
			document.getElementById("deleted").disabled = false;
			document.getElementById("roles").disabled = false;
			document.getElementById("skills").disabled = false;
			document.getElementById("qualifications").disabled = false;
			document.getElementById("technologies").disabled = false;
			document.getElementById("contact").disabled = false;
			document.getElementById("mobile").disabled = false;
			document.getElementById("email").disabled = false;
			document.getElementById("apply").disabled = false;
		}
		function onDelete() {
			onEdit();
			document.getElementById("deleted").checked =  true;
			//document.getElementById("update-form").submit();
		}
		function onLoad() {
			var url = window.location.href;
			console.log(url);
			var array = url.split('/');
			array[array.length-2] = 'update';
			document.getElementById("update-form").action = array.join("/");
			console.log(document.getElementById("update-form").action);
		}
	</script>
	<h1 align="center">Job Posted</h1>
	<%@ page import="com.rs.jobapp.Utils,java.util.List,com.rs.jobapp.dao.*" %>
	<%List<String>  allTechnologies = Utils.getTechnologies(); 
	request.setAttribute("allTechnologies",allTechnologies);
	
	List<String> allQualifications = Utils.getQualifications();
	request.setAttribute("allQualifications", allQualifications);
	
	JobDao jobDao = new JobDao();
	String url = request.getRequestURL().toString();
	System.out.println(url);
	String tokens[] = url.split("/");
	Integer id = Integer.parseInt(tokens[tokens.length-1]);
	JobPosting job = jobDao.get(id);
	System.out.println(job.toString());
	pageContext.setAttribute("job", job);%>
	<form name="update-form" action="/update" method="post" id="update-form">
	
		<label>Company:</label>
		<input type="text" name="company" id="company" disabled  value = "${job.company}">
		
		<label>Job Title:</label>
		<input type="text" id="title" name="title" disabled value = "${job.title}">
		
		<label>Reference ID:</label>
		<input type="text" id="referenceId" name="referenceId" disabled value = "${job.referenceId}">
		
		<label for="postedOn">Posted on:</label>
		<input type="date" id="postedOn" name="postedOn"  disabled value = "${job.postedOn}">
		
		<label for="location">Location:</label>
		<input type="text" id="location" name="location"  disabled value = "${job.location}">
		
		<br/><br/>
		
		<label for="minExp">Minimum Experience:</label>
		<input type="number" min="1" max="20" id="minExp" name="minExp"  disabled value = "${job.minExp}">
		
		<label for="maxExp">Maximum Experience:</label>
		<input type="number" min="1" max="20" id="maxExp" name="maxExp"  disabled value = "${job.maxExp}">
		
	    <input type="checkbox" name="deleted" id="deleted" value="deleted" ${job.deleted ? 'checked' : ''} disabled>
  		<label for="deleted">deleted</label>
	
		<label for="contact">Contact:</label>
		<input id="contact" name="contact" type="text" disabled value="${job.contact}"/>
		
		<label for="mobile">Mobile:</label>
		<input id="mobile" name="mobile" type="text" disabled value="${job.mobile}"/>

		<label for="email">Email:</label>
		<input id="email" name="email" type="text" disabled value="${job.email}"/>
		<br/><br/>

		<label>Skills</label><br/>
		<textarea rows="10" cols="" name="skills" id="skills" style="min-width:100%"  disabled>${job.skills}</textarea>
		<br/><br/>
		<label>Roles</label><br/>
		<textarea rows="10" cols="" name="roles" id="roles" style="min-width:100%" disabled >${job.roles}</textarea>
		<br/><br/>

		<label for="qualifications">Qualification:</label>
		<select id="qualifications" name="qualifications" disabled multiple >
			<c:forEach var="qualification" items="${allQualifications}">
				<option value="${qualification}" ${fn:contains(job.qualifications, qualification) ? 'selected' : ''}>${qualification}</option>
			</c:forEach>
		</select>
		
		<label for="technologies">Technologies:</label>
		<select id="technologies" name="technologies" multiple disabled>
		 <c:forEach var="technology" items="${allTechnologies}">
        	<option value="${technology}" ${fn:contains(job.technologies,technology) ? 'selected' : ''}>${technology}</option>
    	 </c:forEach>
		</select>

		<label for="apply">Apply:</label>
		<input id="apply" name="apply" type="text" size="100" disabled value="${job.apply}"/>

		<br/><br/>
		<div style="text-align:center;width:100%">
			<button onClick="onEdit()" type = "button">Edit</button>
			<button onCLick="onDelete()">Delete</button>
			<input type="submit" value="Submit">
		</div>
	</form>
</body>
</html>