package edu.gatech.buzzmovieselector.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.gatech.buzzmovieselector.dao.impl.ProfileDaoImpl;

import java.io.Serializable;

@DatabaseTable(tableName = "profile", daoClass = ProfileDaoImpl.class)
public class Profile implements Serializable {

    public static final String[] USER_DEGREES = {"", "Architecture",
                                                 "Building Construction",
                                                 "Industrial Design",
                                                 "Computer Science",
                                                 "Computational Media",
                                                 "Aerospace Engineering",
                                                 "Biomedical Engineering",
                                                 "Chemical Engineering",
                                                 "Civil Engineering",
                                                 "Computer Engineering",
                                                 "Electrical Engineering",
                                                 "Environmental Engineering",
                                                 "Industrial Engineering",
                                                 "Materials Science and " +
                                                     "Engineering",
                                                 "Mechanical Engineering",
                                                 "Nuclear Engineering",
                                                 "Business Administration",
                                                 "Applied Language and " +
                                                     "Intercultural " +
                                                     "Studies", "Economics",
                                                 "Economics and International" +
                                                     " Affairs",
                                                 "Global Economics and Modern" +
                                                     " Languages",
                                                 "History, Technology, and " +
                                                     "Society",
                                                 "International Affairs",
                                                 "International Affairs and " +
                                                     "Modern Languages",
                                                 "Literature, Media and " +
                                                     "Communication",
                                                 "Public Policy", "Applied " +
                                                     "Mathematics",
                                                 "Applied Physics",
                                                 "Biochemistry", "Biology",
                                                 "Chemistry", "Discrete " +
                                                     "Mathematics",
                                                 "Earth and Atmospheric " +
                                                     "Sciences", "Physics",
                                                 "Psychology", "Computational" +
                                                     " Science and " +
                                                     "Engineering"};

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private String major;
    @DatabaseField
    private String email;

    /**
     * Default constructor for OrmLite
     */
    public Profile() {
    }

    /**
     * Profile constructor
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param major the user's major
     * @param email the user's email
     */
    public Profile(String firstName, String lastName, String major, String
        email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.email = email;
    }

    /**
     * getter for id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for id
     * @param id to set to
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter
     * @return the firstName of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for first name
     * @param firstName the name to change to
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for last name
     * @return the last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for last name
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter for major
     * @return major the major to get
     */
    public String getMajor() {
        return major;
    }

    /**
     * setter for major
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * getter for email
     * @return email the email to get
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Profile: " + firstName + " " + lastName;
    }
}
