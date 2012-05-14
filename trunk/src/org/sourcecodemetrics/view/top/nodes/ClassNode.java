/**
 * This file is part of SourceCodeMetrics project.
 * 
 * Copyright (C) 2012 Krystian Warzocha
 *
 * SourceCodeMetrics is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 2 of the License, or 
 * (at your option) any later version.
 * 
 * SourceCodeMetrics is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * SourceCodeMetrics. If not, see http://www.gnu.org/licenses/.
 */

package org.sourcecodemetrics.view.top.nodes;

import org.sourcecodemetrics.measurer.api.IClass;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import org.sourcecodemetrics.view.top.nodes.factories.ClassChildFactory;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.cookies.EditCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Krystian Warzocha
 */
public class ClassNode extends AbstractNode {

    public ClassNode(IClass c) {
        super(Children.create(new ClassChildFactory(c), true));
        this.c = c;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        if (c != null) {
            // class metrics
            set.put(new MetricPropertyInteger("NOM", c.getNOM(), "Number Of Methods"));
            set.put(new MetricPropertyInteger("NOSM", c.getNOSM(), "Number Of Static Methods"));
            set.put(new MetricPropertyInteger("NOF", c.getNOF(), "Number Of Fields"));
            set.put(new MetricPropertyInteger("NOSF", c.getNOSF(), "Number Of Static Fields"));
            set.put(new MetricPropertyInteger("LCOM1", c.getLCOM1(), "Lack of COhesion in Methods 1"));
            set.put(new MetricPropertyInteger("LCOM2", c.getLCOM2(), "Lack of COhesion in Methods 2"));
            set.put(new MetricPropertyInteger("LCOM3", c.getLCOM3(), "Lack of COhesion in Methods 3"));
            set.put(new MetricPropertyInteger("LCOM4", c.getLCOM4(), "Lack of COhesion in Methods 4"));
            set.put(new MetricPropertyDouble("LCOM5", c.getLCOM5(), "Lack of COhesion in Methods 5"));
            set.put(new MetricPropertyDouble("TCC", c.getTCC(), "Tight Class Cohesion"));
            set.put(new MetricPropertyDouble("LCC", c.getLCC(), "Loose Class Cohesion"));
            set.put(new MetricPropertyInteger("WMC", c.getWMC(), "Weighted Methods per Class"));
            set.put(new MetricPropertyInteger("LOC", c.getLOC(), "Lines Of Code"));
            set.put(new MetricPropertyInteger("LOCm", c.getLOCm(), "Lines Of Comments"));
            set.put(new MetricPropertyInteger("NTM", c.getNTM(), "Number of Test Methods"));
            set.put(new MetricPropertyDouble("NAK", c.getNAK(), "Number of Asserts per KLOC"));
            set.put(new MetricPropertyInteger("NOC", c.getNOC(), "Number Of Classes"));
            set.put(new MetricPropertyString("C", c.getC() ? "yes" : "no", "Covered by JUnit tests"));

            // method metrics
            set.put(new MetricPropertyInteger("VG", c.getMethodVGAvg(), "Average Cyclomatic Complexity"));
            set.put(new MetricPropertyInteger("NBD", c.getMethodNBDAvg(), "Average Nested Block Depth"));
            set.put(new MetricPropertyInteger("NOP", c.getMethodNOPSum(), "Total Number Of Parameters"));
        }

        sheet.put(set);
        return sheet;
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DataObject dobj = null;
                try {
                    dobj = DataObject.find(c.getFileObject());
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
                EditCookie ec = dobj.getLookup().lookup(EditCookie.class);
                if (ec != null) {
                    ec.edit();
                }

                EditorRegistry.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        JTextComponent jtc = EditorRegistry.focusedComponent();
                        if (jtc != null) {
                            String text = jtc.getText();
                            int idx = text.indexOf("class " + c.getName());
                            if (idx != -1) {
                                jtc.setCaretPosition(idx + 6);
                            }
                        }
                    }
                });
            }
        };
    }

    @Override
    public String getName() {
        if (c == null) {
            return "Error!";
        } else {
            return c.getName();
        }
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/sourcecodemetrics/view/top/icons/class.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    private IClass c;
    private final static Logger logger = Logger.getLogger(ClassNode.class.getName());
}
