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
package org.netbeans.modules.java.hints.jdk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.netbeans.modules.java.hints.regex.parser.ExampleGenerator;
import org.netbeans.modules.java.hints.regex.parser.RegExParser;
import org.netbeans.modules.java.hints.regex.parser.RegexConstructs.RegEx;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

public final class RegexExampleVisualPanel1 extends JPanel {
    
    private final String expression;
    /**
     * Creates new form RegexExampleVisualPanel1
     */
    public RegexExampleVisualPanel1() {
        initComponents();
        this.expression = CheckRegexTopComponent.findInstance().getExpression();
        expressionLabel.setText(expression);
        exampleTextArea.setText(getExamples());
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.REGEX_EXAMPLES");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regExLabel = new javax.swing.JLabel();
        exampleScrollPane = new javax.swing.JScrollPane();
        exampleTextArea = new javax.swing.JTextArea();
        expressionLabel = new javax.swing.JLabel();
        numberField = new javax.swing.JTextField();
        NoLabel = new javax.swing.JLabel();
        regenerateButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(regExLabel, org.openide.util.NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.regExLabel.text")); // NOI18N

        exampleTextArea.setColumns(20);
        exampleTextArea.setRows(5);
        exampleScrollPane.setViewportView(exampleTextArea);

        numberField.setText(org.openide.util.NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.numberField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(NoLabel, org.openide.util.NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.NoLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(regenerateButton, org.openide.util.NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.regenerateButton.text")); // NOI18N
        regenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regenerateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exampleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(regExLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expressionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(regenerateButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regExLabel)
                    .addComponent(expressionLabel)
                    .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exampleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regenerateButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void regenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regenerateButtonActionPerformed
        exampleTextArea.setText(getExamples());
    }//GEN-LAST:event_regenerateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NoLabel;
    private javax.swing.JScrollPane exampleScrollPane;
    private javax.swing.JTextArea exampleTextArea;
    private javax.swing.JLabel expressionLabel;
    private javax.swing.JTextField numberField;
    private javax.swing.JLabel regExLabel;
    private javax.swing.JButton regenerateButton;
    // End of variables declaration//GEN-END:variables

    private String getExamples() {     
        RegExParser r = new RegExParser(expression);        
        RegEx p = r.parse();        
        ExampleGenerator eg = new ExampleGenerator(p);
        int noFieldVal = 0;
        try{
         noFieldVal = Integer.parseInt(numberField.getText());    
        }catch(NumberFormatException e){
            return NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.exampleTextArea.invalidText");
        }    
        ArrayList<String> generate = eg.generate(noFieldVal); 
        StringBuilder sb = new StringBuilder();        
        generate.forEach(example -> {
            sb.append(example + "\n");
        });        
        return sb.toString();
    }
}

final class RegexExampleAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogDescriptor dd = new DialogDescriptor(new RegexExampleVisualPanel1(), NbBundle.getMessage(RegexExampleVisualPanel1.class, "RegexExampleVisualPanel1.REGEX_EXAMPLES"));        
        dd.setOptions(new Object[0]);
        DialogDisplayer.getDefault().notify(dd);
    }

}
