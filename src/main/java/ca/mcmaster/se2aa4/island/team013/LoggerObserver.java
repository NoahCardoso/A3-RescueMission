package ca.mcmaster.se2aa4.island.team013;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerObserver extends Observer {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void update(Drone drone) {
        logger.info("Drone moved to (" + drone.getX() + ", " + drone.getY() + "), Battery: " + drone.getBattery());

        
    }
}
