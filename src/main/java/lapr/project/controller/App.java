package lapr.project.controller;

import lapr.project.model.Company;

public class App {
    private Company company;
    private static App singleton = null;

    private App() {
        company = new Company("Designation");
        //bootstrap();
    }

    //private void bootstrap() {}

    public static App getInstance() {
        if(singleton == null) {
            synchronized(App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    public Company getCompany()
    {
    return this.company;
    }
}
