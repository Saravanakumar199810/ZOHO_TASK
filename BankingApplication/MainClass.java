package BankingApplication;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountManagement account = new AccountManagement();
		boolean value1 = true, value2=true;
		String choice1,choice2;
		do {
			System.out.println("\nEnter your choice : \n1.Create Account \n2.Manage Account"
					+ " \n3.Exit ");
			choice1=sc.next();
			switch(choice1) {
			case "1":
				boolean acOption = true;
				while(acOption) {
					System.out.println("\nEnter Account Type : \n1.Savings \n2.Current \n3.Exit ");
					String acType=sc.next();
					if(acType.equals("1")) {
						account.creatAccount("Savings");
						acOption=false;
					}
					else if(acType.equals("2")) {
						account.creatAccount("Current");
						acOption=false;
					}
					else if(acType.equals("3")) {
						acOption=false;
					}
					else {
						System.out.println("\nEnter Valid Option...\n");
					}
				}
				break;
			case "2":
				while(value2) {
					System.out.println("\nEnter Your Choice : \n1.Admin \n2.Customer \n3.Back ");
					choice2=sc.next();
					if(choice2.equals("1")) {
						System.out.println("Enter Admin Id : ");
						String adminId=sc.next();
						System.out.println("Enter Admin PassWord : ");
						String adminPassWord=sc.next();
						if(account.adminlogin(adminId, adminPassWord)) {
							boolean value3 = true;
							while(value3) {
								System.out.println("\nEnter Your choice :\n1.View All User Details \n2.Remove User Account"
										+ "\n3.Search User Details \n4.Change Admin Password \n5.LogOut");
								String choice3=sc.next();
								switch(choice3) {
								case "1":
									account.allUserDetails();
									break;
								case "2":
									boolean value4 = true;
									while(value4) {
										System.out.println("Enter Account Number For Remove Account : ");
										String acNumber=sc.next();
										int number1 = Integer.parseInt(acNumber);
										if(account.toRemoveUserAccount(number1)) {
											value4=false;
										}
									}
									break;
								case "3":
									account.toSearchUserDetails("admin");
									break;
								case "4":
									boolean value5 = true;
									while(value5) {
										System.out.println("\nEnter New PassWord : ");
										String newPassWord=sc.next();
										System.out.println("\nConfirm New PassWord : ");
										String confirmPassWord=sc.next();
										if(newPassWord.equals(confirmPassWord) && account.isValidPassWord(confirmPassWord)) {
											account.toChangeAdminPassWord(adminId, confirmPassWord);
											value5=false;
										}
										else {
											System.out.println("Enter Valid PassWord...");
										}
									}
									break;
								case "5":
									value3=false;				
									break;
								default:
									System.out.println("Enter valid choice...\n");
								}
							}
						}
						
					}
					else if(choice2.equals("2")) {
						System.out.println("Enter User Id : ");
						String userId=sc.next();
						System.out.println("Enter User PassWord : ");
						String userPassWord=sc.next();
						if(account.userLogin(userId, userPassWord)) {
							boolean value3 = true;
							while(value3) {
								System.out.println("\nEnter Your choice :\n1.View Your Persional Details \n2.View Account Details"
										+ "\n3.View Transaction Details \n4.WithDraw Amount \n5.Deposit Amount \n6.LogOut");
								String choice3=sc.next();
								switch(choice3) {
								case "1":
									account.toSearchUserDetails(userId);
									break;
								case "2":
									account.toSearch(userId);
									break;
								case "3":
									account.userTransactionsDetails(userId);
									break;
								case "4":
									account.withDrawAmount(userId);
									break;
								case "5":
									account.depositeAmount(userId);
									break;
								case "6":
									value3=false;				
									break;
								default:
									System.out.println("Enter valid choice...\n");
								}
							}
						}
						
					}
					else if(choice2.equals("3")) {
						value2=false;
					}
					else {
						System.out.println("Enter valid choice...\n");
					}
				}
				break;
			case "3":
				value1=false;
				sc.close();
				break;
			default :
				System.out.println("\nEnter valid choice...\n");
			}
		}while(value1);

	}

}
