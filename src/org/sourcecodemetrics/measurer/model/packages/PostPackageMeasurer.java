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

package org.sourcecodemetrics.measurer.model.packages;

import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import org.sourcecodemetrics.measurer.model.sources.SourceFileImpl;

/**
 *
 * @author Krystian
 */
public class PostPackageMeasurer {

    public PostPackageMeasurer(PackageImpl p) {
        this.p = p;
    }

    void postMeasure() {
        // statistical calculations for the average values
        p.loc = 0;
        p.locm = 0;
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

        for (SourceFileImpl sfi : p.sourceFiles.values()) {
            p.loc += sfi.getLOC();
            p.locm += sfi.getLOCm();
        }

        for (ClassImpl ci : p.getClasses()) {
            p.cLccAvg += ci.getLCC();
            p.cLcom1Avg += ci.getLCOM1();
            p.cLcom2Avg += ci.getLCOM2();
            p.cLcom3Avg += ci.getLCOM3();
            p.cLcom4Avg += ci.getLCOM4();
            p.cLcom5Avg += ci.getLCOM5();
            p.cNakAvg += ci.getNAK();
            p.cNocSum += ci.getNOC();
            p.cNofSum += ci.getNOF();
            p.cNomSum += ci.getNOM();
            p.cNosfSum += ci.getNOSF();
            p.cNosmSum += ci.getNOSM();
            p.cNtmSum += ci.getNTM();
            p.cTccAvg += ci.getTCC();
            p.cWmcSum += ci.getWMC();
            p.mNbdAvg += ci.getMethodNBDAvg();
            p.mNopSum += ci.getMethodNOPSum();
            p.mVgAvg += ci.getMethodVGAvg();
        }

        if (p.classes.size() > 0) {
            p.cLccAvg /= p.classes.size();
            p.cLcom1Avg /= p.classes.size();
            p.cLcom2Avg /= p.classes.size();
            p.cLcom3Avg /= p.classes.size();
            p.cLcom4Avg /= p.classes.size();
            p.cLcom5Avg /= p.classes.size();
            p.cNakAvg /= p.classes.size();
            p.cTccAvg /= p.classes.size();
            p.mNbdAvg /= p.classes.size();
            p.mVgAvg /= p.classes.size();
        }

        // statistical calculations for minimal and maximal values
        if (!p.classes.isEmpty()) {
            ClassImpl c0 = p.classes.values().iterator().next();
            p.mLocmMax = c0.getMethodLOCmMax();
            p.mLocmMin = c0.getMethodLOCmMin();
            p.mLocMax = c0.getMethodLOCMax();
            p.mLocMin = c0.getMethodLOCMin();
            p.mNopMax = c0.getMethodNOPMax();
            p.mNopMin = c0.getMethodNOPMin();
            p.mNbdMax = c0.getMethodNBDMax();
            p.mNbdMin = c0.getMethodNBDMin();
            p.mVgMax = c0.getMethodVGMax();
            p.mVgMin = c0.getMethodVGMin();
            p.cNocMax = c0.getNOC();
            p.cNocMin = c0.getNOC();
            p.cNakMax = c0.getNAK();
            p.cNakMin = c0.getNAK();
            p.cNtmMax = c0.getNTM();
            p.cNtmMin = c0.getNTM();
            p.cLocmMax = c0.getLOCm();
            p.cLocmMin = c0.getLOCm();
            p.cLocMax = c0.getLOC();
            p.cLocMin = c0.getLOC();
            p.cWmcMax = c0.getWMC();
            p.cWmcMin = c0.getWMC();
            p.cLccMax = c0.getLCC();
            p.cLccMin = c0.getLCC();
            p.cTccMax = c0.getTCC();
            p.cTccMin = c0.getTCC();
            p.cLcom5Max = c0.getLCOM5();
            p.cLcom5Min = c0.getLCOM5();
            p.cLcom4Max = c0.getLCOM4();
            p.cLcom4Min = c0.getLCOM4();
            p.cLcom3Max = c0.getLCOM3();
            p.cLcom3Min = c0.getLCOM3();
            p.cLcom2Max = c0.getLCOM2();
            p.cLcom2Min = c0.getLCOM2();
            p.cLcom1Max = c0.getLCOM1();
            p.cLcom1Min = c0.getLCOM1();
            p.cNosfMax = c0.getNOSF();
            p.cNosfMin = c0.getNOSF();
            p.cNofMax = c0.getNOF();
            p.cNofMin = c0.getNOF();
            p.cNosmMax = c0.getNOSM();
            p.cNosmMin = c0.getNOSM();
            p.cNomMax = c0.getNOM();
            p.cNomMin = c0.getNOM();

            for (ClassImpl ci : p.getClasses()) {
                if (ci.getMethodLOCmMax() != null && p.mLocmMax != null) {
                    if (ci.getMethodLOCmMax() > p.mLocmMax) {
                        p.mLocmMax = ci.getMethodLOCmMax();
                    } else if (ci.getMethodLOCmMin() < p.mLocmMin) {
                        p.mLocmMin = ci.getMethodLOCmMin();
                    }
                }

                if (ci.getMethodLOCMax() != null && p.mLocMax != null && ci.getMethodLOCmMin() != null && p.mLocMin != null) {
                    if (ci.getMethodLOCMax() > p.mLocMax) {
                        p.mLocMax = ci.getMethodLOCMax();
                    } else if (ci.getMethodLOCmMin() < p.mLocMin) {
                        p.mLocMin = ci.getMethodLOCMin();
                    }
                }

                if (ci.getMethodNOPMax() != null && p.mNopMax != null) {
                    if (ci.getMethodNOPMax() > p.mNopMax) {
                        p.mNopMax = ci.getMethodNOPMax();
                    } else if (ci.getMethodNOPMin() < p.mNopMin) {
                        p.mNopMin = ci.getMethodNOPMin();
                    }
                }

                if (ci.getMethodNBDMax() != null && p.mNbdMax != null) {
                    if (ci.getMethodNBDMax() > p.mNbdMax) {
                        p.mNbdMax = ci.getMethodNBDMax();
                    } else if (ci.getMethodNBDMin() < p.mNbdMin) {
                        p.mNbdMin = ci.getMethodNBDMin();
                    }
                }

                if (ci.getMethodVGMax() != null && p.mVgMax != null) {
                    if (ci.getMethodVGMax() > p.mVgMax) {
                        p.mVgMax = ci.getMethodVGMax();
                    } else if (ci.getMethodVGMin() < p.mVgMin) {
                        p.mVgMin = ci.getMethodVGMin();
                    }
                }

                if (ci.getNOC() != null && p.cNocMax != null) {
                    if (ci.getNOC() > p.cNocMax) {
                        p.cNocMax = ci.getNOC();
                    } else if (ci.getNOC() < p.cNocMin) {
                        p.cNocMin = ci.getNOC();
                    }
                }

                if (ci.getNAK() != null && p.cNakMax != null) {
                    if (ci.getNAK() > p.cNakMax) {
                        p.cNakMax = ci.getNAK();
                    } else if (ci.getNAK() < p.cNakMin) {
                        p.cNakMin = ci.getNAK();
                    }
                }

                if (ci.getNTM() != null && p.cNtmMax != null) {
                    if (ci.getNTM() > p.cNtmMax) {
                        p.cNtmMax = ci.getNTM();
                    } else if (ci.getNTM() < p.cNtmMin) {
                        p.cNtmMin = ci.getNTM();
                    }
                }

                if (ci.getLOCm() != null && p.cLocmMax != null) {
                    if (ci.getLOCm() > p.cLocmMax) {
                        p.cLocmMax = ci.getLOCm();
                    } else if (ci.getLOCm() < p.cLocmMin) {
                        p.cLocmMin = ci.getLOCm();
                    }
                }

                if (ci.getLOC() != null && p.cLocMax != null) {
                    if (ci.getLOC() > p.cLocMax) {
                        p.cLocMax = ci.getLOC();
                    } else if (ci.getLOC() < p.cLocMin) {
                        p.cLocMin = ci.getLOC();
                    }
                }

                if (ci.getWMC() != null && p.cWmcMax != null) {
                    if (ci.getWMC() > p.cWmcMax) {
                        p.cWmcMax = ci.getWMC();
                    } else if (ci.getWMC() < p.cWmcMin) {
                        p.cWmcMin = ci.getWMC();
                    }
                }

                if (ci.getLCC() != null && p.cLccMax != null) {
                    if (ci.getLCC() > p.cLccMax) {
                        p.cLccMax = ci.getLCC();
                    } else if (ci.getLCC() < p.cLccMin) {
                        p.cLccMin = ci.getLCC();
                    }
                }

                if (ci.getTCC() != null && p.cTccMax != null) {
                    if (ci.getTCC() > p.cTccMax) {
                        p.cTccMax = ci.getTCC();
                    } else if (ci.getTCC() < p.cTccMin) {
                        p.cTccMin = ci.getTCC();
                    }
                }

                if (ci.getLCOM1() != null && p.cLcom1Max != null) {
                    if (ci.getLCOM1() > p.cLcom1Max) {
                        p.cLcom1Max = ci.getLCOM1();
                    } else if (ci.getLCOM1() < p.cLcom1Max) {
                        p.cLcom1Max = ci.getLCOM1();
                    }
                }

                if (ci.getLCOM2() != null && p.cLcom2Max != null) {
                    if (ci.getLCOM2() > p.cLcom2Max) {
                        p.cLcom2Max = ci.getLCOM2();
                    } else if (ci.getLCOM2() < p.cLcom2Max) {
                        p.cLcom2Max = ci.getLCOM2();
                    }
                }

                if (ci.getLCOM3() != null && p.cLcom3Max != null) {
                    if (ci.getLCOM3() > p.cLcom3Max) {
                        p.cLcom3Max = ci.getLCOM3();
                    } else if (ci.getLCOM3() < p.cLcom3Max) {
                        p.cLcom3Max = ci.getLCOM3();
                    }
                }

                if (ci.getLCOM4() != null && p.cLcom4Max != null) {
                    if (ci.getLCOM4() > p.cLcom4Max) {
                        p.cLcom4Max = ci.getLCOM4();
                    } else if (ci.getLCOM4() < p.cLcom4Max) {
                        p.cLcom4Max = ci.getLCOM4();
                    }
                }

                if (ci.getLCOM5() != null && p.cLcom5Max != null) {
                    if (ci.getLCOM5() > p.cLcom5Max) {
                        p.cLcom5Max = ci.getLCOM5();
                    } else if (ci.getLCOM5() < p.cLcom5Max) {
                        p.cLcom5Max = ci.getLCOM5();
                    }
                }

                if (ci.getNOSF() != null && p.cNosfMax != null) {
                    if (ci.getNOSF() > p.cNosfMax) {
                        p.cNosfMax = ci.getNOSF();
                    } else if (ci.getNOSF() < p.cNosfMax) {
                        p.cNosfMax = ci.getNOSF();
                    }
                }

                if (ci.getNOF() != null && p.cNofMax != null) {
                    if (ci.getNOF() > p.cNofMax) {
                        p.cNofMax = ci.getNOF();
                    } else if (ci.getNOF() < p.cNofMax) {
                        p.cNofMax = ci.getNOF();
                    }
                }

                if (ci.getNOSM() != null && p.cNosmMax != null) {
                    if (ci.getNOSM() > p.cNosmMax) {
                        p.cNosmMax = ci.getNOSM();
                    } else if (ci.getNOSM() < p.cNosmMax) {
                        p.cNosmMax = ci.getNOSM();
                    }
                }

                if (ci.getNOM() != null && p.cNomMax != null) {
                    if (ci.getNOM() > p.cNomMax) {
                        p.cNomMax = ci.getNOM();
                    } else if (ci.getNOM() < p.cNomMax) {
                        p.cNomMax = ci.getNOM();
                    }
                }
            }
        }
    }
    private PackageImpl p;
}
