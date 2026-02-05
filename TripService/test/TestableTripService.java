
import tripservice.trip.*;
import tripservice.user.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A testable version of the TripService class that allows setting the logged-in user and the trips to return for testing purposes.
 */
public class TestableTripService extends TripService {

    private User loggedUser;
    private List<Trip> tripsToReturn;

    /**
     * Creates a new TestableTripService with no logged-in user and an empty list of trips to return.
     */
    public TestableTripService() {
        tripsToReturn = new ArrayList<>();
    }

    /**
     * Creates a new TestableTripService with the specified logged-in user and an empty list of trips to return.
     *
     * @param loggedUser the user to set as the logged-in user
     */
    public TestableTripService(User loggedUser) {
        this.loggedUser = loggedUser;
        tripsToReturn = new ArrayList<>();
    }

    /**
     * Creates a new TestableTripService with no logged-in user and the specified list of trips to return.
     *
     * @param tripsToReturn the list of trips to return when findTripsByUser is called
     */
    public TestableTripService(List<Trip> tripsToReturn) {
        this.tripsToReturn = tripsToReturn;
    }

    /**
     * Creates a new TestableTripService with the specified logged-in user and list of trips to return.
     *
     * @param loggedUser the user to set as the logged-in user
     * @param tripsToReturn the list of trips to return when findTripsByUser is called
     */
    public TestableTripService(User loggedUser, List<Trip> tripsToReturn) {
        this.loggedUser = loggedUser;
        this.tripsToReturn = tripsToReturn;
    }

    /**
     * Sets the logged-in user for this TestableTripService.
     *
     * @param user the user to set as the logged-in user
     */
    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }

    /**
     * Sets the list of trips that will be returned when findTripsByUser is called.
     * 
     * @param trips the list of trips to return
     */
    public void setTripsToReturn(List<Trip> trips) {
        this.tripsToReturn = trips;
    }

    /**
     * Returns the list of trips that will be returned when findTripsByUser is called.
     *
     * @return the list of trips to return
     */
    public List<Trip> getTripsToReturn() {
        return tripsToReturn;
    }
    
    /**
     * Overrides the getLoggedUser method to return the user set for this TestableTripService.
     */
    @Override
    protected User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Overrides the findTripsByUser method to return the list of trips set for this TestableTripService.
     */
    @Override
    protected List<Trip> findTripsByUser(User user) {
        return tripsToReturn;
    }
}
