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
 * Class wrapper for a Java array, with management operations
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class ArrayFoundationClass
   {
      // Class constants
      /**
       * Default constant capacity
       */
      private static final int DEFAULT_CAPACITY = 10;

      /**
       * Default failed access constant
       */
      public static final int FAILED_ACCESS = -999999;

      /**
       * Constant used for allowing setAtIndex to insert value at index
       */
      public static final int INSERT_AFTER = 1003;

      /**
       * Constant used for allowing setAtIndex to insert value at index
       */
      public static final int INSERT_BEFORE = 1002;

      /**
       * Constant used for allowing accessAtIndex to remove an item
       */
      public static final int REMOVE = 1004;

      /**
       * Constant used for allowing setAtIndex to replace value at index
       */
      public static final int REPLACE = 1001;

      /**
       * Constant used for allowing accessAtIndex to retrieve an item
       */
      public static final int RETRIEVE = 1005;

      // Class variables
      /**
       * Member data
       */
      private int arrayCapacity;

      /**
       * Member data
       */
      private int arraySize;

      /**
       * Member - integer array
       */
      private int[] localArray;

      // Class constructors
      /**
       * Default constructor, initializes array to default capacity
       */
      public ArrayFoundationClass()
         {
            localArray = new int[ DEFAULT_CAPACITY ];
            arrayCapacity = DEFAULT_CAPACITY;
            arraySize = 0;
         }

      /**
       * Initializing constructor, initializes array to specified capacity
       * <p>
       * Note: Does not allow array capacity to be set to less than default
       * capacity
       *
       * @param capacity Integer maximum capacity specification for the array
       */
      public ArrayFoundationClass( int capacity )
         {
            if( capacity > DEFAULT_CAPACITY )
               {
                  localArray = new int[ capacity ];
                  arrayCapacity = capacity;
                  arraySize = 0;
               }
            else
               {
                  localArray = new int[ DEFAULT_CAPACITY ];
                  arrayCapacity = DEFAULT_CAPACITY;
                  arraySize = 0;
               }
         }

      /**
       * Copy constructor, initializes array to size and capacity of copied
       * array, then copies only the elements up to the given size
       *
       * @param copied ArrayFoundationClass object to be copied
       */
      public ArrayFoundationClass( ArrayFoundationClass copied )
         {
            this.localArray = new int[ copied.arrayCapacity ];
            this.arrayCapacity = copied.arrayCapacity;

            for( this.arraySize = 0; this.arraySize < copied.arraySize;
                                                              this.arraySize++ )
               {
                  this.localArray[ this.arraySize ] =
                                            copied.localArray[ this.arraySize ];
               }

            // Add 1 to this.arraySize to get it to actual amount of elements in
            // this.localArray
            this.arraySize++;
         }

      // Class methods
      /**
       * Utility method used by getAtIndex and removeAtIndex to access and
       * possibly remove element depending on control code
       * <p>
       * Note: Uses only one loop
       *
       * @param controlCode Integer value with either RETRIEVE or REMOVE to
       * control operations
       * @param index Integer index of element to be retrieved or removed
       * @return Integer value at element or FAILED_ACCESS if attempt to access
       * data out of bounds
       */
      private int accessAtIndex( int controlCode, int index )
         {
            int valueAtIndex = FAILED_ACCESS, loopIndex;

            if( index < arraySize )
               {
                  // "Default" case is to retrieve with special case for REMOVE
                  valueAtIndex = localArray[ index ];

                  if( controlCode == REMOVE )
                     {
                           /*
                            * Begin at index of element to remove and shifts
                            * values "down" one index to keep array continuous
                            * Note: keeps final element in array but is
                            * inaccessible since arraySize is decremented by 1
                            */
                           for( loopIndex = index; loopIndex < arraySize - 1;
                                                                   loopIndex++ )
                              {
                                 localArray[ loopIndex ] = localArray[ loopIndex
                                                                          + 1 ];
                              }

                           arraySize--;
                     }
               }
            // Else return FAILED_ACCESS as index was out of bounds

            return valueAtIndex;
         }

      /**
       * Checks for need to resize; if this is necessary, creates new array with
       * double the original capacity, loads data from original array to new
       * one, then sets localArray to new array
       */
      public void checkForResize()
         {
            int[] resizeArray = new int[ arrayCapacity * 2 ];
            int copyArrayIndex;

            if( arraySize == arrayCapacity )
               {
                  for( copyArrayIndex = 0; copyArrayIndex < arraySize;
                                                              copyArrayIndex++ )
                     {
                        resizeArray[ copyArrayIndex ] = localArray[
                                                               copyArrayIndex ];
                     }

                  localArray = resizeArray;
               }
         }

      /**
       * Clears array of all valid values by setting array size to zero, values
       * remain in array but are not accessible
       */
      public void clear()
         {
            arraySize = 0;
         }

      /**
       * Accesses item in array at specified index if index within array size
       * bounds
       * <p>
       * Note: Calls accessAtIndex with RETRIEVE to conduct action
       *
       * @param accessIndex Integer index of requested element value
       * @return Integer accessed value if successful, FAILED_ACCESS if not
       */
      public int getAtIndex( int accessIndex )
         {
            // accessAtIndex checks if accessIndex is valid
            int valueAtIndex = accessAtIndex( RETRIEVE, accessIndex );

            return valueAtIndex;
         }

      /**
       * Description: Gets current capacity of array
       * <p>
       * Note: capacity of array indicates number of values the array can hold
       *
       * @return Integer capacity of array
       */
      public int getCurrentCapacity()
         {
            return arrayCapacity;
         }

      /**
       * Description: Gets current size of array
       * <p>
       * Note: size of array indicates number of valid or viable values in the
       * array
       *
       * @return Integer size of array
       */
      public int getCurrentSize()
         {
            return arraySize;
         }

      /**
       * Tests for size of array equal to zero, no valid values stored in array
       *
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
         {
            // Evaluates to a boolean true or false and by extension if there
            // are values in the array, arraySize != 0
            return ( arraySize == 0 );
         }

      /**
       * Description: Removes item from array at specified index if index within
       * array size bounds
       * <p>
       * Note: Each data item from the element immediately above the remove
       * index to the end of the array is moved down by one element
       * <p>
       * Note: Calls accessAtIndex with REMOVE to conduct action
       *
       * @param removeIndex Integer index of element value to be removed
       * @return Removed integer value if successful, FAILED_ACCESS if not
       */
      public int removeAtIndex( int removeIndex )
         {
            // accessAtIndex checks if removeIndex is valid
            int valueRemoved = accessAtIndex( REMOVE, removeIndex );

            return valueRemoved;
         }

      /**
       * Sets item in array at specified index
       * <p>
       * Note: If constant REPLACE is used, new value overwrites value at given
       * index
       * <p>
       * Note: If constant INSERT_BEFORE is used, new value is inserted prior to
       * the value at the given index moving all other elements up by one
       * <p>
       * Note: If constant INSERT_AFTER is used, new value is inserted after the
       * value at the given index moving all other elements up by one
       * <p>
       * Note: Accepts index for appending to array (i.e., above last valid
       * index
       * <p>
       * Note: Method checks for available array capacity and adjusts it as
       * needed prior to inserting new item
       * <p>
       * Note: Method must also check for correct array boundaries depending
       * upon INSERT/REPLACE state
       *
       * @param replaceFlag Integer flag to indicate insertion or replacement in
       * the array
       * @param setIndex Integer index of element at which value is to be
       * inserted
       * @param newValue Integer value to be placed in array
       * @return Boolean success if inserted, or failure if incorrect index was
       * used
       */
      public boolean setAtIndex( int replaceFlag, int setIndex, int newValue )
         {
            // set loopBottomLimit to setIndex
            int loopBottomLimit = setIndex, loopIndex;

            // check for REPLACE flag AND if setIndex in bounds (within list),
            // also not empty
            if( replaceFlag == REPLACE && setIndex < arraySize )
               {
                  // set the replace value to the index
                  localArray[ setIndex ] = newValue;

                  // return true
                  return true;
               }

            /*
             * check for setIndex >= 0 AND ( ( INSERT_BEFORE flag AND
             * setIndex <= size ) OR ( INSERT_AFTER flag AND setIndex < size ) )
            */
            if( setIndex >= 0 && ( ( replaceFlag == INSERT_BEFORE &&
                     setIndex <= arraySize ) || ( replaceFlag == INSERT_AFTER &&
                                                      setIndex <= arraySize ) ))
               {
                  // check for resize, adjust capacity
                  checkForResize();

                  // check for setIndex equal to size
                  if( setIndex == arraySize )
                     {
                        // assign value to size
                        localArray[ arraySize ] = newValue;

                        // increment the size
                        arraySize++;

                        // return true
                        return true;
                     }
                  else
                     {
                        // otherwise, assume setIndex not equal to size

                        // check for INSERT_AFTER
                        if( replaceFlag == INSERT_AFTER )
                           {
                              // increment loopBottomLimit
                              loopBottomLimit++;
                           }

                        // loop to pull values up - start at size, go down to
                        // > loopBottomLimit
                        for( loopIndex = arraySize; loopIndex > loopBottomLimit;
                                                                   loopIndex-- )
                           {
                              // set value from current to next element
                              localArray[ loopIndex + 1 ] = localArray[
                                                                    loopIndex ];

                              // array at loop index set to array at loop
                              // index - 1
                              localArray[ loopIndex ] = localArray[ loopIndex -
                                                                            1 ];
                           }

                        // insert value after loop
                        localArray[ loopIndex ] = newValue;

                        // increment arraySize
                        arraySize++;

                        // return true
                        return true;
                     }
               }

               // return false
               return false;
         }
   }
