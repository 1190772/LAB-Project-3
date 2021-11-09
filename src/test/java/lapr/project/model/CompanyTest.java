package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company company;

    public CompanyTest() {
        company = new Company("Designation");
    }

    @Test
    void getDesignation() {
        String expected = "Designation";
        String actual = company.getDesignation();
        Assert.assertEquals(expected, actual);
    }
}