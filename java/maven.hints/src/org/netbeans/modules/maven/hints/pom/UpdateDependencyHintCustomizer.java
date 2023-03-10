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

package org.netbeans.modules.maven.hints.pom;

import java.util.prefs.Preferences;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 * Options panel for {@link UpdateDependencyHint}.
 * @author mbien
 */
public class UpdateDependencyHintCustomizer extends javax.swing.JPanel {
    
    private final boolean noMajorUpgradeOld;

    public UpdateDependencyHintCustomizer(Preferences preferences, boolean noMajorUpgradeInitial) {
        initComponents();
        noMajorUpgradeOld = noMajorUpgradeInitial;
        cbNoMajorUpgrade.setSelected(noMajorUpgradeInitial);
        cbNoMajorUpgrade.addActionListener((e) -> {
            preferences.putBoolean(UpdateDependencyHint.KEY_NO_MAJOR_UPGRADE, cbNoMajorUpgrade.isSelected());
        });
    }

    boolean getSavedNoMajorUpgradeOption() {
        return noMajorUpgradeOld;
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbNoMajorUpgrade = new JCheckBox();

        Mnemonics.setLocalizedText(cbNoMajorUpgrade, NbBundle.getMessage(UpdateDependencyHintCustomizer.class, "UpdateDependencyHintCustomizer.cbNoMajorUpgrade.text")); // NOI18N
        cbNoMajorUpgrade.setToolTipText(NbBundle.getMessage(UpdateDependencyHintCustomizer.class, "UpdateDependencyHintCustomizer.cbNoMajorUpgrade.toolTipText")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbNoMajorUpgrade)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbNoMajorUpgrade)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox cbNoMajorUpgrade;
    // End of variables declaration//GEN-END:variables

}
