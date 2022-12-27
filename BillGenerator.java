//Originator class
public class BillGenerator {
    private String accountNumber;
    private int price;
    private String numberOfBeds;
    private String numberOfRoom;
    private String numberOfDays;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }
    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
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
        return numberOfDays;
    }

    //saveToMemento
    public Receipt saveCurrentTransaction(){
        return new Receipt(accountNumber,price,numberOfBeds,numberOfRoom,numberOfDays);
    }

    //getFromMemento
    public void getTransaction(Receipt receipt){

        accountNumber = receipt.getAccountNumber();
        price = receipt.getPrice();
        numberOfBeds = receipt.getNumberOfBeds();
        numberOfRoom= receipt.getNumberOfRoom();
        numberOfDays = receipt.getNumberOfDays();

    }
}
