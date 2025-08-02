import java.sql.*;

public class ResultST
{
	public static void main(String[] args)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
			
			Statement s = c.createStatement();
			s.executeUpdate("CREATE TABLE if not exists Happy_Banking(first_name varchar(100),last_name varchar(100),e_Mail varchar(100) primary key,passWord varchar(50) unique not null,mobile_number int(20),gender varchar(20))");
			
			ResultSet r = s.executeQuery("select * from happy_banking");
			
			int i = 1;
			int j = 2;
			
			boolean com = false;
			boolean pas = false;
			String tempPass = null;
			
			String compare = "vikas.bhati@live.in";
			String pass = "718775678";
			while(r.next())
			{	
				System.out.println(r.getString("e_Mail"));
				System.out.println(r.getString("passWord"));
				
				if(compare.equalsIgnoreCase(r.getString("e_Mail")))
				{
					com = true;
				}
				else if(pass.equals(r.getString("passWord")))
				{
					tempPass = r.getString("passWord");
					pas = true;
				}
			}
			if(com)
			{
				System.out.println("email matched");
			}
			if(pas)
			{
				System.out.println("pass matched "+tempPass);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {}
	}
}
