package package_1;

public class ImageMakerClass
   {
    private static final char SPACE = ' ';
    private static final char X_CHAR = 'X';
    private static final char ENDLINE_CHAR = '\n';

    public static void main(String[] args)
       {
          int numSpaces, numChars;
        // Diamond with cross /////////////////////////////////////////////////
        // Line 1
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Line 2
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printEndLine();

        // Line 3
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Line 4
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Line 5
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printEndLine();

        // Line 6
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Line 7
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printChar(X_CHAR);
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Line 8
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printChar(X_CHAR);
          printChar(X_CHAR);
          printEndLine();

        // Line 9
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printSpace();
          printChar(X_CHAR);
          printEndLine();

        // Separation line
          printEndLine();

        // Square with X //////////////////////////////////////////////////////
        // Line 1
          numSpaces = 2;
          numChars = 7;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 2
          printSpaces(numSpaces);
          numChars = 2;
          printChars(numChars, X_CHAR);
          numSpaces = 3;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 3
          numSpaces = 2;
          numChars = 1;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          numSpaces = 1;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 4
          numSpaces = 2;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 5
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          numSpaces = 1;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 6
          numSpaces = 2;
          printSpaces(numSpaces);
          numChars = 2;
          printChars(numChars, X_CHAR);
          numSpaces = 3;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 7
          numSpaces = 2;
          numChars = 7;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Separation line
          printEndLine();

        // Diamond shadow with cross //////////////////////////////////////////
        // Line 1
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 2
          numChars = 2;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          numSpaces = 1;
          printSpaces(numSpaces);
          numChars = 1;
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          numChars = 2;
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 3
          numSpaces = 2;
          numChars = 1;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 4
          numChars = 7;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 5
          numChars = 1;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 6
          numChars = 2;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          numSpaces = 1;
          printSpaces(numSpaces);
          numChars = 1;
          printChars(numChars, X_CHAR);
          printSpaces(numSpaces);
          numChars = 2;
          printChars(numChars, X_CHAR);
          printEndLine();

        // Line 7
          numSpaces = 2;
          numChars = 7;
          printSpaces(numSpaces);
          printChars(numChars, X_CHAR);
          printEndLine();
       }

// Supporting methods - DO NOT MODIFY THESE METHODS IN ANY WAY
    public static void printSpace()
       {
        System.out.print( SPACE );
       }

    public static void printChar( char outChar )
       {
        System.out.print( outChar );
       }

    public static void printEndLine()
       {
        System.out.print( ENDLINE_CHAR );
       }

    public static void printSpaces( int numSpaces )
       {
        if( numSpaces > 0 )
           {
            printSpaces( numSpaces - 1 );

            printSpace();
           }
       }

    public static void printChars( int numChars, char outChar )
       {
          if( numChars > 0 )
             {
              printChars( numChars - 1, outChar );

              printChar( outChar );
             }
       }


   }
