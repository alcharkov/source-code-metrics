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

package org.sourcecodemetrics.measurer.model.classes;

import org.sourcecodemetrics.measurer.model.methods.MethodImpl;
import org.sourcecodemetrics.measurer.model.sources.Comment;
import org.sourcecodemetrics.measurer.util.FindAndUnion;
import org.sourcecodemetrics.measurer.util.Utilities;
import org.sourcecodemetrics.measurer.visitors.AssertVisitor;
import org.sourcecodemetrics.measurer.visitors.FieldCounterVisitor;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.sourcecodemetrics.measurer.util.CommentUtility;

/**
 *
 * @author Krystian
 */
public class ClassMeasurer {

    public ClassMeasurer(ClassImpl c) {
        this.c = c;
    }

    public void measure() throws IOException {
        logger.finer("Started measuring class " + c.fullName);

        // command all of the methods to perform measurements
        for (MethodImpl im : c.methods.values()) {
            im.measure();
        }

        // remove invalid constructors
        Iterator<String> it = c.methods.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            MethodImpl mi = c.methods.get(key);
            if (!mi.isValid()) {
                it.remove();
            }
        }

        // count number of all of the fields in the class
        //c.nof = new FieldCounterVisitor(false).scan(c.ct, null);
        c.nof = c.fields.size();

        // count number of static fields in the class
        c.nosf = new FieldCounterVisitor(true).scan(c.ct, null);

        // count number of static methods
        c.nosm = 0;
        for (MethodImpl im : c.methods.values()) {
            if (im.isStat()) {
                c.nosm++;
            }
        }
        // count number of pairs of methods using disjoint sets of fields
        c.lcom1 = computeLCOM1();

        // computation of the LCOM2 metric
        c.lcom2 = computeLCOM2(c.lcom1);

        // computation of the LCOM3 metric
        Collection<Set<MethodImpl>> lcom3Sets = computeMethodSets(false);
        c.lcom3 = lcom3Sets.size();
        logger.finest("Class's " + c.name + " LCOM3=" + c.lcom3 + ", method sets: " + buildSetString(lcom3Sets));

        // computation of LCOM4 metric
        Collection<Set<MethodImpl>> lcom4Sets = computeMethodSets(true);
        c.lcom4 = computeMethodSets(true).size();
        logger.finest("Class's " + c.name + " LCOM4=" + c.lcom4 + ", method sets: " + buildSetString(lcom4Sets));

        c.lcom5 = calculateLCOM5();

        computeCC();

        // calculation of the WMC
        c.wmc = 0;
        for (MethodImpl m : c.getMethodImpls()) {
            c.wmc += m.getVG();
        }

        countLOC(); // counting LOC

        // counting the number of test methods
        c.ntm = 0;
        for (MethodImpl mi : c.methods.values()) {
            if (mi.isTest()) {
                c.ntm++;
            }
        }

//        // computation of all of the asserts
//        int NOA = new AssertVisitor().scan(c.ct, null); // number of all of the asserts in the class
//        double kloc = c.loc / 1000.0;
//        c.nak = (int) (((double) NOA) / kloc);

        // counting the sums of metrics of methods
        c.vgAvg = 0;
        c.nopSum = 0;
        c.nbdAvg = 0;
        for (MethodImpl mi : c.methods.values()) {
            c.vgAvg += mi.getVG();
            c.nopSum += mi.getNOP();
            c.nbdAvg += mi.getNBD();
        }

        // calculating the average of the metrics of methods
        if (c.methods.size() > 0) {
            c.nbdAvg /= c.methods.size();
            c.vgAvg /= c.methods.size();
        }

