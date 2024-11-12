import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Note extends JPanel {
    private JScrollPane scrollPane;

    Note(int id, int ccheckBox,String nnameLabel, String ttema, String moreText, String ttime) {

        int iCheckBox = ccheckBox;
        boolean bCheckBox = false;
        String sNameLabel = nnameLabel;
        final String sTema = ttema;
        final String sMoreText = moreText;
        String time = ttime;


        DConnection a = new DConnection();


        JPanel notePanel = new JPanel();
        notePanel.setLayout(new GridBagLayout());


        this.scrollPane = new JScrollPane(notePanel);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel nameLabel = new JLabel(sNameLabel);
        JLabel labelLocalDateTime = new JLabel(time);
        JLabel tema = new JLabel(sMoreText);
        JButton buttonMore = new JButton("More");
        JButton bDelete = new JButton("Delete");
        this.scrollPane = new JScrollPane(this);



        buttonMore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame moreFrame = new JFrame();
                JLabel moreTema = new JLabel(sTema);
                JLabel moreText = new JLabel(sMoreText);
                JPanel scPanel = new JPanel();
                new JPanel();
                JScrollPane moreScrollPane = new JScrollPane(scPanel);
                moreScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                moreScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.fill = 1;
                scPanel.add(moreTema, constraints);
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.fill = 1;
                scPanel.add(moreText, constraints);
                moreFrame.add(moreScrollPane);
                moreFrame.setSize(300, 300);
                moreFrame.setVisible(true);
            }
        });

        java.sql.Connection connection = a.getConnection();

bDelete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        if(connection != null){
DConnection.deleteRecord(connection, id);
            Main.a.revalidate();
            Main.a.repaint();

            Main.a.panelCheck.revalidate();
            Main.a.panelCheck.repaint();
            Main.a.panelAdd.revalidate();
            Main.a.panelAdd.repaint();
        }

    }
});


        this.add(nameLabel);
        this.add(labelLocalDateTime);
        this.add(tema);
        this.add(buttonMore);
        this.add(bDelete);

        this.revalidate();
        this.repaint();

    }

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }
}
