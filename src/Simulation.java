import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Simulation
{
	private static File inputFile;
	private static double calmPercentage = 50.0;
	private static int startingP = 0;
	private static int incrementP = 0;	
	public static void main(String[] args) throws InterruptedException
	{

		getValuesFromUsers();
		boolean showEvac = true;
		// System.out.println(inputFile.getAbsolutePath());
		// System.out.println(calmPercentage);
		// System.out.println(startingP);
		// System.out.println(incrementP);


		Building lab = new Building(inputFile);
		// System.out.println("Placing Agents");
		lab.placeAgents(startingP,calmPercentage);
		// System.out.println("initiating weights");
		lab.initiate();		// new Simulation();
		if (showEvac)
			lab.createGUI();

		// used for manual stepping
		// Scanner scan = new Scanner(System.in);
		int updateCount = 0;
		do
		{
			++updateCount;
			lab.update();
			// scan.next();
			if (showEvac)
				Thread.sleep(500);
		}
		while(!lab.isEmpty());
		System.out.println("Total number of Updates\n" + updateCount);
	}

	public static void getValuesFromUsers()
	{
		// int[] test = new int[1];
		// JFrame frame = new JFrame("Fire Emergency Simulatior Parameters");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(300, 400);
		// frame.setLayout(new FlowLayout());
		JTextField startingAmount = new JTextField(10);
		JTextField increment = new JTextField(5);
		JPanel myPanel = new JPanel();
		JFileChooser file = new JFileChooser();
		JSlider slider = new JSlider(0,100,50);
		// slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5); // step / interavl
   		slider.setSnapToTicks(true);
   		slider.setPaintTicks(true);

   		slider.setPaintLabels(true);
		myPanel.add(new JLabel("Starting Amount of People:"));
		myPanel.add(startingAmount);

		myPanel.add(new JLabel("People Increment:"));
		myPanel.add(increment);
		myPanel.add(new JLabel("Presense of Calm Agents"));
		myPanel.add(slider);
		myPanel.add(new JLabel("Select Building Template"));
		myPanel.add(file);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		int result = JOptionPane.showConfirmDialog(null, myPanel, "FES Program", JOptionPane.OK_CANCEL_OPTION);
		inputFile = file.getSelectedFile();
		startingP = Integer.parseInt(startingAmount.getText());
		incrementP = Integer.parseInt(increment.getText());
		calmPercentage = slider.getValue();
		// return test;
	}
}