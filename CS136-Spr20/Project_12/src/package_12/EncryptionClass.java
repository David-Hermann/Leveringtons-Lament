package package_12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Date: 22 April 2020
 * <p>
 * Program: PA12
 * <p>
 * Class: CS136 - 001
 * <p>
 * "public class DavidHermann extends DumbPersonClass"
 * <p>
 * Subclass of TwoDimArrayClass
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 *
 */
public class EncryptionClass extends TwoDimArrayClass
   {
      // Constants
      /**
       * Constant for requesting character display
       */
      public static final int CHARACTER_DISPLAY = 5005;
      /**
       * Constant for requesting integer display
       */
      public static final int INTEGER_DISPLAY = 6006;
      /**
       * Constant for maximum input char limit
       */
      private final int MAX_INPUT_CHARS = 256;
      /**
       * Constant for minus sign used in getAnInt
       */
      private final char MINUS_SIGN = '-';
      /**
       * Constant newline character for testing in display
       */
      public static final char NEWLINE_CHAR = '\n';
      /**
       * Constant underscore character for display
       */
      public static final char UNDERSCORE_CHAR = '_';
      /**
       * Constant for unprintable char value used as message end char
       */
      private final int UNPRINTABLE_CHAR_VALUE = 127;

      // Variables
      /**
       * Class Global FileReader variable so methods can be used
       */
      private FileReader fileIn;

      // Constructors
      /**
       * Default constructor - initializes array and other member data
       */
      public EncryptionClass()
         {
            super();
            fileIn = null;
         }

      /**
       * Initialization constructor - accepts row and column capacities
       *
       * @param rowCap Value to be initially used as row capacity
       * @param colCap Value to be initially used as column capacity
       */
      public EncryptionClass( int rowCap, int colCap )
         {
            super( rowCap, colCap );
            fileIn = null;
         }

      /**
       * Copy constructor - copies all data from other object
       *
       * @param copied EncryptionClass object to be copied to this object
       */
      public EncryptionClass( EncryptionClass copied )
         {
            super( copied );
            fileIn = null;
         }

      // Methods
      /**
       * Tests and reports if a character is found in a given string
       * <p>
       * Note: uses .charAt and .length Java utilities for string management
       *
       * @param testChar Character to be tested against the string
       * @param testString String within which the character is tested
       * @return True if the character is in the string, false otherwise
       */
      private boolean charInString( char testChar, String testString )
         {
            boolean charExistsInString = false;
            int testStringLength = testString.length(), index;

            for ( index = 0; index < testStringLength; index++ )
               {
                  if ( testChar == testString.charAt(index) )
                     {
                        charExistsInString = true;
                     }
               }

            return charExistsInString;
         }

      /**
       * Decrypts string from array
       *
       * @return String result of decryption process
       */
      public String decryptData()
         {
            String returnString = "";
            int retrievedInt, oneSideSize = super.getCurrentColCapacity();
            int rowIndex, colIndex;
            boolean foundEnd = false;

            for ( rowIndex = oneSideSize - 1; rowIndex >= 0; rowIndex-- )
               {
                  for ( colIndex = oneSideSize - 1; colIndex >= 0; colIndex-- )
                     {
                        retrievedInt = super.accessItemAt( rowIndex, colIndex );

                        if ( !foundEnd &&
                                        retrievedInt != UNPRINTABLE_CHAR_VALUE )
                           {
                              returnString += (char) retrievedInt;
                           }

                        else
                           {
                              foundEnd = true;
                           }
                     }
               }

            return returnString;
         }

      /**
       * Displays array as either numbers or characters; shows dash between end
       * of string and random characters
       * <p>
       * Note: No Java utilities are used in this method
       *
       * @param displayMode Value to indicate whether to display as characters
       *    or integers - uses available object constants
       */
      public void displayEncryptedArray( int displayMode )
         {
            final int THREE_DIGITS = 100;

            int rowIndex, colIndex;
            int oneSideSize = super.getCurrentColCapacity();
            int retrievedInt;

            if( displayMode == CHARACTER_DISPLAY )
               {
                  System.out.println(
                                    "\nEncrypted Array Display as Characters" );
               }
            else   // displayMode == INTEGER_DISPLAY
               {
                  System.out.println( "\nEncrypted Array Display as Integers" );
               }

                  for ( rowIndex = 0; rowIndex < oneSideSize; rowIndex++ )
                     {
                        System.out.print( "\t" );

                        for ( colIndex = 0; colIndex < oneSideSize; colIndex++ )
                           {
                              retrievedInt = super.accessItemAt( rowIndex,
                                                                     colIndex );

                              if( displayMode == CHARACTER_DISPLAY )
                                 {
                                    if ( retrievedInt ==
                                                        UNPRINTABLE_CHAR_VALUE )
                                       {
                                          System.out.print( " " + MINUS_SIGN );
                                       }
                                    else if ( (char) retrievedInt ==
                                                                  NEWLINE_CHAR )
                                       {
                                          System.out.print( " " +
                                                              UNDERSCORE_CHAR );
                                       }
                                    else
                                       {
                                          System.out.print( " " +
                                                (char) retrievedInt );
                                       }
                                 }
                              else   // displayMode == INTEGER_DISPLAY
                                 {
                                    if( retrievedInt < THREE_DIGITS )
                                       {
                                          System.out.print( "  " +
                                                                 retrievedInt );
                                       }
                                    else
                                       {
                                          System.out.print( " " +
                                                                 retrievedInt );
                                       }
                                 }
                           }
                        System.out.print( NEWLINE_CHAR );
                     }
                  System.out.print( NEWLINE_CHAR );
         }

      /**
       * Downloads encrypted data to file
       * <p>
       * Note: No action taken if array is empty
       *
       * @param fileName String object holding file name to use
       */
      public void downloadData( String fileName )
         {
            final String RETURN_AND_NEWLINE = "\r\n";
            final int THREE_DIGITS = 100;


            FileWriter toFile;
            int outputInt;
            int rowIndex, colIndex, oneSideSize = super.getCurrentColCapacity();

            if ( oneSideSize > 0 )
               {
                  try
                     {
                        toFile = new FileWriter( fileName );

                        toFile.write( "" + oneSideSize + RETURN_AND_NEWLINE );

                        for( rowIndex = 0; rowIndex < oneSideSize; rowIndex++ )
                           {
                              for ( colIndex = 0; colIndex < oneSideSize;
                                                                    colIndex++ )
                                 {
                                    outputInt = super.accessItemAt( rowIndex,
                                                                     colIndex );

                                    if ( outputInt < THREE_DIGITS )
                                       {
                                          toFile.write( "0" );
                                       }

                                    toFile.write( "" + outputInt + " " );
                                 }

                              toFile.write( RETURN_AND_NEWLINE );
                           }

                        toFile.write( RETURN_AND_NEWLINE );

                        toFile.flush();
                        toFile.close();
                     }

                  catch ( IOException couldNotOpenFile )
                     {
                        couldNotOpenFile.printStackTrace();
                     }
               }
         }

      /**
       * Encrypts a string into a series of integers, added to an array from the
       * bottom right to the top left
       * <p>
       * Note: Uses .length and .charAt for string management
       * <p>
       * Note: Adds data to array from bottom left to top right; once string has
       * been completed, loads unprintable character (Delete key) and then loads
       * random characters thereafter
       * <p>
       * Note: uses .length for String management
       *
       * @param toEncrypt String object to be encrypted
       */
      public void encryptData( String toEncrypt )
         {
            int rowIndex, colIndex, arraySide;
            int strIndex = 0, strLength = toEncrypt.length();
            int charAsInt;

            arraySide = findIntSquareRoot( strLength + 1 );
            super.resize( arraySide, arraySide );

            for( rowIndex = arraySide - 1; rowIndex >= 0; rowIndex-- )
               {
                  for( colIndex = arraySide
                                    - 1; colIndex >= 0; colIndex--, strIndex++ )
                     {
                        // encrypt string up to the end of it
                        if ( strIndex < strLength )
                           {
                              charAsInt = toEncrypt.charAt( strIndex );

                              super.setItemAt( rowIndex, colIndex, charAsInt );
                           }

                        // add unprintable character after string
                        else if ( strIndex == strLength )
                           {
                              super.setItemAt( rowIndex, colIndex,
                                                       UNPRINTABLE_CHAR_VALUE );
                           }

                        // fill in all other elements with random
                        // characters/integers
                        else
                           {
                              charAsInt = getRandomCharValue();

                              super.setItemAt( rowIndex, colIndex, charAsInt );
                           }
                     }
               }
         }

      /**
       * Finds the square root of an integer value, rounded up to the next
       * integer
       * <p>
       * Note: Finds square root to precision of 0.000001 without using any Java
       * utilities other than abs
       *
       * @param value Value to find square root of
       * @return Double of the square root of value
       */
      private int findIntSquareRoot( int value )
         {
            final double PRECISION = 0.000001;
            double lowVal = 0, highVal = value;
            double resultVal = (lowVal + highVal) / 2;
            double testSquare = resultVal * resultVal;

            while ( Math.abs(value - testSquare) > PRECISION )
               {
                  if ( testSquare > value )
                     {
                        highVal = resultVal;
                     }

                  else
                     {
                        lowVal = resultVal;
                     }

                  resultVal = ( lowVal + highVal ) / 2;
                  testSquare = resultVal * resultVal;
               }

            return (int) ( resultVal + 1 );
         }

      /**
       * Gets an integer from the input stream
       *
       * @param maxLength Maximum length of characters input to capture the
       *    integer
       * @return Integer captured from file
       */
      private int getAnInt( int maxLength )
         {
            int index = 0, inCharInt, intValue, strBufferLength;
            boolean negativeFlag = false;
            String strBuffer = "";

            try
               {
                  inCharInt = fileIn.read();

                  // Iterate through white space up to first number
                  while (index < maxLength
                          && !charInString( (char) inCharInt, "0123456789+-" ) )
                     {
                        inCharInt = fileIn.read();

                        index++;
                     }

                  if ( inCharInt == MINUS_SIGN )
                     {
                        negativeFlag = true;

                        inCharInt = fileIn.read();
                     }

                  while ( charInString( (char) inCharInt, "0123456789" ) )
                     {
                        strBuffer += (char) (inCharInt);

                        index++;

                        inCharInt = fileIn.read();
                     }
               }

            catch ( IOException couldNotCaptureChar )
               {
                  System.out
                        .println( "INPUT ERROR: Failure to capture character" );

                  strBuffer = "";
               }

            strBufferLength = strBuffer.length();

            if ( strBufferLength > 0 )
               {
                  intValue = Integer.parseInt( strBuffer );
               }

            else
               {
                  intValue = 0;
               }

            if ( negativeFlag )
               {
                  intValue *= -1;
               }

            return intValue;
         }

      /**
       * Generates the integer value of a random character between the lowest
       * possible character value (space) and the highest possible character
       * value (tilde)
       * <p>
       * Note: Method must be capable of generating a space value, a tilde
       * value, and any possible character between; may use any appropriate
       * Math utilities
       *
       * @return Integer value of randomly generated character
       */
      private int getRandomCharValue()
         {
            final int LOWEST_PRINTABLE_CHAR_VALUE = ('a');
            final int HIGHEST_PRINTABLE_CHAR_VALUE = ('z');

            int randomlyGeneratedInt;
            int range = HIGHEST_PRINTABLE_CHAR_VALUE
                                              - LOWEST_PRINTABLE_CHAR_VALUE + 1;

            randomlyGeneratedInt = (int) (Math.random() * range);
            randomlyGeneratedInt += LOWEST_PRINTABLE_CHAR_VALUE;

            return randomlyGeneratedInt;
         }

      /**
       * Uploads data from file holding a square array
       *
       * @param fileName String object holding file name
       * @return TwoDimArrayClass object containing uploaded and encrypted data
       */
      public TwoDimArrayClass uploadData( String fileName )
         {
            int rowIndex, colIndex, tempInput, arrSide;
            TwoDimArrayClass inputArray = null;

            try
               {
                  fileIn = new FileReader( fileName );

                  arrSide = getAnInt( MAX_INPUT_CHARS );

                  if ( arrSide > 0 )
                     {
                        inputArray = new TwoDimArrayClass( arrSide, arrSide );

                        for ( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
                           {
                              for ( colIndex = 0; colIndex < arrSide;
                                                                    colIndex++ )
                                 {
                                    tempInput = getAnInt( MAX_INPUT_CHARS );

                                    inputArray.setItemAt( rowIndex, colIndex,
                                                                    tempInput );
                                 }
                           }
                     }

                  else
                     {
                        inputArray = null;
                     }

                  fileIn.close();
               }
            catch ( FileNotFoundException couldNotFindFile )
               {
                  couldNotFindFile.printStackTrace();
               }
            catch ( IOException couldNotCloseFile )
               {
                  couldNotCloseFile.printStackTrace();
               }

            return inputArray;
         }
   }
