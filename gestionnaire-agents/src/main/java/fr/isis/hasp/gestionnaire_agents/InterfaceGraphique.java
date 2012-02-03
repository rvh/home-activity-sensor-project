package fr.isis.hasp.gestionnaire_agents;

import fr.isis.hasp.agentniveausonore.InterfaceDbMeter;
import fr.isis.hasp.agentnombrepersonne.InterfaceSimulateur;

/**
 * 
 * @author Arnaud
 */
public class InterfaceGraphique extends javax.swing.JFrame {
	
		private javax.swing.JFrame jFrame1;
		private javax.swing.JLabel jLabel1;
		private javax.swing.JLabel jLabel13;
		private javax.swing.JLabel jLabel14;
		private javax.swing.JLabel jLabel15;
		private javax.swing.JLabel jLabel16;
		private javax.swing.JLabel jLabel17;
		private javax.swing.JLabel jLabel2;
		private javax.swing.JLabel jLabel3;
		private javax.swing.JLabel jLabel4;
		private javax.swing.JLabel jLabel5;
		private javax.swing.JLabel jLabel6;
		private javax.swing.JSeparator jSeparator2;
		private javax.swing.JSeparator jSeparator3;
		private javax.swing.JLabel runAnaMouv;
		private javax.swing.JLabel runCoussin;
		private javax.swing.JLabel runEndorm;
		private javax.swing.JLabel runJournal;
		private javax.swing.JLabel runLumTmp;
		private javax.swing.JLabel runMouv;
		private javax.swing.JLabel runNbPers;
		private javax.swing.JLabel runReveil;
		private javax.swing.JLabel runSon;
		private javax.swing.JButton startAnaMouv;
		private javax.swing.JButton startCoussin;
		private javax.swing.JButton startEndorm;
		private javax.swing.JButton startJournal;
		private javax.swing.JButton startLumTmp;
		private javax.swing.JButton startMouv;
		private javax.swing.JButton startNbPers;
		private javax.swing.JButton startReveil;
		private javax.swing.JButton startSon;
		private javax.swing.JButton stopAnaMouv;
		private javax.swing.JButton stopCoussin;
		private javax.swing.JButton stopEndorm;
		private javax.swing.JButton stopJournal;
		private javax.swing.JButton stopLumTmp;
		private javax.swing.JButton stopMouv;
		private javax.swing.JButton stopNbPers;
		private javax.swing.JButton stopReveil;
		private javax.swing.JButton stopSon;
		
		private InterfaceSimulateur nbPersonne = null;
		private InterfaceDbMeter dbMeter = null;
	

	public InterfaceGraphique() {
		initComponents();
		nbPersonne = new InterfaceSimulateur();
		dbMeter = new InterfaceDbMeter();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					InterfaceGraphique.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					InterfaceGraphique.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					InterfaceGraphique.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					InterfaceGraphique.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		jFrame1 = new javax.swing.JFrame();
		jLabel1 = new javax.swing.JLabel();
		startCoussin = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		startLumTmp = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		startNbPers = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		startSon = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		startMouv = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		startJournal = new javax.swing.JButton();
		stopCoussin = new javax.swing.JButton();
		stopLumTmp = new javax.swing.JButton();
		stopNbPers = new javax.swing.JButton();
		stopSon = new javax.swing.JButton();
		stopMouv = new javax.swing.JButton();
		stopJournal = new javax.swing.JButton();
		runCoussin = new javax.swing.JLabel();
		runNbPers = new javax.swing.JLabel();
		runSon = new javax.swing.JLabel();
		runMouv = new javax.swing.JLabel();
		runJournal = new javax.swing.JLabel();
		runLumTmp = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		runReveil = new javax.swing.JLabel();
		runAnaMouv = new javax.swing.JLabel();
		runEndorm = new javax.swing.JLabel();
		startEndorm = new javax.swing.JButton();
		startReveil = new javax.swing.JButton();
		startAnaMouv = new javax.swing.JButton();
		stopAnaMouv = new javax.swing.JButton();
		stopReveil = new javax.swing.JButton();
		stopEndorm = new javax.swing.JButton();
		jSeparator2 = new javax.swing.JSeparator();
		jSeparator3 = new javax.swing.JSeparator();

		javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(
				jFrame1.getContentPane());
		jFrame1.getContentPane().setLayout(jFrame1Layout);
		jFrame1Layout.setHorizontalGroup(jFrame1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		jFrame1Layout.setVerticalGroup(jFrame1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gestion des agents");
		setLocationByPlatform(true);
		setResizable(false);

		jLabel1.setText("Agent Coussin");

		startCoussin.setText("Start");
		startCoussin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startCoussinActionPerformed(evt);
			}
		});

		jLabel2.setText("Agent Luminosit� - Temp�rature");

