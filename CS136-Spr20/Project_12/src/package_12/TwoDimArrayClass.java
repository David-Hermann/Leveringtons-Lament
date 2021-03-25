package package_12;

/**
 * Date: 22 April 2020
 * <p>
 * Program: PA12
 * <p>
 * Class: CS136 - 001
 * <p>
 * "public class DavidHermann extends DumbPersonClass"
 * <p>
 * Class wrapper for a Two Dimensional Java array
 * <p>
 * Note: Maintains a row and column capacity value for maximum number of items
 * that can be stored; protects all appropriate operations from out of bounds
 * conditions
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 *
 */
public class TwoDimArrayClass
   {
      // Constants
      /**
       * Default capacity of array
       */
      private static final int DEFAULT_CAPACITY = 10;
      /**
       * Constant for empty string which would be returned if an attempt is made
       * to access the array outside of the bounds
       */
      public static final int FAILED_ACCESS = -999999;

      // Variables
      /**
       * Integer array that holds data
       */
      protected int[][] localTD_Array;
      /**
       * Current capacity of array rows
       */
      protected int rowCapacity;
      /**
       * Current capacity of array columns
       */
      protected int colCapacity;

      // Constructors
      /**
       * Default constructor, initializes member data
       * <p>
       * Row and column capacities are set to DEFAULT_CAPACITY
       */
      public TwoDimArrayClass()
         {
            this.rowCapacity = DEFAULT_CAPACITY;
            this.colCapacity = DEFAULT_CAPACITY;
            this.localTD_Array = new int[ rowCapacity ][ colCapacity ];
         }

      /**
       * Initializing constructor, initializes array to specified capacities
       *
       * @param rowCap Integer initial row capacity specification
       * @param colCap Integer initial column capacity specification
       */
      public TwoDimArrayClass( int rowCap, int colCap )
         {
            this.rowCapacity = rowCap;
            this.colCapacity = colCap;
            this.localTD_Array = new int[ rowCapacity ][ colCapacity ];
         }

      /**
       * Copy constructor, initializes array to capacities of copied array, then
       * copies all array elements
       * @param copied TwoDimArrayClass object to be copied
       */
      public TwoDimArrayClass( TwoDimArrayClass copied )
         {
            this.rowCapacity = copied.rowCapacity;
            this.colCapacity = copied.colCapacity;
            this.localTD_Array = new int[ rowCapacity ][ colCapacity ];

            int rowIndex, colIndex;
            for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
               {
                  for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                     {
                        localTD_Array[ rowIndex ][ colIndex ] =
                                   copied.localTD_Array[ rowIndex ][ colIndex ];
                     }
               }
         }

      // Methods
      /**
       * Gets current column capacity of array
       *
       * @return colCapacity
       */
      public int getCurrentColCapacity()
         {
            return colCapacity;
         }

      /**
       * Gets current row capacity of array
       *
       * @return rowCapacity
       */
      public int getCurrentRowCapacity()
         {
            return rowCapacity;
         }

      /**
       * Accesses item in array at specified row and column indices if indices
       * within array bounds; otherwise returns FAILED_ACCESS
       *
       * @param rowAccessIndex Row index of requested element value
       * @param colAccessIndex Column index of requested element value
       * @return Accessed value if successful, FAILED_ACCESS if not
       */
      public int accessItemAt( int rowAccessIndex, int colAccessIndex )
         {
            int accessedItem = FAILED_ACCESS;

            if ( rowAccessIndex < rowCapacity && colAccessIndex < colCapacity )
               {
                  accessedItem = localTD_Array[ rowAccessIndex ]
                                                             [ colAccessIndex ];
               }

            return accessedItem;
         }

      /**
       * Sets item at specified row and column indices as long as the indices
       * are within the array bounds
       *
       * @param rowIndexToSet Row index of element to place value
       * @param colIndexToSet Column index of element to place value
       * @param newValue Value to be placed in array
       * @return True if element has been assigned, false otherwise
       */
      public boolean setItemAt( int rowIndexToSet, int colIndexToSet,
                                                                  int newValue )
         {
            boolean itemSetSuccessfully = false;

            if ( rowIndexToSet < rowCapacity && colIndexToSet < colCapacity )
               {
                  localTD_Array[ rowIndexToSet ][ colIndexToSet ] = newValue;
                  itemSetSuccessfully = true;
               }

            return itemSetSuccessfully;
         }

      /**
       * Description: Resizes array, duplicates data in resized array
       * <p>
       * Note: Method may resize to any row and column capacity, including
       * smaller capacities; if an array is resized to a smaller row or column
       * capacity, data will likely be lost
       *
       * @param newRowCapacity New row capacity to be set
       * @param newColCapacity New column capacity to be set
       */
      public void resize( int newRowCapacity, int newColCapacity )
         {
            int[][] temp = new int[ newRowCapacity ][ newColCapacity ];

            int rowIndex, colIndex, copyItem;
            for( rowIndex = 0; rowIndex < newRowCapacity; rowIndex++ )
               {
                  for ( colIndex = 0; colIndex < newColCapacity; colIndex++ )
                     {
                        if( rowIndex < this.rowCapacity &&
                                                   colIndex < this.colCapacity )
                           {
                              copyItem = accessItemAt( rowIndex, colIndex );
                           }
                        else
                           {
                              copyItem = 0;
                           }
                        temp[ rowIndex ][ colIndex ] = copyItem;
                     }
               }

            this.localTD_Array = temp;
            this.rowCapacity = newRowCapacity;
            this.colCapacity = newColCapacity;
         }

      /**
       * Method lists names found in array, shows all elements
       * <p>
       * Note: Element data must be aligned vertically, be comma-delimited, but
       * no comma may be displayed prior to the first element or after the last
       * element
       */
      public void dumpTwoDimArray()
         {
            final char NEWLINE_CHAR = '\n';
            final int THREE_DIGITS = 100;

            int rowIndex, colIndex, accessedItem;

            System.out.print( NEWLINE_CHAR );
            System.out.print( "TwoDimArrayClass Array Dump:" + NEWLINE_CHAR );

            for ( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
               {
                  for ( colIndex = 0; colIndex < colCapacity; colIndex++ )
                     {
                        accessedItem = accessItemAt( rowIndex, colIndex );

                        if( colIndex != colCapacity - 1 )
                           {
                              if( accessedItem < THREE_DIGITS )
                                 {
                                    System.out.print( "   " + accessedItem +
                                                                         ", " );
                                 }
                              else
                                 {
                                    System.out.print( "  " + accessedItem +
                                                                         ", " );
                                 }
                           }
                        else
                           {
                              if( accessedItem < THREE_DIGITS )
                                 {
                                    System.out.print( "   " + accessedItem );
                                 }
                              else
                                 {
                                    System.out.print( "  " + accessedItem );
                                 }
                           }
                     }
                  System.out.print( NEWLINE_CHAR );
               }
            System.out.print( NEWLINE_CHAR + NEWLINE_CHAR );
         }
   }
