package route;


import route.Route;

import java.Station;
import java.util.ArrayList;
import java.util.Objects;

public class RouteBuilder {
    private Route route;

    public RouteBuilder() {
        route = new Route();
    }
    public RouteBuilder addCode(String code){
        assert (!Objects.equals(code, ""));
        route.setCode(code);

        return this;
    }
    public RouteBuilder addStation(Station station){
        assert (station!=null);
        route.addStation(station);

        return this;
    }
    public RouteBuilder setStations(ArrayList<Station> stations){
        assert (stations!=null);
        route.setStationsR(stations);

        return this;
    }
    public Route build(){
        assert (!Objects.equals(route.getCode(), ""));
        assert (route.getStationsCount()>=2);

        return route;
    }
}
