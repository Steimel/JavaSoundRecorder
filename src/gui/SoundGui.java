package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;

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
public class SoundGui extends javax.swing.JFrame {
	private JPanel pnlMain;
	private JMenuBar mnuBar;
	private JMenuItem mnuFileOpen;
	JList lstFiles;
	private JMenuItem mnuFileExit;
	private JMenuItem mnuFileDelete;
	JTabbedPane tabMain;
	private JMenuItem mnuFileNew;
	private JMenu mnuFile;
	private JPanel pnlSidebar;
	private SoundController controller;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
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
				SoundGui inst = new SoundGui();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public SoundGui() {
		super();
		controller = new SoundController(this);
		initGUI();
		controller.init();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				mnuBar = new JMenuBar();
				setJMenuBar(mnuBar);
				{
					mnuFile = new JMenu();
					mnuBar.add(mnuFile);
					mnuFile.setText("File");
					{
						mnuFileNew = new JMenuItem();
						mnuFile.add(mnuFileNew);
						mnuFileNew.setText("New");
						mnuFileNew.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								controller.mnuNewPressedHandler();
							}

						});
					}
					{
						mnuFileOpen = new JMenuItem();
						mnuFile.add(mnuFileOpen);
						mnuFileOpen.setText("Open");
						mnuFileOpen.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								controller.mnuOpenPressedHandler();
							}

						});
					}
					{
						mnuFileDelete = new JMenuItem();
						mnuFile.add(mnuFileDelete);
						mnuFileDelete.setText("Delete");
						mnuFileDelete.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								controller.mnuDeletePressedHandler();
							}

						});
					}
					{
						mnuFileExit = new JMenuItem();
						mnuFile.add(mnuFileExit);
						mnuFileExit.setText("Exit");
						mnuFileExit.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								controller.mnuExitPressedHandler();
							}

						});
					}
				}
			}
			{
				pnlSidebar = new JPanel();
				GridLayout pnlSidebarLayout = new GridLayout(1, 1);
				pnlSidebarLayout.setHgap(5);
				pnlSidebarLayout.setVgap(5);
				pnlSidebarLayout.setColumns(1);
				getContentPane().add(pnlSidebar, BorderLayout.WEST);
				pnlSidebar.setLayout(pnlSidebarLayout);
				pnlSidebar.setPreferredSize(new java.awt.Dimension(174, 276));
				{
					ListModel lstFilesModel = new DefaultComboBoxModel(
							new String[] {});
					lstFiles = new JList();
					pnlSidebar.add(lstFiles);
					lstFiles.setModel(lstFilesModel);
				}
			}
			{
				pnlMain = new JPanel();
				GridLayout pnlMainLayout = new GridLayout(1, 1);
				pnlMainLayout.setHgap(5);
				pnlMainLayout.setVgap(5);
				pnlMainLayout.setColumns(1);
				getContentPane().add(pnlMain, BorderLayout.EAST);
				pnlMain.setLayout(pnlMainLayout);
				pnlMain.setPreferredSize(new java.awt.Dimension(372, 276));
				{
					tabMain = new JTabbedPane();
					pnlMain.add(tabMain);
					tabMain.setPreferredSize(new java.awt.Dimension(372, 282));
				}
			}
			pack();
			this.setSize(564, 337);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
