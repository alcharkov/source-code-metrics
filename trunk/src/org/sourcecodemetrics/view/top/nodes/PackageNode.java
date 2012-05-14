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

import org.sourcecodemetrics.measurer.api.IPackage;
import java.awt.Image;
import org.sourcecodemetrics.view.top.nodes.factories.PackageChildFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;

/**
 * Represents the java package and presents it as the node on the java tree.
 * @author Krystian Warzocha
 */
public class PackageNode extends AbstractNode {

    public PackageNode(IPackage pkg) {
        super(Children.create(new PackageChildFactory(pkg), true));
        this.pkg = pkg;
        if (pkg.getSourceFiles().isEmpty()) {
            this.setChildren(Children.LEAF);
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        if (pkg != null) {
            // package metrics
            set.put(new MetricPropertyInteger("NCP", pkg.getNCP(), "Number of Classes in Package"));
            set.put(new MetricPropertyInteger("NIP", pkg.getNIP(), "Number of Interfaces in Packages"));
            set.put(new MetricPropertyDouble("A", pkg.getA(), "Abstractness"));
            set.put(new MetricPropertyInteger("EC", pkg.getEC(), "Efferent Coupling"));
            set.put(new MetricPropertyInteger("AC", pkg.getAC(), "Afferent Coupling"));
            set.put(new MetricPropertyDouble("I", pkg.getI(), "Instability"));
            set.put(new MetricPropertyDouble("D", pkg.getD(), "Distance"));
            set.put(new MetricPropertyDouble("C", pkg.getC(), "JUnit Tests Coverage"));
            set.put(new MetricPropertyInteger("LOC", pkg.getLOC(), "Lines Of Code"));
            set.put(new MetricPropertyInteger("LOCm", pkg.getLOCm(), "Lines Of Comments"));
            
            // class metrics
            set.put(new MetricPropertyInteger("LCOM1", pkg.getClassLCOM1Avg(), "Average Lack of COhesion in Methods 1"));
            set.put(new MetricPropertyInteger("LCOM2", pkg.getClassLCOM2Avg(), "Average Lack of COhesion in Methods 2"));
            set.put(new MetricPropertyInteger("LCOM3", pkg.getClassLCOM3Avg(), "Average Lack of COhesion in Methods 3"));
            set.put(new MetricPropertyInteger("LCOM4", pkg.getClassLCOM4Avg(), "Average Lack of COhesion in Methods 4"));
            set.put(new MetricPropertyDouble("LCOM5", pkg.getClassLCOM5Avg(), "Average Lack of COhesion in Methods 5"));
            set.put(new MetricPropertyDouble("LCC", pkg.getClassLCCAvg(), "Average Loose Class Cohesion"));
            set.put(new MetricPropertyDouble("TCC", pkg.getClassTCCAvg(), "Average Tight Class Cohesion"));
            set.put(new MetricPropertyDouble("NAK", pkg.getClassNAKAvg(), "Average Number of Assertions per KLOC"));
            set.put(new MetricPropertyInteger("NOC", pkg.getClassNOCSum(), "Total Number Of Classes"));
            set.put(new MetricPropertyInteger("NOF", pkg.getClassNOFSum(), "Total Number Of Fields"));
            set.put(new MetricPropertyInteger("NOM", pkg.getClassNOMSum(), "Total Number Of Methods"));
            set.put(new MetricPropertyInteger("NOSF", pkg.getClassNOSFSum(), "Total Number Of Static Fields"));
            set.put(new MetricPropertyInteger("NOSM", pkg.getClassNOSMSum(), "Total Number Of Static Methods"));
            set.put(new MetricPropertyInteger("NTM", pkg.getClassNTMSum(), "Total Number of Test Methods"));
            set.put(new MetricPropertyInteger("WMC", pkg.getClassWMCSum(), "Total Weighted Methods per Class"));
            
            // method metrics
            set.put(new MetricPropertyInteger("NBD", pkg.getMethodNBDAvg(), "Average Nested Block Depth"));
            set.put(new MetricPropertyInteger("NOP", pkg.getMethodNOPSum(), "Total Number Of Parameters"));
            set.put(new MetricPropertyInteger("VG", pkg.getMethodVGAvg(), "Average Cyclomatic Complexity"));
        }

        sheet.put(set);
        return sheet;
    }

    @Override
    public String getName() {
        if (pkg == null) {
            return "Error!";
        } else {
            return pkg.getName();
        }
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/sourcecodemetrics/view/top/icons/package.png");
    }
    private IPackage pkg;
}
