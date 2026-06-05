import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScheduleManager scheduleManager = new ScheduleManager(sc);

        while (true) {
            System.out.println("=== 일정 종류 선택 ===");
            System.out.println("1. 일반 일정");
            System.out.println("2. 회의 일정");
            System.out.println("3. 할일 일정");
            System.out.println("4. 알림 일정");
            System.out.println("0. 종료");
            System.out.print("선택 : ");

            int scheduleType = sc.nextInt();
            sc.nextLine();

            if (scheduleType == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (scheduleType < 1 || scheduleType > 4) {
                System.out.println("잘못된 선택입니다.");
                continue;
            }

            while (true) {
                System.out.println("=== 기능 선택 ===");
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
                System.out.println("14. 일정 실행");
                System.out.println("0. 뒤로가기");
                System.out.print("선택 : ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        scheduleManager.addSchedule(scheduleType);
                        break;
                    case 2:
                        scheduleManager.displayAllSchedules();
                        break;
                    case 3:
                        System.out.println("조회할 id : ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        scheduleManager.displayScheduleById(scheduleType, id);
                        break;
                    case 4:
                        scheduleManager.updateSchedule(scheduleType);
                        break;
                    case 5:
                        scheduleManager.deleteSchedule(scheduleType);
                        break;
                    case 6:
                        scheduleManager.completeSchedule(scheduleType);
                        break;
                    case 7:
                        scheduleManager.searchByTitle(scheduleType);
                        break;
                    case 8:
                        scheduleManager.searchByDate(scheduleType);
                        break;
                    case 9:
                        scheduleManager.searchByPriority(scheduleType);
                        break;
                    case 10:
                        scheduleManager.sortByDate(scheduleType);
                        break;
                    case 11:
                        scheduleManager.sortByPriority(scheduleType);
                        break;
                    case 12:
                        scheduleManager.sortByCompletion(scheduleType);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("잘못된 선택입니다.");
                }

                if (choice == 0) {
                    break;
                }
            }
        }


    }


}
