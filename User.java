
/*
 * @Author: Anthony Watanabe
 * CS 356 - Project 2
 * 
 * This class represents a Twitter user that can post messages/"tweets."
 * Each User can follow (and unfollow) other Users, and will notify any
 * of its followers of any tweets it makes.
 * 
 * User is the leaf component in the Composite design pattern.
 * User is an Observer of other Users, and Observable by other Users
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;


public class User extends Component implements Observer
{
	private String latestPost;		//the latest post made by this User
	private long latestUpdate;
	private Set<String> followers;	//a set of the ID's of Users following this User
	private Set<String> followings;	//a set of the ID's of Users this Use is following
	private List<String> newsFeed;	//all the posts that appear in this User's news feed
	
	
	/*
	 * Creates a User with an ID of @id and initializes fields.
	 */
	public User(String id)
	{
		super(id);
		latestPost = "";
		followers = new HashSet<String>();
		followings = new HashSet<String>();
		newsFeed = new ArrayList<String>();
		AdminPortal.getInstance().addUser(this);
		System.out.println("User \"" + ID + "\" created at " + creationTime);
	}
	
	/*
	 * Sets this User's latest post to @message, adds @message to this User's news feed,
	 * and notifies followers of the post
	 * 
	 * returns the time that the message was posted as a long
	 */
	public void post(String message)
	{
		latestPost = message;
		newsFeed.add(message);
		setChanged();
		notifyObservers();
		latestUpdate = System.currentTimeMillis();
		System.out.println(latestUpdate + " " + ID + ": " + message);
		AdminPortal.getInstance().setLatestUpdater(this);
	}
	
	/*
	 * updates news feed if @other has posted anything new
	 */
	public void update(Observable other, Object arg)
	{
		if (other.hasChanged())
		{
			if (other instanceof User)
			{
				User temp = (User) other;
				if (!newsFeed.contains(temp.getLatestPost()))
				{
					newsFeed.add(temp.getLatestPost());
				}
			}
		}
	}
	
	/*
	 * subscribes this User to @other so that this User will be notified
	 * whenever @other posts a message
	 */
	public void follow(User other)
	{
		followings.add(other.getID());
		other.addObserver(this);
	}
	
	/*
	 * unsubscribes this User from @other so that this User will no longer be
	 * notified whenever @other posts a message
	 */
	public void unfollow(User other)
	{
		followings.remove(other.getID());
		other.deleteObserver(this);
	}
	
	/*
	 * adds @other to the set of Users that will be notified when this User posts a message
	 */
	public void addFollower(User other)
	{
		followers.add(other.getID());
		addObserver(other);
	}
	
	/*
	 * removes @other from the set of Users that will be notified when this User posts a message
	 */
	public void removeFollower(User other)
	{
		followers.remove(other.getID());
		deleteObserver(other);
	}
	
	public Set<String> getFollowers() 
	{
		return followers;
	}
	
	public Set<String> getFollowings() 
	{
		return followings;
	}
	
	public List<String> getNewsFeed() 
	{
		return newsFeed;
	}
	
	public String getLatestPost()
	{
		return latestPost;
	}
	
	/*
	 * Makes finding the total number of users simpler
	 */
	public int size()
	{
		return 1;
	}
	
}
