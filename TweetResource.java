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
	public class TweetResource extends ServerResource
	{

	    /** Backend. */
	    private Backend backend_;

	/**
     * Constructor.
     * Call for every single user request.
     */
    public TweetResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /* GET tweet from a particular user*/
    @Get("json")
    public Representation getUser() throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        Collection<JSONObject> jsontweets = new ArrayList<JSONObject>();
        int userId = Integer.valueOf(userIdString);
        JSONObject userObject = new JSONObject();
        User user_ = (User)backend_.getDatabase().getUser(userId);
        Collection<Tweet> tweets = user_.getTweet(); 
        for (Tweet tweet : tweets)
        {	
        	JSONObject current = new JSONObject();
        	current.put("tweet", tweet.getTweet());
        	jsontweets.add(current);
        }
        JSONArray jsonArray = new JSONArray(jsontweets);
        return new JsonRepresentation(jsonArray);
    }
    
    /* POST tweet for a particular user*/
    @Post("json")
    public Representation createTweet(JsonRepresentation representation)
        throws Exception
    {
    	String userIdString = (String) getRequest().getAttributes().get("userId");
    	int userId = Integer.valueOf(userIdString);
    	
        JSONObject object = representation.getJsonObject();
        String name = object.getString("tweet");
        // Save the tweet
        User user_ = (User)backend_.getDatabase().getUser(userId);
        Tweet tweet = new Tweet(name);
        user_.getTweet().add(tweet);
        
        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("tweet", tweet.getTweet());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}
