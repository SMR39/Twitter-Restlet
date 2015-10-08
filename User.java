package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.inria.restlet.mta.internals.Tweet;

/**
 *
 * User
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class User
{

    /** Internal id of the user.*/
    private int id_;

    /** Name of the user.*/
    private String name_;

    /** Age of the user.*/
    private int age_;
    
    /* Adding a	collection for tweets from a user */
    Collection<Tweet> userTweets = new ArrayList<Tweet>();
    /** To be added: collection of tweets of the user. */

    public User(String name, int age)
    {
        name_ = name;
        age_ = age;
    }

    public String getName()
    {
        return name_;
    }

    public void setName(String name)
    {
        name_ = name;
    }

    public int getAge()
    {
        return age_;
    }

    public void setAge(int age)
    {
        age_ = age;
    }

    public int getId()
    {
        return id_;
    }

    public void setId(int id)
    {
        id_ = id;
    }
    public void addTweet(Tweet e)
    {
    	userTweets.add(e);
    }
    public Collection<Tweet> getTweet()
    {
    	return userTweets;
    }

}
