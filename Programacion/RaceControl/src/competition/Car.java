package competition;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "model")
public class Car {

	private static final int maxVelocityKmH = 120;
	private int velocityKmH = 0;
	private Double distanceMeters = 0.0;
	private String brand;
	private String model;

	private Garage sticker;

	private Car() {
	}

	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	public static int getMaxVelocityKmH() {
		return maxVelocityKmH;
	}

	public int getVelocityKmH() {
		return velocityKmH;
	}

	public void setVelocityKmH(int velocityMetersMin) {

		if (!(velocityMetersMin < 0) && !(velocityMetersMin > maxVelocityKmH))
			this.velocityKmH = velocityMetersMin;
	}

	public Double getDistance() {
		return distanceMeters;
	}

	public void setDistance(Double distanceMeters) {
		this.distanceMeters = distanceMeters;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Garage getSticker() {
		return sticker;
	}

	public void setSticker(Garage sticker) {
		this.sticker = sticker;
	}

	@Override
	public String toString() {
		return "Brand: " + brand + " - Model: " + model;
	}

}
