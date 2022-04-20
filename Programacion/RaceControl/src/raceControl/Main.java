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
import competition.KnockoutRace;
import competition.Race;
import competition.Races;
import competition.RacesResults;
import competition.ScoreOfCar;
import competition.ScoreOfCarInARace;
import competition.SnapShot;
import competition.StandardRace;
import competition.Tournament;
import competition.Tournaments;
import utils.ParticipantsGenerator;

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

	public static void main(String[] args) throws Exception {

		loadSnapshot();

		boolean interruptor = true;
		while (interruptor) {
			System.out.println("1-Administrar torneos \n2-Administrar garajes \n3-Administrar coches \n4-Finalizar");

			switch (numberInput.nextInt()) {
			case 1:
				System.out.println("1-Create new tournament \n2-Modify existing tournament");
				switch (numberInput.nextInt()) {
				case 1:
					// Crear torneo
					createNewTournament();
					break;

				case 2:
					// Gestionar torneos
					modifyExistingTournament();
					break;
				}
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
				System.out.println("Session finished");
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
			System.out.println(e.getMessage());
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

	public static void showGaragesOfAList(ArrayList<Garage> garages) {
		for (int i = 0; i < garages.size(); i++) {
			System.out.println(i + "-" + garages.get(i).toString());
		}

	}

	public static void showModifyGarageOptionMenu() {

		if (garages.getNumberOfGarages() > 0) {
			int indexOfSelectedGarage;

			showGaragesOfAList(garages.getAll());

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
		showCarsOfAList(garage.getCars());
	}

	public static void addCarToGarage(Garage garage) {

		try {

			int indexOfSelectedCar;
			ArrayList<Car> carsWithoutGarage = cars.getCarsWithoutGarage();
			showCarsOfAList(carsWithoutGarage);

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
			showCarsOfAList(carsOfTheGarage);

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

	public static void showCarsOfAList(ArrayList<Car> cars) {
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

				showCarsOfAList(cars.getAll());

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

	public static void createNewTournament() {
		try {

			String name;
			int raceNumber;

			if (!garages.getGarageWithCars().isEmpty()) {

				System.out.println("Introduce the name:");
				name = stringInput.nextLine();
				System.out.println("Introduce the number of races:");
				raceNumber = stringInput.nextInt();

				Tournament newTournament = new Tournament(name,
						ParticipantsGenerator.generateParticipants(chooseParticipatingGarages()), raceNumber);

				tournaments.addTournament(newTournament);
				saveSnapShot();

			} else {
				System.out.println(
						"There are no garages or these do not have cars to participate , create some garage or add some car to the gaarages ");
			}
		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error creating the tournament");
		}
	};

	public static ArrayList<Garage> chooseParticipatingGarages() {
		ArrayList<Garage> possibleGarage = garages.getGarageWithCars();
		ArrayList<Garage> garagesParticipants = new ArrayList<Garage>();

		Garage selectedGarage;
		boolean interruptor = true;
		int inputIndex;

		showGaragesOfAList(possibleGarage);

		System.out.println("Introduce the index of selected garage, -1 ends");
		while (true) {
			inputIndex = numberInput.nextInt();

			if (inputIndex != -1) {
				selectedGarage = possibleGarage.get(inputIndex);

				if (garagesParticipants.contains(selectedGarage)) {
					System.out.println("The selected garage is alredy in the tournament");
				}
				System.out.println("The chosen garage is:\n" + selectedGarage);
				System.out.println(
						"Do you want to add this garage to the tournament?\nConfirm the changes (1-YES/2-NO):");

				if (numberInput.nextInt() == 1) {
					garagesParticipants.add(selectedGarage);
					System.out.println("Garage added \nIntroduce another index, -1 ends");

				}

			} else if (inputIndex == -1 && garagesParticipants.isEmpty()) {
				System.out.println("Please select some garage to participate");
			} else {
				return garagesParticipants;
			}
		}

	}

	public static void modifyExistingTournament() {
		if (!tournaments.getAll().isEmpty()) {
			System.out.println(
					"1-Add a new race to a tournament \n2-Run the next race in a tournament \n3-View the ranking o a tournament");

			switch (numberInput.nextInt()) {
			case 1:
				createARaceInTournament();
				break;

			case 2:
				runRaceOfUnfinishedTournament();
				break;
			case 3:
				showTournamentRanKing();
				break;

			}
		}
	}

	public static void showTournamentsOfAList(ArrayList<Tournament> tournaments) {
		for (int i = 0; i < tournaments.size(); i++) {
			System.out.println(i + "-" + tournaments.get(i).toString());
		}
	}

	public static void createARaceInTournament() {

		try {

			int indexOfSelectedTournament;
			Tournament selectedTournament;

			ArrayList<Tournament> unFinishedTournaments = tournaments.getTournamentsThatCanAddNewRaces();

			if ((selectedTournament = chooseTournamentOfAList(unFinishedTournaments)) != null) {

				selectedTournament.addRace(createNewRace(selectedTournament.getParticipants()));
				System.out.println("Race added to the tournament");
				saveSnapShot();
			} else {
				System.out.println("There are no tournamet to add new race");
			}

		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			// TODO:Quitar
			System.out.println(ex.getMessage());
			System.out.println("Error creating the race");
		}
	}

	public static Race createNewRace(ArrayList<Car> raceParticipants) throws Exception {
		String name;
		int hours;
		Race newRace = null;

		System.out.println("Introduce the name: ");
		name = stringInput.nextLine();

		System.out.println("Choose the type of the new race \n1-Standar race \n2-KnockOut race");
		switch (numberInput.nextInt()) {

		// Crear carrera
		case 1:
			System.out.println("Introduce the duration in hours");
			hours = numberInput.nextInt();

			newRace = new StandardRace(name, raceParticipants, hours * 60);
			break;
		case 2:
			newRace = new KnockoutRace(name, raceParticipants);
			break;
		}

		races.addRace(newRace);
		return newRace;

	}

	public static void runRaceOfUnfinishedTournament() {
		try {
			Tournament selectedTournament;
			ArrayList<Tournament> unfinishedTournaments = tournaments.getTounamentsThatCanRunNextRace();

			if ((selectedTournament = chooseTournamentOfAList(unfinishedTournaments)) != null) {
				Race runnedRace = selectedTournament.getNextRaceToRun();
				runnedRace.run();
				saveSnapShot();
				ArrayList<ScoreOfCarInARace> raceRanking = racesResults.getResultOfARace(runnedRace);

				System.out.println("The ranking of the race is:");
				for (int i = 0; i < raceRanking.size(); i++) {
					System.out.println((i + 1) + raceRanking.get(i).toString());
				}

			} else {
				System.out.println("There are no tournament that have more race to run");
			}

		} catch (IOException e) {
			System.out.println("Error saving the files");
		} catch (Exception ex) {
			System.out.println("Error runnin the race");
		}
	}

	public static void showTournamentRanKing() {
		try {

			int indexOfSelectedTournament;
			Tournament selectedTournament;

			ArrayList<Tournament> unFinishedTournaments = tournaments.getTournamentsByState(true);

			if ((selectedTournament = chooseTournamentOfAList(unFinishedTournaments)) != null) {
				ArrayList<ScoreOfCar> tournamentRanking = racesResults.getResultsOfTournament(selectedTournament);

				System.out.println("The ranking of tounament is:");
				for (int i = 0; i < tournamentRanking.size(); i++) {
					System.out.println((i + 1) + "-" + tournamentRanking.get(i).toString());
				}

			} else {
				System.out.println("There are no finished tournaments to see the ranking");
			}

		} catch (Exception e) {
			System.out.println("Error creating new race");
		}
	}

	public static Tournament chooseTournamentOfAList(ArrayList<Tournament> tournaments) {
		int indexOfSelectedTournament;
		Tournament selectedTournament;

		if (!tournaments.isEmpty()) {
			showTournamentsOfAList(tournaments);

			System.out.println("Chose the tournament wich you want to run next race:");
			indexOfSelectedTournament = numberInput.nextInt();

			return selectedTournament = tournaments.get(indexOfSelectedTournament);

		}

		return null;
	}
}
