package IO;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import competition.IDataManager;
import competition.SnapShot;

public class JSONFileManager implements IDataManager {
	private static final ObjectMapper objMap = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	private static final String JSON_FILE_PATH = "res/StateFile.json";

	@Override
	public SnapShot load() throws StreamReadException, DatabindException, IOException {
		return objMap.readValue(new File(JSON_FILE_PATH), SnapShot.class);
	}

	@Override
	public void save(SnapShot snapshot) throws IOException {
		objMap.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_FILE_PATH), snapshot);
	}
}
