package com.company.model;

import com.company.model.Athlete;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by zygis on 26/09/2015.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement( name = "athletes" )
public class Athletes
{

    List<Athlete> athletes;

    public List<Athlete> getAthletes() {
        return athletes;
    }

    @XmlElement(name = "athlete")
    public void setAthletes(List<Athlete> athletes)
    {
        this.athletes = athletes;
    }

    public Athletes(List<Athlete> athletes) {
        super();
        this.athletes = athletes;
    }

    public Athletes() {
    }
}
