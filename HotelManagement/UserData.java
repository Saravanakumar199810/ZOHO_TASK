package HotelManagement;

public class UserData {
	private String name;
    private int UserId;
    private String HotelName;
    private double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\nUserData{" +
                "\nname = '" + name + '\'' +
                ",\nUserId = " + UserId +
                ",\nHotelName = '" + HotelName + '\'' +
                ",\ncost = " + cost +"\n"+
                '}'+"\n";
    }
}
