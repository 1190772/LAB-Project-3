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

    public String getCapital() { return capital; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}
