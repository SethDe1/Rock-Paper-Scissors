package RockPaperScissors;

import java.awt.*;
import javax.swing.*;

public class ServerGUI extends JFrame 
{
    JLabel status;	
    
    // Building The GUI for starting the Server
	public ServerGUI() 
	{

	    this.setTitle("Server for Rock Paper Scissors Game"); // Sets Title for the Frame
		this.setSize(400, 100);								  // Sets the Size of the Frame
		this.setResizable(false);				  			  // Makes the Frame not able to be dragged and resized
		this.setVisible(true);								  // Makes the Frame Visible to the User
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Makes the Frame close once the program exits
		getContentPane().setLayout(null);					  // Sets the Layout of the panel
		
		status = new JLabel("");							  // Creates the new label 
		status.setBounds(43, 36, 307, 25);					  // Sets the bounds for the JLabel
		getContentPane().add(status);						  // Adds the JLabel to the Panel

	}
}