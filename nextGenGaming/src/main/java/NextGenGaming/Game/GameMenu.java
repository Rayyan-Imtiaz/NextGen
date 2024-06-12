package NextGenGaming.Game;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameMenu extends javax.swing.JPanel {

    public GameMenu() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new JButton();        ImageIcon snakeIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snakeIcon.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        jButton1.setIcon(snakeIcon);
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new JButton();        ImageIcon mineIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\mineIcon.gif").getImage().getScaledInstance(450, 220, Image.SCALE_DEFAULT));
        jButton2.setIcon(mineIcon);
        jButton3 = new JButton();        ImageIcon birdIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\birdIcon.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        jButton3.setIcon(birdIcon);
        label = new JLabel();        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\menu.gif").getImage().getScaledInstance(1020, 580, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 3));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                snakeEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                snakeExited(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 250, 210));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Game Menu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 230, 50));

        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 3));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mineEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mineExited(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 250, 210));

        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 3));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                birdEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                birdExited(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 250, 210));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 580));

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void mineEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mineEntered
         ImageIcon mineIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\mineIcon.gif").getImage().getScaledInstance(490, 250, Image.SCALE_DEFAULT));
        jButton2.setIcon(mineIcon);
    }//GEN-LAST:event_mineEntered

    private void mineExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mineExited
        ImageIcon mineIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\mineIcon.gif").getImage().getScaledInstance(450, 220, Image.SCALE_DEFAULT));
        jButton2.setIcon(mineIcon);       
    }//GEN-LAST:event_mineExited

    private void snakeEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_snakeEntered
        ImageIcon snakeIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snakeIcon.gif").getImage().getScaledInstance(290, 290, Image.SCALE_DEFAULT));
        jButton1.setIcon(snakeIcon);
    }//GEN-LAST:event_snakeEntered

    private void snakeExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_snakeExited
        ImageIcon snakeIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snakeIcon.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        jButton1.setIcon(snakeIcon);
    }//GEN-LAST:event_snakeExited

    private void birdEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_birdEntered
            
        ImageIcon birdIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\birdIcon.gif").getImage().getScaledInstance(290, 290, Image.SCALE_DEFAULT));
        jButton3.setIcon(birdIcon);
    }//GEN-LAST:event_birdEntered

    private void birdExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_birdExited
     
        ImageIcon birdIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\birdIcon.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        jButton3.setIcon(birdIcon);
    }//GEN-LAST:event_birdExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
