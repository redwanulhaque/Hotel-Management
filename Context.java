public class Context {
    private PaymentStrategy paymentStrategy;

    public Context(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
    public boolean executeStrategy(int payment, int amount){
        return paymentStrategy.strategyOperation(payment, amount);
    }

    public static class OperationGooglePay  implements PaymentStrategy{
        @Override
        public boolean strategyOperation(int payment, int amount) {
            int balance = 2000;
            return balance >= amount;
        }
    }

    public static class OperationCreditCard implements PaymentStrategy{
        @Override
        public boolean strategyOperation(int CreditCard, int amount) {
            int creditBalance = 10000;
            return creditBalance >= amount;
        }
    }
    public static class OperationApplePay implements PaymentStrategy{
        @Override
        public boolean strategyOperation(int payment, int amount) {
            int balance = 3000;
            return balance>= amount;
        }
    }
    public static boolean checkBalance(int paymentNumber, int amount) {
        if(paymentNumber == 1) {
            Context context = new Context(new OperationCreditCard());
            if(context.executeStrategy(paymentNumber, amount)) return true;
        }else if(paymentNumber == 2) {
            Context context = new Context(new OperationGooglePay());
            if(context.executeStrategy(paymentNumber, amount)) return true;
        }else {
            Context context = new Context(new OperationApplePay());
            if(context.executeStrategy(paymentNumber, amount)) return true;
        }
        return false;
    }
}
