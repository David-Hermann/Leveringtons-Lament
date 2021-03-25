package package_5;

import javax.swing.JOptionPane;

public class ZellersCongruenceClass
   {
      // Constant declarations
      private static final int DAYS_IN_WEEK = 7;
      private static final int MONTHS_IN_YEAR = 12;
      private static final int THIRD_MONTH = 3;
      private static final int SATURDAY = 0;
      private static final int SUNDAY = 1;
      private static final int MONDAY = 2;
      private static final int TUESDAY = 3;
      private static final int WEDNESDAY = 4;
      private static final int THURSDAY = 5;
      private static final int FRIDAY = 6;

      // Controls the program
      public static void main( String[] args )
         {
            // Variable declarations
            int month, day, year, yearPart, centuryPart, zellerSecond;
            int monthForCalc, yearForCalc;
            int zellerFourth, zellerFifth, zellerSixth, dateAsInt;
            String dayOfWeek;

            // Get user's input as integers
            month = getUserInput( "Enter month of birth" );
            day = getUserInput( "Enter date of birth" );
            year = getUserInput( "Enter year of birth" );

            // Determine if month is January or February for calculation
            if ( month < THIRD_MONTH )
               {
                  monthForCalc = month + MONTHS_IN_YEAR;
                  yearForCalc = year--;
               }
            else
               {
                  monthForCalc = month;
                  yearForCalc = year;
               }

            // Extract each part of the year for calculation
            centuryPart = (int) Math.floor( yearForCalc / 100.0 );
            yearPart = yearForCalc % 100;

            // Calculate Zeller's Congruence in multiple parts
            zellerSecond = findSecondElement( monthForCalc );
            zellerFourth = findFourthElement( yearPart );
            zellerFifth = findFifthElement( centuryPart );
            zellerSixth = findSixthElement( centuryPart );
            dateAsInt = calculateFullEquation( day, zellerSecond, yearPart,
                                       zellerFourth, zellerFifth, zellerSixth );

            // Determine what day of the week the entered date is
            dayOfWeek = findBirthdayString( dateAsInt );

            // Print results to console window
            printResults( month, day, year, dayOfWeek );
         }

      // Gets user's birth date using JOptionPane
      private static int getUserInput( String prompt )
         {
            String usersInputStr;
            int usersInput;

            usersInputStr = JOptionPane.showInputDialog( prompt );
            usersInput = Integer.parseInt( usersInputStr );

            return usersInput;
         }

      // Finds second element of Zeller's Congruence
      private static int findSecondElement( int month )
         {
            int element;

            month++;
            element = (int) Math.floor( 13 * month / 5 );

            return element;
         }

      // Finds fourth element of Zeller's Congruence
      private static int findFourthElement( int yearPart )
         {
            int element;

            element = (int) Math.floor( yearPart / 4 );

            return element;
         }

      // Finds fifth element of Zeller's Congruence
      private static int findFifthElement( int centuryPart )
         {
            int element;

            element = (int) Math.floor( centuryPart / 4 );

            return element;
         }

      // Finds sixth element of Zeller's Congruence
      private static int findSixthElement( int centuryPart )
         {
            int element;

            element = centuryPart * 5;

            return element;
         }

      private static int calculateFullEquation( int day, int secondElement,
           int yearPart, int fourthElement, int fifthElement, int sixthElement )
         {
            int dayOfWeekInt;

            dayOfWeekInt = ( day + secondElement + yearPart + fourthElement +
                                   fifthElement + sixthElement ) % DAYS_IN_WEEK;

            return dayOfWeekInt;
         }

      // Returns string associated with argument value
      private static String findBirthdayString( int birthDayNumber )
         {
            String birthDayOfWeek;

            switch( birthDayNumber )
            {
               case SATURDAY:
                  birthDayOfWeek = "Saturday";
                  break;
               case SUNDAY:
                  birthDayOfWeek = "Sunday";
                  break;
               case MONDAY:
                  birthDayOfWeek = "Monday";
                  break;
               case TUESDAY:
                  birthDayOfWeek = "Tuesday";
                  break;
               case WEDNESDAY:
                  birthDayOfWeek = "Wednesday";
                  break;
               case THURSDAY:
                  birthDayOfWeek = "Thursday";
                  break;
               case FRIDAY:
                  birthDayOfWeek = "Friday";
                  break;
               default:
                  birthDayOfWeek = "Error!";
            }

            return birthDayOfWeek;
         }

      // Prints results
      private static void printResults( int month, int day, int year,
                                                              String dayOfWeek )
         {
            System.out.println( "Birthday Program" );
            System.out.println( "================" );
            System.out.print("\n");
            System.out.println( "For the date " + month + "/" + day + "/" + year
                                  + ", the person was born on a " + dayOfWeek );
         }
   }
