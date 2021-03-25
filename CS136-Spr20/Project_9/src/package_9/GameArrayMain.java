package package_9;

public class GameArrayMain
   {

    public static void main(String[] args)
       {
        int rowCap = 15, colCap = 10, index;
        GameArrayClass gac_2, gac = new GameArrayClass( rowCap, colCap );
        boolean gameOver = false;

        gac.cheat();

        System.out.println( "Game configured with " + rowCap
                              + " rows, and " + colCap + " columns. Enjoy!\n" );

        for( index = 0; index < 5; index++ )
           {
            gac.tryElement();
           }

        System.out.println( "New object created from copy constructor" );

        gac_2 = new GameArrayClass( gac );

        do
           {
            gameOver = gac_2.tryElement();
           }
        while( !gameOver );

        gac_2.cheat();
       }

   }
