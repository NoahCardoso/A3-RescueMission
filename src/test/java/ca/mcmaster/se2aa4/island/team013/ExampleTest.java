package ca.mcmaster.se2aa4.island.team013;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExampleTest {
    String path = "./maps/map03.json";

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
    public void ClosestCreekTest() {
        POIProcessor ls = new POIProcessor();
        ls.addEmergencySite("00", 0, 0);
        ls.addCreek("20", 20, 0);
        ls.addCreek("14", 15, 40);
        ls.addCreek("41", 40, 10);
        assertTrue(ls.getClosestPOI().equals("20"));
    }


    @Test
    @Order(1)
    public void MapTest() {
        String testFilePath = this.path;
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
            Runner.main(new String[]{testFilePath});
        } catch (Exception e) {
            fail("Runner.main() threw an exception: " + e.getMessage());
        }

        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    @Order(2)
    public void Map06Test(){
        this.path = "./maps/map06.json";
        MapTest();
    }
    @Test
    @Order(3)
    public void Map10Test(){
        this.path="./maps/map10.json";
        MapTest();
    }

    @Test
    @Order(4)
    public void Map17Test(){
        this.path = "./maps/map17.json";
        MapTest();
    }

    @Test
    @Order(5)
    public void Map20Test(){
        this.path = "./maps/map20.json";
        MapTest();
    }
    
    
    @Test
    @Order(6)
    public void hasEmergencySiteTest() throws IOException {
        File currentDir = new File(".");
        String[] files = currentDir.list();
        if (files != null) {
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files found.");
        }


        String testFilePath = "./outputs/_pois.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(testFilePath)));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        
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
    @Order(7)
    public void didStopTest() throws IOException{
        String testFilePath = "./outputs";
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists(), "Test input file must exist: " + testFilePath);

        testFilePath = "./outputs/Explorer_Island.json";
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

    


    
    
    




}
