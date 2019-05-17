// Much of my project code has been cobbled/learned from previous labs and projects


// Perhaps this should be renamed "Comic" ? This class represents one Comic?

//public class Vault {
public class Comic {
    
    // Designate variable names
    private String name;
    private int issue;
    private int year;
    private String title;

    // Do you need the s parameter?
    // descriptive parameter names are really helpful
    Comic(String name, int issue, int year, String title /*, int s*/) {
//    Vault(String n, int i, int y, String t, int s) {
        this.name = name;
        this.issue = issue;
        this.year = year;
        this.title = title;

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
