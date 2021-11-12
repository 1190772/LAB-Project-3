package lapr.project.model;

import lapr.project.ui.auth.AuthFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

    Company company;
    final AuthFacade authFacade;


    public CompanyTest() {
        company = new Company("Designation");
        this.authFacade = company.getAuthFacade();
    }

    @Test
    void getDesignation() {
        String expected = "Designation";
        String actual = company.getDesignation();
        assertEquals(expected, actual);
    }

    @Test
    void getAuthFacade() {
        assertEquals(authFacade, company.getAuthFacade());
    }
}