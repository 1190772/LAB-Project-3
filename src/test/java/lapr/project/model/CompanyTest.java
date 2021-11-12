package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

    Company company;

    public CompanyTest() {
        company = new Company("Designation");
    }

    @Test
    void getDesignation() {
        String expected = "Designation";
        String actual = company.getDesignation();
        assertEquals(expected, actual);
    }
}