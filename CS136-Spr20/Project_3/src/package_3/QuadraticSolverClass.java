package package_3;

import javax.swing.JOptionPane;

public class QuadraticSolverClass
   {
    private static final char ENDLINE_CHAR = '\n';
    private static final char SPACE = ' ';
    private static final char VERTICAL_LINE = '|';
    private static final char DOUBLE_HORIZONTAL_LINE = '=';
    private static final char SINGLE_HORIZONTAL_LINE = '-';
    private static final char EQUALS_SIGN = '=';
    private static final double CONST_OF_DISCRIM = 4.0;
    private static final double CONST_OF_DENOM = 2.0;
    private static final double CONST_OF_NUMER = -1.0;
    private static final int PRECISION = 2;

      public static void main( String[] args )
         {
            String coefficientAString, coefficientBString, coefficientCString;
            double coefficientADouble, coefficientBDouble, coefficientCDouble;
            double discriminant, denominator, rootOne, rootTwo;
            int numChars, numSpaces;

            // Get coefficients from user
            coefficientAString =
                           JOptionPane.showInputDialog( "Enter coefficient A" );
            coefficientBString =
                           JOptionPane.showInputDialog( "Enter coefficient B" );
            coefficientCString =
                           JOptionPane.showInputDialog( "Enter coefficient C" );

            // Translate strings to doubles
            coefficientADouble = Double.parseDouble( coefficientAString );
            coefficientBDouble = Double.parseDouble( coefficientBString );
            coefficientCDouble = Double.parseDouble( coefficientCString );

            // Calculate the discriminant
            discriminant = calcDiscrim( coefficientADouble, coefficientBDouble,
                                                           coefficientCDouble );
            // Calculate the denominator
            denominator = calcDenom( coefficientADouble );
            // Calculate roots
            rootOne = calcRoot( coefficientBDouble, -discriminant, denominator );
            rootTwo = calcRoot( coefficientBDouble, discriminant, denominator );

            // Print table with results
            // Line 0
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 41;
            printChars( numChars, DOUBLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 1
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 41;
            printCenterJustifiedString( "Quadratic Root Solver Program",
                                                                    numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 2
            printChars( numChars, VERTICAL_LINE );
            numChars = 25;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 15;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 3
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 25;
            printLeftJustifiedString( " Coefficient A", numSpaces );
            printChars( numChars, EQUALS_SIGN );
            numSpaces = 15;
            printRightJustifiedDouble( coefficientADouble, PRECISION,
                                                                    numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 4
            printChars( numChars, VERTICAL_LINE );
            numChars = 25;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 15;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 5
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 25;
            printLeftJustifiedString( " Coefficient B", numSpaces );
            printChars( numChars, EQUALS_SIGN );
            numSpaces = 15;
            printRightJustifiedDouble( coefficientBDouble, PRECISION,
                                                                    numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 6
            printChars( numChars, VERTICAL_LINE );
            numChars = 25;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 15;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 7
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 25;
            printLeftJustifiedString( " Coefficient C", numSpaces );
            printChars( numChars, EQUALS_SIGN );
            numSpaces = 15;
            printRightJustifiedDouble( coefficientCDouble, PRECISION,
                                                                    numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 8
            printChars( numChars, VERTICAL_LINE );
            numChars = 25;
            printChars( numChars, DOUBLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 15;
            printChars( numChars, DOUBLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 9
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 25;
            printLeftJustifiedString( " Root One (result)", numSpaces );
            printChars( numChars, EQUALS_SIGN );
            numSpaces = 15;
            printRightJustifiedDouble( rootOne, PRECISION, numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 10
            printChars( numChars, VERTICAL_LINE );
            numChars = 25;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            numChars = 15;
            printChars( numChars, SINGLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 11
            printChars( numChars, VERTICAL_LINE );
            numSpaces = 25;
            printLeftJustifiedString( " Root Two (result)", numSpaces );
            printChars( numChars, EQUALS_SIGN );
            numSpaces = 15;
            printRightJustifiedDouble( rootTwo, PRECISION, numSpaces );
            printChars( numChars, VERTICAL_LINE );
            printEndLine();

            // Line 12
            printChars( numChars, VERTICAL_LINE );
            numChars = 41;
            printChars( numChars, DOUBLE_HORIZONTAL_LINE );
            numChars = 1;
            printChars( numChars, VERTICAL_LINE );
            printEndLine();
         }
      // End of main method

    public static double calcDiscrim( double coefficientA, double coefficientB,
                                                          double coefficientC )
    {
       double  coefficientBSquared, discriminantUnderRadical, discriminant;

       coefficientBSquared =  coefficientB * coefficientB;
       discriminantUnderRadical = coefficientBSquared - ( CONST_OF_DISCRIM * coefficientA *
                                                                 coefficientC );
       discriminant = Math.sqrt( discriminantUnderRadical );

       return discriminant;
    }

    public static double calcDenom( double coefficientA )
    {
       double denominator;

       denominator = CONST_OF_DENOM * coefficientA;

       return denominator;
    }

    public static double calcRoot( double coefficientB, double discriminant,
                                                            double denominator )
    {
       double numerator, root;

       numerator = ( CONST_OF_NUMER * coefficientB ) - discriminant;
       root = numerator / denominator;

       return root;
    }

    // Supporting Methods - DO NOT MAKE ANY CHANGES TO THESE METHODS ///////////

    public static void printEndLine()
       {
        System.out.print( ENDLINE_CHAR );
       }

    public static void printChars( int numChars, char outChar )
       {
        if( numChars > 0 )
           {
            printChars( numChars - 1, outChar );

            System.out.print( outChar );
           }
       }

    public static void printCenterJustifiedString( String outString,
                                                                 int blockSize )
       {
        int preSpaces = blockSize / 2 - outString.length() / 2;
        int postSpaces = blockSize - preSpaces - outString.length();

        printChars( preSpaces, SPACE );
        System.out.print( outString );
        printChars( postSpaces, SPACE );
       }

    public static void printLeftJustifiedString( String outString,
                                                                 int blockSize )
       {
        int postSpaces = blockSize - outString.length();

        System.out.print( outString );
        printChars( postSpaces, SPACE );
       }

    public static void printRightJustifiedString( String outString,
                                                                 int blockSize )
       {
        int preSpaces = blockSize - outString.length();

        printChars( preSpaces, SPACE );
        System.out.print( outString );
       }

    public static void printCenterJustifiedDouble( double outVal,
                                                  int precision, int blockSize )
       {
        String outStr = setPrecision( outVal, precision );

        printCenterJustifiedString( outStr, blockSize );
       }

    public static void printLeftJustifiedDouble( double outVal,
                                                  int precision, int blockSize )
       {
        String outStr = setPrecision( outVal, precision );

        printLeftJustifiedString( outStr, blockSize );
       }

    public static void printRightJustifiedDouble( double outVal,
                                                  int precision, int blockSize )
       {
        String outStr = setPrecision( outVal, precision );

        printRightJustifiedString( outStr, blockSize );
       }

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
