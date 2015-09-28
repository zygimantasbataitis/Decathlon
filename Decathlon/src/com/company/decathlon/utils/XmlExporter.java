package com.company.decathlon.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import com.company.model.Athlete;
import com.company.model.Athletes;

/**
 * Created by zygis on 26/09/2015.
 */
public final class XmlExporter {

    public static void exportAsXML(PrintStream outputStream, List<Athlete> athletes) throws JAXBException, FileNotFoundException, TransformerException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));

        JAXBContext jaxbContext = JAXBContext.newInstance(Athletes.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        jaxbMarshaller.marshal( new Athletes(athletes), out );
    }

}