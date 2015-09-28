package com.company.decathlon.utils;

import static com.company.Consts.ATHLETE_NAME_IDX;
import static com.company.Consts.COMMA_DELIMITER;
import static com.company.Consts.ERROR_CLOSING_READER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Athlete;
import com.company.model.EventType;

/**
 * Created by zygis on 25/09/2015.
 */
public class ImportCsv extends AbstractLogger {

    private static ImportCsv dataImportCsv;
    private ImportCsv() {}

    public static ImportCsv getImportCsv() {
        if(dataImportCsv == null) dataImportCsv = new ImportCsv();
        return dataImportCsv;
    }

    public List<Athlete> readCsvFile(InputStream fileStream) {

        BufferedReader fileReader = null;
        List<Athlete> athletes = new ArrayList<Athlete>();

        try {
            String line = "";

            fileReader = new BufferedReader( new InputStreamReader(fileStream));
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    Athlete athlete = new Athlete(tokens[ATHLETE_NAME_IDX], Double.parseDouble(tokens[EventType.HUNDRET_METERS.getIdx()]),
                            Double.parseDouble(tokens[EventType.LONG_JUMP.getIdx()]), Double.parseDouble(tokens[EventType.SHOT_PUT.getIdx()]),
                            Double.parseDouble(tokens[EventType.HIGH_JUMP.getIdx()]), Double.parseDouble(tokens[EventType.FOUR_HUNDRETS.getIdx()]),
                            Double.parseDouble(tokens[EventType.HURDLES_110M.getIdx()]), Double.parseDouble(tokens[EventType.DISCUS_THROW.getIdx()]),
                            Double.parseDouble(tokens[EventType.POLE_VAULT.getIdx()]), Double.parseDouble(tokens[EventType.JAVELIN_THROW.getIdx()]),
                            Utils.minToSec(tokens[EventType.THOUSAND_FIVE_HUNDRETS.getIdx()]));
                    athletes.add(athlete);
                }
            }

        } catch (Exception e) {
            System.out.println("Error in ImportCsv!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                logInfo(ERROR_CLOSING_READER);
                e.printStackTrace();
            }
        }
        return athletes;
    }

}