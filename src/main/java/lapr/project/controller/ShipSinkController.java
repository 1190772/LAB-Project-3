package lapr.project.controller;

import static lapr.project.model.fsiap.ShipSink.calculateShipSink;
import static lapr.project.model.fsiap.ShipSink.pressure;


public class ShipSinkController {


    public double calculateShipSinkController(double mass,double ship_length,double ship_width,int n_containers,double density){
        double draftInitial = calculateShipSink(mass,ship_length,ship_width,0,density); //17552.65797
        double draftFinal = calculateShipSink(mass,ship_length,ship_width,n_containers,density); // //1.87722125
        double sink = Math.abs(draftFinal-draftInitial); // 17550.78075
    return sink;
    }

    public double pressureController(double mass, double length, double width, double heigth){
        return pressure(mass,length,width,heigth); //16565.49722
    }
}
