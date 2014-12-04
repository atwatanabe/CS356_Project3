import java.util.HashSet;
import java.util.Set;


/*
 * @Author: Anthony Watanabe
 * CS 356
 * 
 * This class controls and has access to the Mini Twitter service
 * 
 * AdminPortal follows the Singleton design pattern
 */
public class AdminPortal 
{
	private Group root;
	private static AdminPortal instance;
	private User lastUpdater;
	private Set<String> IDs;
	
	public static AdminPortal getInstance()
	{
		//return (instance != null) ? instance : new AdminPortal();
		
		/*
		 * I realized that my original implementation never actually initializes @instance
		 */
		if (instance == null)
		{
			instance = new AdminPortal();
		}
		return instance;
	}
	
	private AdminPortal()
	{
		root = new Group();
		IDs = new HashSet<String>();
	}
	
	public int numUsers()
	{
		return root.size();
	}
	
	public void validateIDs()
	{
		for (String s : IDs)
		{
			if (s.contains(" "))
			{
				System.out.println("Invalid name: " + s);
				return;
			}
		}
	}
	
	public void setLatestUpdater(User u)
	{
		lastUpdater = u;
	}
	
	public void printLatestUpdateUser()
	{
		System.out.println("Last post was from " + lastUpdater.getID());
	}
	
	public void addUser(User u)
	{
		IDs.add(u.getID());
	}

	public void addGroup(Group g) 
	{
		IDs.add(g.getID());		
	}
	
}
