package p8_package;

/**
 * Date: 14 October 2020
 * <p>
 * Program: PA07
 * <p>
 * Class: CS249 - 002
 * <p>
 * "I see a red node and I want it painted black"
 * <p>
 * Binary Search Tree (BST) class with self-balancing attributes specifically
 * using the Red/Black Tree (RBT) strategy and the RedBlackNode class
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class RedBlackTreeClass
   {
      // Class constants ///////////////////////////////////////////////////////
      /**
       * Constant used to represent dash
       *
       */
      private static final char DASH = '-';

      /**
       * Traverse code - inorder
       */
      public static final int INORDER_TRAVERSE = 102;

      /**
       * Provides user choice to display node color
       * in BST Tree display
       */
      public static final int NODE_COLOR = 191;

      /**
       * Provides user choice to display node data
       * in BST Tree display
       */
      public static final int NODE_DATA = 192;

      /**
       * Null character returned if data not available
       */
      public final char NULL_CHAR = '\n';

      /**
       * Traverse code - postorder
       */
      public static final int POSTORDER_TRAVERSE = 103;

      /**
       * Traverse code - preorder
       */
      public static final int PREORDER_TRAVERSE = 101;

      /**
       * Constant comma break for printing traversals
       */
      public final String SEMICOLON_BREAK = "; ";

      /**
       * Constant comma break for printing traversals
       */
      public final String SLASH_BREAK = "/";

      /**
       * Constant used to represent space
       *
       */
      private static final char SPACE = ' ';

      // Class variables ///////////////////////////////////////////////////////
      /**
       * Root of Red Black Tree
       */
      private RedBlackNode RBTree_Root;

      /**
       * Variable provides user-selected choice between output
       * of data or color, initialized to NODE_DATA
       */
      private int treeCharDisplayCode;


      /**
       * Class global variable used to display tree structure
       */
      private boolean rowStartFlag;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Default class constructor, no initialization actions
       */
      public RedBlackTreeClass()
         {
            RBTree_Root = null;
            treeCharDisplayCode = NODE_DATA;
         }

      /**
       * Class copy constructor
       * <p>
       * Note: Uses copyConstructorHelper
       *
       * @param copied reference to RedBlackTreeClass object
       */
      public RedBlackTreeClass( RedBlackTreeClass copied )
         {
            RBTree_Root = copyConstructorHelper( copied.RBTree_Root );
            treeCharDisplayCode = NODE_DATA;
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Implements tree duplication effort with recursive method;
       * copies data into newly created nodes and creates all new
       * references to child nodes
       * <p>
       * Note: Uses pre order traversal strategy
       *
       * @param workingCopiedRef RedBlackNode reference that is updated
       * to lower level children with each recursive call
       *
       * @return RedBlackNode reference that links to next higher level node;
       * last return is to root node of THIS object
       */
      private RedBlackNode copyConstructorHelper(
                                                 RedBlackNode workingCopiedRef )
         {
            // Set (RedBlackNode) localNode to null
            RedBlackNode localNode = null;

            // Check if current reference is not null
            if( workingCopiedRef != null )
               {
                  // Set localNode to a new node using the copied ref
                  localNode = new RedBlackNode( workingCopiedRef );
                  localNode.parentRef = new RedBlackNode (
                                                   workingCopiedRef.parentRef );

                  // Set the localNode's leftChild to a recursive call
                  // with the copied ref's left child
                  localNode.leftChildRef = copyConstructorHelper(
                                                workingCopiedRef.leftChildRef );

                  // If not null copy parent
                  if( localNode.leftChildRef != null )
                     {
                        localNode.leftChildRef.parentRef = localNode;
                     }

                  // Set the localNode's rightChild to a recursive call
                  // with the copied ref's right child
                  localNode.rightChildRef = copyConstructorHelper(
                                               workingCopiedRef.rightChildRef );

                  // If not null copy parent
                  if( localNode.rightChildRef != null )
                     {
                        localNode.rightChildRef.parentRef = localNode;
                     }
               }

            // return localNode
            return localNode;
         }

// Oh man this is a mess

      /**
       * Provides inOrder traversal action
       *
       * @param localRoot RedBlackNode tree root reference
       * at the current recursion level
       */
      private void displayInOrder( RedBlackNode localRoot )
         {
            // LEFT PARENT RIGHT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate left child
                  displayInOrder( localRoot.leftChildRef );

                  // Accumulate parent


                  // Accumulate right child
                  displayInOrder( localRoot.rightChildRef );
               }
         }

      /**
       * Provides postOrder traversal action
       *
       * @param localRoot RedBlackNode tree root reference
       * at the current recursion level
       */
      private void displayPostOrder( RedBlackNode localRoot )
         {
            // LEFT RIGHT PARENT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate left child
                  displayPostOrder( localRoot.leftChildRef );

                  // Accumulate right child
                  displayPostOrder( localRoot.rightChildRef );

                  // Accumulate parent

               }
         }

      /**
       * Provides preOrder traversal action
       *
       * @param localRoot RedBlackNode tree root reference
       * at the current recursion level
       */
      private void displayPreOrder( RedBlackNode localRoot )
         {
            // PARENT LEFT RIGHT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate parent


                  // Accumulate left child
                  displayPreOrder( localRoot.leftChildRef );

                  // Accumulate right child
                  displayPreOrder( localRoot.rightChildRef );
               }
         }

      /**
       * Provides user with three ways to display RBTree data
       *
       * @param traverseCode integer code for selecting RBTree traversal method,
       * accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
       */
      public void displayTree( int traverseCode )
         {
            switch( traverseCode )
               {
                  case INORDER_TRAVERSE:
                     displayInOrder( RBTree_Root );
                     break;
                  case PREORDER_TRAVERSE:
                     displayPreOrder( RBTree_Root );
                     break;
                  case POSTORDER_TRAVERSE:
                     displayPostOrder( RBTree_Root );
                     break;
               }
         }

      /**
       * Finds height of tree using helper method
       *
       * @return height of tree
       */
      public int findTreeHeight()
         {
            return findTreeHeightHelper( RBTree_Root );
         }

      /**
       * Recursive tree height helper
       * <p>
       * Note: Empty tree height is -1; single root node tree height is 0
       * <p>
       * Note: It is acceptable to place methods in parameters in this method
       *
       * @param localRoot RedBlackNode reference at current recursive level
       *
       * @return integer height at a given level
       */
      private int findTreeHeightHelper( RedBlackNode localRoot )
         {
            if( localRoot != null )
               {
                  return getMax( findTreeHeightHelper( localRoot.leftChildRef ),
                          findTreeHeightHelper( localRoot.rightChildRef ) ) + 1;
               }

            return -1;
         }

      /** Insert method for RBTree
       * <p>
       * Note: uses insert helper method which returns
       * the inserted node reference to this method
       * <p>
       * Note: After value has been inserted as a Red node,
       * this method calls the resolveRBT_Issues method
       * to rebalance and/or recolor the tree
       *
       * @param inData char data to be added to RBTree
       *
       * @return Boolean result of insertion action
       */
      public boolean insert( char inData )
         {
            System.out.print( "Inserting data and resolving" );

            RedBlackNode inserted = insertHelper( RBTree_Root, inData );
            if( inserted != null )
               {
                  resolveRBT_Issues( inserted );
               }

            return inserted != null;
         }

      /**
       * Insert helper method for RedBlackTree insert action
       * <p>
       * Note: Does not allow duplicate values to be inserted
       *
       * @param localRoot RedBlackNode tree root reference
       * at the current recursion level
       *
       * @param inData char item to be added to RBTree
       *
       * @return RedBlackNode reference of inserted node
       */
      private RedBlackNode insertHelper( RedBlackNode localRoot, char inData )
         {
            // Check for not null
            if( localRoot != null )
               {
                  // Check to see if localRoot's current data
                  // greater than the incoming data
                  if( localRoot.nodeData > inData )
                     {
                        // Check for left child null
                        if( localRoot.leftChildRef == null )
                           {
                              // Set localRoot (parameter)'s left child
                              // to a new RedBlackNode object with the data
                              localRoot.leftChildRef = new
                                                         RedBlackNode( inData );
                              localRoot.leftChildRef.parentRef = localRoot;

                              return localRoot.leftChildRef;
                           }
                        else   // leftChildRef != null
                           {
                              // Call insertHelper to the left child
                              return insertHelper( localRoot.leftChildRef,
                                                                       inData );
                           }
                     }
                  else
                     {
                        // Assume no duplicated data and localRoot's current
                        // data is less than the incoming data

                        // Check for right child null
                        if( localRoot.rightChildRef == null )
                           {
                              // Set localRoot (parameter)'s right child
                              // to a new RedBlackNode object with the data
                              localRoot.rightChildRef = new
                                                         RedBlackNode( inData );
                              localRoot.rightChildRef.parentRef = localRoot;

                              return localRoot.rightChildRef;
                           }
                        else   // rightChildRef != null
                           {
                              // Call insertHelper to the right child
                              return insertHelper( localRoot.rightChildRef,
                                                                       inData );
                           }
                     }
               }
            else
               {
                  // Assume localRoot is null (only happens for empty tree)
                  // set tree root to a new object with the data
                  RBTree_Root = new RedBlackNode( inData );
               }

            // Tree root only returned if tree was empty when insert was called
            return RBTree_Root;
         }

      /**
       * Recursively rebalances and recolors tree as needed
       * <p>
       * Note: Indicates actions being taken by displaying them
       *
       * @param localRef RedBlackNode reference for the local tree being managed
       */
      private void resolveRBT_Issues( RedBlackNode localRef )
         {
            // check for parent (first) and then grandparent are not null
               // set parent, grandparent references
               // find the uncle, check for each side of the grandparent
               // check red parent
                  // check uncle is not null and check red uncle
                     // do part a stuff GeeksForGeeks website
                  // otherwise, assume uncle is black
                     // check for the left side grandparent
                        // check for left left case (check left side of the parent)
                           // check for grandparent is root
                              // rotate right, assign result to root
                           // otherwise, grandparent is not root
                              // assign new greatgrandparent to grandparent's parent
                              // rotate right, assign result to greatgrandparent
                              // greatgrandparent left child = rotateRight(child, parent)
                        // otherwise, assume left right
                           // rotate left, assign to grandparent
                           // recurse with local's left child
                     // otherwise, assume right side grandparent

            RedBlackNode parentRef, grandparentRef;

            if( localRef.parentRef != null && localRef.parentRef.nodeColor
                        != RedBlackNode.COLOR_BLACK && localRef != RBTree_Root )
               {
                  parentRef = localRef.parentRef;
                  grandparentRef = localRef.parentRef.parentRef;

                  if( parentRef != null && grandparentRef != null )
                     {
                        if( parentRef == grandparentRef.leftChildRef )
                           {
                              if( grandparentRef.rightChildRef.nodeColor ==
                                                        RedBlackNode.COLOR_RED )
                                 {
                                    parentRef.nodeColor =
                                                       RedBlackNode.COLOR_BLACK;
                                    grandparentRef.rightChildRef.nodeColor =
                                                       RedBlackNode.COLOR_BLACK;
                                    grandparentRef.nodeColor =
                                                         RedBlackNode.COLOR_RED;
                                    resolveRBT_Issues( grandparentRef );
                                 }

                              if( grandparentRef.rightChildRef.nodeColor ==
                                                      RedBlackNode.COLOR_BLACK )
                                 {
                                    // Left left case
                                    if( parentRef ==
                                                   grandparentRef.leftChildRef )
                                       {
                                          if( localRef ==
                                                        parentRef.leftChildRef )
                                             {
                                                localRef = rotateLeft(
                                                          parentRef, localRef );
                                             }
                                       }
                                    // Left right case
                                    if( parentRef ==
                                             grandparentRef.leftChildRef )
                                       {
                                          if( localRef ==
                                                  parentRef.rightChildRef )
                                             {
                                                localRef = rotateRight(
                                                          parentRef, localRef );
                                             }
                                       }

                                    // Right right case
                                    if( parentRef ==
                                             grandparentRef.rightChildRef )
                                       {
                                          if( localRef ==
                                                  parentRef.rightChildRef )
                                             {
                                                localRef = rotateLeft(
                                                          parentRef, localRef );
                                             }
                                       }

                                    // Right left case
                                    if( parentRef ==
                                             grandparentRef.rightChildRef )
                                       {
                                          if( localRef ==
                                                  parentRef.leftChildRef )
                                             {
                                                localRef = rotateRight(
                                                          parentRef, localRef );
                                             }
                                       }
                                 }
                           }
                     }
               }

            if( localRef == RBTree_Root )
               {
                  localRef.nodeColor = RedBlackNode.COLOR_BLACK;
               }
         }

      /**
       * Rotates local tree right or CW
       *
       * @param newTop reference of item to be moved to top
       *
       * @param oldTop reference of item to be moved to child
       */
      private RedBlackNode rotateLeft( RedBlackNode newTop,
                                                           RedBlackNode oldTop )
          {
             return null;
          }

      /**
       * Rotates local tree left or CCW
       *
       * @param localRef reference of current item
       *
       * @param parRef reference of current item parent
       *
       * @param grndParRef reference of current item grandparent
       */
      private RedBlackNode rotateRight( RedBlackNode newTop,
                                                           RedBlackNode oldTop )
         {
            return null;
         }

      /**
       * Searches for data in RBTree given char with necessary key
       *
       * @param searchData char item containing key
       *
       * @return char found data
       */
      public char search( char searchData )
         {
            return searchHelper( RBTree_Root, searchData );
         }

      /**
       * Helper method for RBTree search action
       *
       * @param localRoot RedBlackNode tree root reference
       * at the current recursion level
       *
       * @param searchData char item containing key
       *
       * @return Character found from search, or NULL_CHAR if not found
       */
      private char searchHelper( RedBlackNode localRoot, char searchData )
         {
            int compareChars;
            RedBlackNode foundNode = null;

            if( localRoot != null )
               {
                  compareChars = localRoot.nodeData - searchData;

                  if( compareChars > 0 )
                     {
                        return searchHelper( localRoot.leftChildRef,
                                                                   searchData );
                     }
                  else if( compareChars < 0 )
                     {
                        return searchHelper( localRoot.rightChildRef,
                                                                   searchData );
                     }

                  foundNode = new RedBlackNode( localRoot );

                  return foundNode.nodeData;
               }

            return NULL_CHAR;
         }


