package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CountryTest {

    private final String alpha2code = "al2";
    private final String alpha3code = "al3";
    private final String name = "name";
    private final String capital = "cap";
    private final String continent = "con";
    private final double population = 1;
    private final double latitude = 2;
    private final double longitude = 2;

    @Test
    void getsTest() {
        Country country = new Country(alpha2code, alpha3code, name, capital, continent, population, latitude, longitude);
        assertEquals(alpha2code, country.getAlpha2code());
        assertEquals(alpha3code, country.getAlpha3code());
        assertEquals(name, country.getName());
        assertEquals(capital, country.getCapital());
        assertEquals(continent, country.getContinent());
        assertEquals(population, country.getPopulation());
        assertEquals(latitude, country.getLatitude());
        assertEquals(longitude, country.getLongitude());
        assertEquals("Country = cap", country.toString());
    }

}
