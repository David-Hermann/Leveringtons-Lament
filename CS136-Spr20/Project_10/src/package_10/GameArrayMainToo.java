/*
 * Author: David Hermann
 * Date: 8 April 2020
 * Program: PA10
 * Class: CS136 - 001
 *
 * "This time I didn't procrastinate as much."
 */

package package_10;

import javax.swing.JOptionPane;

/**
 * Driver class for using GameArrayClassToo to develop game
 *
 * @author MichaelL
 *
 */
public class GameArrayMainToo
   {

       /**
        * Main driver method
        *
        * @param args String array of arguments passed from console
        */
       public static void main( String[] args )
          {
              int rowCap = 10, colCap = 15;

              GameArrayClassToo mainGameOne = new GameArrayClassToo( rowCap,
                                                                       colCap );

              playGame( mainGameOne );
          }

       /**
        * Plays game using tools from GameArrayClassToo
        *
        * @param game GameArrayClassToo object that was initialized in main
        */
       public static void playGame( GameArrayClassToo game )
          {
             String fetchSavedGameStr, saveCurrentGameStr, fileNameStr;
             String tryRowStr, tryColStr, guessCoords;
             char fetchSavedGameChar, saveCurrentGameChar;
             int rowCap, colCap, tryRow, tryCol, retrievedGuess, currentScore;
             boolean gameIsNotFinished = true;

             rowCap = game.getRowCapacity();
             colCap = game.getColumnCapacity();

             fetchSavedGameStr = JOptionPane.showInputDialog( "Do you want to "
                                     + "play a previously stored game (Y/N)?" );
             fetchSavedGameChar = fetchSavedGameStr.charAt( 0 );

             switch( fetchSavedGameChar )
                {
                   case 'y':
                   case 'Y':
                      fileNameStr = JOptionPane.showInputDialog( "Enter file "
                                                                    + "name:" );
                      game.retrieveGame( fileNameStr );
                      break;
                   default:
                      System.out.println( "New game created." );
                }

             GameArrayClassToo copyOfCurrentGameUnplayed =
                                                  new GameArrayClassToo( game );

             while( gameIsNotFinished )
                {
                   tryRowStr = JOptionPane.showInputDialog( "Enter row index "
                                + "between 0 " + " and " + (rowCap - 1) + ":" );
                   tryColStr = JOptionPane.showInputDialog( "Enter col index "
                                + "between 0 " + " and " + (colCap - 1) + ":" );
                   tryRow = Integer.parseInt( tryRowStr );
                   tryCol = Integer.parseInt( tryColStr );

                   guessCoords = "(" + tryRowStr + ", " + tryColStr + ")";

                   currentScore = game.getGameScore();

                   if( game.elementExists( tryRow, tryCol ) )
                      {
                         retrievedGuess = game.getValueAt( tryRow, tryCol );

                         if( retrievedGuess != GameArrayClassToo.WIN_ELEMENT )
                            {
                               game.decrementGameScore();
                               currentScore = game.getGameScore();
                               if( !( game.elementHasBeenUsed( tryRow,
                                                                    tryCol ) ) )
                                  {
                                     System.out.println( "Value " +
                                                         retrievedGuess +
                                                         " found at " +
                                                         guessCoords +
                                                         "; your score is " +
                                                         " now " +
                                                         currentScore );

                                     game.setElementUsed( tryRow, tryCol );
                                  }
                               else
                                  {
                                     System.out.println( "You have already "
                                                + "tried the " + "element at " +
                                                 guessCoords + "; your score " +
                                                     "is now " + currentScore );
                                  }
                            }
                         else
                            {
                               System.out.println( "YOU FOUND IT at " +
                                        guessCoords + "! Your final score is " +
                                                                 currentScore );
                               gameIsNotFinished = false;
                            }
                      }
                   else
                      {
                         game.decrementGameScore();
                         currentScore = game.getGameScore();
                         System.out.println( "The element at " + guessCoords +
                               " is " + "outside the boundaries of your game; "
                                        + "your score is now " + currentScore );
                      }
                }

             saveCurrentGameStr = JOptionPane.showInputDialog( "Do you want to "
                                                   + "store this game (Y/N)?" );
             saveCurrentGameChar = saveCurrentGameStr.charAt( 0 );

             switch( saveCurrentGameChar )
                {
                   case 'y':
                   case 'Y':
                      fileNameStr = JOptionPane.showInputDialog( "Enter file "
                                                                    + "name:" );
                      copyOfCurrentGameUnplayed.storeGame( fileNameStr );
                      break;
                   default:
                      System.out.println( "Current game not saved." );
                }

          }
   }
