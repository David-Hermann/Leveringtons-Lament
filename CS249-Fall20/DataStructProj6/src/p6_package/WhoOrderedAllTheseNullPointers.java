package p6_package;

public class WhoOrderedAllTheseNullPointers
   {
      public static void main( String args[] )
         {
            LinkedFoundationClass myOldNemesis;
            LinkedFoundationClass copyTime;

            IteratorClass itsAList;

            StackClass superStacker;

            myOldNemesis = new LinkedFoundationClass();

            itsAList = new IteratorClass();

            superStacker = new StackClass();

            myOldNemesis.setAtIndex( 0, 3, LinkedFoundationClass.INSERT_AFTER );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 0, 43, LinkedFoundationClass.INSERT_BEFORE );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 0, 2, LinkedFoundationClass.INSERT_BEFORE );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 0, 55, LinkedFoundationClass.INSERT_BEFORE );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 0, 45, LinkedFoundationClass.INSERT_AFTER );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 0, 48, LinkedFoundationClass.REPLACE );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 2, 24, LinkedFoundationClass.INSERT_BEFORE );
            myOldNemesis.runDiagnosticDisplay( true );
            myOldNemesis.setAtIndex( 3, 42, LinkedFoundationClass.INSERT_AFTER );
            myOldNemesis.runDiagnosticDisplay( true );

            copyTime = new LinkedFoundationClass( myOldNemesis );
            copyTime.runDiagnosticDisplay( true );


            itsAList.setAtIndex( 0, 3, LinkedFoundationClass.INSERT_AFTER );
            itsAList.setAtIndex( 0, 43, LinkedFoundationClass.INSERT_BEFORE );
            itsAList.setAtIndex( 0, 2, LinkedFoundationClass.INSERT_BEFORE );
            itsAList.setAtIndex( 0, 55, LinkedFoundationClass.INSERT_BEFORE );
            itsAList.setAtIndex( 0, 45, LinkedFoundationClass.INSERT_AFTER );
            itsAList.setAtIndex( 0, 48, LinkedFoundationClass.REPLACE );
            itsAList.setAtIndex( 2, 24, LinkedFoundationClass.INSERT_BEFORE );
            itsAList.setAtIndex( 3, 42, LinkedFoundationClass.INSERT_AFTER );
            itsAList.moveNext();
            itsAList.runDiagnosticDisplay();

            superStacker.push( 1 );
            superStacker.push( 2 );
            superStacker.push( 3 );
            superStacker.push( 4 );
            superStacker.push( 5 );
            System.out.print( superStacker.peekTop() );
            System.out.print( '\n' );
            superStacker.displayStack();
            superStacker.pop();
            superStacker.displayStack();

         }
   }
