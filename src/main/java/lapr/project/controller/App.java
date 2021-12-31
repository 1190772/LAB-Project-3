package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.shared.Constants;
import lapr.project.model.shared.SQL;
import lapr.project.ui.auth.AuthFacade;
import lapr.project.ui.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    private final Company company;
    private static App singleton = null;
    private final AuthFacade authFacade;
    private final SQL sql;

    private App() {
        Properties properties = getProperties();
        this.company = new Company(properties.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = new AuthFacade();
        sql = new SQL(Constants.DATABASE_USERNAME, Constants.DATABASE_THE_OTHER_ONE);
        bootstrap();
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN, Constants.ROLE_SHIP_CAPTAIN);

        this.authFacade.addUserWithRole("Traffic Manager", "manager@lei.sem1.pt", Constants.THE_OTHER_ONE, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserWithRole("Port Manager", "portmanager@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserWithRole("Ship Captain", "captain@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_SHIP_CAPTAIN);
    }

    private Properties getProperties() {
        Properties props = new Properties();

        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Companhia");


        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ignored) {}
        return props;
    }

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    public Company getCompany() {
        return this.company;
    }

    public SQL getSql() { return sql; }
}
