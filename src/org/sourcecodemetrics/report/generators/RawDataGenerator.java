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

package org.sourcecodemetrics.report.generators;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openide.util.Exceptions;
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.IMethod;
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
import org.sourcecodemetrics.measurer.api.ISourceFile;

/**
 *
 * @author Krystian Warzocha
 */
public class RawDataGenerator {

    public static void generateRawData(IProject project, HSSFWorkbook workbook) {
        try {
            generatePackages(project, workbook);
            generateClasses(project, workbook);
            generateMethods(project, workbook);
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InvocationTargetException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void generatePackages(IProject project, HSSFWorkbook workbook) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        HSSFSheet worksheet = workbook.createSheet("Raw package metrics");

        // generation of header row
        HSSFRow headerRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        for (int i = 0; i < packageMetrics.size(); i++) {
            String title = packageMetrics.get(i);
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(title);
        }

        // generation of package metrics
        for (IPackage pkg : project.getPackages()) {
            if (!pkg.isTests() && !pkg.getSourceFiles().isEmpty()) {

                HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

                // writing out the name of the package
                HSSFCell nameCell = row.createCell(0);
                nameCell.setCellValue(pkg.getName());

                for (int i = 1; i < packageMetrics.size(); i++) {

                    String propertyName = packageMetrics.get(i);
                    Method getter = pkg.getClass().getMethod("get" + propertyName);
                    Object metricValue = getter.invoke(pkg);

                    HSSFCell cell = row.createCell(i);

                    if (metricValue instanceof Integer) {
                        cell.setCellValue((Integer) metricValue);
                    } else if (metricValue instanceof Double) {
                        cell.setCellValue((Double) metricValue);
                    } else if (metricValue instanceof String) {
                        cell.setCellValue((String) metricValue);
                    }
                }
            }
        }

        // autosizing the main column
        worksheet.autoSizeColumn(0);
    }

    private static void generateClasses(IProject project, HSSFWorkbook workbook) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HSSFSheet worksheet = workbook.createSheet("Raw class metrics");

        // generation of header row
        HSSFRow headerRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        for (int i = 0; i < classMetrics.size(); i++) {
            String title = classMetrics.get(i);
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(title);
        }

        // generation of class metrics
        for (IPackage pkg : project.getPackages()) {
            if (!pkg.isTests() && !pkg.getSourceFiles().isEmpty()) {
                for (ISourceFile sf : pkg.getSourceFiles()) {
                    for (IClass c : sf.getClasses()) {
                        HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

                        // writing out the name of the package
                        HSSFCell packageCell = row.createCell(0);
                        packageCell.setCellValue(pkg.getName());

                        // writing out the name of the class
                        HSSFCell classCell = row.createCell(1);
                        classCell.setCellValue(c.getName());

                        // writing values of the metrics
                        for (int i = 2; i < classMetrics.size(); i++) {
                            String propertyName = classMetrics.get(i);
                            Method getter = c.getClass().getMethod("get" + propertyName);
                            Object metricValue = getter.invoke(c);

                            HSSFCell cell = row.createCell(i);

                            if (metricValue instanceof Integer) {
                                cell.setCellValue((Integer) metricValue);
                            } else if (metricValue instanceof Double) {
                                cell.setCellValue((Double) metricValue);
                            } else if (metricValue instanceof String) {
                                cell.setCellValue((String) metricValue);
                            } else if (metricValue instanceof Boolean) {
                                Boolean value = (Boolean) metricValue;
                                cell.setCellValue(value ? 1 : 0);
                            }
                        }
                    }
                }
            }
        }

        // autosizing the main columns
        for (int i = 0; i < 2; i++) {
            worksheet.autoSizeColumn(i);
        }
    }

    private static void generateMethods(IProject project, HSSFWorkbook workbook) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HSSFSheet worksheet = workbook.createSheet("Raw method metrics");

        // generation of header row
        HSSFRow headerRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        for (int i = 0; i < methodMetrics.size(); i++) {
            String title = methodMetrics.get(i);
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(title);
        }

        // generation of method metrics
        for (IPackage pkg : project.getPackages()) {
            if (!pkg.isTests() && !pkg.getSourceFiles().isEmpty()) {
                if (!pkg.isTests() && !pkg.getSourceFiles().isEmpty()) {
                    for (ISourceFile sf : pkg.getSourceFiles()) {
                        for (IClass c : sf.getClasses()) {
                            for (IMethod m : c.getMethods()) {
                                HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

                                // writing out the name of the package
                                HSSFCell packageCell = row.createCell(0);
                                packageCell.setCellValue(pkg.getName());

                                // writing out the name of the class
                                HSSFCell classCell = row.createCell(1);
                                classCell.setCellValue(c.getName());

                                // writing out the name of the method
                                HSSFCell methodCell = row.createCell(2);
                                methodCell.setCellValue(m.getName());

                                for (int i = 3; i < methodMetrics.size(); i++) {
                                    String propertyName = methodMetrics.get(i);
                                    Method getter = m.getClass().getMethod("get" + propertyName);
                                    Object metricValue = getter.invoke(m);

                                    HSSFCell cell = row.createCell(i);

                                    if (metricValue instanceof Integer) {
                                        cell.setCellValue((Integer) metricValue);
                                    } else if (metricValue instanceof Double) {
                                        cell.setCellValue((Double) metricValue);
                                    } else if (metricValue instanceof String) {
                                        cell.setCellValue((String) metricValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // autosizing the main columns
        for (int i = 0; i < 3; i++) {
            worksheet.autoSizeColumn(i);
        }
    }
    public static final List<String> packageMetrics = new ArrayList<String>(Arrays.asList(
            "Package",
            "A",
            "AC",
            "C",
            "D",
            "EC",
            "I",
            "NCP",
            "NIP",
            "LOC",
            "LOCm"));
    public static final List<String> classMetrics = new ArrayList<String>(Arrays.asList(
            "Package",
            "Class",
            "C",
            "LCC",
            "LCOM1",
            "LCOM2",
            "LCOM3",
            "LCOM4",
            "LCOM5",
            "NAK",
            "NOC",
            "NOF",
            "NOM",
            "NOSF",
            "NOSM",
            "NTM",
            "TCC",
            "WMC",
            "LOC",
            "LOCm"));
    public static final List<String> methodMetrics = new ArrayList<String>(Arrays.asList(
            "Package",
            "Class",
            "Method",
            "NBD",
            "NOP",
            "VG",
            "LOC",
            "LOCm"));
}
