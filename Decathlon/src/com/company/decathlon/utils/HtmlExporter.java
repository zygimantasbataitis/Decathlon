package com.company.decathlon.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.company.Consts;
import com.company.model.Athlete;
import com.company.model.Athletes;

/**
 * Created by zygis on 27/09/2015.
 */
public final class HtmlExporter {

    public static void exportAsHTML(PrintStream outputStream, List<Athlete> athletes) throws JAXBException, FileNotFoundException, TransformerException {
        File f = new File(Consts.TEMP_XML);

        FileOutputStream tmpFile = new FileOutputStream(f);
        PrintStream fileStream = new PrintStream(tmpFile);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fileStream));

        JAXBContext jaxbContext = JAXBContext.newInstance(Athletes.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        jaxbMarshaller.marshal( new Athletes(athletes), out );

        InputStream xsl = XmlExporter.class.getResourceAsStream(Consts.RESULTS_XSL);

        BufferedWriter outHTML = new BufferedWriter(new OutputStreamWriter(outputStream));

        Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xsl));
        transformer.transform(new javax.xml.transform.stream.StreamSource(f), new StreamResult(outHTML));

        fileStream.close();
        if (f.exists())
            f.delete();
    }

}
