package HotelManagement;

public class HotelData {
	private String HotelName;
    private int RoomAvailable;
    private String city;
    private int rating;
    private double cost;

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public int getRoomAvailable() {
        return RoomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        RoomAvailable = roomAvailable;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\nHotelData{" +
                "\nHotelName = '" + HotelName + '\'' +
                ",\nRoomAvailable = " + RoomAvailable +
                ",\ncity = '" + city + '\'' +
                ",\nrating = " + rating +
                ",\ncost = " + cost +"\n"+
                '}'+"\n";
    }
}
