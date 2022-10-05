package BankingApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;


public class AccountManagement {
	
	public static boolean isValidEmail(String email)
	{
	    String regex = "^(.+)@(\\S+)$";
	    Pattern p = Pattern.compile(regex);
	    if (email == null) {
	        return false;
	    }
	    Matcher m = p.matcher(email);
	    return m.matches();
	}
	
	public static boolean isValidPhoneNumber(String number)
	{
	    String regex = "^\\d{10}$";
	    Pattern p = Pattern.compile(regex);
	    if (number == null) {
	        return false;
	    }
	    Matcher m = p.matcher(number);
	    return m.matches();
	}
	
	public boolean isValidPassWord(String passWord) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,12}$";
	    Pattern p = Pattern.compile(regex);
	    if (passWord == null) {
	        return false;
	    }
	    Matcher m = p.matcher(passWord);
	    return m.matches();
	}
	
	public boolean isValidPanCard(String panCard) {
		String regex = "[A-Z]{5}{0-9}{4}[A-Z]{1}";
	    Pattern p = Pattern.compile(regex);
	    if (panCard == null) {
	        return false;
	    }
	    Matcher m = p.matcher(panCard);
		return m.matches();
	}
	
	Scanner sc = new Scanner(System.in);
	BankCustomers bankcustomer;
	boolean check = true;
	
	private static Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	public static Connection getConnect() {
		if(con == null) {
			try {
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root","Saravana@98");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		else {
			return con;
		}
		return null;
	}
	
	public void creatAccount(String accType) {
		bankcustomer = new BankCustomers();
		System.out.println("\nEnter Name : ");
		String name = sc.nextLine();
		bankcustomer.setCustomerFullName(name);
		
		while(check!=false) {
			try {
				System.out.println("\nEnter Your Birth ");
				System.out.println("Enter Year : ");
				int year = sc.nextInt();
				System.out.println("Enter Month : ");
				int month = sc.nextInt();
				System.out.println("Enter Date : ");
				int date = sc.nextInt();
				String dob = ""+year+"-"+month+"-"+date;
			
				if (year > 1950 && year < Calendar.getInstance().get(Calendar.YEAR) - 18) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					df.setLenient(false);
					df.parse(dob);
					bankcustomer.setDateOfBirth(dob);
					check=false;
				}
				else {
					System.out.println("\nEnter valid Date...\n");
				}
			} catch (Exception e) {
				System.out.println("\nEnter valid Date...\n");
			}	
		}
		
		check=true;
		while(check!=false) {
			System.out.println("Enter Gender(M-Male, F-Female, T-Trangender : ");
			String gender = ""+sc.next().toUpperCase().charAt(0);
			if(gender.equals("M") || gender.equals("F") || gender.equals("T")) {
				bankcustomer.setGender(gender);
				check=false;
			}
			else {
				System.out.println("\nEnter Valid Details...");
			}
		}
		
		check=true;
		while(check!=false) {
			System.out.println("Enter Email : ");
			String email = sc.next();
			boolean val = isValidEmail(email);
			if(val) {
				bankcustomer.setEmail(email);
				check=false;
			}
			else {
				System.out.println("Enter Valid Email id...");
			}
		}
		check=true;
		while(check!=false) {
			System.out.println("Enter PassWord : ");
			String passWord = sc.next();
			boolean val = isValidPassWord(passWord);
			if(val) {
				bankcustomer.setPassWord(passWord);
				check=false;
			}
			else {
				System.out.println("Create PassWord(8 to 12) digits using numbers, "
						+ "characters(upper,lower), one symbol must(!@#$%&*()-+=^.),...");
			}
		}
		
		check=true;
		while(check!=false) {
			System.out.println("Enter phone Number : ");
			String number = sc.next();
			boolean val = isValidPhoneNumber(number);
			if(val) {
				bankcustomer.setPhoneNumber(number);
				check=false;
			}
			else {
				System.out.println("Enter Valid Phone Number...");
			}
		}
		
		check=true;
		int warning = 0;
		while(check) {
			System.out.println("Enter Mininmum Account Balance Amount \n"
					+ "It must 1000 or more Amount for Savings Account \n"
					+ "(OR) It must 3000 or more Amount for Current Account :");
			long balance = sc.nextLong();
			if(balance < 1000 && accType.equals("Savings")) {
				if(warning!=1) {
					System.out.println("WARNING \nPlease enter Correct amount "
							+ "if you do this mistake again it will be terminated...");
					warning++;
				}
				else {
					return;
				}
			}
			else if(balance < 3000 && accType.equals("Current")) {
				if(warning!=1) {
					System.out.println("WARNING \nPlease enter Correct amount "
							+ "if you do this mistake again it will be terminated...");
					warning++;
				}
				else {
					return;
				}
			}
			else {
				bankcustomer.setBalance(balance);
				check=false;
			}
		}
		sc.nextLine(); //nextline
		System.out.println("Enter City : ");
		String city = sc.nextLine().toLowerCase();
		bankcustomer.setCity(city);
		bankcustomer.setAccounttype(accType);
		try {
			con = getConnect();
			String str ="insert into customerdetails(name, gender, dateofbirth, email, phonenumber, accounttype, city, password) "
					+ "Values(" + "'" + bankcustomer.getCustomerFullName() + "', " + "'" + bankcustomer.getGender() + "', "
					+ "'" + bankcustomer.getDateOfBirth() + "', " + "'" + bankcustomer.getEmail() + "', " + "'" + bankcustomer.getPhoneNumber() + "', "
					+ "'" + bankcustomer.getAccounttype() + "', " + "'" + bankcustomer.getCity() + "', " + "'"
					+ bankcustomer.getPassWord() + "'" + ")";
			pst = con.prepareStatement(str);

			int x = pst.executeUpdate();
			if (x == 1) {
				str="select customerid from customerdetails where phonenumber="+bankcustomer.getPhoneNumber();
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				rs.next();
				bankcustomer.setCustomerid(Integer.parseInt(rs.getString("customerid")));
				bankcustomer.setDateOfOpen(""+java.time.LocalDate.now());
				bankcustomer.setAccountStatus("Active");
				str="insert into accountdetails(customerid, dateofaccountopen, accountbalance, accountstatus) "
						+ "Values(" + "'" + bankcustomer.getCustomerid() + "', "+"'"+bankcustomer.getDateOfOpen()+"', "+"'" + bankcustomer.getBalance() + "', " 
						+ "'" +bankcustomer.getAccountStatus() +"'"+")";
				pst = con.prepareStatement(str);
				x = pst.executeUpdate();
				if(x == 1) {
					str="select accountnumber from accountdetails where customerid="+bankcustomer.getCustomerid();
					pst = con.prepareStatement(str);
					rs=pst.executeQuery();
					rs.next();
					System.out.println("\nYour Account is Successfully Opened\n"
							+ "Your Account Number is : "+ rs.getString("accountnumber")+"\n"
							+ "Your Customer ID is : "+bankcustomer.getCustomerid());
				}
			}
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	
	public boolean adminlogin(String adminId, String adminPassWord) {
		
		try {
			con = getConnect();
			String str ="select adminid, adminpassword from adminlogin where adminid=24681012";
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			rs.next();
			String correctAdminId = rs.getString("adminid");
			String correctAdminPassWord = rs.getNString("adminpassword");
			if(correctAdminId.equals(adminId)) {
				if(correctAdminPassWord.equals(adminPassWord)) {
					return true;
				}
				else {
					System.out.println("Please Enter Valid Admin Password...");
					return false;
				}
			}
			else {
				System.out.println("Please Enter Valid AdminId...");
				return false;
			}
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public void allUserDetails() {
		System.out.println("Enter Your Choice : \n1.Customer Persional Details"
				+ "\n2.Customer Account Details : ");
		String choice1=sc.next();
		try {
			con = getConnect();
			String str ;
			switch(choice1){
			case "1":
				str = "SELECT COUNT(*) FROM customerdetails";
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				str ="select * from customerdetails";
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				while (rs.next()) {
					System.out.println("\n***********************USER INFO***********************\n");
					System.out.println("Customer ID      : "+rs.getString("customerid"));
					System.out.println("Name             : "+rs.getString("name"));
					System.out.println("Gender           : "+rs.getString("gender"));
					System.out.println("Date of Birth    : "+rs.getString("dateofbirth"));
					System.out.println("Email            : "+rs.getString("email"));
					System.out.println("Phone Number     : "+rs.getString("phonenumber"));
					System.out.println("Account Type     : "+rs.getString("accounttype"));
					System.out.println("City             : "+rs.getString("city"));
					System.out.println("PassWord         : "+rs.getString("password"));
					System.out.println("\n***********************USER INFO***********************\n");
				}
				break;
			case "2":
				str = "SELECT COUNT(*) FROM accountdetails";
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				str ="select * from accountdetails";
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				while (rs.next()) {
					System.out.println("\n***********************ACCOUNT INFO***********************\n");
					System.out.println("Customer ID      : "+rs.getString("customerid"));
					System.out.println("Account Number   : "+rs.getString("accountnumber"));
					System.out.println("Date of AC Open  : "+rs.getString("dateofaccountopen"));
					System.out.println("Account Balance  : "+rs.getString("accountbalance"));
					System.out.println("Account Status   : "+rs.getString("accountstatus"));
					System.out.println("\n***********************ACCOUNT INFO***********************\n");
				}
				break;
			default :
				System.out.println("\nEnter Valid Input...\n");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public boolean toRemoveUserAccount(int userId) {
		try {
			con = getConnect();
			String str ="select * from customerdetails where customerid = "+"'"+userId+"'";
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			rs.next();
			System.out.println("\n***********************USER INFO***********************\n");
			System.out.println("Customer ID      : "+rs.getString("customerid"));
			System.out.println("Name             : "+rs.getString("name"));
			System.out.println("Gender           : "+rs.getString("gender"));
			System.out.println("Date of Birth    : "+rs.getString("dateofbirth"));
			System.out.println("Email            : "+rs.getString("email"));
			System.out.println("Phone Number     : "+rs.getString("phonenumber"));
			System.out.println("Account Type     : "+rs.getString("accounttype"));
			System.out.println("City             : "+rs.getString("city"));
			System.out.println("PassWord         : "+rs.getString("password"));
			System.out.println("\n***********************USER INFO***********************\n");
			System.out.println("press 1 to confirm Delete");
			String confirm = sc.next();
			if(confirm.equals("1")) {
				
				str ="delete from transactionsdetails where customerid = "+userId;
				pst = con.prepareStatement(str);
				int x = pst.executeUpdate();
				if(x==0) {
					x=1;
				}
				if (x == 1) {
					str ="delete from accountdetails where customerid = "+userId;
					pst = con.prepareStatement(str);
					x = pst.executeUpdate();
					if (x == 1) {
						str ="delete from customerdetails where customerid = "+userId;
						pst = con.prepareStatement(str);
						x = pst.executeUpdate();
						if (x == 1) {
							System.out.println("\nDeletion Completed\n");
							return true;
						}
					}
				}
			}else {
				System.out.println("\nDeletion Cancelled\n");
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public void toSearchUserDetails(String option) {
		boolean value1=true;
		String search="";
		while(value1) {
			if(option.equals("admin")) {
				System.out.println("\nEnter You Want to Search by \n1.Name \n2.Customer ID "
						+ "\n3.User Account Details \n4.User Transactions Details \n5.Back ");
				search = sc.next();
			}
			else {
				search="2";
			}
			switch(search) {
			case "1":
				sc.nextLine();
				System.out.println("\nEnter Name to Search : ");
				String name=sc.nextLine();
				if(toSearch(name, Integer.parseInt(search))) {
					value1=false;
				}
				else {
					System.out.println("Not Found...");
				}
				break;
			case "2":
				String customerId;
				if(option.equals("admin")) {
					sc.nextLine();
					System.out.println("\nEnter Customer ID to Search : ");
					customerId=sc.nextLine();
				}
				else {
					customerId=option;
				}
				if(toSearch(customerId, Integer.parseInt(search))) {
					value1=false;
				}
				else {
					System.out.println("Not Found...");
				}
				break;
			case "3":
				sc.nextLine();
				System.out.println("\nEnter Customer ID to Search : ");
				customerId=sc.nextLine();
				toSearch(customerId);
				break;
			case "4":
				userTransactionsDetails("admin");
				break;
			case "5":
				value1=false;
				break;
			default:
				System.out.println("\nEnter valid number...\n");
			}
		}
	}
	
	public boolean toSearch(String info) {
		try {
			con = getConnect();
			String str ="select * from accountdetails where customerid = "+info;
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			while (rs.next()) {
				System.out.println("\n***********************ACCOUNT INFO***********************\n");
				System.out.println("Customer ID      : "+rs.getString("customerid"));
				System.out.println("Account Number   : "+rs.getString("accountnumber"));
				System.out.println("Date of AC Open  : "+rs.getString("dateofaccountopen"));
				System.out.println("Account Balance  : "+rs.getString("accountbalance"));
				System.out.println("Account Status   : "+rs.getString("accountstatus"));
				System.out.println("\n***********************ACCOUNT INFO***********************\n");
			}
		}
		catch(Exception e) {
			return false;
		}	
		return true;
	}
	
	public boolean toSearch(String info, int val) {
		if(val==1) {
			info=" name = "+"'"+info+"'";
		}
		else{
			info=" customerid = "+info;
		}
		try {
			con = getConnect();
			String str ="select * from customerdetails where "+info;
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			while (rs.next()) {
				System.out.println("\n***********************USER INFO***********************\n");
				System.out.println("Customer ID      : "+rs.getString("customerid"));
				System.out.println("Name             : "+rs.getString("name"));
				System.out.println("Gender           : "+rs.getString("gender"));
				System.out.println("Date of Birth    : "+rs.getString("dateofbirth"));
				System.out.println("Email            : "+rs.getString("email"));
				System.out.println("Phone Number     : "+rs.getString("phonenumber"));
				System.out.println("Account Type     : "+rs.getString("accounttype"));
				System.out.println("City             : "+rs.getString("city"));
				System.out.println("PassWord         : "+rs.getString("password"));
				System.out.println("\n***********************USER INFO***********************\n");
			}
		}
		catch(Exception e) {
			return false;
		}	
		return true;
	}
	
	public boolean toChangeAdminPassWord(String aId, String pW) {
		try {
			con = getConnect();
			String str ="UPDATE adminlogin SET adminpassword = "+"'"+pW+"'"+" WHERE adminid = "+aId;
			pst = con.prepareStatement(str);
			int x = pst.executeUpdate();
			if (x == 1) {
				System.out.println("\nUpdate Completed\n");
				return true;
			}
			else {
				System.out.println("\nNot Updated...\n");
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}	
		return true;
	}
	
	public boolean userLogin(String userId, String userPassWord) {
		try {
			con = getConnect();
			String str ="select customerid, password from customerdetails where customerid="+userId;
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			rs.next();
			String correctUserId = rs.getString("customerid");
			String correctUserPassWord = rs.getNString("password");
			if(correctUserId.equals(userId)) {
				if(correctUserPassWord.equals(userPassWord)) {
					return true;
				}
				else {
					System.out.println("Please Enter Valid Admin Password...");
					return false;
				}
			}
			else {
				System.out.println("Please Enter Valid AdminId...");
				return false;
			}
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public void withDrawAmount(String userId) {
		boolean check=true;
		con = getConnect();
		while(check) {
			System.out.println("\nEnter Amount to WithDraw : ");
			long amount=sc.nextInt();
			try {
				String str ="select accountbalance from accountdetails where customerid="+userId;
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				rs.next();
				String accountBalance = rs.getString("accountbalance");
				
				if(100000>Integer.parseInt(accountBalance)) {
					if(amount<Integer.parseInt(accountBalance)) {
						System.out.println("Your WithDrawn Amount is : "+amount);
						long amount1=Long.parseLong(accountBalance)-amount;
						str = "update accountdetails set accountbalance = "+amount1+" where customerid="+userId;
						pst = con.prepareStatement(str);
						int x = pst.executeUpdate();
						if (x == 1) {
							if(toUpdateTransactionTable(userId, "WithDrawn", amount)) {
								System.out.println("Your Balance Amount is : "+amount1);
							}
							else {
								System.out.println("\ntransaction Details Error...\n");
							}
							check=false;
						}
					}
					else {
						System.out.println("\nYour WithDrawn Amount is must be less than Available Balance...\n");
					}
				}
				
				
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public void depositeAmount(String userId) {
		boolean check=true;
		con = getConnect();
		while(check) {
			System.out.println("\nEnter Amount to Deposit : ");
			long amount=sc.nextInt();
			try {
				String str ="select accountbalance from accountdetails where customerid="+userId;
				pst = con.prepareStatement(str);
				rs=pst.executeQuery();
				rs.next();
				String accountBalance = rs.getString("accountbalance");
				if(100000>Integer.parseInt(accountBalance)) {
					System.out.println("Your Deposite Amount is : "+amount);
					long amount1=Long.parseLong(accountBalance)-amount ;
					str = "update accountdetails set accountbalance = "+amount1+" where customerid="+userId;
					pst = con.prepareStatement(str);
					int x = pst.executeUpdate();
					if (x == 1) {
						if(toUpdateTransactionTable(userId, "Deposit", amount)) {
							System.out.println("Your Balance Amount is : "+amount1);
						}
						else {
							System.out.println("\ntransaction Details Error...\n");
						}
						check=false;
					}
				}
				else {
					System.out.println("Your Deposite amount is more than 1 Lakh So Please Enter Pan Details : ");
					String panCard = sc.next();
					if(isValidPanCard(panCard)) {
						System.out.println("Your Deposite Amount is : "+amount);
						long amount1=Long.parseLong(accountBalance)-amount ;
						str = "update accountdetails set accountbalance = "+amount1+" where customerid="+userId;
						pst = con.prepareStatement(str);
						int x = pst.executeUpdate();
						if (x == 1) {
							if(toUpdateTransactionTable(userId, "Deposite", amount)) {
								System.out.println("Your Balance Amount is : "+amount1);
								check=false;
							}
							else {
								System.out.println("\ntransaction Details Error...\n");
							}
						}
					}
					else {
						System.out.println("\nYour PanCard Number is Invalid...\n");
					}
				}
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	public boolean toUpdateTransactionTable(String customerId, String transactionType, long transactionAmount) {
		try {
			con = getConnect();
			String dateOfTransaction = ""+java.time.LocalDate.now();
			String str = "insert into transactionsdetails(customerid, dateoftransaction, transactiontype, transactionamount) "
					+ "Values(" + "'" + customerId + "', "+"'"+dateOfTransaction+"', "+"'" + transactionType + "', " 
					+ "'" + transactionAmount +"'"+")";
			pst = con.prepareStatement(str);
			int x = pst.executeUpdate();
			if(x == 1) {
				return true;
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		return true;
	}
	
	public void userTransactionsDetails(String userId) {
		String str="";
		con = getConnect();
		if(userId.equals("admin")) {
			boolean check=true;
			while(check) {
				System.out.println("\nEnter Your Choice \n1.All User Details \n2.Single User Details \n3.Back ");
				String choice2 =sc.next();
				if(choice2.equals("1")) {
					str="select * from transactionsdetails";
					check=false;
				}
				else if(choice2.equals("2")) {
					System.out.println("\nEnter Customer Id : ");
					String custId=sc.next();
					str="select * from transactionsdetails where customerid = "+custId;
					check=false;
				}
				else if(choice2.equals("3")) {
					check=false;
					return;
				}
				else {
					System.out.println("\nEnter Valid Input...\n");
				}
			}
			
		}
		else {
			str="select * from transactionsdetails where customerid = "+userId;
		}
		try {
			int count=0;
			pst = con.prepareStatement(str);
			rs=pst.executeQuery();
			System.out.println("\n***********************TRANSACTION INFO***********************\n");
			while(rs.next()) {
				count++;
				System.out.println("Customer Id          : "+rs.getString("customerid"));
				System.out.println("Date of Transaction  : "+rs.getString("dateoftransaction"));
				System.out.println("Transaction Type     : "+rs.getString("transactiontype"));
				System.out.println("Transaction Amount   : "+rs.getString("transactionamount"));
				System.out.println();
			}
			if(count==0) {
				System.out.println("\nNo Transaction Found\n");
			}
			System.out.println("\n***********************TRANSACTION INFO***********************\n");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
