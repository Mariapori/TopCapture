import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Robot;
import java.awt.Toolkit;

public class Formi1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7465103320816711785L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formi1 frame = new Formi1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Formi1() {
		setTitle("TopCapture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JCheckBox chckbxKokoNytt = new JCheckBox("Koko näyttö");
		contentPane.add(chckbxKokoNytt, BorderLayout.WEST);
		
		JCheckBox chckbxTiettyIkkuna = new JCheckBox("Tietty ikkuna");
		chckbxTiettyIkkuna.setEnabled(false);
		contentPane.add(chckbxTiettyIkkuna, BorderLayout.CENTER);
		
		Button button = new Button("Kaappaa kuva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxKokoNytt.isSelected()) {
					try {
						setVisible(false);
						BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						setVisible(true);
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setDialogTitle("Tallenna kuvankaappaus");   
						FileFilter filtteri = new FileTypeFilter(".png","Kuvankaappaus");
						fileChooser.addChoosableFileFilter((javax.swing.filechooser.FileFilter) filtteri);
						fileChooser.setAcceptAllFileFilterUsed(false);
						int userSelection = fileChooser.showSaveDialog(getContentPane());
						 
						if (userSelection == JFileChooser.APPROVE_OPTION) {
						    File fileToSave = fileChooser.getSelectedFile();
						    fileToSave = new File(fileToSave.toString() + ".png");
						    try {
								ImageIO.write(image, "png", fileToSave);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (HeadlessException | AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				
				if(chckbxTiettyIkkuna.isSelected()) {
					
				}
			}
		});
		contentPane.add(button, BorderLayout.SOUTH);
	}

}
