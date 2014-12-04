
/*
 * @Author: Anthony Watanabe
 * CS 356
 */

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Group extends Component 
{
	Set<Component> elements;	//Set enforces unique IDs
	
	public Group()
	{
		super();
		elements = new TreeSet<Component>();
		System.out.println("Un-named group created at " + creationTime);
		AdminPortal.getInstance().addGroup(this);
	}
	
	public Group(String id)
	{
		super(id);
		System.out.println("Group \"" + ID + "\" created at " + creationTime);
		AdminPortal.getInstance().addGroup(this);
	}
	
	public boolean add(Component newElement)
	{
		return elements.add(newElement);
	}
	
	public boolean remove(Component element)
	{
		return elements.remove(element);
	}
	
	public int size()
	{
		int sum = 0;
		Iterator<Component> iter = elements.iterator();
		while (iter.hasNext())
		{
			sum += ((Component)(iter.next())).size();
		}
		return sum;
	}
	
	public Set<Component> getElements()
	{
		return elements;
	}
	
}
