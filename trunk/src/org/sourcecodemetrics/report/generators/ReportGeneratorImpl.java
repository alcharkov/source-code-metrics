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

import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.openide.util.Exceptions;
import org.sourcecodemetrics.report.api.IReportGenerator;

/**
 *
 * @author Krystian
 */
public class ReportGeneratorImpl implements IReportGenerator {

    @Override
    public void generateReport(IProject project, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Source Code Metrics");

            // creation of the header row
            whiteStyle = workbook.createCellStyle();
            whiteStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            whiteStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
            whiteStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            dottedBlueStyle = workbook.createCellStyle();
            dottedBlueStyle.setFillForegroundColor(HSSFColor.BLACK.index);
            dottedBlueStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
            dottedBlueStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);

            blueStyle = workbook.createCellStyle();
            blueStyle.setFillForegroundColor(HSSFColor.AQUA.index);
            blueStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.RED.index);
            redBlueStyle = workbook.createCellStyle();
            redBlueStyle.setFillForegroundColor(HSSFColor.AQUA.index);
            redBlueStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            redBlueStyle.setFont(font);

            dottedYellowStyle = workbook.createCellStyle();
            dottedYellowStyle.setFillForegroundColor(HSSFColor.BLACK.index);
            dottedYellowStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
            dottedYellowStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);

            yellowStyle = workbook.createCellStyle();
            yellowStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            yellowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            redYellowStyle = workbook.createCellStyle();
            redYellowStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            redYellowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            redYellowStyle.setFont(font);

            dottedStyle = workbook.createCellStyle();
            dottedStyle.setFillForegroundColor(HSSFColor.BLACK.index);
            dottedStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
            dottedStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
            
            redWhiteStyle = workbook.createCellStyle();
            redWhiteStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            redWhiteStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
            redWhiteStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            redWhiteStyle.setFont(font);

            HSSFRow headerRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
            for (int i = 0; i < headings.size(); i++) {
                String title = headings.get(i);
                HSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(title);
                cell.setCellStyle(whiteStyle);
            }

            for (IPackage ip : project.getPackages()) {
                if (!ip.isTests() && !ip.getSourceFiles().isEmpty()) {
                    PackageGenerator.generate(ip, worksheet, workbook);
                }
            }

            // adjusting first three columns
            try {
                for (int i = 0; i < 3; i++) {
                    worksheet.autoSizeColumn(i);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.SEVERE, "Exception when trying to adjust columns", e);
            }

            // setting constant width to other columns
            for (int i = 3; i < headings.size(); i++) {
                worksheet.setColumnWidth(i, 2000);
            }

            // writing out the statistics to the report
            StatisticsGenerator.generateStatistics(project, workbook);

            // writing the raw data to the report
            RawDataGenerator.generateRawData(project, workbook);

            worksheet.createFreezePane(3, 1);

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static void appendEmptyRow(HSSFSheet worksheet) {
        HSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

        for (int i = 0; i < ReportGeneratorImpl.headings.size(); i++) {
            HSSFCell c = row.createCell(i);
            c.setCellStyle(whiteStyle);
        }
    }

    public static HSSFCellStyle getWhiteStyle() {
        return whiteStyle;
    }

    public static HSSFCellStyle getBlueStyle() {
        return blueStyle;
    }

    public static HSSFCellStyle getDottedBlueStyle() {
        return dottedBlueStyle;
    }

    public static HSSFCellStyle getRedBlueStyle() {
        return redBlueStyle;
    }

    public static HSSFCellStyle getDottedYellowStyle() {
        return dottedYellowStyle;
    }

    public static HSSFCellStyle getYellowStyle() {
        return yellowStyle;
    }

    public static HSSFCellStyle getRedYellowStyle() {
        return redYellowStyle;
    }

    public static HSSFCellStyle getDottedStyle() {
        return dottedStyle;
    }

    public static HSSFCellStyle getRedWhiteStyle() {
        return redWhiteStyle;
    }
    public static final List<String> headings = new ArrayList<String>(Arrays.asList(
            "Package",
            "Class",
            "Method",
            "A",
            "AC",
            "D",
            "EC",
            "I",
            "NCP",
            "NIP",
            "C",
            "LCC",
            "LOCM1",
            "LOCM2",
            "LOCM3",
            "LOCM4",
            "LOCM5",
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
            "LOCm",
            "NBD",
            "NOP",
            "VG"));
    private static final Logger logger = Logger.getLogger(ReportGeneratorImpl.class.getName());
    private static HSSFCellStyle whiteStyle;
    private static HSSFCellStyle dottedBlueStyle = null;
    private static HSSFCellStyle blueStyle = null;
    private static HSSFCellStyle redBlueStyle = null;
    private static HSSFCellStyle yellowStyle = null;
    private static HSSFCellStyle dottedYellowStyle = null;
    private static HSSFCellStyle redYellowStyle = null;
    private static HSSFCellStyle dottedStyle = null;
    private static HSSFCellStyle redWhiteStyle = null;
}
