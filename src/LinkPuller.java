import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkPuller implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private JTextField text;
    private JTextField text2;
    private JTextArea text3;
    private JLabel label;
    private JLabel label2;
    private JButton button;

    public static void main(String[] args) {
        LinkPuller ex = new LinkPuller();
    }

    public LinkPuller() {
        frame = new JFrame("Find a Character in the Link!");
        panel = new JPanel(new BorderLayout());
        panel2 = new JPanel(new GridLayout(2, 1));
        label = new JLabel("Input a URL here!");
        text = new JTextField();
        label2 = new JLabel("Search for a character!");
        text2 = new JTextField();
        text3 = new JTextArea();
        button = new JButton("GO");
        button.addActionListener(this);

        panel.add(panel2, BorderLayout.NORTH);
        panel2.add(label);
        panel2.add(text, BorderLayout.NORTH);
        panel2.add(label2);
        panel2.add(text2, BorderLayout.SOUTH);
        panel.add(text3, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }



    public void actionPerformed(ActionEvent e) {
            System.out.println("GO button clicked");
            String c = text.getText();
            String searchTerm = text2.getText();
            System.out.println("url: " + c);
            try {
                System.out.println();
                URL url = new URL(c);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ( (line = reader.readLine()) != null ) {
                    if (line.contains(searchTerm)) {
                        int start = line.indexOf(searchTerm) - 10;
                        while (start != -1) {
                            int end = start + 20;
                            String miniLine = line.substring(start, end);
                            System.out.println(c + ". " + miniLine);
                            text3.append("\n" + miniLine);
                            start = line.indexOf(searchTerm, start + 11);
                        }
                    }
                }
                reader.close();
            }
            catch(Exception ex) {
                System.out.println(ex);
            }
        }
    }