package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.PortStoreDb;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.TwoDTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author David Magalhães 1201237
 */
public class Port2DTree extends TwoDTree<Port> {

    private final PortStoreDb portStoreDb;

    public Port2DTree() {
        super();
        portStoreDb = new PortStoreDb();
    }

    public void createdBalancedPort2DTree(List<Port> list) {
        root = port2DTreeBalanced(list, true);
    }

    private Node2D<Port> port2DTreeBalanced(List<Port> list, boolean divX) {
        if (list.isEmpty())
            return null;
        list.sort(divX ? cmpLat : cmpLon);
        Port port = list.get(list.size()/2);
        Node2D<Port> node = new Node2D<>(port, port.getLatitude(), port.getLongitude());
        ArrayList<Port> listLeft = new ArrayList<>(list.subList(0, list.size() / 2));
        ArrayList<Port> listRight = new ArrayList<>(list.subList(list.size() / 2 + 1, list.size()));
        node.setLeft(port2DTreeBalanced(listLeft, !divX));
        node.setRight(port2DTreeBalanced(listRight, !divX));
        return node;
    }

    protected final Comparator<Port> cmpLat = Comparator.comparingDouble(Port::getLatitude);

    protected final Comparator<Port> cmpLon = Comparator.comparingDouble(Port::getLongitude);

    public void loadPortsFromDatabase() throws SQLException {
        ArrayList<Port> ports = (ArrayList<Port>) portStoreDb.getAllPorts();

        createdBalancedPort2DTree(ports);
    }

    public void savePortsToDb() {
        savePortsToDb(root);
    }

    private void savePortsToDb(Node2D<Port> node) {
        if (node == null)
            return;
        savePortsToDb(node.getLeft());
        portStoreDb.save(App.getInstance().getSql().getDatabaseConnection(), node.getElement());
        savePortsToDb(node.getRight());
    }
}