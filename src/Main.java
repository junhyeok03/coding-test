import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserManager userManager = new UserManager(sc);
        ScheduleManager scheduleManager = new ScheduleManager(sc, userManager);

        while (true) {
            System.out.println("=== 일정 관리 ===");
            System.out.println("1. 사용자 등록");
            System.out.println("2. 전체 사용자 조회");
            System.out.println("3. 사용자 상세 조회");
            System.out.println("4. 사용자 수정");
            System.out.println("5. 사용자 삭제");
            System.out.println("6. 일정 등록");
            System.out.println("7. 전체 일정 조회");
            System.out.println("8. 사용자별 일정 조회");
            System.out.println("9. 일정 상세 조회");
            System.out.println("10. 일정 수정");
            System.out.println("11. 일정 삭제");
            System.out.println("12. 일정 완료 처리");
            System.out.println("13. 제목 검색");
            System.out.println("14. 날짜 검색");
            System.out.println("15. 중요도 검색");
            System.out.println("16. 사용자별 제목 검색");
            System.out.println("17. 사용자별 날짜 검색");
            System.out.println("18. 사용자별 중요도 검색");
            System.out.println("19. 날짜순 정렬");
            System.out.println("20. 중요도순 정렬");
            System.out.println("21. 완료 여부순 정렬");
            System.out.println("22. 사용자별 날짜순 정렬");
            System.out.println("23. 사용자별 중요도순 정렬");
            System.out.println("24. 사용자별 완료 여부순 정렬");
            System.out.println("25. 알림 실행");
            System.out.println("26. 프로그램 종료");
            System.out.print("선택 : ");

            int choice = readInt(sc);
            if (choice == -1) {
                System.out.println("숫자를 입력하세요.");
                continue;
            }

            switch (choice) {
                case 1:
                    userManager.addUser();
                    break;
                case 2:
                    userManager.displayAllUsers();
                    break;
                case 3:
                    userManager.displayUserById();
                    break;
                case 4:
                    userManager.updateUser();
                    break;
                case 5:
                    userManager.deleteUser(scheduleManager.getScheduleItemList());
                    break;
                case 6:
                    scheduleManager.addSchedule();
                    break;
                case 7:
                    scheduleManager.displayAllSchedules();
                    break;
                case 8:
                    userManager.displaySchedulesByUserId(scheduleManager.getScheduleItemList());
                    break;
                case 9:
                    scheduleManager.displayScheduleById();
                    break;
                case 10:
                    scheduleManager.updateSchedule();
                    break;
                case 11:
                    scheduleManager.deleteSchedule();
                    break;
                case 12:
                    scheduleManager.completeSchedule();
                    break;
                case 13:
                    scheduleManager.searchByTitle();
                    break;
                case 14:
                    scheduleManager.searchByDate();
                    break;
                case 15:
                    scheduleManager.searchByPriority();
                    break;
                case 16:
                    userManager.searchByUserAndTitle(scheduleManager.getScheduleItemList());
                    break;
                case 17:
                    userManager.searchByUserAndDate(scheduleManager.getScheduleItemList());
                    break;
                case 18:
                    userManager.searchByUserAndPriority(scheduleManager.getScheduleItemList());
                    break;
                case 19:
                    scheduleManager.sortByDate();
                    break;
                case 20:
                    scheduleManager.sortByPriority();
                    break;
                case 21:
                    scheduleManager.sortByCompletion();
                    break;
                case 22:
                    userManager.sortByUserAndDate(scheduleManager.getScheduleItemList());
                    break;
                case 23:
                    userManager.sortByUserAndPriority(scheduleManager.getScheduleItemList());
                    break;
                case 24:
                    userManager.sortByUserAndCompletion(scheduleManager.getScheduleItemList());
                    break;
                case 25:
                    scheduleManager.runNotification();
                    break;
                case 26:
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
