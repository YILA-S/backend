package backend.services.coursePeriod.domain;

import java.time.Year;

public class CoursePeriod {
    private Quarter quarter;
    private Year year;
    private String id;

    public CoursePeriod(Quarter quarter, Year year) {
        this.quarter = quarter;
        this.year = year;
        this.id = quarter.toString() + year;
    }

    public CoursePeriod(Quarter quarter, Year year, String id) {
        this.quarter = quarter;
        this.year = year;
        this.id = id;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public Year getYear() {
        return year;
    }

    public String getId() {
        return id;
    }
}
