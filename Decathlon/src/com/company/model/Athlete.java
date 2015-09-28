package com.company.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import static com.company.Consts.COMMA_DELIMITER;

/**
 * Created by zygis on 25/09/2015.
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "athlete")
@XmlType(propOrder={"position", "name", "hundretMeters", "longJump", "shotPut", "highJump",
        "fourHundrets", "hurdles", "discusThrow", "poleVault", "javelinThrow", "thousandFiveHundrets", "score"})
public class Athlete implements Comparable<Athlete> {

    private String name;
    private Double hundretMeters;
    private Double longJump;
    private Double shotPut;
    private Double highJump;
    private Double fourHundrets;
    private Double hurdles;
    private Double discusThrow;
    private Double poleVault;
    private Double javelinThrow;
    private Double thousandFiveHundrets;
    private String position;
    @SuppressWarnings("unused")
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHundretMeters() {
        return hundretMeters;
    }

    public void setHundretMeters(Double hundretMeters) {
        this.hundretMeters = hundretMeters;
    }

    public Double getLongJump() {
        return longJump;
    }

    public void setLongJump(Double longJump) {
        this.longJump = longJump;
    }

    public Double getShotPut() {
        return shotPut;
    }

    public void setShotPut(Double shotPut) {
        this.shotPut = shotPut;
    }

    public Double getHighJump() {
        return highJump;
    }

    public void setHighJump(Double highJump) {
        this.highJump = highJump;
    }

    public Double getFourHundrets() {
        return fourHundrets;
    }

    public void setFourHundrets(Double fourHundrets) {
        this.fourHundrets = fourHundrets;
    }

    public Double getHurdles() {
        return hurdles;
    }

    public void setHurdles(Double hurdles) {
        this.hurdles = hurdles;
    }

    public Double getDiscusThrow() {
        return discusThrow;
    }

    public void setDiscusThrow(Double discusThrow) {
        this.discusThrow = discusThrow;
    }

    public Double getPoleVault() {
        return poleVault;
    }

    public void setPoleVault(Double poleVault) {
        this.poleVault = poleVault;
    }

    public Double getJavelinThrow() {
        return javelinThrow;
    }

    public void setJavelinThrow(Double javelinThrow) {
        this.javelinThrow = javelinThrow;
    }

    public Double getThousandFiveHundrets() {
        return thousandFiveHundrets;
    }

    public void setThousandFiveHundrets(Double thousandFiveHundrets) {
        this.thousandFiveHundrets = thousandFiveHundrets;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Athlete() {

    }

    public Athlete(String name, Double hundretMeters, Double longJump, Double shotPut, Double highJump, Double fourHundrets,
                   Double hurdles, Double discusThrow, Double poleVault, Double javelinThrow, Double thousandFiveHundrets) {
        super();
        this.name = name;
        this.hundretMeters = hundretMeters;
        this.longJump = longJump;
        this.shotPut = shotPut;
        this.highJump = highJump;
        this.fourHundrets = fourHundrets;
        this.hurdles = hurdles;
        this.discusThrow = discusThrow;
        this.poleVault = poleVault;
        this.javelinThrow = javelinThrow;
        this.thousandFiveHundrets = thousandFiveHundrets;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return EventType.HUNDRET_METERS.getPoints(getHundretMeters()) +
                EventType.LONG_JUMP.getPoints(getLongJump() +
                EventType.SHOT_PUT.getPoints(getShotPut()) +
                EventType.HIGH_JUMP.getPoints(getHighJump()) +
                EventType.FOUR_HUNDRETS.getPoints(getFourHundrets()) +
                EventType.HURDLES_110M.getPoints(getHurdles()) +
                EventType.DISCUS_THROW.getPoints(getDiscusThrow()) +
                EventType.POLE_VAULT.getPoints(getPoleVault()) +
                EventType.JAVELIN_THROW.getPoints(getJavelinThrow()) +
                EventType.THOUSAND_FIVE_HUNDRETS.getPoints(getThousandFiveHundrets()));
    }

    @Override
    public int compareTo(Athlete athlete) {
        if (this.getScore() > athlete.getScore()) return 1;
        if (this.getScore() < athlete.getScore()) return -1;
        return 0;
    }

    public String getAsCSVString() {
        String csv = "\"" + getName() + "\",";
        csv += COMMA_DELIMITER + getHundretMeters().toString();
        csv += COMMA_DELIMITER + getLongJump().toString();
        csv += COMMA_DELIMITER + getShotPut().toString();
        csv += COMMA_DELIMITER + getHighJump().toString();
        csv += COMMA_DELIMITER + getFourHundrets().toString();
        csv += COMMA_DELIMITER + getHurdles().toString();
        csv += COMMA_DELIMITER + getDiscusThrow().toString();
        csv += COMMA_DELIMITER + getPoleVault().toString();
        csv += COMMA_DELIMITER + getJavelinThrow().toString();
        return csv;
    }

}
