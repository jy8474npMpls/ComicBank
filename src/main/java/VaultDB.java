// Much of my project code has been cobbled/learned from previous labs and projects

import java.sql.*;
import java.util.ArrayList;

public class VaultDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:comics.sqlite";

    private static final String TABLE_NAME = "Collection";
    private static final String NAME_COL = "Comic";
    private static final String ISSUE_COL = "Issue";
    private static final String YEAR_COL = "Year";
    private static final String TITLE_COL = "Title";

    static final String OK = "OK";

    VaultDB() {
        createTable();
    }

    private void createTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NONE EXISTS %s (%s TEXT, %s INTEGER PRIMARY KEY, %s " +
                    "INTEGER, %s INTEGER, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, NAME_COL, ISSUE_COL, YEAR_COL,
                    TITLE_COL);

            statement.executeUpdate(createTableSQL);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }
    ArrayList<Vault> fetchAllRecords () {
// Get all records
        ArrayList<Vault> allRecords = new ArrayList<Vault>();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String selectAllSQL = "SELECT * FROM " + TABLE_NAME;
            ResultSet rsAll = statement.executeQuery(selectAllSQL);

            while (rsAll.next()) {
                String name = rsAll.getString(NAME_COL);
                int issue = rsAll.getInt(ISSUE_COL);
                int year = rsAll.getInt(YEAR_COL);
                String title = rsAll.getString(TITLE_COL);

                Vault vaultRecord = new Comic(name, issue, year, title);
                allRecords.add(vaultRecord);
            }

            rsAll.close();

            return allRecords;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }
// Add a comic
    String addRecord() {

        String addComicSQL = "INSERT INTO" + TABLE_NAME + " VALUES ( ? , ? , ? , ? )";
        final int SQLITE_CONSTRAINT_PRIMARY_KEY = 19;

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement addComicPs = conn.preparedStatement(addComicSQL)) {

            addComicPs.setString(1, comic.getName());
            addComicPs.setInt(2, comic.getIssue());
            addComicPs.setInt(3, comic.getYear());
            addComicPs.setString(4, comic.geTitle());

            addComicPs.execute();

            return OK;

        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == SQLITE_CONSTRAINT_PRIMARY_KEY) {
                return "Comic is already in the collection.";
            } else {
                throw new RuntimeException(sqle);
            }

        }
    }
// Delete a comic
    void delete(Vault comic) {

        String deleteSQL = "DELETE FROM" + TABLE_NAME + " VALUES ( ? , ? , ? , ? )";

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement deletePreparedStatement = conn.preparedStatement(deletSQL)) {

            while (deletePreparedStatement.next()) {
                String name = deletePreparedStatement.getName());
                int issue = deletePreparedStatement.getIssue());
                int year = deletePreparedStatement.getYear());
                String title = deletePreparedStatement..getTitle());

            deletePreparedStatement.execute();

        } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }

    }

}

}