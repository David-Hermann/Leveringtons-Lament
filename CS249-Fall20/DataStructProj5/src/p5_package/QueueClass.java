package p5_package;

/**
 * Date: 23 September 2020
 * <p>
 * Program: PA05
 * <p>
 * Class: CS249 - 002
 * <p>
 * "public class DavidHermann extends StressedStudent"
 * <p>
 * Queue class that uses ArrayFoundationClass as data structure
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class QueueClass
   {
      // Class variable(s)
      private ArrayFoundationClass queueData;

      // Class constructors
      public QueueClass()
         {
            queueData = new ArrayFoundationClass();
         }

      /**
       * Initialization constructor
       *
       * @param capacitySetting Initial capacity of queueData class
       */
      public QueueClass( int capacitySetting )
         {
            queueData = new ArrayFoundationClass( capacitySetting );
         }

      /**
       * Copy constructor
       *
       * @param copied QueueClass object to be copied
       */
      public QueueClass( QueueClass copied )
         {
            this.queueData = new ArrayFoundationClass( copied.queueData );
         }

      // Class methods
      /**
       * Clears queue
       */
      public void clear()
      {
         queueData.clear();
      }

      /**
       * Displays queue
       */
      public void displayQueue()
         {
            int displayIndex, currentSize = queueData.getCurrentSize();

            System.out.print( "Queue Tail-> " );

            for( displayIndex = currentSize - 1; displayIndex >= 0;
                                                                displayIndex-- )
               {
                  if( displayIndex != currentSize - 1 )
                     {
                        // Print value with space and comma behind it
                        System.out.print( ", " + queueData.getAtIndex(
                                                               displayIndex ) );
                     }
                  else
                     {
                        // Print queue tail value with no comma or space
                        System.out.print( queueData.getAtIndex(
                                                               displayIndex ) );
                     }

               }

            System.out.print( "<- Queue Front\n" );
         }

      /**
       * Enqueues value
       *
       * @param newValue Value to be enqueued
       */
      public void enqueue( int newValue )
         {
            int currentSize = queueData.getCurrentSize();

            queueData.setAtIndex( ArrayFoundationClass.INSERT_AFTER,
                                                        currentSize, newValue );
         }

      /**
       * Removes and returns value from front of queue
       *
       * @return Value if successful, FAILED_ACCESS if not
       */
      public int dequeue()
         {
            int valueInQueue = ArrayFoundationClass.FAILED_ACCESS;

            if( !isEmpty() )
               {
                  valueInQueue = queueData.removeAtIndex( 0 );
               }
            // Else queue is empty and there is nothing to dequeue, if not
            // checked this would lead to an IndexOutOfBoundsException

            return valueInQueue;
         }

      /**
       * Reports queue empty state
       *
       * @return Boolean evidence of empty list
       */
      public boolean isEmpty()
         {
            return queueData.isEmpty();
         }

      /**
       * Provides peek at front of queue
       *
       * @return Value if successful, FAILED_ACCESS if not
       */
      public int peekFront()
         {
            int frontValue = queueData.getAtIndex( 0 );

            return frontValue;
         }
   }
