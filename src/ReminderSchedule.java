import java.time.LocalTime;

public class ReminderSchedule extends ScheduleItem {
    private LocalTime reminderTime;
    private String reminderMessage;
    private String notificationType;
    private boolean isReminderSent;


    public ReminderSchedule(String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String reminderTime, String reminderMessage, String notificationType, boolean isReminderSent) {
        super(title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        try {
            this.reminderTime = LocalTime.parse(reminderTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("시작일은 HH:MM 형식이어야 합니다");
        }
        this.reminderMessage = reminderMessage;
        try {
            if (notificationType.equals("POPUP") || notificationType.equals("SOUND") || notificationType.equals("MESSAGE")) {
                this.notificationType = notificationType;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("하나만 사용 가능합니다");
        }
        this.isReminderSent = isReminderSent;
    }

    @Override
    public String getScheduleType() {
        return "ReminderSchedule";
    }

    @Override
    public void notifyUser() {
        System.out.println(reminderMessage);
        this.isReminderSent = true;
    }
}
