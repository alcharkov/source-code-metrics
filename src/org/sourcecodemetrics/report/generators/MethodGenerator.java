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
import org.sourcecodemetrics.measurer.api.IMethod;
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
public class MethodGenerator {

    public static void generate(IMethod m, HSSFSheet worksheet, HSSFWorkbook workbook) {
        HSSFRow row = worksheet.getRow(worksheet.getPhysicalNumberOfRows() - 1);

        // clearing the package level
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());

        // clearing the class level
        cell = row.createCell(1);
        cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());

        // writing dashes to the invalid metrics
        for (int i = 3; i < ReportGeneratorImpl.headings.size(); i++) {
            HSSFCell ce = row.createCell(i);
            ce.setCellStyle(ReportGeneratorImpl.getDottedStyle());
        }

        // name of the method
        cell = row.createCell(2);
        cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());
        cell.setCellValue(m.getName());

        // writing the metrics
        int colIdx = ReportGeneratorImpl.headings.indexOf("VG");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "VG", "method", (double) m.getVG());
        cell.setCellValue(m.getVG());

        colIdx = ReportGeneratorImpl.headings.indexOf("NBD");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NBD", "method", (double) m.getNBD());
        cell.setCellValue(m.getNBD());

        colIdx = ReportGeneratorImpl.headings.indexOf("NOP");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "NOP", "method", (double) m.getNOP());
        cell.setCellValue(m.getNOP());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOC");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOC", "method", (double) m.getLOC());
        cell.setCellValue(m.getLOC());

        colIdx = ReportGeneratorImpl.headings.indexOf("LOCm");
        cell = row.createCell(colIdx);
        setCellStyle(cell, "LOCm", "method", (double) m.getLOCm());
        cell.setCellValue(m.getLOCm());
    }

    private static void setCellStyle(HSSFCell cell, String metricName, String scope, Double value) {
        // getting the configuration of the metric
        Map<String, MetricConfiguration> mm = MetricConfigurations.getMc().getMetricConfigurationsMap();

        MetricConfiguration mc = mm.get(metricName + " " + scope);
        if (mc != null) {
            if (value < mc.getMinimum() || mc.getMaximum() < value) {
                cell.setCellStyle(ReportGeneratorImpl.getRedWhiteStyle());
            } else {
                cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());
            }
        } else {
            cell.setCellStyle(ReportGeneratorImpl.getWhiteStyle());
        }
    }
}
