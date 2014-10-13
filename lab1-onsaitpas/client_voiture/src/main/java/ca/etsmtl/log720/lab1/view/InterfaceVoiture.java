/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.etsmtl.log720.lab1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ca.etsmtl.log720.lab1.ClientVoitureSingleton;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.CollectionReaction;

/**
*
* @author Steven
*/
public class InterfaceVoiture extends javax.swing.JFrame {
	private ActionListener listener;
   /**
    * Creates new form InterfaceVoiture
    */
   public InterfaceVoiture(ActionListener listener) {
	   this.listener = listener;
       initComponents();
       
   }
   
   //for dev
   private InterfaceVoiture() {
	   this.listener = null;
       initComponents();      
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {
   	   infractionsView = new Infractions(listener);
   	   rechercheView = new Recherche(listener);
   	   reactionView = new Reactions(listener);
   	
       jMenuItem3 = new javax.swing.JMenuItem();
       jLabel1 = new javax.swing.JLabel();
       jLabel3 = new javax.swing.JLabel();
       jL_currentDossier = new javax.swing.JLabel();
       jMenuBar1 = new javax.swing.JMenuBar();
       jMenu2 = new javax.swing.JMenu();
       jMenuItem1 = new javax.swing.JMenuItem();
       jMItem_infractions = new javax.swing.JMenuItem();
       jMenu1 = new javax.swing.JMenu();
       jMenuItem4 = new javax.swing.JMenuItem();
       list1 = new java.awt.List();
       list2 = new java.awt.List();

       jMenuItem3.setText("jMenuItem3");

       setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

       jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
       jLabel1.setText("Client Voiture");

       jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
       jLabel3.setText("Dossier selectionne :");

       jL_currentDossier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
       jL_currentDossier.setText("---");

       jMenu2.setText("Fichier");

       jMenuItem1.setText("Reactions");
       jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jMenuItem1ActionPerformed(evt);
           }
       });
       jMenu2.add(jMenuItem1);

       jMItem_infractions.setText("Infractions");
       jMItem_infractions.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jMItem_infractionsActionPerformed(evt);
           }
       });
       jMenu2.add(jMItem_infractions);

       jMenuBar1.add(jMenu2);

       jMenu1.setText("Dossier");

       jMenuItem4.setText("Recherche");
       jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jMenuItem4ActionPerformed(evt);
           }
       });
       jMenu1.add(jMenuItem4);

       jMenuBar1.add(jMenu1);

       setJMenuBar(jMenuBar1);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addGap(22, 22, 22)
               .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
               .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(34, 34, 34))
           .addGroup(layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGap(270, 270, 270)
                       .addComponent(jLabel1))
                   .addGroup(layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jLabel3)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addComponent(jL_currentDossier)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(94, 94, 94)
               .addComponent(jLabel1)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGap(53, 53, 53)
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel3)
                           .addComponent(jL_currentDossier))
                       .addGap(38, 38, 38)
                       .addComponent(list2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                   .addGroup(layout.createSequentialGroup()
                       .addGap(117, 117, 117)
                       .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
               .addContainerGap())
       );

       pack();
   }// </editor-fold>
   
   private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
       reactionView.setVisible(true);
   }                                          

   private void jMItem_infractionsActionPerformed(java.awt.event.ActionEvent evt) {                                           
       infractionsView.setVisible(true);
   }                                          

   private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
       rechercheView.setVisible(true);
   }
   
   public void showCurrentDossier(String currentDossier)
   {
	   jL_currentDossier.setText(currentDossier);
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
       /* Set the Nimbus look and feel */
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
           java.util.logging.Logger.getLogger(InterfaceVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           java.util.logging.Logger.getLogger(InterfaceVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           java.util.logging.Logger.getLogger(InterfaceVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
           java.util.logging.Logger.getLogger(InterfaceVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       }
       //</editor-fold>

       /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new InterfaceVoiture().setVisible(true);
           }
       });
   }

   // Variables declaration - do not modify                     
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jL_currentDossier;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JMenu jMenu2;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JMenuItem jMenuItem1;
   private javax.swing.JMenuItem jMItem_infractions;
   private javax.swing.JMenuItem jMenuItem3;
   private javax.swing.JMenuItem jMenuItem4;
   private java.awt.List list1;
   private java.awt.List list2;
   
   public Infractions infractionsView;
   public Recherche rechercheView;
   public Reactions reactionView; 
   // End of variables declaration  
   
   
   public void refreshInfractions(CollectionInfraction collec_inf){
	   if ( collec_inf==null )
	   {
		   list1.removeAll();
		   return;
	   }
	   	int size_inf = collec_inf.size();
			//System.out.println("size_infraction="+size_reac);
			if(size_inf>0){
				int i=0;
				list1.removeAll();
				//System.out.println("[");
				while(size_inf>i){ // Ajout des infractions a la liste des infractions
					//System.out.println("\tinf num="+i+": {"+banque_infraction.infractions().getInfraction(i)._toString()+"}");	
					list1.add(collec_inf.getInfraction(i)._toString()); // TODO makethis morep retty
					i++;
				}
				//System.out.println("]");
			}
   }
   
   public void refreshReactions(CollectionReaction collec_reac){
	   if ( collec_reac==null )
	   {
		   list2.removeAll();
		   return;
	   }
   		int size_reac = collec_reac.size();
		//System.out.println("size_infraction="+size_reac);
		if(size_reac>0){
			int i=0;
			list2.removeAll();
			//System.out.println("[");
			while(size_reac>i){ // Ajout des infractions a la liste des infractions
				//System.out.println("\tinf num="+i+": {"+banque_infraction.infractions().getInfraction(i)._toString()+"}");	
				list2.add(collec_reac.getReaction(i)._toString()); // TODO makethis morep retty
				i++;
			}
			//System.out.println("]");
		}
   }
}