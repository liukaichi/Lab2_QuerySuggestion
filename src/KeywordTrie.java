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
    AOLEntry lastEntry;
    String currentQuery;

    public KeywordTrie()
    {
        rootNode = new Node();
    }

    public void addEntry(AOLEntry entry)
    {
        addQuery(entry.getQuery());
        lastEntry = entry;
    }

    private void addQuery(String query)
    {
        StringBuilder sb = new StringBuilder(query.toLowerCase());
        currentQuery = query;
        addQueryRec(sb, 0, rootNode);
    }

    private void addQueryRec(StringBuilder query, int index, Node node)
    {
        int childIndex = query.charAt(index) - 'a';
        //if (childIndex == -65) childIndex = 26;
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
        //done if you've reached the end of the word, mark as completed
        if (index == query.length() - 1)
        {
            Node child = node.children[childIndex];
            if (child == null)
            {
                node.children[childIndex] = new Node();
                child = node.children[childIndex];
            }
            child.setComplete(true);
            child.wordRepresented = new StringBuilder(currentQuery);
            return;
        }

        //else read in the character, and go further into the trie
        Node nextNode = node.children[childIndex];
        if (nextNode == null)
        {
            node.children[childIndex] = new Node();
            nextNode = node.children[childIndex];
            //nextNode.wordRepresented.append(node.wordRepresented.append(query.charAt(index)));
        }
        addQueryRec(query, ++index, nextNode);
    }

    /*********
     * INNER CLASS
     ********/

    private class Node implements Serializable
    {
        Node children[] = new Node[28];
        int frequency = 0;
        boolean complete = false;
        StringBuilder wordRepresented;
        Map<String, Integer> modifiedTo;

        public Node()
        {
            wordRepresented = new StringBuilder();
            modifiedTo = new HashMap();
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
