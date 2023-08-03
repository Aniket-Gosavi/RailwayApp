package security.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "Booking")
public class Booking {

	@Transient
	public static final String sequenceName = "BookingSequence";

	@Id
	@NotEmpty
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Email shoud not be empty")
	private String email;
	private int trainNo;
	@NotEmpty(message = "Travellers shoud not be empty")
	private int numberOfTravellers;
	private double fair;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public int getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(int numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

	public double getFair() {
		return fair;
	}

	public void setFair(double fair) {
		this.fair = fair;
	}

	public Booking(@NotEmpty int id, @NotEmpty(message = "Name shoud not be empty") String firstName, String lastName,
			@NotEmpty(message = "Email shoud not be empty") String email, int trainNo,
			@NotEmpty(message = "Travellers shoud not be empty") int numberOfTravellers, double fair) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.trainNo = trainNo;
		this.numberOfTravellers = numberOfTravellers;
		this.fair = fair;
	}

	public Booking() {
		super();
	}

}
