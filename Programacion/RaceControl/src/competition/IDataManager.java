package competition;

//TODO:Cambiar nombre
public interface IDataManager {
	public void load(SnapShot estado) throws Exception;

	public void save(SnapShot estado) throws Exception;
}
