package p1_package;

/**
 * Date: 26 August 2020
 * <p>
 * Program: PA01
 * <p>
 * Class: CS249 - 002
 * <p>
 * "Just like the simulations in CS200"
 * <p>
 * Class used for managing a single based-defined (non-decimal) value
 * <p>
 * Contains array with digits and the number of valid digits in the array, and a
 * Boolean flag in case any of the operations cause an overflow
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class RegisterClass
   {
      /**
       * Lowest base value that can be used
       */
      private static final int MIN_BASE_VALUE = 2;

      /**
       * Highest base value that can be used
       */
      private static final int MAX_BASE_VALUE = 9;

      /**
       * Holds base of given value
       */
      public int base;

      /**
       * Array for holding digits in the given number
       */
      public int[] digitArray;

      /**
       * Maximum number of digits that can be held for this value
       */
      public int maxDigits;

      /**
       * Current number of digits held for this value
       */
      public int numDigits;

      /**
       * Flag set if register is overflowed by any operation
       */
      public boolean overFlow;

      /**
       * Default constructor for RegisterClass
       * <p>
       * Exception: attempt to set base outside of limit (2-9 inclusive) will
       * result in nulled object (all values set to zero and array set to null)
       * with overflow flag set
       *
       * @param baseSet     Sets base of RegisterClass
       * @param maxDigitSet Sets maximum number of digits of value
       * @param decValSet   Accepts a decimal value to be stored as the
       *                    RegisterClass value
       */
      public RegisterClass( int baseSet, int maxDigitSet, int decValSet )
         {
            if ( baseSet >= MIN_BASE_VALUE && baseSet <= MAX_BASE_VALUE )
               {
                  base = baseSet;
                  maxDigits = maxDigitSet;
                  numDigits = 0;
                  overFlow = false;
                  digitArray = decToBase( decValSet );
                  clearLeadingZeros();
               }
            else // The base is unacceptable, nullify object
               {
                  base = 0;
                  maxDigits = 0;
                  digitArray = null;
                  numDigits = 0;
                  overFlow = true;
               }
         }

      /**
       * Copy constructor for DigitClass
       *
       * @param copied DigitClass object to be copied to this object
       */
      public RegisterClass( RegisterClass copied )
         {
            // Set primitives by copying them from "copied" object
            this.base = copied.base;
            this.maxDigits = copied.maxDigits;
            this.numDigits = copied.numDigits;
            this.overFlow = copied.overFlow;
            // Create space in memory for this.digitArray
            this.digitArray = new int[ this.maxDigits ];

            // Copy values from copied.digitArray
            int index;
            for ( index = 0; index < this.maxDigits; index++ )
               {
                  this.digitArray[ index ] = copied.digitArray[ index ];
               }
         }

      /**
       * Clears leading zeros of register by reducing element size
       */
      public void clearLeadingZeros()
         {
            // numDigits always reset to maxDigits in case information got lost
            // and this method will recover it automatically
            // numDigits = maxDigits;
            int index = numDigits - 1;

            // Check if overflow hasn't occurred
            if ( !( overFlow ) )
               {
                  // If this is not zero
                  if( !isZero() )
                     {
                        // Since the number's "digits" are stored from least
                        // significant to most, use maxDigits to "truncate" zeroes
                        // (but they're still in the array)
                        while( ( index >= 0 ) && ( digitArray[ index ] == 0 ) )
                           {
                              numDigits--;
                              index--;
                           }
                     }
                  // Else do nothing
               }
         }

      /**
       * Converts given positive integer input to the specified base within the
       * object
       *
       * @param decValue decimal value to be converted
       * @return integer array holding the converted value, or null if base is
       * outside range (1 less than range less than 10), if this occurs, the
       * overflow flag is also set
       */
      private int[] decToBase( int decValue )
         {
            int[] decimalNumberConvertedToBase;
            int quotient, dividend = decValue, remainder;
            // Create new array with all elements set to 0
            decimalNumberConvertedToBase = initializeDigits();

            /*
             * Base is already checked for acceptability in the constructor
             * The divisor is this.base
             * dividend initialized to decValue in case something goes wrong and
             * the decimal value needs to be recovered
             * THE LEAST SIGNIFICANT DIGIT IS STORED IN INDEX 0
             */
            do
               {
                  quotient = dividend / base;   // Integer division
                  remainder = dividend % base;
                  // numDigits is initialized to 0
                  decimalNumberConvertedToBase[ numDigits ] = remainder;
                  dividend = quotient;
                  numDigits++;
               }
            while( quotient > 0 && numDigits < maxDigits );

            // Check if overflow occurred
            if ( dividend > 0 )
               {
                  decimalNumberConvertedToBase = null;
                  overFlow = true;
               }

            return decimalNumberConvertedToBase;
         }

      /**
       * Accesses the value directly
       * <p>
       * Exception: If value is overflowed, returns dummy string
       *
       * @return String value in base form
       */
      public String getValueAsBase()
         {
            // This constant is to convert ints to chars values in ASCII.
            // E.g. int 48 is char '0'. This only works for numbers 0 through 9.
            final int ASCII_CONVERSION = 48;

            String baseValueAsString = "";
            char currentNumber;
            int index;

            // If the value is not overflowed
            if( !( overFlow ) )
               {
                  if( isZero() )
                     {
                        baseValueAsString = "0";
                     }
                  else
                     {
                        for ( index = numDigits - 1; index >= 0; index-- )
                           {
                              currentNumber = (char) ( digitArray[ index ] +
                                                             ASCII_CONVERSION );
                              baseValueAsString += currentNumber;
                           }
                     }
               }
            // Else return empty string

            return baseValueAsString;
         }

      /**
       * Accesses the value as decimal
       * <p>
       * Exception: If value is overflowed, returns 0
       *
       * Exception: Can only provide decimal value up to level of long int
       *
       * @return integer value in decimal form
       */
      public long getValueAsDecimal()
         {
            long valueAsDecimal = 0;
            int currentValueFromBase, currentDigitAsDecimal, arrayIndex;

            if( !(overFlow) )
               {
                  // Traverse digitArray from LSD to MSD, use intToPower to find
                  // base 10 equivalent of that "digit", and add to
                  // valueAsDecimal
                  for( arrayIndex = 0; arrayIndex < numDigits; arrayIndex++ )
                     {
                        currentValueFromBase = digitArray[ arrayIndex ];
                        currentDigitAsDecimal = currentValueFromBase *
                                            intToPower( this.base, arrayIndex );
                        valueAsDecimal += currentDigitAsDecimal;
                     }
               }

            return valueAsDecimal; // temporary stub return
         }

      /**
       * Creates new digit array and zeroes out the array
       *
       * @return new integer array created, and initialized with all zeroes
       */
      public int[] initializeDigits()
         {
            int[] newNumberArray = new int[ maxDigits ];
            int index;

            for ( index = 0; index < maxDigits; index++ )
               {
                  newNumberArray[ index ] = 0;
               }

            return newNumberArray;
         }

      /**
       * Recursively calculates integer base value to exponent power
       * <p>
       * Note: Required function since Java toPower function requires floating
       * point values, and conducts floating point math
       *
       * @param base     value to multiplied by itself
       *
       * @param exponent number of times base value is to be multiplied by
       *                 itself
       *
       * @return integer result of calculations
       */
      int intToPower( int base, int exponent )
         {
            int result;

            if( exponent > 0 )
               {
                  result = base * intToPower( base, exponent - 1 );
               }
            else   // Anything to the 0th power is 1, no negative exponents
               {
                  result = 1;
               }

            return result;
         }

      /**
       * Checks for base value zero
       *
       * @return true if the number of digits is one or less, and the LSD is
       * zero
       */
      public boolean isZero()
         {
            boolean isZero = false;

            if ( digitArray[ 0 ] == 0 && numDigits <= 1 )
               {
                  isZero = true;
               }

            return isZero;
         }
   }
