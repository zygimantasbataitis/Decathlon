package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private static boolean parseCommandLine (String[] args) throws JAXBException, IOException {
        int i = 0;
        try {
        	if (!readFromCsv(args[i], args[++i])) {
            	Utils.logInfo(Consts.NO_INPUT_DEFINITION);
                return false;
        	}
        	
            orderResults();
            exportXML(args[++i]);

            i++;
            exportHTML(args[i], args[i+1]);

            Utils.logInfo(Consts.ALL_TASKS_EXECUTED);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            Utils.logWarning(Consts.WRONG_FILES_DEFINITION);
            return false;
        }
    }
    
    private static boolean readFromCsv(String inputMethod, String inputParameter) throws FileNotFoundException {
        if (inputMethod != null && inputMethod.compareToIgnoreCase("-csv") == 0 && Utils.existsFile(inputParameter)) {
            athletes = CsvImporter.readCsvFile(new FileInputStream (inputParameter));
            return true;
        } else {
        	return false;
        }
    }
    
    private static void exportXML(String inputParameter) throws FileNotFoundException, JAXBException, IOException {
        try {
            XmlExporter.exportAsXML(Utils.newFileOutputStream(inputParameter), athletes);
        } catch (TransformerException e) {
        	Utils.logInfo(Consts.XML_NOT_EXPORTED);
        }  	
    }
    
    private static void exportHTML(String inputMethod, String inputParameter) throws FileNotFoundException, JAXBException, IOException {
        if (inputMethod != null && inputMethod.compareToIgnoreCase("-html") == 0) {
            try {
                HtmlExporter.exportAsHTML(Utils.newFileOutputStream(inputParameter), athletes);
            } catch (TransformerException e) {
            	Utils.logInfo(Consts.HTML_NOT_EXPORTED);
            }
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
