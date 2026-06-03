import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskSchedule extends ScheduleItem {

    private LocalDate deadline;
    private int progress;
    private String taskStatus;
    private String assignedTo;

    public TaskSchedule(String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String deadline, int progress, String taskStatus, String assignedTo) {
        super(title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("시작일은 yyyy-MM-dd 형식이어야 합니다.");
        }
        try {
            if (progress >= 0 && progress <= 100) {
                this.progress = progress;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("0이상 100 이하의 정수여야 함.");
        }
        try {
            if (taskStatus.equals("TODO") || taskStatus.equals("IN_PROGRESS") || taskStatus.equals("DONE")) {
                this.taskStatus = taskStatus;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("하나만 사용 가능합니다");
        }
        this.assignedTo = assignedTo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("사선 : " + deadline);
        System.out.println("과정 : " + progress);
        System.out.println("작업 상태 : " + taskStatus);
        System.out.println("할당된 : " + assignedTo);
    }

    @Override
    public void markAsCompleted() {
        super.markAsCompleted();
            this.progress = 100;
            this.taskStatus = "DONE";
    }
}
