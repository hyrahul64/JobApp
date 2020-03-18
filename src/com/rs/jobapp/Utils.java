package com.rs.jobapp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rs.jobapp.dao.JobPosting;

public class Utils {
	public static Integer tryParseInt(String s) {
		Integer i;
		try {
			i = Integer.parseInt(s);
		} catch (Exception ex) {
			i = 0;
		}
		return i;
	}

	public static String convertStringArray(String[] arr) {
		if(arr == null)
			return "";
		StringBuffer s = new StringBuffer();
		for (String ele : arr) {
			s.append(ele);
			s.append(".");
		}
		return s.toString();
	}
	
	public static List<String> getTechnologies() {
		List<String> technologies = new ArrayList<>();
		technologies.add("JAVA");
		technologies.add("C/C++");
		technologies.add("MFC/VC++");
		technologies.add("BIG DATA");
		technologies.add("CLOUD");
		technologies.add("DATABASE");
		technologies.add("PYTHON");
		technologies.add("JAVASCRIPT");
		technologies.add("CSS/HTML");
		technologies.add("C#/WPF");
		technologies.add("AI");
		technologies.add("OTHERS");
		return technologies;
	}
	
	public static List<String> getQualifications() {
		List<String> qualifications = new ArrayList<>();
		qualifications.add("BE");
		qualifications.add("MTech");
		qualifications.add("MCA");
		qualifications.add("PhD");
		qualifications.add("BCA/BSc");
		qualifications.add("Any");
		return qualifications;
	}
	
	public static JobPosting parseJobPosting(HttpServletRequest request) {
		String company = request.getParameter("company");
		String title = request.getParameter("title");
		String referenceId = request.getParameter("referenceId");
		String postedOn = request.getParameter("postedOn");
		if (postedOn.isEmpty()) {
			postedOn = null; // o/w mysql throws exception
		}
		String location = request.getParameter("location");
		Integer minExp = Utils.tryParseInt(request.getParameter("minExp"));
		Integer maxExp = Utils.tryParseInt(request.getParameter("maxExp"));
		Boolean deleted = false;
		if(request.getParameter("deleted") != null) {
			deleted = true;
		}
		String skills = request.getParameter("skills");
		String roles = request.getParameter("roles");
		String[] qualifications = request.getParameterValues("qualifications");
		String[] technologies = request.getParameterValues("technologies");
		String contact = request.getParameter("contact");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String apply = request.getParameter("apply");

		JobPosting jobPosting = new JobPosting(company, title, referenceId, postedOn, location, minExp, maxExp, deleted,
				skills, roles, Utils.convertStringArray(qualifications), Utils.convertStringArray(technologies),
				contact, mobile, email, apply);
		
		System.out.println(jobPosting);
		return jobPosting;
		
	}
}