        // calculation of the maximal and minimal values of metrics of methods
        if (c.getMethodImpls().size() > 0) {
            MethodImpl method = c.getMethodImpls().iterator().next();
            c.vgMax = method.getVG();
            c.nbdMax = method.getNBD();
            c.nopMax = method.getNOP();
            c.locMax = method.getLOC();
            c.locmMax = method.getLOCm();
            c.vgMin = method.getVG();
            c.nbdMin = method.getNBD();
            c.nopMin = method.getNOP();
            c.locMin = method.getLOC();
            c.locmMin = method.getLOCm();
            for (MethodImpl mi : c.getMethodImpls()) {
                if (mi.getVG() != null && c.vgMax != null) {
                    if (mi.getVG() > c.vgMax) {
                        c.vgMax = mi.getVG();
                    } else if (mi.getVG() < c.vgMin) {
                        c.vgMin = mi.getVG();
                    }
                }

                if (mi.getNBD() != null && c.nbdMax != null) {
                    if (mi.getNBD() > c.nbdMax) {
                        c.nbdMax = mi.getNBD();
                    } else if (mi.getNBD() < c.nbdMin) {
                        c.nbdMin = mi.getNBD();
                    }
                }

                if (mi.getNOP() != null && c.nopMax != null) {
                    if (mi.getNOP() > c.nopMax) {
                        c.nopMax = mi.getNOP();
                    } else if (mi.getNOP() < c.nopMin) {
                        c.nopMin = mi.getNOP();
                    }
                }

                if (mi.getLOC() != null && c.locMax != null) {
                    if (mi.getLOC() > c.locMax) {
                        c.locMax = mi.getLOC();
                    } else if (mi.getLOC() < c.locMin) {
                        c.locMin = mi.getLOC();
                    }
                }

                if (mi.getLOCm() != null && c.locmMax != null) {
                    if (mi.getLOCm() > c.locmMax) {
                        c.locmMax = mi.getLOCm();
                    } else if (mi.getLOCm() < c.locmMin) {
                        c.locmMin = mi.getLOCm();
                    }
                }
            }
        }

