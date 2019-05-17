// Much of my project code has been cobbled/learned from previous labs and projects.

import java.util.ArrayList;

public class Controller {
// Associate GUI & DB
    private VaultGUI gui;
    private VaultDB db;

    public static void main(String[] args) {
        new Controller().startApp();
    }
// Fetch Collection at start-up
    private void startApp() {
        db = new VaultDB();

        ArrayList<Comic> allData = db.fetchAllRecords();

        gui = new VaultGUI(this);
        gui.setListData(allData);
    }
// Get all data
    ArrayList<Comic> getAllData() {
        return db.fetchAllRecords();
    }
// Add comic
    String addComicToVault(Comic comic) {
         return db.addRecord(comic);
    }
// Delete comic
    void deleteComic(Comic comic) {
         db.delete(comic);
    }
}
