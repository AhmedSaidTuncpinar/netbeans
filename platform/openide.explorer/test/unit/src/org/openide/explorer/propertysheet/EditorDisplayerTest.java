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

package org.openide.explorer.propertysheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.junit.NbTestCase;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;

/* A comprehensive test of EditorPropertyDisplayer */
public class EditorDisplayerTest extends NbTestCase {
    
    static {
        ComboTest.registerPropertyEditors();
    }
    
    public EditorDisplayerTest(String name) {
        super(name);
    }
    
    /*
    public static void main(String args[]) {
//        LookAndFeel lf = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(new com.jgoodies.plaf.plastic.Plastic3DLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        TestRunner.run(suite ());
     
        /*
        boolean go=false;
        try {
            UIManager.setLookAndFeel(new PseudoWindowsLookAndFeel());
            go = true;
        } catch (NoClassDefFoundError e) {
            System.err.println("Couldn't run tests on windows look and feel");
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Couldn't run tests on windows look and feel");
        }
        if (go) {
            TestRunner.run(suite ());
        }
        go=false;
        try {
            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.gtk.GTKLookAndFeel());
            go = true;
        } catch (NoClassDefFoundError e) {
            System.err.println("Couldn't run tests on GTK look and feel");
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Couldn't run tests on GTK look and feel");
            e.printStackTrace();
        }
        if (go) {
            TestRunner.run(suite ());
        }
        try {
            UIManager.setLookAndFeel(lf);
        } catch (Exception e) {
            //highly unlikely
        }
        try {
//        new EditorDisplayerTest("goo").setUp();
        } catch (Exception e){}
     
    }
     */
    
/*
 * This test creates a Property, Editor and Node. First test checks if initialized
 * editor contains the same value as property. The second checks if the property
 * value is changed if the same change will be done in the editor.
 */
    
    EditorPropertyDisplayer basicRen;
    EditorPropertyDisplayer tagsRen1;
    EditorPropertyDisplayer tagsRen2;
    EditorPropertyDisplayer tagsRen3;
    EditorPropertyDisplayer boolRen;
    EditorPropertyDisplayer custRen;
    EditorPropertyDisplayer custRen2;
    EditorPropertyDisplayer exRen;
    EditorPropertyDisplayer numRen;
    EditorPropertyDisplayer edRen;
    
    private TNode tn;
    private BasicProperty basicProp;
    private TagsProperty tags1;
    private TagsProperty tags2;
    private TagsProperty tags3;
    private BooleanProperty booleanProp;
    private EditorCustom ec;
    private CustomProperty customProp;
    private CustomProperty customProp2;
    private BasicEditor te;
    private boolean setup=false;
    private JFrame jf=null;
    private JPanel jp=null;
    private int SLEEP_LENGTH=10;
    
    protected void tearDown() {
        if (jf != null) {
            jf.hide();
            jf.dispose();
        }
    }
    