		startLumTmp.setText("Start");
		startLumTmp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startLumTmpActionPerformed(evt);
			}
		});

		jLabel3.setText("Agent Nombre de personne");

		startNbPers.setText("Start");
		startNbPers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startNbPersActionPerformed(evt);
			}
		});

		jLabel4.setText("Agent Volume Sonore");

		startSon.setText("Start");
		startSon.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startSonActionPerformed(evt);
			}
		});

		jLabel5.setText("Agent Mouvement");

		startMouv.setText("Start");
		startMouv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startMouvActionPerformed(evt);
			}
		});

		jLabel6.setText("Agent Journalisation");

		startJournal.setText("Start");
		startJournal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startJournalActionPerformed(evt);
			}
		});

		stopCoussin.setText("Stop");
		stopCoussin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopCoussinActionPerformed(evt);
			}
		});

		stopLumTmp.setText("Stop");
		stopLumTmp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopLumTmpActionPerformed(evt);
			}
		});

		stopNbPers.setText("Stop");
		stopNbPers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopNbPersActionPerformed(evt);
			}
		});

		stopSon.setText("Stop");
		stopSon.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopSonActionPerformed(evt);
			}
		});

		stopMouv.setText("Stop");
		stopMouv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopMouvActionPerformed(evt);
			}
		});

		stopJournal.setText("Stop");
		stopJournal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopJournalActionPerformed(evt);
			}
		});

		runCoussin.setText("Stopped");

		runNbPers.setText("Stopped");

		runSon.setText("Stopped");

		runMouv.setText("Stopped");

		runJournal.setText("Stopped");

		runLumTmp.setText("Stopped");

		jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel13.setText("Agents d'analyse d'activit�");

		jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel14.setText("Agents Li�s aux capteurs");

		jLabel15.setText("Agent Analyse endomissement");

		jLabel16.setText("Agent Analyse reveil");

		jLabel17.setText("Agent Analyse mouvement");

		runReveil.setText("Stopped");

		runAnaMouv.setText("Stopped");

		runEndorm.setText("Stopped");

		startEndorm.setText("Start");
		startEndorm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startEndormActionPerformed(evt);
			}
		});

		startReveil.setText("Start");
		startReveil.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startReveilActionPerformed(evt);
			}
		});

		startAnaMouv.setText("Start");
		startAnaMouv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startAnaMouvActionPerformed(evt);
			}
		});

		stopAnaMouv.setText("Stop");
		stopAnaMouv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopAnaMouvActionPerformed(evt);
			}
		});

		stopReveil.setText("Stop");
		stopReveil.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopReveilActionPerformed(evt);
			}
		});

		stopEndorm.setText("Stop");
		stopEndorm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopEndormActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel6)
																				.addComponent(
																						jLabel5)
																				.addComponent(
																						jLabel4)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						jLabel1))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		60,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						runCoussin)
																				.addComponent(
																						runJournal)
																				.addComponent(
																						runMouv)
																				.addComponent(
																						runSon)
																				.addComponent(
																						runNbPers)
																				.addComponent(
																						runLumTmp))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						startLumTmp)
																				.addComponent(
																						startNbPers)
																				.addComponent(
																						startSon)
																				.addComponent(
																						startMouv)
																				.addComponent(
																						startJournal)
																				.addComponent(
																						startCoussin))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(8,
																										8,
																										8)
																								.addComponent(
																										stopCoussin))
																				.addComponent(
																						stopNbPers)
																				.addComponent(
																						stopLumTmp)
																				.addComponent(
																						stopSon)
																				.addComponent(
																						stopMouv)
																				.addComponent(
																						stopJournal)))
												.addComponent(
														jSeparator2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														397, Short.MAX_VALUE)
												.addComponent(
														jSeparator3,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														397, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel15)
																				.addComponent(
																						jLabel16)
																				.addComponent(
																						jLabel17))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		67,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										runReveil)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										startReveil)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										stopReveil))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										runEndorm)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										startEndorm)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										stopEndorm))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										runAnaMouv)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										startAnaMouv)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										stopAnaMouv)))))
								.addContainerGap())
				.addGroup(
						layout.createSequentialGroup().addGap(120, 120, 120)
								.addComponent(jLabel13)
								.addContainerGap(133, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup().addGap(128, 128, 128)
								.addComponent(jLabel14)
								.addContainerGap(135, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(10, 10, 10)
								.addComponent(jLabel14)
								.addGap(18, 18, 18)
								.addComponent(jSeparator2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(stopCoussin)
												.addComponent(startCoussin)
												.addComponent(runCoussin))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(stopLumTmp)
												.addComponent(startLumTmp)
												.addComponent(runLumTmp))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(stopNbPers)
												.addComponent(startNbPers)
												.addComponent(runNbPers))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(stopSon)
												.addComponent(startSon)
												.addComponent(runSon))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(stopMouv)
												.addComponent(startMouv)
												.addComponent(runMouv))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(stopJournal)
												.addComponent(startJournal)
												.addComponent(runJournal))
								.addGap(49, 49, 49)
								.addComponent(jLabel13)
								.addGap(18, 18, 18)
								.addComponent(jSeparator3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel15)
												.addComponent(stopEndorm)
												.addComponent(startEndorm)
												.addComponent(runEndorm))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel16)
												.addComponent(stopReveil)
												.addComponent(startReveil)
												.addComponent(runReveil))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel17)
												.addComponent(stopAnaMouv)
												.addComponent(startAnaMouv)
												.addComponent(runAnaMouv))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		stopAnaMouv.setEnabled(false);
		stopCoussin.setEnabled(false);
		stopEndorm.setEnabled(false);
		stopJournal.setEnabled(false);
		stopLumTmp.setEnabled(false);
		stopMouv.setEnabled(false);
		stopNbPers.setEnabled(false);
		stopReveil.setEnabled(false);
		stopSon.setEnabled(false);
		
		pack();
	}// </editor-fold>

	private void startCoussinActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startCoussin.setEnabled(false);
				stopCoussin.setEnabled(true);
				runCoussin.setText("Running...");
			}
		});
	}

	private void startLumTmpActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startLumTmp.setEnabled(false);
				stopLumTmp.setEnabled(true);
				runLumTmp.setText("Running...");
			}
		});
	}

	private void startNbPersActionPerformed(java.awt.event.ActionEvent evt) {
		nbPersonne.start();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startNbPers.setEnabled(false);
				stopNbPers.setEnabled(true);
				nbPersonne.setVisible(true);
				runNbPers.setText("Running...");
			}
		});
	}

	private void startSonActionPerformed(java.awt.event.ActionEvent evt) {
		dbMeter.start();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startSon.setEnabled(false);
				stopSon.setEnabled(true);
				runSon.setText("Running...");
			}
		});
	}

	private void startMouvActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startMouv.setEnabled(false);
				stopMouv.setEnabled(true);
				runMouv.setText("Running...");
			}
		});
	}

	private void startJournalActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startJournal.setEnabled(false);
				stopJournal.setEnabled(true);
				runJournal.setText("Running...");
			}
		});
	}

	private void stopCoussinActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startCoussin.setEnabled(true);
				stopCoussin.setEnabled(false);
				runCoussin.setText("Stopped");
			}
		});
	}

	private void stopLumTmpActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startLumTmp.setEnabled(true);
				stopLumTmp.setEnabled(false);
				runLumTmp.setText("Stopped");
			}
		});
	}

	private void stopNbPersActionPerformed(java.awt.event.ActionEvent evt) {
		nbPersonne.stop();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startNbPers.setEnabled(true);
				stopNbPers.setEnabled(false);
				runNbPers.setText("Stopped");
				nbPersonne.setVisible(false);
			}
		});
	}

	private void stopSonActionPerformed(java.awt.event.ActionEvent evt) {
		dbMeter.stop();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startSon.setEnabled(true);
				stopSon.setEnabled(false);
				runSon.setText("Stopped");
			}
		});
	}

	private void stopMouvActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startMouv.setEnabled(true);
				stopMouv.setEnabled(false);
				runMouv.setText("Stopped");
			}
		});
	}

	private void stopJournalActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startJournal.setEnabled(true);
				stopJournal.setEnabled(false);
				runJournal.setText("Stopped");
			}
		});
	}

	private void startEndormActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startEndorm.setEnabled(false);
				stopEndorm.setEnabled(true);
				runEndorm.setText("Running...");
			}
		});
	}

	private void startReveilActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startReveil.setEnabled(false);
				stopReveil.setEnabled(true);
				runReveil.setText("Running...");
			}
		});
	}

	private void startAnaMouvActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startAnaMouv.setEnabled(false);
				stopAnaMouv.setEnabled(true);
				runAnaMouv.setText("Running...");
			}
		});
	}

	private void stopAnaMouvActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startAnaMouv.setEnabled(true);
				stopAnaMouv.setEnabled(false);
				runAnaMouv.setText("Stopped");
			}
		});
	}

	private void stopReveilActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startReveil.setEnabled(true);
				stopReveil.setEnabled(false);
				runReveil.setText("Stopped");
			}
		});
	}

	private void stopEndormActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				startEndorm.setEnabled(true);
				stopEndorm.setEnabled(false);
				runEndorm.setText("Stopped");
			}
		});
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new InterfaceGraphique().setVisible(true);
			}
		});
	}
}