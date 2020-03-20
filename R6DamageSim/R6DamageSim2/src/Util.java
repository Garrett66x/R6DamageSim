import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Scanner;

public class Util {

	   private String user = "";
	   private String password = "";
	   private Connection connection = null;
	   private String DBName = "";
	   private String ServerUrl = "";
	   private String beginUrl = "";
	   private String endUrl = "";
	   String filename = "/var/lib/tomcat7/webapps/R6DamageSim2/config.properties";
	   public Util()
	   {
		   findStrings();
	   }
	   public void findStrings()
	   {
		try
		{
			File obj = new File(filename);
			Scanner read = new Scanner(obj);
			while(read.hasNextLine())
			{
				if(user == "")
				{
					user = read.nextLine();
				}
				else if(password == "")
				{
					password = read.nextLine();
				}
				else if(beginUrl == "")
				{
					beginUrl = read.nextLine();
				}
				else if(ServerUrl == "")
				{
					ServerUrl = read.nextLine();
				}
				else if(endUrl == "")
				{
					endUrl = read.nextLine();
				}
				else if(DBName == "")
				{
					DBName = read.nextLine();
				}
			}
			read.close();
		}
		
			catch(Exception e)
			{
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		
		}
	   public String getServerUrl()
	   {
		   return this.ServerUrl;
	   }
	   public String getUser()
	   {
		   return this.user;
	   }
	   public String getPassword()
	   {
		   return this.password;
	   }
	   public String getDBName()
	   {
		   return this.DBName;
	   }
	   public String mySQLurl()
	   {
		   return beginUrl + ServerUrl + endUrl + DBName;
	   }
}
