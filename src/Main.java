import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by liukaichi on 5/10/2016.
 */
public class Main
{
    public static void main(String[] args){
        //importData();
        loadGUI();
    }

    private static void loadGUI()
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new SuggestionGUI();
            }
        });



    }

    private static void importData()
    {
        KeywordTrie trie = new KeywordTrie();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        try
        {
            File dataFolder = new File("C:\\Users\\liukaichi\\Programming\\Query-Suggestion\\data");
            if (dataFolder.isDirectory())
            {
                for (File file : dataFolder.listFiles())
                {
                    System.out.println("Reading file " + file.getPath());
                    Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));

                    scanner.nextLine(); // reads the first line, which are just headers.
                    String previousUser = "";
                    Date previousTime = null;
                    KeywordTrie.Node previousNode = null;
                    while (scanner.hasNextLine())
                    {
                        String entry = scanner.nextLine();
                        String info[] = entry.split("\\t");
                        String currentUser = info[0];

                        //add the query to the trie
                        String query = info[1];
                        KeywordTrie.Node currentNode = trie.addEntry(query);

                        Date currentTime = null;
                        try
                        {
                            currentTime = dateFormat.parse(info[2]);

                            if (previousTime != null)
                            {
                                double minutesPassed = ((currentTime.getTime() - previousTime.getTime()) * 1000 / 60);
                                if ( minutesPassed >= 10 && currentUser.equals(previousUser))
                                {

                                    //this was modified well!
                                    Map<String, Integer> previousModifiedMap = previousNode.getModifiedTo();
                                    if (previousModifiedMap == null)
                                    {
                                        previousNode.setModifiedTo(new HashMap<String, Integer>());
                                        previousModifiedMap = previousNode.getModifiedTo();
                                    }
                                    if (previousModifiedMap.containsKey(query))
                                    {
                                        previousModifiedMap.put(query, previousModifiedMap.get(query) + 1);
                                    }
                                    else
                                    {
                                        previousModifiedMap.put(query, 1);
                                    }

                                }
                            }
                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }

                        previousTime = currentTime;
                        previousUser = currentUser;
                        previousNode = currentNode;

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
