package com.company.tests;

import com.company.decathlon.utils.Utils;
import com.company.decathlon.utils.ImportCsv;
import com.company.model.Athlete;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zygis on 27/09/2015.
 */
public class ImportCsvTest {

    private String csvTestInput =
            "Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72\n" +
                    "Beata Kana;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76\n" +
                        "Jaana Lind;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6.22.75";

    private Athlete[] results;

    @Before
    public void setup() throws ParseException {
        results = new Athlete[3];
        int i = 0;
        results[i] = new Athlete();
        results[i].setName("Siim Susi");
        results[i].setHundretMeters(12.61);
        results[i].setLongJump(5.00);
        results[i].setShotPut(9.22);
        results[i].setHighJump(1.50);
        results[i].setFourHundrets(60.39);
        results[i].setHurdles(16.43);
        results[i].setDiscusThrow(21.60);
        results[i].setPoleVault(2.60);
        results[i].setJavelinThrow(35.81);
        results[i].setThousandFiveHundrets(Utils.minToSec("5.25.72"));

        i++;
        results[i] = new Athlete();
        results[i].setName("Beata Kana");
        results[i].setHundretMeters(13.04);
        results[i].setLongJump(4.53);
        results[i].setShotPut(7.79);
        results[i].setHighJump(1.55);
        results[i].setFourHundrets(64.72);
        results[i].setHurdles(18.74);
        results[i].setDiscusThrow(24.20);
        results[i].setPoleVault(2.40);
        results[i].setJavelinThrow(28.20);
        results[i].setThousandFiveHundrets(Utils.minToSec("6.50.76"));

        i++;
        results[i] = new Athlete();
        results[i].setName("Jaana Lind");
        results[i].setHundretMeters(13.75);
        results[i].setLongJump(4.84);
        results[i].setShotPut(10.12);
        results[i].setHighJump(1.50);
        results[i].setFourHundrets(68.44);
        results[i].setHurdles(19.18);
        results[i].setDiscusThrow(30.85);
        results[i].setPoleVault(2.80);
        results[i].setJavelinThrow(33.88);
        results[i].setThousandFiveHundrets(Utils.minToSec("6.22.75"));
    }

    @Test
    public void athletesDataFromCSVFile() {
        List<Athlete> importedResults = ImportCsv.getImportCsv().readCsvFile(new ByteArrayInputStream(csvTestInput.getBytes()));
        assertEquals(results[0].getAsCSVString(), importedResults.get(0).getAsCSVString());
        assertEquals(results[1].getAsCSVString(), importedResults.get(1).getAsCSVString());
        assertEquals(results[2].getAsCSVString(), importedResults.get(2).getAsCSVString());
    }

}