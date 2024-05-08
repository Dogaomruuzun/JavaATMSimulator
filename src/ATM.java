public class ATM 
{  
   public static final int CHECKING = 1;
   public static final int SAVINGS = 2;

   private int state;
   private int customerNumber;
   private Customer currentCustomer;
   private BankAccount currentAccount;
   private Bank theBank;
   
   public static final int START = 1;
   public static final int PIN = 2;
   public static final int ACCOUNT = 3;
   public static final int TRANSACT = 4;
  public ATM(Bank aBank)
   {
      theBank = aBank;
      reset();
   }

  public void reset()
   {
      customerNumber = -1;
      currentAccount = null;
      state = START;             
   }

  public void setCustomerNumber(int number) 
   {
      customerNumber = number;
      state = PIN;
   }

  public void selectCustomer(int pin)
   {  
      currentCustomer 
         = theBank.findCustomer(customerNumber, pin);
      if (currentCustomer == null) 
      {
         state = START;
      }
      else 
      {
         state = ACCOUNT;
      }
   }
  
   public void selectAccount(int account)
   {
      if (account == CHECKING)
      {
         currentAccount = currentCustomer.getCheckingAccount();
      }
      else
      {
         currentAccount = currentCustomer.getSavingsAccount();
      }
      state = TRANSACT;
   }
  
   public void withdraw(double value)
   {  
      currentAccount.withdraw(value);
   }
  
   public void deposit(double value)
   {  
      currentAccount.deposit(value);
   }
  
   public double getBalance()
   {  
      return currentAccount.getBalance();
   }
  
   public void back()
   {
      if (state == TRANSACT)
      {
         state = ACCOUNT;
      }
      else if (state == ACCOUNT)
      {
         state = PIN;
      }
      else if (state == PIN)
      {
         state = START;
      }
   }

   public int getState()
   {
      return state;
   }
}
