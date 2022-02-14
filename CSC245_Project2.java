import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSC245_Project2 {

    public static void main(String[] args) {
        // Read the filename from the command line argument
        String filename = args[0];
        BufferedReader inputStream = null;

        String fileLine;

        //creating a final list, to store different black listed patterns
        //final so other classes cannot instantiate the list to add or delete elements
        //the code \\n\\x allows for choosing of a character, then if it occurs more than x number of times, it gets flagged
        final List<Pattern> blacklist = new ArrayList<>();
        blacklist.add(Pattern.compile("[!#$%^*&<>]")) ;
        blacklist.add(Pattern.compile("(\\@)\\1+"));
        blacklist.add(Pattern.compile("(\\.)\\1+"));


        try {
            inputStream = new BufferedReader(new FileReader(filename));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {

            //normalize before validation
          String s = Normalizer.normalize(fileLine, Normalizer.Form.NFC);

          //print message to show which email is being tested
            //question, does me printing the email, allow for the exploit to be executed? Will email doug to ask
          System.out.println("Testing: " + s);
            //for loop to test each blacklisted patter/characters
            for (Pattern pattern: blacklist){
                Matcher matcher = pattern.matcher(s);
                //if the loop finds a blacklist, throws an IllegalStateException
                if(matcher.find()){
                    throw new IllegalStateException("Invalid email provided");
                }
            }

            }
        } catch (IOException io) {
            System.out.println("Invalid FileA" + io.getMessage()); //reviling as little as possible but helpful to a Dev, having "A" in the throw message
                                                                    // will theoretically let the Dev know which error to focus on.

        } finally {
            // Need another catch for closing
            // the streams
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException io) {
                System.out.println("Invalid fileB" + io.getMessage()); //reviling as little as possible but helpful to a Dev. having "B" in the throw message
                                                                        // will theoretically let the Dev know which error to focus on.

            }

        }


    }

}
