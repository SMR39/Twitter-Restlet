package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.Map;


/**
 * Resource exposing the users
 *
 * @author Shashi Mohan Reddy Ravula
 * @author Ishan Tiwari
 *
 */
public class Tweet
{
	private String tweet;

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	
	public Tweet(String tweet)
	{
		this.tweet = tweet;
	}
	
}
