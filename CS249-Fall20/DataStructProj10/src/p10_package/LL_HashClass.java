package p10_package;

/**
 * Date: 4 November 2020
 * <p>
 * Program: PA10
 * <p>
 * Class: CS249 - 002
 * <p>
 * "It's the Hash Slinging Slasher!"
 * <p>
 * Hash class which utilizes an array of LinkedList head references to store
 * student data
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class LL_HashClass
   {
      // Class constants ///////////////////////////////////////////////////////
      /**
       * Table size default
       * <p>
       * Table size (capacity) is recommended to be a prime number
       */
      private int DEFAULT_TABLE_SIZE = 11;

      /**
       * Constant used to control access operation
       */
      private final boolean REMOVE = true;

      /**
       * Constant used to control access operation
       */
      private final boolean SEARCH = false;

      // Class variables ///////////////////////////////////////////////////////
      /**
       * Array for hash table
       */
      private SimpleStudentClass[] tableArray;

      /**
       * Size of the base table
       */
      private int tableSize;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Default constructor
       * <p>
       * Initializes array to default table size
       */
      public LL_HashClass()
         {
            tableArray = new SimpleStudentClass[ DEFAULT_TABLE_SIZE ];
            tableSize = DEFAULT_TABLE_SIZE;
         }

      /**
       * Initialization constructor
       * <p>
       * Initializes array to specified table size
       *
       * @param inTableSize Sets table size
       */
      public LL_HashClass( int inTableSize )
         {
            tableArray = new SimpleStudentClass[ inTableSize ];
            tableSize = inTableSize;
         }

      /**
       * Copy constructor
       *
       * @param copied LL_HashClass object to be copied
       */
      public LL_HashClass( LL_HashClass copied )
         {
            int index;
            SimpleStudentClass wkgRefToThis, wkgRefToCopied;

            this.tableArray = new SimpleStudentClass[ copied.tableSize ];
            this.tableSize = copied.tableSize;

            for( index = 0; index < copied.tableSize; index++ )
               {
                  if( copied.tableArray[ index ] != null )
                     {
                        // FIRST make a pointer to head node of the Linked List
                        // in that index
                        wkgRefToCopied = copied.tableArray[ index ];
                        // NEXT use copy constructor of SimpleStudentClass to
                        // prevent aliasing
                        this.tableArray[ index ] = new SimpleStudentClass(
                                                               wkgRefToCopied );
                        // NEXT create a pointer to node 0 in the current index
                        wkgRefToThis = this.tableArray[ index ];
                        // NEXT set pointer of copied to node 1
                        wkgRefToCopied = wkgRefToCopied.nextRef;

                        // CHECK if there are still more nodes in copied
                        while( wkgRefToCopied != null )
                           {
                              // SET the next node to what copied currently is
                              // since it is one node ahead
                              wkgRefToThis.nextRef = new SimpleStudentClass(
                                                               wkgRefToCopied );

                              /*
                               * This way of assigning objects works because
                               * wkgRefToThis does not exist in a vacuum; at
                               * first this object's head node IS wkgRefToThis
                               * which means setting wkgRefToThis.nextRef ALSO
                               * sets headRef.nextRef. This process repeats as
                               * long as the reference to copied is not null.
                               *
                               * TL;DR: wkgRefToThis is the same object as
                               * this.headRef at first which means the list will
                               * not be broken
                               */

                              // INCREMENT reference to this by one
                              wkgRefToThis = wkgRefToThis.nextRef;
                              // INCREMENT reference to copied by one
                              wkgRefToCopied = wkgRefToCopied.nextRef;
                           }
                     }
                  else
                     {
                        this.tableArray[ index ] = null;
                     }
               }
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Helper method that handles both searching and removing items in linked
       * list at specified index
       *
       * @param linkIndex Integer index specifying location in array
       * @param studentID Integer key for searching and/or removing node
       * @param removeFlag Boolean flag that indicates whether to search or
       * remove (use SEARCH, REMOVE constants to call this method)
       * @return SimpleStudentClass data found and/or removed
       */
      private SimpleStudentClass accessLinkedData( int linkIndex, int studentID,
                                                            boolean removeFlag )
         {
            SimpleStudentClass soughtStudent = null, priorToSought = null;

            // Find reference to student
            soughtStudent = tableArray[ linkIndex ];
            while( soughtStudent != null && soughtStudent.studentID !=
                                                                     studentID )
               {
                  priorToSought = soughtStudent;
                  soughtStudent = soughtStudent.nextRef;
               }

            // If REMOVE
            if ( removeFlag )
               {
                  // Take the previous node and set its nextRef to skip
                  // over soughtStudent, thus removing it from the list
                  if( soughtStudent != tableArray[ linkIndex ] )
                     {
                        priorToSought.nextRef = soughtStudent.nextRef;
                     }
                  else
                     {
                        // Special case for removing head reference
                        tableArray[ linkIndex ] = tableArray[
                                                            linkIndex ].nextRef;
                     }
               }
            // else simply return

            return soughtStudent;
         }

      /**
       * Adds item to hash table
       * <p>
       * Uses overloaded addItem with object
       *
       * @param inName Name of student
       * @param inStudentID Student ID
       * @param inGender Gender of student
       * @param inGPA GPA of student
       * @return Boolean success of operation
       */
      public boolean addItem( String inName, int inStudentID,
                                                   char inGender, double inGPA )
         {
            SimpleStudentClass addStudent = new SimpleStudentClass( inName,
                                                 inStudentID, inGender, inGPA );
            int hashedIndex = generateHash( addStudent );

            if( hashedIndex >= 0 && hashedIndex < tableSize )
               {
                  appendToList( hashedIndex, addStudent );
                  return true;
               }

            return false;
         }

      /**
       * Adds item to hash table
       * <p>
       * Overloaded method that accepts SimpleStudentClass object
       *
       * @param newItem SimpleStudentClass object
       * @return Boolean success of operation
       */
      public boolean addItem( SimpleStudentClass newItem )
         {
            int hashedIndex = generateHash( newItem );

            if( hashedIndex >= 0 && hashedIndex < tableSize )
               {
                  appendToList( hashedIndex, newItem );
                  return true;
               }

            return false;
         }

      /**
       * Appends new data to end of list at given list index; handles empty node
       * at that index as needed
       *
       * @param listIndex Specified integer index of array
       * @param newNode SimpleStudentClass node to be appended to array/list
       */
      private void appendToList( int listIndex, SimpleStudentClass newNode )
         {
            SimpleStudentClass currentWkgRef;

            if( tableArray[ listIndex ] != null )
               {
                  currentWkgRef = tableArray[ listIndex ];

                  while( currentWkgRef.nextRef != null )
                     {
                        currentWkgRef = currentWkgRef.nextRef;
                     }

                  // Logically must be at end of Linked List and can safely
                  // append newNode
                  currentWkgRef.nextRef = newNode;
               }
            else   // tableArray at current index contains no head reference
               {
                  tableArray[ listIndex ] = newNode;
               }
         }

      /**
       * Clears hash table by clearing all linked list elements
       */
      public void clearHashTable()
         {
            int nukeIndex;

            for( nukeIndex = 0; nukeIndex < tableSize; nukeIndex++ )
               {
                  tableArray[ nukeIndex ] = null;
               }
         }

      /**
       * Method recursively counts number of nodes in a given linked list
       *
       * @param workingRef SimpleStudentClass reference used for recursion;
       * initially set to head
       * @return Integer number of nodes found
       */
      private int countNodes( SimpleStudentClass workingRef )
         {
            if( workingRef != null )
               {
                  return ( 1 + countNodes( workingRef.nextRef ) );
               }
            else
               {
                  return 0;
               }
         }

      /**
       * Searches for item in hash table using student ID as key
       *
       * @param studentID Used for requesting data
       * @return SimpleStudentClass object removed, or null if not found
       */
      public SimpleStudentClass findItem( int studentID )
         {
            SimpleStudentClass foundStudent;
            SimpleStudentClass tempStudent = new SimpleStudentClass( "temp",
                                                          studentID, 'x', 0.0 );
            int hashIndex = generateHash( tempStudent );
            foundStudent = accessLinkedData( hashIndex, studentID, SEARCH );

            return foundStudent;
         }

      /**
       * Method uses student ID to generate hash value for use as index in hash
       * table
       * <p>
       * Process sums the Unicode values of the student ID numbers converted to
       * characters
       *
       * @param studentData SimpleStudentClass object from which hash value will
       * be generated
       * @return Integer hash value to be used as array index
       */
      public int generateHash( SimpleStudentClass studentData )
         {
            // Note: Since all student IDs are 6 digits long I kind of cheated
            // here and made a specific solution
            int index, digitsInID = 6;
            int studentID = studentData.studentID, unicodeValSum = 0;
            char currentUnicodeDigitVal;

            for( index = 0; index < digitsInID; index++ )
               {
                  // Peel off the current LSD from studentID
                  // Note: +48 is to convert it to the proper char value
                  currentUnicodeDigitVal = (char) ( studentID % 10 + 48 );
                  // Integer divide studentID so next digit is now LSD
                  studentID /= 10;
                  // Add Unicode value of integer to accumulator
                  unicodeValSum += currentUnicodeDigitVal;
               }

            // Hash and return
            return unicodeValSum % tableSize;
         }

      /**
       * Removes item from hash table, using student ID as key
       *
       * @param studentID Used for requesting data
       * @return SimpleStudentClass object removed, or null if not found
       */
      public SimpleStudentClass removeItem( int studentID )
         {
            SimpleStudentClass removedStudent;
            SimpleStudentClass tempStudent = new SimpleStudentClass( "temp",
                                                          studentID, 'x', 0.0 );
            int hashIndex = generateHash( tempStudent );
            removedStudent = accessLinkedData( hashIndex, studentID, REMOVE );

            return removedStudent;
         }

      /**
       * Traverses through all array bins, finds lengths of each linked list,
       * then displays as table
       * <p>
       * Shows table of list lengths, then shows table size, then shows the
       * number of empty bins and the longest linked list of the set; adapts to
       * any size array
       */
      public void showHashTableStatus()
         {
            // Going to be frank this is kind of ugly and I wish I could write
            // it in 1 loop instead of 3
            int index, numberOfNodes, emptyBinCount = 0, largestBin = 0;

            System.out.print( "\nArray node report:\n" );
            System.out.print( "           " );

            for( index = 0; index < tableSize; index++ )
               {
                  numberOfNodes = countNodes( tableArray[ index ] );
                  if( numberOfNodes == 0 )
                     {
                        emptyBinCount++;
                     }
                  if( numberOfNodes > largestBin )
                     {
                        largestBin = numberOfNodes;
                     }

                  System.out.format( "%4d", numberOfNodes );

                  if( index != tableSize - 1 )
                     {
                        System.out.print( "   " );
                     }
               }
            System.out.print( '\n' );

            System.out.print( "           " );
            for( index = 0; index < tableSize; index++ )
               {
                  System.out.print( "-----" );

                  if( index != tableSize - 1 )
                     {
                        System.out.print( "  " );
                     }
               }
            System.out.print( '\n' );

            System.out.print( " Index:    " );
            for( index = 0; index < tableSize; index++ )
               {
                  System.out.format( "%4d", index );

                  if( index != tableSize - 1 )
                     {
                        System.out.print( "   " );
                     }
               }
            System.out.print( "\n\n" );

            System.out.print( "With a table size of " + tableSize + "\n" );
            System.out.print( "The number of empty bins was " + emptyBinCount );
            System.out.print( ", and the longest linked list node list was " );
            System.out.print( largestBin + " nodes.\n\n");
         }
   }
