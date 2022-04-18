package raceControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import competition.Car;
import competition.Cars;
import competition.Garage;
import competition.Garages;
import competition.KnockoutRace;
import competition.Races;
import competition.RacesResults;
import competition.SnapShot;
import competition.Tournament;
import competition.Tournaments;
import utils.ParticipantsGenerator;

public class Main {
	private static Scanner stringInput = new Scanner(System.in);
	private static Scanner numberInput = new Scanner(System.in);

	private static Cars cars = Cars.getInstance();
	private static Garages garages = Garages.getInstance();
	private static Races races = Races.getInstance();
	private static RacesResults racesResults = RacesResults.getInstance();
	private static Tournaments tournaments = Tournaments.getInstance();

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			Car newCar = new Car("brand_" + i, "model_" + i);
			cars.addCar(newCar);
		}

		Garage gar = new Garage("Seat");
		garages.addGarage(gar);
		garages.addGarage(new Garage("Renolt"));
		gar.addCar(cars.getCar(1));
		gar.addCar(cars.getCar(2));
		gar.addCar(cars.getCar(3));
		gar.addCar(cars.getCar(4));
		gar.addCar(cars.getCar(5));

		ArrayList<Garage> gara = new ArrayList();
		gara.add(gar);

		ArrayList<Car> participants;
		try {
			participants = ParticipantsGenerator.generateParticipants(gara);
			Tournament tor = new Tournament("Try", participants, 10);
			tournaments.addTournament(tor);

			KnockoutRace race = new KnockoutRace("RaceTry", participants);
			KnockoutRace race1 = new KnockoutRace("RaceTry1", participants);
			races.addRace(race);
			races.addRace(race1);
			tor.addRace(race);
			tor.addRace(race1);

			tor.runRace();
			tor.runRace();
			RacesResults result = RacesResults.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SnapShot snapShop = new SnapShot(tournaments, garages, cars, races, racesResults);

		/*
		 * JSONFileManager json = new JSONFileManager(); json.save(state);
		 */

		ObjectMapper objMap = new ObjectMapper();
		objMap.setVisibility(PropertyAccessor.FIELD, Visibility.ANY).writerWithDefaultPrettyPrinter()
				.writeValue(new File("res/StateFile.json"), snapShop);
		
		SnapShot snapshop2 = objMap.readValue(new File("res/StateFile.json"), SnapShot.class);
		System.out.println("a");
		/*
		 * boolean interruptor = true; while (interruptor) { showInicialMenu(); switch
		 * (numberInput.nextInt()) { case 1: System.out.println("Administrar torneos");
		 * break;
		 * 
		 * case 2:
		 * 
		 * break; case 3: showCarsMenu();
		 * 
		 * switch (numberInput.nextInt()) { case 1: createNewCar(); break;
		 * 
		 * case 2:
		 * 
		 * modifyingCar(); break; } break; case 4: interruptor = false; break;
		 * 
		 * } }
		 */
	}

	public static void showInicialMenu() {
		System.out.println("1-Administrar torneos \n2-Administrar garajes \n3-Administrar coches \n4-Finalizar");
	}

	public static void showCarsMenu() {
		System.out.println("1-Create new Car \n2-Modify existing car");
	}

	public static void createNewCar() {
		String brand, model;

		System.out.println("Introduce the brand:");
		brand = stringInput.nextLine();
		System.out.println("Introduce the model:");
		model = stringInput.nextLine();

		cars.addCar(new Car(brand, model));
	}

	public static void showAllCars() {
		for (int i = 0; i < cars.getNumberOfCars(); i++) {
			System.out.println(i + "-" + cars.getCar(i).toString());
		}
	}

	public static void modifyingCar() {
		int indexOfSelectedCar;
		String newBrand, newModel;
		Car modifiedCar;

		if (cars.getNumberOfCars() > 0) {

			showAllCars();

			System.out.println("Chose the car that you want to change:");
			indexOfSelectedCar = numberInput.nextInt();

			modifiedCar = cars.getCar(indexOfSelectedCar);
			System.out.println("The chosen car is:\n" + modifiedCar + "\n");

			System.out.println("Introduce the brand:");
			newBrand = stringInput.nextLine();
			System.out.println("Introduce the model:");
			newModel = stringInput.nextLine();

			System.out.println("The data car will be:\n Brand: " + newBrand + " - Model: " + newModel
					+ "\nConfirm the changes (1-YES/2-NO): ");

			if (numberInput.nextInt() == 1) {
				modifiedCar.setBrand(newBrand);
				modifiedCar.setModel(newModel);
				System.out.println("Operation completed");
			}
		} else {
			System.out.println("There are no car to modify");
		}
	}

}
