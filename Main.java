import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {  // main class
	
    static AmountOfReservations amountOfReservationsObject = AmountOfReservations.getAmountOfReservationsInstance();
    static int amt = AmountOfReservations.getAmountOfReservationsInstance().getAmount();
    static int noOfRoom =100;

    private List<RoomWatcher> roomWatchers = new ArrayList<>();

    public void addWatcher(RoomWatcher rw){
        roomWatchers.add(rw);
    }
    public void removeWatcher(RoomWatcher rw){
        roomWatchers.remove(rw);
    }
    public void notify (int numOfRoom){
        for(RoomWatcher rw: roomWatchers){
            rw.notify(numOfRoom);
        }
    }

    public static BillGenerator  transaction = new BillGenerator();
    public static TransactionDataBase transactionDataBase = new TransactionDataBase();

    
    
    public static void loadCustomer(myBST<Customer> tree, Customer[] AcctDB) {  // read customer text file data.txt

        try {
            File fileReader = new File("data.txt");
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String customer[] = line.split(",");
                amt++;
                amountOfReservationsObject.setAmount(amt);
                String first = (customer[0].trim());
                String last = (customer[1].trim());
                String accountNumber = (customer[2].trim());
                String hotelName = (customer[3].trim());
                int numberOfDay = Integer.parseInt(customer[4].trim());
                int numberOfRoom = Integer.parseInt(customer[5].trim());
                int numberOfBeds = Integer.parseInt(customer[6].trim());
                int price = Integer.parseInt(customer[7].trim());
                String payment = (customer[8].trim());
                double otherExpense = Double.parseDouble(customer[9]);
                transaction.setNumberOfBeds(customer[6].trim());
                transaction.setPrice(price);
                transaction.setNumberOfRoom(customer[5].trim());
                transaction.setNumberOfDays(customer[4].trim());
                transaction.setAccountNumber(accountNumber);

                noOfRoom = noOfRoom - numberOfRoom;

                Customer customerList = new Customer.customerBuilder()
                        .setFirstName(first)
                        .setLastName(last)
                        .setAccountNumber(accountNumber)
                        .setHotelName(hotelName)
                        .setNumberOfDay(numberOfDay)
                        .setNumberOfRoom(numberOfRoom)
                        .setNumberOfBed(numberOfBeds)
                        .setPrice(price)
                        .setPayment(payment)
                        .setOtherExpense(otherExpense)
                        .build();

                tree.insert(customerList);
                transactionDataBase.addTransaction(transaction.saveCurrentTransaction());
                AcctDB[Integer.parseInt(customerList.getAccountNumber())] = customerList;


            }
        }

        catch (IOException e) {
            System.out.println("File not found!");
        }
        tree.inorder();
    }



    public static String selectCases(String number, int error) {  // select cases

        number = JOptionPane.showInputDialog(null, "1. Make a Hotel Reservation." + "\n2. See reservation Details."
                + "\n3. Edit a Reservation." + "\n4. Cancel a Hotel Reservation." + "\n5. Sandwich and Drink Store" + "\n6. Exit the program.\n" +
                "\n\n\n Current Number Of Reservations: " + amt +"\n\n\nPlease enter a number: ");

        error = Integer.parseInt(number);
        if(error < 1 || error > 6) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Input from the display!");
        }
        return number;
    }



    public static String hotelName(int hotel) {  // hotelName cases

        String hotelN="";

        if(hotel == 1) {
            hotelN = "New York Hotel";
        }

        else if(hotel == 2) {
            hotelN = "Los Angeles Hotel";
        }

        else if(hotel == 3) {
            hotelN = "Seattle Hotel";
        }

        return hotelN;
    }



    public static int price (int day, int room, int bed) {  // calculate price of day, room, and bed

        int eachDay = 80 * day;
        int eachRoom = 20 * room;
        int eachBed = 10 * bed;

        int price = 0;
        price = eachDay + eachRoom + eachBed;

        return price;
    }



    public static String paymentMethod(int payment) {  // payment methods

        String paymentMethod="";

        if(payment == 1) {
            paymentMethod = "Credit Card";
        }

        else if(payment == 2) {
            paymentMethod = "Google Pay";
        }

        else if(payment == 3) {
            paymentMethod = "Apple Pay";
        }

        return paymentMethod;
    }



    // update data.txt with new record
    public static void updateFile(String first, String last, String acctNo, String hotel, int numberOfDayCount, int numberOfRoom, int bedNumber, int totalPrice, String string2, double otherExpense) {

        try (FileWriter f = new FileWriter("data.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {

            p.println(first + ", " + last + ", " + acctNo + ", " + hotel + ", " + numberOfDayCount + ", " + numberOfRoom + ", " + bedNumber + ", " + totalPrice + ", " + string2 + ", " + otherExpense);
            transactionDataBase.addTransaction(transaction.saveCurrentTransaction());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }



    // case 1
    public static void createReservation(myBST<Customer> myTree, Customer[] AcctDB, int noOfRoom, RoomWatcher rw) {  // create a reservation

        JOptionPane.showMessageDialog(null, "Be sure to capitalize first letter of both first and last name!");
        String first, last, acctNo, amount, numberOfDay, hotel, room, bed, payment;

        first = JOptionPane.showInputDialog(null, "Please enter first name ");
        last = JOptionPane.showInputDialog(null, "Please enter last name ");

        acctNo = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number ");
        while(acctNo.length() != 8 || acctNo.length() > 8) {
            acctNo = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number again ");
        }

        int hotelNumber = 0;
        hotel = JOptionPane.showInputDialog(null, "Please choose one of the 3 options for Hotel Location. (Press Number Key)\n 1) New York Hotel \n 2) Los Angeles Hotel \n 3) Seattle Hotel");
        hotelNumber = Integer.parseInt(hotel);
        while(hotelNumber <= 0 || hotelNumber > 3) {
            hotel = JOptionPane.showInputDialog(null, "Please choose one of the 3 options for Hotel Location. (Press Number Key)\n 1) New York Hotel \n 2) Los Angeles Hotel \n 3) Seattle Hotel");
            hotelNumber = Integer.parseInt(hotel);
        }
        hotelName(hotelNumber);  // hotel name cases

        numberOfDay = JOptionPane.showInputDialog(null, "How many days would you be staying? ");
        int numberOfDayCount = Integer.parseInt(numberOfDay);

        room = JOptionPane.showInputDialog(null, "How many room do you Require?");
        int numberOfRoom = Integer.parseInt(room);

        int bedNumber = 0;
        bed = JOptionPane.showInputDialog(null, "How many bed per room you Require? \n(Max is 2 per room)");
        bedNumber = Integer.parseInt(bed);
        while(bedNumber <=0 || bedNumber > 2) {
            bed = JOptionPane.showInputDialog(null, "How many bed per room you Require? \n(Max is 2 per room)");
            bedNumber = Integer.parseInt(bed);
        }

        int totalPrice = price(numberOfDayCount, numberOfRoom, bedNumber);  // calculate price

        double otherExpense =0.00;
        int paymentNumber = 0;
        payment = JOptionPane.showInputDialog(null, "Your total price is $" + totalPrice + "\n\nMethod Of payments (Numerical Input)\n 1) Credit Card \n 2) Google Pay \n 3) Apple Pay");
        paymentNumber = Integer.parseInt(payment);
        while(paymentNumber <= 0 || paymentNumber > 3) {
            payment = JOptionPane.showInputDialog(null, "Your total price is $" + totalPrice + "\n\nMethod Of payments (Numerical Input)\n 1) Credit Card \n 2) Google Pay \n 3) Apple Pay");
            paymentNumber = Integer.parseInt(payment);

        }

        transaction.setPrice(totalPrice);
        transaction.setNumberOfBeds(bed);
        transaction.setNumberOfRoom(room);
        transaction.setAccountNumber(acctNo);
        transaction.setNumberOfDays(numberOfDay);


        if(Context.checkBalance(paymentNumber, totalPrice)) {
            Customer user = new Customer.customerBuilder()
                    .setFirstName(first)
                    .setLastName(last)
                    .setAccountNumber(acctNo)
                    .setHotelName(hotelName(hotelNumber))
                    .setNumberOfDay(numberOfDayCount)
                    .setNumberOfRoom(numberOfRoom)
                    .setNumberOfBed(bedNumber)
                    .setPrice(totalPrice)
                    .setPayment(paymentMethod(paymentNumber))
                    .build();

            myTree.insert(user);
            AcctDB[Integer.parseInt(user.getAccountNumber())] = user;
            JOptionPane.showMessageDialog(null, "Your reservation has been Created! \n Get to the desired Location");  // new account has been created
            noOfRoom = noOfRoom -  numberOfRoom;
            rw.notify(noOfRoom);

        }else {
            JOptionPane.showMessageDialog(null, "Insufficient Balance!\nYour reservation failed!\nHave a good day");  // new account create failed
        }

        amt++;
        amountOfReservationsObject.setAmount(amt);

        System.out.print("\n");
        myTree.inorder();

        // updating data.txt with new record
        updateFile(first, last, acctNo, hotelName(hotelNumber), numberOfDayCount, numberOfRoom, bedNumber, totalPrice, paymentMethod(paymentNumber), otherExpense);

    }



    // case 2
    public static void reservationDetail(myBST<Customer> myTree, Customer[] AcctDB) {  // reservation detail
        String acctNo;

        acctNo = JOptionPane.showInputDialog(null, "Please enter your account number: ");

        while(acctNo.length() != 8 || acctNo.length() > 8) {
            acctNo = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number again ");
        }

        //restoring transaction
        if(transactionDataBase.getDetails(acctNo)==null)
        {
            System.out.println("There is no record in a Database for account number " + acctNo);
        }
        else{
            transaction.getTransaction(transactionDataBase.getDetails(acctNo));
            System.out.println(transaction.toString());
        }

        JOptionPane.showMessageDialog(null, "Your reservation detail\n\n" + "Name: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getFirst()
                + " " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getLast() + "\nAccount Number: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getAccountNumber() +
                "\nHotel Name: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getHotelName() + "\nNumber of Days: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getNumberOfDay() +
                "\nNumber of Room: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getNumberOfRoom() + "\nNumber of Bed: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getNumberOfBed() +
                "\nPrice: $" + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getPrice() + "\nPayment Type: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getPayment()+
                "\nOther Expense: " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense());  // new account has been created);
    }



    // case 3
    public static void editReservation(myBST<Customer> myTree, Customer[] AcctDB, int noOfRoom, RoomWatcher rw) {  // edit a reservation

        String acctNo3;

        acctNo3 = JOptionPane.showInputDialog(null, "Please enter your account number: ");

        while(acctNo3.length() != 8 || acctNo3.length() > 8) {
            acctNo3 = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number again ");
        }

        String option, newHotel, oldHotel;
        int  nDay=0,oldDay, oldRoom, newRoom=0, newBed=0, oldBed;

        option = JOptionPane.showInputDialog(null, "Please choose one of the 3 options for change. (Press Number Key)\n 1) Change Hotel \n 2) Change Room \n 3) Change stay day \n 4) Change number of bed");
        int n = Integer.parseInt(option);
        while(n <= 0 || n > 4) {
            option = JOptionPane.showInputDialog(null, "Please choose one of the 3 options for change. (Press Number Key)\n 1) Change Hotel \n 2) Change Room \n 3) Change stay day \n 4) Change number of bed");
            n = Integer.parseInt(option);
        }

        oldHotel = myTree.search(AcctDB[Integer.parseInt(acctNo3)]).getHotelName();
        oldDay =  myTree.search(AcctDB[Integer.parseInt(acctNo3)]).getNumberOfDay();
        oldRoom = myTree.search(AcctDB[Integer.parseInt(acctNo3)]).getNumberOfRoom();
        oldBed = myTree.search(AcctDB[Integer.parseInt(acctNo3)]).getNumberOfBed();

        int newPrice = 0;

        if( n == 1){
            String hNum = JOptionPane.showInputDialog(null, "Please choose one of the 3 options for Hotel Location. (Press Number Key)\n 1) New York Hotel \n 2) Los Angeles Hotel \n 3) Seattle Hotel");
            newHotel = hotelName(Integer.parseInt(hNum));
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setHotelName(newHotel);
            JOptionPane.showMessageDialog(null,"hotel changed from " + oldHotel + " to " + newHotel);
        }

        else if ( n == 2){
            newRoom = Integer.parseInt( JOptionPane.showInputDialog(null, "How many room do you Require?"));
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setNumberOfRoom(newRoom);
            newPrice = price(oldDay, newRoom, oldBed);
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setPrice(newPrice);
            JOptionPane.showMessageDialog(null,"Room changed form " + oldRoom + " to " + newRoom + "\n\n Your new Price is $" + newPrice);

        }

        else if(n==3){
            String days =JOptionPane.showInputDialog(null, "How many days would you be staying? ");
            nDay = Integer.parseInt(days);
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setNumberOfDay(nDay);
            newPrice = price(nDay, oldRoom, oldBed);
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setPrice(newPrice);
            JOptionPane.showMessageDialog(null,"Days changed form " + oldDay + " to " + nDay + "\n\n Your new Price is $" + newPrice);

            transaction.setNumberOfDays(days);
            transaction.setPrice(newPrice);
        }

        else if(n==4){
            String beds = JOptionPane.showInputDialog(null, "How many bed per room? (Max is 2 per room) ");
            newBed = Integer.parseInt(beds);
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setNumberOfBed(newBed);
            newPrice = price(oldDay, oldRoom, newBed);
            myTree.search(AcctDB[Integer.parseInt(acctNo3)]).setPrice(newPrice);
            JOptionPane.showMessageDialog(null,"Bed per room changed form " + oldBed + " to " + newBed + "\n\n Your new Price is $" + newPrice);

            transaction.setNumberOfBeds(beds);
            transaction.setPrice(newPrice);

        }

        else {
            JOptionPane.showMessageDialog(null, "Wrong option!");
        }

        System.out.print("\n");
        myTree.inorder();
        noOfRoom = noOfRoom + oldRoom - newRoom;
        rw.notify(noOfRoom);
    }



    // case 4
    public static void deleteReservation(myBST<Customer> myTree, Customer[] AcctDB, int noOfRoom, RoomWatcher rw) {  // cancel a reservation

        String acctNo;

        acctNo = JOptionPane.showInputDialog(null, "Please enter your account number: ");
        while(acctNo.length() != 8 || acctNo.length() > 8) {
            acctNo = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number again ");
        }


        if(transactionDataBase.getDetails(acctNo)!=null)
        {
            transactionDataBase.deleteTransaction(acctNo);
        }



        int roomNum = myTree.search(AcctDB[Integer.parseInt(acctNo)]).getNumberOfRoom();
        noOfRoom += roomNum ;
        rw.notify(noOfRoom);
        myTree.delete(AcctDB[Integer.parseInt(acctNo)]);
        JOptionPane.showMessageDialog(null, "The reservation has been canceled. \nDo visit us again!");
        amt--;
        amountOfReservationsObject.setAmount(amt);
        System.out.print("\n");
        myTree.inorder();
    }

    
    
    // case 5
    public static void store(myBST<Customer> myTree, Customer[] AcctDB) {  // snack and drinks
        String acctNo = JOptionPane.showInputDialog(null, "Please enter your account number: ");
        while((acctNo.length() != 8 || acctNo.length() > 8)||myTree.search(AcctDB[Integer.parseInt(acctNo)])==null ) {
            acctNo = JOptionPane.showInputDialog(null, "Please enter 8 digit account Number again ");
        }


        int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "SANDWICH STORE : Which options would you like?\n 1)Meat,Cheese,Mayo,Ketchup + Drink\n " +
                "2) Vegetable, Tofu, Mayo + Drink\n 3) Meat, Ketchup + Drink 4) Vegetable, Mayo+ Drink \n Type 111 To Exit" ));  // items and prices
        while(choice <1 || choice > 5){
            if(choice == 111) break;
            choice = Integer.parseInt(JOptionPane.showInputDialog(null, "SANDWICH STORE : Which options would you like?\n 1)Meat,Cheese,Mayo,Ketchup + Drink\n " +
                    "2) Vegetable, Tofu, Mayo + Drink\n 3) Meat, Ketchup + Drink 4) Vegetable, Mayo+ Drink \n Type 111 To Exit\n" ));
        }

        switch (choice){

            case 1:
                double cost  = myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense();

                Item item = new Sandwich(new Meat(new Cheese(new Mayo(new Ketchup(new Drink(null))))));
                myTree.search(AcctDB[Integer.parseInt(acctNo)]).setOtherExpense(cost+item.cost());
                JOptionPane.showMessageDialog(null,"Your Sandwich and Drink Cost :  " + (item.cost())
                        + " and your Total Municipal Expense is " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense());
                break;
            case 2:
                cost  = myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense();

                Item item2 = new Sandwich(new Vegetables(new Tofu(new Mayo(new Drink(null)))));
                myTree.search(AcctDB[Integer.parseInt(acctNo)]).setOtherExpense(cost+item2.cost());

                JOptionPane.showMessageDialog(null,"Your Sandwich and Drink Cost :  " + (item2.cost())
                        + " and your Total Municipal Expense is " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense());
                break;
            case 3:
                cost  = myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense();

                Item item3 = new Sandwich(new Meat(new Ketchup(new Drink(null))));
                myTree.search(AcctDB[Integer.parseInt(acctNo)]).setOtherExpense(cost+item3.cost());

                JOptionPane.showMessageDialog(null,"Your Sandwich and Drink Cost :  " + (item3.cost())
                        + " and your Total Municipal Expense is " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense());
                break;
            case 4:
                cost  = myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense();

                Item item4 = new Sandwich(new Vegetables(new Mayo(new Drink(null))));
                myTree.search(AcctDB[Integer.parseInt(acctNo)]).setOtherExpense(cost+item4.cost());
                JOptionPane.showMessageDialog(null,"Your Sandwich and Drink Cost :  " + (item4.cost())
                        + " and your Total Municipal Expense is " + myTree.search(AcctDB[Integer.parseInt(acctNo)]).getOtherExpense());
                break;
        }

    }

    
    
    // case 6
    public static void printFile(bstNode t, FileWriter list) throws Exception {  // end program and print it to log.txt

        myBST<Customer> myTree = new myBST<Customer>();

        if(t != null) {
            list.write(t.data.toString());
            printFile(t.leftChild, list);
            printFile(t.rightChild, list);

        }
    }


    
    // main
    public static void main (String[] args) throws Exception {

        Customer[] AcctDB = new Customer[99999999];
        myBST<Customer> myTree = new myBST<Customer>();
        loadCustomer(myTree, AcctDB);

        RoomWatcher rw = new RoomWatcher() {
            @Override
            public void notify(int roomNum) {

                System.out.print("\nThe number of empty rooms now is " + roomNum + "\n");
            }
        };


        String number = "";
        int error = 0;

        while(number != "5") {

            number = selectCases(number, error);  // select cases method

            switch (number) {


                case "1":  // case 1 create a new reservation

                    createReservation(myTree, AcctDB, noOfRoom, rw);
                    break;


                case "2":  // case 2 reservation detail

                    reservationDetail(myTree, AcctDB);
                    break;


                case "3": // case 3 edit Reservation

                    editReservation(myTree, AcctDB, noOfRoom, rw);
                    break;


                case "4":  // case 4 delete a reservation

                    deleteReservation(myTree, AcctDB, noOfRoom, rw);
                    break;

                case "5":  // case 5 Buy Snacks Or Drinks
                    store(myTree, AcctDB);

                    break;


                case "6":  // case 6 print everything to log.txt

                    FileWriter EndList = new FileWriter("log.txt");

                    printFile(myTree.root, EndList);
                    EndList.close();

                    return;

            }  // switch case
        }  //  while loop
    }  // driver class
}  // main
