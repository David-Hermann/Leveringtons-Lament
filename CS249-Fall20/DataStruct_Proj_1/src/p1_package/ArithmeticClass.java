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
 * Class for managing number systems between base 2 and base 9
 * <p>
 * Converts decimal numbers to a specified base and conducts addition,
 * subtraction, multiplication, division, and modulo operations on the data
 *
 * @author David Hermann
 * @author Dr. Michael Leverington
 */
public class ArithmeticClass
   {
      /**
       * Initialization constructor for ArithmeticClass
       */
      public ArithmeticClass()
         {
            // Wow pretty empty in here
         }

      /**
       * Adds two registers in their base, returns sum
       * <p>
       * Note: Register parameters may not be modified within this method
       *
       * @param register_1 first of two values to be processed, as specified
       * @param register_2 second of two values to be processed, as specified
       * @return RegisterClass sum of registers; null if the bases are not the
       *         same
       */
      public RegisterClass addRegisters( RegisterClass register_1,
                                                      RegisterClass register_2 )
         {
            RegisterClass copyRegister_1 = new RegisterClass( register_1 );
            RegisterClass copyRegister_2 = new RegisterClass( register_2 );
            int maxNumDigits = getMax( register_1.numDigits, register_2.numDigits );
            RegisterClass sumOfRegisters = null;
            int cpy1Val, cpy2Val, magnitude, digit, carry = 0;

            // Check bases are equal
            if( copyRegister_1.base == copyRegister_2.base )
               {
                  // Create new RegisterClass for sum
                  sumOfRegisters = new RegisterClass( copyRegister_1.base,
                            copyRegister_1.numDigits + copyRegister_2.numDigits,
                                                                            0 );

                  // Perform adding
                  do
                     {
                        // Calculate what the sum is in base 10
                        if( sumOfRegisters.numDigits - 1 >= copyRegister_1.maxDigits )
                           {
                              cpy1Val = 0;
                           }
                        else
                           {
                              cpy1Val = copyRegister_1.digitArray[ sumOfRegisters.numDigits - 1 ];
                           }

                        if( sumOfRegisters.numDigits - 1 >= copyRegister_2.maxDigits )
                           {
                              cpy2Val = 0;
                           }
                        else
                           {
                              cpy2Val = copyRegister_2.digitArray[ sumOfRegisters.numDigits - 1 ];
                           }

                        magnitude = cpy1Val + cpy2Val + carry;

                        // Convert that sum into its base equivalent
                        digit = magnitude % copyRegister_1.base;

                        // Store this value into the sum array
                        sumOfRegisters.digitArray[ sumOfRegisters.numDigits - 1 ] = digit;

                        // Find out what the carry is via integer division
                        carry = magnitude / copyRegister_1.base;

                        sumOfRegisters.numDigits++;
                     }
                  while( ( carry != 0 ) || ( sumOfRegisters.numDigits <
                                                           maxNumDigits + 1 ) );

                  sumOfRegisters.clearLeadingZeros();
               }

            return sumOfRegisters;
         }

      /**
       * Divides register one by register two, returns quotient
       * <p>
       * Note: Register parameters may not be modified within this method
       *
       * @param register_1 first of two values to be processed, as specified
       * @param register_2 second of two values to be processed, as specified
       * @return RegisterClass quotient between values, null if the bases are
       * not the same
       */
      public RegisterClass divideRegistersToQuotient( RegisterClass register_1,
                                                      RegisterClass register_2 )
         {
            // Copy registers
            // Check if bases !=
               // Return null
            // Create a quotient register with 0 in it
            // Create a register with 1 in it to keep track of subtractions
            // Loop while dividend != null
               // dividend -= divisor
               // quotient++
            // End loop

            // TODO: Stub method
            RegisterClass copyRegister_1 = new RegisterClass( register_1 );
            RegisterClass copyRegister_2 = new RegisterClass( register_2 );
            int largerDigitAmnt = getMax( copyRegister_1.numDigits,
                                                     copyRegister_2.numDigits );
            RegisterClass quotientOfRegisters = null;
            int base;

            // Check bases are equal
            if( copyRegister_1.base == copyRegister_2.base )
               {
                  base = copyRegister_1.base;
                  // Create new RegisterClass for quotient
                  quotientOfRegisters = new RegisterClass( base,
                                                       largerDigitAmnt + 1, 0 );

                  // Repeated subtraction, keep track of how many times
                  // subtracted
                  quotientOfRegisters.clearLeadingZeros();
               }

            return quotientOfRegisters;
         }

      /**
       * Divides register one by register two, returns remainder
       * <p>
       * Note: Register parameters may not be modified within this method
       *
       * @param register_1 first of two values to be processed, as specified
       * @param register_2 second of two values to be processed, as specified
       * @return RegisterClass remainder between values, null if the bases are
       * not the same
       */
      public RegisterClass divideRegistersToRemainder( RegisterClass register_1,
                                                      RegisterClass register_2 )
         {
            // Copy registers
            // Check if bases !=
               // Return null
            // Create a remainder register with 0 in it
            // Loop while divisor <= dividend
               // Do something
            // End loop

            // TODO: Stub method
            RegisterClass copyRegister_1 = new RegisterClass( register_1 );
            RegisterClass copyRegister_2 = new RegisterClass( register_2 );
            int largerDigitAmnt = getMax( copyRegister_1.numDigits,
                                                     copyRegister_2.numDigits );
            RegisterClass remainderOfRegisters = null;
            int base;

            // Check bases are equal
            if( copyRegister_1.base == copyRegister_2.base )
               {
                  base = copyRegister_1.base;
                  // Create new RegisterClass for remainder
                  remainderOfRegisters = new RegisterClass( base,
                                                       largerDigitAmnt + 1, 0 );



                  remainderOfRegisters.clearLeadingZeros();
               }

            return remainderOfRegisters;
         }

      /**
       * Utility method that finds the maximum of two integer values
       *
       * @param valOne one of the two values to be compared
       * @param valTwo other of the two values to be compared
       * @return larger of the two values as specified
       */
      private int getMax( int valOne, int valTwo )
         {
            int max;

            if( valOne >= valTwo )
               {
                  max = valOne;
               }
            else
               {
                  max = valTwo;
               }

            return max;
         }

      /**
       * Multiplies register one by register two, returns product
       * <p>
       * Note: Register parameters may not be modified within this method
       *
       * @param register_1 first of two values to be processed, as specified
       * @param register_2 second of two values to be processed, as specified
       * @return RegisterClass product between values, null if the bases are not
       * the same
       */
      public RegisterClass multiplyRegisters( RegisterClass register_1,
                                                      RegisterClass register_2 )
         {
            RegisterClass copyRegister_1 = new RegisterClass( register_1 );
            RegisterClass copyRegister_2 = new RegisterClass( register_2 );
            RegisterClass productOfRegisters = null;
            RegisterClass constantOfOne = new RegisterClass( register_1.base, register_1.maxDigits, 1 );
            int productMaxDigits = copyRegister_1.numDigits + copyRegister_2.numDigits;

            // Check bases are equal
            if( copyRegister_1.base == copyRegister_2.base )
               {
                  // Create new RegisterClass for product
                  productOfRegisters = new RegisterClass( copyRegister_1.base, productMaxDigits, 0 );

                  // Repeat addition to find product
                  while( !copyRegister_2.isZero() )
                     {
                        productOfRegisters = addRegisters( productOfRegisters, copyRegister_1 );
                        copyRegister_2 = subtractRegisters( copyRegister_2, constantOfOne );
                     }

                  productOfRegisters.clearLeadingZeros();
               }

            return productOfRegisters;
         }

      /**
       * Subtracts register two from register one, returns difference
       * <p>
       * Note: Register parameters may not be modified within this method
       *
       * @param register_1 first of two values to be processed, as specified
       * @param register_2 second of two values to be processed, as specified
       * @return RegisterClass positive difference between values, null if any
       * failure occurred, including: 1) the bases are not the same; or 2)
       * register_2 is numerically greater than register_1
       */
      public RegisterClass subtractRegisters( RegisterClass register_1,
                                                      RegisterClass register_2 )
         {
            RegisterClass copyRegister_1, copyRegister_2;
            RegisterClass differenceOfRegisters = null;
            int digitIndex = 0;
            // initialize/set borrow flag to false
            boolean borrow = false;

            // check for bases not the same or reg2 numDigits > reg1 numDigits
            if( ( register_1.base != register_2.base ) || register_2.numDigits >
                                                          register_1.numDigits )
               {
                  return null;
               }

            // make copies of the two registers
            copyRegister_1 = new RegisterClass( register_1 );
            copyRegister_2 = new RegisterClass( register_2 );
            differenceOfRegisters = new RegisterClass( register_1.base, register_1.maxDigits, 0 );

             // loop across reg1 digits
            while( digitIndex < copyRegister_1.numDigits )
               {
                  // check for borrow
                  if( borrow )
                     {
                        // subtract 1 from the current reg1 value (at index)
                        copyRegister_1.digitArray[ digitIndex ]--;
                        // unset the borrow flag
                        borrow = false;
                     }

                  // check if reg2 digit (at index) > reg1 digit (at index)
                  if( copyRegister_1.digitArray[ digitIndex ] < copyRegister_2.digitArray[ digitIndex ] )
                     {
                        // set the borrow flag
                        borrow = true;
                        // add the base to the reg1 digit
                        copyRegister_1.digitArray[ digitIndex ] += copyRegister_1.base;
                     }

                  // subtract reg2 digit from reg1 digit, assign to solution digit (at index)
                  differenceOfRegisters.digitArray[ digitIndex ] = copyRegister_1.digitArray[ digitIndex ]
                                                                 - copyRegister_2.digitArray[ digitIndex ];

                  differenceOfRegisters.numDigits++;
                  digitIndex++;
               }
            // end loop across digits

            // check the borrow flag
            if( borrow )
               {
                  return null;
               }

            // set the solution number of digits to the reg1 number of digits
            // clear the leading zeros
            differenceOfRegisters.clearLeadingZeros();

            return differenceOfRegisters;
         }
   }
