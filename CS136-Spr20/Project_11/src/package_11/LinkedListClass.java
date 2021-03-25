package package_11;

/**
 * Author: David Hermann
 * <p>
 * Date: 15 April 2020
 * <p>
 * Program: PA11
 * <p>
 * Class: CS136 - 001
 * <p>
 * "A LinkedList is only as strong as its weakest NullPointerException."
 */
public class LinkedListClass
   {
      // Variables
      /**
       * Reference to head of linked list
       */
      private StudentClass headRef;

      // Constructors
      /**
       * Default constructor
       */
      public LinkedListClass()
         {
            this.headRef = null;
         }

      /**
       * Copy constructor copies the other linked list nodes by creating new nodes
       * within this list; must not copy nodes from other list
       *
       * @param copied LinkedListClass object to copy
       */
      public LinkedListClass( LinkedListClass copied )
         {
            StudentClass thisNode, copyNode;

            if( copied.headRef != null )
               {
                  this.headRef = new StudentClass( copied.headRef );
                  thisNode = this.headRef;
                  copyNode = copied.headRef.nextRef;

                  while( copyNode != null )
                     {
                        copyNode = copyNode.nextRef;
                        thisNode = thisNode.nextRef;
                        thisNode.nextRef = new StudentClass( copyNode );
                     }
               }
         }

      // Methods
      /**
       * Appends data to end of linked list
       * <p>
       * Note: Calls appendDataHelper, assigns to head reference
       *
       * @param data StudentClass object to be appended to linked list
       */
      public void appendData( StudentClass data )
         {
            StudentClass currentNode;

            if( this.headRef != null )
               {
                  currentNode = this.headRef;
                  this.headRef = appendDataHelper( currentNode, data );

               }
            else
               {
                  insertAtHead( data );
               }
         }

      /**
       * Recursively cycles to end of list, then appends data node
       *
       * @param wkgRef  StudentClass object used as the current reference during
       *                recursion
       * @param newData StudentClass object to be appended to the list
       * @return StudentClass reference to linked list at the current point
       */
      private StudentClass appendDataHelper( StudentClass wkgRef, StudentClass newData )
         {
            if( wkgRef != null )
               {
                  wkgRef.nextRef = appendDataHelper( wkgRef.nextRef, newData );

                  return wkgRef;
               }

            return new StudentClass( newData );
         }

      /**
       * Clears list by setting head reference to null
       */
      public void clearList()
         {
            this.headRef = null;
         }

      /**
       * Displays linked list by calling displayListHelper with head reference
       */
      public void displayList()
         {
            StudentClass holdsDisplayData = this.headRef;

            if( this.headRef != null )
               {
                  System.out.println( "List Display:" );

                  while( holdsDisplayData != null )
                     {
                        displayListHelper( holdsDisplayData );
                        holdsDisplayData = holdsDisplayData.nextRef;
                     }

               }
            else
               {
                  System.out.println( "List Display - List Empty" );
               }
         }

      /**
       * Recursive method that displays the linked list
       *
       * @param wkgRef StudentClass object holding the current linked list node
       *               reference
       */
      private void displayListHelper( StudentClass wkgRef )
         {
            String displayStudent = wkgRef.toString();
            System.out.println( displayStudent );
         }

      /**
       * Gets the data from a node using the student name as the key; StudentClass
       * object is returned with related data
       *
       * @param requestedData StudentClass object holding the name of the student to
       *                      be retrieved
       * @return StudentClass object holding all of the student's data, or null if the
       *         student was not found
       */
      public StudentClass getNodeData( StudentClass requestedData )
         {
            StudentClass foundStudent;

            if( this.headRef != null )
               {
                  foundStudent = this.headRef;

                  while( foundStudent.compareTo( requestedData ) != 0 )
                     {
                        foundStudent = foundStudent.nextRef;
                     }
               }
            else
               {
                  foundStudent = null;
               }

            return foundStudent;
         }

      /**
       * Inserts new data node at head of linked list
       *
       * @param newData StudentClass object to be inserted at beginning of linked list
       */
      public void insertAtHead( StudentClass newData )
         {
            if( this.headRef != null )
               {
                  newData.nextRef = this.headRef;
                  this.headRef = newData;
               }
            else
               {
                  this.headRef = newData;
               }
         }

      /**
       * Inserts new data after specified node; specified node must exist in list for
       * insertion to occur
       *
       * @param specifiedNode StudentClass object after which the new data must be
       *                      inserted
       * @param newData       StudentClass object to insert after specified node
       * @return Boolean result of operation; if specified node is found and new data
       *         is inserted after it, returns true; otherwise, returns false
       */
      public boolean insertNodeAfter( StudentClass specifiedNode, StudentClass newData )
         {
            boolean successfulInsert = false;
            StudentClass iterator = this.headRef;

            if( this.headRef != null )
               {
                  // Iterate through list
                  while( iterator.compareTo( specifiedNode ) != 0 )
                     {
                        iterator = iterator.nextRef;
                     }

                  // If this is not true, then iterator reached final node.nextRef
                  if( iterator != null )
                     {
                        newData.nextRef = iterator.nextRef;
                        iterator.nextRef = newData;
                        successfulInsert = true;
                     }
               }

            return successfulInsert;
         }

      /**
       * Inserts new data prior to a specified node; specified node must exist in list
       * for insertion to occur
       *
       * @param specifiedNode StudentClass object prior to which the new data must be
       *                      inserted
       * @param newData       StudentClass object to insert prior to specified node
       * @return Boolean result of operation; if specified node is found and new data
       *         is inserted prior to it, returns true; otherwise, returns false
       */
      public boolean insertNodePrior( StudentClass specifiedNode, StudentClass newData )
         {
            boolean successfulInsert = false;
            StudentClass iterator = this.headRef;
            StudentClass priorToIterator = null;

            if( this.headRef != null )
               {
                  // Iterate through list
                  while( iterator.compareTo( specifiedNode ) != 0 )
                     {
                        priorToIterator = iterator;
                        iterator = iterator.nextRef;
                     }

                  // If this is not true, then iterator reached final node.nextRef
                  // and specifiedNode was not found
                  if( iterator != null || priorToIterator.compareTo( specifiedNode ) != 0 )
                     {
                        newData.nextRef = iterator;
                        priorToIterator.nextRef = newData;
                        successfulInsert = true;
                     }
               }

            return successfulInsert;
         }

      /**
       * Searches for StudentClass key/name in linked list, removes and returns node
       * if found
       *
       * @param toBeRemoved StudentClass object to be removed
       * @return StudentClass object that was removed if successful, null otherwise
       */
      public StudentClass removeItem( StudentClass toBeRemoved )
         {
            StudentClass removed = null;
            StudentClass iterator = this.headRef;

            if( this.headRef != null )
               {
                  // Iterate through list
                  while( iterator.compareTo( toBeRemoved ) != 0 )
                     {
                        iterator = iterator.nextRef;
                     }

                  // If this is not true, then iterator reached final node.nextRef
                  if( iterator != null )
                     {
                        removed = iterator.nextRef;
                        iterator.nextRef = iterator.nextRef.nextRef;
                     }
               }

            return removed;
         }
   }
