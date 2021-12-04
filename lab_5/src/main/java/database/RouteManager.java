package database;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import route.Route;

import java.Station;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class RouteManager {
    private Connection connection;

    public RouteManager(){
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
    public void createTableRoute() throws SQLException {
        String table = "CREATE TABLE Routs(" +
                "    Id INT(15) PRIMARY KEY," +
                "    Name VARCHAR(30) NOT NULL" +
                ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(table);
    }
    public void createTableRoutsStations() throws SQLException {
        String table = "CREATE TABLE RoutsStations(" +
                "    IdRoute INT(15) NOT NULL ," +
                "    IdStation INT(15) NOT NULL," +
                "    FOREIGN KEY (IdRoute) REFERENCES Routs(Id)," +
                "    FOREIGN KEY (IdStation) REFERENCES Stations(Id)" +
                ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(table);
    }
    public void deleteTableRouts() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE Routs");
    }
    public void deleteTableStationsRouts() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE RoutsStations");
    }
    public void addRoute(Route route) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT  INTO stations VALUES (?,?)");
        statement.setInt(1,route.getId());
        statement.setString(2,route.getCode());
        statement.execute();
        System.out.println("Add new route");
        for (Station st:
             route.getStationsR()) {
            addRouteStation(st,route);
        }
    }
    public void addRouteStation(Station station,Route route) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO RoutsStations VALUES(?,?)");
        statement.setInt(1,station.getId());
        statement.setInt(2,route.getId());
        statement.execute();
        System.out.println("Add new routeStation");
    }
public Collection<Station> selectRoutStations(int idrout) throws SQLException {

    StationManager manager = new StationManager();
    ArrayList<Station> stations = (ArrayList<Station>) manager.selectAll();
    Collection<Station> res = new ArrayList<>();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM routsstations");


    while (resultSet.next()){
        if(resultSet.getInt("IdRoute")==idrout){
            for (Station st:
                 stations) {
                if(resultSet.getInt("IdStation")==st.getId()){
                    res.add(st);
                }
            }
        }
    }
    return res;

}

    public Collection<Route> selectAll() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = null;

        Collection<Route> routes = new ArrayList<Route>();
        rs = stmt.executeQuery("SELECT * FROM routs;");

        while(rs.next()){
            Route route = new Route(rs.getInt("Id"),rs.getString("Name"),selectRoutStations(rs.getInt("Id")));
        }

        return routes;
    }
}
