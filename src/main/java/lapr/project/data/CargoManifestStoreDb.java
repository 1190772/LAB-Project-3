package lapr.project.data;

import lapr.project.controller.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CargoManifestStoreDb {

    public ArrayList<String> getContainerPositions(int cargoManifestID) {
        ArrayList<String> containerPositions = new ArrayList<>();
        Connection connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();
        String positionCode;
        String containerID;

        String sqlCommand = "select id_container, position_code from Cargo_Manifest where id_cargo_manifest = ?";
        try (PreparedStatement cargoManifestPreparedStatement = connection.prepareStatement(sqlCommand)) {
            cargoManifestPreparedStatement.setInt(1, cargoManifestID);
            try (ResultSet cargoManifestResultSet = cargoManifestPreparedStatement.executeQuery()) {

                while (cargoManifestResultSet.next()) {
                    containerID = cargoManifestResultSet.getString("id_container");
                    positionCode = cargoManifestResultSet.getString("position_code");
                    while (positionCode.length() < 6)
                        positionCode = '0' + positionCode;
                    positionCode = positionCode.substring(0, 2) + ' ' + positionCode.substring(2,4) + ' ' + positionCode.substring(4);
                    containerPositions.add(positionCode + ' ' + containerID);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return containerPositions;
        }
}
