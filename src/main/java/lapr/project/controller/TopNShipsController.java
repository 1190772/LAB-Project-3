package lapr.project.controller;

import lapr.project.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TopNShipsController {


    private final ShipBST shipBST;


    public TopNShipsController() {
        shipBST = App.getInstance().getCompany().getShips();
    }

    public ArrayList<Ship>[] topNShips(int n, LocalDateTime start, LocalDateTime end){
        return shipBST.topNShips(n,start,end);
    }
}
