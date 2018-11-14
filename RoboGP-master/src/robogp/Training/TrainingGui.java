package robogp.Training;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import robogp.deck.Deck;
import robogp.deck.InstructionCard;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
import robogp.deck.InstructionCardGui;
public class TrainingGui extends javax.swing.JFrame {
    
    /*elenco delle InstructionCard esistenti, una per tipo.
        Non modificare la variabile*/
    private ArrayList<InstructionCard> ISTRUZIONI = null;
    
    //GUI delle InstructionCard aggiunte alla programmazione del robot
    private ArrayList<InstructionCardGui> istruzioniGui = null;
    
    //indice dell'istrunction card correntemente visualizzata
    private int indiceIstruzioneMostrata;
    
    private static TrainingGui singleInstance;

    /**
     * Creates new form TrainingGui
     */
    private TrainingGui(RobodromeView robodromo){
        initComponents();
        indiceIstruzioneMostrata = 0;
        ISTRUZIONI = new ArrayList<>();
        istruzioniGui = new ArrayList<>();
        this.setTabellone(robodromo);
        this.pack();
    }    
    
    public static TrainingGui getInstance(RobodromeView robodromo){
        if(TrainingGui.singleInstance == null)
            TrainingGui.singleInstance = new TrainingGui(robodromo);
        return TrainingGui.singleInstance;
    }
    
    protected void setTabellone(RobodromeView robodromo){
        this.containerTabellone.add(robodromo);
    }

    public ArrayList<InstructionCard> getIstruzioni() {
        return ISTRUZIONI;
    }

