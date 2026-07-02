import java.time.LocalTime;

public class ReminderSchedule extends ScheduleItem {
    private LocalTime reminderTime;
    private String reminderMessage;
    private String notificationType;
    private boolean isReminderSent;


    public ReminderSchedule(int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String reminderTime, String reminderMessage, String notificationType, boolean isReminderSent) {
        super(userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        init(reminderTime, reminderMessage, notificationType, isReminderSent);
    }

    public ReminderSchedule(int id, int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String reminderTime, String reminderMessage, String notificationType, boolean isReminderSent) {
        super(id, userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        init(reminderTime, reminderMessage, notificationType, isReminderSent);
    }

    private void init(String reminderTime, String reminderMessage, String notificationType, boolean isReminderSent) {
        try {
            this.reminderTime = LocalTime.parse(reminderTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("알림 시간은 HH:mm 형식이어야 합니다");
        }
        this.reminderMessage = reminderMessage;
        notificationType = notificationType == null ? "" : notificationType.trim().toUpperCase();
        if (notificationType.equals("POPUP") || notificationType.equals("SOUND") || notificationType.equals("MESSAGE")) {
            this.notificationType = notificationType;
        } else {
            throw new RuntimeException("알림 타입은 POPUP, SOUND, MESSAGE 중 하나여야 합니다.");
        }
        this.isReminderSent = isReminderSent;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("알림 시간 : " + reminderTime);
        System.out.println("알림 메시지 : " + reminderMessage);
        System.out.println("알림 타입 : " + notificationType);
        System.out.println("알림 발송 여부 : " + isReminderSent);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "\n"
                + "알림 시간 : " + reminderTime + "\n"
                + "알림 메시지 : " + reminderMessage + "\n"
                + "알림 타입 : " + notificationType + "\n"
                + "알림 발송 여부 : " + isReminderSent;
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
