package robogp.Training;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
import java.awt.BorderLayout;
import java.util.ArrayList;
import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.deck.Deck;
import robogp.deck.InstructionCard;
import robogp.robodrome.view.RobodromeView;
import robogp.deck.InstructionCardGui;
import robogp.matchmanager.Posizione;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.Direction;
import robogp.robodrome.MovimentoController;
import robogp.robodrome.Robodrome;
public class TrainingGui extends javax.swing.JFrame {
    
    /**
     * array per creare la JComboBox di righe
     */
    private static final String[] RIGHE = { "Row 1", "Row 2", "Row 3", "Row 4", "Row 5", "Row 6", "Row 7", "Row 8", "Row 9", "Row 10", "Row 11", "Row 12" };
    
    /**
     * array per creare la JComboBox di righe
     */
    private static final String[] COLONNE = { "Col 1", "Col 2", "Col 3", "Col 4", "Col 5", "Col 6", "Col 7", "Col 8", "Col 9", "Col 10", "Col 11", "Col 12", "Col 13", "Col 14", "Col 15", "Col 16" };
    
    /**
     * array per creare JComboBox di direzioni
     */
    private static final String[] DIREZIONI = { "Ovest", "Nord", "Est", "Sud" };
    
    /**
     * elenco delle InstructionCard esistenti, una per tipo.
     */
    private ArrayList<InstructionCard> ISTRUZIONI = null;
    
    /**
     * GUI delle InstructionCard aggiunte alla programmazione del robot
     */
    private ArrayList<InstructionCardGui> istruzioniGui = null;
    
    /**
     * indice dell'istrunction card correntemente visualizzata
     */
    private int indiceIstruzioneMostrata;
    
    /**
     * segnalino del robot nella mappa
     */
    private ArrayList<RobotMarkerTraining> arrayRobot = null;
    
    /**
     * Controller del movimento
     */
    MovimentoController movimentoCtrl;
    
    //single instance del pattern singleton
    private static TrainingGui singleInstance;

    /**
     * Creates new form TrainingGui
     */
    private TrainingGui(RobodromeView robodromo, ArrayList<RobotMarker> arrayRobot, MovimentoController movimentoCtrl){
        initComponents();
        indiceIstruzioneMostrata = -1; //inizializzo a -1 perche' l'istruzione di indice 0 ancora non esiste
        ISTRUZIONI = new ArrayList<>();
        istruzioniGui = new ArrayList<>();
        this.movimentoCtrl = movimentoCtrl;
        this.setTabellone(robodromo);
        this.arrayRobot = new ArrayList<RobotMarkerTraining>();
        for(RobotMarker r : arrayRobot)
            this.arrayRobot.add((RobotMarkerTraining)r);
        this.pack();
    }    
    
    public static TrainingGui getInstance(RobodromeView robodromo, ArrayList<RobotMarker> arrayRobot, MovimentoController movimentoCtrl){
        if(TrainingGui.singleInstance == null){
            TrainingGui.singleInstance = new TrainingGui(robodromo, arrayRobot, movimentoCtrl);
        }
        return TrainingGui.singleInstance;
    }
    
    protected void setTabellone(RobodromeView robodromo){
        this.containerTabellone.add(robodromo);
    }

    public ArrayList<InstructionCard> getIstruzioni() {
        return ISTRUZIONI;
    }

