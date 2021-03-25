package package_13;

public class MallardDuck extends Duck
   {
      public MallardDuck()
         {
            System.out.println( "Child constructor" );
         }

      @Override
      public void display()
         {
            System.out.println( "I'm a mallard duck" );
         }
   }
