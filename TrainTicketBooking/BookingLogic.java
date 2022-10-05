package TrainTicketBooking;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BookingLogic {
	int avil_Low_Berth  =1;
	int avil_Mid_Berth  =1;
	int avil_Upp_Berth  =1;
	int avil_RAC =2;
	int avil_Wait_List  =1;
	static int i=0;
	
	Queue<Integer> wait_List = new LinkedList<Integer>();
	Queue<Integer> rac_List = new LinkedList<Integer>();
	Map<Integer, PassengerPojo> passenger_data =new HashMap<Integer, PassengerPojo>();
	
public void bookTicket(PassengerPojo p) {
	if(avil_Wait_List == 0) {
		System.out.println("\nNo Tickets Available\n");
	}
	else if(p.getAge() < 5) {
		System.out.println("\nPerson age is less then 5 we can't allote a berth for this person\n");
		
	}
	else if(p.getAge() > 60 && avil_Low_Berth > 0) {
		System.out.println("\nWe Consider your age so arrange Lower berth for you...\n");
		printBookedTicket(p, "L");
		avil_Low_Berth--;
	}
	else if(p.getChild_name()!=("Nil") && p.getChild_age()<5 && avil_Low_Berth > 0) {
		System.out.println("\nYou Have a Child so arrange Lower Berth for you...\n");
		printBookedTicket(p, "L");
		avil_Low_Berth--;
	}
	
	else if((p.getBerth_pref().equalsIgnoreCase("L")&& avil_Low_Berth > 0)|| (p.getBerth_pref().equalsIgnoreCase("M")&& avil_Mid_Berth > 0) || (p.getBerth_pref().equalsIgnoreCase("U") && avil_Upp_Berth > 0)) {
	if(p.getBerth_pref().equalsIgnoreCase("L")) {
		System.out.println("\nLower Berth Given\n");
		printBookedTicket(p, "L");
		avil_Low_Berth--;
	}
	else if(p.getBerth_pref().equalsIgnoreCase("M")) {
		System.out.println("\nMiddle Berth Given\n");
		printBookedTicket(p, "M");
		avil_Mid_Berth--;
	}
	else if(p.getBerth_pref().equalsIgnoreCase("U")) {
		System.out.println("\nUpper Berth Given\n");
		printBookedTicket(p, "U");
		avil_Upp_Berth--;
	}
	}
	else if(avil_Low_Berth > 0) {
		System.out.println("\nLower Berth Given\n");
		printBookedTicket(p, "L");
		avil_Low_Berth--;
	}
	else if(avil_Mid_Berth > 0) {
		System.out.println("\nMiddle Berth Given\n");
		printBookedTicket(p, "M");
		avil_Mid_Berth--;
	}
	else if(avil_Upp_Berth > 0) {
		System.out.println("\nUpper Berth Given\n");
		printBookedTicket(p, "U");
		avil_Upp_Berth--;
	}
	else if(avil_RAC > 0) {
		System.out.println("\nRAC Berth Given\n");
		if(i%2==0) {
		printRacTicket(p, "Berth"+"RAC");
		}
		else {
			printRacTicket(p, "RAC");
		}
		while(i<21) {
			i++;
			break;
		}
		avil_RAC--;
	}
	else if(avil_Wait_List > 0) {
		System.out.println("\nWaiting List Given\n");
		printWlTicket(p, "WL");
		avil_Wait_List--;
	}
}

	public void cancelTicket(int p_id){
		if(!passenger_data.containsKey(p_id)) {
			System.out.println("\nPassenger ID is Not Found\n");
		}
		else if(rac_List.contains(p_id)||wait_List.contains(p_id)) {
			PassengerPojo p = passenger_data.get(p_id);
			String p_alloted = p.alloted;
			System.out.println("\n----------Cancel Successfully------------------\n");
			if(p_alloted.equalsIgnoreCase("RAC")||p_alloted.equalsIgnoreCase("Berth"+"RAC")) {
				PassengerPojo passengerInRAC1 = passenger_data.get(p_id);
				rac_List.remove(passengerInRAC1.passengerId);
				passenger_data.remove(p_id);
				avil_RAC++;
				i--;
				
				if(wait_List.size() > 0) {
					PassengerPojo passengerInwl = passenger_data.get(wait_List.poll());
					wait_List.remove(passengerInwl.passengerId);
					if(p_alloted.equalsIgnoreCase("RAC")){
					passengerInwl.alloted = "RAC";
					}
					else {
						passengerInwl.alloted = "Berth"+"RAC";
					}
					rac_List.add(passengerInwl.passengerId);
					avil_Wait_List++;
					avil_RAC--;
				}
			}
			else if(p_alloted.equalsIgnoreCase("WL")) {
				wait_List.remove(p_id);
				passenger_data.remove(p_id);
				avil_Wait_List++;
			}
		}
		else {
			PassengerPojo p = passenger_data.get(p_id);
			String p_alloted = p.alloted;
			passenger_data.remove(p_id);
			System.out.println("\n----------Cancel Successfully------------------\n");
			if(p_alloted.equalsIgnoreCase("L")) {
				avil_Low_Berth++;
			}
			else if(p_alloted.equalsIgnoreCase("M")) {
				avil_Mid_Berth++;
			}
			else if(p_alloted.equalsIgnoreCase("U")) {
				avil_Upp_Berth++;
			}
			
			if(rac_List.size() > 0) {
				PassengerPojo passengerInRAC = passenger_data.get(rac_List.poll());
				rac_List.remove(passengerInRAC.passengerId);
				avil_RAC++;
				
				if(wait_List.size() > 0) {
					PassengerPojo passengerInwl = passenger_data.get(wait_List.poll());
					wait_List.remove(passengerInwl.passengerId);
					
					passengerInwl.alloted = "RAC";
					rac_List.add(passengerInwl.passengerId);
					avil_Wait_List++;
					avil_RAC--;
				}
				bookTicket(passengerInRAC);
			}
		}
	}
	public void printBookedTicket(PassengerPojo p, String allotedBerth) {
		p.alloted = allotedBerth;
		passenger_data.put(p.passengerId, p);
		System.out.println("Passenger Id : "+p.passengerId);
		System.out.println("Passenger Name : "+p.getName());
		System.out.println("Passenger Age : "+p.getAge());
		System.out.println("Passenger Gender : "+p.getGender());
		System.out.println("Alloted Berth : "+allotedBerth);
		System.out.println("\n----------Successfully Booked------------------\n");
	}
	public void printRacTicket(PassengerPojo p, String RACBerth) {
		p.alloted = RACBerth;
		passenger_data.put(p.passengerId, p);
		rac_List.add(p.passengerId);
		System.out.println("Passenger Id : "+p.passengerId);
		System.out.println("Passenger Name : "+p.getName());
		System.out.println("Passenger Age : "+p.getAge());
		System.out.println("Passenger Gender : "+p.getGender());
		System.out.println("Alloted Berth : "+RACBerth);
		System.out.println("\n----------Successfully Booked------------------\n");		
	}
	public void printWlTicket(PassengerPojo p, String WLBerth) {
		p.alloted = WLBerth;
		passenger_data.put(p.passengerId, p);
		wait_List.add(p.passengerId);
		System.out.println("Passenger Id : "+p.passengerId);
		System.out.println("Passenger Name : "+p.getName());
		System.out.println("Passenger Age : "+p.getAge());
		System.out.println("Passenger Gender : "+p.getGender());
		System.out.println("Alloted Berth : "+WLBerth);
		System.out.println("\n----------Successfully Booked------------------\n");		
	}
	
	public void availableTickets() {
		System.out.println("\nNumber of available Lower Berth Tickets is : "+avil_Low_Berth);
		System.out.println("Number of available Middle Berth Tickets is  : "+avil_Mid_Berth);
		System.out.println("Number of available Upper Berth Tickets is   : "+avil_Upp_Berth);
		System.out.println("Number of available RAC Berth Tickets is     : "+avil_RAC);
		System.out.println("Number of available Waiting LIst Tickets is  : "+avil_Wait_List);
		System.out.println("");
	}
	
	public void getPassengersDetails() {
		if(passenger_data.size() == 0) {
			System.out.println("\nNo Passenger Details Found\n");
		}
		else{
			for(PassengerPojo p : passenger_data.values()) {
				System.out.println("");
				System.out.println("Passenger Id           : "+p.passengerId);
				System.out.println("Passenger Name         : "+p.getName());
				System.out.println("Passenger Age          : "+p.getAge());
				System.out.println("Passenger Gender       : "+p.getGender());
				System.out.println("Passenger Child Name   : "+p.getChild_name());
				System.out.println("Passenger Child Age    : "+p.getChild_age());
				System.out.println("Passenger Child Gender : "+p.getChild_gender());
				System.out.println("Alloted Berth          : "+p.alloted);
				System.out.println("");
			}
			
			
		}
	}
}
