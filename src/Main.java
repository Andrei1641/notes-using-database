import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static String name1 = "";
    public static Ini a;
    public static void main(String[] args) {

        JFrame nameFrame = new JFrame("Enter Your Name");
        nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameFrame.setSize(400, 200);
        nameFrame.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField(30);
        JButton nameOk = new JButton("OK");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        nameFrame.add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        nameFrame.add(nameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        nameFrame.add(nameOk, gridBagConstraints);

        nameOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty()) {
                    name1 = nameField.getText();
                    nameFrame.setVisible(false);

                    a = new Ini();


                    List<DConnection.TmpTabelle> tmpList = new ArrayList<>(DConnection.listTmp);

                    if (tmpList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No records found in database.");
                    } else {

                        for (DConnection.TmpTabelle i : tmpList) {
                            Note note = new Note(i.id, i.checkbox, i.name, i.time, i.tema, i.text);
                            a.panelCheck.add(note);
                            a.panelCheck.revalidate();
                            a.panelCheck.repaint();
                            a.panelAdd.revalidate();
                            a.panelAdd.repaint();
                        }
                    }

                    a.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(nameFrame, "Please enter your name.");
                }
            }
        });


        nameFrame.setVisible(true);
    }
}

