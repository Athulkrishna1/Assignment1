import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
class Register{
	String fname;
	String lname;
	String pno;
	String pass;
	Scanner sc = new Scanner(System.in);
	public void register() {
		System.out.println("First name:");
		fname=sc.next();
		System.out.println("Last name:");
		lname=sc.next();
		System.out.println("phone:");
		pno=sc.next();
		System.out.println("password:");
		pass=sc.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/athul", "root", "Pass@123");
            PreparedStatement ps= con.prepareStatement("insert into user values(?,?,?,?)");
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,pno);
            ps.setString(4,pass);

            ps.execute();
            System.out.println("data inserted");
		}
		catch(Exception e)
		{
			
		}
		
	}

	public void login(){
	
		
		System.out.println("First name:");
		fname=sc.next();
		System.out.println("password:");
		pass=sc.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/athul", "root", "Pass@123");
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery("select * from user");
            while (rs.next()) {
            	if((fname.equals(rs.getString(1))) && (pass.equals(rs.getString(4)))) {
            		System.out.println("login successfull");
            	}
            }
		
	}
		catch(Exception e) {
			System.out.println(e);
		}
	
	
}
class Bank
	{

	String name;
	String ifsc;
	String accno;
	String pno;
	int balance;
	int choice;
	public void createaccount()
	{
	Scanner sc=new Scanner(System.in);
	System.out.print("\n Enter name :");
	name=sc.next();
	System.out.print("\n Enter ifsc :");
	ifsc=sc.next();
	System.out.print("\n Enter accno :");
	accno=sc.next();
	System.out.print("\n Enter pno :");
	pno=sc.next();
	System.out.print("\n Enter balance :");
	balance=sc.nextInt();

	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
	PreparedStatement ps = c.prepareStatement("insert into account values(?,?,?,?,?)");

	ps.setString(1, name);
	ps.setString(2,ifsc);
	ps.setString(3, accno);
	ps.setString(4, pno);
	ps.setInt(5, balance);


	ps.execute();

	System.out.println("account successfully created ...!!");

	}
	catch(Exception e)
	{
	System.out.println("try once more ...!!");
	}

	}

	public void checkbalance()
	{ Scanner sc = new Scanner(System.in);
	System.out.print("\n Enter accno :");
	accno=sc.next();

	try
	{

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
	PreparedStatement ps = c.prepareStatement("select * from account where accno = ?");
	ps.setString(1,accno);
	ResultSet rs = ps.executeQuery();

	while(rs.next())
	{
	System.out.println("Your Balance is:");
	System.out.println(rs.getString(5) + ":" + rs.getString(1) );
	}
	ps.execute();
	}
	catch(Exception e)
	{
	System.out.println("Invalid credentials....Plzz enter right ones ..");
	}
	}

	public void Transfer()
	{
	Scanner sc = new Scanner(System.in);
	System.out.print("\n Enter the choice if user want to withdraw enter 2 otherwise enter 1 :");
	choice=sc.nextInt();
	System.out.print("\n Enter accno :");
	accno=sc.next();
	System.out.print("\n Enter the balance to be transferred :");
	balance=sc.nextInt();

	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
	if(choice==1)
	{

	PreparedStatement ps = c.prepareStatement("update account set balance = balance + ? where accno = ?");
	ps.setInt(1,balance);
	ps.setString(2, accno);

	ps.execute();
	}
	else
	{
	PreparedStatement ps = c.prepareStatement("update account set balance = balance - ? where accno = ?");
	ps.setInt(1,balance);
	ps.setString(2,accno);

	ps.execute();
	}

	System.out.println("Tranfer of money is done successfully:");
	//ps.execute();
	}
	catch(Exception e)
	{
	System.out.println("enter the correct credentials:");
	}

	}
	}
}
public class Assignment {

	public static void main(String[] args) {
		int ch1=0,ch2=0;
		Scanner s =new Scanner(System.in);
		Bank b = new Bank();
		do {
			System.out.println("--------------------------------");
			System.out.println("1.REGISTER");
			System.out.println("2.LOGIN");
			System.out.println("--------------------------------");
			System.out.println("Enter your Choice: ");
			ch1=s.nextInt();
			Register r = new Register();
			if(ch1==1){
				r.register();
			}
			if(ch1==2) {
				r.login();
				
				do {
					System.out.println("1.Create account");
					System.out.println("2.Check Balance");
					System.out.println("3.Transfer Money");
					System.out.println("4.Exit");
					System.out.println("--------------------------------");
					System.out.println("Enter your Choice: ");
					Scanner sc2=new Scanner(System.in);
					ch2=sc2.nextInt();

					switch(ch2){
					case 1:b.createaccount();
					break;
					case 2:b.checkbalance();
					break;
					case 3:b.Transfer();
					break;

					}while(ch2!=4);
			}
				
			
		}while(ch1!=3);
	}


}
