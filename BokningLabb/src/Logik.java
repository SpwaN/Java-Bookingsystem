import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logik {
	public String bokad = "";
	public String reserverad = "";
	public String Dagar[] = new String[7];
	public int n, day = 0, iet = 0;
	public int booked = 1, reserv = 2, unbook = 0;

	public Logik() {
		for (int j = 0; j < 7; j++) {
			Dagar[j] = (j + 1) + " " + LocalDate.now().plusDays(j).format(DateTimeFormatter.ofPattern("EEEE"));
		}
	}

	public void AvRegistreraBoka() {
		bokad = "";
		reserverad = "";
	}
}
