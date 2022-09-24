package DisneyParksPathTest;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import DisneyParksPaths.ParkMap;
import DisneyParksPaths.ParkNode;
import DisneyParksPaths.ParkPaths;
import DisneyParksPaths.Parser;

public class DisneyPathsTesting { // Rename to the name of your "main" class
	
	ParkMap MagicKingdom; 
	ParkNode loc1,loc2;
	HashMap<ParkNode,HashSet<ParkNode>> locations;
	String file1,file2;
	
	@Before
	public void setUp() throws IOException { 
		MagicKingdom = new ParkMap();
		loc1 = loc2 = new ParkNode("A",12,12,12);
		locations = new HashMap<ParkNode,HashSet<ParkNode>>();
		file1 = "data/Magic-Kingdom-Nodes.csv";
        file2 = "data/Magic-Kingdom-Edges.csv";
		MagicKingdom.createNewMap(file1,file2); 
	} 
	
	/**
	 * @param file1 
	 * @param file2 
	 * @return true if file1 and file2 have the same content, false otherwise
	 * @throws IOException
	 */	
	/* compares two text files, line by line */
	private static boolean compare(String file1, String file2) throws IOException {
		BufferedReader is1 = new BufferedReader(new FileReader(file1)); // Decorator design pattern!
		BufferedReader is2 = new BufferedReader(new FileReader(file2));
		String line1, line2;
		boolean result = true;
		while ((line1=is1.readLine()) != null) {
			line2 = is2.readLine();
			
			if (line2 == null) { 
				System.out.println(file1+" longer than "+file2);
				result = false;
				break;
			}
			if (!line1.equals(line2)) {
				System.out.println("Lines: "+line1+" and "+line2+" differ.");
				result = false;
				break; 
			}
		}
		if (result == true && is2.readLine() != null) {
			System.out.println(file1+" shorter than "+file2);
			result = false;
		} 
		is1.close();
		is2.close();
		return result;		 
	}
	 
	private void runTest(String filename) throws IOException {
		InputStream in = System.in; 
		PrintStream out = System.out;				
		String inFilename = "data/"+filename+".test"; // Input filename: [filename].test here  
		String expectedFilename = "data/"+filename+".expected"; // Expected result filename: [filename].expected
		String outFilename = "data/"+filename+".out"; // Output filename: [filename].out
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(inFilename));
		System.setIn(is); // redirects standard input to a file, [filename].test
		PrintStream os = new PrintStream(new FileOutputStream(outFilename));
		System.setOut(os); // redirects standard output to a file, [filename].out 
		ParkPaths.main(null); // Call to YOUR main. May have to rename.
		System.setIn(in); // restores standard input 
		System.setOut(out); // restores standard output 
		assertTrue(compare(expectedFilename,outFilename));  
		// TODO: More informative file comparison will be nice.
		
	}
	
	@Test 
	public void testLocationComparator() {
		assertEquals(loc1.compareTo(loc2),0); 
	} 
	
	@Test(expected=IOException.class)
	public void testMapParserReadNodes() throws IOException {
		Parser.readNodes("", locations);  
	}
	
	@Test(expected=IOException.class)
	public void testMapParserReadEdges() throws IOException {
		Parser.readEdges("", locations);
	} 
	
	@Test
	public void testfindShortestPaths() { 
		// 2 intersection titles/ blank titles
		assertEquals(MagicKingdom.findShortestPath("",""),"Unknown building: []");
		// building 1 and building 2 are the same and don't exist
		assertEquals(MagicKingdom.findShortestPath("aaab", "aaab"),"Unknown building: [aaab]");
		// building 1 and building 2 don't exist
		assertEquals(MagicKingdom.findShortestPath("aaab", "aaac"),"Unknown building: [aaab]\nUnknown building: [aaac]");
		// building 1 doesn't exist
		assertEquals(MagicKingdom.findShortestPath("Space Mountain","aaac"),"Unknown building: [aaac]");
		// building 2 doesn't exist
		assertEquals(MagicKingdom.findShortestPath("aaac","Haunted Mansion"),"Unknown building: [aaac]");
		// no path between given nodesf 
		assertEquals(MagicKingdom.findShortestPath("Cinderella's Castle", "Cinderella's Castle"),"Path from Cinderella's Castle to Cinderella's Castle:\n"
				+ "Total distance: 0.000 pixel units.");
		// short path (attraction wise)
		assertEquals(MagicKingdom.findShortestPath("Emporium", "Cinderella's Castle"), "Path from Emporium to Cinderella's Castle:\n"
				+ "\tWalk North to (Casey's Corner)\n"
				+ "\tWalk North to (Mickey and Walt Disney Center Statue)\n"
				+ "\tWalk NorthWest to (Sleepy Hollow)\n"
				+ "\tWalk East to (Cinderella's Castle)\n"
				+ "Total distance: 505.187 pixel units.");
		// medium path  
		assertEquals(MagicKingdom.findShortestPath("Pirates of the Caribbean","Peter Pan's Flight"),"Path from Pirates of the Caribbean to "
				+ "Peter Pan's Flight:\n\tWalk East to (Walt Disney's Enchanted Tiki Room)\n"
				+ "\tWalk NorthEast to (The Magic Carpets of Aladdin)\n"
				+ "\tWalk NorthWest to (Aloha Isle)\n"
				+ "\tWalk North to (Country Bear Jamboree)\n"
				+ "\tWalk East to (Frontier Trading Post)\n"
				+ "\tWalk East to (The Diamond Horshoe)\n"
				+ "\tWalk NorthEast to (Liberty Tree Tavern)\n"
				+ "\tWalk NorthEast to (The Hall of Presidents)\n"
				+ "\tWalk NorthWest to (Liberty Square Market)\n"
				+ "\tWalk North to (Columbia Harbour House)\n"
				+ "\tWalk NorthWest to (Momento Mori)\n"
				+ "\tWalk East to (it's a small world)\n"
				+ "\tWalk East to (Peter Pan's Flight)\n"
				+ "Total distance: 697.504 pixel units.");
		// long path
		assertEquals(MagicKingdom.findShortestPath("Space Mountain", "Big Thunder Mountain Railroad"),"Path from Space Mountain to Big Thunder Mountain Railroad:"
				+ "\n\tWalk West to (Cool Ship)"
				+ "\n\tWalk West to (Auntie Gravity's Galactic Goodies)"
				+ "\n\tWalk SouthWest to (Monster's Inc. Laugh Floor)"
				+ "\n\tWalk West to (Mickey and Walt Disney Center Statue)"
				+ "\n\tWalk NorthWest to (Ye Olde Christmas Shoppe)"
				+ "\n\tWalk West to (Liberty Tree Tavern)"
				+ "\n\tWalk SouthWest to (The Diamond Horshoe)"
				+ "\n\tWalk West to (Frontier Trading Post)"
				+ "\n\tWalk West to (Country Bear Jamboree)"
				+ "\n\tWalk NorthWest to (Westward Ho)"
				+ "\n\tWalk NorthWest to (Tom Sawyer Island)"
				+ "\n\tWalk NorthWest to (Big Thunder Mountain Railroad)"
				+ "\nTotal distance: 1079.292 pixel units.");

	}
	
}