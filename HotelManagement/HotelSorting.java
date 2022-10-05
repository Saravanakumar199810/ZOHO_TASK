package HotelManagement;

import java.util.*;

public class HotelSorting {
	public List<Map.Entry<String,HotelData>> sortByName(HashMap<String,HotelData> hotelDetails){
        List<Map.Entry<String,HotelData>> nameList = new ArrayList<>(hotelDetails.entrySet());
        
        Collections.sort(nameList, new Comparator<Map.Entry<String, HotelData>>() {
            @Override
            public int compare(Map.Entry<String, HotelData> o1, Map.Entry<String, HotelData> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return nameList;
    }
    public List<Map.Entry<String,HotelData>> sortByRating(HashMap<String,HotelData> hotelDetails){
        List<Map.Entry<String,HotelData>> ratingList = new ArrayList<>(hotelDetails.entrySet());
        Collections.sort(ratingList, new Comparator<Map.Entry<String, HotelData>>() {
            @Override
            public int compare(Map.Entry<String, HotelData> o1, Map.Entry<String, HotelData> o2) {
                return o2.getValue().getRating()-(o1.getValue().getRating());
            }
        });
        return ratingList;
    }
    public List<Map.Entry<String,HotelData>> sortByAvailableRoom(HashMap<String,HotelData> hotelDetails){
        List<Map.Entry<String,HotelData>> roomList = new ArrayList<>(hotelDetails.entrySet());
        Collections.sort(roomList, new Comparator<Map.Entry<String, HotelData>>() {
            @Override
            public int compare(Map.Entry<String, HotelData> o1, Map.Entry<String, HotelData> o2) {
                return o2.getValue().getRoomAvailable()-(o1.getValue().getRoomAvailable());
            }
        });
        return roomList;
    }
}
