/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.java.j2sedeploy.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JComponent;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;
import org.netbeans.modules.java.j2sedeploy.J2SEDeployProperties;
import org.netbeans.modules.java.j2sedeploy.api.J2SEDeployConstants;
import org.netbeans.modules.java.j2seproject.api.J2SECategoryExtensionProvider;
import org.openide.util.HelpCtx;

/**
 *
 * @author Petr Somol
 */
public class JSEDeploymentPanel extends javax.swing.JPanel implements HelpCtx.Provider {

    private final J2SEDeployProperties props;
    private Project project;
    private java.util.List<J2SECategoryExtensionProvider> compProviders = new LinkedList<>();
    private int nextExtensionYPos;
    private List<ActionListener> okListener = new ArrayList<>();
    private List<ActionListener> storeListener = new ArrayList<>();
    private List<ActionListener> closeListener = new ArrayList<>();
    
    /**
     * Creates new form JSEDeploymentPanel
     */
    public JSEDeploymentPanel(J2SEDeployProperties props) {
        this.props = props;
        initComponents();
        checkBoxNativePackaging.setSelected(props.getNativeBundlingEnabled());
        this.project = props.getProject();
        for (J2SECategoryExtensionProvider compProvider : project.getLookup().lookupAll(J2SECategoryExtensionProvider.class)) {
            if( compProvider.getCategory() == J2SECategoryExtensionProvider.ExtensibleCategory.DEPLOYMENT ) {
                if( addExtPanel(project,compProvider,nextExtensionYPos) ) {
                    compProviders.add(compProvider);
                    nextExtensionYPos++;
                }
            }
        }
        addPanelFiller(nextExtensionYPos);
    }
    
    @NonNull
    public List<ActionListener> getOKListeners() {
        return okListener;
    }

    @NonNull
    public List<ActionListener> getStoreListeners() {
        return storeListener;
    }

    @NonNull
    public List<ActionListener> getCloseListeners() {
        return closeListener;
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

        mainPanel = new javax.swing.JPanel();
        labelNativePackaging = new javax.swing.JLabel();
        checkBoxNativePackaging = new javax.swing.JCheckBox();
        extPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(550, 400));
        setLayout(new java.awt.GridBagLayout());

        mainPanel.setPreferredSize(new java.awt.Dimension(550, 60));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        labelNativePackaging.setLabelFor(checkBoxNativePackaging);
        org.openide.awt.Mnemonics.setLocalizedText(labelNativePackaging, org.openide.util.NbBundle.getMessage(JSEDeploymentPanel.class, "JSEDeploymentPanel.labelNativePackaging.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        mainPanel.add(labelNativePackaging, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxNativePackaging, org.openide.util.NbBundle.getMessage(JSEDeploymentPanel.class, "JSEDeploymentPanel.checkBoxNativePackaging.text")); // NOI18N
        checkBoxNativePackaging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxNativePackagingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 15, 0, 0);
        mainPanel.add(checkBoxNativePackaging, gridBagConstraints);
        checkBoxNativePackaging.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JSEDeploymentPanel.class, "AN_JSEDeploymentPanel.checkBoxNativePackaging")); // NOI18N
        checkBoxNativePackaging.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSEDeploymentPanel.class, "AD_JSEDeploymentPanel.checkBoxNativePackaging")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        add(mainPanel, gridBagConstraints);

        extPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(extPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxNativePackagingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxNativePackagingActionPerformed
        boolean sel = checkBoxNativePackaging.isSelected();
        props.setNativeBundlingEnabled(sel);
    }//GEN-LAST:event_checkBoxNativePackagingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxNativePackaging;
    private javax.swing.JPanel extPanel;
    private javax.swing.JLabel labelNativePackaging;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public HelpCtx getHelpCtx() {
        return new HelpCtx(JSEDeploymentPanel.class);
    }
    
    private boolean addExtPanel(Project p, J2SECategoryExtensionProvider compProvider, int gridY) {
        if (compProvider != null) {
            J2SECategoryExtensionProvider.ConfigChangeListener ccl = new J2SECategoryExtensionProvider.ConfigChangeListener() {
                @Override
                public void propertiesChanged(Map<String, String> updates) {
                }
            };
            JComponent comp = compProvider.createComponent(p, ccl);
            if (comp != null) {
                java.awt.GridBagConstraints constraints = new java.awt.GridBagConstraints();
                constraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                constraints.gridx = 0;
                constraints.gridy = gridY;
                constraints.weightx = 1.0;
                extPanel.add(comp, constraints);
                
                // extract listeners if exist
                Object okObject = comp.getClientProperty(J2SEDeployConstants.PASS_OK_LISTENER);
                if(okObject instanceof ActionListener) {
                    okListener.add((ActionListener)okObject);
                }
                Object storeObject = comp.getClientProperty(J2SEDeployConstants.PASS_STORE_LISTENER);
                if(storeObject instanceof ActionListener) {
                    storeListener.add((ActionListener)storeObject);
                }
                Object closeObject = comp.getClientProperty(J2SEDeployConstants.PASS_CLOSE_LISTENER);
                if(closeObject instanceof ActionListener) {
                    closeListener.add((ActionListener)closeObject);
                }
                
                return true;
            }
        }
        return false;
    }

    private void addPanelFiller(int gridY) {
        java.awt.GridBagConstraints constraints = new java.awt.GridBagConstraints();
        constraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = gridY;
        //constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        extPanel.add( new Box.Filler(
                new Dimension(), 
                new Dimension(),
                new Dimension(10000,10000) ),
                constraints);
    }

}
