package package_13;

public class RubberDuck extends Duck
   {
      public RubberDuck()
         {
            System.out.println( "Child constructor" );
         }

      @Override
      public void fly()
         {
            System.out.println( "I'm floating" );
         }

      @Override
      public void display()
         {
            System.out.println( "I'm a rubber duck" );
         }

      @Override
      public void quack()
         {
            System.out.println( "Squeek" );
         }
   }
