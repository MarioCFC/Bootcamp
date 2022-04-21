package raceControl;

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
	 * TODO:Añadir lo de repartir el premio TODO:Opcion de listar los distintos
	 * objetos(Coches,garajes,torneos,carreras) TODO:Si da tiempo separar los
	 * objetos en distintos JSON Resumiendo, hacer que cargue bien y refactorizar a
	 * lo bestia el menú
	 */
	public static void main(String[] args) throws Exception {

		loadSnapshot();

		/*
		 * for (int i = 0; i < 3; i++) { cars.addCar(new Car("model" + i, "brand" + i));
		 * }
		 * 
		 * for (int i = 0; i < 2; i++) { garages.addGarage(new Garage("gar" + i)); }
		 * 
		 * garages.getGarage(0).addCar(cars.getCar(0));
		 * garages.getGarage(0).addCar(cars.getCar(1));
		 * garages.getGarage(1).addCar(cars.getCar(2));
		 */
		loopSession: while (true) {
			System.out.println("1-Torneos \n2-Garajes \n3-Coches \n4-Finalizar");

			switch (numberInput.nextInt()) {
			case 1:

				loopGeneralMenuCar: while (true) {
					System.out.println(
							"\n1-Crear torneo \n2-Borrar torneo finalizado \n3-Listar torneos \n4-Gestionar torneos \n5-Atrás");
					switch (numberInput.nextInt()) {
					case 1:
						// Crear torneo
						createNewTournament();
						break;

					case 2:
						// Borrar torneo
						removeTournament();
						break;
					case 3:

						if (!tournaments.getAll().isEmpty()) {
							System.out.println("\nLos torneos almacenados son:");
							showElementsOfList(tournaments.getAll());
						} else {
							System.out.println("No hay ningun torneo almacenado");
						}
						break;

					case 4:
						// Gestionar torneos
						modifyExistingTournament();
						break;

					case 5:
						saveSnapShot();
						System.out.println();
						break loopGeneralMenuCar;
					}
				}
				break;

			case 2:

				loopGeneralMenuGarage: while (true) {

					// Menu garages
					System.out.println(
							"\n1-Crear garaje \n2-Borrar garaje \n3-Listar garajes \n4-Gestionar garajes existentes \n5-Atrás");

					switch (numberInput.nextInt()) {

					case 1:
						// Crear garaje
						createNewGarage();
						break;

					case 2:
						removeGarage();
						break;

					case 3:
						if (!garages.getAll().isEmpty()) {
							System.out.println("\nLos garajes alamacenados son:");
							showElementsOfList(garages.getAll());
						} else {
							System.out.println("No hay ningun garaje almacenado");
						}

						break;
					case 4:
						// Modificar un garaje existente
						showModifyGarageOptionMenu();
						break;

					case 5:
						System.out.println();
						break loopGeneralMenuGarage;
					}
				}
				break;
			case 3:
				loopGeneralMenuCar: while (true) {
					// Menu coches
					System.out.println(
							"\n1-Crear coche \n2-Borrar coche sin garaje \n3-Listar coches \n4-Modificar los datos de un coche \n5-Atrás");

					switch (numberInput.nextInt()) {
					// Crear coche
					case 1:
						createNewCar();
						break;

					case 2:
						removeCarWithOutGarage();
						break;
					case 3:
						if (!cars.getAll().isEmpty()) {
							System.out.println("\nLos coches sin garaje son:");
							showElementsOfList(cars.getAll());
						} else {
							System.out.println("No hay ningun coche almacenado");
						}

						break;
					case 4:
						modifyingDataOfCar();
						break;

					case 5:
						System.out.println();
						break loopGeneralMenuCar;
					}
				}
				break;
			case 4:
				saveSnapShot();
				System.out.println("Sesión finalizada");
				break loopSession;

			}
		}

	}

	public static void removeTournament() {
		try {

			ArrayList<Tournament> unfinishedTournaments = tournaments.getTournamentsByState(true);
			Tournament selectedTournament;

			if (!unfinishedTournaments.isEmpty()) {
				System.out.println("Selecciona el torneo a eliminar:");
				selectedTournament = selectObjectOfListMenu(unfinishedTournaments);

				if (showConfirmAction()) {
					tournaments.removeTournament(selectedTournament);
					saveSnapShot();
					System.out.println("Torneo eliminado");
				}
			} else {
				System.out.println("No hay ningún torneo finalizado alamacenado");
			}
		} catch (Exception e) {
			System.out.println("\nError al borrar el torneo");
		}
	}

	public static void removeGarage() {
		try {
			ArrayList<Garage> garagesWithOutCars = garages.getGarageWithOutCars();
			Garage selectedGarage;

			if (!garagesWithOutCars.isEmpty()) {
				System.out.println("\nLos garajes sin coches son:");
				showElementsOfList(garagesWithOutCars);
				System.out.println("\nSelecciona el garaje a eliminar:");
				selectedGarage = selectObjectOfListMenu(garagesWithOutCars);

				if (showConfirmAction()) {
					garages.removeGarage(selectedGarage);
					saveSnapShot();
					System.out.println("Garaje eliminado");
				}
			} else {
				System.out.println("No hay ningún garaje alamacenado");
			}
		} catch (Exception e) {
			System.out.println("\nError al borrar el garaje");
		}
	}

	public static void removeCarWithOutGarage() {
		try {

			ArrayList<Car> carsWithOutGarage = cars.getCarsWithoutGarage();
			Car selectedCar;

			if (!carsWithOutGarage.isEmpty()) {
				System.out.println("\nLos coches sin garaje son:");
				showElementsOfList(carsWithOutGarage);
				System.out.println("Selecciona el coche a eliminar:");
				selectedCar = selectObjectOfListMenu(carsWithOutGarage);

				if (showConfirmAction()) {
					cars.removeCar(selectedCar);
					saveSnapShot();
					System.out.println("Coche eliminado");
				}
			} else {
				System.out.println("No hay ningún coche sin garaje alamacenado");
			}
		} catch (Exception e) {
			System.out.println("\nError al borrar el coche");
		}
	}

	public static void loadSnapshot() throws StreamReadException {
		try {
			actualSnapShot = jsonManager.load();
		} catch (Exception e) {
			System.out.println("Archivo no encontrado, creando uno nuevo");
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

	public static void saveSnapShot() {
		try {
			jsonManager.save(actualSnapShot);
		} catch (Exception e) {
			System.out.println("\nError al guardar los datos");
		}

	}

	// Añadir confirmacion
	public static void createNewGarage() {
		try {
			System.out.println("\nIntroduce el nombre:");
			String name = stringInput.nextLine();

			if (showConfirmAction()) {
				garages.addGarage(new Garage(name));
				System.out.println("El garaje ha sido creado");
				saveSnapShot();
			}

		} catch (Exception ex) {
			System.out.println("\nError al crear el garaje");
		}

	}

	// TODO:Plantear subir la opciones de añadir y eliminar a este menú
	public static void showModifyGarageOptionMenu() {
		Garage modifiedGarage = null;
		ArrayList<Garage> allGarages = garages.getAll();

		if (!allGarages.isEmpty()) {

			loopManageGarage: while (true) {

				if (modifiedGarage == null) {
					System.out.println("\nSelecciona un garaje:");
					showElementsOfList(garages.getAll());

					modifiedGarage = selectObjectOfListMenu(garages.getAll());

					System.out.println("\nEl garaje escogido es:\n" + modifiedGarage + "\n");

				}

				System.out.println("\n1-Modificar el nombre \n2-Añadir/Eliminar coche \n3-Atrás");

				switch (numberInput.nextInt()) {
				case 1:
					modifyingGarageName(modifiedGarage);
					break;

				case 2:
					modifyingGarageCars(modifiedGarage);
					break;

				case 3:
					break loopManageGarage;
				}
			}
		} else {
			System.out.println("No hay ningún garaje almacenado");
		}

	}

	public static void modifyingGarageName(Garage modifiedGarage) {
		try {

			String newName;

			System.out.println("\nIntroduce un nuevo nombre:");
			newName = stringInput.nextLine();

			// Confirmacion
			System.out.println("\nLos datos del garaje serán:\n Name: " + newName);

			if (showConfirmAction()) {
				modifiedGarage.setName(newName);
				System.out.println("Operación completada");
				saveSnapShot();
			}

		} catch (Exception ex) {
			System.out.println("\nError cambiando el nombre del garaje");
		}
	}

	public static void modifyingGarageCars(Garage modifiedGarage) {

		loopManageCarsOfGarage: while (true) {
			System.out.println("\n1-Asignar coche \n2-Eliminar coche \n3-Atrás");

			switch (numberInput.nextInt()) {
			case 1:
				addCarToGarage(modifiedGarage);
				break;

			case 2:
				removeACarOfTheCarage(modifiedGarage);
				break;

			case 3:
				break loopManageCarsOfGarage;
			}

		}

	}

	public static void addCarToGarage(Garage garage) {

		try {

			Car selectedCar;
			ArrayList<Car> carsWithoutGarage = cars.getCarsWithoutGarage();

			if (!cars.getCarsWithoutGarage().isEmpty()) {

				System.out.println("\nSelecciona un coche para añadirlo al garaje");

				showElementsOfList(carsWithoutGarage);
				selectedCar = selectObjectOfListMenu(carsWithoutGarage);

				System.out.println("\nEl coche seleccionado es:\n" + selectedCar.toString());

				// Confirmacion
				if (showConfirmAction()) {
					garage.addCar(selectedCar);
					System.out.println("Operacion completada");

					saveSnapShot();
				}
			} else {
				System.out.println("No hay almacenado ningún garaje");
			}
		} catch (Exception ex) {
			System.out.println("\nError al añadir un coche al garaje");
		}
	}

	public static void removeACarOfTheCarage(Garage garage) {
		try {
			ArrayList<Car> carsOfGarage = garage.getCars();
			if (!carsOfGarage.isEmpty()) {
				System.out.println("Selecciona el coche que será eliminado: ");

				showElementsOfList(carsOfGarage);
				Car selectedCar = selectObjectOfListMenu(carsOfGarage);

				// Confirmacion
				System.out.println("El coche selecionado es:\n" + selectedCar.toString());

				if (showConfirmAction()) {
					garage.removeCar(selectedCar);
					System.out.println("Operación completada");

					saveSnapShot();
				}
			}
		} catch (Exception ex) {
			System.out.println("\nError al elminar un coche del garaje");
		}
	}

	public static void createNewCar() {

		try {

			String brand;
			String model;

			System.out.println("\nIntroduce la marca:");
			brand = stringInput.nextLine();
			System.out.println("Introduce el modelo:");
			model = stringInput.nextLine();

			if (showConfirmAction()) {
				cars.addCar(new Car(brand, model));
				saveSnapShot();
				System.out.println("El coche ha sido creado");

			}

		} catch (Exception e) {
			System.out.println("\nError al crear el coche\n");
		}
	}

	public static void modifyingDataOfCar() {

		try {
			int indexOfSelectedCar;
			String newBrand, newModel;
			Car modifiedCar;
			ArrayList<Car> allCars = cars.getAll();

			if (!allCars.isEmpty()) {

				System.out.println("\nSelecciona un coche:");
				showElementsOfList(allCars);
				modifiedCar = selectObjectOfListMenu(allCars);

				System.out.println("\nEl coche escogido es:\n" + modifiedCar.toString() + "\n");

				// TODO:A lo mejor quitar en un nuevo metodo(Menu Introducir datos coche)
				System.out.println("Introduce la nueva marca:");
				newBrand = stringInput.nextLine();
				System.out.println("Introduce el nuevo modelo:");
				newModel = stringInput.nextLine();

				System.out.println("\nLos datos del coche serán:\n Marca: " + newBrand + " - Modelo: " + newModel);

				if (showConfirmAction()) {
					modifiedCar.setBrand(newBrand);
					modifiedCar.setModel(newModel);

					System.out.println("Operacion completada");
					saveSnapShot();
				}

			} else {
				System.out.println("No existe ningún coche");
			}
		} catch (Exception ex) {
			System.out.println("\nError al modificar el coche");
		}
	}

	public static void createNewTournament() {
		try {

			String name;
			int raceNumber;

			if (!garages.getGarageWithCars().isEmpty()) {

				System.out.println("\nIntroduce el nombre:");
				name = stringInput.nextLine();

				System.out.println("Introduce el numero de carreras:");
				raceNumber = numberInput.nextInt();

				Tournament newTournament = new Tournament(name,
						ParticipantsGenerator.generateParticipants(chooseParticipatingGarages()), raceNumber);

				// Todo:Mostrar datos torneo
				if (showConfirmAction()) {
					tournaments.addTournament(newTournament);
					saveSnapShot();
				}

			} else {
				System.out.println("No hay garajes, o estos no contienen coches para poder participar");
			}
		} catch (Exception ex) {
			System.out.println("\nError creando el torneo");
		}
	}

	public static ArrayList<Garage> chooseParticipatingGarages() {
		ArrayList<Garage> possibleGarage = garages.getGarageWithCars();
		ArrayList<Garage> garagesParticipants = new ArrayList<Garage>();

		Garage selectedGarage;
		boolean interruptor = true;
		int inputIndex;

		System.out.println("\nLos garajes con coches son:");
		showElementsOfList(possibleGarage);

		System.out.println("\nSelecciona un garaje, -1 termina");

		while (true) {
			inputIndex = numberInput.nextInt();

			if (inputIndex != -1) {
				selectedGarage = possibleGarage.get(inputIndex);

				if (garagesParticipants.contains(selectedGarage)) {
					System.out.println("\nEl garaje seleccionado ya está en el torneo");
				} else {
					System.out.println("\nEl garaje seleccionado es:\n" + selectedGarage.toString());

					garagesParticipants.add(selectedGarage);
					System.out.println("\nGaraje añadido. Selecciona otro garaje o introduce -1 para terminar");
				}
			} else if (inputIndex == -1 && garagesParticipants.isEmpty()) {
				System.out.println("\nSelecciona, al menos, un garaje");
			} else {
				return garagesParticipants;
			}
		}

	}

	public static void modifyExistingTournament() {
		if (!tournaments.getAll().isEmpty()) {

			loopManageTournamentMenu: while (true) {

				System.out.println(
						"\n1-Añadir una nueva carrera a un torneo \n2-Correr una carrera de un torneo \n3-Ver ranking de un torneo finalizado \n4-Atrás");

				switch (numberInput.nextInt()) {

				case 1:
					createARaceInTournament();
					break;

				case 2:
					runRaceOfUnfinishedTournament();
					break;
				case 3:
					showTournamentRanking();
					break;
				case 4:
					break loopManageTournamentMenu;
				}
			}
		} else {
			System.out.println("No existe ningún torneo");
		}
	}

	public static void createARaceInTournament() {

		try {

			Tournament selectedTournament;

			ArrayList<Tournament> unFinishedTournaments = tournaments.getTournamentsThatCanAddNewRaces();

			if (!unFinishedTournaments.isEmpty()) {
				System.out.println("\nSelecciona un torneo para crear la carrera: ");
				showElementsOfList(unFinishedTournaments);

				selectedTournament = selectObjectOfListMenu(unFinishedTournaments);

				Race createdRace = createNewRace(selectedTournament.getParticipants());

				if (showConfirmAction()) {
					selectedTournament.addRace(createdRace);
					System.out.println("La carrera ha sido añadida al torneo");

					saveSnapShot();
				}

			} else {
				System.out.println("No hay ningún torneo al que se le pueda añadir una carrera");
			}

		} catch (Exception ex) {
			System.out.println("\nError al crear/añadir la carrera");
		}
	}

	public static Race createNewRace(ArrayList<Car> raceParticipants) throws Exception {
		String name;
		int hours;
		Race newRace = null;

		System.out.println("\nIntroduce el nombre: ");
		name = stringInput.nextLine();

		System.out.println("\nSelecciona el tipo de la carrera: \n1-Carrera estandar \n2-Carrera eliminatoria");
		switch (numberInput.nextInt()) {

		// Crear carrera
		case 1:
			System.out.println("\nIntroduce la duracion en horas:");
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

			if (!unfinishedTournaments.isEmpty()) {
				System.out.println("\nEscoge un torneo para celebrar la carrera:");
				showElementsOfList(unfinishedTournaments);
				selectedTournament = selectObjectOfListMenu(unfinishedTournaments);

				Race runnedRace = selectedTournament.getNextRaceToRun();

				if (showConfirmAction()) {
					runnedRace.run();

					saveSnapShot();
					ArrayList<ScoreOfCarInARace> raceRanking = racesResults.getResultOfARace(runnedRace);

					System.out.println("\nEl ranking es: ");
					for (int i = 0; i < raceRanking.size(); i++) {
						System.out.println((i + 1) + "-" + raceRanking.get(i).toString());
					}
				}

			} else {
				System.out.println("\nNo hay ninguna carrera que pueda correr una carrera");
			}

		} catch (Exception ex) {
			System.out.println("\nError celebrando una carrera");
		}
	}

	public static void showTournamentRanking() {
		try {

			int indexOfSelectedTournament;
			Tournament selectedTournament;

			ArrayList<Tournament> finishedTournaments = tournaments.getTournamentsByState(true);

			if (!finishedTournaments.isEmpty()) {

				System.out.println("\nSelecciona un torneo finalizado:");
				showElementsOfList(finishedTournaments);
				selectedTournament = selectObjectOfListMenu(finishedTournaments);

				ArrayList<ScoreOfCar> tournamentRanking = racesResults.getResultsOfTournament(selectedTournament);

				System.out.println("\nEl ranking del torneo es:");

				for (int i = 0; i < tournamentRanking.size(); i++) {
					System.out.println((i + 1) + "-" + tournamentRanking.get(i).toString());
				}

				if (tournamentRanking.size() > 1
						&& tournamentRanking.get(0).getScore() == tournamentRanking.get(1).getScore()) {
					System.out.println("\nLos ganadores del torneo, estando empatados son: \n1-"
							+ tournamentRanking.get(0).getCar().toString() + "\n"
							+ tournamentRanking.get(1).getCar().toString());
				} else {
					System.out.println("\nEl ganador del torneo es:\n" + tournamentRanking.get(0).getCar().toString());
				}

			} else {
				System.out.println("\nNo hay torneos finalizados de llos que se puedan ver el ranking");
			}

		} catch (Exception e) {
			System.out.println("\nError mostrando el ranking");
		}
	}

	public static <T> T selectObjectOfListMenu(ArrayList<T> objList) {
		return objList.get(numberInput.nextInt());

	}

	public static <T> void showElementsOfList(ArrayList<T> objList) {
		for (int i = 0; i < objList.size(); i++) {
			System.out.println(i + "-" + objList.get(i).toString());
		}
	}

	public static boolean showConfirmAction() {
		System.out.println("\nConfirma la acción (1-Si/2-No): ");

		if (numberInput.nextInt() == 2) {
			System.out.println("Operación cancelada");
			return false;
		}
		return true;

	}
}
