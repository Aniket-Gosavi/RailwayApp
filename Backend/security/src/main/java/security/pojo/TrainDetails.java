package security.pojo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "traindetails")
public class TrainDetails {
	
	@Transient
	public static final String sequenceName = "TrainSequence";

	@Id
	@NotNull
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String name;
	@NotEmpty(message = "TrainNo shoud not be empty")
	private int trainNo;
	private String boardingStation;
	private String destination;
	private String timing;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private double fair;
    private int ticketsAvailable;

}
