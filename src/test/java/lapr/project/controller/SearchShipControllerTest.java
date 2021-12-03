package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchShipControllerTest {

    App app;
    Company company;
    SearchShipController controller;
    ShipBST shipBST;
    Ship ship;

    public SearchShipControllerTest() {
        app = App.getInstance();
        company = app.getCompany();
        ship = new Ship("210950000","VARAMO","IMO9395044",0,0,"C4SQ2",70,166,25,1,(float) 9.5);
        shipBST = company.getShips();
        shipBST.insert(ship);
        controller = new SearchShipController();
    }

    @Test
    void findShipTest() {
        Ship expected = ship;
        Ship actual = controller.findShip("IMO9395044");
        assertEquals(expected.toString(), actual.toString());
    }
}