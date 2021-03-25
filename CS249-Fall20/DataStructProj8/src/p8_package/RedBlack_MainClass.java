package p8_package;

public class RedBlack_MainClass
   {

      public static void main(String[] args)
         {
            RedBlackTreeClass copiedClass, testClass = new RedBlackTreeClass();
            //char removedItem, foundItem;

            // balanced cases
            testClass.insert( 'M' );
            testClass.insert( 'G' );
            testClass.insert( 'R' );
            testClass.insert( 'B' );
            testClass.insert( 'J' );
            testClass.insert( 'P' );
            testClass.insert( 'U' );
            testClass.insert( 'A' );
            testClass.insert( 'C' );
            testClass.insert( 'H' );
            testClass.insert( 'L' );
            testClass.insert( 'N' );
            testClass.insert( 'Q' );
            testClass.insert( 'S' );
            testClass.insert( 'X' );
/*
            // Right right cases
            testClass.insert( 'A' );
            testClass.insert( 'B' );
            testClass.insert( 'C' );
            testClass.insert( 'D' );
            testClass.insert( 'E' );
            testClass.insert( 'F' );
            testClass.insert( 'G' );
            testClass.insert( 'H' );
            testClass.insert( 'I' );
            testClass.insert( 'J' );
            testClass.insert( 'K' );


            // Left left cases
            testClass.insert( 'Z' );
            testClass.insert( 'Y' );
            testClass.insert( 'X' );
            testClass.insert( 'W' );
            testClass.insert( 'V' );
            testClass.insert( 'U' );
            testClass.insert( 'T' );
            testClass.insert( 'S' );
            testClass.insert( 'R' );
            testClass.insert( 'Q' );
            testClass.insert( 'P' );
*/
            System.out.println( "\nTree height: " + testClass.findTreeHeight() );

            System.out.println( "\nTree Data Display:" );

            testClass.displayTreeStructure();

            testClass.setTreeDisplayCharacter( RedBlackTreeClass.NODE_COLOR );

            System.out.println( "\nTree Red/Black Color Display:" );

            testClass.displayTreeStructure();

            System.out.println( "\nInorder List Data Display:" );

            testClass.displayTree( RedBlackTreeClass.INORDER_TRAVERSE );

            System.out.println( "\nImplementing Copy Constructor" );
            copiedClass = new RedBlackTreeClass( testClass );

            copiedClass.displayTree( RedBlackTreeClass.INORDER_TRAVERSE );

            System.out.println( "\nPreorder List Data Display:" );

            copiedClass.displayTree( RedBlackTreeClass.PREORDER_TRAVERSE );

            System.out.println( "\nPostorder List Data Display:" );

            copiedClass.displayTree( RedBlackTreeClass.POSTORDER_TRAVERSE );

            System.out.println( "\nCopied List Data Display:" );

            copiedClass.setTreeDisplayCharacter( RedBlackTreeClass.NODE_DATA );

            copiedClass.displayTreeStructure();

            System.out.println( "\nCopied Tree Red/Black Color Display:" );

            copiedClass.setTreeDisplayCharacter( RedBlackTreeClass.NODE_COLOR );

            copiedClass.displayTreeStructure();

         }

   }
