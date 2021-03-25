package p3_package;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class that demonstrates merge and quick sort operations with
 * randomly generated, downloaded, and uploaded data in given bases
 *
 * @author MichaelL
 *
 */
public class MainTestClass
    {
     public static final int MIN_VAL_LENGTH = 6;
     public static final int MAX_VAL_LENGTH = 9;
     public static final int END_OF_FILE_MARKER = -1;

    public static void main(String[] args)
        {
         int base = 2, maxValues = 10, index;
         RegisterClass[] mergeSortData;
         RegisterDataClass dataClass;
         String registerValue;

         downloadRandomData( maxValues, base, "BaseTwoData.txt" );

         dataClass = uploadDataSet( "BaseTwoData.txt", maxValues, base );

         System.out.println( "Array dump of uploaded data: " );

         dataClass.diagnosticArrayDump();
         /*
         quickSortData = dataClass.runQuickSort();

         System.out.println( "Quick Sort Result:" );

         for( index = 0; index < maxValues; index++ )
            {
             registerValue = quickSortData[ index ].getValueAsBase();

             System.out.println( "  " + registerValue );
            }
         */

         mergeSortData = dataClass.runMergeSort();

         System.out.println( "\nMerge Sort Result:" );

         for( index = 0; index < maxValues; index++ )
            {
             registerValue = mergeSortData[ index ].getValueAsBase();

             System.out.println( "  " + registerValue );
            }

        }

    /**
     * Generates and downloads a specified number of values in a specified base
     *
     * @param numVals integer number of values to be downloaded
     *
     * @param base integer base of values to be downloaded
     *
     * @param fileName String name of file to be accessed
     *
     * @return Boolean outcome of operation
     */
    public static boolean downloadRandomData( int numVals,
                                                      int base, String fileName)
       {
        // variable initialization
        FileWriter fileOut;
        long outValue;
        int counter;

        // open try block
        try
           {
            // instantiate the object, open the file
               // method: FileWriter constructor
            fileOut = new FileWriter( fileName );

            for( counter = 0; counter < numVals; counter++ )
               {
                outValue = generateValue( base );

                fileOut.write( outValue + "\r\n" );
               }

            // file closing
               // method: write, flush, close
            fileOut.write( "\r\n" );
            fileOut.flush();
            fileOut.close();
           }

        // catch exception as needed
        catch( IOException ioe )
           {
            return false;
           }

        return true;
       }

     /**
      * generates a random value up to MAX_VAL_LENGTH in specified base
      *
      * @param base integer base value provided
      *
      * @return long value generated as specified
      */
     public static long generateValue( int base )
        {
         int counter;
         int digitRange = MAX_VAL_LENGTH - MIN_VAL_LENGTH + 1;
         int countLimit = getRandBaseDigit( digitRange ) + MIN_VAL_LENGTH;
         int multiplier = 10;
         int result = 0;

         for( counter = 0; counter < countLimit; counter++ )
            {
             result *= multiplier;

             result += getRandBaseDigit( base );
            }

         return result;
        }

    /**
     * Uploads random values in a given base from file
     *
     * @param fileName String name of file to access
     *
     * @param numVals integer number of values to upload
     *
     * @param base integer base of uploaded values
     *
     * @return RegisterDataClass object holding the specified  data
     */
    public static RegisterDataClass uploadDataSet( String fileName,
                                                         int numVals, int base )
        {
         int value, regArrayIndex = 0;
         String numberString;
         RegisterDataClass dataClass = new RegisterDataClass();

         //FileReader
         FileReader fileIn = null;

         try
            {
             fileIn = new FileReader( fileName );

             // read base text
             value = fileIn.read();

             while( value != END_OF_FILE_MARKER && regArrayIndex < numVals )
                 {
                  numberString = "";

                  // get number
                  while( value >= '0' && value <= '9' )
                     {
                      numberString += (char)value;

                      value = fileIn.read();
                     }

                  if( numberString.length() > 0 )
                     {
                      dataClass.addRegister( new RegisterClass( base,
                                               MAX_VAL_LENGTH, numberString ) );

                      regArrayIndex++;
                     }

                  value = fileIn.read();
                 }
              // end data collection loop

              if( fileIn != null )
                 {
                  fileIn.close();
                 }
             }

          catch( IOException ioe )
             {
              return null;
             }

          return dataClass;
         }

     /**
      * gets a random digit between 0 and base - 1 (inclusive0
      *
      * @param base integer value representing base
      *
      * @return integer random value generated
      */
     public static int getRandBaseDigit( int base )
        {
         return  (int)( Math.random() * base );
        }
    }