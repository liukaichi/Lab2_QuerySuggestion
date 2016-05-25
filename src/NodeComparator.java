import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by liukaichi on 5/24/2016.
 */
public class NodeComparator implements Comparator<KeywordTrie.Node>
{
    int queryWordNumber;
    String lastQueryWord;
    WCF_App wcf_app = new WCF_App();
    KeywordTrie.Node queryNode;
    Map<String, Integer> queryModifications;
    public NodeComparator(String query)
    {
        String word[] = query.split(" ");
        queryWordNumber = word.length;
        lastQueryWord = word[word.length-1];
        this.queryModifications = Main.trie.findEntry(query).getModifiedTo();
    }

    @Override public int compare(KeywordTrie.Node o1, KeywordTrie.Node o2)
    {
        double scoreo1 = getSuggestedRank(o1);
        double scoreo2 = getSuggestedRank(o2);
        if (scoreo1 == scoreo2) return 0;
        return scoreo1 > scoreo2 ? -1 : 1;
    }


    public double getSuggestedRank(KeywordTrie.Node node)
    {
        double freq = 0;
        double wcf = 0;
        double mod = 0;

        freq = Math.log(node.getFrequency());
        try
        {
            double wcf_score = wcf_app.compare(lastQueryWord, node.getWordRepresented().split(" ")[queryWordNumber]);
            if (wcf_score == -1) wcf = 0;
            else wcf = Math.log(wcf_score);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        if (queryModifications != null)
        {
            if (queryModifications.containsKey(node.getWordRepresented()))
            {
                //System.out.println("this one has a lot of mods.");
                mod = Math.log(queryModifications.get(node.getWordRepresented()));
            }

        }


        return (freq + wcf + mod) / (1 - Math.min(Math.min(freq, wcf), mod));



    }
}
