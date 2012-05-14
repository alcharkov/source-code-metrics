package org.sourcecodemetrics.view.top;

import org.sourcecodemetrics.measurer.api.IProject;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.api.project.Project;

/**
 *
 * @author Krystian
 */
public class Projects {

    /**
     * Adds the outcome of the measurements of the project to the list of 
     * already performed measurements.
     * @param name Name of the project.
     * @param project Project itself.
     * @param iProject Measurements.
     */
    public static void addMeasurement(String name, Project project, IProject iProject) {
        measurements.put(name, new AbstractMap.SimpleEntry<Project, IProject>(project, iProject));
    }

    /**
     * Determines whether measurements have already been performed for the 
     * specified project.
     * @param name Name of the project.
     * @return True if mesurements have been performed.
     */
    public static boolean contains(String name) {
        return measurements.containsKey(name);
    }

    /**
     * Returns the measurements of the given project.
     * @param name Name of the given project.
     * @return Measurements. 
     */
    public static IProject getMeasurements(String name) {
        AbstractMap.SimpleEntry<Project, IProject> entry = measurements.get(name);
        if (entry != null) {
            return entry.getValue();
        } else {
            return null;
        }
    }
    /**
     * Maps the measurements of the project to the name of the project and the 
     * reference of the project in the IDE.
     */
    private static Map<String, AbstractMap.SimpleEntry<Project, IProject>> measurements =
            new HashMap<String, AbstractMap.SimpleEntry<Project, IProject>>();
}
