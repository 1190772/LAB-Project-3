package lapr.project.model.fsiap;

public class ShipSink {

    public static double calculateShipSink(double mass,double ship_length,double ship_width,int n_containers,double density){
       double volume = mass+determineTotalMassPlacedOnTheVessel(n_containers) / density; //17562.68806
       double ship_heigth= volume /(ship_length * ship_width); //1.87722125
       return ship_heigth;
    }

    public static double determineTotalMassPlacedOnTheVessel(int n_containers){
        double containerMass = 500;
        double totalContainerMass = containerMass * n_containers;
        return totalContainerMass;
    }

    public static double pressure(double mass, double length, double width, double heigth){
        double g = 10;
        double pressure = (mass*g)/calculateArea(length,width,heigth);//16565.49722
        return pressure;
    }

    public static double calculateArea(double length, double width, double heigth){
        double area = length * width + 2 * heigth * width + 2 * heigth * length; //10564.12601
        return area;
    }






}
