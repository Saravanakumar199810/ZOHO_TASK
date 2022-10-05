package TrainTicketBooking;

import java.util.Scanner;

public class ReservationMain {
public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		boolean value=true;
		BookingLogic bt = new BookingLogic();
		
		while(value) {
			System.out.println("1)Book : \n2)Cancel : \n3)Available Tickets : \n4)Booked Tickets : \n5)Exit : \n");
			System.out.println("Enter the choice :");
			int num=sc.nextInt();
			
			switch(num) {
			
			case 1: 
			{
				if(bt.avil_Low_Berth!=0 || bt.avil_Mid_Berth!=0 || bt.avil_Upp_Berth!=0 || bt.avil_RAC!=0 || bt.avil_Wait_List!=0) {
				System.out.println("Enter the Passenger Name ");
				String name = sc.next();
				System.out.println("Enter the Passenger Age ");
				int age = sc.nextInt();
				System.out.println("Enter the Passenger Gender (Male-M,Female-F) ");
				String gender = sc.next();
				if(gender.equalsIgnoreCase("F")) {
					System.out.println("\nPress 1 for you have a child \nPress 0 for you not have a child ");
					int child=sc.nextInt();
					if(child==1) {
						System.out.println("Enter the Child Name ");
						String child_name = sc.next();
						System.out.println("Enter the Child Age ");
						int child_age = sc.nextInt();
						System.out.println("Enter the Child Gender (Male-M,Female-F) ");
						String child_gender = sc.next();
						System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
						String berth_pref = sc.next();
						
							if(bt.avil_Low_Berth==0 && berth_pref.contains("L")  ) {
							System.out.println(berth_pref+" is full choose another");
							System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
							berth_pref = sc.next();
							}
							else if(bt.avil_Mid_Berth==0 && berth_pref.contains("M")) {
								System.out.println(berth_pref+" is full choose another");
								System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
								berth_pref = sc.next();
							}
							else if(bt.avil_Upp_Berth==0 && berth_pref.contains("U")){
								System.out.println(berth_pref+" is full choose another");
								System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
								berth_pref = sc.next();
							}
						
						PassengerPojo p= new PassengerPojo ();
						p.setName(name);
						p.setAge(age);
						p.setGender(gender);
						p.setChild_name(child_name);
						p.setChild_age(child_age);
						p.setChild_gender(child_gender);
						p.setBerth_pref(berth_pref);
						bt.bookTicket(p);
					}
					else if(child==0) {
						String child_name = "Nil";
						int child_age = 0;
						String child_gender = "Nil";
						System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
						String berth_pref = sc.next();
						PassengerPojo p= new PassengerPojo ();
						p.setName(name);
						p.setAge(age);
						p.setGender(gender);
						p.setChild_name(child_name);
						p.setChild_age(child_age);
						p.setChild_gender(child_gender);
						p.setBerth_pref(berth_pref);
						bt.bookTicket(p);
					}
				}
				else if(gender.equalsIgnoreCase("M")) {
					String child_name = "Nil";
					int child_age = 0;
					String child_gender = "Nil";
				System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
				String berth_pref = sc.next();
				
					if(bt.avil_Low_Berth==0 && berth_pref.contains("L")  ) {
					System.out.println(berth_pref+" is full choose another");
					System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
					berth_pref = sc.next();
					}
					else if(bt.avil_Mid_Berth==0 && berth_pref.contains("M")) {
						System.out.println(berth_pref+" is full choose another");
						System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
						berth_pref = sc.next();
					}
					else if(bt.avil_Upp_Berth==0 && berth_pref.contains("U")){
						System.out.println(berth_pref+" is full choose another");
						System.out.println("Enter the Passenger berth preference (Lower-L,Middele-M,Upper-U");
						berth_pref = sc.next();
					}
				
				PassengerPojo p= new PassengerPojo ();
				p.setName(name);
				p.setAge(age);
				p.setGender(gender);
				p.setChild_name(child_name);
				p.setChild_age(child_age);
				p.setChild_gender(child_gender);
				p.setBerth_pref(berth_pref);
				bt.bookTicket(p);
				}
				}
				else {
					System.out.println("\nNo Ticket Available\n");
				}
				break;
			}
			case 2:
			{
				System.out.println("Enter the Passenger ID : ");
				int id = sc.nextInt();
				bt.cancelTicket(id);
				break;
			}
			case 3:
			{
				bt.availableTickets();
				break;
			}
			case 4:
			{
				bt.getPassengersDetails();
				break;
			}
			case 5:
			{
				sc.close();
				value=false;
				break;
			}
			default :
			{
				System.out.println("\nWrong option. Please Enter correct choice.......\n");
			}
			}
		}
	}

}
