package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.util.TestUtil;

/**
 * Servlet implementation class Yoga
 */
@WebServlet("/Yoga")
public class Yoga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Yoga() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String Fname = request.getParameter("fname");
		String Lname = request.getParameter("lname");
		String Email = request.getParameter("email");
		String Password = request.getParameter("pwd");
		String ConPassword = request.getParameter("pwd1");
		String Address = request.getParameter("address");
		//To interact with Database
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pst = null;
		try{
			Class.forName(TestUtil.DRIVER_NAME);
			con = DriverManager.getConnection(TestUtil.DB_URL,TestUtil.USER_NAME,TestUtil.PASSWORD);
			System.out.println("Connection = "+con);
			
			// Insert data into database
			String query="insert into yoga(Fname,Lname,Email,Password,ConPassword,Address)values(?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			// Set values to place holder
			pst.setString(1,Fname);
			pst.setString(2, Lname);
			pst.setString(3, Email);
			pst.setString(4, Password);
			pst.setString(5, ConPassword);
			pst.setString(6, Address);
			
			// executeUpdate() - use it for insert,update and delete
			int res = pst.executeUpdate();
			if(res==1)
				System.out.println("Inserted a record = "+res);
			else
				System.out.println("Unable to insert a record");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				// closing database connection
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
