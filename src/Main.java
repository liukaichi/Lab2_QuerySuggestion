import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by liukaichi on 5/10/2016.
 */
public class Main
{
    public static void main(String[] args){
        KeywordTrie trie = new KeywordTrie();
        try
        {
            File dataFolder = new File("C:\\Users\\liukaichi\\Programming\\Query-Suggestion\\data");
            if (dataFolder.isDirectory())
            {
                for (File file : dataFolder.listFiles())
                {
                    Scanner scanner = new Scanner(file);

                    scanner.nextLine(); // reads the first line, which are just headers.
                    while (scanner.hasNextLine())
                    {
                        AOLEntry newEntry = new AOLEntry(scanner.nextLine());
                        trie.addEntry(newEntry);
                    }
                }
            }

            System.out.println("Done!");



        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
