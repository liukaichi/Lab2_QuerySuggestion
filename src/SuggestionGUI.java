import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by liukaichi on 5/24/2016.
 */
public class SuggestionGUI extends JFrame
{
    public SuggestionGUI()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(50, 50));

        JTextPane suggestionsBox = new JTextPane ();



        JTextField queryField = new JTextField();
        queryField.setFocusTraversalKeysEnabled(false);
        queryField.addKeyListener(new KeyAdapter()
        {

            @Override public void keyTyped(KeyEvent e)
            {

                if (e.getKeyChar() == KeyEvent.VK_SPACE || e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    KeywordTrie.Node node = Main.trie.findEntry(queryField.getText().trim());
                    KeywordTrie.Node[] suggestionNodes = node.getCompletedChildren();
                    Arrays.sort(suggestionNodes);

                    String result = "";

                    for (int i = 0; i < Math.min(8, suggestionNodes.length); i++)
                    {
                        result += suggestionNodes[i].getWordRepresented() + " " + suggestionNodes[i].frequency + "\n";
                    }


                    suggestionsBox.setText(result);
                }
                super.keyTyped(e);
            }
        });

        panel.add(queryField, BorderLayout.NORTH);
        panel.add(suggestionsBox, BorderLayout.CENTER);
        setContentPane(panel);
        setSize(600, 400);
        setVisible(true);
    }
}
