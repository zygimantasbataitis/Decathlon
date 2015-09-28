package com.company.model;

import static java.lang.Math.pow;
/**
 * Created by zygis on 25/09/2015.
 */
public enum EventType {

    HUNDRET_METERS(1, 25.4347, 18, 1.81, UnitType.SECONDS, true, false),
    LONG_JUMP(2, 0.14354, 220, 1.4, UnitType.METERS, false, true),
    SHOT_PUT(3, 51.39, 1.5, 1.05, UnitType.METERS, false, false),
    HIGH_JUMP(4, 0.8465, 75, 1.42, UnitType.METERS, false, true),
    FOUR_HUNDRETS(5, 1.53775, 82, 1.81, UnitType.SECONDS, true, false),
    HURDLES_110M(6, 5.74352, 28.5, 1.92, UnitType.SECONDS, true, false),
    DISCUS_THROW(7, 12.91, 4, 1.1, UnitType.METERS, false, false),
    POLE_VAULT(8, 0.2797, 100, 1.35, UnitType.METERS, false, false),
    JAVELIN_THROW(9, 10.14, 7, 1.08, UnitType.METERS, false, false),
    THOUSAND_FIVE_HUNDRETS(10, 0.03768, 480, 1.85, UnitType.MINUTES_SECONDS, true, false);

    private int idx;
    private double par_a;
    private double par_b;
    private double par_c;
    private UnitType unitType;
    private boolean track;
    private boolean jump;

    private EventType(int idx, double par_a, double par_b, double par_c, UnitType unitType, boolean track, boolean jump) {
        this.idx = idx;
        this.par_a = par_a;
        this.par_b = par_b;
        this.par_c = par_c;
        this.unitType = unitType;
        this.track = track;
        this.jump = jump;
    }

    public double getPar_c() {
        return par_c;
    }

    public double getPar_b() {
        return par_b;
    }

    public double getPar_a() {
        return par_a;
    }

    public int getIdx() {
        return idx;
    }
    public UnitType getUnitType() {
        return unitType;
    }

    public int getPoints (double p) {
        int result;
        if (jump) {
            p = p * 100;
        }
        if (track) {
            result = (int)(getPar_a() * pow(getPar_b() - p, getPar_c()));
        } else {
            result = (int)(getPar_a() * pow(p - getPar_b(), getPar_c()));
        }
        return result;
    }

}