    /**
     * usato per inizializzare la variabile, non usarlo più e non modificare più questa variabile
     */
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
        comboRighe = new javax.swing.JComboBox<>();
        comboColonne = new javax.swing.JComboBox<>();
        comboDirezione = new javax.swing.JComboBox<>();
        menuListaTipiInstructionCard = new javax.swing.JComboBox<>();
        btnAggiungiIstruzione = new javax.swing.JButton();
        btnAggiornaPosizione = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRimuoviIstruzione = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 370));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
        containerComandi1.setLayout(new java.awt.GridLayout(1, 0));

        btnIstruzionePrecedente.setText("<<");
        btnIstruzionePrecedente.setToolTipText("go backward");
        btnIstruzionePrecedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIstruzionePrecedenteActionPerformed(evt);
            }
        });
        containerComandi1.add(btnIstruzionePrecedente);

        labelCardsCounter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCardsCounter.setText("0/0");
        containerComandi1.add(labelCardsCounter);

        btnIstruzioneSuccessiva.setText(">>");
        btnIstruzioneSuccessiva.setToolTipText("stop");
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

        btnPlayPause.setBackground(new java.awt.Color(204, 255, 204));
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
        containerSettingsLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        containerSettings.setLayout(containerSettingsLayout);

        comboRighe.setModel(new javax.swing.DefaultComboBoxModel<>(this.RIGHE));
        comboRighe.setToolTipText("Imposta la riga iniziale di partenza del robot");
        comboRighe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboRigheItemStateChanged(evt);
            }
        });
        comboRighe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRigheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboRighe, gridBagConstraints);

        comboColonne.setModel(new javax.swing.DefaultComboBoxModel<>(this.COLONNE));
        comboColonne.setToolTipText("Imposta la colonna di partenza del robot nel robodromo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboColonne, gridBagConstraints);

        comboDirezione.setModel(new javax.swing.DefaultComboBoxModel<>(this.DIREZIONI));
        comboDirezione.setToolTipText("Imposta la direzione iniziale del robot");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(comboDirezione, gridBagConstraints);

        menuListaTipiInstructionCard.setModel(new javax.swing.DefaultComboBoxModel<>(Deck.tipi));
        menuListaTipiInstructionCard.setToolTipText("Seleziona una scheda istruzione da assegnare alla prorammazione del robot");
        menuListaTipiInstructionCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaTipiInstructionCardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(menuListaTipiInstructionCard, gridBagConstraints);

        btnAggiungiIstruzione.setText("➕");
        btnAggiungiIstruzione.setToolTipText("Aggiunge la scheda istruzione selezionata all'elenco di istruzioni del robot");
        btnAggiungiIstruzione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggiungiIstruzioneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(btnAggiungiIstruzione, gridBagConstraints);

        btnAggiornaPosizione.setText("Posiziona robot");
        btnAggiornaPosizione.setToolTipText("Posiziona il robot nella mappa con riga, colonna e direzioni scelti");
        btnAggiornaPosizione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggiornaPosizioneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        containerSettings.add(btnAggiornaPosizione, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clicca ▶⏸ quando sei pronto");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        containerSettings.add(jLabel1, gridBagConstraints);

        btnRimuoviIstruzione.setText("➖");
        btnRimuoviIstruzione.setToolTipText("rimuove l'ultima istruzione inserita");
        btnRimuoviIstruzione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRimuoviIstruzioneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        containerSettings.add(btnRimuoviIstruzione, gridBagConstraints);

        containerOpzioni.add(containerSettings, java.awt.BorderLayout.SOUTH);

        getContentPane().add(containerOpzioni, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setLineWrap(true);
        logTextArea.setRows(5);
        logTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(logTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
        System.out.println(this.getDrome().toString());
        this.resetTrainingGui();
    }//GEN-LAST:event_btnStopActionPerformed
    
    private void btnPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayPauseActionPerformed
        //controlla che il robot sia nella mappa
        if(this.arrayRobot.get(0).getLastPosition() == null) // se non è stato posizionato il primo robot
            this.placeRobots();
        avviaAllenamento();
    }//GEN-LAST:event_btnPlayPauseActionPerformed

    private void comboRigheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRigheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRigheActionPerformed

    private void btnBackwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackwardActionPerformed
        indietroDiUno();
    }//GEN-LAST:event_btnBackwardActionPerformed

    
    /**
     * aggiunge alla view del programma del robot l'istruzione selezionata
     */
    private void btnAggiungiIstruzioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggiungiIstruzioneActionPerformed
        this.aggiungiIstruzione();
    }//GEN-LAST:event_btnAggiungiIstruzioneActionPerformed

    /**
     * metodo synchronized per aggiungere un'istruzione al programma
     */
    private synchronized void aggiungiIstruzione(){
        //crea un'istruzione
        InstructionCard card = new InstructionCard(this.menuListaTipiInstructionCard.getSelectedItem().toString());
        //aggiunge l'istruzione al programma
        this.istruzioniGui.add(new InstructionCardGui(card));//crea la nuova GUI della instrution card e la aggiunge all'elenco
        //aggiorna l'indice perche' cambia l'istruzione che viene mostrata
        this.indiceIstruzioneMostrata++;
        //aggiorna la GUI del programma
        updateIstruzioneMostrata(this.indiceIstruzioneMostrata);
        
        System.out.println("Programma: " + this.istruzioniGui);
        System.out.println( this.indiceIstruzioneMostrata + " Mostrata: " + this.istruzioniGui.get(this.indiceIstruzioneMostrata));
        
        this.sendToLog("Aggiunta l'istruzione " + card);
    }
    
    /**
     * restituisce il robodromo istanziato in RobodromeView
     */
    private Robodrome getDrome(){
        return ((RobodromeView)this.containerTabellone.getComponent(0)).getDrome();
    }
    
    /**
     * aggiorna la view della scheda istruzione mostrata
     * @param indice indice dell'istruzione che si vuole mostrare
     */
    private synchronized void updateIstruzioneMostrata(int indice){
        
        this.panelCardGui.removeAll(); // svuota il pannello che mostra il programma
        if(indice > -1){
            //aggiorna la view della carta istruzione (mostra la card precedente) e aggiorna l'indice
            //solo se c'e' almeno un'istruzione nel programma
            this.indiceIstruzioneMostrata = indice; //mostra l'istruzione mostrata
            this.panelCardGui.add(this.istruzioniGui.get(indice), BorderLayout.CENTER);    
        }
        this.panelCardGui.validate();
        this.panelCardGui.repaint();
        this.updateLabelIndiceIstruzioneMostrata(this.indiceIstruzioneMostrata);
    }


    private void menuListaTipiInstructionCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaTipiInstructionCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuListaTipiInstructionCardActionPerformed

    /**
     * Mostra la GUI della scheda istruzione precedente
     */
    private void btnIstruzionePrecedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIstruzionePrecedenteActionPerformed
        System.out.println("Vai indietro di una istruzione");
        if(this.indiceIstruzioneMostrata > 0){
            //aggiorna l'indice dell'istruzione mostrata
            this.indiceIstruzioneMostrata--;
            //mostra l'istruzione precedente
            this.updateIstruzioneMostrata(this.indiceIstruzioneMostrata);
        }else{
            System.out.println("Non puoi andare più indietro di così");
        }
    }//GEN-LAST:event_btnIstruzionePrecedenteActionPerformed

    
    /**
     * mostra la GUI dell'istruzione successiva nel programma
     */
    private void btnIstruzioneSuccessivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIstruzioneSuccessivaActionPerformed
        System.out.println("Vai avanti di una istruzione");
        if(indiceIstruzioneMostrata < (istruzioniGui.size() - 1)){
            //aggiorna l'indice dell'istruzione mostrata
            this.indiceIstruzioneMostrata++;
            //mostra l'istruzione successiva
            this.updateIstruzioneMostrata(this.indiceIstruzioneMostrata);
        }else{
            System.out.println("Non puoi andare più avanti di così");
        }
    }//GEN-LAST:event_btnIstruzioneSuccessivaActionPerformed

    private void comboRigheItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboRigheItemStateChanged
        
    }//GEN-LAST:event_comboRigheItemStateChanged

    /**
     * cambia la posizione del segnalino del robot nel tabellone
     */
    private void btnAggiornaPosizioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggiornaPosizioneActionPerformed
        this.placeRobots();
        System.out.println("placeRobot: " + this.arrayRobot.get(0).toString());
    }//GEN-LAST:event_btnAggiornaPosizioneActionPerformed

    /**
     * metodo synchronized per cambiare la posizione del robot
     */
    private synchronized void placeRobots(){
        int indiceRiga, indiceColonna, indiceDirezione;
        
        indiceRiga = this.comboRighe.getSelectedIndex();
        indiceColonna = this.comboColonne.getSelectedIndex();
        indiceDirezione = this.comboDirezione.getSelectedIndex();
        Direction d = Direction.values()[indiceDirezione];
        
        movimentoCtrl.placeRobot(arrayRobot.get(0), d, indiceRiga, indiceColonna); // aggiunge il robot1 al tabellone  
        movimentoCtrl.placeRobot(arrayRobot.get(1), d, 4, 4); // aggiunge il robot2 al tabellone
        this.sendToLog("Robot posizionato in " + (indiceRiga+1) + " " + (indiceColonna+1) + ", " + Direction.values()[indiceDirezione]);
    }
    
    /**
     * rimuove l'ultima istruzione del programma
     */
    private void btnRimuoviIstruzioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRimuoviIstruzioneActionPerformed
        this.rimuoviUltimaIstruzione();
    }//GEN-LAST:event_btnRimuoviIstruzioneActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.singleInstance = null;
    }//GEN-LAST:event_formWindowClosed

    /**
     * chiama un metodo sinchronized per rimuovere l'ultima istruzione dal programma
     */
    private synchronized void rimuoviUltimaIstruzione(){
        if(this.indiceIstruzioneMostrata > -1){
            int ultima = this.istruzioniGui.size() - 1;
            InstructionCard card = this.istruzioniGui.get(ultima).getSourceCard();
            
            this.istruzioniGui.remove(ultima);//rimuove l'ultima card
            if(this.indiceIstruzioneMostrata == ultima){
                this.indiceIstruzioneMostrata--;
                this.updateIstruzioneMostrata(this.indiceIstruzioneMostrata); //mostra la penultima card
            }            
            this.sendToLog("Rimossa " + card);
            this.updateLabelIndiceIstruzioneMostrata(this.indiceIstruzioneMostrata);
        }
        else System.out.println("Non hai istruzioni da rimuovere");
    }
    
    /**
     * aggiunge un messaggio visibile all'inizio del log
     */
    protected synchronized void sendToLog(String message){
        this.logTextArea.append("◾ " + message + "\n");
    }
    
    
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
    private javax.swing.JButton btnAggiornaPosizione;
    private javax.swing.JButton btnAggiungiIstruzione;
    private javax.swing.JButton btnBackward;
    private javax.swing.JButton btnIstruzionePrecedente;
    private javax.swing.JButton btnIstruzioneSuccessiva;
    private javax.swing.JButton btnPlayPause;
    private javax.swing.JButton btnRimuoviIstruzione;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCardsCounter;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JComboBox<String> menuListaTipiInstructionCard;
    private javax.swing.JPanel panelCardGui;
    // End of variables declaration//GEN-END:variables

    /**
     * Aggiorna la label con istruzione mostrata / totale.
     * @param indice indice dell'istruzione mostrata da scrivere nel label
     */
    private void updateLabelIndiceIstruzioneMostrata(int n) {
        int indice = n + 1;
        System.out.println("Istruzione: " + indice + "/" + this.istruzioniGui.size());
        this.labelCardsCounter.setText(indice + "/" + this.istruzioniGui.size());
    }

    /**
     * Restituisce l'istanza di RobodromeView.
     */
    private RobodromeView getDromeView() {
        return (RobodromeView)this.containerTabellone.getComponent(0);
    }
    
    
    /**
     * Avvia l'esecuzione delle fasi di allenamento
     */
    private void avviaAllenamento(){
        eseguiTutteIstruzioni();
        this.movimentoCtrl.nastriTrasportatori();
        this.sendToLog("Robot in: " + this.arrayRobot.get(0).getLastPosition().toString());
        
        this.movimentoCtrl.play();
    }
    
    
    /**
     * Esegue ogni istruzione aggiunta alla gui di allenamento.
     */
    private void eseguiTutteIstruzioni(){
        for(InstructionCardGui ic : this.istruzioniGui){
            this.movimentoCtrl.muoviRobot(ic.getSourceCard(), this.arrayRobot.get(0));
        }
    }

    
    /**
     * Resetta la gui di gioco
     */
    private void resetTrainingGui(){
        
        this.getDrome().getCell(arrayRobot.get(0).getLastPosition().getRiga(), arrayRobot.get(0).getLastPosition().getColonna()).robotOutside();
        this.getDromeView().removeRobot(arrayRobot.get(0));
        this.arrayRobot.get(0).reset();
        this.logTextArea.setText("");
        
        this.istruzioniGui = new ArrayList<InstructionCardGui>();//svuota l'elenco di schede istruzione
        this.panelCardGui.removeAll(); // svuota il pannello che mostra il programma
        this.indiceIstruzioneMostrata = -1;
        this.updateLabelIndiceIstruzioneMostrata(this.indiceIstruzioneMostrata);
        this.panelCardGui.repaint();
    }
    
    
    /**
     * Riposiziona il robot alla posizione precedente.
     */
    private void indietroDiUno(){
        System.out.printf("storico posizioni %d - storico direzioni: %d\n",
                this.arrayRobot.get(0).getStoricoPosizioni().size(), this.arrayRobot.get(0).getStoricoDirections().size());
        Posizione posizione = this.arrayRobot.get(0).getLastPosition();
        Direction direzione;
        int riga, colonna;
        
        if( this.arrayRobot.get(0).getStoricoPosizioni().size() < 2){//se non ci sono posizioni da cancellare  
            System.out.println("Non puoi andare più indietro di così");
        }else{
            this.getDrome().getCell(posizione.getRiga(), posizione.getColonna()).robotOutside();
            
            this.arrayRobot.get(0).cancellaUltimaPosizione();//cancella posizione corrente
            this.arrayRobot.get(0).cancellaUltimaDirezione();//cancella direzione corrente
            
            posizione = this.arrayRobot.get(0).getLastPosition();
            riga = posizione.getRiga();
            colonna = posizione.getColonna();
            direzione = this.arrayRobot.get(0).getLastDirection();
            
            // riposiziona il robot nella giusta cella
            this.getDromeView().changeRobotPosition(arrayRobot.get(0), direzione, riga, colonna, true);

            
            this.getDrome().getCell(posizione.getRiga(), posizione.getColonna()).robotInside();
            this.sendToLog("Indietro di una mossa");
        }
    }
}