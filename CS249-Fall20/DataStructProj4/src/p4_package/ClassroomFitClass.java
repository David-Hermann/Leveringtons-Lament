package p4_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class used to input RoomClass and CourseClass data
 * to find which courses will fit into which rooms
 *
 * @author Dr. Michael Leverington
 * @author David Hermann
 *
 */
public class ClassroomFitClass
   {
       /**
        * Maximum number of rooms expected
        */
       private static final int MAX_NUM_ROOMS = 25;

       /**
        * Maximum input characters from file
        */
       private static final int MAX_INPUT_CHARS = 80;

       /**
        * Setting when class index is not available
        */
       private static final int NO_CLASS = -1;

       /**
        * Local constant
        */
       private static final char SPACE = ' ';

       /**
        * Local constant
        */
       private static final char COMMA = ',';

       /**
        * Local constant
        */
       private static final char SEMI_COLON = ';';

       /**
        * Flag to allow input upload display
        */
       private static final boolean SHOW_INPUT = true;

       /**
        * Flag to allow display of recursive operations
        */
       private static final boolean DISPLAY_PROCESS = true;

       /**
        * FileReader object for managing file input
        */
       private static FileReader fileIn;

       /**
        * Array of rooms
        */
       private static RoomClass[] roomList;

       /**
        * Array of courses
        */
       private static CourseClass[] courseList;

       /**
        * Number of rooms and classes
        */
       private static int numRooms;

       /**
        * Main method drives the operations that upload the data
        * from the file, and conducts the class fitting operations\
        * @param args
        */
       public static void main( String[] args )
          {
              int initialRoomLevel = 0;
              roomList = new RoomClass[ MAX_NUM_ROOMS ];
              courseList = new CourseClass[ MAX_NUM_ROOMS ];

              if( uploadData( "in_7" ) )
                 {
                    System.out.println( "Start Solution Search" );

                    if( fitClassRooms( initialRoomLevel ) )
                       {
                          System.out.println( "\nFit Classroom Success:"
                                                          + " Solution Found.");
                          displayList();
                       }
                    else
                       {
                          System.out.println( "\nFit Classroom Failure:"
                                                      + " Solution not found.");
                       }
                 }
              else
                 {
                    System.out.println( "Upload failure" );
                 }
          }

       /**
        * Tests and reports if a character is found in a given string
        *
        * @param testChar character to be tested against the string
        * @param testString string within which the character is tested
        * @return Boolean result of test
        */
       private static boolean charInString( char testChar, String testString )
          {
             int index;

             for( index = 0; index < testString.length(); index++ )
                {
                   if( testChar == testString.charAt( index ) )
                      {
                         return true;
                      }
                }

             return false;
          }

       /**
        * Compares two strings without consideration for case
        *
        * @param oneString one of the strings to be tested
        * @param otherString other string to be tested
        * @return first greater than second: greater than zero;
        * first equal to second: equals zero
        * first less than second: less than zero
        */
       public static int compareStrings( String oneString, String otherString )
          {
             int difference, index = 0;

             while( index < oneString.length() && index < otherString.length() )
                {
                   difference = toLower( oneString.charAt( index ) )
                                       - toLower( otherString.charAt( index ) );

                   if( difference != 0 )
                      {
                         return difference;
                      }

                   index++;
                }

             return oneString.length() - otherString.length();
          }

       /**
        * Displays classrooms with associated courses
        */
       public static void displayList()
          {
              RoomClass roomData;
              CourseClass courseData;
              int roomIndex, courseIndex;
              String roomStr, courseStr;

              System.out.println( "\nClassroom/Class Request Fit List" );
              System.out.println( "================================\n" );

              for( roomIndex = 0; roomIndex < numRooms; roomIndex++ )
                 {
                    roomData = roomList[ roomIndex ];
                    roomStr = roomData.toString();
                    courseIndex = roomData.getAssociatedIndex();
                    courseData = courseList[ courseIndex ];
                    courseStr = courseData.toString();

                    System.out.println( roomStr + " - " + courseStr );
                 }
          }

       /**
        * Uses recursive backtracking to set the correct sized courses
        * into the appropriate class rooms
        *
        * @param roomLevel parameter indicates level of recursion
        * which also indicates the classroom that is seeking a course
        * @return Boolean result of fitting operation
        */
       private static boolean fitClassRooms( int roomLevel )
          {
             // Declare local constants
             final char END_LINE = '\n';

             // Declare local variables
             RoomClass currentRoom = roomList[ roomLevel ];
             CourseClass currentCourse = null;
             int currentRoomCapacity, currentCourseSize;
             int courseAccessIndex = 0;

             // Check if roomLevel past end of room list
             if( roomLevel == numRooms )
                {
                   return true;
                }
             else
                {
                   // There are still rooms to be filled, get capacity
                   currentRoomCapacity = currentRoom.getRoomCapacity();
                }

             // If true, display which room needs a course
             if( DISPLAY_PROCESS )
                {
                   System.out.print( END_LINE );
                   printSpaces( roomLevel );
                   System.out.print( "Seeking a fit for " );
                   System.out.print( currentRoom.toString() + END_LINE );
                }

             // Loop across courses
             while( courseAccessIndex < numRooms )
                {
                   // Get a course
                   currentCourse = courseList[ courseAccessIndex ];
                   currentCourseSize = currentCourse.getSizeRequest();

                   // If true, display which course is being tested
                   if( DISPLAY_PROCESS )
                      {
                         printSpaces( roomLevel + 2 );
                         System.out.print( "Trying " );
                         System.out.print( currentCourse.toString() );
                      }

                   // Check if currentCourse fits in room and course
                   // availability
                   if( currentCourseSize <= currentRoomCapacity &&
                                             currentCourse.courseIsAvailable() )
                      {
                         // Set course to room
                         currentRoom.setAssociatedIndex( courseAccessIndex );
                         // Set course to unavailable
                         currentCourse.setCourseAvailable( false );

                         // Display success, print course and room it is in
                         if( DISPLAY_PROCESS )
                            {
                               System.out.print( " - " );
                               System.out.print( "Success!" + END_LINE );
                               printSpaces( roomLevel + 4 );
                               System.out.print( currentCourse.toString() );
                               System.out.print(  " will fit in "  );
                               System.out.print( currentRoom.toString() +
                                                                     END_LINE );
                            }

                         // Call method with next room index
                         if( fitClassRooms( roomLevel + 1 ) )
                            {
                               return true;
                            }
                         else
                            {
                               // Iteration through all the rooms was finished
                               // with no fit found, thus set course to
                               // available
                               currentCourse.setCourseAvailable( true );

                               // This will only display if a course is tested
                               // against all rooms in roomList
                               if( DISPLAY_PROCESS )
                                  {
                                     System.out.print( END_LINE );
                                     printSpaces( roomLevel + 4 );
                                     System.out.print(
                                                     currentCourse.toString() );
                                     System.out.print( " - recursion failed!" +
                                                          END_LINE + END_LINE );
                                  }
                            }
                      }
                   else
                      {
                         // Course didn't fit inside room
                         // Set classroom association to NO_CLASS
                         currentRoom.setAssociatedIndex( NO_CLASS );

                         // If true, displays current course didn't fit in room
                         if( DISPLAY_PROCESS )
                            {
                               System.out.print( " - " );
                               System.out.print( "Failed!" + END_LINE );
                            }
                      }

                   // Increment course access index to compare another course
                   // to currentRoom
                   courseAccessIndex++;
                }
             // End loop across courses

             /*
              * If the method gets to this point, all courses in courseList have
              * been compared to currentRoom with no fits and the most recent
              * version of this method will pop off the stack.
              * Therefore the method will return to the previous version and
              * that course will be set to available.
              */
             return false;
          }

       /**
        * Gets a string up to a maximum length or to specified delimiter
        *
        * @param maxLength maximum length of input line
        * @param delimiterChar delimiter character to stop input
        * @return String captured from file
        */
       private static String getALine( int maxLength, char delimiterChar )
          {
              int inCharInt;
              int index = 0;
              String strBuffer = "";

              try
                 {
                     inCharInt = fileIn.read();

                     // consume leading spaces
                     while( index < maxLength && inCharInt <= ( SPACE ) )
                        {
                           index++;
                           inCharInt = fileIn.read();
                        }

                     while( index < maxLength && inCharInt != ( delimiterChar ) )
                        {
                           if( inCharInt >= ( SPACE ) )
                              {
                                 strBuffer += (char)( inCharInt );

                                 index++;

                                 if( compareStrings( strBuffer,
                                                     "Start Room Data;" ) == 0 )
                                    {
                                       return "Start_Classrooms";
                                    }
                                 else if( compareStrings( strBuffer,
                                                       "End Room Data;" ) == 0 )
                                    {
                                       return "End_Classrooms";
                                    }
                                 else if( compareStrings( strBuffer,
                                                    "Start Class Data;" ) == 0 )
                                    {
                                       return "Start_Class_Requests";
                                    }
                                 else if( compareStrings( strBuffer,
                                                      "End Class Data;" ) == 0 )
                                    {
                                       return "End_Class_Requests";
                                    }
                              }

                           inCharInt = fileIn.read();
                        }

                     inCharInt = fileIn.read();
                 }
              catch( IOException ioe )
                 {
                    System.out.println( "INPUT ERROR: Failure to capture"
                                                               + " character" );

                    strBuffer = "";
                 }

              return strBuffer;
          }

       /**
        * Gets an integer from the input string
        *
        * @param maxLength maximum length of characters input to capture the
        * integer
        * @return integer captured from file
        */
       private static int getAnInt( int maxLength )
          {
              int inCharInt;
              int index = 0;
              String strBuffer = "";

              try
                 {
                    inCharInt = fileIn.read();

                    // clear space up to number
                    while( index < maxLength && !charInString( (char)inCharInt,
                                                                "0123456789" ) )
                       {
                          inCharInt = fileIn.read();

                          index++;
                       }

                    while( charInString( (char)inCharInt, "0123456789" ) )
                       {
                          strBuffer  += (char)( inCharInt );

                          index++;

                          inCharInt = fileIn.read();
                       }
                 }
              catch( IOException ioe )
                 {
                    System.out.println( "INPUT ERROR: Failure to capture"
                                                               + " character" );

                    strBuffer = "";
                 }

              return Integer.parseInt( strBuffer );
          }

       /**
        * Prints a specified number of spaces to screen
        *
        * @param numSpaces number of spaces to print
        */
       private static void printSpaces( int numSpaces )
          {
             while( numSpaces > 0 )
                {
                   System.out.print( SPACE );

                   numSpaces--;
                }
          }

       /**
        * Changes upper case letter to a lower case letter
        *
        * @param inChar letter to be tested and potentially modified
        * @return lower case letter if upper case letter is input;
        * otherwise character is returned unchanged
        */
       private static char toLower( char inChar )
          {
             if( inChar >= 'A' && inChar <= 'Z' )
                    {
                       return (char)( inChar - 'A' + 'a' );
                    }

             return inChar;
          }

       /**
        * Uploads data from requested file
        *
        * @param fileName name of file to access
        * @return Boolean result of data upload
        */
       private static boolean uploadData( String fileName )
          {
              String bldgName, courseName;
              int roomNumber, roomCap, numStudents;
              int roomIndex = 0, courseIndex = 0;

              try
                 {
                    // Open FileReader
                    fileIn = new FileReader( fileName );

                    // get first dummy line - "Start Room Data"
                    getALine( MAX_INPUT_CHARS, SEMI_COLON );

                    // get first building name
                    bldgName = getALine( MAX_INPUT_CHARS, COMMA );

                    while( compareStrings( bldgName, "End_Classrooms" ) != 0 )
                       {
                          roomNumber = getAnInt( MAX_INPUT_CHARS );

                          roomCap = getAnInt( MAX_INPUT_CHARS );

                          roomList[ roomIndex ] = new RoomClass( bldgName,
                                                          roomNumber, roomCap );

                          if( SHOW_INPUT )
                             {
                                System.out.println(
                                             roomList[ roomIndex ].toString() );
                             }

                          roomIndex++;

                          bldgName = getALine( MAX_INPUT_CHARS, COMMA );
                       }

                    numRooms = roomIndex;

                    // get first dummy line - "Start Course Data"
                    getALine( MAX_INPUT_CHARS, SEMI_COLON );

                    // get first course name
                    courseName = getALine( MAX_INPUT_CHARS, COMMA );

                    while( compareStrings( courseName, "End_Class_Requests" )
                                                                          != 0 )
                       {
                          numStudents = getAnInt( MAX_INPUT_CHARS );

                          courseList[ courseIndex ] = new CourseClass(
                                                      courseName, numStudents );

                          if( SHOW_INPUT )
                             {
                                System.out.println(
                                         courseList[ courseIndex ].toString() );
                             }

                          courseIndex++;

                          courseName = getALine( MAX_INPUT_CHARS, COMMA );
                       }
                 }
              catch( FileNotFoundException fnfe )
                 {
                    System.out.println( "DATA ACCESS ERROR: Failure to open"
                                                              + " input file" );

                    return false;
                 }

              return ( numRooms == courseIndex );
          }
   }
