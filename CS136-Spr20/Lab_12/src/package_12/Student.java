package package_12;

public class Student
   {
         int id;
         String name;
         public Student next;

         // Constructor
         Student( int id, String name )
            {
                this.id = id;
                this.name = name;
                this.next = null;
            }
   }