/*
 * Author: David Hermann
 * Date: 10 April 2020
 * Program: Lab 12 Linked List
 * Class: CS136L - 001
 *
 * "I may not be able to prove it, but I got this son of a gun to compile and
 * run flawlessly on the first try. Procrastination is the best strategy."
 */

package package_12;

public class LinkedListClass
{
   /**
    * Head node of the linked list
    */
   private Student head;

   /**
    * Default constructor for the LinkedListClass
    */
   public LinkedListClass()
   {
      this.head = null;
   }

   /**
    * Appends a new node at the end of the passed in list.
    *
    * @param list The list to append to
    * @param id ID of the student, a member variable of the Student class
    * @param name Name of the student, a member variable of the Student class
    * @return The list passed into the method with the new node
    */
   public LinkedListClass insert( LinkedListClass list, int id, String name )
      {
         // Create a new node with id and name
         Student newStudent = new Student( id, name );
         Student currentStudent;

         // If the Linked List is empty, make the new node the head
         if( list.head == null )
            {
               list.head = newStudent;
               newStudent.next = null;
            }
         // Else traverse until the last node and append newStudent
         else
            {
               currentStudent = list.head;

               while( currentStudent.next != null )
                  {
                     currentStudent = currentStudent.next;
                  }

               // Insert newStudent at last node
               currentStudent.next = newStudent;
            }

         // Return the list
         return list;
      }

   /**
    * Traverses a LinkedListClass and prints the data of each node.
    * <p>
    * The printed data for this specific class is the Name and ID of a student.
    *
    * @param list LinkedListClass to traverse
    */
   public void printList( LinkedListClass list )
      {
         Student currentStudent = list.head;
         String currentName;
         int currentID;


         System.out.println( "LinkedList: ");

         // Traverse through the LinkedList
         while( currentStudent.next != null )
            {
               currentName = currentStudent.name;
               currentID = currentStudent.id;

               // Print the name and id at the current node
               System.out.println( currentName + ' ' + currentID );

               // Go to next node
               currentStudent = currentStudent.next;
            }
      }
}
