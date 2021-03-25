/*
 * Author: David Hermann
 * Date: 8 April 2020
 * Program: PA10
 * Class: CS136 - 001
 *
 * "This time I didn't procrastinate as much."
 */

package package_10;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class GameArrayClassToo
   {
      // Constants
      public static final int EMPTY_ELEMENT = 0;
      public static final int USED_ELEMENT = -1;
      public static final int WIN_ELEMENT = 1;
      public static final int NO_ELEMENT_FOUND = -999;

      private static final int END_OF_FILE_MARKER = -1;
      private static final int MAX_INPUT_CHARS = 80;
      private static final char SPACE_CHAR = ' ';
      private static final String SPACE_STR = " ";
      private static final char COLON = ':';

      // Class variables
      private int[][] gameArray;
      private int rowCapacity;
      private int colCapacity;
      private int gameScore;

      private FileWriter outFile;
      private FileReader inFile;

      // Constructors

      /**
       *  Generates a new GameArrayClassToo using the resetGame method
       *
       * @param rowCap Row capacity to initialize
       * @param colCap Column capacity to initialize
       */
      public GameArrayClassToo( int rowCap, int colCap )
         {
            resetGame( rowCap, colCap );
         }

      /**
       *  Copies a GameArrayClassToo object to this instance of the class
       *
       * @param copied GameArrayClassToo object to copy
       */
      public GameArrayClassToo( GameArrayClassToo copied )
         {
            this.rowCapacity = copied.rowCapacity;
            this.colCapacity = copied.colCapacity;
            this.gameScore = copied.gameScore;
            gameArray = new int[ rowCapacity ][ colCapacity ];
            this.outFile = null;
            this.inFile = null;

            int rowIndex, colIndex;
            for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
               {
                  for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                     {
                        this.gameArray[ rowIndex ][ colIndex ] =
                                       copied.gameArray[ rowIndex ][ colIndex ];
                     }
               }
         }

      // Methods

      // Getters
      /**
       * @return gameScore
       */
      public int getGameScore()
      {
         return this.gameScore;
      }

      /**
       * @return rowCapacity
       */
      public int getRowCapacity()
      {
         return this.rowCapacity;
      }

      /**
       * @return colCapacity
       */
      public int getColumnCapacity()
      {
         return this.colCapacity;
      }

      /**
       *  Gets a value from gameArray
       *
       *  @param rowIndex Index of the row
       *  @param colIndex Index of the column
       *
       *  @return Element at given parameters if it exists, else returns
       *    NO_ELEMENT_FOUND
       */
      public int getValueAt( int rowIndex, int colIndex )
      {
         int valueAt = NO_ELEMENT_FOUND;

         if( elementExists( rowIndex, colIndex ))
            {
               valueAt = this.gameArray[ rowIndex ][ colIndex ];
            }

         return valueAt;
      }

      /**
       *  If the provided indices are within bounds, set element to USED_ELEMENT
       *
       *  @param rowIndex Index of the row
       *  @param colIndex Index of the column
       */
      public void setElementUsed( int rowIndex, int colIndex )
      {
         if( elementExists( rowIndex, colIndex ))
            {
               this.gameArray[ rowIndex ][ colIndex ] = USED_ELEMENT;
            }
      }

      /**
       *  Prints all elements of gameArray
       */
      public void cheat()
      {
         int rowIndex, colIndex, blockIndex, prefixSpaces, elementToPrint;
         int blockSize = 5;
         String elementToPrintStr;
         System.out.print( "Cheat Screen:\n" );
         for ( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
            {
               for ( colIndex = 0; colIndex < colCapacity; colIndex++ )
                  {
                     elementToPrint = gameArray[ rowIndex ][ colIndex ];
                     elementToPrintStr = String.valueOf( elementToPrint );
                     prefixSpaces = blockSize - elementToPrintStr.length();

                     for( blockIndex = 0; blockIndex <= prefixSpaces;
                                                                  blockIndex++ )
                        {
                           System.out.print( SPACE_STR );
                        }
                     System.out.print( elementToPrintStr );
                  }
               System.out.print( "\n" );
            }
      }

      /**
       *  Decrements the current game's score
       */
      public void decrementGameScore()
      {
         this.gameScore--;
      }

      /**
       *  Starts at element containing WIN_ELEMENT, and uses setNearNeighbors to
       *  generate a new game board
       *  <p>
       *  Called from GameArrayClassToo( int, int ) constructor
       *
       * @param rowCap Maximum row capacity passed from the constructor
       * @param colCap Maximum column capacity passed from the constructor
       */
      public void resetGame( int rowCap, int colCap )
      {
         // Initialize variables, gameArray
         rowCapacity = rowCap;
         colCapacity = colCap;
         gameArray = new int[ rowCapacity ][ colCapacity ];
         gameScore = rowCapacity * colCapacity;
         inFile = null;
         outFile = null;

         // Declare local variables
         int testToSetNeighborsRow = 0, testToSetNeighborsCol = 0;
         int rowIndex, colIndex;
         boolean neighborsSet = true;

         // Set all values to EMPTY_ELEMENT and set a random win spot
         clearGameBoard();
         setWinSpot();

         // Locate the randomly generated win spot
         for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
            {
               for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                  {
                     if( gameArray[ rowIndex ][ colIndex ] == WIN_ELEMENT )
                        {
                           testToSetNeighborsRow = rowIndex;
                           testToSetNeighborsCol = colIndex;
                           // Exit nested loops early
                           rowIndex = rowCapacity;
                           colIndex = colCapacity;
                        }
                  }
            }

         /*
          * This triple nested loop of aggravation fills gameArray with values
          * through the use of the setNearNeighbors method. neighborsSet is
          * initialized to true as the first call of setNearNeighbors will
          * always return true when generating the board around WIN_ELEMENT.
          */

         while( neighborsSet )
            {
               // Make sure neighbors were set when iterating through the entire
               // array
               neighborsSet = false;
               for( rowIndex = testToSetNeighborsRow; rowIndex < rowCapacity;
                                                                    rowIndex++ )
                  {
                     for( colIndex = testToSetNeighborsCol;
                                            colIndex < colCapacity; colIndex++ )
                        {
                           // If the current element exists and is not
                           // EMPTY_ELEMENT
                           if( !(elementIsClear( rowIndex, colIndex )) )
                              {
                                 // Attempt to set neighboring elements around
                                 // current, set flag to true if successful
                                 if( setNearNeighbors( testToSetNeighborsRow,
                                                       testToSetNeighborsCol ) )
                                    {
                                       neighborsSet = true;
                                    }
                              }
                           testToSetNeighborsCol++;
                        }
                     testToSetNeighborsRow++;
                     testToSetNeighborsCol = 0; // Set to 0 to start the next
                  }
               /*
                *  Set to 0 to start the next iteration when one complete
                *  iteration through the array is finished. If a subsequent
                *  iteration does not set any neighbors, the outer while loop
                *  will exit and the method will complete.
                */
               testToSetNeighborsRow = 0;
            }
      }

      /**
       *  Resets all elements of gameArray to EMPTY_ELEMENT
       */
      private void clearGameBoard()
      {
         int rowIndex, colIndex;
         for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
            {
               for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                  {
                     this.gameArray[ rowIndex ][ colIndex ] = EMPTY_ELEMENT;
                  }
            }
      }

      /**
       *  Sets adjacent neighbors to origin element's value + 1 if they are
       *    clear
       *  <p>
       *  If the origin element's value is EMPTY_ELEMENT, no neighbors are set
       *
       * @param rowIndex Row index of the origin element
       * @param colIndex Column index of the origin element
       * @return True if at least one neighbor set, false otherwise
       */
      private boolean setNearNeighbors( int rowIndex, int colIndex )
      {
         boolean neighborSet = false;
         int currentLocationValue;

         if( elementExists( rowIndex, colIndex ) )
            {
               currentLocationValue = gameArray[ rowIndex ][ colIndex ];
               if( currentLocationValue != EMPTY_ELEMENT )
                  {
                     if( elementIsClear( rowIndex + 1, colIndex ) )
                        {
                           gameArray[ rowIndex + 1 ][ colIndex ] =
                                                       currentLocationValue + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex, colIndex + 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex + 1 ] =
                                                       currentLocationValue + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex - 1, colIndex ) )
                        {
                           gameArray[ rowIndex - 1 ][ colIndex ] =
                                                       currentLocationValue + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex, colIndex - 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex - 1 ] =
                                                       currentLocationValue + 1;
                           neighborSet = true;
                        }
                  }
            }

         return neighborSet;
      }

      /**
       *  Tests whether element is clear
       *
       * @param rowIndex Row index of the element
       * @param colIndex Column index of the element
       * @return True if the element contains EMPTY_ELEMENT, false otherwise
       */
      public boolean elementIsClear( int rowIndex, int colIndex )
      {
         boolean isClear = false;

         if( elementExists( rowIndex, colIndex ) )
            {
               if( gameArray[ rowIndex ][ colIndex ] == EMPTY_ELEMENT )
                  {
                     isClear = true;
                  }
            }

         return isClear;
      }

      /**
       *  Tests whether an element has been used
       *
       * @param rowIndex Row index of the element
       * @param colIndex Column index of the element
       * @return True if the element is used, false otherwise
       */
      public boolean elementHasBeenUsed( int rowIndex, int colIndex )
      {
         boolean isUsed = false;

         if( elementExists( rowIndex, colIndex ) )
            {
               if( gameArray[ rowIndex ][ colIndex ] == USED_ELEMENT )
                  {
                     isUsed = true;
                  }
            }

         return isUsed;
      }

      /**
       *  Makes sure indices are within bounds
       *
       * @param rowIndex Row index of the element
       * @param colIndex Column index of the element
       * @return True if it is within the array, false otherwise
       */
      public boolean elementExists( int rowIndex, int colIndex )
      {
         boolean isWithinBounds = false;

         if ( rowIndex < rowCapacity && rowIndex >= 0 &&
                                       colIndex < colCapacity && colIndex >= 0 )
            {
               isWithinBounds = true;
            }

         return isWithinBounds;
      }

      /**
       *  Uses getRandBetween to generate a random indices, then sets that
       *  element to WIN_ELEMENT
       */
      private void setWinSpot()
      {
         int rowRandIndex, colRandIndex;

         rowRandIndex = getRandBetween( 0, rowCapacity - 1 );
         colRandIndex = getRandBetween( 0, colCapacity - 1 );

         gameArray[ rowRandIndex ][ colRandIndex ] = WIN_ELEMENT;
      }

      /**
       * Generates a random number between the given low limit
       * and high limit, inclusive
       *
       * @param lowLimit integer low limit of random value to be generated
       * @param highLimit integer high limit of random value to be generated
       * @return integer result of random generation
       */
      private int getRandBetween( int lowLimit, int highLimit )
         {
            int range = highLimit - lowLimit + 1;

            return (int)( Math.random() * range );
         }

      /**
       *  Retrieves a game from fileName, a text file
       *
       * @param fileName Name of the file to read
       * @return True if the game was successfully retrieved, false otherwise
       */
      public boolean retrieveGame( String fileName )
      {
         boolean successfulRetrieval;
         int capturedElement, rowIndex, colIndex;

         try
            {
               inFile = new FileReader( fileName );

               readString( COLON, MAX_INPUT_CHARS );
               rowCapacity = readInt();
               readString( COLON, MAX_INPUT_CHARS );
               colCapacity = readInt();

               gameArray = new int[ rowCapacity ][ colCapacity ];
               gameScore = rowCapacity * colCapacity;

               for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
                  {
                     for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                        {
                           capturedElement = readInt();
                           gameArray[ rowIndex ][ colIndex ] = capturedElement;
                        }
                  }

               inFile.close();

               successfulRetrieval = true;
            }
         catch( IOException couldNotRetrieve )
            {
               successfulRetrieval = false;
               couldNotRetrieve.printStackTrace();
            }

         return successfulRetrieval;
      }


      /**
       * Captures an integer from inFile's input stream, helper method of
       * retrieveGame
       *
       * @return Captured integer if file stream read successfully, {@literal 0}
       *    otherwise
       */
      private int readInt()
         {
            int charAsInt, theReadInteger;
            char intAsChar;
            String collectionString = "";

            try
               {
                  do
                     {
                        charAsInt = inFile.read();
                        intAsChar = (char) charAsInt;
                     }
                  while( intAsChar <= SPACE_CHAR );

                  do
                     {
                        collectionString += intAsChar;
                        charAsInt = inFile.read();
                        intAsChar = (char) charAsInt;
                     }
                  while( charAsInt != END_OF_FILE_MARKER && intAsChar >= '0' &&
                                                             intAsChar <= '9' );

                  if ( collectionString.length() > 0 )
                     {
                        theReadInteger = Integer.parseInt( collectionString );
                     }
                  else
                     {
                        theReadInteger = 0;
                     }
               }
            catch ( IOException couldNotReadInt )
               {
                  theReadInteger = 0;
               }

            return theReadInteger;
         }

      /**
       * Captures a string from inFile's input stream, helper method of
       * retrieveGame
       *
       * @param delimiter Character to read up to
       * @param maxChars Maximum amount of characters to read
       * @return Captured string if file stream read successfully, empty string
       *    otherwise
       */
      private String readString( char delimiter, int maxChars )
         {
            String stringToReturn;
            char readChar;
            int index = 0;

            try
               {
                  readChar = (char) inFile.read();

                  while( readChar <= SPACE_CHAR && index < maxChars )
                     {
                        readChar = (char) inFile.read();
                        index++;
                     }

                  stringToReturn = "";
                  while( readChar != END_OF_FILE_MARKER &&
                                                         readChar != delimiter )
                     {
                        readChar = (char) inFile.read();
                        stringToReturn += readChar;
                        index++;
                     }
               }
            catch( IOException couldNotReadString )
               {
                  stringToReturn = "";
               }

            return stringToReturn;
         }

      /**
       * Stores a game to a text file, prints stack trace if it cannot write to
       * the file
       *
       * @param fileName File to store to
       */
      public void storeGame( String fileName )
         {
            int rowIndex, colIndex, numElement;
            String printElement;

            try
               {
                  outFile = new FileWriter( fileName );

                  // Write human-readable row and col count at top of file
                  outFile.write( "Number of Rows: " + rowCapacity + "\r\n" );
                  outFile.write( "Number of Columns: " + colCapacity + "\r\n" );
                  outFile.write( "\r\n" );

                  // Write each element of gameArray to the file
                  for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
                     {
                        outFile.write( SPACE_STR + SPACE_STR );
                        for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                           {
                              numElement = gameArray[ rowIndex ][ colIndex ];
                              printElement = Integer.toString( numElement );
                              if( numElement < 10 )
                                 {
                                    outFile.write( SPACE_CHAR );
                                 }
                              outFile.write( printElement + SPACE_CHAR );
                           }
                        outFile.write( "\r\n" );
                     }

                  // Flush and close file
                  outFile.flush();
                  outFile.close();
               }
            catch( IOException couldNotStore )
               {
                  couldNotStore.printStackTrace();
               }
         }
  }
