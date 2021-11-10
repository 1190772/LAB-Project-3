package lapr.project.ui.auth;

import lapr.project.ui.auth.model.User;
import lapr.project.ui.auth.model.UserRole;
import lapr.project.ui.auth.store.UserRoleStore;
import lapr.project.ui.auth.store.UserStore;

import java.util.Optional;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthFacade {
    private UserSession userSession;

    private UserRoleStore roles;
    private UserStore users;

    public AuthFacade() {
        this.userSession = new UserSession();
        this.roles = new UserRoleStore();
        this.users = new UserStore();
    }

    public AuthFacade(UserStore userStore) {
        this.userSession = new UserSession();
        this.roles = new UserRoleStore();
        this.users = userStore;
    }


    public boolean addUserRole(String id, String description) {
        UserRole role = this.roles.create(id, description);
        return this.roles.add(role);
    }

    public boolean addUser(String name, String email, String pwd) {
        User user = this.users.create(name, email, pwd);
        return this.users.add(user);
    }

    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        Optional<UserRole> roleResult = this.roles.getById(roleId);
        if (!roleResult.isPresent())
            return false;

        User user = this.users.create(name, email, pwd);
        user.addRole(roleResult.get());
        return this.users.add(user);
    }

    public boolean addUserWithRoles(String name, String email, String pwd, String[] rolesId) {
        User user = this.users.create(name, email, pwd);
        for (String roleId : rolesId) {
            Optional<UserRole> roleResult = this.roles.getById(roleId);
            if (roleResult.isPresent())
                user.addRole(roleResult.get());
        }

        return this.users.add(user);
    }

    public boolean existsUser(String email) {
        return this.users.exists(email);
    }

    public UserSession doLogin(String email, String password) {
        Optional<User> result = this.users.getById(email);
        if (result.isPresent()) {
            User user = result.get();
            if (user.hasPassword(password)) {
                this.userSession = new UserSession(user);
            }
        }
        return this.userSession;
    }

    public void doLogout() {
        this.userSession.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return this.userSession;
    }

}
