/*
 * Author: David Hermann
 * Date: 1 April 2020
 * Program: PA09
 * Class: CS136 - 001
 *
 * "Either start now or it's too late huh? That's solid advice."
 */

package package_9;

import javax.swing.JOptionPane;

public class GameArrayClass
   {
      // Constants
      private final int EMPTY_ELEMENT = 0;
      private final int USED_ELEMENT = -1;
      private final int WIN_ELEMENT = 1;

      // Attributes
      private int[][] gameArray;
      private int rowCapacity;
      private int colCapacity;
      private int gameScore;

      // Constructors
      public GameArrayClass( int rowCap, int colCap )
      {
         resetGame( rowCap, colCap );
      }

      public GameArrayClass( GameArrayClass copied )
      {
         this.rowCapacity = copied.rowCapacity;
         this.colCapacity = copied.colCapacity;
         this.gameScore = copied.gameScore;
         gameArray = new int[ rowCapacity ][ colCapacity ];

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
      /**
       * Generates a random number between the given low limit
       * and high limit, inclusive
       *
       * @param lowLimit integer low limit of random value to be generated
       *
       * @param highLimit integer high limit of random value to be generated
       *
       * @return integer result of random generation
       */
      private int getRandBetween( int lowLimit, int highLimit )
         {
            int range = highLimit - lowLimit + 1;

            return (int)( Math.random() * range );
         }

      // Resets all elements of gameArray to EMPTY_ELEMENT
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

      // Prints all elements of gameArray
      public void cheat()
      {
         System.out.print( "Cheat Screen:\n" );
         int rowIndex, colIndex;
         for ( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
            {
               for ( colIndex = 0; colIndex < colCapacity; colIndex++ )
                  {
                     System.out.print( gameArray[ rowIndex ][ colIndex ] +
                                                                        "   " );
                  }
               System.out.print( "\n" );
            }
      }

      // Makes sure indices are within bounds
      private boolean elementExists( int rowIndex, int colIndex )
      {
         boolean isWithinBounds = false;

         if ( rowIndex < rowCapacity && rowIndex >= 0 &&
                                       colIndex < colCapacity && colIndex >= 0 )
            {
               isWithinBounds = true;
            }

         return isWithinBounds;
      }

      // Tests whether element has been used
      private boolean elementHasBeenUsed( int rowIndex, int colIndex )
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

      // Tests whether element is clear
      private boolean elementIsClear( int rowIndex, int colIndex )
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

      // Uses getRandBetween to generate a random win condition
      private void setWinSpot()
      {
         int rowRandIndex, colRandIndex;

         rowRandIndex = getRandBetween( 0, rowCapacity - 1 );
         colRandIndex = getRandBetween( 0, colCapacity - 1 );
         gameArray[ rowRandIndex ][ colRandIndex ] = WIN_ELEMENT;
      }

      // Sets adjacent neighbors to origin element + 1, returns true if at least
      // one neighbor set
      private boolean setNearNeighbors( int rowIndex, int colIndex )
      {
         boolean neighborSet = false;

         if( elementExists( rowIndex, colIndex ) )
            {
               int currentLoc = gameArray[ rowIndex ][ colIndex ];

               if( currentLoc != EMPTY_ELEMENT )
                  {
                     if( elementIsClear( rowIndex + 1, colIndex ) )
                        {
                           gameArray[ rowIndex + 1 ][ colIndex ] = currentLoc
                                                                            + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex, colIndex + 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex + 1 ] =
                                                                 currentLoc + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex - 1, colIndex ) )
                        {
                           gameArray[ rowIndex - 1 ][ colIndex ] = currentLoc
                                                                            + 1;
                           neighborSet = true;
                        }
                     if( elementIsClear( rowIndex, colIndex - 1 ) )
                        {
                           gameArray[ rowIndex ][ colIndex - 1 ] =
                                                                 currentLoc + 1;
                           neighborSet = true;
                        }
                  }
            }

         return neighborSet;
      }

      // Starts at element containing WIN_ELEMENT, and uses setNearNeighbors to
      // generate a new game board - called from GameArrayClass(int,int) constr
      public void resetGame( int rowCap, int colCap )
      {
         rowCapacity = rowCap;
         colCapacity = colCap;
         gameArray = new int[ rowCapacity ][ colCapacity ];
         gameScore = rowCapacity * colCapacity;

         //int winSpotRow = 0, winSpotCol = 0, elementsLeft = gameScore;

         clearGameBoard();
         setWinSpot();
         setNearNeighbors( 0, 0 );

         /*
         int rowIndex, colIndex;
         for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
            {
               for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                  {
                     if( gameArray[ rowIndex ][ colIndex ] == WIN_ELEMENT )
                        {
                           setNearNeighbors( rowIndex, colIndex );
                           winSpotRow = rowIndex;
                           winSpotCol = colIndex;
                           // Exit nested loops early
                           rowIndex = rowCapacity;
                           colIndex = colCapacity;
                        }
                  }
            }
         */

         /*
         do
            {
               unusedElement = elementHasBeenUsed( winSpotRow, winSpotCol );
               elementExists = elementExists( winSpotRow, winSpotCol );


            }
         while( unusedElement && elementsLeft > 0 );
         */
      }

      // The beef of the game runtime
      public boolean tryElement()
      {
         boolean winCondition = false; // You are (not) a winner
         String tryRowStr, tryColStr, guessCoords;
         int tryRow, tryCol, retrievedGuess;
         tryRowStr = JOptionPane.showInputDialog( "Enter row index between 0 " +
                                             " and " + this.rowCapacity + ":" );
         tryColStr = JOptionPane.showInputDialog( "Enter col index between 0 " +
                                             " and " + this.colCapacity + ":" );
         tryRow = Integer.parseInt( tryRowStr );
         tryCol = Integer.parseInt( tryColStr );

         guessCoords = "(" + tryRow + ", " + tryCol + ")";
         retrievedGuess = this.gameArray[ tryRow ][ tryCol ];

         if( elementExists( tryRow, tryCol ) )
            {
               if( retrievedGuess != WIN_ELEMENT )
                  {
                     this.gameScore--;
                     if( !( elementHasBeenUsed( tryRow, tryCol ) ) )
                        {
                           System.out.println( "Value " + retrievedGuess +
                                 " found at " + guessCoords + "; your score is "
                                                   + " now " + this.gameScore );
                        }
                     else
                        {
                           System.out.println( "You have already tried the "
                                 + "element at " + guessCoords + "; your score "
                                                 + "is now " + this.gameScore );
                        }
                  }
               else
                  {
                     System.out.println( "YOU FOUND IT at " + guessCoords +
                                    "! Your final score is " + this.gameScore );
                     winCondition = true;
                  }
            }
         else
            {
               System.out.println( "The element at " + guessCoords + " is "
                     + "outside the boundaries of your game; your score is now "
                                                             + this.gameScore );
            }


         return winCondition;
      }
   }
