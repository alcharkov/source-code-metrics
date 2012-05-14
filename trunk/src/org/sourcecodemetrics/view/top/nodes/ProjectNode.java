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

import org.sourcecodemetrics.measurer.api.IProject;
import java.awt.Image;
import org.sourcecodemetrics.view.top.nodes.factories.ProjectChildFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.sourcecodemetrics.view.top.MetricsWindowTopComponent;

/**
 * This is the presentation of the project node.
 * @author Krystian Warzocha
 */
public class ProjectNode extends AbstractNode {

    public ProjectNode(IProject project) {
        super(Children.create(new ProjectChildFactory(project), true));
        this.project = project;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        if (project != null) {
            set.put(new MetricPropertyInteger("LOC", project.getLOC(), "Total Lines Of Code"));
            set.put(new MetricPropertyInteger("LOCm", project.getLOCm(), "Total Lines Of Comments"));

            // package metrics
            set.put(new MetricPropertyInteger("NCP", project.getPackageNCPSum(), "Total Number of Classes in package"));
            set.put(new MetricPropertyInteger("NIP", project.getPackageNIPSum(), "Total number of Interfaces in package"));
            set.put(new MetricPropertyDouble("A", project.getPackageAAvg(), "Average Abstractness of the package"));
            set.put(new MetricPropertyInteger("EC", project.getPackageECAvg(), "Average Efferent Coupling of the package"));
            set.put(new MetricPropertyInteger("AC", project.getPackageACAvg(), "Average Afferent Coupling of the package"));
            set.put(new MetricPropertyDouble("I", project.getPackageIAvg(), "Average Instablity of the package"));
            set.put(new MetricPropertyDouble("D", project.getPackageDAvg(), "Average Distance of the package"));
            set.put(new MetricPropertyDouble("C", project.getPackageCoverageAvg(), "Average Coverage of the package"));

            // class metrics
            set.put(new MetricPropertyInteger("LCOM1", project.getClassLCOM1Avg(), "Average Lack of COhesion in Methods 1 of a class"));
            set.put(new MetricPropertyInteger("LCOM2", project.getClassLCOM2Avg(), "Average Lack of COhesion in Methods 2 of a class"));
            set.put(new MetricPropertyInteger("LCOM3", project.getClassLCOM3Avg(), "Average Lack of COhesion in Methods 3 of a class"));
            set.put(new MetricPropertyInteger("LCOM4", project.getClassLCOM4Avg(), "Average Lack of COhesion in Methods 4 of a class"));
            set.put(new MetricPropertyDouble("LCOM5", project.getClassLCOM5Avg(), "Average Lack of COhesion in Methods 5 of a class"));
            set.put(new MetricPropertyDouble("LCC", project.getClassLCCAvg(), "Average Loose Class Cohesion"));
            set.put(new MetricPropertyDouble("TCC", project.getClassTCCAvg(), "Average Tight Class Cohesion"));
            set.put(new MetricPropertyDouble("NAK", project.getClassNAKAvg(), "Average Number of Assertions per KLOC of class"));
            set.put(new MetricPropertyInteger("NOC", project.getClassNOCSum(), "Total Number Of Children of class"));
            set.put(new MetricPropertyInteger("NOF", project.getClassNOFSum(), "Total Number Of Fields of class"));
            set.put(new MetricPropertyInteger("NOM", project.getClassNOMSum(), "Total Number Of Methods"));
            set.put(new MetricPropertyInteger("NOSF", project.getClassNOSFSum(), "Total Number Of Static Fields of class"));
            set.put(new MetricPropertyInteger("NOSM", project.getClassNOSMSum(), "Total Number Of Static Methods of class"));
            set.put(new MetricPropertyInteger("NTM", project.getClassNTMSum(), "Total Number of Test Methods of class"));
            set.put(new MetricPropertyInteger("WMC", project.getClassWMCSum(), "Total Weighted Methods per Class"));

            // method metrics
            set.put(new MetricPropertyInteger("NBD", project.getMethodNBDAvg(), "Average Nested Block Depth of method"));
            set.put(new MetricPropertyInteger("NOP", project.getMethodNOPSum(), "Total Number of Parameters of method"));
            set.put(new MetricPropertyInteger("VG", project.getMethodVGAvg(), "Average Cyclomatic Complexity of method"));
        }

        sheet.put(set);
        return sheet;
    }

    @Override
    public String getHtmlDisplayName() {
        if (project == null) {
            MetricsWindowTopComponent.getSelf().setMessage(false, "No projects have been measured yet.");
            return "none";
        } else {
            return project.getName();
        }
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/sourcecodemetrics/view/top/icons/project.png");
    }
    private IProject project;
}