    //usato per inizializzare la variabile, non usarlo più e non modificare più questa variabile
    public void setIstruzioni(ArrayList<InstructionCard> istruzioni) {
        this.ISTRUZIONI = istruzioni;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        containerTabellone = new javax.swing.JPanel();
        containerOpzioni = new javax.swing.JPanel();
        containerProgramma = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        containerComandi1 = new javax.swing.JPanel();
        btnIstruzionePrecedente = new javax.swing.JButton();
        labelCardsCounter = new javax.swing.JLabel();
        btnIstruzioneSuccessiva = new javax.swing.JButton();
        panelCardGui = new javax.swing.JPanel();
        containerComandi = new javax.swing.JPanel();
        btnBackward = new javax.swing.JButton();
        btnPlayPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        containerSettings = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboRighe = new javax.swing.JComboBox<>();
        comboColonne = new javax.swing.JComboBox<>();
        comboDirezione = new javax.swing.JComboBox<>();
        menuListaTipiInstructionCard = new javax.swing.JComboBox<>();
        btnAvviaTraining = new javax.swing.JButton();
        btnAggiungiIstruzione = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 370));
        setResizable(false);

        containerTabellone.setBackground(new java.awt.Color(255, 255, 255));
        containerTabellone.setForeground(new java.awt.Color(255, 255, 255));
        containerTabellone.setName("containerTabellone"); // NOI18N
        containerTabellone.setRequestFocusEnabled(false);
        containerTabellone.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(containerTabellone, java.awt.BorderLayout.CENTER);

        containerOpzioni.setBackground(new java.awt.Color(255, 255, 255));
        containerOpzioni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        containerOpzioni.setName("containerOpzioni"); // NOI18N
        containerOpzioni.setPreferredSize(new java.awt.Dimension(230, 446));
        containerOpzioni.setLayout(new java.awt.BorderLayout());

        containerProgramma.setBackground(new java.awt.Color(255, 255, 255));
        containerProgramma.setBorder(javax.swing.BorderFactory.createTitledBorder("Istruzioni"));
        containerProgramma.setMinimumSize(new java.awt.Dimension(200, 12));
        containerProgramma.setName("containerProgramma"); // NOI18N
        containerProgramma.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        containerComandi1.setBackground(new java.awt.Color(255, 255, 255));
        containerComandi1.setName("containerComandi"); // NOI18N
        containerComandi1.setLayout(new java.awt.GridLayout());

        btnIstruzionePrecedente.setText("<<");
        btnIstruzionePrecedente.setToolTipText("go backward");
        btnIstruzionePrecedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIstruzionePrecedenteActionPerformed(evt);
            }
        });
        containerComandi1.add(btnIstruzionePrecedente);

        labelCardsCounter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCardsCounter.setText("7");
        containerComandi1.add(labelCardsCounter);

        btnIstruzioneSuccessiva.setText(">>");
        btnIstruzioneSuccessiva.setToolTipText("stop");
        btnIstruzioneSuccessiva.setActionCommand(">>");
        btnIstruzioneSuccessiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIstruzioneSuccessivaActionPerformed(evt);
            }
        });
        containerComandi1.add(btnIstruzioneSuccessiva);

        jPanel1.add(containerComandi1, java.awt.BorderLayout.PAGE_END);

        panelCardGui.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelCardGui, java.awt.BorderLayout.CENTER);

        containerProgramma.add(jPanel1, java.awt.BorderLayout.CENTER);

        containerOpzioni.add(containerProgramma, java.awt.BorderLayout.CENTER);

        containerComandi.setBackground(new java.awt.Color(255, 255, 255));
        containerComandi.setName("containerComandi"); // NOI18N
        containerComandi.setLayout(new java.awt.GridLayout(1, 0));

        btnBackward.setText("⏮");
        btnBackward.setToolTipText("go backward");
        btnBackward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackwardActionPerformed(evt);
            }
        });
        containerComandi.add(btnBackward);

        btnPlayPause.setText("▶⏸");
        btnPlayPause.setToolTipText("play / pause");
        btnPlayPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayPauseActionPerformed(evt);
            }
        });
        containerComandi.add(btnPlayPause);

        btnStop.setText("⏹");
        btnStop.setToolTipText("stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        containerComandi.add(btnStop);

        containerOpzioni.add(containerComandi, java.awt.BorderLayout.PAGE_START);

        containerSettings.setBackground(new java.awt.Color(204, 204, 255));
        containerSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        containerSettings.setMinimumSize(new java.awt.Dimension(50, 109));
        containerSettings.setPreferredSize(new java.awt.Dimension(226, 119));
        java.awt.GridBagLayout containerSettingsLayout = new java.awt.GridBagLayout();
        containerSettingsLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
        containerSettingsLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        containerSettings.setLayout(containerSettingsLayout);

        jLabel1.setText("Posizione e direzione");
        jLabel1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Schede Istruzione");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(jLabel2, gridBagConstraints);

        comboRighe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Row 1", "Row 2", "Row 3", "Row 4", "Row 5", "Row 6", "Row 7", "Row 8", "Row 9", "Row 10", "Row 11", "Row 12" }));
        comboRighe.setToolTipText("La riga iniziale di partenza del robot");
        comboRighe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRigheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboRighe, gridBagConstraints);

        comboColonne.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Col 1", "Col 2", "Col 3", "Col 4", "Col 5", "Col 6", "Col 7", "Col 8", "Col 9", "Col 10", "Col 11", "Col 12", "Col 13", "Col 14", "Col 15", "Col 16" }));
        comboColonne.setToolTipText("La colonna di partenza del robot nel robodromo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboColonne, gridBagConstraints);

        comboDirezione.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Est", "Sud", "Ovest", "Nord" }));
        comboDirezione.setToolTipText("La direzione iniziale del robot");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboDirezione, gridBagConstraints);

        menuListaTipiInstructionCard.setModel(new javax.swing.DefaultComboBoxModel<>(Deck.tipi));
        menuListaTipiInstructionCard.setToolTipText("Seleziona una scheda istruzione");
        menuListaTipiInstructionCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaTipiInstructionCardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(menuListaTipiInstructionCard, gridBagConstraints);

        btnAvviaTraining.setText("Avvia simulazione");
        btnAvviaTraining.setToolTipText("Avvia la sessione di allenamento con le impostazioni settate");
        btnAvviaTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvviaTrainingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        containerSettings.add(btnAvviaTraining, gridBagConstraints);

        btnAggiungiIstruzione.setText("Aggiungi");
        btnAggiungiIstruzione.setToolTipText("Aggiunge la scheda istruzione selezionata all'elenco di istruzioni del robot");
        btnAggiungiIstruzione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggiungiIstruzioneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(btnAggiungiIstruzione, gridBagConstraints);

        containerOpzioni.add(containerSettings, java.awt.BorderLayout.SOUTH);

        getContentPane().add(containerOpzioni, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n◾ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus aliquet ligula quis augue faucibus lobortis. Etiam ultrices efficitur lectus, non venenatis diam ultricies et. Nullam ut tellus quis sapien efficitur.\n");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayPauseActionPerformed

    private void comboRigheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRigheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRigheActionPerformed

    private void btnBackwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackwardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackwardActionPerformed

    private void btnAvviaTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvviaTrainingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvviaTrainingActionPerformed

    private void btnAggiungiIstruzioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggiungiIstruzioneActionPerformed
        InstructionCard card = new InstructionCard(this.menuListaTipiInstructionCard.getSelectedItem().toString());
        this.istruzioniGui.add(new InstructionCardGui(card));//crea la nuova GUI della instrution card e la aggiunge all'elenco
        //aggiorna la view della carta istruzione e aggiorna l'indice
        this.panelCardGui.removeAll();
        this.indiceIstruzioneMostrata = this.istruzioniGui.size() -1;
        this.panelCardGui.add(this.istruzioniGui.get(this.indiceIstruzioneMostrata), BorderLayout.CENTER);
        this.panelCardGui.validate();
        System.out.println("Programma: " + this.istruzioniGui);
        System.out.println( this.indiceIstruzioneMostrata + " Mostrata: " + this.istruzioniGui.get(this.indiceIstruzioneMostrata));
    }//GEN-LAST:event_btnAggiungiIstruzioneActionPerformed



    private void menuListaTipiInstructionCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaTipiInstructionCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuListaTipiInstructionCardActionPerformed

    private void btnIstruzionePrecedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIstruzionePrecedenteActionPerformed

    }//GEN-LAST:event_btnIstruzionePrecedenteActionPerformed

    private void btnIstruzioneSuccessivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIstruzioneSuccessivaActionPerformed
        if(indiceIstruzioneMostrata < (istruzioniGui.size() - 1)){
            //mostra l'istruzione successiva
        }
        //incrementa l'indice
    }//GEN-LAST:event_btnIstruzioneSuccessivaActionPerformed

    
    public void start() {
        /* Set the Nimbus look and feel */
        /*
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrainingGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainingGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainingGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainingGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TrainingGui().setVisible(true);
//            }
//        });
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggiungiIstruzione;
    private javax.swing.JButton btnAvviaTraining;
    private javax.swing.JButton btnBackward;
    private javax.swing.JButton btnIstruzionePrecedente;
    private javax.swing.JButton btnIstruzioneSuccessiva;
    private javax.swing.JButton btnPlayPause;
    private javax.swing.JButton btnStop;
    private javax.swing.JComboBox<String> comboColonne;
    private javax.swing.JComboBox<String> comboDirezione;
    private javax.swing.JComboBox<String> comboRighe;
    protected javax.swing.JPanel containerComandi;
    protected javax.swing.JPanel containerComandi1;
    protected javax.swing.JPanel containerOpzioni;
    protected javax.swing.JPanel containerProgramma;
    private javax.swing.JPanel containerSettings;
    protected javax.swing.JPanel containerTabellone;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelCardsCounter;
    private javax.swing.JComboBox<String> menuListaTipiInstructionCard;
    private javax.swing.JPanel panelCardGui;
    // End of variables declaration//GEN-END:variables

    private void updateLabelIndiceIstruzioneMostrata() {
        System.out.println("Istruzione mostrata: " + this.indiceIstruzioneMostrata);
        this.labelCardsCounter.setText(this.indiceIstruzioneMostrata + "");
    }


    
}