    protected void setUp() throws Exception {
        //            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
        //            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.gtk.GTKLookAndFeel());
        
        try {
            if (setup) return;
            basicProp= new BasicProperty("basicProp", true);
            System.err.println("Created basicProp at " + System.currentTimeMillis() + " - " + basicProp);
            
            tags1 = new TagsProperty("tags1", true, new String[] {"What","is","the","meaning","of","life"});
            tags2 = new TagsProperty("tags2", true, new String[] {"Austrolopithecines","automatically","engender","every","one"});
            tags3 = new TagsProperty("tags3", true, new String[] {"Behold","the","power","of","cheese"});
            booleanProp = new BooleanProperty("booleanProp", true);
            customProp = new CustomProperty("CustomProp", true);
            customProp2 = new CustomProperty("CustomProp2", true);
            ExceptionProperty exProp = new ExceptionProperty("Exception prop", true);
            NumProperty numProp = new NumProperty("Int prop", true);
            EditableNumProperty edProp = new EditableNumProperty("Editable", true);
            
            
            // Create new BasicEditor
            te = new BasicEditor();
            ec = new EditorCustom();
            // Create new TNode
            tn = new TNode();
            
            System.err.println("Crating frame");
            jf = new JFrame();
            jf.getContentPane().setLayout(new BorderLayout());
            jp = new JPanel();
            jp.setLayout(new FlowLayout());
            jf.getContentPane().add(jp, BorderLayout.CENTER);
            jf.setLocation(20,20);
            jf.setSize(600, 200);
            
            synchronized (jp.getTreeLock()) {
                System.err.println("BasicProp = " + basicProp);
                
                basicRen = new EditorPropertyDisplayer(basicProp);
                tagsRen1 = new EditorPropertyDisplayer(tags1);
                tagsRen2 = new EditorPropertyDisplayer(tags2);
                tagsRen3 = new EditorPropertyDisplayer(tags3);
                boolRen = new EditorPropertyDisplayer(booleanProp);
                custRen = new EditorPropertyDisplayer(customProp);
                custRen2 = new EditorPropertyDisplayer(customProp2);
                exRen = new EditorPropertyDisplayer(exProp);
                numRen = new EditorPropertyDisplayer(numProp);
                edRen = new EditorPropertyDisplayer(edProp);
                
                tagsRen2.setRadioButtonMax(10);
                
                jp.add(basicRen);
                jp.add(tagsRen1);
                jp.add(tagsRen2);
                jp.add(tagsRen3);
                jp.add(boolRen);
                jp.add(custRen);
                jp.add(custRen2);
                jp.add(exRen);
                jp.add(numRen);
                jp.add(edRen);
            }
            
            System.err.println("Waiting for window");
            new WaitWindow(jf);  //block until window open
            System.err.println("Window shown");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setup = true;
        }
    }
    
    public void testEverything() throws Exception {
        try {
            System.err.println("running");
            clickOn(basicRen);
            sleep();
            sleep();
            sleep();
            tagsRen2.setRadioButtonMax(2);
            System.err.println("set to 2");
            sleep();
            sleep();
            sleep();
            tagsRen2.setRadioButtonMax(10);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            
        } finally {
            setup = true;
            //            jf.hide();
            //            jf.dispose();
        }
    }
    
    private class FL implements FocusListener {
        private FocusEvent gainedEvent=null;
        private FocusEvent lostEvent=null;
        private int gainedCount=0;
        private int lostCount=0;
        public void assertGained() {
            assertNotNull("No focus gained received after clicking on an editable renderer", gainedEvent);
            assertTrue("Received wrong number of focus gained events for a single click on a renderer " +  gainedCount, gainedCount == 1);
        }
        
        public void assertLost() {
            assertNotNull("No focus lost event received after clicking away from a focused, editable renderer", lostEvent);
            assertTrue("Received wrong number of focus lost events for a single click away from a focused renderer" + lostCount, lostCount == 1);
        }
        
        public void focusGained(FocusEvent e) {
            gainedEvent = e;
            gainedCount++;
        }
        
        public void focusLost(FocusEvent e) {
            lostEvent = e;
            lostCount++;
        }
    }
    
    private class CL implements ChangeListener {
        
        private ChangeEvent e;
        public void assertEvent(String msg) {
            sleep(); //give the event time to happen
            assertNotNull(msg, e);
            e = null;
        }
        
        public void assertNoEvent(String msg) {
            sleep();
            assertNull(e);
            e = null;
        }
        
        public void stateChanged(ChangeEvent e) {
            this.e = e;
        }
        
    }
    
    private static class TestGCVal extends Object {
        public String toString() {
            return "TestGCVal";
        }
    }
    
    private static class WaitWindow extends WindowAdapter {
        boolean shown=false;
        public WaitWindow(JFrame f) {
            f.addWindowListener(this);
            f.show();
            if (!shown) {
                synchronized(this) {
                    try {
                        //System.err.println("Waiting for window");
                        wait(5000);
                    } catch (Exception e) {}
                }
            }
        }

        @Override
        public void windowOpened(WindowEvent e) {
            shown = true;
            synchronized(this) {
                //System.err.println("window opened");
                notifyAll();
                ((JFrame) e.getSource()).removeWindowListener(this);
            }
        }
    }
    
