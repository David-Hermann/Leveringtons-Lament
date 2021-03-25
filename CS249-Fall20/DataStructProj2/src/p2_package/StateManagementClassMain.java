package p2_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class StateManagementClassMain
   {
    public static final char COMMA = ',';
    public static final char SEMICOLON = ';';
    public static final char SPACE = ' ';
    public static final char MINUS_SIGN = '-';

    public static final int MAX_INPUT_CHARS = 80;
    public static final int EOF_MARKER = -1;

    private static FileReader fileIn;
    private static final boolean SHOW_INPUT = false;


      public static void main(String[] args)
         {
          StateManagementClass originalData, bubbleSortedData;
          StateManagementClass insertionSortedData, selectionSortedData;
          StateManagementClass shellSortedData;
          int numStates = 55;

          System.out.println( "Uploading Data" );

          originalData = uploadData( "StateData.txt", numStates );

          if( originalData != null )
              {
               System.out.println( "\nOriginal Data " );

               originalData.diagnosticArrayDump();


               System.out.println( "\nBubble sort " );

               bubbleSortedData = originalData.runBubbleSort();

               bubbleSortedData.diagnosticArrayDump();

               System.out.println( "\nInsertion sort " );

               insertionSortedData = originalData.runInsertionSort();

               insertionSortedData.diagnosticArrayDump();

               System.out.println( "\nSelection sort " );

               selectionSortedData = originalData.runSelectionSort();

               selectionSortedData.diagnosticArrayDump();

               System.out.println( "\nShell sort " );

               shellSortedData = originalData.runShellSort();

               shellSortedData.diagnosticArrayDump();

              }

         }

      /**
       * tests and reports if a character is found in a given string
       *
       * @param testChar character to be tested against the string
       *
       * @param testString string within which the character is tested
       *
       * @return Boolean result of test
       */
      private static boolean charInString( char testChar, String testString )
         {
          int index;

          for( index = 0; index < testString.length(); index++ )
             {
              if( testChar == testString.charAt( index ) )
                 {
                  return true;
                 }
             }

          return false;
         }

      /**
       * compares two strings without consideration for case
       *
       * @param oneString one of the strings to be tested
       *
       * @param otherString other string to be tested
       *
       * @return first greater than second: greater than zero;
       * first equal to second: equals zero
       * first less than second: less than zero
       */
      public static int compareStrings( String oneString, String otherString )
         {
         int difference, index = 0;

         while( index < oneString.length() && index < otherString.length() )
           {
            difference = toLower( oneString.charAt( index ) )
                         - toLower( otherString.charAt( index ) );

            if( difference != 0 )
               {
                return difference;
               }

            index++;
           }

          return oneString.length() - otherString.length();
         }

      /**
       * gets a string up to a maximum length or to specified delimiter
       *
       * @param maxLength maximum length of input line
       *
       * @param delimiterChar delimiter character to stop input
       *
       * @return String captured from file
       */
      private static String getALine( int maxLength, char delimiterChar )
         {
          int inCharInt;
          int index = 0;
          String strBuffer = "";

          try
             {
              inCharInt = fileIn.read();

              // consume leading spaces
              while( index < maxLength && inCharInt <= ( SPACE )  )
                 {
                  if( inCharInt == EOF_MARKER )
                     {
                      return "EndOfFile";
                     }

                  index++;

                  inCharInt = fileIn.read();
                 }

              while( index < maxLength && inCharInt != ( delimiterChar ) )
                 {
                  if( inCharInt >= ( SPACE ) )
                     {
                      strBuffer += (char)( inCharInt );

                      index++;
                    }

                  inCharInt = fileIn.read();
                 }

              //inCharInt = fileIn.read();
             }

          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );

              strBuffer = "";
             }

          return strBuffer;
         }

      /**
       * gets an integer from the input string
       *
       * @param maxLength maximum length of characters
       * input to capture the integer
       *
       * @return integer captured from file
       */
      private static int getAnInt( int maxLength )
         {
          int inCharInt;
          int index = 0;
          String strBuffer = "";
          int intValue;
          boolean negativeFlag = false;

          try
             {
              inCharInt = fileIn.read();

              // clear space up to number
              while( index < maxLength
                          && !charInString( (char)inCharInt, "0123456789+-" ) )
                 {
                  inCharInt = fileIn.read();

                  index++;
                 }

              if( inCharInt == MINUS_SIGN )
                 {
                  negativeFlag = true;

                  inCharInt = fileIn.read();
                 }

              while( charInString( (char)inCharInt, "0123456789" ) )
                 {
                  strBuffer += (char)( inCharInt );

                  index++;

                  inCharInt = fileIn.read();
                 }
             }

          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );

              strBuffer = "";
             }

          intValue = Integer.parseInt( strBuffer );

          if( negativeFlag )
             {
              intValue *= -1;
             }

          return intValue;
         }

      /**
       * changes upper case letter to a lower case letter
       *
       * @param inChar letter to be tested and potentially modified
       *
       * @return lower case letter if upper case letter is input;
       * otherwise character is returned unchanged
       */
      private static char toLower( char inChar )
         {
          if( inChar >= 'A' && inChar <= 'Z' )
             {
              return (char)( inChar - 'A' + 'a' );
             }

          return inChar;
         }

      /**
       * uploads data from requested file
       *
       * @param fileName name of file to access
       *
       * @return Boolean result of data upload
       */
      private static StateManagementClass uploadData( String fileName,
                                                                    int arrCap )
         {
          String stateName, stateInit;
          long population;
          StateDataClass dataItem;
          StateManagementClass newData = new StateManagementClass( arrCap );

          try
             {
              // Open FileReader
              fileIn = new FileReader( fileName );

              // get first line - state name
              stateName = getALine( MAX_INPUT_CHARS, COMMA );

              while( compareStrings( stateName, "EndOfFile" ) != 0 )
                 {
                  // get state initials
                  stateInit = getALine( MAX_INPUT_CHARS, COMMA );

                  // get number of institutions
                  population = getAnInt( MAX_INPUT_CHARS );

                  // add data to StateDataClass, and then to tree
                  dataItem = new StateDataClass( stateName, stateInit,
                                                                   population );
                  newData.addState( dataItem );

                  if( SHOW_INPUT )
                     {
                      System.out.println( dataItem.toString() );
                     }

                  stateName = getALine( MAX_INPUT_CHARS, COMMA );
                 }
             }

          catch( FileNotFoundException fnfe )
             {
              System.out.println( "DATA ACCESS ERROR: Failure to open input file" );

              return null;
             }

          return newData;
         }

   }
