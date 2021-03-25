package package_12;

public class LLDriver
   {
      public static void main(String[] args)
      {
          //Create instance
          LinkedListClass list = new LinkedListClass();

          // Insert the values
          list.insert(list, 1234, "Michael");
          list.insert(list, 4321, "Dwight");
          list.insert(list, 1111, "Jim");
          list.insert(list, 1001, "Pam");
          list.insert(list, 9999, "Angela");
          list.insert(list, 5678, "Kevin");
          list.insert(list, 8765, "Oscar");
          list.insert(list, 5555, "NardDog");

          // Print the LinkedList
          list.printList(list);
      }
  }
