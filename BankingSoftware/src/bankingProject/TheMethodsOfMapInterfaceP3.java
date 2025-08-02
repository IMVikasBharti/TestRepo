package bankingProject;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class TheMethodsOfMapInterfaceP3 
{
	public static void main(String[] args) 
	{
		// create a map to store
		// Integer as a key
		// String as a value
		LinkedHashMap<Double, String> map = new LinkedHashMap<Double, String>();

		map.put(1.0, "a");
		map.put(2.0, "b");
		map.put(3.0, "c");
		map.put(4.0, "d");
		
		// fetch the keys of map in form of a set
		Set<Double> set = map.keySet();
		
		// fetch the data from the set either using for-each-loop or using
		// iterator
		Iterator<Double> itr = set.iterator();
		
		while(itr.hasNext())
		{
			// fetch a key from the iterator
			Double key = itr.next();
			
			// fetch a value associated with this key
			String value = map.get(key);
			System.out.println("key: "+key+", value: "+value);
		}
	}
}