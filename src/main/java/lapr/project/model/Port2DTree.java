package lapr.project.model;

import lapr.project.utils.TwoDTree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author David Magalhães 1201237
 */
public class Port2DTree extends TwoDTree<Port> {

    public void createdBalancedPort2DTree(ArrayList<Port> list) {
        root = port2DTreeBalanced(list, true);
    }

    private Node2D<Port> port2DTreeBalanced(ArrayList<Port> list, boolean divX) {
        if (list.isEmpty())
            return null;
        list.sort(divX ? cmpX : cmpY);
        Port port = list.get(list.size()/2);
        Node2D<Port> node = new Node2D<>(port, port.getLatitude(), port.getLongitude());
        ArrayList<Port> listLeft = new ArrayList<>(list.subList(0, list.size() / 2));
        ArrayList<Port> listRight = new ArrayList<>(list.subList(list.size() / 2 + 1, list.size()));
        node.setLeft(port2DTreeBalanced(listLeft, !divX));
        node.setRight(port2DTreeBalanced(listRight, !divX));
        return node;
    }

    protected final Comparator<Port> cmpX = Comparator.comparingDouble(Port::getLatitude);

    protected final Comparator<Port> cmpY = Comparator.comparingDouble(Port::getLongitude);
}