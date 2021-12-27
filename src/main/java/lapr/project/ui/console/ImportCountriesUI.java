package lapr.project.ui.console;

import lapr.project.controller.ImportCountriesController;

public class ImportCountriesUI implements Runnable{

    private final ImportCountriesController controller;

    public ImportCountriesUI() {
    controller = new ImportCountriesController();
    }

    @Override
    public void run() {
        controller.importCountries("Data-Sprint3-4/countries.csv");
        controller.saveCountriesToDb();
    }
}
