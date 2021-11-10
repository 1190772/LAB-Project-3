package lapr.project.ui.auth.model;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Password implements Serializable {

    private final String passwordUser;

    public Password(String passwordUser) {
        if (!validate(passwordUser))
            throw new IllegalArgumentException("Invalid Email Address.");
        this.passwordUser = createHash(passwordUser);
    }

    private boolean validate(String password) {
        if (password.length() == 0)
            return false;
        // Check for other invalid criteria here

        //
        return true;
    }

    private String createHash(String password) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password.toCharArray());
    }

    public boolean checkPassword(String pwd) {
        if (pwd.length() == 0)
            return false;
        BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(), this.passwordUser.toCharArray());
        return result.verified;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 7 * hash + this.passwordUser.hashCode();
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
        Password obj = (Password) o;
        return Objects.equals(this.passwordUser, obj.passwordUser);
    }
}
