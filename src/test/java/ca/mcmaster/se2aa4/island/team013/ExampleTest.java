package ca.mcmaster.se2aa4.island.team013;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;


public class ExampleTest {

    @Test
    public void ClosestCreekTest() {
        POIProcessor ls = new POIProcessor();
        ls.addEmergencySite("00", 0, 0);
        ls.addCreek("20", 20, 0);
        ls.addCreek("14", 15, 40);
        ls.addCreek("41", 40, 10);
        assertTrue(ls.getClosestPOI().equals("20"));
    }

    @Test
    public void FlyTestX() {
        Drone drone = new Drone(1000, Direction.EAST);
        int x = drone.getX();
        int y = drone.getY();
        JSONObject temp = new Fly().execute(drone);
        assertTrue(drone.getY() == y && (drone.getX()-1) == x);
    }

    @Test
    public void FlyTestY() {
        Drone drone = new Drone(1000, Direction.SOUTH);
        int x = drone.getX();
        int y = drone.getY();
        JSONObject temp = new Fly().execute(drone);
        assertTrue((drone.getY()-1) == y && drone.getX()== x);
    }

    @Test
    public void TurnTest() {
        Drone drone = new Drone(1000, Direction.EAST);
        int x = drone.getX();
        int y = drone.getY();
        JSONObject temp = new Heading(Direction.SOUTH).execute(drone);
        assertTrue((drone.getY()-1) == y && (drone.getX()-1)== x);
    }


    

    @Test
    void testMain() {
        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // Prepare test input file (ensure this file exists)
        String testFilePath = "./maps/map03.json";
        //assertTrue(testFile.exists(), "Test input file must exist.");
        
        // Run the main method
        
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists(), "Test input file must exist: " + testFilePath);
        
        // Ensure output directory exists
        File outputDir = new File("./outputs");
        if (!outputDir.exists()) {
            assertTrue(outputDir.mkdirs(), "Failed to create output directory");
        }

        try {
            Runner.main(new String[]{testFilePath});
        } catch (Exception e) {
            fail("Runner.main() threw an exception: " + e.getMessage());
        }

        // Reset System.out and System.err
        System.setOut(System.out);
        System.setErr(System.err);
    }





}
