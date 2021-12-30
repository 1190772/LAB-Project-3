package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.ContainerOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContainerOperationStoreDb {

    public ArrayList<ContainerOperation> getContainerOperationsByCargoManifestID(int cargoManifestID) {
        ArrayList<ContainerOperation> containerOperations = new ArrayList<>();
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();

        // get container operations of the chosen Cargo Manifest
        String sqlCommand = "select * from Container_Operation where id_cargo_manifest = ?";
        try (PreparedStatement containerOperationsPreparedStatement = connection.prepareStatement(sqlCommand)) {
            containerOperationsPreparedStatement.setInt(1, cargoManifestID);
            try (ResultSet containerOperationsResultSet = containerOperationsPreparedStatement.executeQuery()) {

                while (containerOperationsResultSet.next()) {

                    // get the type of operation based on its code
                    sqlCommand = "select description from Type_Operation where code = ?";
                    try (PreparedStatement operationTypePreparedStatement = connection.prepareStatement(sqlCommand)) {
                        operationTypePreparedStatement.setString(1, containerOperationsResultSet.getString("type_operation"));
                        try (ResultSet operationTypeResultSet = operationTypePreparedStatement.executeQuery()) {
                            if (operationTypeResultSet.next()) {
                                String operationType = operationTypeResultSet.getString("description");

                                // create Container Operation and add it to the result list
                                containerOperations.add(new ContainerOperation(containerOperationsResultSet.getString("employee"),
                                        containerOperationsResultSet.getTimestamp("base_date_time").toLocalDateTime(),
                                        operationType,
                                        containerOperationsResultSet.getString("id_container"),
                                        cargoManifestID));
                            }
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return containerOperations;
    }
}
