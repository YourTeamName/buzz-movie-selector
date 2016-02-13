package edu.gatech.buzzmovieselector.entity;

/**
 * Profile that holds information about a user
 */
public class Profile {

    /**
     * Enum that holds all possible degrees at Georgia Tech
     */
    public enum UserDegree {
        ARCHITECTURE,
        BUILDING_CONSTRUCTION,
        INDUSTRIAL_DESIGN,
        COMPUTER_SCIENCE,
        COMPUTATIONAL_MEDIA,
        AEROSPACE_E,
        BIOMEDICAL_E,
        CHEMICAL_E,
        CIVIL_E,
        COMPUTER_E,
        ELECTRICAL_E,
        ENVIRONMENTAL_E,
        INDUSTRIAL_E,
        MATERIALS_SCIENCE_E,
        MECHANICAL_E,
        NUCLEAR_E,
        BUSINESS_ADMIN,
        APPL_LANG_INTERCULTURAL,
        ECONOMICS,
        ECONOMICS_INTER_AFF,
        GLOBAL_ECONOMICS_MODERN_LANG,
        HISTORY_TECHNOLOGY_SOCIETY,
        INTER_AFF,
        INTER_AFF_MODERN_LANG,
        LIT_MEDIA_COMM,
        PUBLIC_POLICY,
        APPL_MATH,
        APPL_PHYSICS,
        BIOCHEMISTRY,
        BIOLOGY,
        CHEMISTRY,
        DISCRETE_MATH,
        EARTH_SCIENCE,
        PHYSICS,
        PSYCHOLOGY;

        @Override
        public String toString() {
            switch (this) {
                case ARCHITECTURE: return "Architecture";
                case BUILDING_CONSTRUCTION: return "Building Construction";
                case INDUSTRIAL_DESIGN: return "Industrial Design";
                case COMPUTER_SCIENCE: return "Computer Science";
                case COMPUTATIONAL_MEDIA: return "Computational Media";
                case AEROSPACE_E: return "Aerospace Engineering";
                case BIOMEDICAL_E: return "Biomedical Engineering";
                case CHEMICAL_E: return "Chemical Engineering";
                case CIVIL_E: return "Civil Engineering";
                case COMPUTER_E: return "Computer Engineering";
                case ELECTRICAL_E: return "Electrical Engineering";
                case ENVIRONMENTAL_E: return "Environmental Engineering";
                case INDUSTRIAL_E: return "Industrial Engineering";
                case MATERIALS_SCIENCE_E: return "Materials Science and Engineering";
                case MECHANICAL_E: return "Mechanical Engineering";
                case NUCLEAR_E: return "Nuclear Engineering";
                case BUSINESS_ADMIN: return "Business Administration";
                case APPL_LANG_INTERCULTURAL: return "APPL Language and Intercultural Studies";
                case ECONOMICS: return "Economics";
                case ECONOMICS_INTER_AFF: return "Economics and International Affairs";
                case GLOBAL_ECONOMICS_MODERN_LANG: return "Global Economics and Modern Languages";
                case HISTORY_TECHNOLOGY_SOCIETY: return "History, Technology, and Society";
                case INTER_AFF: return "International Affairs";
                case INTER_AFF_MODERN_LANG: return "International Affairs and Modern Languages";
                case LIT_MEDIA_COMM: return "Literature, Media, and Communication";
                case PUBLIC_POLICY: return "Public Policy";
                case APPL_MATH: return "APPL Mathematics";
                case APPL_PHYSICS: return "APPL Physics";
                case BIOCHEMISTRY: return "Biochemistry";
                case BIOLOGY: return "Biology";
                case CHEMISTRY: return "Chemistry";
                case DISCRETE_MATH: return "Discrete Mathematics";
                case EARTH_SCIENCE: return "Earth and Atmospheric Sciences";
                case PHYSICS: return "Physics";
                case PSYCHOLOGY: return "Psychology";
                default:
                    throw new IllegalArgumentException("Invalid UserDegree enum value");
            }
        }
    }

    private String firstName;
    private String lastName;
    private UserDegree major;
    private String email;

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

    public UserDegree getMajor() {
        return major;
    }

    public void setMajor(UserDegree major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
