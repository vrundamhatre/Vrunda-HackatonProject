import java.util.Scanner;
import java.sql.*;

class Employee
{  
 	int id;	  
 	String name;  
	String task;
	String date;
	String status;
	Connection con;
	Statement sqlstmt;
	ResultSet rs;

	Employee()
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con=DriverManager.getConnection("jdbc:sqlserver://172.23.79.11:1433;userName=sa;password=P@ssw0rd;databaseName=Test"); 
			sqlstmt=con.createStatement();		
		}catch(Exception e){e.printStackTrace();}
	
	}
	void insertData()
	{  
		char ich;
		Scanner s = new Scanner(System.in);
		do
		{
		System.out.println("Enter Employee ID : ");
    		id = s.nextInt();  
		System.out.println("Enter Employee name : ");
    		name = s.nextLine();
		name = s.nextLine();
		System.out.println("Enter Task : "); 
		task = s.nextLine();
		System.out.println("Enter Task Date : "); 
		date = s.nextLine();
		System.out.println("Enter Task Status: "); 
		status = s.nextLine();

		//jdbc
		
		try
		{
			String query = "insert into Employee values("+id+",'"+name+"','"+task+"','"+date+"','"+status+"')";
			int row = sqlstmt.executeUpdate(query);
			System.out.println("Employee "+id+" Record inserted !!!"); 
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Do you want to insert more employee details: ");
		ich = s.next().charAt(0);
		}while(ich=='y'||ich=='Y');
		
    	}

	void updateData()
	{
		try{
		System.out.print("Enter ID of employee whose details you want to update:");
		Scanner s = new Scanner(System.in);
		int eid = s.nextInt();	
		System.out.print("Enter what update(Name/Task/Date/Status):");
		String change = s.next();
		if(change.equals("Name"))
		{
			System.out.print("Enter Name to update:");
			String name = s.next();
			sqlstmt.executeUpdate("update Employee set name like '"+name+"' where id="+eid);
			System.out.println("Employee "+id+" Name updated!!!"); 
		}
		else if(change.equals("Task"))
		{
			System.out.print("Enter Task to update:");
			String task = s.next();
			sqlstmt.executeUpdate("update Employee set taskname like '"+task+"' where id="+eid);
			System.out.println("Employee "+id+" Task updated!!!"); 
		}
		else if(change.equals("Task Date"))
		{
			System.out.print("Enter Task Date to update:");
			String date= s.next();
			sqlstmt.executeUpdate("update Employee set taskdate like '"+date+"' where id="+eid);
			System.out.println("Employee "+id+" Task Date updated!!!"); 
		}
		else
		{
			System.out.print("Enter Task Status to update:");
			String status= s.next();
			sqlstmt.executeUpdate("update Employee set taskstatus like '"+status+"' where id="+eid);
			System.out.println("Employee "+id+" Task Status updated!!!"); 
		}
		}catch(Exception e){e.printStackTrace();}
		
	}

	void deleteData()
	{
		try{
		System.out.print("Enter ID of employee whose details you want to delete:");
		Scanner s = new Scanner(System.in);
		int eid = s.nextInt();	
		sqlstmt.executeUpdate("delete from Employee where id="+eid);
		System.out.println("Employee "+id+" Record deleted !!!"); 
		}catch(Exception e){e.printStackTrace();}
	}

	void showAllData()
	{
		try{  
			ResultSet rs = sqlstmt.executeQuery("select * from Employee");
			System.out.println("Employee ID | \t Employee Name | \t Task Name | \t Task Date | \t Task Status"); 
			System.out.println("------------------------------------------------------------------------------"); 		
			while(rs.next())
			{
				System.out.print(rs.getInt("id")+"\t|"); 
				System.out.print(rs.getString("name")+"\t|");
				System.out.print(rs.getString("taskname")+"\t|");  
				System.out.print(rs.getString("taskdate")+"\t|");  
				System.out.print(rs.getString("taskstatus"));  
			}
		}catch(Exception e){e.printStackTrace();}
	}

	void showData()
	{
		try{
			System.out.print("Enter ID of employee whose details you want to view: ");
			Scanner s = new Scanner(System.in);
			int eid = s.nextInt();
			ResultSet rs = sqlstmt.executeQuery("select * from Employee where id = "+eid);
			System.out.println("Employee ID | \t Employee Name | \t Task Name | \t Task Date | \t Task Status"); 
			System.out.println("------------------------------------------------------------------------------"); 		
			while(rs.next())
			{
				System.out.print(rs.getInt("id")+"\t|"); 
				System.out.print(rs.getString("name")+"\t|");
				System.out.print(rs.getString("taskname")+"\t|");  
				System.out.print(rs.getString("taskdate")+"\t|");  
				System.out.print(rs.getString("taskstatus"));  
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
 }
public class EmpTaskApp
{
    	public static void main(String args[])
	{  
		System.out.println("Welcome to NIIT Employee Portal\n------------------------------------------------------");
		System.out.println("\tMenu\n1. Insert Employee Data\n2. Modify Employee Data\n3. Delete Employee Data\n4. Show all Employee Data\n5. View Single Employee Data\n6. Exit");
		char ch1;
		Employee e = new Employee();
		do {
		System.out.println("Enter your choice : ");
		Scanner s = new Scanner(System.in);
		int ch = s.nextInt();
		if(ch == 1)
		{
			e.insertData();
		}
		else if(ch == 2)
		{
			e.updateData();  
		}
		else if(ch == 3)
		{
			e.deleteData();  
		}
		else if(ch == 4)
		{
			e.showAllData();  
		}
		else if(ch == 5)
		{
			e.showData();  
		}
		else
		{
			System.exit(0);
		}
		System.out.println("Do you want to continue next transaction : ");
		ch1 = s.next().charAt(0);
		}
		while(ch1 == 'Y' || ch1 =='y');
   	}  
}  