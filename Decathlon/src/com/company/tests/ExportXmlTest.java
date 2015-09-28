package com.company.tests;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.company.decathlon.utils.XmlExporter;
import com.company.decathlon.utils.Utils;
import com.company.model.Athlete;

/**
 * Created by zygis on 27/09/2015.
 */
public class ExportXmlTest {

    private List<Athlete> results = new ArrayList<Athlete>();

    @Before
    public void setup() throws ParseException {
        results.add(new Athlete(new String("Siim Susi"), 12.61, 5.00, 9.22, 1.50,
                60.39, 16.43, 21.60, 2.60, 35.81, Utils.minToSec("5.25.72")));
        results.add(new Athlete(new String("Beata Kana"), 13.04, 4.53, 7.79, 1.55,
                64.72, 18.74, 24.20, 2.40, 28.20, Utils.minToSec("6.50.76")));
        results.add(new Athlete(new String("Jaana Lind"), 13.75, 4.84, 10.12, 1.50,
                68.44, 19.18, 30.85, 2.80, 33.88, Utils.minToSec("6.22.75")));
    }

    @Test
    public void exportAsXMLTest() throws ParserConfigurationException, IOException, SAXException, JAXBException, TransformerException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        XmlExporter.exportAsXML(new PrintStream(outStream), results);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outStream.toByteArray());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        try {
            builder.parse(new InputSource(inputStream));
        } catch (Exception e) {
            fail("XML is not valid.");
        }
    }

}