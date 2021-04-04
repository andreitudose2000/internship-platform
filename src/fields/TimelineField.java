package fields;

import java.time.YearMonth;

abstract class TimelineField implements Comparable<TimelineField> {
    private YearMonth startDate;
    private YearMonth finishDate;
    private String description;

    public TimelineField(YearMonth startDate, YearMonth finishDate, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public YearMonth getFinishDate() {
        return finishDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(TimelineField o) {
        int comp = this.startDate.compareTo(o.startDate);
        if (comp != 0) {
            return comp;
        }
        return this.finishDate.compareTo(o.finishDate);
    }
}

