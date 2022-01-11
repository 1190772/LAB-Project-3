package lapr.project.controller;

import org.junit.jupiter.api.Test;


public class ImportFromFilesTest {
    @Test
    void bordersTest() {
        new ImportBordersController().importBorders("Data-Sprint3-4/borders.csv");
    }

    @Test
    void countriesTest() {
        new ImportCountriesController().importCountries("Data-Sprint3-4/countries.csv");
    }

    @Test
    void seaDistancesTest() {
        new ImportSeaDistancesController().importSeaDistances("Data-Sprint3-4/seadists.csv");
    }
}
