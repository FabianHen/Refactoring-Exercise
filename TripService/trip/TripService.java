package tripservice.trip;

import java.util.ArrayList;
import java.util.List;

import tripservice.exception.UserNotLoggedInException;
import tripservice.user.User;
import tripservice.user.UserSession;

/**
 * The TripService class provides functionality to retrieve trips for a user, ensuring that the logged-in user is a friend of the user whose trips are being requested.
 */
public class TripService {

	/**
	 * Returns a list of trips for the given user if the logged-in user is a friend of the given user.
	 * If there is no logged-in user, a UserNotLoggedInException is thrown. If the logged-in user is not a friend of the given user, an empty list is returned.
	 *
	 * @param user the user for whom to retrieve trips
	 * @return a list of trips for the given user if the logged-in user is a friend, otherwise an empty list
	 * @throws UserNotLoggedInException if there is no logged-in user
	 */
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		boolean isFriend = isFriend(user, loggedUser);
		if (isFriend) {
			return findTripsByUser(user);
		}
		return new ArrayList<>();
	}

	private static boolean isFriend(User user, User loggedUser) {
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		return isFriend;
	}

	/**
	 * Retrieves a list of trips for the specified user by calling the TripDAO.
	 *
	 * @param user the user for whom to find trips
	 * @return a list of trips associated with the specified user
	 */
	protected  List<Trip> findTripsByUser(User user){
		return TripDAO.findTripsByUser(user);
	}

	/**
	 * Retrieves the currently logged-in user from the UserSession.
	 *
	 * @return the currently logged-in user, or null if no user is logged in
	 */
	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
