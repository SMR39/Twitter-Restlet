package org.inria.restlet.mta.application;


import org.inria.restlet.mta.resources.TweetResource;
import org.inria.restlet.mta.resources.TweetsResource;
import org.inria.restlet.mta.resources.UserResource;
import org.inria.restlet.mta.resources.UsersResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author msimonin
 *
 */
public class MyTwitterApplication extends Application
{

    public MyTwitterApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/users", UsersResource.class);
        /* Added three more URI */
        router.attach("/users/{userId}", UserResource.class);
        router.attach("/users/{userId}/tweets", TweetResource.class);
        /* Different URI for getting Tweets from all the users because /users/ expects a user id i.e number all the time*/
        router.attach("/tweets", TweetsResource.class);
        return router;
    }
}
