import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleItem {
    private final int id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private LocalDate createdAt;
    private LocalTime updatedAt;
    private boolean isCompleted;


    private static int nextId = 1;
    public ScheduleItem(String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        this(nextId++, title, description, startDate, endDate, startTime, endTime, priority, isCompleted);
    }

    protected ScheduleItem(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        this.id = id;
        if (title == null || title.trim().isEmpty()) {
            throw new RuntimeException("비어있을 수 없습니다");
        }
        this.title = title;
        this.description = description;
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (RuntimeException e) {
            throw new RuntimeException("시작일은 YYYY-MM-DD 형식이어야 합니다.");
        }
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (RuntimeException e) {
            throw new RuntimeException("종료일은 YYYY-MM-DD 형식이어야 합니다.");
        }
        try {
            this.startTime = LocalTime.parse(startTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("시작 시간은 HH:mm 형식이어야 합니다");
        }
        try {
            this.endTime = LocalTime.parse(endTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("종료 시간은 HH:mm 형식이어야 합니다");
        }

        if (this.endDate.isBefore(this.startDate)) {
            throw new RuntimeException("종료일은 시작일보다 빠를 수 없습니다.");
        }

        if (this.endDate.equals(this.startDate) && this.endTime.isBefore(this.startTime)) {
            throw new RuntimeException("같은 날짜에서는 종료 시간이 시작 시간보다 빠를 수 없습니다.");
        }

        priority = priority == null ? "" : priority.trim().toUpperCase();

        if (priority.equals("LOW") || priority.equals("MEDIUM") || priority.equals("HIGH")) {
            this.priority = priority;
        } else {
            throw new RuntimeException("우선순위는 HIGH, MEDIUM, LOW 중 하나여야 합니다.");
        }

        this.createdAt = LocalDate.now();
        this.updatedAt = LocalTime.now();
        this.isCompleted = isCompleted;
    }

    public void displayInfo() {
        System.out.println("id : " + id);
        System.out.println("제목 : " + title);
        System.out.println("내용 : " + description);
        System.out.println("시작일 :  " + startDate);
        System.out.println("종료일 : " + endDate);
        System.out.println("시작시간 : " + startTime);
        System.out.println("종료시간 : " + endTime);
        System.out.println("일정 중요도 : " + priority);
        System.out.println("일정 생성 시각 : " + createdAt);
        System.out.println("일정 수정 시각 : " + updatedAt);
        System.out.println("일정 완료 여부 : " + isCompleted);
    }

    public String toFileString() {
        return "일정 종류 : " + getScheduleType() + "\n"
                + "id : " + id + "\n"
                + "제목 : " + title + "\n"
                + "내용 : " + description + "\n"
                + "시작일 : " + startDate + "\n"
                + "종료일 : " + endDate + "\n"
                + "시작시간 : " + startTime + "\n"
                + "종료시간 : " + endTime + "\n"
                + "일정 중요도 : " + priority + "\n"
                + "일정 생성 시각 : " + createdAt + "\n"
                + "일정 수정 시각 : " + updatedAt + "\n"
                + "일정 완료 여부 : " + isCompleted;
    }

    public String getScheduleType() {
        return "ScheduleItem";
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void notifyUser() {
        System.out.println("알림 대상이 아닙니다");
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalTime getUpdateAt() {
        return updatedAt;
    }

    public LocalTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public static int getNextId() {
        return nextId;
    }
}
