import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Bokningsystem extends JFrame {

	private Logik logik = new Logik();
	public static JButton btnTable[] = new JButton[16];
	private int bokning[][] = new int[16][7];
	private String name[][] = new String[16][7];
	private String DiaName = "";
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Bokningsystem frame = new Bokningsystem();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Bokningsystem v1.0 - By Kevin Berling & Joel Löfberg");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Bokningsystem() {
		int i;
		logik.day = 0; // Dag
		for (i = 0; i < 16; i++) {
			btnTable[i] = new JButton();
			btnTable[i].setText("Bord " + (i + 1));
			btnTable[i].setForeground(Color.white);
			btnTable[i].setBackground(new Color(0, 100, 0));
			btnTable[i].setActionCommand(Integer.toString(i));
		}
		for (int j = 0; j < 7; j++) {
			for (i = 0; i < 16; i++) {
				bokning[i][j] = logik.unbook;
			}
		}

		for (i = 0; i < 16; i++) {
			logik.iet = 0;
			btnTable[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
					JButton btn = (JButton) source;

					logik.iet = Integer.parseInt(e.getActionCommand());

					if (bokning[logik.iet][logik.day] == logik.unbook) {
						DialogRuta();
					} else if (bokning[logik.iet][logik.day] == logik.reserv) {
						DialogRutaReserv();
					} else if (bokning[logik.iet][logik.day] == logik.booked) {
						DialogRutaBokad();
					}

					if (logik.n == 0) {
						btn.setBackground(Color.red);
						bokning[logik.iet][logik.day] = logik.booked;
					} else if (logik.n == 1) {
						btn.setBackground(Color.GRAY);
						bokning[logik.iet][logik.day] = logik.reserv;
						name[logik.iet][logik.day] = DiaName;
						btn.setToolTipText("Bokad för " + DiaName);
					} else if (logik.n == 2) {
						btn.setBackground(new Color(0, 100, 0));
						bokning[logik.iet][logik.day] = logik.unbook;
						btn.setToolTipText(null);
					} else {
						System.out.println("Du har inte valt rätt JOBANI BLYAT!");
					}
				}
			});
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Platserna

		btnTable[0].setBounds(15, 50, 100, 50);
		btnTable[1].setBounds(15, 110, 100, 50);
		btnTable[2].setBounds(15, 170, 100, 50);
		btnTable[3].setBounds(15, 230, 100, 50);
		btnTable[4].setBounds(15, 290, 100, 50);
		btnTable[5].setBounds(15, 350, 100, 50);
		btnTable[6].setBounds(150, 440, 100, 50);
		btnTable[7].setBounds(260, 440, 100, 50);
		btnTable[8].setBounds(370, 440, 100, 50);
		btnTable[9].setBounds(480, 440, 100, 50);
		btnTable[10].setBounds(200, 320, 100, 50);
		btnTable[11].setBounds(310, 320, 100, 50);
		btnTable[12].setBounds(440, 320, 100, 50);
		btnTable[13].setBounds(550, 240, 100, 50);
		btnTable[14].setBounds(550, 170, 100, 50);
		btnTable[15].setBounds(550, 100, 100, 50);

		// Lägg till

		for (i = 0; i < 16; i++) {
			contentPane.add(btnTable[i]);
		}

		JLabel lblPatio = new JLabel("Patio");
		lblPatio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatio.setBounds(50, 25, 45, 15);
		contentPane.add(lblPatio);

		JLabel lblResturang = new JLabel("Resturang");
		lblResturang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResturang.setBounds(330, 400, 80, 20);
		contentPane.add(lblResturang);

		JLabel lblBokningSystem = new JLabel("BokningSystem v1.0");
		lblBokningSystem.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblBokningSystem.setBounds(200, 0, 380, 70);
		contentPane.add(lblBokningSystem);

		JLabel lblGreen = new JLabel(
				"<html><font color='green'>Grön</font> <font color='grey'>bakground = </font> <font color='green'>Ledig</font></html>");
		lblGreen.setBounds(260, 170, 140, 15);
		contentPane.add(lblGreen);

		JLabel lblRed = new JLabel(
				"<html><font color='red'>Röd</font> <font color='grey'>bakground = </font>  <font color='red'>Bokad</font></html>");
		lblRed.setBounds(260, 190, 140, 15);
		contentPane.add(lblRed);

		JLabel lblGrey = new JLabel("<html>Grå <font color='grey'>bakground = </font>Reserverad </html>");
		lblGrey.setBounds(260, 210, 180, 15);
		contentPane.add(lblGrey);

		JLabel lblDatum = new JLabel("Välj dag:");
		lblDatum.setBounds(300, 90, 140, 15);
		contentPane.add(lblDatum);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(logik.Dagar);
		comboBox.setBounds(300, 110, 105, 20);
		contentPane.add(comboBox);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("rawtypes")
				JComboBox cbox = (JComboBox) e.getSource();
				String selected = cbox.getSelectedItem().toString();

				switch (selected.charAt(0)) {
				case '1':
					logik.day = 0;
					int i = 0;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}

					break;
				case '2':
					logik.day = 1;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				case '3':
					logik.day = 2;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				case '4':
					logik.day = 3;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				case '5':
					logik.day = 4;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				case '6':
					logik.day = 5;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				case '7':
					logik.day = 6;
					for (i = 0; i < 16; i++) {
						btnTable[i].setToolTipText(null);
						if (bokning[i][logik.day] == logik.booked) {
							btnTable[i].setBackground(Color.red);
						} else if (bokning[i][logik.day] == logik.reserv) {
							btnTable[i].setBackground(Color.GRAY);
							btnTable[i].setToolTipText("Bokad för " + name[i][logik.day]);
						} else {
							btnTable[i].setBackground(new Color(0, 100, 0));
						}
					}
					break;
				}
			}

		});
		
		JToggleButton tglbtnDarkMode = new JToggleButton("Dark/Light Mode");
		tglbtnDarkMode.setBounds(520, 20, 130, 25);
		contentPane.add(tglbtnDarkMode);

		tglbtnDarkMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tglbtnDarkMode.isSelected()) {
					getContentPane().setBackground(new Color(20, 20, 20));
					/*
					 * lblPatio lblResturang lblBokningSystem lblGreen lblRed lblGrey lblDatum
					 */
					lblPatio.setForeground(Color.white);
					lblResturang.setForeground(Color.white);
					lblBokningSystem.setForeground(Color.white);
					lblDatum.setForeground(Color.white);
					lblGreen.setText(
							"<html><font color='green'>Grön</font> <font color='white'>bakground = </font> <font color='green'>Ledig</font></html>");
					lblRed.setText(
							"<html><font color='red'>Röd</font> <font color='white'>bakground = </font>  <font color='red'>Bokad</font></html>");
					lblGrey.setText("<html>Grå <font color='White'>bakground = </font>Reserverad </html>");
				} else {
					getContentPane().setBackground(Color.WHITE);
					lblPatio.setForeground(Color.black);
					lblResturang.setForeground(Color.black);
					lblBokningSystem.setForeground(Color.black);
					lblDatum.setForeground(Color.black);
					lblGreen.setText(
							"<html><font color='green'>Grön</font> <font color='grey'>bakground = </font> <font color='green'>Ledig</font></html>");
					lblRed.setText(
							"<html><font color='red'>Röd</font> <font color='grey'>bakground = </font>  <font color='red'>Bokad</font></html>");
					lblGrey.setText("<html>Grå <font color='grey'>bakground = </font>Reserverad </html>");
				}

			}
		});
	}

	public void DialogRuta() {
		Object[] options = { "Boka", "Reservera" };
		logik.n = JOptionPane.showOptionDialog(contentPane, "Vill du boka eller reservera bordet?", "Hantera bokning",
				0, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (logik.n == 1) {
			DiaName = JOptionPane.showInputDialog(contentPane, "Vem ska bordet reservera för?");
		}
	}

	public void DialogRutaReserv() {
		Object[] options = { "Avreservera", "Stäng" };
		int svar = JOptionPane.showOptionDialog(contentPane, "Redan reserverad, ta bort reservation?",
				"Hantera reservation", 0, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (svar == 0) {
			logik.n = 2;
		}
	}

	public void DialogRutaBokad() {
		Object[] options = { "Avboka", "Stäng" };
		int svar = JOptionPane.showOptionDialog(contentPane, "Redan Bokad, ta bort Bokning?", "Hantera Bokning", 0,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (svar == 0) {
			logik.n = 2;
		}
	}
}
