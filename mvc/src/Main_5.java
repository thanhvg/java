import javax.swing.*;

import model.TriangleModel;
import view.*;
import java.awt.GridLayout;

public class Main_5 {

	public static void main(String[] args) {
		model.TriangleModel model = new TriangleModel();
		GraphicalView vGraphical = new GraphicalView(model);
		TextView vText = new TextView(model);

		JFrame frame = new JFrame("Triangle");
		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Text View", vText);
		tabs.add("Graphical View", vGraphical);
		frame.getContentPane().add(tabs);

		frame.setSize(480, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}