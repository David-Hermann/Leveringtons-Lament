package p1_package;

public class ArithmeticClassTest
   {

      public static void main( String[] args )
         {
            ////////////////////////////////////////////////////////////////////////
            // NOTE: Methods are used in parameter lists in this class
            // for testing/running purposes but must never be used in class
            // or working program development

            ArithmeticClass AC = new ArithmeticClass();
            RegisterClass regObject1, regObject2;
            RegisterClass acSum, acDifference, acProduct; // acQuotient, acRemainder;

            System.out.print( "==================================================" + "\nBase 2:\t" );

            regObject1 = new RegisterClass( 2, 10, 57 );
            regObject2 = new RegisterClass( 2, 10, 37 );

            acSum = AC.addRegisters( regObject1, regObject2 );
            acDifference = AC.subtractRegisters( regObject1, regObject2 );
            acProduct = AC.multiplyRegisters( regObject1, regObject2 );
            //acQuotient = AC.divideRegistersToQuotient( regObject1, regObject2 );
            //acRemainder = AC.divideRegistersToRemainder( regObject1, regObject2 );

            System.out.print( "\t(" + regObject1.getValueAsBase() + ") " + regObject1.getValueAsDecimal() + " & " );
            System.out.println( "(" + regObject2.getValueAsBase() + ") " + regObject2.getValueAsDecimal() );

            System.out.println( "Sum: \t\t(" + acSum.getValueAsBase() + ") " + acSum.getValueAsDecimal() );
            System.out.println( "Difference: \t(" + acDifference.getValueAsBase() + ") " + acDifference
                    .getValueAsDecimal() );
            System.out.println( "Product: \t(" + acProduct.getValueAsBase() + ") " + acProduct.getValueAsDecimal() );
            //System.out.println( "Quotient: \t(" + acQuotient.getValueAsBase() + ") " + acQuotient.getValueAsDecimal() );
            //System.out.println( "Remainder: \t(" + acRemainder.getValueAsBase() + ") " + acRemainder
            //         .getValueAsDecimal() );


            System.out.print( "==================================================" + "\nBase 3:\t" );

            regObject1 = new RegisterClass( 3, 10, 65 );
            regObject2 = new RegisterClass( 3, 10, 45 );

            System.out.print( "\t(" + regObject1.getValueAsBase() + ") " + regObject1.getValueAsDecimal() + " & " );
            System.out.println( "(" + regObject2.getValueAsBase() + ") " + regObject2.getValueAsDecimal() );

            acSum = AC.addRegisters( regObject1, regObject2 );
            acDifference = AC.subtractRegisters( regObject1, regObject2 );
            acProduct = AC.multiplyRegisters( regObject1, regObject2 );
            //acQuotient = AC.divideRegistersToQuotient( regObject1, regObject2 );
            //acRemainder = AC.divideRegistersToRemainder( regObject1, regObject2 );

            System.out.println( "Sum: \t\t(" + acSum.getValueAsBase() + ") " + acSum.getValueAsDecimal() );
            System.out.println( "Difference: \t(" + acDifference.getValueAsBase() + ") " + acDifference
                     .getValueAsDecimal() );
            System.out.println( "Product: \t(" + acProduct.getValueAsBase() + ") " + acProduct.getValueAsDecimal() );
            //System.out.println( "Quotient: \t(" + acQuotient.getValueAsBase() + ") " + acQuotient.getValueAsDecimal() );
            //System.out.println( "Remainder: \t(" + acRemainder.getValueAsBase() + ") " + acRemainder
            //         .getValueAsDecimal() );

            System.out.print( "==================================================" + "\nBase 5:\t" );

            regObject1 = new RegisterClass( 5, 10, 73 );
            regObject2 = new RegisterClass( 5, 10, 53 );

            System.out.print( "\t(" + regObject1.getValueAsBase() + ") " + regObject1.getValueAsDecimal() + " & " );
            System.out.println( "(" + regObject2.getValueAsBase() + ") " + regObject2.getValueAsDecimal() );

            acSum = AC.addRegisters( regObject1, regObject2 );
            acDifference = AC.subtractRegisters( regObject1, regObject2 );
            acProduct = AC.multiplyRegisters( regObject1, regObject2 );
            //acQuotient = AC.divideRegistersToQuotient( regObject1, regObject2 );
            //acRemainder = AC.divideRegistersToRemainder( regObject1, regObject2 );

            System.out.println( "Sum: \t\t(" + acSum.getValueAsBase() + ") " + acSum.getValueAsDecimal() );
            System.out.println( "Difference: \t(" + acDifference.getValueAsBase() + ") " + acDifference
                     .getValueAsDecimal() );
            System.out.println( "Product: \t(" + acProduct.getValueAsBase() + ") " + acProduct.getValueAsDecimal() );
            //System.out.println( "Quotient: \t(" + acQuotient.getValueAsBase() + ") " + acQuotient.getValueAsDecimal() );
            //System.out.println( "Remainder: \t(" + acRemainder.getValueAsBase() + ") " + acRemainder
            //         .getValueAsDecimal() );

            System.out.print( "==================================================" + "\nBase 7:\t" );

            regObject1 = new RegisterClass( 7, 10, 53 );
            regObject2 = new RegisterClass( 7, 10, 53 );

            System.out.print( "\t(" + regObject1.getValueAsBase() + ") " + regObject1.getValueAsDecimal() + " & " );
            System.out.println( "(" + regObject2.getValueAsBase() + ") " + regObject2.getValueAsDecimal() );

            acSum = AC.addRegisters( regObject1, regObject2 );
            acDifference = AC.subtractRegisters( regObject1, regObject2 );
            acProduct = AC.multiplyRegisters( regObject1, regObject2 );
            //acQuotient = AC.divideRegistersToQuotient( regObject1, regObject2 );
            //acRemainder = AC.divideRegistersToRemainder( regObject1, regObject2 );

            System.out.println( "Sum: \t\t(" + acSum.getValueAsBase() + ") " + acSum.getValueAsDecimal() );
            System.out.println( "Difference: \t(" + acDifference.getValueAsBase() + ") " + acDifference
                     .getValueAsDecimal() );
            System.out.println( "Product: \t(" + acProduct.getValueAsBase() + ") " + acProduct.getValueAsDecimal() );
            //System.out.println( "Quotient: \t(" + acQuotient.getValueAsBase() + ") " + acQuotient.getValueAsDecimal() );
            //System.out.println( "Remainder: \t(" + acRemainder.getValueAsBase() + ") " + acRemainder
            //         .getValueAsDecimal() );

            System.out.println( "==================================================" );
         }
   }
