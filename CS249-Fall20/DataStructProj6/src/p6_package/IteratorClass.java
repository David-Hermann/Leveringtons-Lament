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
 * Iterator class inherited from BasicLinkedListClass; conducts iterator
 * operations
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class IteratorClass extends LinkedFoundationClass
   {
      // Class constants ///////////////////////////////////////////////////////
      /**
       * Constant character for display
       */
      private final char LEFT_BRACKET = '[';

      /**
       * Constant character for display
       */
      private final char RIGHT_BRACKET = ']';

      /**
       * Constant character for display
       */
      private final char SPACE = ' ';

      // Class variables ///////////////////////////////////////////////////////
      /**
       * Current index of iterator
       */
      private int currentIndex;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Default constructor for IteratorClass
       * <p>
       * Note: Sets currentIndex to -1
       */
      public IteratorClass()
         {
            super();
            currentIndex = -1;
         }

      /**
       * Copy constructor for IteratorClass
       * @param copied IteratorClass to be copied
       */
      public IteratorClass( IteratorClass copied )
         {
            super( copied );
            this.currentIndex = copied.currentIndex;
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Clears virtual array
       */
      @Override
      public void clear()
         {
            super.clear();
            currentIndex = -1;
         }

      /**
       * Gets value at iterator cursor location
       * @return Integer value returned, FAILED_ACCESS if not found
       */
      public int getAtCurrent()
         {
            return super.getAtIndex( currentIndex );
         }

      /**
       * Reports if iterator cursor is at beginning of existing list
       * <p>
       * Note: Must not return true from empty list
       * @return Boolean result of action; true if at beginning of existing
       *    list, false otherwise
       */
      public boolean isAtBeginning()
         {
            return ( !( isEmpty() ) && currentIndex == 0 );
         }

      /**
       * Reports if iterator cursor is at end of existing list
       * <p>
       * Note: Must not return true from empty list
       * @return Boolean result of action; true if at end of existing list,
       *    false otherwise
       */
      public boolean isAtEnd()
         {
            return ( !( isEmpty() ) &&
                               ( currentIndex == super.getCurrentSize() - 1 ) );
         }

      /**
       * Reports if list is empty
       * @return Boolean result of action; true if empty, false otherwise
       */
      @Override
      public boolean isEmpty()
         {
            return super.isEmpty();
         }

      /**
       * Moves iterator cursor one position to the right, or next, of existing
       * list
       * <p>
       * Note: Must not return true from empty list
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
       * Moves iterator cursor one position to the left, or previous, of
       * existing list
       * <p>
       * Note: Must not return true from empty list
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
       * Note: cursor must remain in same relative position after removal unless
       * the last item is removed
       * @return Integer value removed from list
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
       * Replaces value at iterator cursor
       * @param newValue Integer value to be replaced in list
       * @return Boolean result of action; true if successful, false otherwise
       */
      public boolean replaceAtCurrent( int newValue )
         {
            return super.setAtIndex( REPLACE, currentIndex, newValue );
         }

      /**
       * Shows space-delimited list with cursor location indicated
       * <p>
       * Indicates cursor with left/right brackets (e.g., "[##]")
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
       * Sets value to location after current index
       * @param newValue Integer value to be inserted into list
       * @return Boolean result of operation
       */
      public boolean setAfterCurrent( int newValue )
         {
            return super.setAtIndex( INSERT_AFTER, currentIndex, newValue );
         }

      /**
       * Sets value to location before current index
       * @param newValue Integer value to be inserted into list
       * @return Boolean result of operation
       */
      public boolean setBeforeCurrent( int newValue )
         {
            return super.setAtIndex( INSERT_BEFORE, currentIndex, newValue );
         }

      /**
       * Sets iterator cursor to the beginning of the existing list
       * <p>
       * Note: Must not return true from empty list
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
       * Sets iterator cursor to the end of the existing list
       * <p>
       * Note: Must not return true from empty list
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
