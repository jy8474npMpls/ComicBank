// Much of my project code has been cobbled/learned from previous labs and projects

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VaultGUI extends JFrame {
// link to GUI items
    private JPanel mainPanel;
    private JTextField nameInput;
    private JTextField issueInput;
    private JTextField yearInput;
    private JTextField titleInput;
    private JButton addButton;
    private JButton removeButton;
    private JList<Comic> comicList;  // Add this component to the VaultGUI form
    // Activate controller
    private Controller controller;

    private DefaultListModel<Comic> allComicsListModel;
// Run GUI
    VaultGUI(Controller controller) {
        // Need this reference to the controller object to make requests from the database
        this.controller = controller;

        // Configure the list model  TODO initialize the allComicsListModel before this line
        allComicsListModel = new DefaultListModel<Comic>();
        
        // Add a JList to the GUI, call it comicList, and this line will work
        comicList.setModel(allComicsListModel);
        
        addListeners();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
// Check for errors
    private void errorDialog(String msg) {
        JOptionPane.showMessageDialog(VaultGUI.this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
// Listeners
    private void addListeners() {
// Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read data. Send message to Database via Controller.
                String name = nameInput.getText();

                if (name.isEmpty()) {
                    errorDialog("Enter the comic's name.");
                    return;
                }

                int issue;

                try {
                    issue = Integer.parseInt(issueInput.getText());
                } catch (NumberFormatException nfe) {
                    errorDialog("Enter the comic's issue number.");
                    return;
                }

                int year;

                try {
                    year = Integer.parseInt(yearInput.getText());
                } catch (NumberFormatException nfe) {
                    errorDialog("Enter the comic's year.");
                    return;
                }

                String title = titleInput.getText();

                if (title.isEmpty()) {
                    errorDialog("Enter the comic's title.");
                    return;
                }

                Comic vaultRecord = new Comic(name, issue, year, title);
                String result = controller.addComicToVault(vaultRecord);

                if (result.equals(VaultDB.OK)) {
                    // Clear fields
                    nameInput.setText("");
                    issueInput.setText("");
                    yearInput.setText("");
                    nameInput.setText("");

                    ArrayList<Comic> allData = controller.getAllData();
                } else {
                    errorDialog(result);
                }

            }
        });
// Remove Button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Identify what is selected
                
                Comic comic = comicList.getSelectedValue();
                if (comic == null) {
                    JOptionPane.showMessageDialog(VaultGUI.this, "Please choose a comic to " +
                            "remove.");
                } else {
                    controller.deleteComic(comic);
                    ArrayList<Comic> comics = controller.getAllData();
                    setListData(comics);
                }
                }
        });

    }
// Update Collection
    void setListData(ArrayList<Comic> data) {
        allComicsListModel.clear();

        if (data != null) {
            for (Comic comic : data) {
                allComicsListModel.addElement(comic);
            }
        }
    }
    
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
