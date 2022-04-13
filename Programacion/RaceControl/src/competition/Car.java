package competition;

public class Car {
	private static double maxSpeed;
	private final String brand;
	private final String model;
	private Garage sticker;

	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	public static double getMaxSpeed() {
		return maxSpeed;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public Garage getSticker() {
		return sticker;
	}

	public void setSticker(Garage sticker) {
		this.sticker = sticker;
	}

}
