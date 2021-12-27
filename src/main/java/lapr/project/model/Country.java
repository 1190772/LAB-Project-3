package lapr.project.model;

public class Country {

    String alpha2_code;
    String alpha3_code;
    String name;
    String capital;
    String continent;
    double population;
    double latitude;
    double longitude;

    public Country(String alpha2_code, String alpha3_code, String name, String capital, String continent, double population, double latitude, double longitude) {
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAlpha2_code() { return alpha2_code; }

    public String getAlpha3_code() { return alpha3_code; }

    public String getName() { return name; }

    public String getCapital() { return capital; }

    public String getContinent() { return continent; }

    public double getPopulation() { return population; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}
