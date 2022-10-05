package HotelManagement;

import java.util.*;

public class UserInterface {
	public static void main(String args[]){
        LogicLayer logic = new LogicLayer();
        Scanner scan = new Scanner(System.in);
        boolean mainRepeat =  true;
        while(mainRepeat) {
            System.out.println("\n1. Admin Portal");
            System.out.println("2. Book Hotel");
            System.out.println("3. Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("\nEnter Your UserId");
                    String UserName = scan.next();
                    System.out.println("\nEnter Password");
                    String password = scan.next();
                    if (logic.adminCheck(UserName, password)) {
                        System.out.println("\nLogin Successfully\n");
                        boolean adminRepeat = true;
                        while(adminRepeat) {
                            System.out.println("\n1. Add Hotels");
                            System.out.println("2. See User Booking details");
                            System.out.println("3. Back");
                            int adminChoice = scan.nextInt();
                            if(adminChoice == 1){
                                System.out.println("\nEnter City");
                                String city = scan.next().toLowerCase();
                                System.out.println("\nEnter hotel name");
                                String hotelName = scan.next().toLowerCase();
                                System.out.println("\nEnter hotel rating");
                                int rating = scan.nextInt();
                                System.out.println("\nEnter available Rooms");
                                int availableRooms = scan.nextInt();
                                System.out.println("\nEnter Cost per day");
                                double cost = scan.nextDouble();
                                HotelData hotel = new HotelData();
                                hotel.setCity(city);
                                hotel.setRating(rating);
                                hotel.setRoomAvailable(availableRooms);
                                hotel.setCost(cost);
                                hotel.setHotelName(hotelName);

                                String message = logic.addHotel(hotel);
                                System.out.println(message);
                            }
                            else if(adminChoice == 2){
                               HashMap<Integer,UserData> userMap = logic.bookingDetails();
                                for(Map.Entry<Integer,UserData> e : userMap.entrySet()){
                                    System.out.println(e.getKey()+"   "+e.getValue());
                                }
                            }
                            else if(adminChoice == 3){
                                adminRepeat = false;
                            }
                        }
                    } else {
                        System.out.println("\nPlease Enter Correct Information\n");
                    }
                    break;
                }
                case 2: {
                    boolean userRepeat = true;
                    while(userRepeat) {
                        System.out.println("\n1. Sort Hotel By Name");
                        System.out.println("2. Sort Hotel By Rating");
                        System.out.println("3. Sort Hotel Available Rooms");
                        System.out.println("4. Search Hotel For a Location");
                        System.out.println("5. Book Hotel");
                        System.out.println("6. Back");
                        int userChoice = scan.nextInt();
                        switch(userChoice) {
                        case 1:
                            List<Map.Entry<String,HotelData>> nameList = logic.sortByName();
                            for(Map.Entry<String,HotelData> e : nameList){
                                System.out.println(e);
                            }
                            break;
                        case 2:
                            List<Map.Entry<String,HotelData>> ratingList = logic.sortByRating();
                            for(Map.Entry<String,HotelData> e : ratingList){
                                System.out.println(e);
                            }
                            break;
                        case 3:
                            List<Map.Entry<String,HotelData>> roomList = logic.sortByAvailableRoom();
                            for(Map.Entry<String,HotelData> e : roomList){
                                System.out.println(e);
                            }
                            break;
                        case  4:
                            System.out.println("\nEnter Location You Want to Search : ");
                            String city = scan.next();
                            logic.searchByLocation(city);                            
                            break;
                        case 5:
                            System.out.println("Please Check the availability of the hotel and then book");
                            System.out.println("Enter Your name : ");
                            String name = scan.next();
                            System.out.println("Enter Hotel Name : ");
                            String hotelName = scan.next().toLowerCase();
                            if(logic.hotelCheck(hotelName)){
                            	if(logic.isRoomFull(logic.hotelDetails.get(hotelName))) {
	                                UserData user = logic.bookHotel(hotelName,name);
	                                System.out.println("Hotel Booked  Successfully"+"\n"+"Your UserId is : "+user.getUserId()+"\n"+"Hotel Name : "+user.getHotelName()+"\n"+"Booking Cost : "+user.getCost());
                            	}
                            	else {
                            		System.out.println("\nRoom is Full\n");
                            	}
                            }
                            else{
                                System.out.println("\nYou Entered Details is wrong Please check once again...\n");
                            }
                            break;
                        case 6:
                            userRepeat = false;
                            break;
                        default:
                        	System.out.println("\nEnter Valid input...\n");
                        }
                    }
                    break;
                }
                case 3: {
                    mainRepeat = false;
                    scan.close();
                    break;
                }
            }
        }
    }
}
