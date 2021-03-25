package p6_package;

/**
 * Date: 7 October 2020
 * <p>
 * Program: PA06
 * <p>
 * Class: CS249 - 002
 * <p>
 * "Wait, it's all NullPointerException?"
 * <p>
 * Stack class that uses LinkedFoundationClass as data structure
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class StackClass
   {
      // Class variable(s) /////////////////////////////////////////////////////
      private LinkedFoundationClass stackData;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Stack default constructor
       */
      public StackClass()
         {
            stackData = new LinkedFoundationClass();
         }

      /**
       * Copy constructor
       * @param copied StackClass object to be copied
       */
      public StackClass( StackClass copied )
         {
            this.stackData = new LinkedFoundationClass( copied.stackData );
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Clears stack
       */
      public void clear()
         {
            stackData.clear();
         }

      /**
       * Displays stack as comma-delimited list
       */
      public void displayStack()
         {
            int displayIndex, currentSize = stackData.getCurrentSize();

            System.out.print( "Stack Bottom-> " );

            for( displayIndex = currentSize - 1; displayIndex >= 0;
                                                                displayIndex-- )
               {
                  if( displayIndex != currentSize - 1 )
                     {
                        // Print value with space and comma behind it
                        System.out.print( ", " + stackData.getAtIndex(
                                                               displayIndex ) );
                     }
                  else
                     {
                        // Print stack top value with no comma or space
                        System.out.print( stackData.getAtIndex(
                                                               displayIndex ) );
                     }

               }

            System.out.print( "<- Stack Top\n" );
         }

      /**
       * Reports stack empty status
       * @return Boolean evidence of empty list
       */
      public boolean isEmpty()
         {
            return stackData.isEmpty();
         }

      /**
       * Provides peek at top of stack
       * @return Integer value if successful, FAILED_ACCESS if not
       */
      public int peekTop()
         {
            // I consider index 0 to be the top of the stack.
            return stackData.getAtIndex( 0 );
         }

      /**
       * Removes and returns data from top of stack
       * @return Integer value if successful, FAILED_ACCESS if not
       */
      public int pop()
         {
            int valueInQueue = LinkedFoundationClass.FAILED_ACCESS;

            if( !isEmpty() )
               {
                  valueInQueue = stackData.removeAtIndex( 0 );
               }
            // Else stack is empty and there is nothing to pop, if not
            // checked this would lead to a NullPointerException

            return valueInQueue;
         }

      /**
       * Places data item on top of the stack
       * @param newVal Integer value to pushed onto stack
       */
      public void push( int newVal )
         {
            stackData.setAtIndex( 0, newVal, LinkedFoundationClass.INSERT_BEFORE );
         }
   }
