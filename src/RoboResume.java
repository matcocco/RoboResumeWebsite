

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoboResume
 */
@WebServlet("/RoboResume")
public class RoboResume extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#HttpServlet()
	 */


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FullName = request.getParameter("FullName");
		String EmailAddress = request.getParameter("EmailAddress");
		String Degree = request.getParameter("Degree");
		String Work = request.getParameter("Work");
		String Skills = request.getParameter("Skills");

		ArrayList<Person> bobs = new ArrayList<Person>();
		ArrayList<Education> schools = new ArrayList<Education>();//Schools is the list of Education objects
		ArrayList<Experience> jobs = new ArrayList<Experience>();
		ArrayList<Skills> skls = new ArrayList<Skills>(); 
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/RoboResumeDB", "root", "password");
			stmt = con.createStatement();

		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		Person bob = new Person();
		
		bob.setName(FullName);
		bob.setEmail(EmailAddress);
		java.sql.PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("Insert into Person (FullName, Email) values (?, ?)");
			pstmt.setString(1, bob.getName());
			pstmt.setString(2, bob.getEmail());
			pstmt.executeUpdate();
			//Selects person Id
			pstmt = con.prepareStatement("Select max(PersonId) from Person");
			ResultSet rs4 = pstmt.executeQuery();
			rs4.next();
			int id = rs4.getInt(1);
			bob.setPersonId(id);
			//System.out.println("Enter your education");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		Education school = new Education();
		school.setDegree(Degree);
		java.sql.PreparedStatement pstmt1 = null;
		try {
			pstmt1 = con.prepareStatement("Insert into Education (Degree, PersonId) values (?,?)");
			pstmt1.setString(1, (school.getDegree()));
			pstmt1.setInt(2, (bob.getPersonId()));
			pstmt1.executeUpdate();
			schools.add(school);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		Experience job = new Experience();
		job.setWork(Work);
		java.sql.PreparedStatement pstmt2 = null;
		try {
			pstmt2 = con.prepareStatement("Insert into Experience (Experience, PersonId) values (?,?)");
			pstmt2.setString(1, (job.getWork()));
			pstmt2.setInt(2, (bob.getPersonId()));
			pstmt2.executeUpdate();
			jobs.add(job);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		



		Skills skl = new Skills();
		skl.setSkills(Skills);
		java.sql.PreparedStatement pstmt3 = null;
		try {
			pstmt3 = con.prepareStatement("Insert into Skills (Skill, PersonId) values (?,?)");
			pstmt3.setString(1, (skl.getSkills()));
			pstmt3.setInt(2, (bob.getPersonId()));
			pstmt3.executeUpdate();
			skls.add(skl);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			stmt.close();
			con.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("FullName", FullName);
		request.setAttribute("EmailAddress", EmailAddress);
		request.setAttribute("Degree", Degree);
		request.setAttribute("Work", Work);
		request.setAttribute("Skills", Skills);
		
	getServletContext().getRequestDispatcher("/RoboResume.jsp").forward(request, response);
	
	}
}