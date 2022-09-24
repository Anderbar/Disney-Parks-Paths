package DisneyParksPaths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;




public class Parser { 
	  
	/**  
	@param String filename : The file we are creating locations from.
    @param HashMap<CampusPoint,HashSet<Integer>> map : Map in which keys are Location objects and values are sets of edges.
    @effects Pulls locations, coordinates, ids, and intersections from the file and stores them in a map.
	*/
	public static void readNodes(String filename,HashMap<ParkNode,HashSet<ParkNode>> map) 
    		throws IOException {  	  
    	BufferedReader reader = new BufferedReader(new FileReader(filename));
    	try {
    		String line = null;  
            while ((line = reader.readLine()) != null) {    
               String[] data = line.split(",");
               String location = data[0];
               int id = Integer.valueOf(data[1]);
               int x = Integer.valueOf(data[2]);
               int y = Integer.valueOf(data[3]);
               map.put(new ParkNode(location,id,x,y),new HashSet<ParkNode>());
          }
    	}
    	finally {
    		reader.close();
    	}
        
    }
	
	/**  
	@param String filename : The file we are pulling edges from.
    @param HashMap<CampusPoint,HashSet<Integer>> map : Map in which keys are Location objects and values are sets of edges.
    @effects Pulls edges from the file and stores them in the map.
	*/ 
	public static void readEdges(String filename,HashMap<ParkNode,HashSet<ParkNode>> map) 
    		throws IOException {  	  
    	BufferedReader reader = new BufferedReader(new FileReader(filename));
    	try {
    		String line = null;
            while ((line = reader.readLine()) != null) {   
               /**
                * Find the Locations that match the ids and add them as a connection to each other
                */
               String[] data = line.split(",");
               ParkNode building1 = null;
               ParkNode building2 = null;
               int id1 = Integer.valueOf(data[0]);
               int id2 = Integer.valueOf(data[1]);
               for (ParkNode n : map.keySet()) {
              	 if (n.getId() == id1) {
              		 building1 = n;
              	 }
              	 if (n.getId() == id2) {
              		 building2 = n;
              	 }
               }  
               map.get(building1).add(building2);
               map.get(building2).add(building1);
          }
    	}
    	finally {
    		reader.close();
    	}
        
    }
}