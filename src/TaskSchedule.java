import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskSchedule extends ScheduleItem {

    private LocalDate deadline;
    private int progress;
    private String taskStatus;
    private String assignedTo;

    public TaskSchedule(String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String deadline, int progress, String taskStatus, String assignedTo) {
        super(title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        init(deadline, progress, taskStatus, assignedTo);
    }

    public TaskSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String deadline, int progress, String taskStatus, String assignedTo) {
        super(id, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        init(deadline, progress, taskStatus, assignedTo);
    }

    private void init(String deadline, int progress, String taskStatus, String assignedTo) {
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("마감일은 YYYY-MM-DD 형식이어야 합니다.");
        }
        if (progress >= 0 && progress <= 100) {
            this.progress = progress;
        } else {
            throw new RuntimeException("진행률은 0 이상 100 이하의 정수여야 합니다.");
        }

        taskStatus = taskStatus == null ? "" : taskStatus.trim().toUpperCase();
        if (taskStatus.equals("TODO") || taskStatus.equals("IN_PROGRESS") || taskStatus.equals("DONE")) {
            this.taskStatus = taskStatus;
        } else {
            throw new RuntimeException("작업 상태는 TODO, IN_PROGRESS, DONE 중 하나여야 합니다.");
        }
        this.assignedTo = assignedTo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("마감일 : " + deadline);
        System.out.println("진행률 : " + progress);
        System.out.println("작업 상태 : " + taskStatus);
        System.out.println("담당자 : " + assignedTo);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "\n"
                + "마감일 : " + deadline + "\n"
                + "진행률 : " + progress + "\n"
                + "작업 상태 : " + taskStatus + "\n"
                + "담당자 : " + assignedTo;
    }

    @Override
    public void markAsCompleted() {
        super.markAsCompleted();
            this.progress = 100;
            this.taskStatus = "DONE";
    }
}
