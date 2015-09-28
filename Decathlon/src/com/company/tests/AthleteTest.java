package com.company.tests;

/**
 * Created by zygis on 27/09/2015.
 */

import com.company.decathlon.utils.Utils;
import com.company.model.Athlete;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AthleteTest {

    @Test
    public void testAthlete() {
        Athlete athlete = new Athlete();
        assertNull(athlete.getName());
    }

    @Test
    public void athleteScoreCalculatedCorrectly() throws ParseException {
        //Siim Susi
        Athlete athlete = new Athlete();
        athlete.setHundretMeters(12.61);
        athlete.setLongJump(5.00);
        athlete.setShotPut(9.22);
        athlete.setHighJump(1.50);
        athlete.setFourHundrets(60.39);
        athlete.setHurdles(16.43);
        athlete.setDiscusThrow(21.60);
        athlete.setPoleVault(2.60);
        athlete.setJavelinThrow(35.81);
        athlete.setThousandFiveHundrets(Utils.minToSec("5.25.72"));
        assertEquals(athlete.getScore(), 6757431);
    }

}
