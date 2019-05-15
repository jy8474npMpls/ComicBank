// Much of my project code has been cobbled/learned from previous labs and projects

public class Vault {

    // Designate variable names
    private String name;
    private int issue;
    private int year;
    private String title;

    Vault(String n, int i, int y, String t, int s) {
        name = n;
        issue = i;
        year = y;
        title = t;

    }

    // populate data
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getIssue() {
        return issue;
    }
    public void setIssue(int issue) {this.issue = issue; }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {this.year = year; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Display comic details
    @Override
    public String toString() {return "Comic:" + name + "Issue No: " + issue + "Year: " + year + "Title: " + title; }
}
