package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.shared.Constants;
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

    private App() {
        Properties properties = getProperties();
        this.company = new Company(properties.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = new AuthFacade();
        bootstrap();
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN, Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserRole(Constants.ROLE_PORT_STAFF, Constants.ROLE_PORT_STAFF);
        this.authFacade.addUserRole(Constants.ROLE_CREW, Constants.ROLE_CREW);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CHIEF_ELECTRICAL_ENGINEER, Constants.ROLE_SHIP_CHIEF_ELECTRICAL_ENGINEER);
        this.authFacade.addUserRole(Constants.ROLE_FLEET_MANAGER, Constants.ROLE_FLEET_MANAGER);

        this.authFacade.addUserWithRole("Traffic Manager", "manager@lei.sem1.pt", Constants.THE_OTHER_ONE, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserWithRole("Port Manager", "portmanager@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserWithRole("Ship Captain", "captain@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserWithRole("Port Staff", "portstaff@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_PORT_STAFF);
        this.authFacade.addUserWithRole("Crew", "crew@lei.pt", "bd7wd5aF", Constants.ROLE_CREW);
        this.authFacade.addUserWithRole("Ship Chief Electrical Engineer", "electrical@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_SHIP_CHIEF_ELECTRICAL_ENGINEER);
        this.authFacade.addUserWithRole("Fleet Manager", "fleetmanager@lei.pt", Constants.THE_OTHER_ONE, Constants.ROLE_FLEET_MANAGER);
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
}
