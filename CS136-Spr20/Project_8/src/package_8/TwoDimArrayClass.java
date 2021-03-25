/*
 * Author: David Hermann
 * Date: 25 March 2020
 * Program: PA08
 * Class: CS136 - 001
 *
 * "I hope I never see another ArrayIndexOutOfBoundsException ever again."
 */

// NOTICE: This class is fully functional as of 31 Mar 2020 but does not meet
// specifications for dumpTwoDimArray() due to no formatting on output

package package_8;

public class TwoDimArrayClass
   {
      // Constants
      private static final int DEFAULT_MAX_CAPACITY = 100;
      private static final int DEFAULT_MIN_CAPACITY = 3;
      public static final int FAILED_ACCESS = -999999;

      // Attributes
      private int[][] localTD_Array;
      private int maxCapacity;
      private int rowCapacity;
      private int colCapacity;

      // Constructors
      // Sets attributes to default values
      TwoDimArrayClass()
         {
            rowCapacity = DEFAULT_MIN_CAPACITY;
            colCapacity = DEFAULT_MIN_CAPACITY;
            maxCapacity = DEFAULT_MAX_CAPACITY;
            localTD_Array = new int[ rowCapacity ][ colCapacity ];
         }

      // Uses passed in parameters to create new custom instance
      TwoDimArrayClass( int rowCap, int colCap, int maxCap )
         {
            rowCapacity = DEFAULT_MIN_CAPACITY;
            colCapacity = DEFAULT_MIN_CAPACITY;
            maxCapacity = maxCap;

            if ( rowCap > DEFAULT_MIN_CAPACITY )
               {
                  rowCapacity = rowCap;
               }

            if ( colCap > DEFAULT_MIN_CAPACITY )
               {
                  colCapacity = colCap;
               }

            if ( rowCapacity > DEFAULT_MAX_CAPACITY )
               {
                  maxCapacity = rowCapacity;
               }
            else if ( colCapacity > DEFAULT_MAX_CAPACITY )
               {
                  maxCapacity = colCapacity;
               }

            localTD_Array = new int[ rowCapacity ][ colCapacity ];
         }

      // Copies one TwoDimArrayClass into a new instance using the
      // accessItemAt( int, int ) method
      public TwoDimArrayClass( TwoDimArrayClass copied )
         {
            rowCapacity = copied.getCurrentRowCapacity();
            colCapacity = copied.getCurrentColCapacity();
            maxCapacity = DEFAULT_MAX_CAPACITY;
            localTD_Array = new int[ rowCapacity ][ colCapacity ];

            int rowIndex, colIndex;
            for( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
               {
                  for( colIndex = 0; colIndex < colCapacity; colIndex++ )
                     {
                        this.localTD_Array[ rowIndex ][ colIndex ] =
                                      copied.accessItemAt( rowIndex, colIndex );
                     }
               }
         }

      // Methods
      // Getters
      public int getCurrentRowCapacity()
         {
            return rowCapacity;
         }

      public int getCurrentColCapacity()
         {
            return colCapacity;
         }

      // Determines whether indices will not trigger an exception
      public boolean arrayIndicesWithinMaxBounds( int testedRowIndex,
                                                            int testedColIndex )
         {
            boolean isWithinBounds = false;

            if ( testedRowIndex < maxCapacity && testedColIndex < maxCapacity )
               {
                  isWithinBounds = true;
               }

            return isWithinBounds;
         }

      // Accesses an integer in the array if the indices are legal, defaults to
      // 0 for a healthy dose of frustration
      public int accessItemAt( int rowAccessIndex, int colAccessIndex )
         {
            int accessedItem = 0;
            boolean legalBounds;

            legalBounds = this.arrayIndicesWithinMaxBounds( rowAccessIndex,
                  colAccessIndex );

            if ( legalBounds )
               {
                  accessedItem = localTD_Array[ rowAccessIndex ]
                                                             [ colAccessIndex ];
               }

            return accessedItem;
         }

      // Resizes array to given arguments and preserves old data
      public void resize( int newRowCapacity, int newColCapacity )
         {
            boolean insideCurrentBounds, allClear = false;

            insideCurrentBounds = arrayIndicesWithinMaxBounds( newRowCapacity,
                                                               newColCapacity );
            if( insideCurrentBounds )
               {
                  if( newRowCapacity > this.rowCapacity && newColCapacity >
                                                              this.colCapacity )
                     {
                        allClear = true;
                     }
                  // Else the array cannot be resized without truncating data
               }
            // Else the array is too large to be created

            if( allClear )
               {
                  int[][] temp = new int[ newRowCapacity ][ newColCapacity ];

                  int rowIndex, colIndex, copyItem;
                  for( rowIndex = 0; rowIndex < newRowCapacity; rowIndex++ )
                     {
                        for ( colIndex = 0; colIndex < newColCapacity; colIndex++ )
                           {
                              if( rowIndex < this.rowCapacity && colIndex < this.colCapacity )
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
            // Else the array will not be resized due to the new indices either
            // being > maxCapacity or < current rowCapacity && colCapacity
         }

      // Sets a specific item in the array, returns true if no problems occurred
      public boolean setItemAt( int rowIndexToSet, int colIndexToSet,
                                                                  int newValue )
         {
            boolean legalBounds, successful = false;

            legalBounds = this.arrayIndicesWithinMaxBounds( rowIndexToSet,
                  colIndexToSet );
            // Within current bounds, easy peasy
            if ( legalBounds )
               {
                  localTD_Array[ rowIndexToSet ][ colIndexToSet ] = newValue;
                  successful = true;
               }
            // Ah crud it's out of current bounds but it can be salvaged
            else if ( rowIndexToSet > maxCapacity &&
                                          rowIndexToSet < DEFAULT_MAX_CAPACITY )
               {
                  resize( rowIndexToSet, this.colCapacity );
                  successful = true;
               }
            else if ( colIndexToSet > maxCapacity &&
                                          colIndexToSet < DEFAULT_MAX_CAPACITY )
               {
                  resize( this.rowCapacity, colIndexToSet );
                  successful = true;
               }
            // If it didn't hit any of these if statements, ya screwed up

            return successful;
         }

      // Prints all contents of the array separated by commas and organized into
      // rows and columns
      public void dumpTwoDimArray()
         {
            System.out.print( "\n" );
            System.out.println( "TwoDimArrayClass Array Dump:" );
            int rowIndex, colIndex;
            for ( rowIndex = 0; rowIndex < rowCapacity; rowIndex++ )
               {
                  for ( colIndex = 0; colIndex < colCapacity; colIndex++ )
                     {
                        System.out.print( accessItemAt( rowIndex, colIndex ) + ",  " );
                     }
                  System.out.print( "\n" );
               }
            System.out.print( "\n\n" );
         }
   }
