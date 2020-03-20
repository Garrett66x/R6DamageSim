import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

public class Connector
{
	Util var;
	private Connection connection = null;

	   public Connector(Util var)
	   {
	      this.var = var;
	   }
	   public void connect()
	   {
	   var = new Util();
		     try 
		     {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		     } 
		     catch (ClassNotFoundException ex) 
	        {
	         System.out.println("Where is your MySQL JDBC Driver?");
		         ex.printStackTrace();
		         return;
		      }
		      this.connection = null;
			try 
			{
		        this.connection = DriverManager.getConnection(var.mySQLurl(), var.getUser(), var.getPassword());
		     } 
			catch (SQLException e) 
			{
		        System.out.println("Connection Failed! Check output console");
		        e.printStackTrace();
		        return;
		     }
		     if (this.connection != null) 
		     {
		    	 
		     
		     }
		     else 
		     {
		        System.out.println("Failed to make connection!");
		     }
	   }
	   public Connection getConn()
	   {
		   return this.connection;
	   }
}

