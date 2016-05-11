/**
 * Created by liukaichi on 5/10/2016.
 */
public class KeywordTrie
{
    private class Node
    {
        Node children[] = new Node[26];
        int frequency = 0;
        boolean complete = false;
    }
}
