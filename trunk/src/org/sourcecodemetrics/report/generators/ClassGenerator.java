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

import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.IMethod;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.sourcecodemetrics.view.options.limitations.MetricConfiguration;
import org.sourcecodemetrics.view.options.limitations.MetricConfigurations;

/**
 *
 * @author Krystian
 */
public class ClassGenerator {

    public static void generate(IClass c, HSSFSheet worksheet, HSSFWorkbook workbook) {
        int startRow = worksheet.getPhysicalNumberOfRows();

        HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

        // clearing the package level
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());

        // writing dashes to the invalid metrics
        for (int i = 2; i < ReportGeneratorImpl.headings.size(); i++) {
            HSSFCell ce = row.createCell(i);
            ce.setCellStyle(ReportGeneratorImpl.getDottedYellowStyle());
        }

        // writing the name
        cell = row.createCell(1);
        cell.setCellStyle(ReportGeneratorImpl.getYellowStyle());
        cell.setCellValue(c.getName());

        // writing the metrics
        int colIdx = ReportGeneratorImpl.headings.indexOf("C");
        cell = row.createCell(colIdx);
        cell.setCellStyle(c.getC() ? ReportGeneratorImpl.getYellowStyle() : ReportGeneratorImpl.getRedYellowStyle());
        cell.setCellValue(c.getC() ? "yes" : "no");

        colIdx = ReportGeneratorImpl.headings.indexOf("LCC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCC", "class", (double) c.getLCC());
        cell.setCellValue(c.getLCC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM1");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCOM1", "class", (double) c.getLCOM1());
        cell.setCellValue(c.getLCOM1());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM2");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCOM2", "class", (double) c.getLCOM2());
        cell.setCellValue(c.getLCOM2());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM3");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCOM3", "class", (double) c.getLCOM3());
        cell.setCellValue(c.getLCOM3());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM4");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCOM4", "class", (double) c.getLCOM4());
        cell.setCellValue(c.getLCOM4());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCM5");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LCOM5", "class", (double) c.getLCOM5());
        cell.setCellValue(c.getLCOM5());

        colIdx = ReportGeneratorImpl.headings.indexOf("NAK");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NAK", "class", (double) c.getNAK());
        cell.setCellValue(c.getNAK());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOF");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOF", "class", (double) c.getNOF());
        cell.setCellValue(c.getNOF());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOM", "class", (double) c.getNOM());
        cell.setCellValue(c.getNOM());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOSF");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOSF", "class", (double) c.getNOSF());
        cell.setCellValue(c.getNOSF());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOSM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOSM", "class", (double) c.getNOSM());
        cell.setCellValue(c.getNOSM());

        colIdx = ReportGeneratorImpl.headings.indexOf("NTM");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NTM", "class", (double) c.getNTM());
        cell.setCellValue(c.getNTM());

        colIdx = ReportGeneratorImpl.headings.indexOf("TCC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "TCC", "class", (double) c.getTCC());
        cell.setCellValue(c.getTCC());

        colIdx = ReportGeneratorImpl.headings.indexOf("WMC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "WMC", "class", (double) c.getWMC());
        cell.setCellValue(c.getWMC());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOC", "class", (double) c.getNOC());
        cell.setCellValue(c.getNOC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOC", "class", (double) c.getLOC());
        cell.setCellValue(c.getLOC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCm");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCm", "class", (double) c.getLOCm());
        cell.setCellValue(c.getLOCm());

        // method metrics
        colIdx = ReportGeneratorImpl.headings.indexOf("VG");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "VG", "method", (double) c.getMethodVGAvg());
        cell.setCellValue(c.getMethodVGAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NBD");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NBD", "method", (double) c.getMethodNBDAvg());
        cell.setCellValue(c.getMethodNBDAvg());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOP");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOP", "method", (double) c.getMethodNOPSum());
        cell.setCellValue(c.getMethodNOPSum());

        if (c.getMethods().size() > 0) {
            worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        }

        // generate all of the methods
        for (Iterator<IMethod> it = c.getMethods().iterator(); it.hasNext();) {
            IMethod im = it.next();
            MethodGenerator.generate(im, worksheet, workbook);

            if (it.hasNext()) {
                worksheet.createRow(worksheet.getPhysicalNumberOfRows());
            }
        }
        ReportGeneratorImpl.appendEmptyRow(worksheet);

        int endRow = startRow + c.getMethods().size();
        worksheet.groupRow(startRow, endRow);
    }

    private static void setCellStyle(HSSFCell cell, String metricName, String scope, Double value) {
        // getting the configuration of the metric
        Map<String, MetricConfiguration> mm = MetricConfigurations.getMc().getMetricConfigurationsMap();

        MetricConfiguration mc = mm.get(metricName + " " + scope);
        if (mc != null) {
            if (value < mc.getMinimum() || mc.getMaximum() < value) {
                cell.setCellStyle(ReportGeneratorImpl.getRedYellowStyle());
            } else {
                cell.setCellStyle(ReportGeneratorImpl.getYellowStyle());
            }
        } else {
            cell.setCellStyle(ReportGeneratorImpl.getYellowStyle());
        }
    }
}
