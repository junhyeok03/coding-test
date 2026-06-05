import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ScheduleManager {
    ScheduleItem[] scheduleItemList = new ScheduleItem[100];
    int count = 0;
    private final Scanner sc;

    public ScheduleManager(Scanner sc) {
        this.sc = sc;
    }

    public void addSchedule(int scheduleType) {
        if (count >= scheduleItemList.length) {
            System.out.println("더 이상 일정을 등록할 수 없습니다.");
            return;
        }

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
                addGeneralSchedule(sc, title, description, startDate, endDate, startTime, endTime, priority);
                break;
            case 2:
                addMeetingSchedule(sc, title, description, startDate, endDate, startTime, endTime, priority);
                break;
            case 3:
                addTaskSchedule(sc, title, description, startDate, endDate, startTime, endTime, priority);
                break;
            case 4:
                addReminderSchedule(sc, title, description, startDate, endDate, startTime, endTime, priority);
                break;
            default:
                System.out.println("잘못된 일정 종류입니다.");
        }
    }

    private void addGeneralSchedule(Scanner sc, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority) {
        System.out.print("카테고리 : ");
        String category = sc.nextLine();

        System.out.print("장소 : ");
        String place = sc.nextLine();

        System.out.print("메모 : ");
        String memo = sc.nextLine();

        scheduleItemList[count++] = new GeneralSchedule(title, description, startDate, endDate, startTime, endTime, priority, false, category, place, memo);
        System.out.println("일정이 등록되었습니다.");
    }

    private void addMeetingSchedule(Scanner sc, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority) {
        System.out.print("위치 : ");
        String location = sc.nextLine();

        System.out.print("참가자 : ");
        String participants = sc.nextLine();

        System.out.print("의제 : ");
        String agenda = sc.nextLine();

        System.out.print("호스트 : ");
        String host = sc.nextLine();

        scheduleItemList[count++] = new MeetingSchedule(title, description, startDate, endDate, startTime, endTime, priority, false, location, participants, agenda, host);
        System.out.println("일정이 등록되었습니다.");
    }

    private void addTaskSchedule(Scanner sc, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority) {
        System.out.print("마감일 (YYYY-MM-DD) : ");
        String deadline = sc.nextLine();

        System.out.print("진행률(0~100) : ");
        int progress = Integer.parseInt(sc.nextLine());

        System.out.print("작업 상태(TODO|IN_PROGRESS|DONE) : ");
        String taskStatus = sc.nextLine();

        System.out.print("담당자 : ");
        String assignedTo = sc.nextLine();

        scheduleItemList[count++] = new TaskSchedule(title, description, startDate, endDate, startTime, endTime, priority, false, deadline, progress, taskStatus, assignedTo);
        System.out.println("일정이 등록되었습니다.");
    }

    private void addReminderSchedule(Scanner sc, String title, String description, String startDate, String endDate, String startTime, String endTime, String priority) {
        System.out.print("알림 시간 (HH:MM) : ");
        String reminderTime = sc.nextLine();

        System.out.print("알림 메시지 : ");
        String reminderMessage = sc.nextLine();

        System.out.print("알림 타입(POPUP|SOUND|MESSAGE) : ");
        String notificationType = sc.nextLine();

        scheduleItemList[count++] = new ReminderSchedule(title, description, startDate, endDate, startTime, endTime, priority, false, reminderTime, reminderMessage, notificationType, false);
        System.out.println("일정이 등록되었습니다.");
    }

    public void displayAllSchedules(int scheduleType) {
        for (int i = 0; i < count; i++) {
            if (isSameScheduleType(scheduleItemList[i], scheduleType)) {
                scheduleItemList[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void displayAllSchedules() {
        for (int i = 0; i < count; i++) {
            scheduleItemList[i].displayInfo();
            System.out.println();
        }
    }

    public void displayScheduleById(int scheduleType, int id) {
        for (int i = 0; i < count; i++) {
            if (scheduleItemList[i].getId() == id && isSameScheduleType(scheduleItemList[i], scheduleType)) {
                scheduleItemList[i].displayInfo();
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void updateSchedule(int scheduleType) {
        System.out.print("수정할 Id : ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (scheduleItemList[i].getId() == id && isSameScheduleType(scheduleItemList[i], scheduleType)) {
                removeSchedule(i);
                System.out.println("새 정보를 입력하세요.");
                addSchedule(scheduleType);
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void deleteSchedule(int scheduleType) {
        System.out.print("삭제할 Id : ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (scheduleItemList[i].getId() == id && isSameScheduleType(scheduleItemList[i], scheduleType)) {
                removeSchedule(i);
                System.out.println("삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void completeSchedule(int scheduleType) {
        System.out.print("완료할 Id : ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (scheduleItemList[i].getId() == id && isSameScheduleType(scheduleItemList[i], scheduleType)) {
                scheduleItemList[i].markAsCompleted();
                System.out.println("완료 처리 되었습니다.");
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }

    public void searchByTitle(int scheduleType) {
        System.out.print("제목 입력 : ");
        String title = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (isSameScheduleType(scheduleItemList[i], scheduleType) && scheduleItemList[i].getTitle().contains(title)) {
                scheduleItemList[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void searchByDate(int scheduleType) {
        System.out.print("검색할 날짜(YYYY-MM-DD) : ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        for (int i = 0; i < count; i++) {
            if (isSameScheduleType(scheduleItemList[i], scheduleType) && scheduleItemList[i].getStartDate().equals(date)) {
                scheduleItemList[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void searchByPriority(int scheduleType) {
        System.out.print("우선순위 입력(HIGH|MEDIUM|LOW) : ");
        String priority = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (isSameScheduleType(scheduleItemList[i], scheduleType) && scheduleItemList[i].getPriority().equals(priority)) {
                scheduleItemList[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void sortByDate(int scheduleType) {
        Arrays.sort(scheduleItemList, 0, count,
                Comparator.comparing(ScheduleItem::getStartDate)
                        .thenComparing(ScheduleItem::getStartTime));

        displayAllSchedules(scheduleType);
    }

    public void sortByPriority(int scheduleType) {
        Arrays.sort(scheduleItemList, 0, count,
                Comparator.comparing(item -> getPriorityOrder(item.getPriority())));

        displayAllSchedules(scheduleType);
    }

    public void sortByCompletion(int scheduleType) {
        Arrays.sort(scheduleItemList, 0, count,
                Comparator.comparing(ScheduleItem::isCompleted));

        displayAllSchedules(scheduleType);
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

    private boolean isSameScheduleType(ScheduleItem scheduleItem, int scheduleType) {
        switch (scheduleType) {
            case 1:
                return scheduleItem instanceof GeneralSchedule;
            case 2:
                return scheduleItem instanceof MeetingSchedule;
            case 3:
                return scheduleItem instanceof TaskSchedule;
            case 4:
                return scheduleItem instanceof ReminderSchedule;
            default:
                return false;
        }
    }

    private void removeSchedule(int index) {
        for (int i = index; i < count - 1; i++) {
            scheduleItemList[i] = scheduleItemList[i + 1];
        }
        scheduleItemList[count - 1] = null;
        count--;
    }
}
