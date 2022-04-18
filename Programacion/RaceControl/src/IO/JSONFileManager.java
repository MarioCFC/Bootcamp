package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import competition.IDataManager;
import competition.SnapShot;

public class JSONFileManager implements IDataManager {
	private static final String JSON_FILE_PATH = "res/StateFile.json";
	private static Gson gsonManager;

	@Override
	public SnapShot load() throws FileNotFoundException {
		return gsonManager.fromJson(new BufferedReader(new FileReader(JSON_FILE_PATH)), SnapShot.class);
	}

	@Override
	public void save(SnapShot state) throws IOException {
		Writer writter = Files.newBufferedWriter(Paths.get(JSON_FILE_PATH));
		gsonManager.toJson(state, writter);
	}
}
