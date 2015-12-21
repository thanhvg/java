package pratice.five;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Iterable<Player>{
	List<Player> tab = new ArrayList<Player>();
	public void add(Player p) {
		tab.add(p);
	}
	@Override
	public Iterator<Player> iterator() {
		// TODO Auto-generated method stub
		Iterator<Player> ite = tab.iterator();
		
		
		return ite;
	}
	
//	private Iterator<Player> ite = tab.iterator();
//	@Override
//	public boolean hasNext() {
//		return ite.hasNext();
//	}
//	@Override
//	public Player next() {
//		return ite.next();
//	}
//	@Override
//	public void remove() {
//		ite.remove();		
//	}

}
