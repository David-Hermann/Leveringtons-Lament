package package_8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple encryption class that stores character data as integers from the
 * bottom of an array to the top from right to left
 *
 * @author MichaelL
 */
public class EncryptionClassMain
   {
      /**
       * Constant for maximum input char limit
       */
      private static final int MAX_INPUT_CHARS = 256;

      /**
       * Constant for unprintable char value used as message end char
       */
      private static final int UNPRINTABLE_CHAR_VALUE = 127; // ASCII for delete
                                                             // key

      /**
       * Constant for minus sign used in getAnInt
       */
      private static final char MINUS_SIGN = '-';

      /**
       * Class Global FileReader variable so methods can be used
       */
      private static FileReader fileIn;

      /**
       * Main method uploads data and translates
       *
       * @param args String array of arguments, not used
       */
      public static void main(String[] args)
         {
            String resultStr, testStr = "This is a test, but it is only a test";

            // String testString = new String( testStr );

            TwoDimArrayClass testArr = encryptData(testStr);

            testArr.dumpTwoDimArray();

            resultStr = decryptData(testArr);

            System.out.println("Result String from first test: " + resultStr);

            downloadData("testfile.txt", testArr);

            TwoDimArrayClass newArray = uploadData("testfile.txt");

            resultStr = decryptData(newArray);

            System.out.println("Result String from upload: " + resultStr);

            TwoDimArrayClass copyArray = new TwoDimArrayClass(newArray);

            resultStr = decryptData(copyArray);

            System.out
                  .println("Result String from copy constructor: " + resultStr);

            displayCharArray(testArr);

            copyArray.resize(12, 12);

            System.out.println("Array resized to 12 by 12: ");

            copyArray.dumpTwoDimArray();
         }

      /**
       * tests and reports if a character is found in a given string
       * <p>
       * Note: uses .charAt and .length Java utilities for string management
       *
       * @param testChar   character to be tested against the string
       *
       * @param testString string within which the character is tested
       *
       * @return Boolean result of test
       */
      private static boolean charInString(char testChar, String testString)
         {
            int index;

            for (index = 0; index < testString.length(); index++)
               {
                  if (testChar == testString.charAt(index))
                     {
                        return true;
                     }
               }

            return false;
         }

      /**
       * Decrypts string from array
       *
       * @param data TwoDimArrayClass object to be decrypted to a string
       *
       * @return String result of decryption process
       */
      public static String decryptData(TwoDimArrayClass data)
         {
            int oneSideSize = data.getCurrentColCapacity();
            int rowIndex, colIndex;
            String resultStr = "";
            int retrievedInt;
            boolean foundEnd = false;

            for (rowIndex = oneSideSize - 1; rowIndex >= 0; rowIndex--)
               {
                  for (colIndex = oneSideSize - 1; colIndex >= 0; colIndex--)
                     {
                        retrievedInt = data.accessItemAt(rowIndex, colIndex);

                        if (!foundEnd && retrievedInt != UNPRINTABLE_CHAR_VALUE)
                           {
                              resultStr += (char) retrievedInt;
                           }

                        else
                           {
                              foundEnd = true;
                           }
                     }
               }

            return resultStr;
         }

      /**
       * Displays array in character form for diagnostics; shows dash between
       * end of string and random characters
       * <p>
       * Note: No Java utilities are used in this method
       *
       * @param data TwoDimArrayClass object to be displayed as characters
       */
      public static void displayCharArray(TwoDimArrayClass data)
         {
            int rowIndex, colIndex;
            int oneSideSize = data.getCurrentColCapacity();
            int retrievedInt;

            System.out.println("\nCharacter Array Display");

            for (rowIndex = 0; rowIndex < oneSideSize; rowIndex++)
               {
                  System.out.print("\t");

                  for (colIndex = 0; colIndex < oneSideSize; colIndex++)
                     {
                        retrievedInt = data.accessItemAt(rowIndex, colIndex);

                        if (retrievedInt != UNPRINTABLE_CHAR_VALUE)
                           {
                              System.out.print("" + (char) retrievedInt);
                           }

                        else
                           {
                              System.out.print("" + MINUS_SIGN);
                           }
                     }

                  System.out.println();
               }

            System.out.println();
         }

      /**
       * Downloads encrypted data to file
       * <p>
       * Note: No action taken if array is empty
       *
       * @param fileName String object holding file name to use
       *
       * @param data     TwoDimArrayClass object holding encrypted data
       */
      public static void downloadData(String fileName, TwoDimArrayClass data)
         {
            FileWriter toFile;
            int outputInt;

            int rowIndex, colIndex, oneSideSize = data.getCurrentColCapacity();

            if (oneSideSize > 0)
               {
                  try
                     {
                        toFile = new FileWriter(fileName);

                        toFile.write("" + oneSideSize + "\r\n");

                        for (rowIndex = 0; rowIndex < oneSideSize; rowIndex++)
                           {
                              for (colIndex = 0; colIndex < oneSideSize; colIndex++)
                                 {
                                    outputInt = data.accessItemAt(rowIndex,
                                          colIndex);

                                    if (outputInt < 100)
                                       {
                                          toFile.write("0");
                                       }

                                    toFile.write("" + outputInt + " ");
                                 }

                              toFile.write("\r\n");
                           }

                        toFile.write("\r\n");

                        toFile.flush();
                        toFile.close();
                     }

                  catch (IOException ioe)
                     {
                        ioe.printStackTrace();
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
       *
       * @param toEncrypt String object to be encrypted
       *
       * @return TwoDimArrayClass object containing encrypted data
       */
      public static TwoDimArrayClass encryptData(String toEncrypt)
         {
            int rowIndex, colIndex, arraySide;
            int strIndex = 0, strLength = toEncrypt.length();
            int charAsInt;
            TwoDimArrayClass encryptedArrObject;

            arraySide = findIntSquareRoot(strLength + 1);

            encryptedArrObject = new TwoDimArrayClass(arraySide, arraySide,
                  arraySide);

            for (rowIndex = arraySide - 1; rowIndex >= 0; rowIndex--)
               {
                  for (colIndex = arraySide
                        - 1; colIndex >= 0; colIndex--, strIndex++)
                     {
                        // encrypt string up to the end of it
                        if (strIndex < strLength)
                           {
                              charAsInt = toEncrypt.charAt(strIndex);

                              encryptedArrObject.setItemAt(rowIndex, colIndex,
                                    charAsInt);
                           }

                        // add unprintable character after string
                        else if (strIndex == strLength)
                           {
                              encryptedArrObject.setItemAt(rowIndex, colIndex,
                                    UNPRINTABLE_CHAR_VALUE);
                           }

                        // fill in all other elements with random
                        // characters/integers
                        else
                           {
                              charAsInt = getRandomCharValue();

                              encryptedArrObject.setItemAt(rowIndex, colIndex,
                                    charAsInt);
                           }
                     }
               }

            return encryptedArrObject;
         }

      /**
       * Finds the square root of an integer value, rounded up to the next
       * integer
       * <p>
       * Note: Finds square root to precision of 0.000001 without using any Java
       * utilities other than abs
       *
       * @param value integer value to find square root of
       *
       * @return double square root value
       */
      private static int findIntSquareRoot(int value)
         {
            final double PRECISION = 0.000001;
            double lowVal = 0, highVal = value;
            double resultVal = (lowVal + highVal) / 2;
            double testSquare = resultVal * resultVal;

            while (Math.abs(value - testSquare) > PRECISION)
               {
                  if (testSquare > value)
                     {
                        highVal = resultVal;
                     }

                  else
                     {
                        lowVal = resultVal;
                     }

                  resultVal = (lowVal + highVal) / 2;
                  testSquare = resultVal * resultVal;
               }

            return (int) (resultVal + 1);
         }

      /**
       * gets an integer from the input stream
       *
       * @param maxLength maximum length of characters input to capture the
       *                  integer
       *
       * @return integer captured from file
       */
      private static int getAnInt(int maxLength)
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
                  while (index < maxLength
                        && !charInString((char) inCharInt, "0123456789+-"))
                     {
                        inCharInt = fileIn.read();

                        index++;
                     }

                  if (inCharInt == MINUS_SIGN)
                     {
                        negativeFlag = true;

                        inCharInt = fileIn.read();
                     }

                  while (charInString((char) inCharInt, "0123456789"))
                     {
                        strBuffer += (char) (inCharInt);

                        index++;

                        inCharInt = fileIn.read();
                     }
               }

            catch (IOException ioe)
               {
                  System.out
                        .println("INPUT ERROR: Failure to capture character");

                  strBuffer = "";
               }

            if (strBuffer.length() > 0)
               {
                  intValue = Integer.parseInt(strBuffer);
               }

            else
               {
                  return 0;
               }

            if (negativeFlag)
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
       * value, and any possible character between; may use any appropriate Math
       * utilities
       *
       * @return integer value of randomly generated character
       */
      private static int getRandomCharValue()
         {
            final int LOWEST_PRINTABLE_CHAR_VALUE = ('a');
            final int HIGHEST_PRINTABLE_CHAR_VALUE = ('z');
            int range = HIGHEST_PRINTABLE_CHAR_VALUE
                  - LOWEST_PRINTABLE_CHAR_VALUE + 1;

            int randVal = (int) (Math.random() * range);

            return randVal + LOWEST_PRINTABLE_CHAR_VALUE;
         }

      /**
       * Uploads data from file holding a square array
       *
       * @param fileName String object holding file name
       *
       * @return TwoDimArrayClass object containing uploaded and encrypted data
       */
      public static TwoDimArrayClass uploadData(String fileName)
         {
            int rowIndex, colIndex;
            int tempInput, arrSide = 0;
            TwoDimArrayClass inputArray = null;

            try
               {
                  // Open FileReader
                  fileIn = new FileReader(fileName);

                  // get side length
                  arrSide = getAnInt(MAX_INPUT_CHARS);

                  if (arrSide > 0)
                     {
                        inputArray = new TwoDimArrayClass(arrSide, arrSide,
                              arrSide);

                        for (rowIndex = 0; rowIndex < arrSide; rowIndex++)
                           {
                              for (colIndex = 0; colIndex < arrSide; colIndex++)
                                 {
                                    tempInput = getAnInt(MAX_INPUT_CHARS);

                                    inputArray.setItemAt(rowIndex, colIndex,
                                          tempInput);
                                 }
                           }
                     }

                  else
                     {
                        inputArray = null;
                     }

                  fileIn.close();
               }

            // for opening file
            catch (FileNotFoundException fnfe)
               {
                  fnfe.printStackTrace();
               }

            // for closing file
            catch (IOException ioe)
               {
                  ioe.printStackTrace();
               }

            return inputArray;
         }
   }