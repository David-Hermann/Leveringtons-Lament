package p2_package;

/**
 * Date: 2 September 2020
 * <p>
 * Program: PA02
 * <p>
 * Class: CS249 - 002
 * <p>
 * "I've been poisoned by my constituents!"
 * <p>
 * Class manages quantities of state data found in StateDataClass objects
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class StateManagementClass
	{
	   // Constants
	   /**
	    * Constant default capacity
	    */
	   private final int DEFAULT_CAPACITY = 10;

	   /**
	    * Constant used if item not found in array
	    */
	   private final int NOT_FOUND = -1;

	   // Member variables
	   /**
	    * Private array holding state data
	    */
	   private StateDataClass[] stateArray;

	   /**
	    * Private capacity and size data
	    */
	   private int capacity;

	   /**
	    * Private capacity and size data
	    */
	   private int size;

	   // Constructors
	   /**
	    * Default constructor
	    */
	   public StateManagementClass()
	      {
	         this.capacity = DEFAULT_CAPACITY;
	         this.stateArray = new StateDataClass[ this.capacity ];
	         // this.size initialized to 0 since no states are in the array
	         this.size = 0;
	      }

	   /**
	    * Initialization constructor
	    *
	    * @param initialCapacity integer value to set class initial capacity
	    */
	   public StateManagementClass ( int initialCapacity )
   	   {
   	      this.capacity = initialCapacity;
   	      this.stateArray = new StateDataClass[ this.capacity ];
   	      // this.size initialized to 0 since no states are in the array
   	      this.size = 0;
   	   }


	   /**
	    * Copy constructor
	    * <p>
       * Note: Assumes object array has some configured capacity
       *
	    * @param copied StateManagementClass object to be copied
	    */
	   public StateManagementClass( StateManagementClass copied )
   	   {
   	      this.capacity = copied.capacity;
   	      this.stateArray = new StateDataClass[ this.capacity ];

   	      // Copy values from copied to this object and set size accordingly
   	      copyArrayData( this.stateArray, copied.stateArray );
   	      this.size = copied.size;
   	   }

	   // Methods
	   /**
	    * If there is room in the array, creates and adds a new StateDataClass
	    * object to list
	    * <p>
       * Note: Overloaded method
       *
	    * @param newState StateDataClass object to be added to array
	    * @return Boolean result of item addition to array
	    */
	   public boolean addState( StateDataClass newState )
	      {
	         boolean stateAdded = false;
	         StateDataClass copyNewState;

	         if( size < capacity )
	            {
	               copyNewState = new StateDataClass( newState );
	               stateArray[ size ] = copyNewState;
	               size++;
	               stateAdded = true;
	            }

	         return stateAdded;
	      }

	   /**
	    * Creates a StateDataClass object, then adds to list using copy
	    * constructor
	    * <p>
       * Note: Uses anonymous StateDataClass instantiation in call to other
       * method; no more than one line of code
       * <p>
       * Note: Overloaded method
       *
	    * @param addStateName String name of state
	    * @param addStateAbbrev String state abbreviation
	    * @param addStatePop long population of state
	    * @return Boolean result of adding state
	    */
	   public boolean addState( String addStateName, String addStateAbbrev,
	                                                           long addStatePop )
	      {
	         /*
	          * ATTN: Grader
	          *
	          * The second author of this program would like to apologize for the
	          * additional line of code contained in this method. To meet General
	          * Usage Rubric compliance, this additional line of code was added
	          * to prevent going over the specified eighty character limit per
	          * line of code.
	          *
	          * With great regret,
	          *
	          * David Hermann
	          * Sophomore at Northern Arizona University
	          * Dumpster Outside of the Campus Bookstore
	          * Flagstaff, Arizona 86011
	          */
	         return addState( new StateDataClass( addStateName, addStateAbbrev,
	                                                             addStatePop ) );
	      }

	   /**
	    * Copies state list from one array to other
	    * <p>
       * Note: To protect from aliasing, must create and assign new
       * StateDataClass to each element in destination array
       *
	    * @param dest StateDataClass array to which data is copied
	    * @param source StateDataClass array from which data is copied
	    */
	   private void copyArrayData( StateDataClass[] dest,
	                                                    StateDataClass[] source )
   	   {
   	      StateDataClass currentCopy;
            int index;

            if( source != null )   // Protect from NullPointerException
               {
                  for( index = 0; index < dest.length; index++ )
                     {
                        if( source[ index ] != null )
                           {
                              currentCopy = new StateDataClass( source[
                                                                      index ] );
                              dest[ index ] = currentCopy;
                           }
                     }
               }
   	   }

	   /**
	    * Local array dump for diagnostics
	    */
	   public void diagnosticArrayDump()
	      {
	         String stateInArray;
	         int index;
	         for( index = 0; index < size; index++ )
	            {
	               stateInArray = stateArray[ index ].toString();
	               System.out.println( stateInArray );
	            }
	      }

	   /**
	    * Finds state in array, returns data
	    * <p>
       * Note: Uses findStateIndex
       *
       * @param state StateDataClass object to be found
       * @return StateDataClass object found, or null if not found
	    */
	   public StateDataClass findState( StateDataClass state )
	      {
	         StateDataClass foundState = null;
	         int findStateIndex = findStateIndex( state );

	         if( findStateIndex != NOT_FOUND )
	            {
	               foundState = stateArray[ findStateIndex ];
	            }

	         return foundState;
	      }

	   /**
	    * Finds state's index in array, returns index, or returns NOT_FOUND
	    * if item is not in the array
	    * <p>
       * Note: Must use appropriate comparison method for class
       *
	    * @param state StateDataClass object to be found
	    * @return index of StateDataClass object, or NOT_FOUND
	    */
	   private int findStateIndex( StateDataClass state )
	      {
	         int stateIndex = NOT_FOUND;
	         int arrayIndex;

	         for( arrayIndex = 0; arrayIndex < size; arrayIndex++ )
	            {
	               if( stateArray[ arrayIndex ].compareTo( state ) == 0 )
	                  {
	                     stateIndex = arrayIndex;
	                  }
	            }

	         return stateIndex;
	      }

	   /**
	    * Removes state from array, shifts elements down to keep array contiguous
	    * <p>
       * Note: Uses findStateIndex
       *
	    * @param state StateDataClass object to be removed
	    * @return StateDataClass object that was removed, or null if not found
	    */
	   public StateDataClass removeState( StateDataClass state )
	      {
	         StateDataClass secededState = findState( state );   // Civil War
	         int secededStateIndex = findStateIndex( state );    // time yeehaw
	         int index;

	         if( secededState != null )
	            {
	               stateArray[ secededStateIndex ] = null;

	               for( index = secededStateIndex + 1; index < size; index++ )
	                  {
	                     swapValues( this.stateArray, index, index - 1 );
	                  }

	               size--;
	            }

	         return secededState;
	      }

	   /**
	    * Sorts elements using the bubble sort algorithm
	    * <p>
       * Note: Creates new StateDataClass array, sorts contents of array, and
       * returns the sorted result; does not modify (this) object state array
       *
	    * @return new StateDataClass array with sorted items
	    */
	   public StateManagementClass runBubbleSort()
	      {
	         StateManagementClass sorted = new StateManagementClass( this );
	         boolean swapped = true;
	         int index;

	         // Outer loop to determine if swaps are still happening
	         while( swapped )
	            {
	               swapped = false;
	               index = 1;   // Starting from index 1 makes more sense to me
	               // Inner loop to continue swapping values until they "bubble"
	               // to the top
	               while( index < sorted.size )
	                  {
	                     // Use override method .compareTo() for StateDataClass
	                     if( sorted.stateArray[ index ].compareTo(
	                                       sorted.stateArray[ index - 1 ] ) < 0 )
	                        {
	                           swapValues( sorted.stateArray, index, index - 1 );
	                           swapped = true;
	                        }

	                     index++;
	                  }
	            }

	         return sorted;
	      }

	   /**
	    * Sorts character elements using the insertion sort algorithm
	    * <p>
       * Note: Creates new StateDataClass array, sorts contents of array, and
       * returns the sorted result; does not modify (this) object state array
       *
	    * @return new StateDataClass array with sorted items
	    */
	   public StateManagementClass runInsertionSort()
	      {
	         StateManagementClass sorted = new StateManagementClass( this );
	         StateDataClass tempHoldForState = null;
	         int searchIndex, listIndex = 1;

	         // Start at index 1 and loop until all values are sorted
	         while( listIndex < sorted.size )
	            {
	             // Make copy of StateDataClass to insert in its proper spot
	             tempHoldForState = new StateDataClass( sorted.stateArray[
	                                                               listIndex ] );

	             // Start at index given by outer loop
	             searchIndex = listIndex;

	             // Compare StateDataClasses using override .compareTo() method
	             while( searchIndex > 0 && tempHoldForState.compareTo(
	                                 sorted.stateArray[ searchIndex - 1 ] ) < 0 )
	                {
	                   // Copy the item below to present index
	                   swapValues( sorted.stateArray, searchIndex,
	                                                           searchIndex - 1 );

	                   searchIndex--;
	                }

	                // Insert StateDataClass into
	                sorted.stateArray[ searchIndex ] = tempHoldForState;

	                listIndex++;
	            }

            return sorted;
	      }

	   /**
	    * Sorts character elements using the selection sort algorithm
	    * <p>
       * Note: Creates new StateDataClass array, sorts contents of array, and
       * returns the sorted result; does not modify (this) object state array
       *
	    * @return new StateDataClass array with sorted items
	    */
	   public StateManagementClass runSelectionSort()
   	   {
   	      StateManagementClass sorted = new StateManagementClass( this );
   	      int listIndex, lowestIndex, currentSearchIndex;

   	      // Loop through sorted up to second to last StateDataClass object
   	      for( listIndex = 0; listIndex < sorted.size - 1; listIndex++ )
   	         {
   	            // Set initial lowestIndex at first element to be searched
   	            lowestIndex = listIndex;

   	            // Loop across sorted.stateArray from current element to end
   	            for( currentSearchIndex = listIndex + 1; currentSearchIndex <
   	                                       sorted.size; currentSearchIndex++ )
   	               {
   	                  // Compare values and determine if swap needed
   	                  if( sorted.stateArray[ currentSearchIndex ].compareTo(
   	                                  sorted.stateArray[ lowestIndex ] ) < 0 )
   	                     {
   	                        // Reset lowest index to current search index
   	                        lowestIndex = currentSearchIndex;
   	                     }
   	               }
   	            // Swap current StateDataClass object with lowest value object
   	            // according to override .compareTo() method
   	            swapValues( sorted.stateArray, listIndex, lowestIndex );
   	          }

            return sorted;
   	   }

	   /**
	     * Uses Shell's sorting algorithm to sort an array of integers
	     * <p>
	     * Shell's sorting algorithm is an optimized insertion algorithm
	     * <p>
	     * Not my code -DCH
	     * <p>
	     * Note: Creates new StateDataClass array, sorts contents of array,
	     * and returns the sorted result;
	     * does not modify (this) object state array
	     *
	     * @return new StateDataClass array with sorted items
	     */
      public StateManagementClass runShellSort()
          {
           int gap, gapPassIndex, insertionIndex;
           StateDataClass tempItem;

           StateManagementClass sorted = new StateManagementClass( this );

           for( gap = size / 2; gap > 0; gap /= 2 )
              {
               for( gapPassIndex = gap; gapPassIndex < size; gapPassIndex++ )
                  {
                   tempItem = sorted.stateArray[ gapPassIndex ];

                   insertionIndex = gapPassIndex;

                   while( insertionIndex >= gap
                   && sorted.stateArray[ insertionIndex - gap].compareTo(
                                                                tempItem ) > 0 )
                      {
                      sorted.stateArray[ insertionIndex ] =
                                      sorted.stateArray[ insertionIndex - gap ];

                       insertionIndex -= gap;
                      }  // end search loop

                   sorted.stateArray[ insertionIndex ] = tempItem;
                  }  // end list loop

              }  // end gap size setting loop

           return sorted;
          }

      /**
       * Swaps values within given array
       *
       * @param stateArray StateDataClass array within which elements will be
       *    swapped
       * @param indexOne integer index for one of the two items to be swapped
       * @param indexOther integer index for the other of the two items to be
       *    swapped
       */
      private void swapValues(StateDataClass[] stateArray, int indexOne,
                                                                 int indexOther)
         {
            // StateDataClass objects are copied to prevent aliasing
            StateDataClass firstState = new StateDataClass ( stateArray[
                                                                   indexOne ] );
            StateDataClass secondState = new StateDataClass ( stateArray[
                                                                 indexOther ] );
            stateArray[ indexOne ] = secondState;
            stateArray[ indexOther ] = firstState;
         }
	}
