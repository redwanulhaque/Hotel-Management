//Memento class
public class Receipt {

    private String accountNumber;
    private int price;
    private String numberOfBeds;
    private String numberOfRoom;
    private String NumberOfDays;

    public Receipt(String accountNumber, int price, String numberOfBeds, String numberOfRoom, String NumberOfDays) {

        this.accountNumber = accountNumber;
        this.price = price;
        this.numberOfBeds = numberOfBeds;
        this.numberOfRoom = numberOfRoom;
        this.NumberOfDays = NumberOfDays;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public int getPrice() {
        return price;
    }
    public String getNumberOfBeds() {
        return numberOfBeds;
    }
    public String getNumberOfRoom() {
        return numberOfRoom;
    }
    public String getNumberOfDays() {
        return NumberOfDays;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "paymentType='" + accountNumber + '\'' +
                ", price=" + price +
                ", numberOfBeds='" + numberOfBeds + '\'' +
                ", numberOfRoom='" + numberOfRoom + '\'' +
                ", NumberOfDays='" + NumberOfDays + '\'' +
                '}';
    }
}
