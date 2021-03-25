package p5_package;

public class OhYeahItsAllFallingApart
   {
      public static void main( String args[] )
         {
            ArrayFoundationIteratorClass ohYeahItsAllComingTogether = new ArrayFoundationIteratorClass();

            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 0 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 8 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 1 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 29 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 24 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_AFTER, 0, 20 );
            ohYeahItsAllComingTogether.setAtIndex( ArrayFoundationClass.INSERT_BEFORE, 1, 35 );

            ohYeahItsAllComingTogether.setToFirstElement();

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            ohYeahItsAllComingTogether.setToLastElement();

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            ohYeahItsAllComingTogether.replaceAtCurrent( 7 );

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            ohYeahItsAllComingTogether.moveNext();

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            ohYeahItsAllComingTogether.movePrev();

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            ohYeahItsAllComingTogether.removeAtCurrent();

            ohYeahItsAllComingTogether.runDiagnosticDisplay();

            /*
            QueueClass theLineNeverEnds = new QueueClass();

            System.out.print( theLineNeverEnds.isEmpty() );

            theLineNeverEnds.enqueue( 0 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 4 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 8 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 21 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 34 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 15 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 13 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 8 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 26 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 71 );
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.enqueue( 61 );
            theLineNeverEnds.displayQueue();

            System.out.print( theLineNeverEnds.isEmpty() );

            theLineNeverEnds.dequeue();
            theLineNeverEnds.displayQueue();

            theLineNeverEnds.clear();
            theLineNeverEnds.displayQueue();

            System.out.print( theLineNeverEnds.isEmpty() );
            */
         }
   }
