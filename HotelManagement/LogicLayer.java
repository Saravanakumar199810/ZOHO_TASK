package HotelManagement;

import java.util.*;

public class LogicLayer {
	String adminName = "2";
    String passCode = "A";
    HashMap<String, HotelData> hotelDetails = new HashMap<>();
    HashMap<Integer,UserData> userDetails = new HashMap<>();
    HotelSorting sorting = new HotelSorting();
    int userId = 100;
    public boolean adminCheck(String userName, String password) {
        if(userName.equals(adminName) && password.equals(passCode)){
            return true;
        }
        return false;
    }

    public String addHotel(HotelData hotel) {
        String hotelName = hotel.getHotelName();
        hotelDetails.put(hotelName,hotel);
         return "\nHotel Added Successfully\n";
    }
    public List<Map.Entry<String,HotelData>> sortByName(){
        List<Map.Entry<String,HotelData>> nameList= sorting.sortByName(hotelDetails);
        return nameList;
    }
    public List<Map.Entry<String,HotelData>> sortByRating(){
        List<Map.Entry<String,HotelData>> ratingList= sorting.sortByRating(hotelDetails);
        return ratingList;
    }
    public List<Map.Entry<String,HotelData>> sortByAvailableRoom(){
        List<Map.Entry<String,HotelData>> roomList= sorting.sortByAvailableRoom(hotelDetails);
        return roomList;
    }
    public void searchByLocation(String city){
    	System.out.println();
        System.out.printf("%-17s%-16s%-18s%-8s%-12s\n","HotelName","AvailableRooms","Location","Rating","RatePerDay");
        for(Map.Entry<String,HotelData> e : hotelDetails.entrySet()){
            if(e.getValue().getCity().equals(city)){
                System.out.printf("%-17s%-16d%-18s%-8d%-12.2f\n",e.getValue().getHotelName(),e.getValue().getRoomAvailable(),e.getValue().getCity(),e.getValue().getRating(),e.getValue().getCost());
            }
        }
        
    }

    public boolean hotelCheck(String hotelName) {
        if(hotelDetails.containsKey(hotelName)){
            return true;
        }
        else {
        	System.out.println("\nThere is No Hotel Found...\n");
        }
        return false;
    }

    public UserData bookHotel(String hotelName, String name) {
        HotelData hotel = hotelDetails.get(hotelName);
        double cost = hotel.getCost();
        int room = hotel.getRoomAvailable();
        room = room - 1;
        hotel.setRoomAvailable(room);
        hotelDetails.put(hotelName,hotel);

        UserData user = new UserData();
        user.setName(name);
        user.setUserId(userId);
        user.setHotelName(hotelName);
        user.setCost(cost);
        userDetails.put(userId,user);
        userId++;
        return user;
    }

    public HashMap<Integer, UserData> bookingDetails() {
        return userDetails;
    }
    
    public boolean isRoomFull(HotelData hd) {
    	if(hd.getRoomAvailable()==0) {
    		return false;
    	}
    	return true;
    }
}
