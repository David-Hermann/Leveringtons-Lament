package p11_package;

/**
 * Date: 11 November 2020
 * <p>
 * Program: PA11
 * <p>
 * Class: CS249 - 002
 * <p>
 * "Discrete Mathematics has taught me how to discretely yell at graphs."
 * <p>
 * Simple class for managing vertices and edges in a graph
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 */
public class GraphClass
   {
    /**
     * default vertex capacity
     * <p>
     * Note: Limited to number of upper case letters in alphabet
     */
    private final int VERTEX_CAPACITY = 26;

    /**
     * constant indication of vertex not in list
     */
    private final int NOT_IN_LIST = -1;

    /**
     * constant space character
     *
     */
    private final char SPACE = ' ';

    /**
     * constant dash character
     *
     */
    private final char DASH = '-';

    /**
     * size of vertex array
     */
    private int vertexListSize;

    /**
     * array of vertices
     */
    private VertexNode[] vertexList;

    /**
     * Default constructor
     */
    public GraphClass()
       {
          vertexList = new VertexNode[ VERTEX_CAPACITY ];
          vertexListSize = 0;
       }

    /**
     * Gets complete vertex node and data using the adjacent node data
     *
     * @param adjNode AdjacentNode data provided
     *
     * @return VertexNode data found in array
     */
    private VertexNode adjToVertex( AdjacentNode adjNode )
       {
          char vertexListCurrentValue, adjNodeValue = adjNode.getVertex();
          int listIndex;

          for( listIndex = 0; listIndex < vertexListSize; listIndex++ )
             {
                vertexListCurrentValue = vertexList[ listIndex ].getVertex();
                if( vertexListCurrentValue == adjNodeValue )
                   {
                      // New VertexNode created since all data is copied
                      // and to prevent aliasing
                      return new VertexNode( vertexList[ listIndex ] );
                   }
             }

          return null;
       }

    /**
     * Breadth-First Search (BFS), is actually just a traversal
     *
     * @param startVertex character vertex to start with
     *
     * @param showQueue boolean flag to control display
     * of queue during operations
     *
     * @return String result of traversal process
     * showing each visited vertex in the order it was visited
     */
    public String BFS( char startVertex, boolean showQueue )
       {
          // TODO

        // Implement method: +6 pts
          VertexQueue accumulatorForBFS = new VertexQueue();
          VertexNode currentVertex;
          String accumulatorString = "";
          int currentIndex = vertexInList( startVertex );

          if( currentIndex != NOT_IN_LIST )
             {
                currentVertex = vertexList[ currentIndex ];
                accumulatorForBFS.enqueue( currentVertex );
                accumulatorString += currentVertex.getVertex();

                if( showQueue )
                   {
                      System.out.print( "Vertex Queue: " +
                                                 accumulatorForBFS.toString() );
                   }
                currentVertex.setVisited();
             }

          return accumulatorString;
       }

    /**
     * Clears all vertex visited flags; for use after completion of BFS, DFS
     */
    public void clearVisitedFlags()
       {
          int listIndex;

          for( listIndex = 0; listIndex < vertexListSize; listIndex++ )
             {
                vertexList[ listIndex ].unSetVisited();
             }
       }

    /**
     * Depth-First Search (DFS), is actually just a traversal
     *
     * @param startVertex character vertex to start with
     *
     * @param showStack boolean flag to control display
     * of stack during operations
     *
     * @return String result of traversal process
     * showing each visited vertex in the order it was visited
     */
    public String DFS( char startVertex, boolean showStack )
       {
          // TODO
        // Implement method: +6 pts
          VertexNode currentVertex;
          AdjacentNode adjacentToCurrent;
          int vertexCounter = 0, currentIndex = vertexInList( startVertex );
          VertexStack accumulatorForDFS = new VertexStack();
          String accumulatorString = "";

          if( currentIndex != NOT_IN_LIST )
             {
                currentVertex = vertexList[ currentIndex ];

                while( vertexCounter < vertexListSize )
                   {
                      accumulatorForDFS.push( currentVertex );
                      accumulatorString += currentVertex.getVertex();
                      currentVertex.setVisited();

                      if( showStack )
                         {
                            System.out.print( "Vertex Stack: " +
                                                 accumulatorForDFS.toString() );
                         }

                      // NOTE: Missing check for visit flag, this kills the crab
                      adjacentToCurrent = currentVertex.getFirstAdjacency();
                      currentVertex = adjToVertex( adjacentToCurrent );

                      vertexCounter++;
                   }
             }

          return accumulatorString;
       }

    /**
     * Generates a list of the vertices with their adjacent vertices
     */
    public void generateAdjacencyLists()
       {
          int listIndex;
          VertexNode currentVertex;
          AdjacentNode currentAdjacency;
          char currentVertexValue;

          for( listIndex = 0; listIndex < vertexListSize; listIndex++ )
             {
                currentVertex = vertexList[ listIndex ];
                currentVertexValue = currentVertex.getVertex();
                System.out.print( "For Vertex " + currentVertexValue + ": " );

                currentAdjacency = currentVertex.getFirstAdjacency();
                if( currentAdjacency != null )
                   {
                      System.out.print( currentAdjacency.getVertex() );
                   }

                while( currentAdjacency != null )
                   {
                      currentAdjacency = currentVertex.getNextAdjacency();
                      System.out.print( ", " + currentAdjacency.getVertex() );
                   }
             }
       }

    /**
     * Generates an adjacency matrix table
     * that displays weights between vertices
     */
    public void generateAdjacencyMatrix()
       {
          // TODO
        // Implement method: +3 pts
          // Note: has I/O
          System.out.print( SPACE + DASH );
       }

    /**
     * Inserts vertex, adjacent vertex, and weight into array alphabetically
     * <p>
     * Note: Uses insertion sort strategy
     *
     * @param vertex character vertex letter
     *
     * @param adjVertex character adjacent vertex letter
     *
     * @param weight integer weight between vertices
     *
     * @return boolean result of insertion;
     * false if vertex array is full, true otherwise
     */
    private boolean insertVertex( char vertex, char adjVertex, int weight )
       {
          // TODO
        // Implement method: +3 pts

        return false;
       }

    /**
     * Recursive method that prints
     * a specified number of specified characters
     *
     * @param numChars integer number of characters to print
     *
     * @param outChar character value to be printed
     */
    void printChars( int numChars, char outChar )
       {
          if( numChars > 0 )
             {
                System.out.print( outChar );
                printChars( numChars - 1, outChar );
             }
       }

    /**
     * Sets vertex with adjacency
     * <p>
     * Note: Adds new vertex as needed;
     * otherwise adds adjacent vertex and weight to existing vertex
     * <p>
     * Note: Adds vertices in both directions (e.g., A with B as adjacency,
     * and B with A as adjacency)
     * <p>
     * Uses insertVertex to minimize excessive coding
     *
     * @param vertex character vertex letter
     *
     * @param adjVertex character adjacent vertex letter
     *
     * @param weight integer weight between vertices
     *
     * @return boolean result of action,
     * false if vertex array is full, true otherwise
     */
    public boolean setVertex( char vertex, char adjVertex, int weight )
       {
          boolean setSuccess = false;
          int vertexIndex = vertexInList( vertex );

          if( vertexListSize < VERTEX_CAPACITY )
             {
                if( vertexIndex != NOT_IN_LIST )
                   {
                      setSuccess = insertVertex( vertex, adjVertex, weight );
                      vertexListSize++;
                   }
                else
                   {
                      vertexList[ vertexIndex ].addAdjacentVertex(
                                                            adjVertex, weight );
                      setSuccess = true;
                   }
             }

          return setSuccess;
       }

    /**
     * Tests for vertex in list
     *
     * @param testVertex character vertex to search for
     *
     * @return integer index if vertex found,
     * constant NOT_IN_LIST otherwise
     */
    private int vertexInList( char testVertex )
       {
          int listIndex;
          char currentVertexValue;

          for( listIndex = 0; listIndex < vertexListSize; listIndex++ )
             {
                currentVertexValue = vertexList[ listIndex ].getVertex();
                if( currentVertexValue == testVertex )
                   {
                      return listIndex;
                   }
             }

          return NOT_IN_LIST;
       }
   }