    private void sleep() {
        //useful when running interactively
        
        try {
            Thread.currentThread().sleep(SLEEP_LENGTH);
        } catch (InterruptedException ie) {
            //go away
        }
        
        
        
        //runs faster -uncomment for production use
        
        try {
            //jf.getTreeLock().wait();
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    System.currentTimeMillis();
                }
            });
            //jf.getTreeLock().wait();
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    System.currentTimeMillis();
                }
            });
        } catch (Exception e) {
        }
        
        
    }
    
    private void changeProperty(final RendererPropertyDisplayer ren, final Node.Property newProp) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                ren.setProperty(newProp);
            }
        });
    }
    
    private void clickOn(final EditorPropertyDisplayer ren, final int fromRight, final int fromTop) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                Point toClick = new Point(ren.getWidth() - fromRight, fromTop);
                Component target=ren.getComponentAt(toClick);
                toClick = SwingUtilities.convertPoint(ren, toClick, target);
                System.err.println("Target component is " + target.getClass().getName() + " - " + target + " clicking at " + toClick);
                
                MouseEvent me = new MouseEvent(target, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, toClick.x, toClick.y, 2, false);
                target.dispatchEvent(me);
                me = new MouseEvent(target, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, toClick.x, toClick.y, 2, false);
                target.dispatchEvent(me);
                me = new MouseEvent(target, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, toClick.x, toClick.y, 2, false);
            }
        });
        sleep();
    }
    
    private void clickOn(final EditorPropertyDisplayer ren) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                Point toClick = new Point(5,5);
                Component target=ren.getComponentAt(toClick);
                MouseEvent me = new MouseEvent(target, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, toClick.x, toClick.y, 2, false);
                target.dispatchEvent(me);
            }
        });
        sleep();
    }
    
    private void setEnabled(final EditorPropertyDisplayer ren,final boolean val) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                ren.setEnabled(val);
            }
        });
        sleep();
    }
    
    private Exception throwMe = null;
    private String flushResult = null;
    private String flushValue(final EditorPropertyDisplayer ren) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                try {
                    //flushResult = ren.flushValue();
                } catch (Exception e) {
                    throwMe = e;
                    flushResult = null;
                }
            }
        });
        if (throwMe != null) {
            try {
                throw throwMe;
            } finally {
                throwMe = null;
            }
        }
        return flushResult;
    }
    
    
    private void releaseKey(final Component target, final int key) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                KeyEvent ke = new KeyEvent(target, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, (char) key);
                target.dispatchEvent(ke);
            }
        });
        sleep();
    }
    
    private void pressKey(final Component target, final int key) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                KeyEvent ke = new KeyEvent(target, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, (char) key);
                target.dispatchEvent(ke);
            }
        });
        sleep();
    }
    
    private void shiftPressKey(final Component target, final int key) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                KeyEvent ke = new KeyEvent(target, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.SHIFT_MASK, key, (char) key);
                target.dispatchEvent(ke);
            }
        });
        sleep();
    }
    
    
    private void typeKey(final Component target, final int key) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                KeyEvent ke = new KeyEvent(target, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, (char) key);
                target.dispatchEvent(ke);
            }
        });
        sleep();
    }
    
    //Node definition
    public class TNode extends AbstractNode {
        //create Node
        public TNode() {
            super(Children.LEAF);
            setName("TNode"); // or, super.setName if needed
            setDisplayName("TNode");
            createSheet();
        }
        //clone existing Node
        public Node cloneNode() {
            return new TNode();
        }
        
        public void addProp(Node.Property p) {
            props.put(p);
            this.firePropertyChange(PROP_PROPERTY_SETS, null, null);
            this.firePropertySetsChange(null, null);
        }
        
        Sheet sheet=null;
        Sheet.Set props=null;
        // Create a property sheet:
        protected Sheet createSheet() {
            sheet = super.createSheet();
            // Make sure there is a "Properties" set:
            props = sheet.get(Sheet.PROPERTIES);
            if (props == null) {
                props = Sheet.createPropertiesSet();
                sheet.put(props);
            }
            props.put(basicProp);
            props.put(tags1);
            props.put(tags2);
            props.put(tags3);
            props.put(booleanProp);
            props.put(customProp);
            
            return sheet;
        }
        // Method firing changes
        public void fireMethod(String s, Object o1, Object o2) {
            firePropertyChange(s,o1,o2);
        }
    }
    
    // Property definition
    public class BasicProperty extends PropertySupport {
        private Object myValue = "Value";
        // Create new Property
        public BasicProperty(String name, boolean isWriteable) {
            super(name, Object.class, name, "", true, isWriteable);
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            Object oldVal = myValue;
            myValue = value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        // get the property editor
        public PropertyEditor getPropertyEditor() {
            return te;
        }
    }
    
    // Editor definition
    public class BasicEditor extends PropertyEditorSupport implements ExPropertyEditor {
        PropertyEnv env;
        
        // Create new BasicEditor
        public BasicEditor() {
        }
        
        /*
         * This method is called by the IDE to pass
         * the environment to the property editor.
         */
        public void attachEnv(PropertyEnv env) {
            this.env = env;
        }
        
        // Set that this Editor doesn't support custom Editor
        @Override
        public boolean supportsCustomEditor() {
            return false;
        }
        
        // Set the Property value threw the Editor
        @Override
        public void setValue(Object newValue) {
            super.setValue(newValue);
        }

        @Override
        public String getAsText() {
            return getValue() == null ? "null" : getValue().toString();
        }
    }
    
    
    public class TagsEditor extends PropertyEditorSupport implements ExPropertyEditor {
        PropertyEnv env;
        String[] tags;
        public TagsEditor(String[] tags) {
            this.tags = tags;
        }

        @Override
        public String[] getTags() {
            return tags;
        }
        
        public void attachEnv(PropertyEnv env) {
            this.env = env;
        }

        @Override
        public boolean supportsCustomEditor() {
            return false;
        }

        @Override
        public void setValue(Object newValue) {
            super.setValue(newValue);
        }
        
        
    }
    
    // Property definition
    public class TagsProperty extends PropertySupport {
        private Object myValue = "Value";
        private String[] tags;
        // Create new Property
        public TagsProperty(String name, boolean isWriteable, String[] tags) {
            super(name, Object.class, name, "", true, isWriteable);
            this.tags = tags;
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            Object oldVal = myValue;
            myValue = value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        // get the property editor
        public PropertyEditor getPropertyEditor() {
            return new TagsEditor(tags);
        }
        
        public String getShortDescription() {
            return "I have tags!";
        }
    }
    
    // Property definition
    public class BooleanProperty extends PropertySupport {
        private Boolean myValue = Boolean.FALSE;
        // Create new Property
        public BooleanProperty(String name, boolean isWriteable) {
            super(name, Boolean.class, name, "", true, isWriteable);
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            Object oldVal = myValue;
            myValue = (Boolean) value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        public Object getValue(String key) {
            if ("valueIcon".equals(key)) {
                return new ValueIcon();
            } else {
                return super.getValue(key);
            }
        }
    }
    
    public class CustomProperty extends PropertySupport {
        private Object myValue = "Value";
        // Create new Property
        public CustomProperty(String name, boolean isWriteable) {
            super(name, Object.class, name, "", true, isWriteable);
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            Object oldVal = myValue;
            myValue = value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        // get the property editor
        public PropertyEditor getPropertyEditor() {
            return ec;
        }
        
        public Object getValue(String key) {
            if ("valueIcon".equals(key)) {
                return new ValueIcon();
            } else {
                return super.getValue(key);
            }
        }
    }
    
    public class ExceptionProperty extends PropertySupport {
        private Object myValue = "Value";
        // Create new Property
        public ExceptionProperty(String name, boolean isWriteable) {
            super(name, Object.class, name, "", true, isWriteable);
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            Object oldVal = myValue;
            myValue = value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        // get the property editor
        public PropertyEditor getPropertyEditor() {
            return exed;
        }
    }
    
    private ExEditor exed = new ExEditor();
    public static class ExEditor extends PropertyEditorSupport {
        private Object myVal="Value";
        public ExEditor() {}
        @Override
        public void setAsText(String val) {
            //System.err.println("SetAsText");
            if (val.equals("Value")) {
                myVal = val;
            } else {
                IllegalArgumentException iae = new IllegalArgumentException("No!");
                Exceptions.attachLocalizedMessage(iae, "NoNo!");
                throw iae;
            }
        }

        @Override
        public void setValue(Object newValue) {
            myVal = newValue;
            firePropertyChange();
        }

        @Override
        public Object getValue() {
            return "Value";
        }
    }
    
    
    // Editor definition
    public class EditorCustom extends PropertyEditorSupport implements ExPropertyEditor {
        PropertyEnv env;
        
        // Create new BasicEditor
        public EditorCustom() {
        }
        
        /*
         * This method is called by the IDE to pass
         * the environment to the property editor.
         */
        public void attachEnv(PropertyEnv env) {
            this.env = env;
            env.setState(env.STATE_INVALID);
        }
        
        // Set that this Editor doesn't support custom Editor
        @Override
        public boolean supportsCustomEditor() {
            return true;
        }
        
        // Set the Property value threw the Editor
        @Override
        public void setValue(Object newValue) {
            super.setValue(newValue);
        }

        @Override
        public String getAsText() {
            return getValue() == null ? "null" : getValue().toString();
        }

        @Override
        public Component getCustomEditor() {
            return new JPanel();
        }
    }
    
    public class NumProperty extends PropertySupport {
        private Integer myValue = new Integer(4);
        // Create new Property
        public NumProperty(String name, boolean isWriteable) {
            super(name, Integer.class, name, "", true, isWriteable);
        }
        // get property value
        public Object getValue() {
            return myValue;
        }
        // set property value
        public void setValue(Object value) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException {
            if (!(value instanceof Integer)) {
                throw new IllegalArgumentException("Not an integer - " + value);
            }
            Object oldVal = myValue;
            myValue = (Integer) value;
            tn.fireMethod(getName(), oldVal, myValue);
        }
        // get the property editor
        public PropertyEditor getPropertyEditor() {
            return new NumberedTagsEditor();
        }
    }
    
    public class EditableNumProperty extends TagsProperty {
        public EditableNumProperty(String name, boolean isWriteable) {
            super(name, isWriteable, new String[]{"boo"});
        }

        @Override
        public PropertyEditor getPropertyEditor() {
            return new EditorDisplayerTest.EditableTagsEditor();
        }
    }
    
    
    // Combo must display text, not numbers
    public class NumberedTagsEditor extends PropertyEditorSupport {
        private int val=3;
        // Create new BasicEditor
        public NumberedTagsEditor() {
        }

        @Override
        public String[] getTags() {
            return new String[] {"zero","one","two","three","four","five","six","seven"};
        }
        
        
        // Set the Property value threw the Editor
        @Override
        public void setValue(Object newValue) {
            val = ((Integer) newValue).intValue();
            firePropertyChange();
        }

        @Override
        public String getAsText() {
            return getTags()[((Integer) getValue()).intValue()];
        }

        @Override
        public void setAsText(String txt) {
            String[] t = getTags();
            for (int i=0; i < t.length; i++) {
                if (txt.trim().equals(t[i])) {
                    setValue(new Integer(i));
                    return;
                }
            }
            IllegalArgumentException iae = new IllegalArgumentException(txt);
            Exceptions.attachLocalizedMessage(iae, txt + " is not a valid value");
        }

        @Override
        public Object getValue() {
            return new Integer(val);
        }

        @Override
        public Component getCustomEditor() {
            return new JPanel();
        }
    }
    
    public class EditableTagsEditor extends TagsEditor implements ExPropertyEditor {
        private Object val="woof";
        public EditableTagsEditor() {
            super(new String[] {"miaou","woof","moo","quack"});
        }
        public void attachEnv(PropertyEnv env) {
            env.getFeatureDescriptor().setValue("canEditAsText", Boolean.TRUE);
        }
        @Override
        public void setAsText(String s) {
            setValue(s);
        }
        @Override
        public void setValue(Object val) {
            this.val = val;
        }
        @Override
        public Object getValue() {
            return val;
        }
        @Override
        public String getAsText() {
            return val.toString();
        }
        @Override
        public boolean supportsCustomEditor() {
            return true;
        }
        @Override
        public Component getCustomEditor() {
            return new JLabel("You called?");
        }
    }
    
    private class ValueIcon implements Icon {
        
        public int getIconHeight() {
            return 12;
        }
        
        public int getIconWidth() {
            return 12;
        }
        
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Color col = g.getColor();
            try {
                g.setColor(Color.BLUE);
                g.drawRect(x, y, getIconWidth(), getIconHeight());
                g.fillRect(x+3, y+3, getIconWidth()-5, getIconHeight()-5);
            } finally {
                g.setColor(col);
            }
        }
        
    }
}