////////////////////////////////////////////////////////////////////////////////
// No coding below this point
////////////////////////////////////////////////////////////////////////////////
      /**
       * Displays text-graphical representation of one level/line
       * of the RBT tree
       * <p>
       * Note: choice of color or letter data is made with
       * setTreeDisplayCharacter
       *
       * @param workingNode node reference at current recursive level
       *
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       *
       * @param displayLevel level of tree at which the current line
       * of display is to be presented
       *
       * @param workingLevel current level during recursive actions
       */
      private void displayAtTreeLevel( RedBlackNode workingNode, int nodeHeight,
                                            int displayLevel, int workingLevel )
         {
             char charOut = workingNode.nodeData;

             if( treeCharDisplayCode == NODE_COLOR )
                {
                   charOut = (char)workingNode.nodeColor;
                }

             if( workingLevel == displayLevel )
                {
                   displayValue( charOut, nodeHeight, workingLevel );

                   return;
                }

             if( workingNode.leftChildRef != null )
                {
                   displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                               displayLevel, workingLevel + 1 );
                }
             else
                {
                   displayEmptyNodeSpaces( nodeHeight,
                                               displayLevel, workingLevel + 1 );
                }

             if( workingNode.rightChildRef != null )
                {
                   displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                             displayLevel, workingLevel + 1 );
                }
             else
                {
                   displayEmptyNodeSpaces( nodeHeight,
                                               displayLevel, workingLevel + 1 );
                }
         }

      /**
       * Local recursive method to display a specified number
       * of a specified character
       *
       * @param numChars number of characters to display
       *
       * @param outChar character to display
       */
      private void displayChars( int numChars, char outChar )
         {
             if( numChars > 0 )
                {
                    System.out.print( outChar );

                    displayChars( numChars - 1, outChar );
                }
         }

      /**
       * Method that displays null or blank nodes for a tree at null locations
       * <p>
       * Note: used by displayAtTreeLevel
       *
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       *
       * @param displayLevel level of the tree at which
       * the display will be applied
       *
       * @param workingLevel level of tree just below
       * non-null node at which method is currently working
       */
      private void displayEmptyNodeSpaces( int nodeHeight,
                                            int displayLevel, int workingLevel )
         {
             int nodesToDisplay = toPower( 2, displayLevel - workingLevel );
             char charOut = SPACE;

             if( displayLevel == workingLevel )
                {
                   charOut = DASH;

                   if( treeCharDisplayCode == NODE_COLOR )
                      {
                         charOut = 'B';
                      }
                }

             while( nodesToDisplay > 0 )
                {
                   displayValue( charOut, nodeHeight, displayLevel );

                   nodesToDisplay--;
                }
         }

      /**
       * Displays text-graphical representation of RBT tree
       */
      public void displayTreeStructure()
         {
             int displayLevel, nodeHeight = findTreeHeight() + 2;
             int workingLevel = 1;

             if( RBTree_Root != null )
                {
                   for( displayLevel = 1;
                                    displayLevel <= nodeHeight; displayLevel++ )
                    {
                       rowStartFlag = true;

                       displayAtTreeLevel( RBTree_Root, nodeHeight,
                                                  displayLevel, workingLevel );

                       System.out.println();
                    }
                }

             else
                {
                   System.out.println( "\nEmpty Tree - No Display");
                }
         }

      /**
       * Method used to display a character or color letter
       * along with calculated leading spaces
       * <p>
       * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
       *
       * @param data character value to display, either letter or color data
       *
       * @param nodeHeight integer height of tree plus two
       * for current height of nodes, including lowermost null children
       *
       * @param workingLevel integer current level during recursive actions
       */
      private void displayValue( char data, int nodeHeight, int workingLevel )
         {
             int leadingSpaces;

             if( rowStartFlag )
                {
                   leadingSpaces = toPower( 2, nodeHeight - workingLevel );

                   rowStartFlag = false;
                }

             else
                {
                   leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 )
                                                                            - 1;
                }

             displayChars( leadingSpaces, SPACE );

             System.out.print( data );
         }

      /**
       * Finds maximum of two given numbers
       *
       * @param one integer value of one of the values to be tested
       *
       * @param other integer value of the other of the two values to be tested
       *
       * @return integer greater of the two values
       */
      private int getMax( int one, int other )
         {
             int max = one;

             if( other > max )
                {
                   max = other;
                }

             return max;
         }

      /**
       * Test for empty tree
       *
       * @return Boolean result of test
       */
      public boolean isEmpty()
         {
            return RBTree_Root == null;
         }

      /**
       * Allows user to set tree display character
       *
       * @param displayCode specifies whether to set tree
       * to display data character (NODE_DATA)
       * or color letter (NODE_COLOR)
       */
      public void setTreeDisplayCharacter( int displayCode )
         {
            treeCharDisplayCode = displayCode;
         }

      /**
       * Swaps colors between two RBT nodes
       *
       * @param one one of the RBT nodes
       *
       * @param other other of the RBT nodes
       */
      public void swapColors( RedBlackNode one, RedBlackNode other )
         {
            int tempColor = one.nodeColor;
            one.nodeColor = other.nodeColor;
            other.nodeColor = tempColor;
         }

      /**
       * Local recursive method to calculate exponentiation
       * with integers
       *
       * @param base base of exponentiation
       *
       * @param exponent exponent of exponentiation
       *
       * @return result of exponentiation calculation
       */
      private int toPower( int base, int exponent )
         {
            if( exponent > 0 )
               {
                  return toPower( base, exponent - 1 ) * base;
               }

            return 1;
         }
   }
