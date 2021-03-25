package p7_package;

/**
 * Date: 14 October 2020
 * <p>
 * Program: PA07
 * <p>
 * Class: CS249 - 002
 * <p>
 * "The data structure being a tree perfectly suits the metaphorical burning of
 * my brain"
 * <p>
 * Binary search tree (BST) class that stores StudentClassNode data using the
 * student ID as the key
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class BST_Class
   {
      // Class variables ///////////////////////////////////////////////////////
      /**
       * Root of BST
       */
      private StudentClassNode BST_Root;

      /**
       * Used for acquiring ordered tree visitations in String form
       */
      private String outputString;

      // Class constructors ////////////////////////////////////////////////////
      /**
       * Default class constructor, initializes BST
       */
      public BST_Class()
         {
            BST_Root = null;
            outputString = "";
         }

      /**
       *  Copy constructor
       *  <p>
       *  Note: Uses copyConstHelper
       * @param copied BST_Class object to be copied
       */
      public BST_Class( BST_Class copied )
         {
            BST_Root = copyConstHelper( copied.BST_Root );
            outputString = "";
         }

      // Class methods /////////////////////////////////////////////////////////
      /**
       * Copy constructor helper, recursively adds nodes to duplicate tree
       * @param copiedRef StudentClassNode reference for accessing copied object
       *    data
       * @return StudentClassNode reference to node added at current level of
       *    recursion
       */
      private StudentClassNode copyConstHelper( StudentClassNode copiedRef )
         {
            // Set (StudentClassNode) localTemp to null
            StudentClassNode localTempNode = null;

             // Check if current reference is not null
            if( copiedRef != null )
               {
                  // Set localTemp to a new node using the copied ref
                  localTempNode = copiedRef;

                  // Set the localTemp's leftChild to a recursive call
                  // with the copied ref's left child
                  localTempNode.leftChildRef = copyConstHelper(
                                                       copiedRef.leftChildRef );

                  // Set the localTemp's rightChild to a recursive call
                  // with the copied ref's right child
                  localTempNode.rightChildRef = copyConstHelper(
                                                      copiedRef.rightChildRef );
               }

            // return localTemp;
            return localTempNode;
         }

      /**
       * Clears tree
       */
      public void clearTree()
         {
            // Only you can prevent Binary Search Tree fires
            BST_Root = null;
         }

      /**
       * Insert method for BST
       * <p>
       * Note: uses insert helper method
       * @param inData StudentClassNode data to be added to BST
       */
      public void insert( StudentClassNode inData )
         {
            // Initialize the insert recursive traversal with the root
            insertHelper( BST_Root, inData );
         }

      /**
       * Insert helper method for BST insert action
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       * @param inData StudentClassNode item to be added to BST
       */
      private void insertHelper( StudentClassNode localRoot,
                                                       StudentClassNode inData )
         {
            // This method follows a similar structure as searchHelper with
            // different operations

            // Check for not null
            if( localRoot != null )
               {
                  // Check to see if localRoot's current data
                  // greater than the incoming data
                  if( localRoot.studentID > inData.studentID )
                     {
                        // Check for left child null
                        if( localRoot.leftChildRef == null )
                           {
                              // Set localRoot (parameter)'s left child
                              // to a new StudentClassNode object with the data
                              localRoot.leftChildRef = new
                                                     StudentClassNode( inData );
                           }
                        else   // leftChildRef != null
                           {
                              // Call insertHelper to the left child
                              insertHelper( localRoot.leftChildRef, inData );
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
                              // to a new StudentClassNode object with the data
                              localRoot.rightChildRef = new
                                                     StudentClassNode( inData );
                           }
                        else   // rightChildRef != null
                           {
                              // Call insertHelper to the right child
                              insertHelper( localRoot.rightChildRef, inData );
                           }
                     }
               }
            else
               {
                  // Assume localRoot is null (only happens for empty tree)
                  // set tree root to a new object with the data
                  BST_Root = new StudentClassNode( inData );
               }
         }

      /**
       * Test for empty tree
       * @return Boolean result of test
       */
      public boolean isEmpty()
         {
            return BST_Root == null;
         }

      /**
       * Provides inOrder traversal for user as a string
       * @return String containing in order output
       */
      public String outputInOrder()
         {
            // This is essentially a getter method with the variable being
            // dynamic storage depending on what order
            outputString = "";
            outputInOrderHelper( BST_Root );
            return outputString;
         }

      /**
       * Provides inOrder traversal action helper
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       */
      private void outputInOrderHelper( StudentClassNode localRoot )
         {
            // LEFT PARENT RIGHT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate left child
                  outputInOrderHelper( localRoot.leftChildRef );

                  // Accumulate parent
                  outputString += localRoot.toString();
                  outputString += '\n';

                  // Accumulate right child
                  outputInOrderHelper( localRoot.rightChildRef );
               }
         }

      /**
       * Provides postOrder traversal for use as a string
       * @return String containing post order output
       */
      public String outputPostOrder()
         {
            // This is essentially a getter method with the variable being
            // dynamic storage depending on what order
            outputString = "";
            outputPostOrderHelper( BST_Root );
            return outputString;
         }

      /**
       * Provides postOrder traversal action helper
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       */
      private void outputPostOrderHelper( StudentClassNode localRoot )
         {
            // LEFT RIGHT PARENT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate left child
                  outputPostOrderHelper( localRoot.leftChildRef );

                  // Accumulate right child
                  outputPostOrderHelper( localRoot.rightChildRef );

                  // Accumulate parent
                  outputString += localRoot.toString();
                  outputString += '\n';
               }
         }

      /**
       * Provides preOrder traversal for user as a string
       * @return String containing pre order output
       */
      public String outputPreOrder()
         {
            // This is essentially a getter method with the variable being
            // dynamic storage depending on what order
            outputString = "";
            outputPreOrderHelper( BST_Root );
            return outputString;
         }

      /**
       * Provides preOrder traversal action helper
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       */
      private void outputPreOrderHelper( StudentClassNode localRoot )
         {
            // PARENT LEFT RIGHT

            // Check if current reference is not null
            if( localRoot != null )
               {
                  // Accumulate parent
                  outputString += localRoot.toString();
                  outputString += '\n';

                  // Accumulate left child
                  outputPreOrderHelper( localRoot.leftChildRef );

                  // Accumulate right child
                  outputPreOrderHelper( localRoot.rightChildRef );
               }
         }

      /**
       * Searches tree from given node to minimum value node below it,
       * stores data value found, and then unlinks the node
       * @param minParent StudentClassNode reference to current node
       * @param minChild StudentClassNode reference to child node to be tested
       * @return StudentClassNode reference containing removed node
       */
      private StudentClassNode removeFromMin( StudentClassNode minParent,
                                                     StudentClassNode minChild )
         {
            // Note: I know something is wrong in between here and
            // removeNodeHelper but I can't figure it out for the life of me
            // I followed the lecture videos to a T with no progress
            if( minParent.leftChildRef != null )
               {
                  removeFromMin( minParent.leftChildRef,
                                                        minChild.leftChildRef );
               }
            else
               {
                  minParent.leftChildRef = minChild.rightChildRef;
               }

            return minChild;
         }

      /**
       * Removes data node from tree using given key
       * <p>
       * Note: Uses remove helper method
       * <p>
       * Note: Verifies if data is available with search method, then if found,
       * calls remove
       * @param inData StudentClassNode that includes the necessary key
       * @return StudentClassNode result of remove action
       */
      public StudentClassNode removeNode( StudentClassNode inData )
         {
            StudentClassNode removedData = search( inData );

            if( removedData != null )
               {
                  BST_Root = removeNodeHelper( BST_Root, inData );
               }

            return removedData;
         }

      /**
       * Remove helper for BST remove action
       * <p>
       * Note: Uses removeFromMin method
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       * @param outData StudentClassNode item that includes the necessary key
       * @return StudentClassNode reference result of remove helper action
       */
      private StudentClassNode removeNodeHelper( StudentClassNode localRoot,
                                                      StudentClassNode outData )
         {
            // Shopping List #1

            // is it null, return null
            if( localRoot == null )
               {
                  return null;
               }

            // is the inData value greater than current
            if( outData.studentID > localRoot.studentID )
               {
                  // recurse right, assign the result to localRoot's right child
                  localRoot.rightChildRef = removeNodeHelper(
                                             localRoot.rightChildRef, outData );
               }

            // is the value less than current
            if( outData.studentID < localRoot.studentID )
               {
                  // recurse left
                  localRoot.leftChildRef = removeNodeHelper(
                                              localRoot.leftChildRef, outData );
               }

            // we found it

            // Shopping list #2

            // check if left child is null
            if( localRoot.leftChildRef == null )
               {
                  // set localREf to right child
                  localRoot = localRoot.rightChildRef;
                  return localRoot;
               }
            // check if right child is null
            if( localRoot.rightChildRef == null )
               {
                  // set localRef to left child
                  localRoot = localRoot.leftChildRef;
                  return localRoot;
               }

            // the node has two children

            // shopping list #3

            // does child right of this root have its own left child
            if( localRoot.rightChildRef.leftChildRef != null )
               {
                  // remove from min, assign data to current local Ref
                  localRoot = removeFromMin( localRoot.rightChildRef,
                                         localRoot.rightChildRef.leftChildRef );
               }
            // otherwise
            else
               {
                  // assign the data from the left to the local ref
                  localRoot.studentID = localRoot.leftChildRef.studentID;
                  localRoot.name = localRoot.leftChildRef.name;
                  localRoot.gender = localRoot.leftChildRef.gender;
                  localRoot.gpa = localRoot.leftChildRef.gpa;
                  localRoot.rightChildRef =
                                           localRoot.leftChildRef.rightChildRef;

                  // assign local ref's left child to the left child's left
                  // child
                  localRoot.leftChildRef = localRoot.leftChildRef.leftChildRef;
               }

            return localRoot;
         }

      /**
       * Searches for data in BST given StudentClassNode with necessary key
       * @param searchData StudentClassNode item containing key
       * @return StudentClassNode reference to found data
       */
      public StudentClassNode search( StudentClassNode searchData )
         {
            return searchHelper( BST_Root, searchData );
         }

      /**
       * Helper method for BST search action
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       * @param searchData StudentClassNode item containing key
       * @return StudentClassNode item found
       */
      private StudentClassNode searchHelper( StudentClassNode localRoot,
                                                   StudentClassNode searchData )
         {
            // This method follows a similar structure as insertHelper with
            // different operations

            // Check for not null
            int compareResult;

            if( localRoot != null )
               {
                  compareResult = localRoot.studentID - searchData.studentID;

                  if( compareResult > 0 )
                     {
                        return searchHelper( localRoot.leftChildRef,
                                                                   searchData );
                     }
                  else if( compareResult < 0 )
                     {
                        return searchHelper( localRoot.rightChildRef,
                                                                   searchData );
                     }

                  return new StudentClassNode( localRoot );
               }

            return null;
         }
   }
