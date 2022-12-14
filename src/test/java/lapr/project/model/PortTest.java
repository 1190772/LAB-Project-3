package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortTest {
    Port port;
    String id= "29002";
    String name="Liverpool";
    //String continent="Europe";
    Country country=new Country("UK", null, "United Kingdom", "London", "Europe", 0, 0, 0);
    double latitude=53.46666667;
    double longitude=-3.033333333;
    String toString = "Port{id='29002', name='Liverpool', country=Country{alpha2code='UK', alpha3code='null', name='United Kingdom', capital='London', continent='Europe', population=0.0, latitude=0.0, longitude=0.0}, latitude=53.46666667, longitude=-3.033333333, capacity=0}";

    public PortTest(){
        port = new Port(id, name, country, latitude, longitude, 0);
    }

    @Test
    public void getID(){
        Assertions.assertEquals( id, port.getID());
    }

    @Test
    public void getName(){
        Assertions.assertEquals( name, port.getName());
    }

    @Test
    public void getCountry(){
        Assertions.assertEquals( country, port.getCountry());
    }

    @Test
    public void getLatitude(){
        Assertions.assertEquals( latitude, port.getLatitude());
    }

    @Test
    public void getLongitude(){
        Assertions.assertEquals( longitude, port.getLongitude());
    }

    @Test
    public void toStringTest(){
        Assertions.assertEquals( toString, port.toString());
    }
}
