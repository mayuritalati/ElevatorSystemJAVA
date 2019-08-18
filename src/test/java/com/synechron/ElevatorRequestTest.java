
package com.synechron;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 
 * @author MayuriJain
 *
 */
public class ElevatorRequestTest {

    private ElevatorController elevatorController;
    private Thread elevatorControllerThread;



    @Before
    public void setUp() throws Exception {

        elevatorController = ElevatorController.getInstance();
        elevatorController.initializeElevators(15);
        elevatorControllerThread = new Thread(elevatorController);
        elevatorControllerThread.start();
    }

    @After
    public void tearDown() throws Exception {
        if(!elevatorController.isControllerState()) {
            elevatorController.setControllerState(true);
        }
      
    }

    @Test
    public void testSubmitRequest1() throws Exception {
        ElevatorRequest elevatorRequest = new ElevatorRequest(0, 2);
        Elevator elevator = elevatorRequest.submitRequest();
        Thread.sleep(5000);
        assertEquals(2, elevator.getCurrentFloor().getFloorNumber().intValue());
    }
  
}