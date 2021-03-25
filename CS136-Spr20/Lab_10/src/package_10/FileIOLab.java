package package_10;

import java.io.*;

public class FileIOLab
   {
      /**
       * Class Global FileReader variable so methods can be used
       */
      private static FileReader readFrom;

      /**
       * Class Global FileWriter variable so methods can be used
       */
      private static FileWriter writeTo;

      /**
       * Global constant variable to check for end of file marker
       *
       */
      private static final int END_OF_FILE = -1;

      /**
       *
       * @param args Array of strings passed into main, not used
       */
      public static void main( String[] args )
         {
            // String for file input
            String liesAndDeception = "CS136 Lab is more fun online!";

            // Attempt to create and write to fileOutput.txt
            try
               {
                  writeTo = new FileWriter( "fileOutput.txt" );
                  writeTo.write( liesAndDeception );
                  writeTo.flush();
                  writeTo.close();
               }
            catch( IOException inputOutputEx )
               {
                  inputOutputEx.printStackTrace();
               }

            // Attempt to read from fileOutput.txt and print to console
            try
               {
                  readFrom = new FileReader( "fileOutput.txt" );

                  int intRepresentationOfChar = END_OF_FILE + 1;
                  while ( intRepresentationOfChar != END_OF_FILE )
                     {
                        intRepresentationOfChar = readFrom.read();

                        // Added this if statement since the loop condition is
                        // not evaluated before the end of file marker is
                        // reached
                        if( intRepresentationOfChar != END_OF_FILE )
                           {
                              System.out.print( (char)
                                                      intRepresentationOfChar );
                           }
                     }

                  readFrom.close();
               }
            catch( FileNotFoundException notFound )
               {
                  notFound.printStackTrace();
               }
            catch( IOException inputOutputEx )
               {
                  inputOutputEx.printStackTrace();
               }
         }
   }
