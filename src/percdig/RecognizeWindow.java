/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RecognizeWindow.java
 *
 * Created on 01.04.2013, 15:13:47
 */
package percdig;

import ai.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

/**
 *
 * @author tyamgin
 */
public class RecognizeWindow extends javax.swing.JFrame {

    PlotSteps plot;
    PlotSteps plot2;
    
    /** Creates new form RecognizeWindow */
    public RecognizeWindow() {
        super();
        initComponents();
        plot = new PlotSteps("Ответы однослойного перцептрона");
        plot2 = new PlotSteps("Ответы двуслойного перцептрона");
        plot2.setLocation(new Point(0, plot.getHeight() + 4));
        init();
    }
    
    private void init() 
    {        
        canvasImage.setSize(w, h);
        points = new int[w][h];
        perceptron = new Perceptron(10, w*h);
        perceptron2 = new Perceptron2(w*h, 300, 10);
        spinTeachCount.setValue(20);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvasImage = new java.awt.Canvas();
        spinTeachCount = new javax.swing.JSpinner();
        butTeach = new javax.swing.JButton();
        butClear = new javax.swing.JButton();
        butRecognize = new javax.swing.JButton();
        AddToDataBase = new javax.swing.JButton();
        rightAnswerField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        canvasImage.setBackground(new java.awt.Color(255, 255, 255));
        canvasImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                canvasImageMouseDragged(evt);
            }
        });

        spinTeachCount.setToolTipText("Number of iterations");
        spinTeachCount.setRequestFocusEnabled(false);

        butTeach.setText("Teach");
        butTeach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butTeachMouseClicked(evt);
            }
        });

        butClear.setText("Clear");
        butClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butClearMouseClicked(evt);
            }
        });

        butRecognize.setText("Recognize");
        butRecognize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butRecognizeMouseClicked(evt);
            }
        });

        AddToDataBase.setText("Add to data base");
        AddToDataBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddToDataBaseMouseClicked(evt);
            }
        });

        jLabel1.setText("Right answer:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvasImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(butTeach, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinTeachCount, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                    .addComponent(butClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(butRecognize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rightAnswerField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddToDataBase)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinTeachCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(butTeach))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butRecognize))
                    .addComponent(canvasImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddToDataBase)
                    .addComponent(jLabel1)
                    .addComponent(rightAnswerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butTeachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butTeachMouseClicked
        // TODO add your handling code here:
        int n = ((Integer)spinTeachCount.getValue()).intValue();
        try
        {
            teach(n);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }//GEN-LAST:event_butTeachMouseClicked

    private void butClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butClearMouseClicked
        // TODO add your handling code here:
        canvasImage.getGraphics().setPaintMode();
        canvasImage.getGraphics().setColor(Color.red);
        canvasImage.getGraphics().clearRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points.length; j++)
                points[i][j] = 0;
    }//GEN-LAST:event_butClearMouseClicked

    private void butRecognizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butRecognizeMouseClicked
        // TODO add your handling code here:
        int d = recognize();
    }//GEN-LAST:event_butRecognizeMouseClicked

    private void canvasImageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasImageMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();       
        if (x >= 2 && y >= 2 && x <= canvasImage.getWidth()-2 && y <= canvasImage.getHeight()-2) {
            canvasImage.getGraphics().setColor(Color.black);        
            canvasImage.getGraphics().fillRect(x - 2, y - 2, 4, 4);           
            for (int i = x - 2; i <= x + 1; i++) {
                for (int j = y - 2; j <= y + 1; j++) {
                    points[i][j] = 1;
                }
            }
        }
        butRecognizeMouseClicked(new MouseEvent(this, 0, 0, 0, 0, 0, 0, false));
    }//GEN-LAST:event_canvasImageMouseDragged

    private void AddToDataBaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddToDataBaseMouseClicked
        // TODO add your handling code here:
        int rightAnswer;
        try
        {
            rightAnswer = Integer.parseInt(rightAnswerField.getText());
            
        }
        catch(NumberFormatException ex)
        {
            return;
        }
        if (rightAnswer < 0 || rightAnswer > 9)
            return;
        rightAnswerField.setText("");
        
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setBackground(Color.white);
        g.clearRect(0, 0, w, h);
        g.setColor(Color.black);
        for(int i = 0; i < w; i++)
            for(int j = 0; j < h; j++)
                if (points[i][j] != 0)
                    g.drawOval(i, j, 1, 1);
        try
        {
            String fileName = String.format("data\\%d_my%d.jpg", rightAnswer, new Random().nextInt(2000000000));
            ImageIO.write(img, "JPEG", new File(fileName));
            System.out.println(rightAnswer + " Saved");
        }
        catch(IOException ex)
        {
        }
    }//GEN-LAST:event_AddToDataBaseMouseClicked

    
    private double[] getBitMap()
    {
        double[] x = new double[points.length * points.length];
        for (int i = 0, k = 0; i < points.length; i++)
            for (int j = 0; j < points.length; j++)
                x[k++] = points[j][i];
        return x;
    }
    
    
    
    private int recognize() 
    {
        double[] x = getBitMap();
            
        double[] vec = perceptron.recognize(x);
        String[] texts = new String[vec.length];
        for(int i = 0; i < vec.length; i++)
            texts[i] = "[" + i + "]";
        plot.setValues(texts, vec);
        plot.setVisible(true);
        plot.repaint();
        
        vec = perceptron2.recognize(x);
        plot2.setValues(texts, vec);
        plot2.setVisible(true);
        plot2.repaint();
        
        return 0;
    }
    
    private void teach(int n) throws Exception
    {
        Teacher t = new Teacher(perceptron);
        Teacher t2 = new Teacher(perceptron2);

        t2.teach("data", n);
        t.teach("data", n);
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RecognizeWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToDataBase;
    private javax.swing.JButton butClear;
    private javax.swing.JButton butRecognize;
    private javax.swing.JButton butTeach;
    private java.awt.Canvas canvasImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField rightAnswerField;
    private javax.swing.JSpinner spinTeachCount;
    // End of variables declaration//GEN-END:variables

    private Perceptron perceptron;
    private Perceptron2 perceptron2;
    private int[][] points;
    int w = 64, h = 64;
}