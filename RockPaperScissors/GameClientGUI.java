package RockPaperScissors;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Rectangle;

public class GameClientGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public JButton RockButton;			
	public JButton PaperButton;			
	public JButton ScissorButton;
	public JButton SubmitButton;
	public JLabel GameFeedbackBox;
	
	 
	public GameClientGUI() {
		setTitle("Rock Paper Scissors");
		setPreferredSize(new Dimension(450, 300));
		setName("Rock Paper Scissors");
		setBounds(new Rectangle(100, 100, 450, 300));
		setMinimumSize(new Dimension(450, 300));
		this.setVisible(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		SubmitButton = new JButton("");
		SubmitButton.setIcon(new ImageIcon(GameClientGUI.class.getResource("/RockPaperScissors/SubmitButton.jpg")));
		SubmitButton.setBounds(165, 341, 87, 29);
		getContentPane().add(SubmitButton);
		
		GameFeedbackBox = new JLabel();
		GameFeedbackBox.setEnabled(false);
		GameFeedbackBox.setBounds(421, 218, 149, 150);
		getContentPane().add(GameFeedbackBox);
		
		ScissorButton = new JButton("");
		ScissorButton.setIcon(new ImageIcon(GameClientGUI.class.getResource("/RockPaperScissors/ScissorsButton.jpg")));
		ScissorButton.setBounds(137, 218, 135, 102);
		getContentPane().add(ScissorButton);
		
		PaperButton = new JButton("");
		PaperButton.setIcon(new ImageIcon(GameClientGUI.class.getResource("/RockPaperScissors/PaperButton.jpg")));
		PaperButton.setBounds(253, 95, 129, 101);
		getContentPane().add(PaperButton);
		
		RockButton = new JButton("");
		RockButton.setIcon(new ImageIcon(GameClientGUI.class.getResource("/RockPaperScissors/RockButtonPic.jpg")));
		RockButton.setBounds(46, 95, 129, 101);
		getContentPane().add(RockButton);
		
		JLabel RPSBackground = new JLabel("");
		RPSBackground.setBounds(0, 0, 594, 398);
		RPSBackground.setIcon(new ImageIcon(GameClientGUI.class.getResource("/RockPaperScissors/RockPaperScissorsGUI.jpg")));
		setBounds(0, 0, 610, 437);
		getContentPane().add(RPSBackground);
		this.setVisible(true);
	}
}
