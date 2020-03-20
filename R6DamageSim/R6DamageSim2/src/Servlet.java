import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int x;
	static Connection connection = null;
	public Servlet()
	{
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		Util login = new Util();
		Connector connect = new Connector(login);
		connect.connect();
		this.connection = connect.getConn();
		String selectSQL = "SELECT * FROM guns;";
		setX();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			response.getWriter().println("<!doctype html public \".//w3c//dtd html 4.0 " + 
			"transitional//en\">\n" + "<html>\n" + "<head><title>R6DamageSim2</title></head>\n" + 
					"<body>\n" + "<h1 align = \"center\">Rainbow Six Siege Damage Simulator</h1>\n" + 
			"<label>Select Weapon From List By Typing The Number In The List</label>\n" + "<br>\n" + 
					"<form method = \"post\" action = \"Servlet2\">\n" + "<input type = \"number\" id = \"gunNumber\" name = \"gunNumber\" min = \"1\" max = \"84\" value = \"1\"/>\n" + 
			"<br>\n" + "<label>Now Pick The Distance You Want To Shoot At In Meters 1-41</label>\n" + 
					"<br>\n" + "<input type = \"number\" id = \"distance\" name = \"distance\" min = \"1\" max = \"41\" value = \"1\"/>\n" + 
			"<br>\n" + "<input type = \"submit\" value = \"submit\"/>" + "<br>\n" + "<label>Type 1 To Reset Operator Health</label>\n" + "<br>\n" +
					"<input type = \"number\" id = \"reset\" name = \"reset\" min = \"0\" max = \"1\" value = \"0\"/>\n" + "<br>\n" +
			"</form>\n" + "<label>Operator's Total Health</label>\n" + 
					"<br>" + "<h1 align = \"right\">" + x + "</h1>\n" + "<br>" + 
					 "</body>\n" + "</html>");
			while (rs.next())
			{
				String id = rs.getString("ID");
				String gun_name = rs.getString("gun_name");
				response.getWriter().append(id + ". " + gun_name + "<br>").toString();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doGet(request, response);
	}
	public int setX()
	{
		return x = 100;
	}
}
