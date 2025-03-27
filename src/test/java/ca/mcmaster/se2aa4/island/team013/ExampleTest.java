package ca.mcmaster.se2aa4.island.team013;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @AfterEach
    public void hasEmergencySiteTest() throws IOException {
        // Read and parse JSON file
        String testFilePath = "./outputs/_pois.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(testFilePath)));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        
        // Ensure it's an array
        assertTrue(rootNode.isArray(), "JSON root should be an array");
        
        boolean hasEmergencySite = false;
        
        for (JsonNode node : rootNode) {
            
            if ("EmergencySite".equals(node.get("kind").asText())) {
                hasEmergencySite = true;
            }
        }
        
        // Ensure at least one entry is an EmergencySite
        assertTrue(hasEmergencySite, "There must be at least one 'EmergencySite'");
    }
    
    @Test
    public void Map03Test(){
        MapTest("./maps/map03.json");
    }

    @Test
    public void Map06Test(){
        MapTest("./maps/map06.json");
    }

    @Test
    public void Map10Test(){
        MapTest("./maps/map10.json");
    }

    @Test
    public void Map17Test(){
        MapTest("./maps/map17.json");
    }

    @Test
    public void Map20Test(){
        MapTest("./maps/map20.json");
    }

    @Test
    public void didStopTest() throws IOException{
        String testFilePath = "./outputs/ExplorerDecorator_Island.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(testFilePath)));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        
        // Ensure it's an array
        assertTrue(rootNode.isArray(), "JSON root should be an array");

        boolean isStop = false;
        if (rootNode.get(rootNode.size() - 2).get("data").has("action")){
            isStop = "stop".equals(rootNode.get(rootNode.size()-2).get("data").get("action").asText());
        }
        assertTrue(isStop);
    }



    public void MapTest(String testFilePath) {
        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        
        // Run the main method
        
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists(), "Test input file must exist: " + testFilePath);
        
        // Ensure output directory exists
        File outputDir = new File("./outputs");
        if (!outputDir.exists()) {
            assertTrue(outputDir.mkdirs(), "Failed to create output directory");
        }

        try {
            RunnerTest.main(new String[]{testFilePath});
        } catch (Exception e) {
            fail("Runner.main() threw an exception: " + e.getMessage());
        }

        // Reset System.out and System.err
        System.setOut(System.out);
        System.setErr(System.err);
    }


    





}
