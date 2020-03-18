package com.rs.jobapp.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class JobDao {

	public static final String URL = "jdbc:mysql://localhost:3306/jobs";
	public static final String USER = "root";
	public static final String PASS = "arthrexserver";

	Connection connection = null;

	/**
	 * Get a connection to database
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return (Connection) DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	public JobDao() {
	}

	public Integer saveJobPosting(JobPosting jobPosting) {

		connection = JobDao.getConnection();
		PreparedStatement ps = null;

		try {
			ps = (PreparedStatement) connection.prepareStatement(
					"INSERT INTO job_listing VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, jobPosting.getCompany());
			ps.setString(2, jobPosting.getTitle());
			ps.setString(3, jobPosting.getPostedOn());
			ps.setInt(4, jobPosting.getMinExp());
			ps.setInt(5, jobPosting.getMaxExp());
			ps.setBoolean(6, jobPosting.isDeleted());
			ps.setString(7, jobPosting.getSkills());
			ps.setString(8, jobPosting.getRoles());
			ps.setString(9, jobPosting.getReferenceId());
			ps.setString(10, jobPosting.getLocation());
			ps.setString(11, jobPosting.getQualifications());
			ps.setString(12, jobPosting.getTechnologies());
			ps.setString(13, jobPosting.getContact());
			ps.setString(14, jobPosting.getMobile());
			ps.setString(15, jobPosting.getEmail());
			ps.setString(16, jobPosting.getApply());
			int i = ps.executeUpdate();
			if (i == 1) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return generatedKeys.getInt(1);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				return -1;
			}
		}
		return -1;
	}

	public boolean updateJobPosting(JobPosting jobPosting) {
		Connection connection = JobDao.getConnection();
		PreparedStatement ps = null;

		try {
			ps = (PreparedStatement) connection.prepareCall("update job_listing set"
					+ " company = ?, title = ?,  roles = ?,  skills = ?,  technologies = ?,  qualifications = ?,"
					+ " experience_min = ?,  experience_max = ?,  deleted = ?, posted_on = ?,  reference_id = ?,"
					+ " location = ?, contact = ?, mobile = ?, email = ?, apply = ? where id = ?");
			ps.setString(1, jobPosting.getCompany());
			ps.setString(2, jobPosting.getTitle());
			ps.setString(3, jobPosting.getRoles());
			ps.setString(4, jobPosting.getSkills());
			ps.setString(5, jobPosting.getTechnologies());
			ps.setString(6, jobPosting.getQualifications());
			ps.setInt(7, jobPosting.getMinExp());
			ps.setInt(8, jobPosting.getMaxExp());
			ps.setBoolean(9, jobPosting.isDeleted());
			ps.setString(10, jobPosting.getPostedOn());
			ps.setString(11, jobPosting.getReferenceId());
			ps.setString(12, jobPosting.getLocation());
			ps.setString(13, jobPosting.getContact());
			ps.setString(14, jobPosting.getMobile());
			ps.setString(15, jobPosting.getEmail());
			ps.setString(16, jobPosting.getApply());
			ps.setInt(17, jobPosting.getId());

			int result = ps.executeUpdate();
			if (result == 1)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

	public List<JobPosting> list() {

		List<JobPosting> jobs = new ArrayList<>();
		Statement st = null;
		try {
			connection = JobDao.getConnection();
			st = (Statement) connection.createStatement();
			String sql = "select * from job_listing";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if(!rs.getBoolean("deleted")) {
					JobPosting jobPosting = new JobPosting(rs.getInt("id"));
					jobPosting.setCompany(rs.getString("company"));
					jobPosting.setTitle(rs.getString("title"));
					jobPosting.setTechnologies(rs.getString("technologies"));
					Date postedOn = rs.getDate("posted_on");
					if (postedOn != null)
						jobPosting.setPostedOn(postedOn.toString());
					jobPosting.setMinExp(rs.getInt("experience_min"));
					jobPosting.setMinExp(rs.getInt("experience_max"));
					jobPosting.setLocation(rs.getString("location"));
					jobs.add(jobPosting);
				}
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jobs;
	}

	public JobPosting get(Integer id) {
		connection = JobDao.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "select * from job_listing where id = ?";
			pst = (PreparedStatement) connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			if (resultSet.next()) {
				JobPosting jobPosting = new JobPosting(resultSet.getInt("id"));
				jobPosting.setCompany(resultSet.getString("company"));
				jobPosting.setTitle(resultSet.getString("title"));
				jobPosting.setReferenceId(resultSet.getString("reference_id"));
				jobPosting.setPostedOn(resultSet.getDate("posted_on").toString());
				jobPosting.setLocation(resultSet.getString("location"));
				jobPosting.setMinExp(resultSet.getInt("experience_min"));
				jobPosting.setMaxExp(resultSet.getInt("experience_max"));
				jobPosting.setDeleted(resultSet.getBoolean("deleted"));
				jobPosting.setSkills(resultSet.getString("skills"));
				jobPosting.setRoles(resultSet.getString("roles"));
				jobPosting.setQualifications(resultSet.getString("qualifications"));
				jobPosting.setTechnologies(resultSet.getString("technologies"));
				jobPosting.setContact(resultSet.getString("contact"));
				jobPosting.setMobile(resultSet.getString("mobile"));
				jobPosting.setEmail(resultSet.getString("email"));
				jobPosting.setApply(resultSet.getString("apply"));
				return jobPosting;
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
