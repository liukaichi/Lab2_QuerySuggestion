import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        JTextPane suggestions = new JTextPane ();



        JTextField queryField = new JTextField();
        queryField.setFocusTraversalKeysEnabled(false);
        queryField.addKeyListener(new KeyAdapter()
        {

            @Override public void keyTyped(KeyEvent e)
            {

                if (e.getKeyChar() == KeyEvent.VK_SPACE || e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    
                }
                super.keyTyped(e);
            }
        });

        panel.add(queryField, BorderLayout.NORTH);
        panel.add(suggestions, BorderLayout.CENTER);
        setContentPane(panel);
        setSize(600, 400);
        setVisible(true);
    }
}
