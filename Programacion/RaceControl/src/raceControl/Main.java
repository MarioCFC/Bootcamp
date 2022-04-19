package raceControl;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import IO.JSONFileManager;
import competition.Car;
import competition.Cars;
import competition.Garages;
import competition.Races;
import competition.RacesResults;
import competition.SnapShot;
import competition.Tournaments;

public class Main {
	private static Scanner stringInput = new Scanner(System.in);
	private static Scanner numberInput = new Scanner(System.in);

	private static JSONFileManager jsonManager = new JSONFileManager();
	private static SnapShot actualSnapShot;

	private static Tournaments tournaments;
	private static Garages garages;
	private static Cars cars;
	private static Races races;
	private static RacesResults racesResults;

	public static void main(String[] args) throws IOException {

		loadSnapshot();

		boolean interruptor = true;
		while (interruptor) {
			showInicialMenu();
			switch (numberInput.nextInt()) {
			case 1:
				System.out.println("Administrar torneos");
				break;

			case 2:
				// Menu garages
				showGaragesMenu();

				switch (numberInput.nextInt()) {

				case 1:
					// Crear garaje

					break;

				case 2:
					// Modificar un garaje existente

					break;
				}

				break;
			case 3:
				// Menu coches
				showCarsMenu();

				switch (numberInput.nextInt()) {
				// Crear coche
				case 1:
					createNewCar();
					break;

				case 2:
					// Modificar un coche existente
					modifyingCar();
					break;
				}

				break;
			case 4:
				interruptor = false;
				break;

			}
		}

	}

	public static void loadSnapshot() throws StreamReadException, DatabindException, IOException {
		try {
			actualSnapShot = jsonManager.load();

			tournaments = actualSnapShot.getTournaments();
			garages = actualSnapShot.getGarages();
			cars = actualSnapShot.getCars();
			races = actualSnapShot.getRaces();
			racesResults = actualSnapShot.getRacesResults();

		} catch (Exception e) {
			tournaments = Tournaments.getInstance();
			garages = Garages.getInstance();
			cars = Cars.getInstance();
			races = Races.getInstance();
			racesResults = RacesResults.getInstance();

			actualSnapShot = new SnapShot(tournaments, garages, cars, races, racesResults);
		}
	}

	public static void saveSnapShot() throws IOException {
		jsonManager.save(actualSnapShot);
	}

	public static void showInicialMenu() {
		System.out.println("1-Administrar torneos \n2-Administrar garajes \n3-Administrar coches \n4-Finalizar");
	}

	public static void showGaragesMenu() {
		System.out.println("1-Create new garage \n2-Modify existing garage");
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
		System.out.println("A new car has been created");
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

			System.out.println("Introduce the new brand:");
			newBrand = stringInput.nextLine();
			System.out.println("Introduce the new model:");
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
