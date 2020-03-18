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
 * Servlet implementation class UpdateJob
 */
@WebServlet("/update")
public class UpdateJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pathInfo = request.getPathInfo();
		JobPosting jobPosting = Utils.parseJobPosting(request);
		jobPosting.setId(Integer.valueOf(pathInfo.substring(pathInfo.lastIndexOf("/")+1)));
		JobDao jobDao = new JobDao();
		boolean result = jobDao.updateJobPosting(jobPosting);
		if(result) {
			String redirectPath = request.getContextPath() + "/job/" + jobPosting.getId();
			response.sendRedirect(redirectPath);
		}else {
			response.sendRedirect(request.getContextPath() + "/error");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
