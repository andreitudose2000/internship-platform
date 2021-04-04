package fields;

import java.time.YearMonth;

public class EducationField extends TimelineField {
    private String institutionName;
    private String specializationName;
    public EducationField(YearMonth startDate, YearMonth finishDate, String description, String institutionName, String specializationName){
        super(startDate, finishDate, description);
        this.institutionName = institutionName;
        this.specializationName = specializationName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
