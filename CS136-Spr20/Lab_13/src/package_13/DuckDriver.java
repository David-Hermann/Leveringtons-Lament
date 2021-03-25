package package_13;

public class DuckDriver
   {
      public static void main(String[] args)
         {
            Duck genericDuck = new Duck();
            genericDuck.display();
            genericDuck.quack();
            genericDuck.fly();

            System.out.println( "\n" );

            Duck newDuck = new MallardDuck();
            newDuck.display();
            newDuck.quack();
            newDuck.fly();

            System.out.println( "\n" );

            Duck nextDuck = new RedHeadDuck();
            nextDuck.display();
            nextDuck.quack();
            nextDuck.fly();

            System.out.println( "\n" );

            Duck lastDuck = new RubberDuck();
            lastDuck.display();
            lastDuck.quack();
            lastDuck.fly();
         }
   }
