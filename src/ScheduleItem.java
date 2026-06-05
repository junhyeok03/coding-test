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
    private LocalTime updateAt;
    private boolean isCompleted;


    private static int nextId = 1;

    // POJO, equals, Object

//    public ScheduleItem() {
//
//    }

    // 착한 경고 -> 오타 수정
    // 문서 꼼꼼히 보기
    public ScheduleItem(String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        this.id = nextId++;
        if (title == null) {
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
            throw new RuntimeException("시작일은 YYYY-MM-DD 형식이어야 합니다.");
        }
        try {
            this.startTime = LocalTime.parse(startTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("시작일은 HH:MM 형식이어야 합니다");
        }
        try {
            this.endTime = LocalTime.parse(endTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("시작일은 HH:MM 형식이어야 합니다");
        }

        if (this.endDate.isBefore(this.startDate)) {
            throw new RuntimeException("종료일은 시작일보다 빠를 수 없습니다.");
        }

        if (priority.equals("LOW") || priority.equals("MEDIUM") || priority.equals("HIGH")) {
            this.priority = priority;
        } else {
            throw new RuntimeException("하나만 실행 가능 합니다");
        }

        this.createdAt = LocalDate.now();
        this.updateAt = LocalTime.now();
        this.isCompleted = isCompleted;
    }

    public void displayInfo() {
        System.out.println("제목 : " + title);
        System.out.println("내용 : " + description);
        System.out.println("시작일 :  " + startDate);
        System.out.println("종료일 : " + endDate);
        System.out.println("시작시간 : " + startTime);
        System.out.println("종료시간 : " + endTime);
        System.out.println("일정 중요도 : " + priority);
        System.out.println("일정 생성 시각 : " + createdAt);
        System.out.println("일정 수정 시각 : " + updateAt);
        System.out.println("일정 완료 여부 : " + isCompleted);
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
        return updateAt;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public static int getNextId() {
        return nextId;
    }
}
