package p9_package;

/**
 * Date: 28 October 2020
 * <p>
 * Program: PA09
 * <p>
 * Class: CS249 - 002
 * <p>
 * "Pile the errors onto the heap!"
 * <p>
 * Array-based generic min heap class used as a priority queue for generic data
 * <p>
 * Note: This class requires any passed in objects to be comparable to the same
 * class, hence GenericData extends Comparable<GenericData>
 * <p>
 * Note: Comparable classes will have an @Override method for comparison
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class GenericHeapClass<GenericData extends Comparable<GenericData>>
   {
      // Class constant ////////////////////////////////////////////////////////
      /**
       * Initial array capacity
       */
      public final int  DEFAULT_ARRAY_CAPACITY = 10;

      // Class variables ///////////////////////////////////////////////////////
      /**
       * Management data for array
       */
      private int arrayCapacity;

      /**
       * Management data for array
       */
      private int arraySize;

      /**
       * Display flag can be set to observe bubble up and trickle down
       * operations
       */
      private boolean displayFlag;

      /**
       * Array for heap
       */
      private Object[] heapArray;

      // Class constructor /////////////////////////////////////////////////////
      /**
       * Default constructor sets up array management conditions and default
       * display flag setting
       */
      public GenericHeapClass()
         {
            arrayCapacity = DEFAULT_ARRAY_CAPACITY;
            arraySize = 0;
            displayFlag = false;
            heapArray = new Object[ arrayCapacity ];
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Accepts GenericData item and adds it to heap
       * <p>
       * Note: Uses bubbleUpArrayHeap to resolve unbalanced heap after data
       * addition
       *
       * @param newItem GenericData item to be added
       */
      public void addItem( GenericData newItem )
         {
            String newItemToStr;

            if( displayFlag )
               {
                  newItemToStr = newItem.toString();

                  System.out.print( "Adding new process: " );
                  System.out.print( newItemToStr + '\n' );
               }

            checkForResize();
            heapArray[ arraySize ] = newItem;
            bubbleUpArrayHeap( arraySize );
            // arraySize not modified until end so correct index is assessed in
            // bubbleUpArrayHeap
            arraySize++;

            if( displayFlag )
               {
                  System.out.print( '\n' );
               }
         }

      /**
       * Recursive operation to reset data in the correct order for the min heap
       * after new data addition
       *
       * @param currentIndex Index of current item being assessed, and moved up
       * as needed
       */
      @SuppressWarnings( "unchecked" )
      private void bubbleUpArrayHeap( int currentIndex )
         {
            GenericData current, parent, temp;
            String currentToStr, parentToStr;
            int parentIndex = ( currentIndex - 1 ) / 2;

            if( currentIndex > 0 )
               {
                  current = (GenericData) heapArray[ currentIndex ];
                  parent = (GenericData) heapArray[ parentIndex ];

                  // If current less than parent, swap
                  if( current.compareTo( parent ) < 0 )
                     {
                        if( displayFlag )
                           {
                              currentToStr = current.toString();
                              parentToStr = parent.toString();

                              System.out.print( "   " + "- Bubble up:\n" );
                              System.out.print( "     - Swapping parent: " );
                              System.out.print( parentToStr + ' ' );
                              System.out.print( "with child: " );
                              System.out.print( currentToStr + '\n' );
                           }

                        temp = parent;
                        parent = current;
                        current = temp;
                        heapArray[ currentIndex ] = current;
                        heapArray[ parentIndex ] = parent;

                        // Recurse to make sure heap is sorted
                        bubbleUpArrayHeap( parentIndex );
                     }
               }
            // Else the latest item added in addItem started the heap OR
            // all values have bubbled up in the heap and it is sorted
         }

      /**
       * Automatic resize operation used prior to any new data addition in the
       * heap
       * <p>
       * Tests for full heap array, and resizes to twice the current capacity as
       * required
       */
      @SuppressWarnings( "unchecked" )
      private void checkForResize()
         {
            Object[] tempArray;
            GenericData tempElement;
            int index;

            if( arraySize + 1 == arrayCapacity )
               {
                  arrayCapacity *= 2;
                  // Note: arraySize is not modified since no elements are added
                  tempArray = new Object[ arrayCapacity ];

                  for( index = 0; index <= arraySize; index++ )
                     {
                        tempElement = (GenericData) heapArray[ index ];
                        tempArray[ index ] = tempElement;
                     }

                  heapArray = tempArray;
               }
         }

      /**
       * Tests for empty heap
       *
       * @return Boolean result of test
       */
      public boolean isEmpty()
         {
            return arraySize == 0;
         }

      /**
       * Removes GenericData item from top of min heap, thus being the operation
       * with the lowest priority value
       * <p>
       * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
       * removal
       *
       * @return GenericData item removed
       */
      @SuppressWarnings( "unchecked" )
      public GenericData removeItem()
         {
            GenericData removed = (GenericData) heapArray[ 0 ];
            String removedStr;

            if( displayFlag )
               {
                  removedStr = removed.toString();

                  System.out.print( "\nRemoving process: " );
                  System.out.print( removedStr + '\n' );
               }

            heapArray[ 0 ] = heapArray[ arraySize - 1 ];
            arraySize--;
            trickleDownArrayHeap( 0 );

            return removed;
         }

      /**
       * Utility method to set the display flag for displaying internal
       * operations of the heap bubble and trickle operations
       *
       * @param setState Flag used to set the state to display, or not
       */
      public void setDisplayFlag( boolean setState )
         {
            displayFlag = setState;
         }

      /**
       * Dumps array to screen as is, no filtering or management
       */
      public void showArray()
         {
            int index;
            for( index = 0; index < arraySize; index++ )
               {
                  System.out.print( heapArray[ index ].toString() + "\n" );
               }
         }

      /**
       * Recursive operation to reset data in the correct order for the min heap
       * after data removal
       *
       * @param currentIndex Index of current item being assessed, and moved
       * down as required
       */
      @SuppressWarnings( "unchecked" )
      private void trickleDownArrayHeap( int currentIndex )
         {
            int childOneIndex = currentIndex * 2 + 1;
            int childTwoIndex = currentIndex * 2 + 2;
            int comparePriority;
            GenericData parent = (GenericData) heapArray[ currentIndex ];
            GenericData childOne, childTwo, temp;
            String parentToStr, childToStr;

            // If childOne is valid, compare to parent
            if( childOneIndex < arraySize )
               {
                  childOne = (GenericData) heapArray[ childOneIndex ];
                  comparePriority = childOne.compareTo( parent );

                  // If child less than parent, trickle down
                  if( comparePriority < 0 )
                     {
                        if( displayFlag )
                           {
                              parentToStr = parent.toString();
                              childToStr = childOne.toString();

                              System.out.print( "   " + "- Trickle down:\n" );
                              System.out.print( "     - Swapping parent: " );
                              System.out.print( parentToStr + ' ' );
                              System.out.print( "with left child: " );
                              System.out.print( childToStr + '\n' );
                           }

                        temp = parent;
                        parent = childOne;
                        childOne = temp;
                        heapArray[ currentIndex ] = parent;
                        heapArray[ childOneIndex ] = childOne;

                        trickleDownArrayHeap( childOneIndex );
                     }
               }
            // childOne is valid but greater than parent
            // If childTwo is valid, compare to parent
            if( childTwoIndex < arraySize )
               {
                  childTwo = (GenericData) heapArray[ childTwoIndex ];
                  comparePriority = childTwo.compareTo( parent );

                  // If child less than parent, trickle down
                  if( comparePriority < 0 )
                     {
                        if( displayFlag )
                           {
                              parentToStr = parent.toString();
                              childToStr = childTwo.toString();

                              System.out.print( "   " + "- Trickle down:\n" );
                              System.out.print( "     - Swapping parent: " );
                              System.out.print( parentToStr + ' ' );
                              System.out.print( "with right child: " );
                              System.out.print( childToStr + '\n' );
                           }

                        temp = parent;
                        parent = childTwo;
                        childTwo = temp;
                        heapArray[ currentIndex ] = parent;
                        heapArray[ childTwoIndex ] = childTwo;

                        trickleDownArrayHeap( childTwoIndex );
                     }
               }
            // Else parent is less than both children and the trickle down was
            // successful

            /*
             * Programmer's Note: I accomplished for getting this drawn out on
             * paper then figuring out all I needed to change was my second
             * "else if" to just an if since the right child might still need to
             * trickle down
             */
         }
   }
