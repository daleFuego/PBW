package ga;

import java.util.ArrayList;

public class RouteManager {

	private static ArrayList<DestinationPoint> destinations = new ArrayList<DestinationPoint>();

    public static void addDestination(DestinationPoint destination) {
        destinations.add(destination);
    }
    
    public static DestinationPoint getDestination(int index){
        return (DestinationPoint)destinations.get(index);
    }
    
    public static int destinationCount(){
        return destinations.size();
    }
}