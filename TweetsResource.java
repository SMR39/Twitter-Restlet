package org.inria.restlet.mta.resources;


import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing the users
 *
 * @author Shashi Mohan Reddy Ravula
 * @author Ishan Tiwari
 *
 */
	public class TweetsResource extends ServerResource
	{

	    /** Backend. */
	    private Backend backend_;

	/**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource()
    {
    	super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }
    /**
    *
    * Returns the list of all the users
    *
    * @return  JSON representation of the users 
    * @throws JSONException
    */
    
    /* GET tweets for a all users*/
    @Get("json")
    public Representation getUser() throws Exception
    {
    	Collection<User> users = backend_.getDatabase().getUsers();
        Collection<JSONObject> jsonUserstweets = new ArrayList<JSONObject>();

        for (User user : users)
        {
        	/* iterating all the users*/
        	Collection<Tweet> tweets = user.getTweet(); 
        	for (Tweet tweet : tweets)
            {
        		/* iterating all the tweets from the users*/
            	JSONObject current = new JSONObject();
            	current.put(user.getName(), tweet.getTweet());
            	jsonUserstweets.add(current);
            }
        }

        JSONArray jsonArray = new JSONArray(jsonUserstweets);
        return new JsonRepresentation(jsonArray);
    }
}