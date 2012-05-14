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

package org.sourcecodemetrics.view.top;

import org.sourcecodemetrics.measurer.MeasurerImpl;
import org.sourcecodemetrics.measurer.api.IMeasurer;
import org.sourcecodemetrics.measurer.api.IProject;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.sourcecodemetrics.view.top.nodes.ProjectNode;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.project.Project;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.TaskListener;

@ActionID(category = "Tools",
id = "metrics.view.MetricsAction")
@ActionRegistration(iconBase = "org/sourcecodemetrics/view/top/magnifier.gif",
displayName = "#CTL_MetricsAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 190),
    @ActionReference(path = "Projects/Actions", position = 1400, separatorBefore = 1350, separatorAfter = 1450)
})
@Messages("CTL_MetricsAction=Source Code Metrics")
public final class MetricsAction implements ActionListener {

    private final Project context;
    /**
     * Allows to run only one task at a time and tasks are interruptible.
     */
    private final static RequestProcessor RP = new RequestProcessor("interruptible tasks", 1, true);
    /**
     * Used to measure the metrics of the project.
     */
    private static IMeasurer measurer = null;

    public MetricsAction(Project context) {
        this.context = context;
    }

    public static IProject getProject() {
        return project;
    }
    private static IProject project = null;

    @Override
    public void actionPerformed(ActionEvent ev) {

        Runnable r = new Runnable() {

            @Override
            public void run() {

                // make sure that the measurer is built correctly
                if (measurer == null) {
                    measurer = new MeasurerImpl();
                }

                // Initlization and measurement
                project = null;
                measurer.initilize();
                try {
                    project = measurer.measure(context.getProjectDirectory());
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error. Couldn't perform the measurements, due to the exception.", e);
                    NotifyDescriptor d =
                            new NotifyDescriptor.Message(
                            "Error. Couldn't perform the measurements, due to "
                            + "the exception: \r\n" + e.getMessage(), NotifyDescriptor.ERROR_MESSAGE);
                    DialogDisplayer.getDefault().notify(d);
                    return;
                }

                // make sure that the returned project is valid
                if (project == null) {
                    NotifyDescriptor d =
                            new NotifyDescriptor.Message(
                            "The returned project is null.", NotifyDescriptor.ERROR_MESSAGE);
                    DialogDisplayer.getDefault().notify(d);
                    return;
                }
            }
        };

        final RequestProcessor.Task measurementTask = RP.create(r);

        final ProgressHandle ph = ProgressHandleFactory.createHandle("Measuring...", measurementTask);
        measurementTask.addTaskListener(new TaskListener() {

            @Override
            public void taskFinished(Task task) {

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (project != null) {
                            // set the new context in the metrics top component
                            MetricsWindowTopComponent mwtc = MetricsWindowTopComponent.getSelf();
                            mwtc.getExplorerManager().setRootContext(new ProjectNode(project));
                            mwtc.setDisplayName("Source Code Metrics of " + project.getName());

                            // remember the measurements of the given project
                            Projects.addMeasurement(context.getProjectDirectory().getName(), context, project);

                            // show the metrics window top component
                            if (!mwtc.isOpened()) {
                                mwtc.open();
                            }
                            mwtc.setMessage(true, "");
                        }
                    }
                });

                // progress indicator to finish when the measurement has been finished
                ph.finish();
            }
        });

        ph.start();
        measurementTask.schedule(0); // start the measurement
    }
    private static final Logger logger = Logger.getLogger(MetricsAction.class.getName());
}
