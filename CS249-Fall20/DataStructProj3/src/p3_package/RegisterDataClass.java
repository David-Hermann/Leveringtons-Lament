package p3_package;

/**
 * Date: 9 September 2020
 * <p>
 * Program: PA03
 * <p>
 * Class: CS249 - 002
 * <p>
 * "Uh-oh, back to the math again."
 * <p>
 * Class used to manage and sort multiple RegisterClasses
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class RegisterDataClass
   {
      // Class Constants =======================================================
      /**
       * Constant default capacity
       */
      private final int DEFAULT_CAPACITY = 10;

      /**
       * Constant used if item not found in array
       */
      private final int NOT_FOUND = -1;

      // Member variables ======================================================
      /**
       * Private array holding register data
       */
      private RegisterClass[] registerArray;

      /**
       * Private capacity data
       */
      private int capacity;

      /**
       * Private size data
       */
      private int size;

      // Constructors ==========================================================

      /**
       * Default constructor
       */
      public RegisterDataClass()
         {
            this.capacity = DEFAULT_CAPACITY;
            this.registerArray = new RegisterClass[ this.capacity ];
            this.size = 0;
         }

      /**
       * Copy constructor
       *
       * @param copied RegisterDataClass object to be copied
       */
      public RegisterDataClass( RegisterDataClass copied )
         {
            this.capacity = copied.capacity;
            this.registerArray = new RegisterClass[ this.capacity ];
            copyArrayData( this.registerArray, copied.registerArray, 0, copied.capacity );
            this.size = copied.size;
         }

      // Methods ===============================================================
      /**
       * Adds a RegisterClass item to list
       *
       * @param newRegister RegisterClass object to be added to array
       */
      public void addRegister( RegisterClass newRegister )
         {
            checkForResize();
            registerArray[ size ] = newRegister;
            size++;
         }

      /**
       * Checks array capacity to verify there is room to accept new data; if
       * array is at capacity, resizes array and copies data as needed to double
       * capacity of the array
       */
      private void checkForResize()
         {
            RegisterClass[] resized;
            int newCapacity;

            if( size == capacity )
               {
                  newCapacity = capacity * 2;
                  resized = new RegisterClass[ newCapacity ];
                  copyArrayData( resized, registerArray, 0, size - 1 );
                  registerArray = resized;
                  capacity = newCapacity;
               }
         }

      /**
       * Copies register list from one array to other
       * <p>
       * Note: Must create new RegisterClass object to assign to each element to
       * destination array to eliminate aliasing
       *
       * @param dest RegisterClass array to which data is copied
       * @param source RegisterClass array from which data is copied
       * @param lowIndex Integer index at which to start copying from source
       * array, inclusive
       * @param highIndex Integer index at which to end copying from source
       * array, inclusive
       */
      private void copyArrayData( RegisterClass[] dest, RegisterClass[] source,
                                                   int lowIndex, int highIndex )
         {
            int index;

            for( index = lowIndex; index <= highIndex; index++ )
               {
                  dest[ index ] = new RegisterClass( source[ index ] );
               }
         }

      /**
       * Optional method, local array dump for diagnostics
       */
      public void diagnosticArrayDump()
         {
            RegisterClass printReg;
            String printRegBase;
            long printRegDeci;
            int base, index;
            for( index = 0; index < size; index++ )
               {
                  printReg = new RegisterClass( registerArray[ index ] );
                  base = printReg.base;
                  printRegBase = printReg.getValueAsBase();
                  printRegDeci = printReg.getValueAsDecimal();

                  System.out.println( "Base: " + base );
                  System.out.println( "Base Value: " + printRegBase );
                  System.out.println( "Decimal Equivalent: " + printRegDeci );
               }
         }

      /**
       * Finds register in array, returns data
       * <p>
       * Note: Method must use findRegisterIndex
       *
       * @param registerRegisterClass object to be found
       * @return Object found, or null if not found
       */
      public RegisterClass findRegister( RegisterClass register )
         {
            RegisterClass foundRegister = null;
            int foundIndex = findRegisterIndex( register );

            if( foundIndex != NOT_FOUND )
               {
                  foundRegister = registerArray[ foundIndex ];
               }

            return foundRegister;
         }

      /**
       * Finds register's index in array, returns index, or returns NOT_FOUND
       * if item is not in the array
       *
       * @param register RegisterClass object to be found
       * @return Index of RegisterClass object, or NOT_FOUND
       */
      private int findRegisterIndex( RegisterClass register )
         {
            int regIndex = NOT_FOUND;
            int index = 0;
            boolean found = false;

            do
               {
                  if( register.compareTo( registerArray[ index ] ) == 0 )
                     {
                        regIndex = index;
                        found = true;
                     }
                  index++;
               }
            while( index < size && !found );

            return regIndex;
         }

      /**
       * Removes register from array, shifts elements down to keep array
       * contiguous
       * <p>
       * Note: Method must use findRegisterIndex
       *
       * @param register RegisterClass object to be removed
       * @return RegisterClass object that was removed, or null if not found
       */
      public RegisterClass removeRegister( RegisterClass register )
         {
            RegisterClass removedRegister = null;
            int foundIndex = findRegisterIndex( register );
            int index;

            if( foundIndex != NOT_FOUND )
               {
                  removedRegister = registerArray[ foundIndex ];

                  for( index = foundIndex; index < size - 1; index++ )
                     {
                        swapValues( registerArray, index, index + 1 );
                     }
                  size--;
               }

            return removedRegister;
         }

      /**
       * Merges RegisterClass values brought in between a low and high index
       * segment (inclusive) of an array
       * <p>
       * Creates one local temporary array for the exact size needed to conduct
       * merge
       *
       * @param workingArray RegisterClass array to be managed by method
       * @param lowIndex Lowest index of array segment to be managed
       * @param middleIndex Middle index of array segment to be managed
       * @param highIndex High index of array segment to be managed
       */
      private void runMerge( RegisterClass[] workingArray, int lowIndex,
                                                int middleIndex, int highIndex )
         {

      //                                                     57       60       63
      // private void runMerge( RegisterClass[] regArr, int low, int mid, int high )

               // initialize method

                  // find local capacity needed (hi - lo + 1 )

                  // create an array with this capacity

                  // copy array from regArr to regCpy

                  // create indices
                     // set lowSide to 0
                     // set highSide to mid - low + 1
                     // set lowMax to mid - low
                     // set highMax to local cap - 1
                     // set wkgRegArrIndex (WRAI to low

               // loop while lowSide index <= lowMax index AND highSide <== highMax

                   // check if value at lowSide index < value at highSide index

                       // assign value at lowSide in cpyArr to regArr at WRAI

                       // increment lowSide

                   // otherwise, ls not < hs

                       // assign value at highSide in cpyArr to regArr at WRAI

                       // increment highSide

                   // increment WRAI

               // end comparison loop

               // loop while lowSide index <= lowMax index

                   // assign value at lowSide in cpyArr to regArr at WRAI

                   // increment lowSide

                   // increment WRAI

               // end left clean up loop

               // loop while highSide index <= highMax index

                   // assign value at highSide in cpyArr to regArr at WRAI

                   // increment highSide

                   // increment WRAI

               // end left clean up loop

            RegisterClass[] tempLocalArray;
            int halfOneFirst = lowIndex, halfOneLast = middleIndex;
            int halfTwoFirst = middleIndex + 1, halfTwoLast = highIndex;
            int index = 0, localCapacity = highIndex - lowIndex + 1;
            tempLocalArray = new RegisterClass[ localCapacity ];

            copyArrayData( tempLocalArray, workingArray, lowIndex,
                                                                  middleIndex );

            while( halfOneFirst <= halfOneLast && halfTwoFirst <= halfTwoLast )
               {
                  if( tempLocalArray[ halfOneFirst ].compareTo( tempLocalArray[
                                                          halfTwoFirst ] ) > 0 )
                     {
                        swapValues( tempLocalArray, index, lowIndex );
                        halfOneFirst++;
                     }
                  else
                     {
                        swapValues( tempLocalArray, index, middleIndex + 1 );
                        halfTwoFirst++;
                     }

                  index++;
               }
         }

      /**
       * RegisterClass data sorted using merge sort algorithm
       * <p>
       * Note: Calls runMergeSortHelper with lower and upper indices of array to
       * be sorted
       * <p>
       * Note: Creates new RegisterClass array, sorts contents of array, and
       * returns the sorted result; does not modify (this) object register array
       *
       * @return RegisterClass array containing sorted data
       */
      public RegisterClass[] runMergeSort()
         {
            // "Driver" method for the merge sort algorithm
            RegisterClass[] sortedArray = new RegisterClass[ size ];

            copyArrayData( sortedArray, registerArray, 0, size - 1 );

            if( size > 1 )
               {
                  runMergeSortHelper( sortedArray, 0, size );
               }
            // Else the array only contains one RegisterClass object, return


            // runMergeSort is the actual sorting process that incorporates
            // breaking the array segment into smaller segments and then merging
            // them back together sorted

            return sortedArray;
         }

      /**
       * Merge sort helper, recursively breaks given array segment down to
       * smaller segments between lowIndex and highIndex (inclusive), then sorts
       * data using merge sort method
       *
       * @param workingArray RegisterClass array holding unsorted values
       * @param lowIndex lowest index of array segment to be managed; this
       * varies as the segments are broken down recursively
       * @param highIndex highest index of array segment to be managed; this
       * varies as the segments are broken down recursively
       */
      private void runMergeSortHelper( RegisterClass[] workingArray,
                                                   int lowIndex, int highIndex )
         {
            int middleIndex = ( highIndex - lowIndex ) / 2;

            if( lowIndex < highIndex )
               {
                  runMergeSortHelper( workingArray, lowIndex, middleIndex );
                  runMergeSortHelper( workingArray, middleIndex + 1,
                                                                    highIndex );
                  runMerge( workingArray, lowIndex, middleIndex, highIndex );
               }

            /*
             * If it wasn't already obvious I have no clue what I'm doing
             * and having three functions to run this algorithm confuses me
             * to no end. Where do I do the comparison? I don't understand why
             * the recursion works. How does the recursion work? Don't get me
             * started on how the heck quick sort works. I thought I understood
             * this while going over it in Instructor Beverly's lectures...
             */
         }

      /**
       * Partitions array using the first value as the pivot; when this method
       * is complete the partition value is in the correct location in the array
       *
       * @param workingArray RegisterClass array holding array to be managed by
       * method
       * @param lowIndex Low index of array segment to be partitioned
       * @param highIndex High index of array segment to be partitioned
       * @return Integer index of partition pivot
       */
      private int runPartition( RegisterClass[] workingArray, int lowIndex,
                                                                 int highIndex )
         {

            //private int runPartition( RegisterClass[] regArr, int low, int high )

               // initialize method

                  // set my pivot value, from the array

                  // set pivot index to low

                  // set wkg index to low + 1

               // loop across the indices, inclusive

                   // check if the current element (index) is less than the pivot

                       // increment pivot index

                       // swap between the pivot index and the working index

                   // increment index (as needed)

               // end wkg loop

               // swap between the original pivot index (low) and the current pivot index

               // return regArr

            int pivotPartitionIndex = 0;

            return pivotPartitionIndex;
         }

      /**
       * RegisterClass data sorted using quick sort algorithm
       * <p>
       * Note: Calls runQuickSortHelper with lower and upper indices of array to
       * be sorted
       * <p>
       * Note: Creates new RegisterClass array, sorts contents of array, and
       * returns the sorted result; does not modify (this) object register array
       *
       * @return RegisterClass array containing sorted data
       */
      public RegisterClass[] runQuickSort()
         {
            // "Driver" method for quick sort algorithm
            RegisterClass[] sortedArray = new RegisterClass[ size ];

            copyArrayData( sortedArray, registerArray, 0, size - 1 );

            if( size > 1 )
               {
                  runQuickSortHelper( sortedArray, 0, size );
               }
            // Else the array only contains one RegisterClass object, return

            return sortedArray;
         }

      /**
       * Helper method run with parameters that support recursive access
       *
       * @param workingArray RegisterClass array holding unsorted values
       * @param lowIndex Low index of the segment of the array to be processed
       * @param highIndex High index of the segment of the array to be processed
       */
      private void runQuickSortHelper( RegisterClass[] workingArray,
                                                   int lowIndex, int highIndex )
         {
            runPartition( workingArray, lowIndex, highIndex );
         }

      /**
       * Swaps values within given array
       *
       * @param workingArray Array of RegisterClasses used for swapping
       * @param indexOne Integer index for one of the two items to be swapped
       * @param indexOther Integer index for the other of the two items to be
       * swapped
       */
      private void swapValues( RegisterClass[] workingArray, int indexOne,
                                                                int indexOther )
         {
            RegisterClass firstReg = new RegisterClass ( workingArray[
                                                                    indexOne] );
            RegisterClass secondReg = new RegisterClass ( workingArray[
                                                                 indexOther ] );
            workingArray[ indexOne ] = secondReg;
            workingArray[ indexOther ] = firstReg;
         }
   }
