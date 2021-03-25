package package_12;

/**
 * Simple encryption class that stores character data as integers
 * from the bottom of an array to the top from right to left
 *
 * @author MichaelL
 *
 */
public class EncryptionClassMainToo
   {
    /**
     * Main method uploads data and translates
     *
     * @param args String array of arguments, not used
     */
    public static void main( String[] args )
       {
        String resultStr, testStr = "This is a test, but it is only a test";

        String decOfInd = "When in the Course of human events, it becomes necessary for one people\n"
        + "to dissolve the political bands which have connected them with another,\n"
        + "and to assume among the powers of the earth, the separate and equal station\n"
        + "to which the Laws of Nature and of Nature's God entitle them,\n"
        + "a decent respect to the opinions of mankind requires that they should declare\n"
        + "the causes which impel them to the separation.\n";

        //String testString = new String( testStr );

        System.out.println( "Test with simple string" );
        System.out.println( "=======================\n" );

        EncryptionClass test = new EncryptionClass();

        test.encryptData( testStr );

        resultStr = test.decryptData();

        System.out.println( "Result String from first test: " + resultStr );

        EncryptionClass test_2 = new EncryptionClass( test );

        System.out.println( "\nTest after copy constructor" );
        System.out.println( "===========================\n" );

        test_2.displayEncryptedArray( EncryptionClass.CHARACTER_DISPLAY );

        test_2.displayEncryptedArray( EncryptionClass.INTEGER_DISPLAY );

        System.out.println( "Test of larger text" );
        System.out.println( "===================\n" );

        test_2.encryptData( decOfInd );

        resultStr = test_2.decryptData();

        test_2.displayEncryptedArray( EncryptionClass.CHARACTER_DISPLAY );

        test_2.displayEncryptedArray( EncryptionClass.INTEGER_DISPLAY );

        System.out.println( "Result String after second decrypt:\n"
                                                                  + resultStr );

        System.out.println( "\nDownloading, uploading data test" );
        System.out.println(   "================================" );

        test_2.downloadData( "testfile.txt" );

        test_2.uploadData( "testfile.txt" );

        resultStr = test_2.decryptData();

        test_2.displayEncryptedArray( EncryptionClass.CHARACTER_DISPLAY );

        test_2.displayEncryptedArray( EncryptionClass.INTEGER_DISPLAY );

        System.out.println( "Result String after second decrypt:\n"
                                                                  + resultStr );
       }
   }
