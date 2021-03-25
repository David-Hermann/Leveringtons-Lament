package package_7;

import java.io.FileReader;
import java.io.IOException;

public class ArrayClassMain
   {
      private static FileReader fileIn;
      private final static int END_OF_FILE_MARKER = -1;
      private final static char SPACE = ' ';
      private final static char SEMICOLON = ';';

      public static void main(String[] args)
         {
          // Tests for methods within the ArrayClass class using arrayTest
          int capacity = 100;
          ArrayClass arrayTest = getData( "Names.txt", capacity );

          System.out.println( "Accessing item at index 5: "
                                                + arrayTest.accessItemAt( 5 ) );
          arrayTest.listNames();

          System.out.println( "\nSetting \"Smith, Bill\" at index 10" );

          arrayTest.setItemAt( 10, "Smith, Bill" );

          arrayTest.listNames();

          // Additional testing
          System.out.println( arrayTest.getCurrentCapacity() );
          System.out.println( arrayTest.arrayAtCapacity() );

          boolean didResize = arrayTest.resize( 105 );
          System.out.println( didResize );
          System.out.println( arrayTest.getCurrentCapacity() );

          didResize = arrayTest.resize( capacity );
          System.out.println( didResize );
          System.out.println( arrayTest.getCurrentCapacity() );

         }

      /**
       * Local method uploads data character by character,
       * parses characters, and loads into ArrayClass type data
       * <p>
       * Exception: If there is a file failure such as file not found,
       * method will return null
       * <p>
       * Exception: If the capacity parameter is set too low for the data,
       * method will also return null
       *
       * @param fileName name of file in local directory required for upload
       *
       * @param setCapacity directs method to set capacity of String array
       *
       * @return String array of data
       */
      public static ArrayClass getData( String fileName, int setCapacity )
         {
          int value;

          ArrayClass names = new ArrayClass( setCapacity );
          String nameStr;
          int index = 0;
          boolean failedAccess = false;

          //FileReader
          fileIn = null;

          try
             {
              fileIn = new FileReader( fileName );

              // read prime, name
              // clear non-printable characters, if any
              do
                 {
                  value = fileIn.read();
                 }
              while( value <= SPACE );

              // reset name string
              nameStr = "";

              while( value != END_OF_FILE_MARKER & !failedAccess )
                 {
                  // take in string/name
                  while( value != SEMICOLON )
                     {
                      nameStr += (char)value;

                      value = fileIn.read();
                     }

                  // load data into ArrayClass object
                  // check for success
                  if( !names.setItemAt( index, nameStr ) )
                     {
                      failedAccess = true;
                     }

                  // increment index for ArrayClass
                  index++;

                  // reprime for next
                  // clear non-printable characters, if any
                  do
                     {
                      value = fileIn.read();
                     }
                  while( value <= SPACE && value != END_OF_FILE_MARKER );

                  // reset name string
                  nameStr = "";

                 }  // end data collection loop

              if( fileIn != null )
                 {
                  fileIn.close();
                 }
             }

          catch( IOException ioe )
             {
              failedAccess = true;
             }

          if( failedAccess )
             {
              names = null;
             }

          return names;
         }


   }