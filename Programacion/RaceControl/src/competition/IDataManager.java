package competition;

//TODO:Cambiar nombre
public interface IDataManager {
	public SnapShot load() throws Exception;

	public void save(SnapShot estado) throws Exception;
}
