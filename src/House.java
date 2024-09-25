public class House {
    private String id;
    private int apartment_number;
    private int square;
    private int floor;
    private int number_of_rooms;
    private String street;

    //Конструктор
    public House(String id,int apartment_number,int square,
                 int floor,int number_of_rooms,String street) {
        this.id = id;
        this.apartment_number = apartment_number;
        this.square = square;
        this.floor = floor;
        this.number_of_rooms = number_of_rooms;
        this.street = street;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getApartment_number() {
        return apartment_number;
    }
    public void setApartment_number(int apartment_number) {
        this.apartment_number = apartment_number;
    }
    public int getSquare() {
        return square;
    }
    public void setSquare(int square) {
        this.square = square;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public int getNumber_of_rooms() {
        return number_of_rooms;
    }
    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return"HOUSE:"+
                "\n"+" ID="+id+"\n"+" Apartment Number="+apartment_number+
                "\n"+" Square="+square+"\n"+" Floor="+floor+
                "\n"+" Number of Rooms="+number_of_rooms
                +"\n"+" Street="+street;
    }
}