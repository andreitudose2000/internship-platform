package repository.actions;

import model.Student;
import model.fields.TimelineField;

public interface TimelineFieldRepository {

    /**
     * @param student student
     * @param timelineField timelineField
     * @return id for newly added
     */
    int addTimelineFieldForStudent(Student student, TimelineField timelineField);


    /**
     * @param timelineFieldId timelineFieldId
     * @param timelineField timelineField
     */
    void modifyTimelineField(int timelineFieldId, TimelineField timelineField);

    /**
     * @param timelineFieldId timelineField
     */
    void removeTimelineField(int timelineFieldId);

}
