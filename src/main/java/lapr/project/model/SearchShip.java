package lapr.project.model;

import lapr.project.controller.App;

import javax.naming.OperationNotSupportedException;

public class SearchShip {

    private App app;

    private Company company;

    private ShipBST ships;

    public SearchShip() {
        App app = App.getInstance();
        Company company = app.getCompany();
        ShipBST ships = company.getShips();
    }

    public Ship findShip(String codeType, String code) throws OperationNotSupportedException {
        Ship ship;

        if (codeType.equals("MMSI"))
            ship = findShipByMMSI(code);
        else if (codeType.equals("IMO"))
            ship = findShipByIMO(code);
        else
            ship = findShipByCallSign(code);

        return ship;
    }

    private Ship findShipByCallSign(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    private Ship findShipByIMO(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    private Ship findShipByMMSI(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
