import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ScheduleManager {
    List<ScheduleItem> scheduleItemList = new ArrayList<>();
    private final Scanner sc;

    public ScheduleManager(Scanner sc) {
        this.sc = sc;
    }

    public void addSchedule() {
        int scheduleType = selectScheduleType();
        if (scheduleType == 0) {
            return;
        }
        addSchedule(scheduleType);
    }


    // 일정 종류
    private int selectScheduleType() {
        System.out.println("일정 종류를 선택하세요.");
        System.out.println("1. 일반 일정");
        System.out.println("2. 회의 일정");
        System.out.println("3. 할일 일정");
        System.out.println("4. 알림 일정");
        System.out.print("선택 : ");

        int scheduleType = readInt();
        if (scheduleType == -1) {
            System.out.println("숫자를 입력하세요.");
            return 0;
        }

        if (scheduleType < 1 || scheduleType > 4) {
            System.out.println("잘못된 일정 종류입니다.");
            return 0;
        }

        return scheduleType;
    }

    // 실제 등록을 처리하는 메서드
    public void addSchedule(int scheduleType) {
        ScheduleItem scheduleItem = createSchedule(scheduleType);
        if (scheduleItem == null) {
            return;
        }

        if (hasConflict(scheduleItem, -1)) {
            System.out.println("기존 일정과 시간이 겹쳐 등록할 수 없습니다.");
            return;
        }

        scheduleItemList.add(scheduleItem);
        System.out.println("일정이 등록되었습니다.");
    }

    // 사용자에게 공통 정보를 입력받는 메서드
    private ScheduleItem createSchedule(int scheduleType) {
        return createSchedule(scheduleType, 0, false);
    }

    private ScheduleItem createSchedule(int scheduleType, int id, boolean isCompleted) {
        try {
            System.out.print("제목 : ");
            String title = sc.nextLine();

            System.out.print("설명 : ");
            String description = sc.nextLine();

            System.out.print("일정 시작일 (YYYY-MM-DD) : ");
            String startDate = sc.nextLine();

            System.out.print("일정 종료일 (YYYY-MM-DD) : ");
            String endDate = sc.nextLine();

            System.out.print("일정 시작 시간 (HH:mm) : ");
            String startTime = sc.nextLine();

            System.out.print("일정 종료 시간 (HH:mm) : ");
            String endTime = sc.nextLine();

            System.out.print("일정 중요도(HIGH|MEDIUM|LOW) : ");
            String priority = sc.nextLine();

            switch (scheduleType) {
                case 1:
                    return createGeneralSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted);
                case 2:
                    return createMeetingSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted);
                case 3:
                    return createTaskSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted);
                case 4:
                    return createReminderSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted);
                default:
                    System.out.println("잘못된 일정 종류입니다.");
                    return null;
            }
        } catch (RuntimeException e) {
            System.out.println("입력 오류 : " + e.getMessage());
            return null;
        }
    }

    private ScheduleItem createGeneralSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        System.out.print("카테고리 : ");
        String category = sc.nextLine();

        System.out.print("장소 : ");
        String place = sc.nextLine();

        System.out.print("메모 : ");
        String memo = sc.nextLine();

        if (id == 0) {
            return new GeneralSchedule(title, description, startDate, endDate, startTime, endTime, priority, isCompleted, category, place, memo);
        }
        return new GeneralSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted, category, place, memo);
    }

    private ScheduleItem createMeetingSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        System.out.print("위치 : ");
        String location = sc.nextLine();

        System.out.print("참가자 : ");
        String participants = sc.nextLine();

        System.out.print("의제 : ");
        String agenda = sc.nextLine();

        System.out.print("호스트 : ");
        String host = sc.nextLine();

        if (id == 0) {
            return new MeetingSchedule(title, description, startDate, endDate, startTime, endTime, priority, isCompleted, location, participants, agenda, host);
        }
        return new MeetingSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted, location, participants, agenda, host);
    }

    private ScheduleItem createTaskSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        System.out.print("마감일 (YYYY-MM-DD) : ");
        String deadline = sc.nextLine();

        System.out.print("진행률(0~100) : ");
        int progress;
        try {
            progress = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new RuntimeException("진행률은 숫자로 입력해야 합니다.");
        }

        System.out.print("작업 상태(TODO|IN_PROGRESS|DONE) : ");
        String taskStatus = sc.nextLine();

        System.out.print("담당자 : ");
        String assignedTo = sc.nextLine();

        if (id == 0) {
            return new TaskSchedule(title, description, startDate, endDate, startTime, endTime, priority, isCompleted, deadline, progress, taskStatus, assignedTo);
        }
        return new TaskSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted, deadline, progress, taskStatus, assignedTo);
    }

    private ScheduleItem createReminderSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority, boolean isCompleted) {
        System.out.print("알림 시간 (HH:mm) : ");
        String reminderTime = sc.nextLine();

        System.out.print("알림 메시지 : ");
        String reminderMessage = sc.nextLine();

        System.out.print("알림 타입(POPUP|SOUND|MESSAGE) : ");
        String notificationType = sc.nextLine();

        if (id == 0) {
            return new ReminderSchedule(title, description, startDate, endDate, startTime, endTime, priority, isCompleted, reminderTime, reminderMessage, notificationType, false);
        }
        return new ReminderSchedule(id, title, description, startDate, endDate, startTime, endTime, priority, isCompleted, reminderTime, reminderMessage, notificationType, false);
    }

    public void displayAllSchedules() {
        if (scheduleItemList.isEmpty()) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }

        for (ScheduleItem scheduleItem : scheduleItemList) {
            scheduleItem.displayInfo();
            System.out.println();
        }
    }

    public void displayScheduleById(int id) {
        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getId() == id) {
                scheduleItem.displayInfo();
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void displayScheduleById() {
        System.out.print("조회할 id : ");
        int id = readInt();
        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        displayScheduleById(id);
    }

    public void updateSchedule() {
        System.out.print("수정할 Id : ");
        int id = readInt();
        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        for (int i = 0; i < scheduleItemList.size(); i++) {
            ScheduleItem currentSchedule = scheduleItemList.get(i);
            if (currentSchedule.getId() == id) {
                int scheduleType = getScheduleTypeNumber(currentSchedule);
                System.out.println("새 정보를 입력하세요.");
                ScheduleItem newScheduleItem = createSchedule(scheduleType, currentSchedule.getId(), currentSchedule.isCompleted());

                if (newScheduleItem == null) {
                    System.out.println("수정이 취소되었습니다.");
                    return;
                }

                if (hasConflict(newScheduleItem, i)) {
                    System.out.println("기존 일정과 시간이 겹쳐 수정할 수 없습니다.");
                    return;
                }

                scheduleItemList.set(i, newScheduleItem);
                System.out.println("수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void deleteSchedule() {
        System.out.print("삭제할 Id : ");
        int id = readInt();
        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        for (int i = 0; i < scheduleItemList.size(); i++) {
            if (scheduleItemList.get(i).getId() == id) {
                scheduleItemList.remove(i);
                System.out.println("삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void completeSchedule() {
        System.out.print("완료할 Id : ");
        int id = readInt();
        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getId() == id) {
                scheduleItem.markAsCompleted();
                System.out.println("완료 처리 되었습니다.");
                scheduleItem.displayInfo();
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void searchByTitle() {
        System.out.print("제목 입력 : ");
        String title = sc.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("검색어를 입력하세요.");
            return;
        }

        boolean found = false;

        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getTitle().contains(title)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    public void searchByDate() {
        LocalDate date = readDate("검색할 날짜(YYYY-MM-DD) : ");
        if (date == null) {
            return;
        }

        boolean found = false;
        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getStartDate().equals(date)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    public void searchByPriority() {
        System.out.print("우선순위 입력(HIGH|MEDIUM|LOW) : ");
        String priority = sc.nextLine().trim().toUpperCase();
        if (!isValidPriority(priority)) {
            System.out.println("우선순위는 HIGH, MEDIUM, LOW 중 하나여야 합니다.");
            return;
        }

        boolean found = false;

        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getPriority().equals(priority)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    public void sortByDate() {
        if (scheduleItemList.isEmpty()) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }

        scheduleItemList.sort(
                Comparator.comparing(ScheduleItem::getStartDate)
                        .thenComparing(ScheduleItem::getStartTime));

        displayAllSchedules();
    }

    public void sortByPriority() {
        if (scheduleItemList.isEmpty()) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }

        scheduleItemList.sort(
                Comparator.comparing(item -> getPriorityOrder(item.getPriority())));

        displayAllSchedules();
    }

    public void sortByCompletion() {
        if (scheduleItemList.isEmpty()) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }

        scheduleItemList.sort(
                Comparator.comparing(ScheduleItem::isCompleted));

        displayAllSchedules();
    }


    // 일정 충돌 확인
    public void checkConflict() {
        try {
            LocalDate startDate = readDate("시작일 (YYYY-MM-DD) : ");
            if (startDate == null) {
                return;
            }

            LocalDate endDate = readDate("종료일 (YYYY-MM-DD) : ");
            if (endDate == null) {
                return;
            }

            LocalTime startTime = readTime("시작 시간 (HH:mm) : ");
            if (startTime == null) {
                return;
            }

            LocalTime endTime = readTime("종료 시간 (HH:mm) : ");
            if (endTime == null) {
                return;
            }

            if (endDate.isBefore(startDate) || (endDate.equals(startDate) && endTime.isBefore(startTime))) {
                System.out.println("종료 일시는 시작 일시보다 빠를 수 없습니다.");
                return;
            }

            boolean found = false;
            for (ScheduleItem scheduleItem : scheduleItemList) {
                if (isOverlapped(startDate, endDate, startTime, endTime, scheduleItem)) {
                    printConflictSchedule(scheduleItem);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("충돌하는 일정이 없습니다.");
            }
        } catch (RuntimeException e) {
            System.out.println("입력 오류 : " + e.getMessage());
        }
    }

    public void runNotification() {
        System.out.print("알림 실행할 Id : ");
        int id = readInt();
        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getId() == id) {
                scheduleItem.notifyUser();
                scheduleItem.displayInfo();
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void saveToFile() {
        if (scheduleItemList.isEmpty()) {
            System.out.println("저장할 일정이 없습니다.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("schedules.txt"))) {
            for (ScheduleItem scheduleItem : scheduleItemList) {
                writer.println(scheduleItem.toFileString());
                writer.println("--------------------");
            }
            System.out.println("schedules.txt 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }

    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private LocalDate readDate(String message) {
        try {
            System.out.print(message);
            return LocalDate.parse(sc.nextLine());
        } catch (RuntimeException e) {
            System.out.println("날짜는 YYYY-MM-DD 형식이어야 합니다.");
            return null;
        }
    }

    private LocalTime readTime(String message) {
        try {
            System.out.print(message);
            return LocalTime.parse(sc.nextLine());
        } catch (RuntimeException e) {
            System.out.println("시간은 HH:mm 형식이어야 합니다.");
            return null;
        }
    }

    private boolean hasConflict(ScheduleItem target, int excludeIndex) {
        boolean conflicted = false;
        for (int i = 0; i < scheduleItemList.size(); i++) {
            ScheduleItem scheduleItem = scheduleItemList.get(i);
            if (i != excludeIndex && isOverlapped(target, scheduleItem)) {
                printConflictSchedule(scheduleItem);
                conflicted = true;
            }
        }
        return conflicted;
    }

    private boolean isOverlapped(ScheduleItem a, ScheduleItem b) {
        return isOverlapped(a.getStartDate(), a.getEndDate(), a.getStartTime(), a.getEndTime(), b);
    }

    private boolean isOverlapped(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, ScheduleItem scheduleItem) {
        LocalDateTime targetStart = LocalDateTime.of(startDate, startTime);
        LocalDateTime targetEnd = LocalDateTime.of(endDate, endTime);
        LocalDateTime itemStart = LocalDateTime.of(scheduleItem.getStartDate(), scheduleItem.getStartTime());
        LocalDateTime itemEnd = LocalDateTime.of(scheduleItem.getEndDate(), scheduleItem.getEndTime());

        return targetStart.isBefore(itemEnd) && itemStart.isBefore(targetEnd);
    }

    private void printConflictSchedule(ScheduleItem scheduleItem) {
        System.out.println("충돌 일정 id : " + scheduleItem.getId());
        System.out.println("충돌 일정 제목 : " + scheduleItem.getTitle());
        System.out.println("충돌 일정 시작일 : " + scheduleItem.getStartDate());
        System.out.println("충돌 일정 시작시간 : " + scheduleItem.getStartTime());
        System.out.println("충돌 일정 종료일 : " + scheduleItem.getEndDate());
        System.out.println("충돌 일정 종료시간 : " + scheduleItem.getEndTime());
        System.out.println();
    }

    private int getPriorityOrder(String priority) {
        if (priority.equals("HIGH")) {
            return 1;
        } else if (priority.equals("MEDIUM")) {
            return 2;
        } else if (priority.equals("LOW")) {
            return 3;
        }
        return 4;
    }

    private boolean isValidPriority(String priority) {
        return priority.equals("HIGH") || priority.equals("MEDIUM") || priority.equals("LOW");
    }

    private int getScheduleTypeNumber(ScheduleItem scheduleItem) {
        if (scheduleItem instanceof GeneralSchedule) {
            return 1;
        } else if (scheduleItem instanceof MeetingSchedule) {
            return 2;
        } else if (scheduleItem instanceof TaskSchedule) {
            return 3;
        } else if (scheduleItem instanceof ReminderSchedule) {
            return 4;
        }
        return 0;
    }

}
