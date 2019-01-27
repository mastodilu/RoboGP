package robogp.matchmanager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import robogp.deck.InstructionCard;

/**
 *
 * @author Alessio
 */
public class scegliScheda extends javax.swing.JFrame {

    private int numRegistro;
    private final RobotMarker robot;
    private Image image;
    private programmaRobotJFrame frameProg;
    /**
     * Creates new form scegliScheda
     * @param ctrPartita
     */
    public scegliScheda(RobotMarker robot, int registro, programmaRobotJFrame frameProgr) {
        initComponents();
        
        this.numRegistro = registro;
        this.robot = robot;
        this.jComboBox1.removeAllItems();
        this.frameProg = frameProgr;
        
        for(InstructionCard card:this.robot.getManoRobot().getCards()){
            this.jComboBox1.addItem(card.getTipo());
        }

        try {                
          image = ImageIO.read(new File("robots/" + robot.getName() + ".png"));
          image = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
       } catch (IOException ex) {  
       }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        //chiamata del metodo che mette l'immagine
        //pannelloRegistro.add(picLabel);
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

        jPanel30 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 180));
        setSize(new java.awt.Dimension(400, 180));
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel30.setMaximumSize(new java.awt.Dimension(400, 120));
        jPanel30.setMinimumSize(new java.awt.Dimension(400, 120));
        jPanel30.setPreferredSize(new java.awt.Dimension(400, 120));
        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabel16.setText("Scheda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel30.add(jLabel16, gridBagConstraints);

        jButton14.setText("Conferma");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel30.add(jButton14, gridBagConstraints);

        jButton15.setText("Annulla");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 20);
        jPanel30.add(jButton15, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel30.add(jComboBox1, gridBagConstraints);

        getContentPane().add(jPanel30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        switch (this.numRegistro) {
            case 0:  
                this.frameProg.setRegistro1(this.jComboBox1.getSelectedItem().toString());
                break;
            case 1:  
                this.frameProg.setRegistro2(this.jComboBox1.getSelectedItem().toString());
                break;
            case 2:  
                this.frameProg.setRegistro3(this.jComboBox1.getSelectedItem().toString());
                break;
            case 3:  
                this.frameProg.setRegistro4(this.jComboBox1.getSelectedItem().toString());
                break;
            case 4:  
                this.frameProg.setRegistro5(this.jComboBox1.getSelectedItem().toString());
                break;         
        }
        
        
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel30;
    // End of variables declaration//GEN-END:variables


}
