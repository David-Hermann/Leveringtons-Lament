package p8_package;

/**
 * Red Black node for use in Red Black Tree
 *
 * @author Michael Leverington
 *
 */
  public class RedBlackNode
     {
      /**
       * Color black constant
       */
      public static final int COLOR_BLACK = 66;

      /**
       * Color red constant
       */
      public static final int COLOR_RED = 82;

      /**
       * Character node data
       */
       public char nodeData;

       /**
        * Color for node
        */
       public int nodeColor;

       /**
        * Parent reference for node
        */
       public RedBlackNode parentRef;

       /**
        * Left child reference for node
        */
       public RedBlackNode leftChildRef;

       /**
        * Right child reference for node
        */
       public RedBlackNode rightChildRef;

       /**
        * Initialization constructor for data
        *
        * @param inData char quantity
        */
       public RedBlackNode( char inData )
          {
           nodeData = inData;

           nodeColor = COLOR_RED;

           parentRef = leftChildRef = rightChildRef = null;
          }

        /**
        * copy constructor for RedBlackNode
        *
        * @param copied RedBlackNode object to be duplicated
        */
       public RedBlackNode( RedBlackNode copied )
          {
           nodeData = copied.nodeData;

           nodeColor = copied.nodeColor;

           parentRef = leftChildRef = rightChildRef = null;
          }

   }