        logger.finer("NOF: " + c.nof + ", NOSF: " + c.nosf + ", NOSM: " + c.nosm
                + ", LOCM1: " + c.lcom1 + ", LOCM2: " + c.lcom2 + ", LOCM3: " + c.lcom3
                + ", LOCM4: " + c.lcom4 + ", LOCM5: " + c.lcom5 + ", TCC: " + c.tcc
                + ", LCC: " + c.lcc + ", WMC: " + c.wmc + ", LOC: " + c.loc
                + ", LOCM: " + c.locm + ", NTM: " + c.ntm + ", NAK: " + c.nak
                + ", MVG: " + c.vgMax + ", MNBD: " + c.nbdMax + ", MNOP: " + c.nopMax);
        logger.finer("Finished measuring class " + c.fullName);
    }

    /**
     * Computes the LCOM1 metric on this class based on the set of methods.
     * @return LCOM1 metric for this class.
     */
    private int computeLCOM1() {
        int result = 0; // contains the LCOM1 metric
        Iterator<Entry<String, MethodImpl>> it = c.methods.entrySet().iterator();
        while (it.hasNext()) {
            // the selected method to which will following methods will be compared
            MethodImpl method = it.next().getValue();

            // the set of methods that follow the selected method
            NavigableMap<String, MethodImpl> followingMethods = c.methods.tailMap(method.getSignature(), false);

            for (Entry<String, MethodImpl> im2 : followingMethods.entrySet()) {
                MethodImpl followingMethod = im2.getValue(); // one of the following methods

                // getting the fields used by both methods
                Set<String> fields1 = method.getFieldsUsed();
                Set<String> fields2 = followingMethod.getFieldsUsed();

                if (Collections.disjoint(fields1, fields2)) {
                    // this is another pait of disjoint fields
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Computes the LCOM2 for this class.
     * @param lcom1 The value of LCOM1 for this class.
     * @return The LCOM2 metric for this class.
     */
    private int computeLCOM2(int lcom1) {
        // calculation of the number of all of the combinations of the methods of this class
        int numMeth = c.methods.size();
        BigDecimal N = Utilities.factorial(numMeth);
        BigDecimal NK = Utilities.factorial(numMeth - 2);
        int combinations = N.divide(NK).divide(new BigDecimal(2)).intValue();

        int P = lcom1; // sum of all pairs of methods which reference no common attribute
        int Q = combinations - lcom1; // all other pairs
        int result = P - Q;
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    /**
     * The role of this method is to build the sets of methods which are 
     * using the same class fields. Methods are inserted into the same set if
     * they are using at least one common class field.
     * @param all If it is false then only directly used fields are taken into
     * account. Otherwise all fields are taken into account (including fields
     * used indirectly, by calling other methods).
     * @return Collection of disjoint sets of methods, which do not use the same
     * fields. 
     */
    private Collection<Set<MethodImpl>> computeMethodSets(boolean all) {
        // helper object for building sets of methods
        FindAndUnion<MethodImpl> fAndU = new FindAndUnion<MethodImpl>(c.methods.values());

        for (MethodImpl m1 : c.methods.values()) {
            Set<String> v1 = (all ? m1.getAllFieldsUsed() : m1.getFieldsUsed());
            for (MethodImpl m2 : c.methods.values()) {
                if (!m1.getSignature().equals(m2.getSignature())) {
                    Set<String> v2 = (all ? m2.getAllFieldsUsed() : m2.getFieldsUsed());
                    if (!Collections.disjoint(v1, v2)) {
                        // methods are connected by the usage of same attributes
                        // so they should be put in one set
                        fAndU.union(m1, m2);
                    }
                }
            }
        }
        return fAndU.getSet();
    }

    /**
     * The role of this class is to collect the set of methods which are using 
     * common class field. This is done for every field in the class.
     * @return The set of sets of methods using specified class field.
     */
    private List<Integer> computeMethodSets() {
        List<Integer> sets = new ArrayList<Integer>(c.fields.size());
        for (String f : c.fields.keySet()) {
            int setSize = 0;
            // count all methods using this class field f directly or indirectly
            for (MethodImpl m : c.getMethodImpls()) {
                if (m.getFieldsUsed().contains(f)) {
                    // field f is used directly by method m
                    setSize++;
                } else if (m.getFieldsUsed().isEmpty() && m.getAllFieldsUsed().contains(f)) {
                    // method m does not use any fields directly but uses field f indirectly
                    setSize++;
                }
            }
            sets.add(setSize);
        }
        return sets;
    }

    /**
     * The role of this class is to collect the set of methods which are using 
     * common class field directly or indirectly.
     * This is done for every field in the class.
     * @return The set of sets of methods using specified class field.
     */
    private List<Integer> computeIndirectMethodSets() {
        List<Integer> sets = new ArrayList<Integer>(c.fields.size());
        for (String f : c.fields.keySet()) {
            int setSize = 0;
            // count all methods using this class field f directly or indirectly
            for (MethodImpl m : c.getMethodImpls()) {
                if (m.getAllFieldsUsed().contains(f)) {
                    // field f is used directly or indirectly by method m
                    setSize++;
                }
            }
            sets.add(setSize);
        }
        return sets;
    }

    private Double calculateLCOM5() {
        double lcom5 = 0;
        int NOM = c.getMethodImpls().size(); // Number Of Methods
        int NOA = c.getFields().size(); // Number Of Attributes

        if (NOM > 1 && NOA > 0) {
            int NOAA = 0; // Number Of Attributes Accessed by all of the methods
            for (MethodImpl m : c.getMethodImpls()) {
                NOAA += m.getFieldsUsed().size();
            }

            // calculation of the LCOM5
            lcom5 = ((double) NOM - (double) NOAA / (double) NOA) / (((double) NOM) - 1.0);
        }

        return lcom5;
    }

    private void computeCC() {
        int N = c.methods.size(); // number of methods
        int NP = N * (N - 1) / 2; // maximum number of connections between all methods
        int NDC = 0;              // number of direct connections
        int NIC = 0;              // number of indirect connections

        // graph which holds the connections between methods
        UndirectedGraph<MethodImpl, DefaultEdge> methodGraph =
                new SimpleGraph<MethodImpl, DefaultEdge>(DefaultEdge.class);
        for (MethodImpl m : c.getMethodImpls()) {
            methodGraph.addVertex(m);
        }

        // building sets of methods using common attribute
        List<List<MethodImpl>> sets = new LinkedList<List<MethodImpl>>();
        for (String f : c.fields.keySet()) {
            List<MethodImpl> set = new ArrayList<MethodImpl>();
            // count all methods using this class field f directly or indirectly
            for (MethodImpl m : c.getMethodImpls()) {
                if (m.getFieldsUsed().contains(f)) {
                    // field f is used directly by method m
                    set.add(m);
                } else if (m.getFieldsUsed().isEmpty() && m.getAllFieldsUsed().contains(f)) {
                    // method m does not use any fields directly but uses field f indirectly
                    set.add(m);
                }
            }
            sets.add(set);
        }

        // building the graph of methods using common attributes
        ListIterator<List<MethodImpl>> setsIterator = sets.listIterator();
        while (setsIterator.hasNext()) {
            List<MethodImpl> set = setsIterator.next();
            ListIterator<MethodImpl> setIterator = set.listIterator();
            while (setIterator.hasNext()) {
                int index = setIterator.nextIndex();
                MethodImpl m1 = setIterator.next();
                ListIterator<MethodImpl> subSetIterator = set.listIterator(index + 1);
                while (subSetIterator.hasNext()) {
                    MethodImpl m2 = subSetIterator.next();
                    methodGraph.addEdge(m1, m2);
                }
            }
        }

        // Building the connected sets in the graph
        ConnectivityInspector<MethodImpl, DefaultEdge> ci = new ConnectivityInspector<MethodImpl, DefaultEdge>(methodGraph);
        List<Set<MethodImpl>> connectedSets = ci.connectedSets();

        // calculation of number of direct connections and of TCC
        NDC = methodGraph.edgeSet().size();
        if (NP > 0) {
            c.tcc = ((double) NDC) / ((double) NP);
        } else {
            c.tcc = 0.0;
        }

        // calculation of number of indirect connections and of LCC
        if (NP > 0) {
            for (Set<MethodImpl> connectedSet : connectedSets) {
                int NN = connectedSet.size();
                NIC += NN * (NN - 1) / 2;
            }
            c.lcc = ((double) NIC) / ((double) NP);
        } else {
            c.lcc = 0.0;
        }
    }

    private void countLOC() throws IOException {
        // calculation of LOC
        c.loc = 0;
        int start = 0, end = 0; // contain the start and end elements of the 
        String text = c.fo.asText();

        String s = "";
        switch (c.getCompilationUnitKind()) {
            case CLASS:
                s = "class";
                break;
            case ENUMERATION:
                s = "enum";
                break;
            case INTERFACE:
                s = "interface";
                break;
        }

        String p = s + " +" + c.name;
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {

            try {

                int index = matcher.start();
                start = index;
                if (index != -1) {
                    int parethesisIndex = text.indexOf("{", index);
                    int accumulator = 1;
                    int numberOfLines = 1;
                    int i = parethesisIndex + 1;
                    while (accumulator != 0) {
                        if (text.charAt(i) == '\n') {
                            numberOfLines++;
                        } else if (text.charAt(i) == '{') {
                            if (!CommentUtility.isAComment(c.sf.getComments(), i)) {
                                accumulator++;
                            }
                        } else if (text.charAt(i) == '}') {
                            if (!CommentUtility.isAComment(c.sf.getComments(), i)) {
                                accumulator--;
                            }
                        }
                        i++;
                        end = i;
                        c.loc = numberOfLines + 1;
                    }
                }
            } catch (StringIndexOutOfBoundsException ex) {
            }

            // calculation of LOCm
            c.locm = 0;
            for (Comment comment : c.sf.getComments()) {
                if (comment.isContained(start, end)) {
                    c.locm += comment.getLines();
                }
            }
        }
    }

    /**
     * Returns the text depicting the sets of methods.
     * @param sets The sets of methods.
     * @return Text representing the sets of methods.
     */
    private String buildSetString(Collection<Set<MethodImpl>> sets) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Set<MethodImpl> set : sets) {
            sb.append("{ ");
            for (MethodImpl method : set) {
                sb.append(method.getSignature()).append(" ");
            }
            sb.append(" } ");
        }
        sb.append("}");
        return sb.toString();
    }
    private ClassImpl c;
    private static final Logger logger = Logger.getLogger(ClassMeasurer.class.getName());
}
