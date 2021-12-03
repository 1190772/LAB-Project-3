package lapr.project.data;

import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipStoreDb implements Persistable{

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Ship ship = (Ship) object;
        boolean returnValue;

        try {
            saveShipToDatabase(databaseConnection, ship);

            deleteShipPositions(databaseConnection, ship);

            addShipPositions(databaseConnection, ship);

            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(ShipStoreDb.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        boolean returnValue;
        Connection connection = databaseConnection.getConnection();
        Ship ship = (Ship) object;

        try {
            String sqlCommand;
            sqlCommand = "delete from Position_Ship where id_ship = ?";
            try (PreparedStatement deleteShipPositionsPreparedStatement = connection.prepareStatement(sqlCommand)) {
                deleteShipPositionsPreparedStatement.setString(1, ship.getIMO());
                deleteShipPositionsPreparedStatement.executeUpdate();
            }

            sqlCommand = "delete from Ship where imo_code = ?";
            try (PreparedStatement deleteShipPreparedStatement = connection.prepareStatement(
                    sqlCommand)) {
                deleteShipPreparedStatement.setString(1, ship.getIMO());
                deleteShipPreparedStatement.executeUpdate();
            }

            returnValue = true;

        } catch (SQLException exception) {
            Logger.getLogger(ShipStoreDb.class.getName())
                    .log(Level.SEVERE, null, exception);
            databaseConnection
                    .registerError(exception);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Checks is a ship is already registered on the database. If the ship
     * is registered, it updates it. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void saveShipToDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {

        if (isShipOnDatabase(databaseConnection, ship)) {
            updateShipOnDatabase(databaseConnection, ship);
        } else {
            insertShipOnDatabase(databaseConnection, ship);
        }

    }

    /**
     * Checks if a ship is registered on the Database by its IMO code.
     *
     * @param databaseConnection
     * @param ship
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException
     */
    private boolean isShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isShipOnDatabase;

        String sqlCommand = "select * from ship where imo_code = ?";

        try (PreparedStatement getShipsPreparedStatement = connection.prepareStatement(sqlCommand)) {
            getShipsPreparedStatement.setString(1, ship.getIMO());

            try (ResultSet shipsResultSet = getShipsPreparedStatement.executeQuery()) {

                if (shipsResultSet.next()) {
                    // if ship already exists in the database
                    isShipOnDatabase = true;
                } else {
                    // if ship does not exist in the database
                    isShipOnDatabase = false;
                }
            }
        }


        return isShipOnDatabase;
    }

    /**
     * Adds a new ship record to the database.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void insertShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "insert into Ship(mmsi_code, name_ship, number_generators, power_out_generator, call_sign, vessel_type, length_ship, width_ship, draft, capacity_ship, imo_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipStatementOnDatabase(databaseConnection, ship, sqlCommand);
    }

    /**
     * Updates an existing ship record on the database.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void updateShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "update Ship set mmsi_code = ?, name_ship = ?, number_generators = ?, power_out_generator = ?, call_sign = ?, vessel_type = ?, length_ship = ?, width_ship = ?, draft = ?, capacity_ship = ? where imo_code = ?";

        executeShipStatementOnDatabase(databaseConnection, ship,  sqlCommand);
    }

    /**
     * Executes the save Ship Statement.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void executeShipStatementOnDatabase(DatabaseConnection databaseConnection, Ship ship, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement saveShipPreparedStatement = connection.prepareStatement(sqlCommand)) {
            saveShipPreparedStatement.setString(1, ship.getMMSI());
            saveShipPreparedStatement.setString(2, ship.getName());
            saveShipPreparedStatement.setInt(3, ship.getNumberEnergyGenerators());
            saveShipPreparedStatement.setInt(4, ship.getGeneratorPowerOutput());
            saveShipPreparedStatement.setString(5, ship.getCallSign());
            saveShipPreparedStatement.setInt(6, ship.getVesselType());
            saveShipPreparedStatement.setInt(7, ship.getLength());
            saveShipPreparedStatement.setInt(8, ship.getWidth());
            saveShipPreparedStatement.setDouble(9, ship.getDraft());
            saveShipPreparedStatement.setDouble(10, ship.getCapacity());
            saveShipPreparedStatement.setString(11, ship.getIMO());
            saveShipPreparedStatement.executeUpdate();
        }
    }

    private void deleteShipPositions(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand;

        sqlCommand = "select * from Position_Ship where id_ship = ?";
        try (PreparedStatement getShipPositionsPreparedStatement = connection.prepareStatement(sqlCommand)) {
            getShipPositionsPreparedStatement.setString(1, ship.getIMO());
            try (ResultSet shipPositionsResultSet = getShipPositionsPreparedStatement.executeQuery()) {
                while (shipPositionsResultSet.next()) {
                boolean found = false;
                LocalDateTime positionDateTime = shipPositionsResultSet.getTimestamp("base_date_time").toLocalDateTime();

                ShipPosition position = ship.getPosition().findPosition(positionDateTime);

                if (position != null) {
                    found = true;
                }
                if (!found) {
                    sqlCommand = "delete from Position_Ship where id_ship = ? and base_date_time = ?";

                    try (PreparedStatement shipPositionDeletePreparedStatement = connection.prepareStatement(sqlCommand)) {
                        shipPositionDeletePreparedStatement.setString(1, ship.getIMO());
                        shipPositionDeletePreparedStatement.setTimestamp(2, shipPositionsResultSet.getTimestamp("base_date_time"));
                        getShipPositionsPreparedStatement.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    private void addShipPositions(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand;

        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
        for (int i = 0; i < list.size(); i++) {
            ShipPosition position = list.get(i);

            sqlCommand = "select * from Position_Ship where id_ship = ? and base_date_time = ?";
            try (PreparedStatement shipPositionsPreparedStatement = connection.prepareStatement(sqlCommand)) {
                shipPositionsPreparedStatement.setString(1, ship.getIMO());
                shipPositionsPreparedStatement.setTimestamp(2, Timestamp.valueOf(position.getBaseDateTime()));

            try (ResultSet shipPositionsResultSet = shipPositionsPreparedStatement.executeQuery()) {
                if (!shipPositionsResultSet.next()) {
                    sqlCommand = "insert into Position_Ship(id_ship, base_date_time, latitude, longitude, sog, cog, heading, transceiver_class, cargo) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertShipPositionsPreparedStatement = connection.prepareStatement(sqlCommand)) {
                        insertShipPositionsPreparedStatement.setString(1, ship.getIMO());
                        insertShipPositionsPreparedStatement.setTimestamp(2, Timestamp.valueOf(position.getBaseDateTime()));
                        insertShipPositionsPreparedStatement.setDouble(3, position.getLatitude());
                        insertShipPositionsPreparedStatement.setDouble(4, position.getLongitude());
                        insertShipPositionsPreparedStatement.setDouble(5, position.getSOG());
                        insertShipPositionsPreparedStatement.setDouble(6, position.getCOG());
                        insertShipPositionsPreparedStatement.setDouble(7, position.getHeading());
                        insertShipPositionsPreparedStatement.setString(8, String.valueOf(position.getTransceiverClass()));
                        insertShipPositionsPreparedStatement.setDouble(9, position.getCargo());
                        insertShipPositionsPreparedStatement.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    public ResultSet getAllShips(DatabaseConnection databaseConnection) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from Ship";
        PreparedStatement shipsPreparedStatement = connection.prepareStatement(sqlCommand);
            return shipsPreparedStatement.executeQuery();
    }

    public ResultSet getShipPostions(DatabaseConnection databaseConnection, String shipIMO) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from Position_Ship where id_ship = ?";
        PreparedStatement positionsPreparedStatement = connection.prepareStatement(sqlCommand);
            positionsPreparedStatement.setString(1, shipIMO);
            return positionsPreparedStatement.executeQuery();
    }

}
