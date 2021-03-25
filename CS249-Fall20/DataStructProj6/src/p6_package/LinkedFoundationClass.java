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
 * Class wrapper for a Java linked list, with management operations
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class LinkedFoundationClass
   {
      /**
       * Internal node class for managing data in the linked list
       */
      private class NodeClass
         {
            int nodeData;
            NodeClass nextRef;

            /**
             * Initialization constructor for NodeClass
             * @param newData Integer value to be placed in node
             */
            private NodeClass( int newData )
               {
                  nodeData = newData;
                  nextRef = null;
               }

            /**
             * Copy constructor for NodeClass
             * @param copied NodeClass object to be copied into this node
             */
            private NodeClass( NodeClass copied )
               {
                  this.nodeData = copied.nodeData;
                  this.nextRef = null;
               }
         }

      // Class constants ///////////////////////////////////////////////////////

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

      // Class variable(s) /////////////////////////////////////////////////////
      /**
       * Member - linked list head reference
       */
      private NodeClass headRef;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Default constructor, initializes linked list
       */
      public LinkedFoundationClass()
         {
            // headRef is initialized to null since no values are given to
            // populate a NodeClass with data
            headRef = null;
         }

      /**
       * Copy constructor, initializes linked list, then copies all nodes to
       * local (this) linked list
       * @param copied LinkedFoundationCLass object to be copied
       */
      public LinkedFoundationClass( LinkedFoundationClass copied )
         {
            NodeClass wkgRefToThis, wkgRefToCopied;

            if( copied.headRef != null )
               {
                  // FIRST make a pointer to copied's head node
                  wkgRefToCopied = copied.headRef;
                  // NEXT use copy constructor of NodeClass to prevent aliasing
                  this.headRef = new NodeClass( wkgRefToCopied );
                  // NEXT create a pointer to node 0
                  wkgRefToThis = this.headRef;
                  // NEXT set pointer of copied to node 1
                  wkgRefToCopied = wkgRefToCopied.nextRef;

                  // CHECK if there are still more nodes in copied
                  while( wkgRefToCopied != null )
                     {
                        // SET the next node to what copied currently is since
                        // it is one node ahead
                        wkgRefToThis.nextRef = new NodeClass( wkgRefToCopied );

                        /*
                         * This way of assigning objects works because
                         * wkgRefToThis does not exist in a vacuum; at first
                         * this object's head node IS wkgRefToThis which means
                         * setting wkgRefToThis.nextRef ALSO sets
                         * headRef.nextRef. This process repeats as long as
                         * the reference to copied is not null.
                         *
                         * TL;DR: wkgRefToThis is the same object as
                         * this.headRef at first which means the list will not
                         * be broken
                         */

                        // INCREMENT reference to this by one
                        wkgRefToThis = wkgRefToThis.nextRef;
                        // INCREMENT reference to copied by one
                        wkgRefToCopied = wkgRefToCopied.nextRef;
                     }
               }
            else
               {
                  this.headRef = null;
               }
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Utility method used by getAtIndex and removeAtIndex to access and
       * possibly remove element depending on control code
       * <p>
       * Note: Data is managed with virtual index found by getRefAtIndex
       * <p>
       * Note: Uses maximum one loop
       * @param controlCode integer value with either RETRIEVE or REMOVE to
       *    control operations
       * @param index integer virtual index of element to be retrieved or
       *    removed
       * @return integer value at element or FAILED_ACCESS if attempt to access
       *    data out of bounds
       */
      private int accessAtIndex( int controlCode, int index )
         {
            int data = FAILED_ACCESS;
            NodeClass soughtNode, priorToSought;

            // Switches > chain if else
            switch( controlCode )
               {
                  case RETRIEVE:
                     // Find pointer to node
                     soughtNode = getRefAtIndex( headRef, index );
                     // Get that data
                     data = soughtNode.nodeData;
                     break;
                  case REMOVE:
                     // Same process as Retrieve
                     soughtNode = getRefAtIndex( headRef, index );
                     data = soughtNode.nodeData;
                     // Now find the previous node and set its nextRef to skip
                     // over soughtNode, thus removing it from the list
                     if( index != 0 )
                        {
                           priorToSought = getRefAtIndex( headRef, index - 1 );
                           priorToSought.nextRef = soughtNode.nextRef;
                        }
                     else
                        {
                           // Special case for removing head reference
                           headRef = headRef.nextRef;
                        }
                     break;
               }

            return data;
         }

      /**
       * Clears linked list of all valid values by setting linked list head
       * reference to null
       */
      public void clear()
         {
            // Some men just want to watch the compiler NullPointerException
            headRef = null;
         }

      /**
       * Accesses item in linked list at specified virtual index if it is within
       * linked list limits
       * <p>
       * Note: Linked list value specified by virtual index is returned to
       * calling program
       * <p>
       * Note: Calls accessAtIndex with RETRIEVE to conduct action
       * @param accessIndex Integer virtual index of requested element value
       * @return Integer accessed value if successful, FAILED_ACCESS if not
       */
      public int getAtIndex( int accessIndex )
         {
            return accessAtIndex( RETRIEVE, accessIndex );
         }

      /**
       * Gets size of linked list
       * <p>
       * Note: Uses getCurrentSizeHelper
       * <p>
       * Note: Handles empty list prior to calling helper if needed
       * @return Integer virtual index
       */
      public int getCurrentSize()
         {
            int size = 0;

            if( !( isEmpty() ) )
               {
                  size = getCurrentSizeHelper( headRef );
               }

            return size;
         }

      /**
       * Uses recursion to find linked list size
       * @param wkgRef NodeClass current reference in recursion, initially
       * called with head reference
       * @return Integer linked list size
       */
      private int getCurrentSizeHelper( NodeClass wkgRef )
         {
            if( wkgRef != null )
               {
                  return ( 1 + getCurrentSizeHelper( wkgRef.nextRef ) );
               }
            else
               {
                  return 0;
               }
         }

      /**
       * Private recursive method that finds a node represented by a virtual
       * index
       * <p>
       * Note: if requested index is less than zero or outside linked list
       * boundary, returns null
       * @param wkgRef NodeClass reference that initially called with the head
       *    reference, and is used for recursive operations
       * @param requestedIndex Integer value representing virtual index
       *    requested by the user
       * @return NodeClass reference to the element at the virtual index
       */
      private NodeClass getRefAtIndex( NodeClass wkgRef, int requestedIndex )
         {
            NodeClass pointerToNode = null;

            // Check if index within bounds
            if( requestedIndex >= 0 && requestedIndex < getCurrentSize() )
               {
                  // Check if this is the node requested by decrementing each
                  // call
                  if( requestedIndex != 0 )
                     {
                        pointerToNode = getRefAtIndex( wkgRef.nextRef,
                                                           requestedIndex - 1 );
                     }
                  else
                     {
                        // This is it chief
                        pointerToNode = wkgRef;
                     }
               }

            return pointerToNode;
         }

      /**
       * Tests for empty linked list
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
         {
            return ( headRef == null );
         }

      /**
       * Removes item from linked list at specified virtual index if index
       * within linked list size bounds
       * <p>
       * Note: Linked list node specified by virtual index is removed from list
       * <p>
       * Note: Calls accessAtIndex with REMOVE to conduct action
       * @param removeIndex Integer index of element value to be removed
       * @return Removed integer value if successful, FAILED_ACCESS if not
       */
      public int removeAtIndex( int removeIndex )
         {
            return accessAtIndex( REMOVE, removeIndex );
         }

      /**
       * Displays formatted list with virtual indices
       * @param showIndex Boolean value turns on display of index under value if
       *    set to true, otherwise only shows pipe delimited values
       */
      public void runDiagnosticDisplay( boolean showIndex )
         {
            int displayIndex, currentData, size = getCurrentSize();

            System.out.print( " List: |" );
            for( displayIndex = 0; displayIndex < size; displayIndex++ )
               {
                  currentData = getAtIndex( displayIndex );
                  System.out.format( "%4d |", currentData );
               }
            System.out.print( '\n' );

            if( showIndex )
               {
                  System.out.print( "Index: |" );
                  for( displayIndex = 0; displayIndex < size; displayIndex++ )
                     {
                        System.out.format( "%3d   ", displayIndex );
                     }
                  System.out.print( '\n' );
               }
         }

      /**
       * Sets item in linked list at specified virtual index
       * <p>
       * Note: If constant REPLACE is used, new value overwrites value at
       * current virtual index
       * <p>
       * Note: If constant INSERT_BEFORE is used, new value is inserted prior to
       * the value at the current virtual index
       * <p>
       * Note: If constant INSERT_AFTER is used, new value is inserted after the
       * value at the current virtual index
       * <p>
       * Note: Method must check for correct virtual array boundaries; if index
       * requested is below zero or above list size - 1, method must take no
       * action and return false
       * <p>
       * Note: Method must check for correct replace flag; if it is not one of
       * the three specified flags, it must take no action and return false
       * @param setIndex Integer index of element at which value is to be
       *    replaced, or value is to be inserted before or after
       * @param newValue Integer value to be placed in linked list
       * @param replaceFlag Integer flag to indicate insertion or replacement in
       *    the linked list
       * @return Boolean success if inserted, or failure if incorrect index or
       *    replace flag was used
       */
      public boolean setAtIndex( int setIndex, int newValue, int replaceFlag )
         {
            NodeClass wkgRef;
            NodeClass insertValue = new NodeClass( newValue );
            int size = getCurrentSize();

            // If list is empty, SPECIAL case where the list is started
            if( headRef == null && setIndex == 0 && replaceFlag != REPLACE )
               {
                  headRef = insertValue;
                  return true;
               }

            /*
             * Check if setIndex within "bounds" of linked list
             * Also catches when headRef == null && replaceFlag == REPLACE
             * which must be rejected since there is nothing to replace in an
             * empty list
             */
            if( setIndex >= size || setIndex < 0 )
               {
                  return false;
               }

            // Check if replaceFlag is valid
            if( replaceFlag != REPLACE && replaceFlag != INSERT_BEFORE &&
                                                   replaceFlag != INSERT_AFTER )
               {
                  return false;
               }

            // Special case, place node as new head of the list
            if( replaceFlag == INSERT_BEFORE && setIndex == 0 )
               {
                  insertValue.nextRef = headRef;
                  headRef = insertValue;
                  return true;
               }

            // Find node before setIndex as this is not a doubly linked list
            if( replaceFlag == INSERT_BEFORE )
               {
                  setIndex--;
               }

            // Travel to correct node and return a POINTER to it
            wkgRef = getRefAtIndex( headRef, setIndex );

            if( wkgRef != null )
               {
                  switch( replaceFlag )
                     {
                        case INSERT_BEFORE:
                           insertValue.nextRef = wkgRef.nextRef;
                           wkgRef.nextRef = insertValue;
                           break;
                        case REPLACE:
                           wkgRef.nodeData = newValue;
                           break;
                        case INSERT_AFTER:
                           // If at end of list, insertValue.nextRef will be set
                           // to null but it will be appended to end of list
                           insertValue.nextRef = wkgRef.nextRef;
                           wkgRef.nextRef = insertValue;
                           break;
                     }
               }
            else
               {
                  return false;
               }

            // If the method make it to here, then newValue was handled
            return true;
         }
   }
