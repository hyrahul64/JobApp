package com.rs.jobapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.jobapp.dao.JobDao;
import com.rs.jobapp.dao.JobPosting;

/**
 * Servlet implementation class ProcessJobPosting
 */
@WebServlet("/add")
public class AddJob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		JobPosting jobPosting = Utils.parseJobPosting(request);

		JobDao jobDao = new JobDao();
		Integer result  = jobDao.saveJobPosting(jobPosting);
		if (result > -1) {
			String redirectPath = request.getContextPath() + "/job/" + result;
			System.out.println(redirectPath);
			response.sendRedirect(redirectPath);
		} else {
			response.sendRedirect(request.getContextPath() + "/error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post received.");
		doGet(request, response);
	}

}
