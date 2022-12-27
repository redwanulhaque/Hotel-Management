import java.util.ArrayList;
import java.util.List;

public class Customer implements Comparable<Customer>, RoomWatcher{
    private String first, last, accountNumber, hotelName, payment;
    private double otherExpense;
    private int data;
    private int numberOfDay, numberOfRoom, numberOfBed, price;

    private List<RoomWatcher> roomWatcher = new ArrayList<>();

    public void addWatcher(RoomWatcher cw){
        roomWatcher.add(cw);
    }

    public void removeWatch(RoomWatcher cw){
        roomWatcher.remove(cw);
    }

    public void notify(int roomNum) {

        System.out.print("\nThe number of empty rooms now is " + roomNum + "\n");
    }


    public Customer(customerBuilder builder) {
        this.first = builder.first;
        this.last = builder.last;
        this.accountNumber = builder.accountNumber;
        this.numberOfDay = builder.numberOfDay;
        this.hotelName = builder.hotelName;
        this.numberOfRoom = builder.numberOfRoom;
        this.numberOfBed = builder.numberOfBed;
        this.price = builder.price;
        this.payment = builder.payment;
        this.otherExpense = builder.otherExpense;
    }

    public double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(double otherExpense) {
        this.otherExpense = otherExpense;
    }

    public int toInt(){
        return data;
    }

    // first name
    public String getFirst() {
        return first;
    }

    // last name
    public String getLast() {
        return last;
    }

    // account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // people
    public int getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    // hotel name
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    // number of room
    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    // number of bed
    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    // price
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // payment
    public String getPayment() {
        return payment;
    }

    public String toString(){  // printing the bank info to toSting method
        return "[Name: " + first + " " + last + "; Account Number: " + accountNumber + "; Hotel Name: " + hotelName + "; Number of Days: " + numberOfDay +
                "; Number of Room: " + numberOfRoom + "; Number of Bed: " + numberOfBed + "; Price: $" + price +  "; Payment: " + payment + "]\n";
    }

    @Override
    public int compareTo(Customer other) {  // Compare customer type

        int value = this.last.compareTo(other.last);  // equality by last name

        if(value != 0) return value;

        return this.first.compareTo(first); // equality by first name
    }




    // builder pattern
    public static class customerBuilder {

        private String first, last, accountNumber, hotelName, payment;
        private int numberOfDay, numberOfRoom, numberOfBed, price;
        private double otherExpense;


        public customerBuilder setOtherExpense(double otherExpense) {
            this.otherExpense = otherExpense;
            return this;
        }

        public customerBuilder setFirstName(String firstName) {
            this.first = firstName;
            return this;
        }

        public customerBuilder setLastName(String lastName) {
            this.last = lastName;
            return this;
        }

        public customerBuilder setAccountNumber (String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public customerBuilder setHotelName (String hotelName) {
            this.hotelName = hotelName;
            return this;
        }

        public customerBuilder setNumberOfDay (int numberOfDay) {
            this.numberOfDay = numberOfDay;
            return this;
        }

        public customerBuilder setNumberOfRoom (int numberOfRoom) {
            this.numberOfRoom = numberOfRoom;
            return this;
        }

        public customerBuilder setNumberOfBed (int numberOfBed) {
            this.numberOfBed = numberOfBed;
            return this;
        }

        public customerBuilder setPrice (int price) {
            this.price = price;
            return this;
        }

        public customerBuilder setPayment (String payment) {
            this.payment = payment;
            return this;
        }

        public Customer build() {
            Customer customer =  new Customer(this);
            return customer;
        }
    }
}