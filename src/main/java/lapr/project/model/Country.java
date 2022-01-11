package lapr.project.model;

public class Country implements FreightNetworkVertex {

    private final String alpha2code;
    private final String alpha3code;
    private final String name;
    private final String capital;
    private final String continent;
    private final double population;
    private final double latitude;
    private final double longitude;

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
        return "Country{" +
            "alpha2code='" + alpha2code + '\'' +
            ", alpha3code='" + alpha3code + '\'' +
            ", name='" + name + '\'' +
            ", capital='" + capital + '\'' +
            ", continent='" + continent + '\'' +
            ", population=" + population +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }

@Override
    public String getVertexName() {
        return "Country = " + capital;
    }
}
