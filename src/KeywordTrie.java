import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liukaichi on 5/10/2016.
 */
public class KeywordTrie implements Serializable
{
    private static final int SPACE = -65;
    private static final int APOSTROPHE = -58;
    Node rootNode;
    String lastEntry;
    String currentQuery;

    public KeywordTrie()
    {
        rootNode = new Node();
    }

    public Node addEntry(String entry)
    {

        lastEntry = entry;
        return addQuery(entry);
    }

    private Node addQuery(String query)
    {
        StringBuilder sb = new StringBuilder(query.toLowerCase());
        currentQuery = query;
        return addQueryRec(sb, 0, rootNode);
    }

    private Node addQueryRec(StringBuilder query, int index, Node node)
    {
        int childIndex = query.charAt(index) - 'a';

        //check for special characters
        if (childIndex < 0)
        {
            switch (childIndex)
            {
                case SPACE:
                    childIndex = 26;
                    break;
                case APOSTROPHE:
                    childIndex = 27;
                    break;
            default:
                System.out.println("Problem character is: " + query.charAt(index));
            }


        }
        //done if you've reached the end of the word, mark as completed, add to frequency
        if (index == query.length() - 1)
        {
            Node child = node.children[childIndex];
            if (child == null)
            {
                node.children[childIndex] = new Node();
                child = node.children[childIndex];
            }
            child.setComplete(true);
            child.frequency++;
            child.wordRepresented = new StringBuilder(currentQuery);
            return child;
        }

        //else read in the character, and go further into the trie
        Node nextNode = node.children[childIndex];
        if (nextNode == null)
        {
            node.children[childIndex] = new Node();
            nextNode = node.children[childIndex];
            //nextNode.wordRepresented.append(node.wordRepresented.append(query.charAt(index)));
        }
        return addQueryRec(query, ++index, nextNode);

    }

    /*********
     * INNER CLASS
     ********/

    public class Node implements Serializable
    {
        Node children[] = new Node[28];
        int frequency = 0;
        boolean complete = false;
        StringBuilder wordRepresented;
        Map<String, Integer> modifiedTo;

        public Node()
        {
            //modifiedTo = new HashMap();
        }

        public Node[] getChildren()
        {
            return children;
        }

        public void setChildren(Node[] children)
        {
            this.children = children;
        }

        public int getFrequency()
        {
            return frequency;
        }

        public void setFrequency(int frequency)
        {
            this.frequency = frequency;
        }

        public boolean isComplete()
        {
            return complete;
        }

        public void setComplete(boolean complete)
        {
            this.complete = complete;
        }

        public Map<String, Integer> getModifiedTo()
        {
            return modifiedTo;
        }

        public void setModifiedTo(Map<String, Integer> modifiedTo)
        {
            this.modifiedTo = modifiedTo;
        }
    }

}
