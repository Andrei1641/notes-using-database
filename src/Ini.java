import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Ini extends JFrame {
    public JPanel panelCheck = new JPanel();
    public JPanel panelAdd = new JPanel();

    public Ini() {
        this.setSize(350, 450);
        this.setDefaultCloseOperation(3);

        final CardLayout cardLayout = new CardLayout();
        final JPanel panelCard = new JPanel(cardLayout);
        JPanel buttonPanel = new JPanel(new GridLayout());
        JButton buttonCheck = new JButton("Check");
        JButton buttonAdd = new JButton("Add");

        JTextArea addTextArea = new JTextArea(20, 30);
        JTextField addTextField = new JTextField(30);
        JButton send = new JButton("Send");
        DConnection a = new DConnection();
        JScrollPane jScrollPaneText = new JScrollPane(addTextArea);

        addTextArea.setLineWrap(true);
        addTextArea.setWrapStyleWord(true);

        jScrollPaneText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        addTextField.setMaximumSize(new Dimension(30, 10));

        this.panelCheck.setBackground(Color.YELLOW);
        this.panelAdd.setBackground(Color.BLACK);
        panelCard.add(this.panelCheck, "Check");
        panelCard.add(this.panelAdd, "Add");
        buttonCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCard, "Check");
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCard, "Add");
            }
        });
        buttonPanel.add(buttonCheck);
        buttonPanel.add(buttonAdd);
        this.setLayout(new BorderLayout());
        this.add(panelCard, "Center");
        this.add(buttonPanel, "North");
        this.setVisible(true);

        java.sql.Connection connection = a.getConnection();

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatedDT = localDateTime.format(formater);

                if (connection != null) {

                    String tmpText = addTextArea.getText();
                    String tmpTema = addTextField.getText();

                    DConnection.insertRecord(connection, 0, formatedDT, tmpText,tmpTema ,Main.name1 );

                    addTextArea.setText("");
                    addTextField.setText("");

                    Main.a.revalidate();
                    Main.a.repaint();

                    panelCheck.revalidate();
                    panelCheck.repaint();
                    panelAdd.revalidate();
                    panelAdd.repaint();
                } else {
                    System.out.println("Connection is null.");
                }
            }
        });
this.panelAdd.setLayout(new GridBagLayout());
GridBagConstraints bagConstraints = new GridBagConstraints();

bagConstraints.gridx = 0;
bagConstraints.gridy = 0;
bagConstraints.gridwidth = 1;
bagConstraints.gridheight = 1;
bagConstraints.fill = GridBagConstraints.BOTH;
this.panelAdd.add(addTextField, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        this.panelAdd.add(jScrollPaneText, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 2;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        this.panelAdd.add(send, bagConstraints);
    }
}

