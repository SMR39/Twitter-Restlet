package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.User;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * Resource exposing a user.
 *
 * @author msimonin
 * @author ctedeschi
 *
 */
public class UserResource extends ServerResource
{

    /** Backend.*/
    private Backend backend_;

    /** Utilisateur géré par cette resource.*/
    private User user_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public UserResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /* GET and DELETE user requests*/
    @Get("json")
    public Representation getUser() throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);
        /* Checking for zero users */
        if (user_==null)
        {
        	JSONObject userObject = new JSONObject();
            userObject.put("name", "No such user");
        	return new JsonRepresentation(userObject);
        }
        JSONObject userObject = new JSONObject();
        userObject.put("name", user_.getName());
        userObject.put("age", user_.getAge());
        userObject.put("id", user_.getId());

        return new JsonRepresentation(userObject);
    }
    
    /* Deleting the user */
    @Delete("json")
    public void deleteUser() throws Exception
    {
    	Collection<User> users = backend_.getDatabase().getUsers();
    	String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);
    	users.remove(user_);
        
        
    }
    

}
