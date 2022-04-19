package raceControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import IO.JSONFileManager;
import competition.Car;
import competition.Cars;
import competition.Garage;
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
			System.out.println("1-Administrar torneos \n2-Administrar garajes \n3-Administrar coches \n4-Finalizar");

			switch (numberInput.nextInt()) {
			case 1:
				System.out.println("Administrar torneos");
				break;

			/*
			 * crearTorneo(); administrarTorneo(); verClasificacion
			 */
			case 2:
				// Menu garages
				System.out.println("1-Create new garage \n2-Modify existing garage");

				switch (numberInput.nextInt()) {

				case 1:
					// Crear garaje
					createNewGarage();
					break;

				case 2:
					// Modificar un garaje existente
					showModifyGarageOptionMenu();
					break;
				}

				break;
			case 3:
				// Menu coches
				System.out.println("1-Create new Car \n2-Modify existing car");

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
				saveSnapShot();
				interruptor = false;
				break;

			}
		}

	}

	/*
	 * public static void createToutnament() { tournaments.addTournament(new
	 * Tournament(name, participants, numberOfRaces)); }
	 */

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

	public static void createNewGarage() {
		try {
			System.out.println("Introduce the name:");
			String name = stringInput.nextLine();
			garages.addGarage(new Garage(name));
			System.out.println("A new garage has been created");
			saveSnapShot();
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error creating the garage");
		}

	}

	public static void showAllGarages() {
		for (int i = 0; i < garages.getNumberOfGarages(); i++) {
			System.out.println(i + "-" + garages.getGarage(i).toString());
		}

	}

	public static void showModifyGarageOptionMenu() {

		if (garages.getNumberOfGarages() > 0) {
			int indexOfSelectedGarage;

			showAllGarages();

			System.out.println("Chose the garage that you want to change:");
			indexOfSelectedGarage = numberInput.nextInt();

			Garage modifiedGarage = garages.getGarage(indexOfSelectedGarage);
			System.out.println("The chosen garage is:\n" + modifiedGarage + "\n");

			System.out.println("1-Modify the name of garage \n2-Add/Remove cars form garage");
			switch (numberInput.nextInt()) {
			case 1:
				modifyingGarageName(modifiedGarage);
				break;

			case 2:
				modifyingGarageCars(modifiedGarage);
				break;
			}
		} else {
			System.out.println("There is no garage to modify");
		}

	}

	public static void modifyingGarageName(Garage modifiedGarage) {
		try {

			String newName;

			System.out.println("Introduce the new name:");
			newName = stringInput.nextLine();

			System.out.println("The garage data will be:\n Name: " + newName + "\nConfirm the changes (1-YES/2-NO): ");

			if (numberInput.nextInt() == 1) {
				modifiedGarage.setName(newName);
				System.out.println("Operation completed");

				saveSnapShot();

			} else {
				System.out.println("There are no garage to modify");
			}

		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error changing the name of garage");
		}
	}

	// TODO:Cambiar el nombre del método
	public static void modifyingGarageCars(Garage modifiedGarage) {
		int indexOfSelectedGarage;
		boolean thereAreNoCars;
		String newName;


		thereAreNoCars = modifiedGarage.getCars().isEmpty();

		if (thereAreNoCars) {
			System.out.println("There is no car in the selected garage");
		} else {
			showCarsOfAGarage(modifiedGarage);
		}

		System.out.println("1-Add a car to the garage");
		if (!thereAreNoCars) {
			System.out.println("2-Remove a car of the garage");
		}

		int optionInput = numberInput.nextInt();
		if (optionInput == 1) {
			addCarToGarage(modifiedGarage);
		} else if (optionInput == 2 && !thereAreNoCars) {
			// Modificar un coche existente
			removeACarOfTheCarage(modifiedGarage);
		}

	}

	public static void showCarsOfAGarage(Garage garage) {
		showDataCarsOfList(garage.getCars());
	}

	public static void addCarToGarage(Garage garage) {

		try {

			int indexOfSelectedCar;
			ArrayList<Car> carsWithoutGarage = cars.getCarsWithoutGarage();
			showDataCarsOfList(carsWithoutGarage);

			System.out.println("Chose the car that you want to add to the garage:");
			indexOfSelectedCar = numberInput.nextInt();

			Car selectedCar = carsWithoutGarage.get(indexOfSelectedCar);
			System.out.println("The chosen car is:\n" + selectedCar.toString() + "\n");

			System.out.println("Confirm the changes (1-YES/2-NO): ");
			if (numberInput.nextInt() == 1) {
				garage.addCar(selectedCar);
				System.out.println("Operation completed");

				saveSnapShot();
			}
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error adding a car to the garage");
		}
	}

	public static void removeACarOfTheCarage(Garage garage) {
		try {

			int indexOfSelectedCar;
			ArrayList<Car> carsOfTheGarage = garage.getCars();
			showDataCarsOfList(carsOfTheGarage);

			System.out.println("Chose the car that you want to remove of the garage:");
			indexOfSelectedCar = numberInput.nextInt();

			Car selectedCar = carsOfTheGarage.get(indexOfSelectedCar);
			System.out.println("The chosen car is:\n" + selectedCar.toString() + "\n");

			System.out.println("Confirm the changes (1-YES/2-NO): ");
			if (numberInput.nextInt() == 1) {
				garage.removeCar(selectedCar);
				System.out.println("Operation completed");

				saveSnapShot();
			}
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error removing a car of the garage");
		}
	}

	public static void createNewCar() {

		try {

			String brand, model;

			System.out.println("Introduce the brand:");
			brand = stringInput.nextLine();
			System.out.println("Introduce the model:");
			model = stringInput.nextLine();

			cars.addCar(new Car(brand, model));
			System.out.println("A new car has been created");
			saveSnapShot();
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error creating a new car");
		}
	}

	public static void showDataCarsOfList(java.util.List<Car> cars) {
		for (int i = 0; i < cars.size(); i++) {
			System.out.println(i + "-" + cars.get(i).toString());
		}
	}

	public static void modifyingCar() {

		try {
			int indexOfSelectedCar;
			String newBrand, newModel;
			Car modifiedCar;

			if (cars.getNumberOfCars() > 0) {

				showDataCarsOfList(cars.getAll());

				System.out.println("Chose the car that you want to change:");
				indexOfSelectedCar = numberInput.nextInt();

				modifiedCar = cars.getCar(indexOfSelectedCar);
				System.out.println("The chosen car is:\n" + modifiedCar.toString() + "\n");

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
					saveSnapShot();
				}
			} else {
				System.out.println("There are no car to modify");
			}
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error modifying a car");
		}
	}

}
