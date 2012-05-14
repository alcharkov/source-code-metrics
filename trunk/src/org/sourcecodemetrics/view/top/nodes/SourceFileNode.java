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

import org.sourcecodemetrics.measurer.api.ISourceFile;
import java.awt.Image;
import org.sourcecodemetrics.view.top.nodes.factories.SourceFileChildFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Krystian Warzocha
 */
public class SourceFileNode extends AbstractNode {

    public SourceFileNode(ISourceFile srcFile) {
        super(Children.create(new SourceFileChildFactory(srcFile), true));
        this.srcFile = srcFile;
    }

    @Override
    public String getName() {
        return srcFile.getName();
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/sourcecodemetrics/view/top/icons/source.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    private ISourceFile srcFile;
}
