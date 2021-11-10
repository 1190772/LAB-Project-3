package lapr.project.ui.auth.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Email {

    private final String emailUser;

    public Email(String emailUser) {
        if (!validate(emailUser))
            throw new IllegalArgumentException("Invalid Email Address.");
        this.emailUser = emailUser;
    }

    private boolean validate(String email) {
        if (email.length() == 0)
            return false;
        // Check for other invalid criteria here

        //
        return checkFormat(email);
    }

    // Extracted from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    public static boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public String getEmailUser() {
        return emailUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.emailUser.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Email obj = (Email) o;
        return Objects.equals(this.emailUser, obj.emailUser);
    }

    @Override
    public String toString() {
        return String.format("%s", this.emailUser);
    }
}
