package lapr.project.data;

import lapr.project.controller.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoManifestStoreDb {

    public List<String> getContainerPositions(int cargoManifestID) {
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
                    positionCode = positionCode.substring(0, 2) + ' ' + positionCode.substring(2, 4) + ' ' + positionCode.substring(4);
                    containerPositions.add(positionCode + ' ' + containerID);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return containerPositions;
    }

    public List<String> getContainerInfo(int cargoManifestID) {
        ArrayList<String> containerInfo = new ArrayList<>();
        Connection connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();
        StringBuilder positionCode;
        StringBuilder s;

        String sqlCommand = "select cm.id_container, cm.position_code, cm.cargo_weight, cm.refrigeration_temperature, l.value_length, wh.value_width, wh.value_height " +
                "from Cargo_Manifest cm, Container c, ISO iso, Container_Length l, Width_Height wh  " +
                "where id_cargo_manifest = " + cargoManifestID + " and cm.id_container = c.id_container and c.iso_code = iso.iso_code and iso.length_code = l.length_code and iso.width_height_code = wh.width_height_code " +
                "order by cm.position_code";
        try (PreparedStatement cargoManifestPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet cargoManifestResultSet = cargoManifestPreparedStatement.executeQuery()) {
                while (cargoManifestResultSet.next()) {
                    s = new StringBuilder();
                    s.append(cargoManifestResultSet.getString(1)).append(" ");
                    positionCode = new StringBuilder(cargoManifestResultSet.getString(2));
                    while (positionCode.length() < 6)
                        positionCode.insert(0, '0');
                    s.append(positionCode.toString(), 0, 2).append(' ').append(positionCode.toString(), 2, 4).append(' ').append(positionCode.toString(), 4, 6);

                    s.append(" ").append(cargoManifestResultSet.getString(3));
                    s.append(" ").append(cargoManifestResultSet.getInt(4));

                    for (int i = 5; i<=7; i++)
                    s.append(" ").append(cargoManifestResultSet.getDouble(i)/100);


                    containerInfo.add(s.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return containerInfo;
    }
}
