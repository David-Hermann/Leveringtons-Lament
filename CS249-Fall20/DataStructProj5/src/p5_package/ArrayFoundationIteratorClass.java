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
 * Iterator class inherited from ArrayFoundationClass; conducts iterator
 * operations
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class ArrayFoundationIteratorClass extends ArrayFoundationClass
   {
      // Class constants
      /**
       * Constant character for display
       */
      private final char SPACE = ' ';

      /**
       * Constant character for display
       */
      private final char LEFT_BRACKET = '[';

      /**
       * Constant character for display
       */
      private final char RIGHT_BRACKET = ']';

      // Class variables
      /**
       * Current index of iterator
       */
      private int currentIndex;

      // Constructors
      /**
       * Default constructor for ArrayFoundationIteratorClass
       * <p>
       * Note: current index set below element
       */
      public ArrayFoundationIteratorClass()
         {
            super();
            currentIndex = -1;
         }

      /**
       * Initialization constructor for ArrayFoundationIteratorClass
       * <p>
       * Note: current index set below element
       *
       * @param initCapacity Integer value at which to set initial array
       * capacity
       */
      public ArrayFoundationIteratorClass( int initCapacity )
         {
            super( initCapacity );
            currentIndex = -1;
         }

      /**
       * Copy constructor for ArrayFoundationIteratorClass
       *
       * @param copied ArrayFoundationIteratorClass object to be copied
       */
      public ArrayFoundationIteratorClass( ArrayFoundationIteratorClass copied )
         {
            super( copied );
            this.currentIndex = copied.currentIndex;
         }

      // Class methods
      /**
       * Clears array
       * <p>
       * Resets current index to below first element
       */
      @Override
      public void clear()
         {
            super.clear();
            currentIndex = -1;
         }

      /**
       * Gets value at iterator cursor location
       *
       * @return Integer value returned; FAILED_ACCESS if not found
       */
      public int getAtCurrent()
         {
            int current = super.getAtIndex( currentIndex );

            return current;
         }

      /**
       * Reports if iterator cursor is at beginning
       * <p>
       * Must consider whether list is empty
       *
       * @return Boolean result of action; true if at beginning, false otherwise
       */
      public boolean isAtFirstElement()
         {
            return ( !( isEmpty() ) && currentIndex == 0 );
         }

      /**
       * Reports if iterator cursor is at end
       * <p>
       * Must consider whether list is empty
       *
       * @return Boolean result of action; true if at end, false otherwise
       */
      public boolean isAtLastElement()
         {
            return ( !( isEmpty() ) && ( currentIndex == getCurrentSize()
                                                                        - 1 ) );
         }

      /**
       * Reports if list is empty
       *
       * @return Boolean result of action; true if empty, false otherwise
       */
      @Override
      public boolean isEmpty()
         {
            return super.isEmpty();
         }

      /**
       * If possible, moves iterator cursor one position to the right, or next
       * <p>
       * Must consider whether list is empty
       *
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean moveNext()
         {
            boolean moved = false;

            if( !( isEmpty() ) && currentIndex < super.getCurrentSize() - 1 )
               {
                  currentIndex++;
                  moved = true;
               }

            return moved;
         }

      /**
       * If possible, moves iterator cursor one position to the left, or
       * previous
       * <p>
       * Must consider whether list is empty
       *
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean movePrev()
         {
            boolean moved = false;

            if( !( isEmpty() ) && currentIndex > 0 )
               {
                  currentIndex--;
                  moved = true;
               }

            return moved;
         }

      /**
       * Removes and returns a data value from the iterator cursor position
       * <p>
       * Note: cursor must be located at succeeding element unless last item
       * removed
       *
       * @return Integer value removed from list, or FAILED_ACCESS if not found
       */
      public int removeAtCurrent()
         {
            int removed = super.removeAtIndex( currentIndex );

            if( currentIndex == super.getCurrentSize() )
               {
                  currentIndex--;
               }

            return removed;
         }

      /**
       * Replaces value at iterator cursor with new value
       *
       * @param newValue Integer value to be inserted in list
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean replaceAtCurrent( int newValue )
         {
            boolean replacedSuccessfully = super.setAtIndex( REPLACE,
                                                       currentIndex, newValue );

            return replacedSuccessfully;
         }

      /**
       * Shows space-delimited list with cursor location indicated
       */
      public void runDiagnosticDisplay()
         {
            int displayIndex, currentValue;

            for( displayIndex = super.getCurrentSize() - 1; displayIndex >= 0;
                                                                displayIndex-- )
               {
                  currentValue = super.getAtIndex( displayIndex );

                  System.out.print( SPACE );

                  if( displayIndex != currentIndex )
                     {
                        System.out.print( currentValue );
                     }
                  else
                     {
                        System.out.print( LEFT_BRACKET );
                        System.out.print( currentValue );
                        System.out.print( RIGHT_BRACKET );
                     }
               }

            System.out.print( '\n' );
         }

      /**
       * Inserts new value after value at iterator cursor
       * <p>
       * Note: Current value must remain the same after data set
       *
       * @param newValue Integer value to be inserted in list
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean setAfterCurrent( int newValue )
         {
            boolean setSuccessfully = super.setAtIndex( INSERT_AFTER,
                                                       currentIndex, newValue );

            return setSuccessfully;
         }

      /**
       * Inserts new before value at iterator cursor
       * <p>
       * Note: Current value must remain the same after data set
       *
       * @param newValue Integer value to be inserted in list
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean setBeforeCurrent( int newValue )
         {
            boolean setSuccessfully = super.setAtIndex( INSERT_BEFORE,
                                                       currentIndex, newValue );

            return setSuccessfully;
         }

      /**
       * Sets iterator cursor to beginning of list
       *
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean setToFirstElement()
         {
            boolean setSuccessfully = false;

            if( !( isEmpty() ) )
               {
                  currentIndex = 0;
                  setSuccessfully = true;
               }

            return setSuccessfully;
         }

      /**
       * Sets iterator cursor to the end of the list
       *
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean setToLastElement()
         {
            boolean setSuccessfully = false;

            if( !( isEmpty() ) )
               {
                  currentIndex = super.getCurrentSize() - 1;
                  setSuccessfully = true;
               }

            return setSuccessfully;
         }
   }
