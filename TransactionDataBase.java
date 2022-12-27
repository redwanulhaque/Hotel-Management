import java.util.ArrayList;
import java.util.List;

//CareTaker Class
public class TransactionDataBase {

    private List<Receipt> transactionList = new ArrayList<Receipt>();
    public void addTransaction(Receipt receipt){
        transactionList.add(receipt);
    }

    public Receipt getTransaction(int index)
    {
        return transactionList.get(index);
    }

    public Receipt getDetails(String accountNumber){

        for(int i=0; i<transactionList.size(); i++)
        {
            if(transactionList.size()==0)
            {

                return null;
            }
            if(transactionList.get(i).getAccountNumber().equals(accountNumber))
            {
                return getTransaction(i);
            }
        }
        return null;
    }

    public void deleteTransaction(String accountNumber){

        transactionList.remove(getDetails(accountNumber));

    }
}
