package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static lapr.project.model.fsiap.ShipSink.calculateShipSink;
import static lapr.project.model.fsiap.ShipSink.pressure;

public class ShipSInkTest {

    //@Test
    void shipSinkControllerTest(){
        double expected = 17550.78075;
        double mass = 17500000;
        double ship_length = 289.56;
        double ship_width = 32.31;
        int n_containers = 20;
        double density = 997;
        double value;
        value = calculateShipSink(mass,ship_length,ship_width,n_containers,density);
        Assertions.assertEquals(expected,value,0.5);
    }

    //@Test
    void pressureControllerTest(){
        double expected = 16565.49722;
        double mass = 17500000;
        double length = 289.56;
        double width = 32.31;
        double heigth = calculateShipSink(mass,length,width,20,997);
        double value;
        value = pressure(mass,length,width,heigth);
        Assertions.assertEquals(expected,value,0.5);
    }
}
