import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScheduleManager scheduleManager = new ScheduleManager(sc);

        while (true) {
            System.out.println("=== 일정 관리 ===");
            System.out.println("1. 일정 등록");
            System.out.println("2. 전체 일정 조회");
            System.out.println("3. 일정 상세 조회");
            System.out.println("4. 일정 수정");
            System.out.println("5. 일정 삭제");
            System.out.println("6. 일정 완료 처리");
            System.out.println("7. 제목 검색");
            System.out.println("8. 날짜 검색");
            System.out.println("9. 중요도 검색");
            System.out.println("10. 날짜순 정렬");
            System.out.println("11. 중요도순 정렬");
            System.out.println("12. 완료 여부순 정렬");
            System.out.println("13. 일정 충돌 확인");
            System.out.println("14. 알림 실행");
            System.out.println("15. 프로그램 종료");
            System.out.print("선택 : ");

            int choice = readInt(sc);
            if (choice == -1) {
                System.out.println("숫자를 입력하세요.");
                continue;
            }

            switch (choice) {
                case 1:
                    scheduleManager.addSchedule();
                    break;
                case 2:
                    scheduleManager.displayAllSchedules();
                    break;
                case 3:
                    scheduleManager.displayScheduleById();
                    break;
                case 4:
                    scheduleManager.updateSchedule();
                    break;
                case 5:
                    scheduleManager.deleteSchedule();
                    break;
                case 6:
                    scheduleManager.completeSchedule();
                    break;
                case 7:
                    scheduleManager.searchByTitle();
                    break;
                case 8:
                    scheduleManager.searchByDate();
                    break;
                case 9:
                    scheduleManager.searchByPriority();
                    break;
                case 10:
                    scheduleManager.sortByDate();
                    break;
                case 11:
                    scheduleManager.sortByPriority();
                    break;
                case 12:
                    scheduleManager.sortByCompletion();
                    break;
                case 13:
                    scheduleManager.checkConflict();
                    break;
                case 14:
                    scheduleManager.runNotification();
                    break;
                case 15:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }


    // 실수입력 방지
    private static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
