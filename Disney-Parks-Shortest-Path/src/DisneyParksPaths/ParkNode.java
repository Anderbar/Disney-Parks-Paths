package DisneyParksPaths;
public class ParkNode implements Comparable<ParkNode>{
	String title;
	int id;
	int x;
	int y;
	
	/**  
	@param String name : The title of the point.
    @param int _id : id for the point
    @param int xcord : x-coordinate of the point
    @param int ycord : y-coordinate of the point
    @effects Constructs a new CampusPoint object.
	*/
	public ParkNode(String name,int _id,int xcord,int ycord) {
		title = name;
		x = xcord;
		y = ycord;
		id = _id;
	}
	
	/**  
	@param none
    @effects none
    @returns The title of the location.
	*/
	public String getTitle() {
		return title;
	}
	
	/**  
	@param none
    @effects none
    @returns The x-coordinate of the location.
	*/
	public int getX() {
		return x;
	}
	
	/**  
	@param none
    @effects none
    @returns The y coordinate of the location.
	*/
	public int getY() {
		return y;
	}
	
	/**  
	@param none
    @effects none
    @returns The id of the location.
	*/
	public int getId() {
		return id;
	}
	
	/**  
	@param Location loc : location to compare to
    @effects none
    @returns If the two locations are equal or not.
	*/
	public boolean equals(ParkNode loc) {
		if (title.equals(loc.getTitle()) && id == loc.getId())
				return true;
		return false;
	}
	
	/**  
	@param CampusPoint building : point we are comparing to
    @effects none
    @returns returns the distance between two buildings in Double form
	*/
	public Double getDistance(ParkNode building) {
		return Math.sqrt(Math.pow((x-building.getX()), 2) + Math.pow((y-building.getY()), 2));
	}
	
	String getDirection(ParkNode building1) {
        double angle; // = Math.toDegrees(Math.acos((n1.y-n2.y)/distance(n1.x,n1.y,n2.x,n2.y)));
        if (x < building1.getX()) {
            angle = Math.toDegrees(Math.acos((y-building1.getY())/this.getDistance(building1)));
            if (0 <= angle && angle < 22.5) 
                return "North";
            else if (22.5 <= angle && angle < 67.5)
                return "NorthEast"; 
            else if (67.5 <= angle && angle < 112.5)
                return "East";
            else if (112.5 <= angle && angle < 157.5)
                return "SouthEast";
            else   
                return "South";
        }
        else {
            angle = Math.toDegrees(Math.acos(-(y-building1.getY())/this.getDistance(building1)));
            if (0 <= angle && angle < 22.5) 
                return "South";
            else if (22.5 <= angle && angle < 67.5)
                return "SouthWest";
            else if (67.5 <= angle && angle < 112.5)
                return "West";
            else if (112.5 <= angle && angle < 157.5)
                return "NorthWest";
            else 
                return "North"; 
        } 
    }
	
	/**  
	@param none
    @effects none
    @returns returns int for comparison between two CampusPoint objects
	*/
	@Override
	public int compareTo(ParkNode building) {
		return this.getTitle().compareTo(building.getTitle());
	}
	
	/**  
	@param none
    @effects none
    @returns returns the hash code value of the point
	*/
	@Override
	public int hashCode()
	{
	    return id;  
	}
}