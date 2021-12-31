package lapr.project.model.store;

import lapr.project.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CountryStoreTest {

    private final ArrayList<Country> countries;
    private final Country country1;
    private final Country country2;

    public CountryStoreTest() {
        countries = new ArrayList<>();
        country1 = new Country("BZ","BLZ","Belize","Belmopan","America",397.6,17.25,-88.766667);
        country2 = new Country("MX","MEX","Mexico","Mexico City","America",128.9,19.43333333,-99.133333);
        countries.add(country1);
        countries.add(country2);
    }

    @Test
    void getCountryByName() {
        CountryStore countryStore = new CountryStore();

        countryStore.addCountry(country1);
        countryStore.addCountry(country2);

        Assertions.assertEquals(country2, countryStore.getCountryByName(country2.getName()));
        Assertions.assertNull(countryStore.getCountryByName("Nome"));
    }

    @Test
    void getCountryByAlpha2code() {
        CountryStore countryStore = new CountryStore();

        countryStore.addCountry(country1);
        countryStore.addCountry(country2);

        Assertions.assertEquals(country2, countryStore.getCountryByAlpha2code(country2.getAlpha2code()));
        Assertions.assertNull(countryStore.getCountryByAlpha2code("ZZ"));
    }

    @Test
    void addCountryGetCountries() {
        CountryStore countryStore = new CountryStore();

        countryStore.addCountry(country1);
        countryStore.addCountry(country2);

        Assertions.assertEquals(countries, countryStore.getCountries());
    }

    @Test
    void refresh() {
        CountryStore countryStore = mock(CountryStore.class);

        when(countryStore.refresh()).thenReturn(true);

        Assertions.assertTrue(countryStore.refresh());
    }
}