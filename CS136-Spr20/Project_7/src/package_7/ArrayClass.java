/*
 * Author: David Hermann
 * Date: 11th March 2020
 * Program: PA07
 * Class: CS136 - 001
 *
 * "These uses of the this keyword are getting out of this.hand!"
 */

package package_7;

public class ArrayClass
   {
      private static final int DEFAULT_CAPACITY = 10;
      private static final String EMPTY_STRING = "";
      private String[] stringArray;
      private int arrayCapacity;

      // Constructors
      public ArrayClass()
         {
            this.arrayCapacity = DEFAULT_CAPACITY;
            this.stringArray = new String[ arrayCapacity ];
         }

      public ArrayClass( int capacity )
         {
            this.arrayCapacity = capacity;
            this.stringArray = new String[ this.arrayCapacity ];
         }

      public ArrayClass( ArrayClass copied )
         {
            this.arrayCapacity = copied.getCurrentCapacity();
            this.stringArray = new String[ this.arrayCapacity ];
            int index;

            for( index = 0; index < this.arrayCapacity; index++ )
               {
                  this.stringArray[ index ] = copied.accessItemAt( index );
               }
         }

      // Class methods
      public int getCurrentCapacity()
         {
            return this.arrayCapacity;
         }

      public boolean arrayAtCapacity()
         {
            boolean capacityCheck = true;
            int index;

            while( capacityCheck )
               {
                  for( index = 0; index < arrayCapacity; index++ )
                     {
                        if( stringArray[ index ] == null )
                           {
                              capacityCheck = false;
                           }
                     }
               }

            return capacityCheck;
         }

      public String accessItemAt( int accessIndex )
         {
            String item;

            if( accessIndex < arrayCapacity )
               {
                  item = stringArray[ accessIndex ];
               }
            else
               {
                  item = EMPTY_STRING;
               }

            return item;
         }

      public void listNames()
      {
         int index;

         for( index = 0; index < this.arrayCapacity; index++ )
            {
               if( stringArray[ index ] != null )
                  {
                     System.out.println( "Name: " + stringArray[ index ] );
                  }
            }
      }

      public boolean resize( int newCapacity )
      {
         boolean successfulResize = false;

         if( newCapacity >= this.arrayCapacity )
            {
               this.arrayCapacity = newCapacity;
               successfulResize = true;
            }

         return successfulResize;
      }

      public boolean setItemAt( int indexToSet, String newValue )
      {
         boolean successfulReplacement = false;

         if( indexToSet < this.arrayCapacity )
            {
               this.stringArray[ indexToSet ] = newValue;
               successfulReplacement = true;
            }

         return successfulReplacement;
      }
   }