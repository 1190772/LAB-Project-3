package lapr.project.model;

import lapr.project.utils.AVL;

public class ShipBST extends AVL<Ship> {


    /**
     * Searches for a ship using the provided code.
     *
     * @param code the code.
     *
     * @return a ship if found or null otherwise.
     */
    /*
    public Ship findShip(String code) {
        String type = determineCodeType(code);
        Node<Ship> node = root;
        Ship res = null;
        boolean find = false;

        if (type.equals("IMO")) {
            while (node != null && !find) {
            if (node.getElement().getIMO().equals(code))
                find = true;
            else if (node.getElement().getIMO().compareTo(code) > 0)
                node = node.getLeft();
            else if (node.getElement().getIMO().compareTo(code) < 0)
                node = node.getRight();
            }
        }
        else if (type.equals("CallSign")) {
            while (node != null && !find) {
            if (node.getElement().getCallSign().equals(code))
                find = true;
            else if (node.getElement().compareTo(element) > 0)
                node = node.getLeft();
            else if (node.getElement().compareTo(element) < 0)
                node = node.getRight();
            }
        }
        else {
            while (node != null && !find) {
            if (node.getElement() == element)
                find = true;
            else if (node.getElement().compareTo(element) > 0)
                node = node.getLeft();
            else if (node.getElement().compareTo(element) < 0)
                node = node.getRight();
            }
        }

        if (find)
            res = node.getElement();

        return res;
    }

    private String determineCodeType(String code) {
        String type;

        if (code.startsWith("IMO"))
            type = "IMO";
        else if (code.length() == 5)
            type = "CallSign";
        else
            type = "MMSI";

        return type;
    }
*/
}
