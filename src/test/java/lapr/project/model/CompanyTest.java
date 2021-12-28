package lapr.project.model;

import lapr.project.ui.auth.AuthFacade;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void freightNetwork() {
        FreightNetwork network = new FreightNetwork(new ArrayList<>(), new Long[1][1]);
        company.setFreightNetwork(network);
        assertEquals(network, company.getFreightNetwork());
    }
}