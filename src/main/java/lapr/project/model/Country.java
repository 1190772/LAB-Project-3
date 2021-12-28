package lapr.project.model;

public class Country {

    String alpha2code;
    String alpha3code;
    String name;
    String capital;
    String continent;
    double population;
    double latitude;
    double longitude;

    public Country(String alpha2code, String alpha3code, String name, String capital, String continent, double population, double latitude, double longitude) {
        this.alpha2code = alpha2code;
        this.alpha3code = alpha3code;
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAlpha2code() { return alpha2code; }

    public String getAlpha3code() { return alpha3code; }

    public String getName() { return name; }

    public String getCapital() { return capital; }

    public String getContinent() { return continent; }

    public double getPopulation() { return population; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    @Override
    public String toString() {
        return "Country = " + capital;
    }
}
