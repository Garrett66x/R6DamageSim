import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int a = 0;
	public static int x = 100;
	static Connection connection = null;
	public Servlet2()
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
		
		try
		{
			int y = Integer.parseInt(request.getParameter("gunNumber"));
			int z = Integer.parseInt(request.getParameter("distance"));
			int reset = Integer.parseInt(request.getParameter("reset"));
			String gunChoice = setX(y, z);
			if (reset == 1)
			{
				x = 100;
			}
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
					"<input type = \"number\" id = \"reset\" name = \"reset\" min = \"0\" max = \"1\" value = \"0\"/>\n" + 
					"</form>\n" + "<label>Operator's Total Health</label>\n" + 
					"<br>" + "<h1 align = \"right\">" + x + "</h1>\n" + "<br>" + "<label>You Chose " + gunChoice + " And It Did " + a + " Damage at " + z + " meters</label>\n" + "<br>\n" + 
			
					 "<br>\n" + "</body>\n" + "</html>");
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
	public String setX(int y, int z)
	{
		String selectSQL = "SELECT * FROM guns;";
		String gun_name = "";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			rs.absolute(y);
			gun_name = rs.getString("gun_name");
			a = Integer.parseInt(rs.getString("m" + z));
			this.x = x - a;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return gun_name;
	}
}
