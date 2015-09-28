package com.company.decathlon.utils;

import com.company.model.Athlete;
import com.company.model.Athletes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import static com.company.Consts.END_XML_OUTPUT;
import static com.company.Consts.START_XML_OUTPUT;

/**
 * Created by zygis on 26/09/2015.
 */
public class ExportXml extends AbstractLogger{

    private static ExportXml dataExportXml;
    private ExportXml() {}

    public static ExportXml getExportXml() {
        if(dataExportXml == null) dataExportXml = new ExportXml();
        return dataExportXml;
    }

    public void exportAsXML(PrintStream outputStream, List<Athlete> athletes) throws JAXBException, FileNotFoundException, TransformerException {
        logInfo(START_XML_OUTPUT);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));

        JAXBContext jaxbContext = JAXBContext.newInstance(Athletes.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        jaxbMarshaller.marshal( new Athletes(athletes), out );

        logInfo(END_XML_OUTPUT);
    }

}