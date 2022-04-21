package raceControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;

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
	private static SnapShot actualSnapShot = null;

	private static Tournaments tournaments = Tournaments.getInstance();
	private static Garages garages = Garages.getInstance();
	private static Cars cars = Cars.getInstance();
	private static Races races = Races.getInstance();
	private static RacesResults racesResults = RacesResults.getInstance();

	/*
	 * TODO:Meter opcion para cancelar TODO:Opcion de listar los distintos
	 * objetos(Coches,garajes,torneos,carreras) TODO:Si da tiempo separar los
	 * objetos en distintos JSON Resumiendo, hacer que cargue bien y refactorizar a
	 * lo bestia el menú
	 */
	public static void main(String[] args) throws Exception {

		loadSnapshot();

		boolean interruptor = true;
		while (interruptor) {
			System.out.println("1-Torneos \n2-Garajes \n3-Coches \n4-Finalizar");

			switch (numberInput.nextInt()) {
			case 1:
				System.out.println("1-Crear torneo \n2-Gestionar torneos existentes");
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

			case 2:
				// Menu garages
				System.out.println("1-Crear garaje \n2-Gestionar garajes existentes");

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
				System.out.println("1-Crear coche \n2-Gestionar coches existentes");

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
				System.out.println("Sesion finalizada");
				break;

			}
		}

	}

	public static void loadSnapshot() throws StreamReadException {
		try {
			actualSnapShot = jsonManager.load();
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\nError al cargar los datos almacenados");
		}

		if (actualSnapShot != null && actualSnapShot.areListsInitiated()) {
			tournaments.loadTournamentsList(actualSnapShot.getTournaments());
			garages.loadGarageList(actualSnapShot.getGarages());
			cars.loadCarsList(actualSnapShot.getCars());
			races.loadRacesList(actualSnapShot.getRaces());
			racesResults.loadRacesResultsList(actualSnapShot.getRacesResults());
		} else {
			tournaments.initiateTournamentsList();
			garages.initiateGarageList();
			cars.initiateCarsList();
			races.initiateRacesList();
			racesResults.initiateRacesResultsList();
			actualSnapShot = new SnapShot(tournaments.getAll(), garages.getAll(), cars.getAll(), races.getAll(),
					racesResults.getAll());
		}

	}

	public static void saveSnapShot() throws IOException {
		try {
			jsonManager.save(actualSnapShot);
		} catch (Exception e) {
			System.out.println("Error al guardar los datos");
		}

	}

	public static void createNewGarage() {
		try {
			System.out.println("Introduce el nombre:");
			String name = stringInput.nextLine();

			garages.addGarage(new Garage(name));
			System.out.println("El garaje ha sido creado");
			saveSnapShot();
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

			System.out.println("Selecciona un garaje");
			indexOfSelectedGarage = numberInput.nextInt();

			Garage modifiedGarage = garages.getGarage(indexOfSelectedGarage);
			System.out.println("El garaje escogido es:\n" + modifiedGarage + "\n");

			System.out.println("1-Modificar el nombre \n2-Añadir/Eliminar coches");
			switch (numberInput.nextInt()) {
			case 1:
				modifyingGarageName(modifiedGarage);
				break;

			case 2:
				modifyingGarageCars(modifiedGarage);
				break;
			}
		} else {
			System.out.println("No existe ningún garaje para modificar");
		}

	}

	public static void modifyingGarageName(Garage modifiedGarage) {
		try {

			String newName;

			System.out.println("Introduce el nuevo nombre:");
			newName = stringInput.nextLine();

			System.out.println(
					"Los datos del programa serán:\n Name: " + newName + "\nConfirmar los cambios (1-Si/2-No): ");

			if (numberInput.nextInt() == 1) {
				modifiedGarage.setName(newName);
				System.out.println("Operación completada");

				saveSnapShot();

			} else {
				System.out.println("Cambios descartados");
			}

		} catch (Exception ex) {
			System.out.println("Error cambiando el nombre del garaje");
		}
	}

	// TODO:Cambiar el nombre del método
	public static void modifyingGarageCars(Garage modifiedGarage) {
		int indexOfSelectedGarage;
		boolean thereAreNoCars;
		String newName;

		thereAreNoCars = modifiedGarage.getCars().isEmpty();

		if (thereAreNoCars) {
			System.out.println("El garaje seleccionado no tiene asignado ningún coche");
		} else {
			showCarsOfAGarage(modifiedGarage);
		}

		System.out.println("1-Asignar coche");
		if (!thereAreNoCars) {
			System.out.println("2-Eliminar coche del garaje");
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

			System.out.println("S:");
			indexOfSelectedCar = numberInput.nextInt();

			Car selectedCar = carsWithoutGarage.get(indexOfSelectedCar);
			System.out.println(
					"El coche seleccionado es:\n" + selectedCar.toString() + "\nConfirmar los cambios (1-Si/2-No): ");

			if (numberInput.nextInt() == 1) {
				garage.addCar(selectedCar);
				System.out.println("Operacion completada");

				saveSnapShot();
			}
		} catch (Exception ex) {
			System.out.println("Error al añadir un coche al garaje");
		}
	}

	public static void removeACarOfTheCarage(Garage garage) {
		try {

			int indexOfSelectedCar;
			ArrayList<Car> carsOfTheGarage = garage.getCars();
			showCarsOfAList(carsOfTheGarage);

			System.out.println("Selecciona el coche que será eliminado: ");
			indexOfSelectedCar = numberInput.nextInt();

			Car selectedCar = carsOfTheGarage.get(indexOfSelectedCar);
			System.out.println(
					"El coche selecionado es:\n" + selectedCar.toString() + "\nConfirmar los cambios (1-Si/2-No): : ");

			if (numberInput.nextInt() == 1) {
				garage.removeCar(selectedCar);
				System.out.println("Operación completada");

				saveSnapShot();
			}
		} catch (Exception ex) {
			System.out.println("Error al elminar un coche del garaje");
		}
	}

	public static void createNewCar() {

		try {

			String brand, model;

			System.out.println("Introduce la marca:");
			brand = stringInput.nextLine();
			System.out.println("Introduce elmodelo:");
			model = stringInput.nextLine();

			cars.addCar(new Car(brand, model));
			System.out.println("El coche ha sido creado");
			saveSnapShot();
		} catch (Exception e) {
			System.out.println("Error al crear un coche nuevo");
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

				System.out.println("Selecciona un coche:");
				indexOfSelectedCar = numberInput.nextInt();

				modifiedCar = cars.getCar(indexOfSelectedCar);
				System.out.println("El coche escogido es:\n" + modifiedCar.toString() + "\n");

				System.out.println("Introduce la nueva marca:");
				newBrand = stringInput.nextLine();
				System.out.println("Introduce el nuevo modelo:");
				newModel = stringInput.nextLine();

				System.out.println("Los datos del coche serán:\n Marca: " + newBrand + " - Modelo: " + newModel
						+ "\nConfirmar los cambios (1-Si/2-No): ");

				if (numberInput.nextInt() == 1) {
					modifiedCar.setBrand(newBrand);
					modifiedCar.setModel(newModel);
					System.out.println("Operacion completada");
					saveSnapShot();
				}
			} else {
				System.out.println("No existen coches para modificar");
			}
		} catch (Exception ex) {
			System.out.println("Error modificando un coche");
		}
	}

	public static void createNewTournament() {
		try {

			String name;
			int raceNumber;

			if (!garages.getGarageWithCars().isEmpty()) {

				System.out.println("Introduce el nombre:");
				name = stringInput.nextLine();
				System.out.println("Introduce el numero de carreras:");
				raceNumber = stringInput.nextInt();

				Tournament newTournament = new Tournament(name,
						ParticipantsGenerator.generateParticipants(chooseParticipatingGarages()), raceNumber);

				tournaments.addTournament(newTournament);
				saveSnapShot();

			} else {
				System.out.println("No hay garajes, o estos no contienen coches para poder participar");
			}
		} catch (Exception ex) {
			System.out.println("Error creando el torneo");
		}
	};

	public static ArrayList<Garage> chooseParticipatingGarages() {
		ArrayList<Garage> possibleGarage = garages.getGarageWithCars();
		ArrayList<Garage> garagesParticipants = new ArrayList<Garage>();

		Garage selectedGarage;
		boolean interruptor = true;
		int inputIndex;

		showGaragesOfAList(possibleGarage);

		System.out.println("Selecciona un garaje, -1 termina");
		while (true) {
			inputIndex = numberInput.nextInt();

			if (inputIndex != -1) {
				selectedGarage = possibleGarage.get(inputIndex);

				if (garagesParticipants.contains(selectedGarage)) {
					System.out.println("El garaje seleccionado ya está en el torneo");
				}
				System.out.println("El garaje seleccionado es:\n" + selectedGarage);
				System.out.println("¿Quieres añadir el garaje al torneo? \nConfirmar los cambios (1-Si/2-No): ");

				if (numberInput.nextInt() == 1) {
					garagesParticipants.add(selectedGarage);
					System.out.println("Garaje añadido \n Selecciona otro garaje o introduce -1 para terminar");

				}

			} else if (inputIndex == -1 && garagesParticipants.isEmpty()) {
				System.out.println("Selecciona, al menos, un garaje");
			} else {
				return garagesParticipants;
			}
		}

	}

	public static void modifyExistingTournament() {
		if (!tournaments.getAll().isEmpty()) {
			System.out.println(
					"1-Añadir una nueva carrera a un torneo \n2-Correr una carrera de un torneo \n3-Ver ranking de un torneo finalizado");

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
				System.out.println("Carrera añadida al torneo");
				saveSnapShot();
			} else {
				System.out.println("No hay ningún torneo al que se le pueda añadir una carrera");
			}

		} catch (Exception ex) {
			// TODO:Quitar
			System.out.println("Error creando la carrera");
		}
	}

	public static Race createNewRace(ArrayList<Car> raceParticipants) throws Exception {
		String name;
		int hours;
		Race newRace = null;

		System.out.println("Introduce el nombre: ");
		name = stringInput.nextLine();

		System.out.println("Selecciona el tipo de la carrera \n1-Carrera estandar \n2-Carrera eliminatoria");
		switch (numberInput.nextInt()) {

		// Crear carrera
		case 1:
			System.out.println("Introduce la duracion en horas");
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

				System.out.println("El ranking es: ");
				for (int i = 0; i < raceRanking.size(); i++) {
					System.out.println((i + 1) + raceRanking.get(i).toString());
				}

			} else {
				System.out.println("No hay ninguna carrera que pueda correr una carrera");
			}

		} catch (Exception ex) {
			System.out.println("Error corriendo una carrera");
		}
	}

	public static void showTournamentRanKing() {
		try {

			int indexOfSelectedTournament;
			Tournament selectedTournament;

			ArrayList<Tournament> unFinishedTournaments = tournaments.getTournamentsByState(true);

			if ((selectedTournament = chooseTournamentOfAList(unFinishedTournaments)) != null) {
				ArrayList<ScoreOfCar> tournamentRanking = racesResults.getResultsOfTournament(selectedTournament);

				System.out.println("El ranking del torneo es:");
				for (int i = 0; i < tournamentRanking.size(); i++) {
					System.out.println((i + 1) + "-" + tournamentRanking.get(i).toString());
				}

			} else {
				System.out.println("No hay torneos finalizados de llos que se puedan ver el ranking");
			}

		} catch (Exception e) {
			System.out.println("Error mostrando el ranking");
		}
	}

	public static Tournament chooseTournamentOfAList(ArrayList<Tournament> tournaments) {
		int indexOfSelectedTournament;
		Tournament selectedTournament;

		if (!tournaments.isEmpty()) {
			showTournamentsOfAList(tournaments);

			System.out.println("Selecciona el torneo del que quieras correr la siguiente carrera:");
			indexOfSelectedTournament = numberInput.nextInt();

			return selectedTournament = tournaments.get(indexOfSelectedTournament);

		}

		return null;
	}
}
