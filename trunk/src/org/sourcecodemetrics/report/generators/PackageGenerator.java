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

import java.util.Map;
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.sourcecodemetrics.view.options.limitations.MetricConfiguration;
import org.sourcecodemetrics.view.options.limitations.MetricConfigurations;

/**
 *
 * @author Krystian
 */
public class PackageGenerator {

    public static void generate(IPackage p, HSSFSheet worksheet, HSSFWorkbook workbook) {
        int startRow = worksheet.getPhysicalNumberOfRows();

        HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

        // writing the name of the package
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(p.getName());
        cell.setCellStyle(ReportGeneratorImpl.getBlueStyle());

        for (int i = 1; i < ReportGeneratorImpl.headings.size(); i++) {
            HSSFCell c = row.createCell(i);
            c.setCellValue("");
            c.setCellStyle(ReportGeneratorImpl.getDottedBlueStyle());
        }

        int colIdx = ReportGeneratorImpl.headings.indexOf("NCP");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NCP", "package", (double) p.getNCP());
        cell.setCellValue(p.getNCP());

        colIdx = ReportGeneratorImpl.headings.indexOf("NIP");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NIP", "package", (double) p.getNIP());
        cell.setCellValue(p.getNIP());

        colIdx = ReportGeneratorImpl.headings.indexOf("A");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "A", "package", (double) p.getA());
        cell.setCellValue(p.getA());

        colIdx = ReportGeneratorImpl.headings.indexOf("EC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "EC", "package", (double) p.getEC());
        cell.setCellValue(p.getEC());

        colIdx = ReportGeneratorImpl.headings.indexOf("AC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "AC", "package", (double) p.getAC());
        cell.setCellValue(p.getAC());

        colIdx = ReportGeneratorImpl.headings.indexOf("I");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "I", "package", (double) p.getI());
        cell.setCellValue(p.getI());

        colIdx = ReportGeneratorImpl.headings.indexOf("D");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "D", "package", (double) p.getD());
        cell.setCellValue(p.getD());

        colIdx = ReportGeneratorImpl.headings.indexOf("C");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "C", "package", (double) p.getC());
        cell.setCellValue(p.getC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOC", "package", (double) p.getLOC());
        cell.setCellValue(p.getLOC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCm");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCm", "package", (double) p.getLOCm());
        cell.setCellValue(p.getLOCm());

        // class metrics 
        colIdx = ReportGeneratorImpl.headings.indexOf("LCC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCC", "class", (double) p.getClassLCCAvg());
        cell.setCellValue(p.getClassLCCAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM1");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCM1", "class", (double) p.getClassLCOM1Avg());
        cell.setCellValue(p.getClassLCOM1Avg());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM2");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCM2", "class", (double) p.getClassLCOM2Avg());
        cell.setCellValue(p.getClassLCOM2Avg());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM3");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCM3", "class", (double) p.getClassLCOM3Avg());
        cell.setCellValue(p.getClassLCOM3Avg());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM4");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCM4", "class", (double) p.getClassLCOM4Avg());
        cell.setCellValue(p.getClassLCOM4Avg());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM5");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCM5", "class", (double) p.getClassLCOM5Avg());
        cell.setCellValue(p.getClassLCOM5Avg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NAK");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NAK", "class", (double) p.getClassNAKAvg());
        cell.setCellValue(p.getClassNAKAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOF");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOF", "class", (double) p.getClassNOFSum());
        cell.setCellValue(p.getClassNOFSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOM", "class", (double) p.getClassNOMSum());
        cell.setCellValue(p.getClassNOMSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOSF");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOSF", "class", (double) p.getClassNOSFSum());
        cell.setCellValue(p.getClassNOSFSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOSM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOSM", "class", (double) p.getClassNOSMSum());
        cell.setCellValue(p.getClassNOSMSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("NTM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NTM", "class", (double) p.getClassNTMSum());
        cell.setCellValue(p.getClassNTMSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("TCC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "TCC", "class", (double) p.getClassTCCAvg());
        cell.setCellValue(p.getClassTCCAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("WMC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "WMC", "class", (double) p.getClassWMCSum());
        cell.setCellValue(p.getClassWMCSum());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOC", "class", (double) p.getClassNOCSum());
        cell.setCellValue(p.getClassNOCSum());

        // method metrics
        colIdx = ReportGeneratorImpl.headings.indexOf("VG");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "VG", "method", (double) p.getMethodVGAvg());
        cell.setCellValue(p.getMethodVGAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NBD");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NBD", "method", (double) p.getMethodNBDAvg());
        cell.setCellValue(p.getMethodNBDAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOP");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOP", "method", (double) p.getMethodNOPSum());
        cell.setCellValue(p.getMethodNOPSum());

        // generation of all of the classes
        for (ISourceFile isf : p.getSourceFiles()) {
            for (IClass ic : isf.getClasses()) {
                ClassGenerator.generate(ic, worksheet, workbook);
            }
        }
        ReportGeneratorImpl.appendEmptyRow(worksheet);

        int endRow = worksheet.getPhysicalNumberOfRows() - 2;
        worksheet.groupRow(startRow, endRow);
    }

    public static void setCellStyle(HSSFCell cell, String metricName, String scope, Double value) {
        // getting the configuration of the metric
        Map<String, MetricConfiguration> mm = MetricConfigurations.getMc().getMetricConfigurationsMap();

        MetricConfiguration mc = mm.get(metricName + " " + scope);
        if (mc != null) {
            if (value < mc.getMinimum() || mc.getMaximum() < value) {
                cell.setCellStyle(ReportGeneratorImpl.getRedBlueStyle());
            } else {
                cell.setCellStyle(ReportGeneratorImpl.getBlueStyle());
            }
        } else {
            cell.setCellStyle(ReportGeneratorImpl.getBlueStyle());
        }
    }
}
