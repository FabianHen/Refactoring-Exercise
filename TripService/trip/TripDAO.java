package tripservice.trip;

import java.util.List;

import tripservice.exception.CollaboratorCallException;
import tripservice.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}