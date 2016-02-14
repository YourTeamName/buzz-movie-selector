package edu.gatech.buzzmovieselector.entity;

/**
 * ProfileActivity that holds information about a user
 */
public class Profile {

    public static final String[] USER_DEGREES = { "", "Architecture", "Building Construction",
            "Industrial Design", "Computer Science", "Computational Media", "Aerospace Engineering",
            "Biomedical Engineering", "Chemical Engineering", "Civil Engineering",
            "Computer Engineering", "Electrical Engineering", "Environmental Engineering",
            "Industrial Engineering", "Materials Science and Engineering", "Mechanical Engineering",
            "Nuclear Engineering", "Business Administration",
            "Applied Language and Intercultural Studies", "Economics",
            "Economics and International Affairs", "Global Economics and Modern Languages",
            "History, Technology, and Society", "International Affairs",
            "International Affairs and Modern Languages", "Literature, Media, and Communication",
            "Public Policy", "Applied Mathematics", "Applied Physics", "Biochemistry", "Biology",
            "Chemistry", "Discrete Mathematics", "Earth and Atmospheric Sciences",
            "Physics", "Psychology"};

    private String firstName;
    private String lastName;
    private String major;
    private String email;

    public Profile(String firstName, String lastName, String major, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.email = email;
    }

    public Profile() {
        this("", "", "", "");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
