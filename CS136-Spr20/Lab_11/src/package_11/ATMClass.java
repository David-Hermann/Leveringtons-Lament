/*
 * Author: David Hermann
 * Date: 3 April 2020
 * Program: Lab 11
 * Class: CS136L - 001
 *
 * "Grab that cash with both hands and make a stash."
 */

package package_11;

public class ATMClass
   {
      // Constants
      private static final int DEFAULT_ID = 0000;
      private static final String DEFAULT_NAME = "No name given";
      private static final double DEFAULT_BALANCE = 0.00;

      // Attributes
      private int id;
      private String name;
      private double balance;

      // Constructors
      public ATMClass()
      {
         this.id = DEFAULT_ID;
         this.name = DEFAULT_NAME;
         this.balance = DEFAULT_BALANCE;
      }

      public ATMClass( double balance, int id, String name )
      {
         this.id = id;
         this.name = name;
         this.balance = balance;
      }

      public ATMClass( ATMClass toCopy )
      {
         this.id = toCopy.id;
         this.name = toCopy.name;
         this.balance = toCopy.balance;
      }

      // Methods
      public int getId()
      {
         return this.id;
      }

      public String getName()
      {
         return this.name;
      }

      public double getBalance()
      {
         return this.balance;
      }

      public void makeDeposit( double amount )
      {
         balance += amount;
      }

      public boolean makeWithdrawal( double amount )
      {
         boolean successfulWithdrawal = false;

         if( amount <= balance )
            {
               balance -= amount;
               successfulWithdrawal = true;
            }
         else
            {
               System.out.println( "Insufficient funds" );
            }

         return successfulWithdrawal;
      }
   }
