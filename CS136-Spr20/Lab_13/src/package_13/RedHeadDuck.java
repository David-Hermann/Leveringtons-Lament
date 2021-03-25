package package_13;

public class RedHeadDuck extends Duck
   {
      public RedHeadDuck()
         {
            System.out.println( "Child constructor" );
         }

      @Override
      public void display()
         {
            System.out.println( "I'm a redhead duck" );
         }
   }
