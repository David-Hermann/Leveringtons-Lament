package package_2;

import javax.swing.JOptionPane;

public class IdealGasLawClass
   {
    private static final char ENDLINE_CHAR = '\n';
    private static final char SPACE = ' ';
    private static final char EQUAL_SIGN = '=';
    private static final char VERTICAL_BAR = '|';
    private static final char DASH = '-';
    private static final double IDEAL_GAS_LAW_CONST = 8.314;
    private static final int PRECISION = 2;
    
      public static void main( String[] args )
         {
            /*
             * Programmer's Note: I used string literals for the input
             * dialog prompts and the single use strings of the formatted table.
             * If this disobeys the GUR, please inform me.
             */
            // Variable declarations
            String input_mols, input_temperature, input_volume;
            double mols, temperature, volume, pressure;
            int numChars, numSpaces;
            
            // Grab user input with JOptionPane
            input_mols = JOptionPane.showInputDialog( "Enter number of mols" );
            input_temperature = 
                  JOptionPane.showInputDialog( "Enter temperature (K)" );
            input_volume = 
                  JOptionPane.showInputDialog( "Enter volume (N/m^2)" );
            
            // Convert user's input from strings to doubles
            mols = Double.valueOf( input_mols );
            temperature = Double.valueOf( input_temperature );
            volume = Double.valueOf( input_volume );
            
            /*
             * Calculate the pressure according to the Ideal Gas Law
             * using 8.314 J/(mol*K) as the constant
            */
            pressure = (( mols * IDEAL_GAS_LAW_CONST * temperature ) / volume );
            
            // Display Table
            // Line 0 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 36;
            printChars( numChars, EQUAL_SIGN );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 1 - Header cells
            numSpaces = 20;
            printChars( numChars, VERTICAL_BAR );
            printCenterJustifiedString( "Value Name", numSpaces);
            numSpaces = 15;
            printChars( numChars, VERTICAL_BAR );
            printCenterJustifiedString( "Value", numSpaces );
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 2 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 20;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 15;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 3 - Number of mols cells
            numSpaces = 20;
            printChars( numChars, VERTICAL_BAR );
            printLeftJustifiedString( " Number of mols", numSpaces);
            numSpaces = 14;
            printChars( numChars, EQUAL_SIGN );
            printRightJustifiedDouble( mols, PRECISION, numSpaces );
            numChars = 1;
            printChars( numChars, SPACE);
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 4 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 20;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 15;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 5 - Temperature cells
            numSpaces = 20;
            printChars( numChars, VERTICAL_BAR );
            printLeftJustifiedString( " Temperature", numSpaces);
            numSpaces = 14;
            printChars( numChars, EQUAL_SIGN );
            printRightJustifiedDouble( temperature, PRECISION, numSpaces );
            numChars = 1;
            printChars( numChars, SPACE);
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 6 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 20;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 15;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 7 - Volume cells
            numSpaces = 20;
            printChars( numChars, VERTICAL_BAR );
            printLeftJustifiedString( " Volume", numSpaces);
            numSpaces = 14;
            printChars( numChars, EQUAL_SIGN );
            printRightJustifiedDouble( volume, PRECISION, numSpaces );
            numChars = 1;
            printChars( numChars, SPACE);
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 8 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 20;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 15;
            printChars( numChars, DASH );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 9 - Pressure cells
            numSpaces = 20;
            printChars( numChars, VERTICAL_BAR );
            printLeftJustifiedString( " Pressure (Result)", numSpaces);
            numSpaces = 14;
            printChars( numChars, EQUAL_SIGN );
            printRightJustifiedDouble( pressure, PRECISION, numSpaces );
            numChars = 1;
            printChars( numChars, SPACE);
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
            
            // Line 10 - Dividing lines
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            numChars = 36;
            printChars( numChars, EQUAL_SIGN );
            numChars = 1;
            printChars( numChars, VERTICAL_BAR );
            printEndLine();
         }

// Supporting Methods - DO NOT MAKE ANY CHANGES TO THESE METHODS ///////////////

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
