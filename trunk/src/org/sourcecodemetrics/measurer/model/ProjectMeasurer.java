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

package org.sourcecodemetrics.measurer.model;

import org.sourcecodemetrics.measurer.model.packages.PackageImpl;

/**
 *
 * @author Krystian
 */
public class ProjectMeasurer {

    public ProjectMeasurer(ProjectImpl p) {
        this.p = p;
    }

    void measure() {
        // counting the number of LOC and LOCm
        p.loc = 0;
        p.locm = 0;
        p.pAAvg = 0.0;
        p.pAcAvg = 0;
        p.pCAvg = 0.0;
        p.pDAvg = 0.0;
        p.pEcAvg = 0;
        p.pIAvg = 0.0;
        p.pNcpSum = 0;
        p.pNipSum = 0;
        p.cLccAvg = 0.0;
        p.cLcom1Avg = 0;
        p.cLcom2Avg = 0;
        p.cLcom3Avg = 0;
        p.cLcom4Avg = 0;
        p.cLcom5Avg = 0.0;
        p.cNakAvg = 0.0;
        p.cNocSum = 0;
        p.cNofSum = 0;
        p.cNomSum = 0;
        p.cNosfSum = 0;
        p.cNosmSum = 0;
        p.cNtmSum = 0;
        p.cTccAvg = 0.0;
        p.cWmcSum = 0;
        p.mNbdAvg = 0;
        p.mNopSum = 0;
        p.mVgAvg = 0;
        for (PackageImpl pi : p.packages.values()) {
            if (!pi.isTestPackage() && !pi.isTests() && !pi.getClasses().isEmpty()) {
                p.loc += pi.getLOC();
                p.locm += pi.getLOCm();
                p.pAAvg += pi.getA();
                p.pAcAvg += pi.getAC();
                p.pCAvg += pi.getC();
                p.pDAvg += pi.getD();
                p.pEcAvg += pi.getEC();
                p.pIAvg += pi.getI();
                p.pNcpSum += pi.getNCP();
                p.pNipSum += pi.getNIP();

                p.cLccAvg += pi.getClassLCCAvg();
                p.cLcom1Avg += pi.getClassLCOM1Avg();
                p.cLcom2Avg += pi.getClassLCOM2Avg();
                p.cLcom3Avg += pi.getClassLCOM3Avg();
                p.cLcom4Avg += pi.getClassLCOM4Avg();
                p.cLcom5Avg += pi.getClassLCOM5Avg();
                p.cNakAvg += pi.getClassNAKAvg();
                p.cNocSum += pi.getClassNOCSum();
                p.cNofSum += pi.getClassNOFSum();
                p.cNomSum += pi.getClassNOMSum();
                p.cNosfSum += pi.getClassNOSFSum();
                p.cNosmSum += pi.getClassNOMSum();
                p.cNtmSum += pi.getClassNTMSum();
                p.cTccAvg += pi.getClassTCCAvg();
                p.cWmcSum += pi.getClassWMCSum();

                p.mNbdAvg += pi.getMethodNBDAvg();
                p.mNopSum += pi.getMethodNOPSum();
                p.mVgAvg += pi.getMethodVGAvg();
            }
        }

        if (p.numberOfPackages > 0) {
            p.pAAvg /= p.numberOfPackages;
            p.pAcAvg /= p.numberOfPackages;
            p.pCAvg /= p.numberOfPackages;
            p.pDAvg /= p.numberOfPackages;
            p.pEcAvg /= p.numberOfPackages;
            p.pIAvg /= p.numberOfPackages;
            p.pNcpSum /= p.numberOfPackages;
            p.pNipSum /= p.numberOfPackages;

            p.cLccAvg /= p.numberOfPackages;
            p.cLcom1Avg /= p.numberOfPackages;
            p.cLcom2Avg /= p.numberOfPackages;
            p.cLcom3Avg /= p.numberOfPackages;
            p.cLcom4Avg /= p.numberOfPackages;
            p.cLcom5Avg /= p.numberOfPackages;
            p.cNakAvg /= p.numberOfPackages;
            p.cTccAvg /= p.numberOfPackages;

            p.mNbdAvg /= p.numberOfPackages;
            p.mVgAvg /= p.numberOfPackages;
        }

        // statistical calculations
        if (!p.packages.isEmpty()) {
            PackageImpl p0 = p.packages.values().iterator().next();
            p.mLocmMax = p0.getMethodLOCmMax();
            p.mLocmMin = p0.getMethodLOCmMin();
            p.mLocMax = p0.getMethodLOCMax();
            p.mLocMin = p0.getMethodLOCMin();
            p.mNopMax = p0.getMethodNOPMax();
            p.mNopMin = p0.getMethodNOPMin();
            p.mNbdMax = p0.getMethodNBDMax();
            p.mNbdMin = p0.getMethodNBDMin();
            p.mVgMax = p0.getMethodVGMax();
            p.mVgMin = p0.getMethodVGMin();
            p.cNocMax = p0.getClassNOCMax();
            p.cNocMin = p0.getClassNOCMin();
            p.cNakMax = p0.getClassNAKMax();
            p.cNakMin = p0.getClassNAKMin();
            p.cNtmMax = p0.getClassNTMMax();
            p.cNtmMin = p0.getClassNTMMin();
            p.cLocmMax = p0.getClassLOCmMax();
            p.cLocmMin = p0.getClassLOCmMin();
            p.cLocMax = p0.getClassLOCMax();
            p.cLocMin = p0.getClassLOCMin();
            p.cWmcMax = p0.getClassWMCMax();
            p.cWmcMin = p0.getClassWMCMin();
            p.cLccMax = p0.getClassLCCMax();
            p.cLccMin = p0.getClassLCCMin();
            p.cTccMax = p0.getClassTCCMax();
            p.cTccMin = p0.getClassTCCMin();
            p.cLcom5Max = p0.getClassLCOM5Max();
            p.cLcom5Min = p0.getClassLCOM5Min();
            p.cLcom4Max = p0.getClassLCOM4Max();
            p.cLcom4Min = p0.getClassLCOM4Min();
            p.cLcom3Max = p0.getClassLCOM3Max();
            p.cLcom3Min = p0.getClassLCOM3Min();
            p.cLcom2Max = p0.getClassLCOM2Max();
            p.cLcom2Min = p0.getClassLCOM2Min();
            p.cLcom1Max = p0.getClassLCOM1Max();
            p.cLcom1Min = p0.getClassLCOM1Min();
            p.cNosfMax = p0.getClassNOSFMax();
            p.cNosfMin = p0.getClassNOSFMin();
            p.cNofMax = p0.getClassNOFMax();
            p.cNofMin = p0.getClassNOFMin();
            p.cNosmMax = p0.getClassNOSMMax();
            p.cNosmMin = p0.getClassNOSMMin();
            p.cNomMax = p0.getClassNOMMax();
            p.cNomMin = p0.getClassNOMMin();
            p.pAMax = p0.getA();
            p.pAMin = p0.getA();
            p.pAcMax = p0.getAC();
            p.pAcMin = p0.getAC();
            p.pCMax = p0.getC();
            p.pCMin = p0.getC();
            p.pDMax = p0.getD();
            p.pDMin = p0.getD();
            p.pEcMax = p0.getEC();
            p.pEcMin = p0.getEC();
            p.pIMax = p0.getI();
            p.pIMin = p0.getI();
            p.pNcpMax = p0.getNCP();
            p.pNcpMin = p0.getNCP();
            p.pNipMax = p0.getNIP();
            p.pNipMin = p0.getNIP();
            p.pLocMax = p0.getLOC();
            p.pLocMin = p0.getLOC();
            p.pLocmMax = p0.getLOCm();
            p.pLocmMin = p0.getLOCm();

            for (PackageImpl pi : p.packages.values()) {

                if (!pi.isTestPackage() && !pi.isTests() && !pi.getClasses().isEmpty()) {

                    if (pi.getMethodLOCmMax() != null && pi.getMethodLOCmMin() != null) {
                        if (p.mLocmMax == null) {
                            p.mLocmMax = pi.getMethodLOCmMax();
                        }
                        if (p.mLocmMin == null) {
                            p.mLocmMin = pi.getMethodLOCmMin();
                        }
                        if (pi.getMethodLOCmMax() > p.mLocmMax) {
                            p.mLocmMax = pi.getMethodLOCmMax();
                        } else if (pi.getMethodLOCmMin() < p.mLocmMin) {
                            p.mLocmMin = pi.getMethodLOCmMin();
                        }
                    }

                    if (pi.getMethodLOCMax() != null && pi.getMethodLOCMin() != null && pi.getMethodLOCmMin() != null && p.mLocMin != null) {
                        if (p.mLocMax == null) {
                            p.mLocMax = pi.getMethodLOCMax();
                        }
                        if (p.mLocMin == null) {
                            p.mLocMin = pi.getMethodLOCMin();
                        }
                        if (pi.getMethodLOCMax() > p.mLocMax) {
                            p.mLocMax = pi.getMethodLOCMax();
                        } else if (pi.getMethodLOCmMin() < p.mLocMin) {
                            p.mLocMin = pi.getMethodLOCMin();
                        }
                    }

                    if (pi.getMethodNOPMax() != null && pi.getMethodNOPMin() != null) {
                        if (p.mNopMax == null) {
                            p.mNopMax = pi.getMethodNOPMax();
                        }
                        if (p.mNopMin == null) {
                            p.mNopMin = pi.getMethodNOPMin();
                        }
                        if (pi.getMethodNOPMax() > p.mNopMax) {
                            p.mNopMax = pi.getMethodNOPMax();
                        } else if (pi.getMethodNOPMin() < p.mNopMin) {
                            p.mNopMin = pi.getMethodNOPMin();
                        }
                    }

                    if (pi.getMethodNBDMax() != null && pi.getMethodNBDMin() != null) {
                        if (p.mNbdMax == null) {
                            p.mNbdMax = pi.getMethodNBDMax();
                        }
                        if (p.mNbdMin == null) {
                            p.mNbdMin = pi.getMethodNBDMin();
                        }
                        if (pi.getMethodNBDMax() > p.mNbdMax) {
                            p.mNbdMax = pi.getMethodNBDMax();
                        } else if (pi.getMethodNBDMin() < p.mNbdMin) {
                            p.mNbdMin = pi.getMethodNBDMin();
                        }
                    }

                    if (pi.getMethodVGMax() != null && pi.getMethodVGMin() != null) {
                        if (p.mVgMax == null) {
                            p.mVgMax = pi.getMethodVGMax();
                        }
                        if (p.mVgMin == null) {
                            p.mVgMin = pi.getMethodVGMin();
                        }
                        if (pi.getMethodVGMax() > p.mVgMax) {
                            p.mVgMax = pi.getMethodVGMax();
                        } else if (pi.getMethodVGMin() < p.mVgMin) {
                            p.mVgMin = pi.getMethodVGMin();
                        }
                    }

                    if (pi.getClassNOCMax() != null && pi.getClassNOCMin() != null) {
                        if (p.cNocMax == null) {
                            p.cNocMax = pi.getClassNOCMax();
                        }
                        if (p.cNocMin == null) {
                            p.cNocMin = pi.getClassNOCMin();
                        }
                        if (pi.getClassNOCMax() > p.cNocMax) {
                            p.cNocMax = pi.getClassNOCMax();
                        } else if (pi.getClassNOCMin() < p.cNocMin) {
                            p.cNocMin = pi.getClassNOCMin();
                        }
                    }

                    if (pi.getClassNAKMax() != null && pi.getClassNAKMin() != null) {
                        if (p.cNakMax == null) {
                            p.cNakMax = pi.getClassNAKMax();
                        }
                        if (p.cNakMin == null) {
                            p.cNakMin = pi.getClassNAKMin();
                        }
                        if (pi.getClassNAKMax() > p.cNakMax) {
                            p.cNakMax = pi.getClassNAKMax();
                        } else if (pi.getClassNAKMin() < p.cNakMin) {
                            p.cNakMin = pi.getClassNAKMin();
                        }
                    }

                    if (pi.getClassNTMMax() != null && pi.getClassNTMMin() != null) {
                        if (p.cNtmMax == null) {
                            p.cNtmMax = pi.getClassNTMMax();
                        }
                        if (p.cNtmMin == null) {
                            p.cNtmMin = pi.getClassNTMMin();
                        }
                        if (pi.getClassNTMMax() > p.cNtmMax) {
                            p.cNtmMax = pi.getClassNTMMax();
                        } else if (pi.getClassNTMMin() < p.cNtmMin) {
                            p.cNtmMin = pi.getClassNTMMin();
                        }
                    }

                    if (pi.getClassLOCmMax() != null && pi.getClassLOCmMin() != null) {
                        if (p.cLocmMax == null) {
                            p.cLocmMax = pi.getClassLOCmMax();
                        }
                        if (p.cLocmMin == null) {
                            p.cLocmMin = pi.getClassLOCmMin();
                        }
                        if (pi.getClassLOCmMax() > p.cLocmMax) {
                            p.cLocmMax = pi.getClassLOCmMax();
                        } else if (pi.getClassLOCmMin() < p.cLocmMin) {
                            p.cLocmMin = pi.getClassLOCmMin();
                        }
                    }

                    if (pi.getClassLOCMax() != null && pi.getClassLOCMin() != null) {
                        if (p.cLocMax == null) {
                            p.cLocMax = pi.getClassLOCMax();
                        }
                        if (p.cLocMin == null) {
                            p.cLocMin = pi.getClassLOCMin();
                        }
                        if (pi.getClassLOCMax() > p.cLocMax) {
                            p.cLocMax = pi.getClassLOCMax();
                        } else if (pi.getClassLOCMin() < p.cLocMin) {
                            p.cLocMin = pi.getClassLOCMin();
                        }
                    }

                    if (pi.getClassWMCMax() != null && pi.getClassWMCMin() != null) {
                        if (p.cWmcMax == null) {
                            p.cWmcMax = pi.getClassWMCMax();
                        }
                        if (p.cWmcMin == null) {
                            p.cWmcMin = pi.getClassWMCMin();
                        }
                        if (pi.getClassWMCMax() > p.cWmcMax) {
                            p.cWmcMax = pi.getClassWMCMax();
                        } else if (pi.getClassWMCMin() < p.cWmcMin) {
                            p.cWmcMin = pi.getClassWMCMin();
                        }
                    }

                    if (pi.getClassLCCMax() != null && pi.getClassLCCMin() != null) {
                        if (p.cLccMax == null) {
                            p.cLccMax = pi.getClassLCCMax();
                        }
                        if (p.cLccMin == null) {
                            p.cLccMin = pi.getClassLCCMin();
                        }
                        if (pi.getClassLCCMax() > p.cLccMax) {
                            p.cLccMax = pi.getClassLCCMax();
                        } else if (pi.getClassLCCMin() < p.cLccMin) {
                            p.cLccMin = pi.getClassLCCMin();
                        }
                    }

                    if (pi.getClassTCCMax() != null && pi.getClassTCCMin() != null) {
                        if (p.cTccMax == null) {
                            p.cTccMax = pi.getClassTCCMax();
                        }
                        if (p.cTccMin == null) {
                            p.cTccMin = pi.getClassTCCMin();
                        }
                        if (pi.getClassTCCMax() > p.cTccMax) {
                            p.cTccMax = pi.getClassTCCMax();
                        } else if (pi.getClassTCCMin() < p.cTccMin) {
                            p.cTccMin = pi.getClassTCCMin();
                        }
                    }

                    if (pi.getClassLCOM1Max() != null && pi.getClassLCOM1Min() != null) {
                        if (p.cLcom1Max == null) {
                            p.cLcom1Max = pi.getClassLCOM1Max();
                        }
                        if (p.cLcom1Min == null) {
                            p.cLcom1Min = pi.getClassLCOM1Min();
                        }
                        if (pi.getClassLCOM1Max() > p.cLcom1Max) {
                            p.cLcom1Max = pi.getClassLCOM1Max();
                        } else if (pi.getClassLCOM1Min() < p.cLcom1Max) {
                            p.cLcom1Max = pi.getClassLCOM1Min();
                        }
                    }

                    if (pi.getClassLCOM2Max() != null && pi.getClassLCOM2Min() != null) {
                        if (p.cLcom2Max == null) {
                            p.cLcom2Max = pi.getClassLCOM2Max();
                        }
                        if (p.cLcom2Min == null) {
                            p.cLcom2Min = pi.getClassLCOM2Min();
                        }
                        if (pi.getClassLCOM2Max() > p.cLcom2Max) {
                            p.cLcom2Max = pi.getClassLCOM2Max();
                        } else if (pi.getClassLCOM2Min() < p.cLcom2Max) {
                            p.cLcom2Max = pi.getClassLCOM2Min();
                        }
                    }

                    if (pi.getClassLCOM3Max() != null && pi.getClassLCOM3Min() != null) {
                        if (p.cLcom3Max == null) {
                            p.cLcom3Max = pi.getClassLCOM3Max();
                        }
                        if (p.cLcom3Min == null) {
                            p.cLcom3Min = pi.getClassLCOM3Min();
                        }
                        if (pi.getClassLCOM3Max() > p.cLcom3Max) {
                            p.cLcom3Max = pi.getClassLCOM3Max();
                        } else if (pi.getClassLCOM3Min() < p.cLcom3Max) {
                            p.cLcom3Max = pi.getClassLCOM3Min();
                        }
                    }

                    if (pi.getClassLCOM4Max() != null && pi.getClassLCOM4Min() != null) {
                        if (p.cLcom4Max == null) {
                            p.cLcom4Max = pi.getClassLCOM4Max();
                        }
                        if (p.cLcom4Min == null) {
                            p.cLcom4Min = pi.getClassLCOM4Min();
                        }
                        if (pi.getClassLCOM4Max() > p.cLcom4Max) {
                            p.cLcom4Max = pi.getClassLCOM4Max();
                        } else if (pi.getClassLCOM4Min() < p.cLcom4Max) {
                            p.cLcom4Max = pi.getClassLCOM4Min();
                        }
                    }

                    if (pi.getClassLCOM5Max() != null && pi.getClassLCOM5Min() != null) {
                        if (p.cLcom5Max == null) {
                            p.cLcom5Max = pi.getClassLCOM5Max();
                        }
                        if (p.cLcom5Min == null) {
                            p.cLcom5Min = pi.getClassLCOM5Min();
                        }
                        if (pi.getClassLCOM5Max() > p.cLcom5Max) {
                            p.cLcom5Max = pi.getClassLCOM5Max();
                        } else if (pi.getClassLCOM5Min() < p.cLcom5Max) {
                            p.cLcom5Max = pi.getClassLCOM5Min();
                        }
                    }

                    if (pi.getClassNOSFMax() != null && pi.getClassNOSFMin() != null) {
                        if (p.cNosfMax == null) {
                            p.cNosfMax = pi.getClassNOSFMax();
                        }
                        if (p.cNosfMin == null) {
                            p.cNosfMin = pi.getClassNOSFMin();
                        }
                        if (pi.getClassNOSFMax() > p.cNosfMax) {
                            p.cNosfMax = pi.getClassNOSFMax();
                        } else if (pi.getClassNOSFMin() < p.cNosfMax) {
                            p.cNosfMax = pi.getClassNOSFMin();
                        }
                    }

                    if (pi.getClassNOFMax() != null && pi.getClassNOFMin() != null) {
                        if (p.cNofMax == null) {
                            p.cNofMax = pi.getClassNOFMax();
                        }
                        if (p.cNofMin == null) {
                            p.cNofMin = pi.getClassNOFMin();
                        }
                        if (pi.getClassNOFMax() > p.cNofMax) {
                            p.cNofMax = pi.getClassNOFMax();
                        } else if (pi.getClassNOFMin() < p.cNofMax) {
                            p.cNofMax = pi.getClassNOFMin();
                        }
                    }

                    if (pi.getClassNOSMMax() != null && pi.getClassNOSMMax() != null) {
                        if (p.cNosmMax == null) {
                            p.cNosmMax = pi.getClassNOSMMax();
                        }
                        if (p.cNosmMin == null) {
                            p.cNosmMin = pi.getClassNOSMMin();
                        }
                        if (pi.getClassNOSMMax() > p.cNosmMax) {
                            p.cNosmMax = pi.getClassNOSMMax();
                        } else if (pi.getClassNOSMMin() < p.cNosmMax) {
                            p.cNosmMax = pi.getClassNOSMMin();
                        }
                    }

                    if (pi.getClassNOMMax() != null && pi.getClassNOMMax() != null) {
                        if (p.cNomMax == null) {
                            p.cNomMax = pi.getClassNOMMax();
                        }
                        if (p.cNomMin == null) {
                            p.cNomMin = pi.getClassNOMMin();
                        }
                        if (pi.getClassNOMMax() > p.cNomMax) {
                            p.cNomMax = pi.getClassNOMMax();
                        } else if (pi.getClassNOMMin() < p.cNomMax) {
                            p.cNomMax = pi.getClassNOMMin();
                        }
                    }

                    if (pi.getA() != null) {
                        if (p.pAMax == null) {
                            p.pAMax = pi.getA();
                        }
                        if (p.pAMin == null) {
                            p.pAMin = pi.getA();
                        }
                        if (pi.getA() > p.pAMax) {
                            p.pAMax = pi.getA();
                        } else if (pi.getA() < p.pAMin) {
                            p.pAMin = pi.getA();
                        }
                    }

                    if (pi.getAC() != null) {
                        if (p.pAcMax == null) {
                            p.pAcMax = pi.getAC();
                        }
                        if (p.pAcMin == null) {
                            p.pAcMin = pi.getAC();
                        }
                        if (pi.getAC() > p.pAcMax) {
                            p.pAcMax = pi.getAC();
                        } else if (pi.getAC() < p.pAcMin) {
                            p.pAcMin = pi.getAC();
                        }
                    }

                    if (pi.getC() != null) {
                        if (p.pCMax == null) {
                            p.pCMax = pi.getC();
                        }
                        if (p.pCMin == null) {
                            p.pCMin = pi.getC();
                        }
                        if (pi.getC() > p.pCMax) {
                            p.pCMax = pi.getC();
                        } else if (pi.getC() < p.pCMin) {
                            p.pCMin = pi.getC();
                        }
                    }

                    if (pi.getD() != null) {
                        if (p.pDMax == null) {
                            p.pDMax = pi.getD();
                        }
                        if (p.pDMin == null) {
                            p.pDMin = pi.getD();
                        }
                        if (pi.getD() > p.pDMax) {
                            p.pDMax = pi.getD();
                        } else if (pi.getD() < p.pDMin) {
                            p.pDMin = pi.getD();
                        }
                    }

                    if (pi.getEC() != null) {
                        if (p.pEcMax == null) {
                            p.pEcMax = pi.getEC();
                        }
                        if (p.pEcMin == null) {
                            p.pEcMin = pi.getEC();
                        }
                        if (pi.getEC() > p.pEcMax) {
                            p.pEcMax = pi.getEC();
                        } else if (pi.getD() < p.pEcMin) {
                            p.pEcMin = pi.getEC();
                        }
                    }

                    if (pi.getI() != null) {
                        if (p.pIMax == null) {
                            p.pIMax = pi.getI();
                        }
                        if (p.pIMin == null) {
                            p.pIMin = pi.getI();
                        }
                        if (pi.getI() > p.pIMax) {
                            p.pIMax = pi.getI();
                        } else if (pi.getI() < p.pIMin) {
                            p.pIMin = pi.getI();
                        }
                    }

                    if (pi.getNCP() != null) {
                        if (p.pNcpMax == null) {
                            p.pNcpMax = pi.getNCP();
                        }
                        if (p.pNcpMin == null) {
                            p.pNcpMin = pi.getNCP();
                        }
                        if (pi.getNCP() > p.pNcpMax) {
                            p.pNcpMax = pi.getNCP();
                        } else if (pi.getNCP() < p.pNcpMin) {
                            p.pNcpMin = pi.getNCP();
                        }
                    }

                    if (pi.getNIP() != null) {
                        if (p.pNipMax == null) {
                            p.pNipMax = pi.getNIP();
                        }
                        if (p.pNipMin == null) {
                            p.pNipMin = pi.getNIP();
                        }
                        if (pi.getNIP() > p.pNipMax) {
                            p.pNipMax = pi.getNIP();
                        } else if (pi.getNIP() < p.pNipMin) {
                            p.pNipMin = pi.getNIP();
                        }
                    }

                    if (pi.getLOC() != null) {
                        if (p.pLocMax == null) {
                            p.pLocMax = pi.getLOC();
                        }
                        if (p.pLocMin == null) {
                            p.pLocMin = pi.getLOC();
                        }
                        if (pi.getLOC() > p.pLocMax) {
                            p.pLocMax = pi.getLOC();
                        } else if (pi.getLOC() < p.pLocMin) {
                            p.pLocMin = pi.getLOC();
                        }
                    }

                    if (pi.getLOCm() != null) {
                        if (p.pLocmMax == null) {
                            p.pLocmMax = pi.getLOCm();
                        }
                        if (p.pLocmMin == null) {
                            p.pLocmMin = pi.getLOCm();
                        }
                        if (pi.getLOCm() > p.pLocmMax) {
                            p.pLocmMax = pi.getLOCm();
                        } else if (pi.getLOCm() < p.pLocmMin) {
                            p.pLocmMin = pi.getLOCm();
                        }
                    }
                }
            }
        }
    }
    private ProjectImpl p;
}
