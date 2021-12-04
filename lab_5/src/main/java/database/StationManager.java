package database;

import java.Station;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class StationManager {
    private Connection connection;

    public StationManager(){
        try {
            connection = new BaseConnection().open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTableStations() throws SQLException {
        String table = "CREATE TABLE Stations(" +
                "    Id INT(15) PRIMARY KEY," +
                "    Name VARCHAR(30) NOT NULL" +
                ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(table);
    }
    public void deleteTableStations() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE Stations");
    }
    public void addStation(Station station) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT  INTO Station VALUES (?,?)");
        statement.setInt(1,station.getId());
        statement.setString(2,station.getName());
        statement.execute();
        System.out.println("Add:\n"+station.toString());
    }

    /**
     * Return all stations in table
     * @return
     * @throws SQLException
     */
    public Collection<Station> selectAll() throws SQLException {
        var stations = new ArrayList<Station>();

        ResultSet resultSet = null;
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM Stations");
        System.out.println("It work normal");
        while (resultSet.next()){
            System.out.println("ok");
            Station station = new Station(resultSet.getString("Name"),resultSet.getInt("Id"));
            stations.add(station);
        }
        return stations;
    }



}
