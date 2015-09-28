package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import com.company.decathlon.utils.ExportHtml;
import com.company.decathlon.utils.ExportXml;
import com.company.decathlon.utils.ImportCsv;
import com.company.decathlon.utils.Utils;
import com.company.model.Athlete;

public class Decathlon {

    static Logger log = Logger.getLogger(Decathlon.class.getName());

    static List<Athlete> athletes = new ArrayList<>();

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        if (!parseCommandLine(args)) {
            System.out.println(Consts.APPLICATION_USAGE);
        }

    }

    static boolean parseCommandLine (String[] args) throws JAXBException, FileNotFoundException {
        int i = 0;
        try {
            if (args[i] != null && args[i].compareToIgnoreCase("-csv") == 0 && Utils.existsFile(args[i+1])) {
                i++;
                athletes = ImportCsv.getImportCsv().readCsvFile(new FileInputStream (args[i]));
            } else {
                log.info(Consts.NO_INPUT_DEFINITION);
                return false;
            }

            i++;

            orderResults();
            try {
                ExportXml.getExportXml().exportAsXML(Utils.newFileOutputStream(args[i]), athletes);
            } catch (TransformerException e) {
                log.info(Consts.XML_NOT_EXPORTED);
                return false;
            }

            i++;
            if (args[i] != null && args[i].compareToIgnoreCase("-html") == 0) {
                try {
                    i++;
                    ExportHtml.getExportHtml().exportAsHTML(Utils.newFileOutputStream(args[i]), athletes);
                } catch (TransformerException e) {
                    log.info(Consts.HTML_NOT_EXPORTED);
                    return false;
                }
            }

            log.info(Consts.ALL_TASKS_EXECUTED);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warning(Consts.WRONG_FILES_DEFINITION);
            return false;
        }
    }

    static void orderResults() {
        Integer previousPoint = 0;
        Integer previousPlace = 0;
        Integer currentPlace = 1;
        String placeText;

        Collections.sort(athletes, Collections.reverseOrder());
        for (Athlete athlete : athletes) {
            if (athlete.getScore() == previousPoint)	{
                placeText = previousPlace.toString() + "-" + currentPlace.toString();
                for (int j = previousPlace-1; j<currentPlace; j++) {
                    athletes.get(j).setPosition(placeText);
                }
            } else {
                previousPlace = currentPlace;
                athlete.setPosition(currentPlace.toString());
            }
            currentPlace++;
            previousPoint = athlete.getScore();
        }
    }
}
