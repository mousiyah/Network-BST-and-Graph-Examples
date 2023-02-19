import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Profile.java
 * @version 1.0.0
 * @author musslimaz
 */

public class Profile {

    // Personal information
    private String familyNames;
    private String givenNames;

    // Date of PhD award
    private int yearPhD;
    private int monthPhD;
    private int dayPhD;

    // Contacts
    private String email;

    // Research interests
    private ArrayList<String> researchInts;

    // Collaborations
    public ArrayList<Profile> collaborators = new ArrayList<>();

    /**
     * Create a new profile.
     * @param familyName Family name of the researcher.
     * @param givenName Given name of the researcher.
     * @param yearPhD Year of PhD award.
     * @param monthPhD Month of PhD award.
     * @param dayPhD Day of PhD award.
     * @param emailAddress Email address.
     */
    public Profile(String familyName, String givenName, int yearPhD, int monthPhD, int dayPhD, String emailAddress, ArrayList<String> researchInts) {
        this.familyNames = familyName;
        this.givenNames = givenName;
        this.yearPhD = yearPhD;
        this.monthPhD = monthPhD;
        this.dayPhD = dayPhD;
        this.email = emailAddress;
        this.researchInts = researchInts;
    }

    /**
     * @return Family name of researcher.
     */
    public String getFamilyNames() {
        return familyNames;
    }

    /**
     * @param familyNames Family name of researcher.
     */
    public void setFamilyNames(String familyNames) {
        this.familyNames = familyNames;
    }

    /**
     * @return Given name of researcher.
     */
    public String getGivenNames() {
        return givenNames;
    }

    /**
     * @param givenNames Given name of researcher.
     */
    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    /**
     * @return PhD award year.
     */
    public int getYearPhD() {
        return yearPhD;
    }

    /**
     * @param yearPhD PhD award year.
     */
    public void setYearPhD(int yearPhD) {
        this.yearPhD = yearPhD;
    }

    /**
     * @return PhD award month.
     */
    public int getMonthPhD() {
        return monthPhD;
    }

    /**
     * @param monthPhD PhD award month.
     */
    public void setMonthPhD(int monthPhD) {
        this.monthPhD = monthPhD;
    }

    /**
     * @return PhD award day.
     */
    public int getDayPhD() {
        return dayPhD;
    }

    /**
     * @param dayPhD PhD award day.
     */
    public void setDayPhD(int dayPhD) {
        this.dayPhD = dayPhD;
    }

    /**
     * @return Email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ArrayList of research interests.
     */
    public ArrayList<String> getResearchInts() {
        return researchInts;
    }

    /**
     * @param researchInts ArrayList of research interests.
     */
    public void setResearchInts(ArrayList<String> researchInts) {
        this.researchInts = researchInts;
    }

    /**
     * @return Date of PhD award in yyyy-mm-dd format.
     */
    public String getDateOfPhD() {
            return new DecimalFormat("0000").format(yearPhD)
                    + "-" + new DecimalFormat("00").format(monthPhD)
                    + "-" + new DecimalFormat("00").format(dayPhD);
    }

    /**
     * Adds a researcher to collaborations.
     * @param p Another researcher.
     */
    public void collaborate(Profile p) {
        collaborators.add(p);
        p.collaborators.add(this);
    }

    /**
     * Indicates whether a researcher has collaborated with a specified other
     * @param p Another researcher
     * @return true if this researcher collaborated with the other one,
     * false otherwise.
     */
    public boolean hasCollaboratedWith(Profile p) {
        return collaborators.contains(p);
    }

    /**
     * Return the number of collaborations
     * @return the number of researchers for this profile
     */
    public int numOfCollabs() {
        return collaborators.size();
    }

    /**
     * Convert the profile to string
     * @return string
     */
    @Override
    public String toString() {
        String str = familyNames + " " + givenNames
                + "\nPhD date: " + getDateOfPhD()
                + "\nEmail: " + email
                + "\nResearch interests: " + String.join(", ", researchInts)
                + "\nNumOfCollabs: " + numOfCollabs()
                + "\nCollaborations: ";

        for(Profile p: collaborators){
            str += "\n" + p.getFamilyNames() + " " + p.getGivenNames();
        }

        str += "\n";

        return str;
    }
}
