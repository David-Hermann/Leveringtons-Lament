/*
 * Author: David Hermann
 * Date: 4th March 2020
 * Program: PA06
 * Class: CS136 - 001
 *
 * "Well CS200 is doing this, why not here?"
 */

package package_6;

import javax.swing.JOptionPane;

public class QuadraticSolverClass_2
   {
      private static final char SINGLE_HORIZONTAL_LINE = '-';
      private static final char DOUBLE_HORIZONTAL_LINE = '=';
      private static final char ENDLINE_CHAR = '\n';
      private static final char SPACE = ' ';
      private static final char VERTICAL_LINE = '|';
      private static final char EQUALS_SIGN = '=';
      private static final int EQUALS_SIGN_COUNT = 1;
      private static final int VERTICAL_LINE_COUNT = 1;
      private static final int SINGLE_SPACE_COUNT = 1;
      private static final int STANDARD_PRECISION = 2;
      private static final int FULL_LINE_BLOCK_SIZE = 41;
      private static final int LEFT_SIDE_BLOCK_SIZE = 24;
      private static final int RIGHT_SIDE_BLOCK_SIZE = 14;
      private static final int SINGLE_LINE = 1001;
      private static final int DOUBLE_LINE = 2002;
      private static final int ONE_ROOT = 3003;
      private static final int TWO_ROOTS = 4004;
      private static final int NO_ROOTS = 5005;
      private static final double CONST_OF_DISCRIM = 4.0;
      private static final double CONST_OF_DENOM = 2.0;

      // Main driver for quadratic root solver program
      public static void main( String[] args )
         {
            // Variable declarations
            double coeffA, coeffB, coeffC, discriminant, discriminantSqrt;
            double denominator, rootOne = 0, rootTwo = 0;
            int rootFlag;

            // Get coefficients from user
            coeffA = getCoefficient( "A" );
            coeffB = getCoefficient( "B" );
            coeffC = getCoefficient( "C" );

            // Calculate discriminant, denominator, amount of roots
            discriminant = calcDiscrim( coeffA, coeffB, coeffC );
            denominator = calcDenom( coeffA );
            rootFlag = setRootFlag( discriminant );

            /* Programmer's Note: I decided to leave the check for negative
             * discriminant values in main as the specifications imply Math.sqrt
             * exists in the main method instead of writing a separate method to
             * perform a logic check and any calculations.
             */
            if( rootFlag != NO_ROOTS )
               {
                  discriminantSqrt = Math.sqrt( discriminant );
                  if( rootFlag == TWO_ROOTS )
                     {
                        rootOne = calcRoot( -coeffB, discriminantSqrt,
                                                                   denominator);
                        rootTwo = calcRoot( -coeffB, -discriminantSqrt,
                                                                   denominator);
                     }
                  else
                     {
                        rootOne = calcRoot( -coeffB, discriminantSqrt,
                                                                   denominator);
                     }
               }

            // Print results to the console in tabulated form
            printResults( rootFlag, coeffA, coeffB, coeffC, rootOne, rootTwo );

         }

      // Acquires the coefficient from the user as a String; converts to double
      // and returns
      public static double getCoefficient( String coefLetter )
         {
            String coefficientStr;
            double coefficient;

            coefficientStr = JOptionPane.showInputDialog( "Enter coefficient" +
                                                             coefLetter + ":" );
            coefficient = Double.parseDouble( coefficientStr );

            return coefficient;
         }

      // Calculates the discriminant for the root calculation equation
      public static double calcDiscrim( double cofA, double cofB, double cofC )
         {
            double  coefficientBSquared, fourAC, discriminant;

            coefficientBSquared =  cofB * cofB;
            fourAC = CONST_OF_DISCRIM * cofA * cofC;
            discriminant = coefficientBSquared - fourAC;

            return discriminant;
         }

      // Calculates the denominator for use in the quadratic root equation
      public static double calcDenom( double cofA )
         {
            double denominator;

            denominator = CONST_OF_DENOM * cofA;

            return denominator;
         }

      // Sets root flag to appropriate code depending on status of the
      // discriminant. Assumes discriminant is negative in case of errors.
      public static int setRootFlag( double discrim )
         {
            int rootFlag = NO_ROOTS;

            if( discrim > 0 )
               {
                  rootFlag = TWO_ROOTS;
               }
            else if( discrim == 0 )
               {
                  rootFlag = ONE_ROOT;
               }

            return rootFlag;
         }

      // Calculates one root of a quadratic equation
      public static double calcRoot( double cofB, double discSqrRt,
                                                                  double denom )
         {
            double numerator, root;

            numerator = cofB + discSqrRt;
            root = numerator / denom;

            return root;
         }

      /* I tried my hand at a Javadoc and promptly decided to not do Javadocs
       * for the rest of the methods
       */

      /**
       * Intakes multiple data to print to console
       *
       * @param controlCode Tells method how many roots are present
       *
       * @param cofA Coefficient A of the quadratic equation
       *
       * @param cofB Coefficient B of the quadratic equation
       *
       * @param cofC Coefficient C of the quadratic equation
       *
       * @param rootOne First root of the quadratic equation, controlCode must
       *    not be NO_ROOTS for this to be printed
       *
       * @param rootTwo Second root of the quadratic equation, controlCode must
       *    be TWO_ROOTS for this to be printed
       */
      public static void printResults( int controlCode, double cofA,
                      double cofB, double cofC, double rootOne, double rootTwo )
         {
            // Line 0
            printDividerLine( DOUBLE_LINE );
            // Line 1
            printTableTitle();
            // Line 2
            printDividerLine( SINGLE_LINE );
            // Line 3
            printDataDisplayLine( "Coefficient A", cofA );
            // Line 4
            printDividerLine( SINGLE_LINE );
            // Line 5
            printDataDisplayLine( "Coefficient B", cofB );
            // Line 6
            printDividerLine( SINGLE_LINE );
            // Line 7
            printDataDisplayLine( "Coefficient C", cofC );
            // Line 8
            printDividerLine( DOUBLE_LINE );
            // Evaluate how many roots need to be printed

            if ( controlCode != NO_ROOTS )
               {
                  if( controlCode == TWO_ROOTS )
                     {
                        printDataDisplayLine( "Root One (result)", rootOne );
                        printDividerLine( SINGLE_LINE );
                        printDataDisplayLine( "Root Two (result)", rootTwo );
                     }
                  else
                     {
                        printDataDisplayLine( "Root One (result)", rootOne );
                     }

               }
            else
               {
                  printStringDisplayLine( "Imaginary (result)", "No Roots" );
               }
            printDividerLine( DOUBLE_LINE );
         }

      // Supporting Methods - DO NOT MAKE ANY CHANGES TO THESE METHODS /////////

      /**
       * Prints double value center justified
       *
       * @param outVal double value to be printed
       *
       * @param precision integer value specifying number of digits
       * to the right of the decimal point
       *
       * @param blockSize integer size of text block within which
       * to place double value
       */
      public static void printCenterJustifiedDouble( double outVal,
                                                    int precision, int blockSize )
         {
          String outStr = setPrecision( outVal, precision );

          printCenterJustifiedString( outStr, blockSize );
         }

      /**
       * Prints String value center justified
       *
       * @param outString String value to be printed
       *
       * @param blockSize integer size of text block within which
       * to place String value
       */
      public static void printCenterJustifiedString( String outString,
                                                                   int blockSize )
         {
          int preSpaces = blockSize / 2 - outString.length() / 2;
          int postSpaces = blockSize - preSpaces - outString.length();

          printChars( preSpaces, SPACE );
          System.out.print( outString );
          printChars( postSpaces, SPACE );
         }

      /**
       * Prints a specified number of characters
       *
       * @param numChars integer number of characters to be printed
       *
       * @param outChar character value to be printed
       */
      public static void printChars( int numChars, char outChar )
         {
          if( numChars > 0 )
             {
              printChars( numChars - 1, outChar );

              System.out.print( outChar );
             }
         }

      /**
       * Prints data line with double result
       */
      public static void printDataDisplayLine( String prompt, double value )
         {
             printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
             printChars( SINGLE_SPACE_COUNT, SPACE );
             printLeftJustifiedString( prompt, LEFT_SIDE_BLOCK_SIZE );
             printChars( EQUALS_SIGN_COUNT, EQUALS_SIGN );
             printRightJustifiedDouble( value,
                                       STANDARD_PRECISION, RIGHT_SIDE_BLOCK_SIZE );
             printChars( SINGLE_SPACE_COUNT, SPACE );
             printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
             printEndLine();
         }

      /**
       * Prints internal table divider line with single or double line
       * depending on line type control integer; line has vertical lines
       * on each end and a vertical line character divider
       *
       * @param lineType integer flag indicating whether to use
       * single or double divider lines
       */
      public static void printDividerLine( int lineType )
         {
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );

          if( lineType == SINGLE_LINE )
             {
              printChars( FULL_LINE_BLOCK_SIZE, SINGLE_HORIZONTAL_LINE );
             }

          else
             {
              printChars( FULL_LINE_BLOCK_SIZE, DOUBLE_HORIZONTAL_LINE );
             }

          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );

          printEndLine();
         }

      /**
       * Ends text line on output screen
       */
      public static void printEndLine()
         {
          System.out.print( ENDLINE_CHAR );
         }

      /**
       * Prints double value justified to the left
       *
       * @param outVal double value to be printed
       *
       * @param precision integer value specifying number of digits
       * to the right of the decimal point
       *
       * @param blockSize integer size of text block within which
       * to place double value
       */
      public static void printLeftJustifiedDouble( double outVal,
                                                    int precision, int blockSize )
         {
          String outStr = setPrecision( outVal, precision );

          printLeftJustifiedString( outStr, blockSize );
         }

      /**
       * Prints String value justified to the left
       *
       * @param outString String value to be printed
       *
       * @param blockSize integer size of text block within which
       * to place String value
       */
      public static void printLeftJustifiedString( String outString,
                                                                   int blockSize )
         {
          int postSpaces = blockSize - outString.length();

          System.out.print( outString );
          printChars( postSpaces, SPACE );
         }

      /**
       * Prints double value justified to the right
       *
       * @param outVal double value to be printed
       *
       * @param precision integer value specifying number of digits
       * to the right of the decimal point
       *
       * @param blockSize integer size of text block within which
       * to place double value
       */
      public static void printRightJustifiedDouble( double outVal,
                                                    int precision, int blockSize )
         {
          String outStr = setPrecision( outVal, precision );

          printRightJustifiedString( outStr, blockSize );
         }

      /**
       * Prints String value justified to the right
       *
       * @param outString String value to be printed
       *
       * @param blockSize integer size of text block within which
       * to place String value
       */
      public static void printRightJustifiedString( String outString,
                                                                   int blockSize )
         {
          int preSpaces = blockSize - outString.length();

          printChars( preSpaces, SPACE );
          System.out.print( outString );
         }

      /**
       * Prints a solid double line, using equals sign characters
       */
      public static void printSolidDoubleLine()
         {
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printChars( FULL_LINE_BLOCK_SIZE, DOUBLE_HORIZONTAL_LINE );
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printEndLine();
         }

      /**
       * Prints data line with double result
       */
      public static void printStringDisplayLine( String prompt, String rightSide )
         {
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printChars( SINGLE_SPACE_COUNT, SPACE );
          printLeftJustifiedString( prompt, LEFT_SIDE_BLOCK_SIZE );
          printChars( EQUALS_SIGN_COUNT, EQUALS_SIGN );
          printRightJustifiedString( rightSide, RIGHT_SIDE_BLOCK_SIZE );
          printChars( SINGLE_SPACE_COUNT, SPACE );
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printEndLine();
         }

      /**
       * Prints formatted table title
       */
      public static void printTableTitle()
         {
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printCenterJustifiedString(
                          "Quadratic Root Solver Program", FULL_LINE_BLOCK_SIZE );
          printChars( VERTICAL_LINE_COUNT, VERTICAL_LINE );
          printEndLine();
         }

      /**
       * Accepts double value and precision (i.e., number of digits to the right
       * of the decimal point); returns String representation of value
       * with specified precision
       *
       * @param outVal double value to be processed
       *
       * @param precision integer value specifying precision
       * as specified in description
       *
       * @return String value holding input value with specified precision
       */
      public static String setPrecision( double outVal, int precision )
         {
          int tempPrecision = precision + 1;
          int wkgIntVal, outValInt = (int)outVal;
          double outValFraction = Math.abs( outVal - outValInt );
          String outValStr = "";

          while( tempPrecision > 1 )
             {
              outValFraction *= 10;

              wkgIntVal = (int)outValFraction;

              outValStr += wkgIntVal;

              outValFraction -= wkgIntVal;

              tempPrecision--;
             }

          outValFraction *= 100;

          if( outValFraction > 45 )
             {
              wkgIntVal = Integer.parseInt( outValStr ) + 1;

              outValStr = Integer.toString( wkgIntVal );
             }

          outValStr = Integer.toString( outValInt ) + "." + outValStr;

          return outValStr;
         }

   }