public class AmountOfReservations {

    //create an object of Amount of Customers
    private static AmountOfReservations amountOfCustomers = new AmountOfReservations();
    private int amount;
    private AmountOfReservations(){
        amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static AmountOfReservations getAmountOfReservationsInstance(){
        return amountOfCustomers;
    }
}

