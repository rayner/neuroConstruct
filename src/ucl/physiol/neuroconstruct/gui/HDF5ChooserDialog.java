/**
 *  neuroConstruct
 *  Software for developing large scale 3D networks of biologically realistic neurons
 * 
 *  Copyright (c) 2009 Padraig Gleeson
 *  UCL Department of Neuroscience, Physiology and Pharmacology
 *
 *  Development of this software was made possible with funding from the
 *  Medical Research Council and the Wellcome Trust
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package ucl.physiol.neuroconstruct.gui;

import java.io.*;
import java.util.*;


import ncsa.hdf.object.*;
import ncsa.hdf.object.h5.*;


import ucl.physiol.neuroconstruct.dataset.*;
import ucl.physiol.neuroconstruct.gui.plotter.*;
import ucl.physiol.neuroconstruct.neuroml.hdf5.*;
import ucl.physiol.neuroconstruct.utils.*;

/**
 *
 * @author  Padraig
 */

public class HDF5ChooserDialog extends javax.swing.JDialog 
{
    private static final long serialVersionUID = -154251755576893475L;
    
    private static transient ClassLogger logger = new ClassLogger("Cell");
    
    boolean standalone =false;
    
    String SELECT_INFO="-- Please select from list below --";
    
    final String defaultPlotLocation = "New frame";
    
    File myFile = null;
    
    
    /** Creates new form HDF5ChooserDialog */
    public HDF5ChooserDialog(java.awt.Frame parent, boolean modal, File hdf5File)
    {
        super(parent, modal);
        initComponents();
        
        this.setTitle("Datasets present in HDF5 file: "+ hdf5File.getAbsolutePath());
        
        myFile = hdf5File;
        
        try
        {
            H5File h5 = Hdf5Utils.openH5file(hdf5File);

            Group g = Hdf5Utils.getRootGroup(h5);

            boolean plotToo = true;

            ArrayList<DataSet> dataSets = Hdf5Utils.parseGroupForDatasets(g, plotToo);

            StringBuffer summary = new StringBuffer("\n  Number of DataSets found in file: "+ hdf5File.getAbsolutePath()+": "+dataSets.size()+"\n\n  Select which to plot below.");
            
            this.jTextAreaMain.setText(summary.toString());
            
            this.jComboBox1.addItem(SELECT_INFO);
            
            for(DataSet ds: dataSets)
            {
                DataSetHolder dsh = new DataSetHolder(ds);
                this.jComboBox1.addItem(dsh);
                //summary.append(ds.toString()+"\n"+ds.getDescription()+"\n\n");
            }
            
            this.refresh();
        }
        catch(Exception e)
        {
            this.jTextAreaMain.setText("An error occured loading information from file: "+ hdf5File+"\n" +
                "Information:\n\n"+e.getMessage());
        }
        
        
        
    }
    
    
    private void refresh()
    {

        jComboBoxPlotFrames.removeAllItems();

        jComboBoxPlotFrames.addItem(defaultPlotLocation);

        ArrayList<String> allPlots = PlotManager.getPlotterFrameReferences();
        for (int i = 0; i < allPlots.size(); i++)
        {
            String next = (String) allPlots.get(i);
            jComboBoxPlotFrames.addItem(next);
        }

    } 
    
    
    private class DataSetHolder
    {
        private DataSetHolder(){};
        
        public DataSetHolder(DataSet ds)
        {
            this.ds = ds;
        };
        
        protected DataSet ds;
        
        @Override
        public String toString()
        {
            return ds.getReference();
        }
            
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMain = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonPlotAll = new javax.swing.JButton();
        jButtonPlot = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxPlotFrames = new javax.swing.JComboBox();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 6, 6), javax.swing.BorderFactory.createEtchedBorder()));
        jScrollPane1.setRequestFocusEnabled(false);

        jTextAreaMain.setColumns(80);
        jTextAreaMain.setRows(30);
        jScrollPane1.setViewportView(jTextAreaMain);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox1.setMinimumSize(new java.awt.Dimension(200, 20));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel3.add(jComboBox1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setMaximumSize(new java.awt.Dimension(200, 200));

        jButtonPlotAll.setText("Plot all");
        jButtonPlotAll.setActionCommand("Plot_all");
        jButtonPlotAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotAllActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPlotAll);

        jButtonPlot.setText("Plot selected");
        jButtonPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPlot);

        jLabel1.setText(" in:");
        jPanel1.add(jLabel1);

        jComboBoxPlotFrames.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPlotFrames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPlotFramesActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxPlotFrames);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        
        this.dispose();
        
        if (standalone) System.exit(0);
        
}//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotActionPerformed
        
        if (jComboBox1.getSelectedItem()==null || jComboBox1.getSelectedItem().equals(SELECT_INFO))
        {
            return;
        }
        
        if (jComboBox1.getSelectedItem() instanceof DataSetHolder)
        {
            DataSetHolder dsh = (DataSetHolder)jComboBox1.getSelectedItem();
            
            String targetFrame = (String)jComboBoxPlotFrames.getSelectedItem();
            
            String plotFrame = "Data from: " + dsh.ds.getReference();
            if (!targetFrame.equals(defaultPlotLocation))
            {
                plotFrame = targetFrame;
            }
                

            PlotterFrame frame = PlotManager.getPlotterFrame(plotFrame, false, false);
            
            frame.addDataSet(dsh.ds);

            frame.setVisible(true);
        }
        this.refresh();
        
}//GEN-LAST:event_jButtonPlotActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
        {
            logger.logComment("Item selected: "+ evt.getItem());
            
            if (evt.getItem() instanceof DataSetHolder)
            {
                DataSet ds = ((DataSetHolder)evt.getItem()).ds;

                this.jTextAreaMain.setText(ds.getDescription());
            }
            
        }
       
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButtonPlotAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotAllActionPerformed
        // TODO add your handling code here:
        
            
        String targetFrame = (String)jComboBoxPlotFrames.getSelectedItem();

        String plotFrame = "Plot of all 1D data arrays in "+myFile.getAbsolutePath();
        
        if (!targetFrame.equals(defaultPlotLocation))
        {
            plotFrame = targetFrame;
        }
        
        PlotterFrame frame = PlotManager.getPlotterFrame(plotFrame, false, false);

        for( int i=0; i<jComboBox1.getItemCount();i++)
        {
            if (jComboBox1.getItemAt(i) instanceof DataSetHolder)
            {
                DataSetHolder dsh = (DataSetHolder)jComboBox1.getItemAt(i);
                frame.addDataSet(dsh.ds);
            }
            
        }

        frame.setVisible(true);

        
}//GEN-LAST:event_jButtonPlotAllActionPerformed

    private void jComboBoxPlotFramesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlotFramesActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jComboBoxPlotFramesActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                File f = new File("../temp/ep0601aa.hdf5");
                    
                    
                HDF5ChooserDialog dialog = new HDF5ChooserDialog(new javax.swing.JFrame(), false, f);
                dialog.standalone = true;
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonPlot;
    private javax.swing.JButton jButtonPlotAll;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxPlotFrames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMain;
    // End of variables declaration//GEN-END:variables
    
}
