package pratice.seven;

public class Test2 {

	public static void main(String[] args) {
		Player p1 = new Player("Jim");
		Player p2 = new Player("Jon");
		Player p3 = new Player("Don");
		Player p4 = new Player("Joanna");
		Player p5 = new Player("Mary");
		
		Table table = new Table();
		table.add(p1);
		table.add(p2);
		table.add(p3);
		table.add(p4);
		table.add(p5);
		
		Dealer dealer = new Dealer();
		dealer.shuffle();
		
		for (Player p : table) {
			dealer.giveAcard(p);
			System.out.println(p);
			
		}

	}

}
