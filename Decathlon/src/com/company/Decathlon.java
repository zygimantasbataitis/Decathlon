package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import com.company.decathlon.utils.CsvImporter;
import com.company.decathlon.utils.HtmlExporter;
import com.company.decathlon.utils.Utils;
import com.company.decathlon.utils.XmlExporter;
import com.company.model.Athlete;

public class Decathlon {

    static List<Athlete> athletes = new ArrayList<>();

    public static void main(String[] args) throws JAXBException, IOException {

        if (!parseCommandLine(args)) {
            System.out.println(Consts.APPLICATION_USAGE);
        }

    }

    static boolean parseCommandLine (String[] args) throws JAXBException, IOException {
        int i = 0;
        try {
            if (args[i] != null && args[i].compareToIgnoreCase("-csv") == 0 && Utils.existsFile(args[i+1])) {
                i++;
                athletes = CsvImporter.readCsvFile(new FileInputStream (args[i]));
            } else {
            	Utils.logInfo(Consts.NO_INPUT_DEFINITION);
                return false;
            }

            i++;

            orderResults();
            try {
                XmlExporter.exportAsXML(Utils.newFileOutputStream(args[i]), athletes);
            } catch (TransformerException e) {
            	Utils.logInfo(Consts.XML_NOT_EXPORTED);
                return false;
            }

            i++;
            if (args[i] != null && args[i].compareToIgnoreCase("-html") == 0) {
                try {
                    i++;
                    HtmlExporter.exportAsHTML(Utils.newFileOutputStream(args[i]), athletes);
                } catch (TransformerException e) {
                	Utils.logInfo(Consts.HTML_NOT_EXPORTED);
                    return false;
                }
            }

            Utils.logInfo(Consts.ALL_TASKS_EXECUTED);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            Utils.logWarning(Consts.WRONG_FILES_DEFINITION);
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
