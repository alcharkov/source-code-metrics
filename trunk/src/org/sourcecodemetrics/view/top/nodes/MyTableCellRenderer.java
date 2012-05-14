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

import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreeNode;
import org.openide.explorer.view.Visualizer;
import org.openide.nodes.Node;
import org.sourcecodemetrics.view.options.limitations.MetricConfiguration;
import org.sourcecodemetrics.view.options.limitations.MetricConfigurations;

/**
 *
 * @author Krystian
 */
public class MyTableCellRenderer extends JLabel implements TableCellRenderer {

    public MyTableCellRenderer() {
        hsbColor = Color.RGBtoHSB(51, 153, 255, null);
        blueColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        lbl = new JLabel();

        if (value != null) {

            // getting the metric scope
            String scope = "";
            TreeNode o = (TreeNode) table.getValueAt(row, 0);
            Node node = Visualizer.findNode(o);
            if (node instanceof PackageNode) {
                scope = "package";
            } else if (node instanceof ClassNode) {
                scope = "class";
            } else if (node instanceof MethodNode) {
                scope = "method";
            } else if (node instanceof ProjectNode) {
                scope = "project";
            }

            // getting the metric name
            TableColumnModel tcm = table.getColumnModel();
            TableColumn tc = tcm.getColumn(column);
            String metricName = tc.getHeaderValue().toString() + " " + scope;

            // getting the configuration of the metric
            Map<String, MetricConfiguration> mm = MetricConfigurations.getMc().getMetricConfigurationsMap();
            MetricConfiguration mc = mm.get(metricName);

            Double mv = null;
            try {
                mv = Double.valueOf(value.toString());
            } catch (NumberFormatException ex) {
                lbl.setText(value.toString());
                lbl.setToolTipText(value.toString());
                return lbl;
            }

            // if the metric value is outside limits then label is red
            if (mc != null) {
                if (mv < mc.getMinimum() || mc.getMaximum() < mv) {
                    lbl.setForeground(Color.red);
                }
            }

            String tooltip = value.toString();
            if (value instanceof TooltipProvider) {
                TooltipProvider tp = (TooltipProvider) value;
                tooltip = tp.getTooltip();
            }

            lbl.setText(value.toString());
            lbl.setToolTipText(tooltip);

            if (isSelected) {
                lbl.setOpaque(true);
                lbl.setBackground(blueColor);
            }
        } else {
            lbl.setText("");
        }
        return lbl;
    }
    private JLabel lbl;
    private static float[] hsbColor;
    private static Color blueColor;
}
