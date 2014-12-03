package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class SoundAppGui extends javax.swing.JFrame {
	JButton btnRecord;
	JButton btnPlay;
	JTextField txtFilename;
	private JLabel lblFilenameLabel;
	private JPanel pnlBottom;
	private JPanel pnlMid;
	private SoundAppController controller;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SoundAppGui inst = new SoundAppGui();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public SoundAppGui() {
		super();
		controller = new SoundAppController(this);
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(2, 1);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			thisLayout.setColumns(1);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{

				pnlMid = new JPanel();
				GridLayout pnlMidLayout = new GridLayout(3, 5);
				pnlMidLayout.setHgap(5);
				pnlMidLayout.setVgap(5);
				pnlMidLayout.setColumns(1);
				getContentPane().add(pnlMid);
				pnlMid.setLayout(pnlMidLayout);
				pnlMid.setPreferredSize(new java.awt.Dimension(399, 110));
				addBlanks(pnlMid, 6);
				{
					btnRecord = new JButton();
					pnlMid.add(btnRecord);
					btnRecord.setText("Record");
					btnRecord.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							controller.recordPressHandler();
						}

					});
				}
				addBlanks(pnlMid, 1);
				{
					btnPlay = new JButton();
					pnlMid.add(btnPlay);
					btnPlay.setText("Play");
					btnPlay.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							controller.playPressHandler();
						}

					});
				}
				addBlanks(pnlMid, 6);
				{
					pnlBottom = new JPanel();
					GridLayout pnlBottomLayout = new GridLayout(3, 4);
					pnlBottomLayout.setHgap(5);
					pnlBottomLayout.setVgap(5);
					pnlBottomLayout.setColumns(1);
					getContentPane().add(pnlBottom);
					pnlBottom.setLayout(pnlBottomLayout);
					pnlBottom.setPreferredSize(new java.awt.Dimension(399, 93));
					{
						addBlanks(pnlBottom, 5);
						lblFilenameLabel = new JLabel();
						pnlBottom.add(lblFilenameLabel);
						lblFilenameLabel.setText("Filename:");
					}
					{
						// addBlanks(pnlBottom, 3);
						txtFilename = new JTextField();
						pnlBottom.add(txtFilename);
						addBlanks(pnlBottom, 4);
					}
				}

			}
			pack();
			this.setSize(415, 231);
			this.setTitle("SoundApp");
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void addBlanks(JPanel pnl, int numBlanks) {
		for (int i = 0; i < numBlanks; i++) {
			pnl.add(new JLabel());
		}
	}

}
