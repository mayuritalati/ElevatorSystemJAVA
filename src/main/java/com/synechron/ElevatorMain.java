package com.synechron;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author MayuriJain
 *
 */
public class ElevatorMain {

	private static ElevatorController eleController;
	private static Thread eleControllerThread;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		eleController = ElevatorController.getInstance();
		//initialize elevator
	
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of Elevator(1 to 15) ");
		int elevatorCount = input.nextInt();
		eleController.initializeElevators(elevatorCount);
		
		eleControllerThread = new Thread(eleController);
		eleControllerThread.start();

		int choice;

		input = new Scanner(System.in);
		System.out.println("Enter number of concurrent request for Elevator(0 to 22500) ");
		choice = input.nextInt();

		if (choice >= 1 && choice <= 22500) {
			for (int i = 0; i < choice; i++) {
				//it will take random requested floor and target floor
				int reqestFloor = getRandomNumber();
				int targetFloor = getRandomNumber();
				System.out.println("Request Floor : "+reqestFloor +" | Target Floor : "+targetFloor);

				ElevatorRequest elevatorRequest = new ElevatorRequest(reqestFloor, targetFloor);
				Elevator elevator = elevatorRequest.submitRequest();
			}

		}

	}

	public static int getRandomNumber() {
		Random r = new Random();
		int low = 1;
		int high = 150;
		return r.nextInt(high - low) + low;
	}
}
