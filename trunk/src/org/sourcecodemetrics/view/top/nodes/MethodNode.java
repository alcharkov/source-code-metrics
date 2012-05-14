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

import org.sourcecodemetrics.measurer.api.IMethod;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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
public class MethodNode extends AbstractNode {

    public MethodNode(IMethod method) {
        super(Children.LEAF);
        this.method = method;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        if (method != null) {
            set.put(new MetricPropertyInteger("LOC", method.getLOC(), "Lines Of Code"));
            set.put(new MetricPropertyInteger("LOCm", method.getLOCm(), "Lines Of Code"));
            set.put(new MetricPropertyInteger("NBD", method.getNBD(), "Nested Block Depth"));
            set.put(new MetricPropertyInteger("NOP", method.getNOP(), "Number Of Parameters"));
            set.put(new MetricPropertyInteger("VG", method.getVG(), "McCabe's Cyclomatic Complexity"));
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
                    dobj = DataObject.find(method.getFileObject());
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
                            Pattern p = Pattern.compile("\\w+ +(" + method.getName() + ") *\\(.*\\) *\\{");
                            Matcher m = p.matcher(text);
                            if (m.find()) {
                                int idx = m.start(1);
                                jtc.setCaretPosition(idx);
                            }
                        }
                    }
                });
            }
        };
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/sourcecodemetrics/view/top/icons/method.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getName() {
        if (method != null) {
            return method.getName();
        } else {
            return "Error!";
        }
    }
    private IMethod method;
}
