package package_11;

import javax.swing.JOptionPane;

public class ATMClassMain
   {
      public static void main(String[] args)
         {
            double balance = 450;
            int id = 1234;
            String name = "CS136";

            ATMClass classTest = new ATMClass( balance, id, name );
            atmOperation( classTest );

            ATMClass copy = new ATMClass( classTest );
            atmOperation( copy );
         }

       public static void atmOperation( ATMClass atmInstance )
       {
          int input;
          double deposit, withdrawal;
          String inputStr, depositStr, withdrawalStr;

          inputStr = JOptionPane.showInputDialog
                ( "Enter 1 for balance, 2 for deposit, 3 for withdrawal or 4 to quit" );
          input = Integer.parseInt( inputStr );

          while( input != 4 )
             {
                switch( input )
                {
                   case 1:
                      System.out.println( "Balance is: $" + atmInstance.getBalance() );
                      break;
                   case 2:
                      depositStr = JOptionPane.showInputDialog( "Enter the deposit amount" );
                      deposit = Double.parseDouble( depositStr );
                      atmInstance.makeDeposit( deposit );
                      break;
                   case 3:
                      withdrawalStr = JOptionPane.showInputDialog
                         ( "Enter the withdrawal amount" );
                      withdrawal = Double.parseDouble( withdrawalStr );
                      atmInstance.makeWithdrawal( withdrawal );
                      break;
                   default:
                      System.out.println( "Enter a choice between 1 - 4" );
                      break;
                }
                inputStr = JOptionPane.showInputDialog
                      ( "Enter 1 for balance, 2 for deposit, 3 for withdrawal or 4 to quit" );
                input = Integer.parseInt( inputStr );
             }
       }
   }
