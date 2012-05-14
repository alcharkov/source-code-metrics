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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.sourcecodemetrics.measurer.api.IProject;

/**
 *
 * @author Krystian Warzocha
 */
public class StatisticsGenerator {

    public static void generateStatistics(IProject project, HSSFWorkbook workbook) {

        if (blueStyle == null) {
            blueStyle = workbook.createCellStyle();
            blueStyle.setFillForegroundColor(HSSFColor.AQUA.index);
            blueStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        }

        HSSFSheet worksheet = workbook.createSheet("Statistics");

        // creation of the header row
        HSSFRow headerRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        for (int i = 0; i < headings.size(); i++) {
            String title = headings.get(i);
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(blueStyle);
        }

        // writing out all of the values of the metrics
        HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("A");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Abstractness");
        cell = row.createCell(3);
        if (project.getPackageAMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageAMin());
        }
        cell = row.createCell(4);
        if (project.getPackageAMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageAMax());
        }
        cell = row.createCell(5);
        if (project.getPackageAAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageAAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("AC");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Afferent Coupling");
        cell = row.createCell(3);
        if (project.getPackageACMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageACMin());
        }
        cell = row.createCell(4);
        if (project.getPackageACMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageACMax());
        }
        cell = row.createCell(5);
        if (project.getPackageACAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageACAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("C");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Coverage");
        cell = row.createCell(3);
        if (project.getPackageCoverageMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageCoverageMin());
        }
        cell = row.createCell(4);
        if (project.getPackageCoverageMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageCoverageMax());
        }
        cell = row.createCell(5);
        if (project.getPackageCoverageAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageCoverageAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("D");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Distance");
        cell = row.createCell(3);
        if (project.getPackageDMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageDMin());
        }
        cell = row.createCell(4);
        if (project.getPackageDMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageDMax());
        }
        cell = row.createCell(5);
        if (project.getPackageDAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageDAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("EC");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Efferent Coupling");
        cell = row.createCell(3);
        if (project.getPackageECMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageECMin());
        }
        cell = row.createCell(4);
        if (project.getPackageECMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageECMax());
        }
        cell = row.createCell(5);
        if (project.getPackageECAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageECAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("I");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Instability");
        cell = row.createCell(3);
        if (project.getPackageIMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageIMin());
        }
        cell = row.createCell(4);
        if (project.getPackageIMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageIMax());
        }
        cell = row.createCell(5);
        if (project.getPackageIAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageIAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOC");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Code");
        cell = row.createCell(3);
        if (project.getPackageLocMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageLocMin());
        }
        cell = row.createCell(4);
        if (project.getPackageLocMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageLocMax());
        }
        cell = row.createCell(6);
        if (project.getLOC() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getLOC());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOCm");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Comments");
        cell = row.createCell(3);
        if (project.getPackageLocmMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageLocmMin());
        }
        cell = row.createCell(4);
        if (project.getPackageLocmMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageLocmMax());
        }
        cell = row.createCell(6);
        if (project.getLOCm() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getLOCm());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NCP");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Classes in Package");
        cell = row.createCell(3);
        if (project.getPackageNCPMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNCPMin());
        }
        cell = row.createCell(4);
        if (project.getPackageNCPMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNCPMax());
        }
        cell = row.createCell(6);
        if (project.getPackageNCPSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNCPSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NIP");
        cell = row.createCell(1);
        cell.setCellValue("package");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Interfaces in Package");
        cell = row.createCell(3);
        if (project.getPackageNIPMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNIPMin());
        }
        cell = row.createCell(4);
        if (project.getPackageNIPMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNIPMax());
        }
        cell = row.createCell(6);
        if (project.getPackageNIPSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getPackageNIPSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCC");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Loose Class Coupling");
        cell = row.createCell(3);
        if (project.getClassLCCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCCMax());
        }
        cell = row.createCell(4);
        if (project.getClassLCCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCCMin());
        }
        cell = row.createCell(5);
        if (project.getClassLCCAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCCAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCOM1");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lack Of Cohesion in Methods 1");
        cell = row.createCell(3);
        if (project.getClassLCOM1Min() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM1Min());
        }
        cell = row.createCell(4);
        if (project.getClassLCOM1Max() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM1Max());
        }
        cell = row.createCell(5);
        if (project.getClassLCOM1Avg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM1Avg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCOM2");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lack Of Cohesion in Methods 2");
        cell = row.createCell(3);
        if (project.getClassLCOM2Min() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM2Min());
        }
        cell = row.createCell(4);
        if (project.getClassLCOM2Max() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM2Max());
        }
        cell = row.createCell(5);
        if (project.getClassLCOM2Avg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM2Avg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCOM3");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lack Of Cohesion in Methods 3");
        cell = row.createCell(3);
        if (project.getClassLCOM3Min() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM3Min());
        }
        cell = row.createCell(4);
        if (project.getClassLCOM3Max() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM3Max());
        }
        cell = row.createCell(5);
        if (project.getClassLCOM3Avg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM3Avg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCOM4");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lack Of Cohesion in Methods 4");
        cell = row.createCell(3);
        if (project.getClassLCOM4Min() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM4Min());
        }
        cell = row.createCell(4);
        if (project.getClassLCOM4Max() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM4Max());
        }
        cell = row.createCell(5);
        if (project.getClassLCOM4Avg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM4Avg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LCOM5");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lack Of Cohesion in Methods 5");
        cell = row.createCell(3);
        if (project.getClassLCOM5Min() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM5Min());
        }
        cell = row.createCell(4);
        if (project.getClassLCOM5Max() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM5Max());
        }
        cell = row.createCell(5);
        if (project.getClassLCOM5Avg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLCOM5Avg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOC");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Code");
        cell = row.createCell(3);
        if (project.getClassLOCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLOCMin());
        }
        cell = row.createCell(4);
        if (project.getClassLOCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLOCMax());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOCm");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Comments");
        cell = row.createCell(3);
        if (project.getClassLOCmMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLOCmMin());
        }
        cell = row.createCell(4);
        if (project.getClassLOCmMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassLOCmMax());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NAK");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number of Assertions per KLOC");
        cell = row.createCell(3);
        if (project.getClassNAKMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNAKMin());
        }
        cell = row.createCell(4);
        if (project.getClassNAKMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNAKMax());
        }
        cell = row.createCell(5);
        if (project.getClassNAKAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNAKAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOC");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Children");
        cell = row.createCell(3);
        if (project.getClassNOCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOCMin());
        }
        cell = row.createCell(4);
        if (project.getClassNOCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOCMax());
        }
        cell = row.createCell(6);
        if (project.getClassNOCSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOCSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOF");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Fields");
        cell = row.createCell(3);
        if (project.getClassNOFMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOFMin());
        }
        cell = row.createCell(4);
        if (project.getClassNOFMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOFMax());
        }
        cell = row.createCell(6);
        if (project.getClassNOFSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOFSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOM");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Methods");
        cell = row.createCell(3);
        if (project.getClassNOMMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOMMin());
        }
        cell = row.createCell(4);
        if (project.getClassNOMMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOMMax());
        }
        cell = row.createCell(6);
        if (project.getClassNOMSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOMSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOSF");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Static Fields");
        cell = row.createCell(3);
        if (project.getClassNOSFMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSFMin());
        }
        cell = row.createCell(4);
        if (project.getClassNOSFMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSFMax());
        }
        cell = row.createCell(6);
        if (project.getClassNOSFSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSFSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOSM");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Static Methods");
        cell = row.createCell(3);
        if (project.getClassNOSMMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSMMin());
        }
        cell = row.createCell(4);
        if (project.getClassNOSMMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSMMax());
        }
        cell = row.createCell(6);
        if (project.getClassNOSMSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNOSMSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NTM");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Number of Test Methods");
        cell = row.createCell(3);
        if (project.getClassNTMMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNTMMin());
        }
        cell = row.createCell(4);
        if (project.getClassNTMMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNTMMax());
        }
        cell = row.createCell(6);
        if (project.getClassNTMSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassNTMSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("TCC");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Tight Class Coupling");
        cell = row.createCell(3);
        if (project.getClassTCCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassTCCMin());
        }
        cell = row.createCell(4);
        if (project.getClassTCCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassTCCMax());
        }
        cell = row.createCell(5);
        if (project.getClassTCCAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassTCCAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("WMC");
        cell = row.createCell(1);
        cell.setCellValue("class");
        cell = row.createCell(2);
        cell.setCellValue("Weighted Method Count");
        cell = row.createCell(3);
        if (project.getClassWMCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassWMCMin());
        }
        cell = row.createCell(4);
        if (project.getClassWMCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassWMCMax());
        }
        cell = row.createCell(6);
        if (project.getClassWMCSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getClassWMCSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOC");
        cell = row.createCell(1);
        cell.setCellValue("method");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Code");
        cell = row.createCell(3);
        if (project.getMethodLOCMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodLOCMin());
        }
        cell = row.createCell(4);
        if (project.getMethodLOCMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodLOCMax());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("LOCm");
        cell = row.createCell(1);
        cell.setCellValue("method");
        cell = row.createCell(2);
        cell.setCellValue("Lines Of Comments");
        cell = row.createCell(3);
        if (project.getMethodLOCmMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodLOCmMin());
        }
        cell = row.createCell(4);
        if (project.getMethodLOCmMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodLOCmMax());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NBD");
        cell = row.createCell(1);
        cell.setCellValue("method");
        cell = row.createCell(2);
        cell.setCellValue("Nested Block Depth");
        cell = row.createCell(3);
        if (project.getMethodNBDMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNBDMin());
        }
        cell = row.createCell(4);
        if (project.getMethodNBDMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNBDMax());
        }
        cell = row.createCell(5);
        if (project.getMethodNBDAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNBDAvg());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("NOP");
        cell = row.createCell(1);
        cell.setCellValue("method");
        cell = row.createCell(2);
        cell.setCellValue("Number Of Parameters");
        cell = row.createCell(3);
        if (project.getMethodNOPMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNOPMin());
        }
        cell = row.createCell(4);
        if (project.getMethodNOPMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNOPMax());
        }
        cell = row.createCell(6);
        if (project.getMethodNOPSum() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodNOPSum());
        }

        row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
        cell = row.createCell(0);
        cell.setCellValue("VG");
        cell = row.createCell(1);
        cell.setCellValue("method");
        cell = row.createCell(2);
        cell.setCellValue("McGabe's Cyclomatic Complexity");
        cell = row.createCell(3);
        if (project.getMethodVGMin() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodVGMin());
        }
        cell = row.createCell(4);
        if (project.getMethodVGMax() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodVGMax());
        }
        cell = row.createCell(5);
        if (project.getMethodVGAvg() == null) {
            cell.setCellValue("-");
        } else {
            cell.setCellValue(project.getMethodVGAvg());
        }

        // adjusting first three columns
        try {
            for (int i = 0; i < 7; i++) {
                worksheet.autoSizeColumn(i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Exception when trying to adjust columns", e);
        }

        // setting constant width to other columns
        for (int i = 3; i < 7; i++) {
            worksheet.setColumnWidth(i, 2700);
        }
    }
    private static HSSFCellStyle blueStyle = null;
    public static final List<String> headings = new ArrayList<String>(Arrays.asList(
            "Symbol",
            "Level",
            "Name",
            "Minimum",
            "Maximum",
            "Average",
            "Total"));
    private static final Logger logger = Logger.getLogger(StatisticsGenerator.class.getName());
}
