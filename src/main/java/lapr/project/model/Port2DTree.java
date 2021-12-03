package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.PortStoreDb;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.TwoDTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author David Magalhães 1201237
 */
public class Port2DTree extends TwoDTree<Port> {

    private PortStoreDb portStoreDb;

    public Port2DTree() {
        super();
        portStoreDb = new PortStoreDb();
    }

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

    public void loadPortsFromDatabase() throws SQLException {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        ResultSet ports = portStoreDb.getAllPorts(connection);
        ArrayList<Port> list = new ArrayList<>();
        while (ports.next()) {
        Port port = new Port(ports.getInt("id_port"),
                ports.getString("name"),
                ports.getString("continent"),
                ports.getString("country"),
                ports.getDouble("latitude"),
                ports.getDouble("longitude"));
        list.add(port);
        }
        ports.close();
        createdBalancedPort2DTree(list);
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