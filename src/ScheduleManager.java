import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScheduleManager {
    ScheduleItem[] scheduleItemList = new ScheduleItem[100]; // 배열로 고치고 나중에 써보기.
    int count = 0;


    public ScheduleItem[] getAllSchedule() { // 검섹
        displayAll();
        return scheduleItemList;
    }

    public ScheduleItem[] search() { // 조회
        return scheduleItemList;
    }

    public ScheduleItem[] sortResult() { // 정렬
        return scheduleItemList;
    }

    public void displayAll() {
        for (int i = 0; i < count; i++) {
            scheduleItemList[i].displayInfo();
        }
    }

    public void addSchedule() {
        Scanner sc = new Scanner(System.in);

        System.out.println("일정 종류를 선택하시오!");
        System.out.print("1.일반 2.회의 3.할일 4.알림 : ");
        int num = sc.nextInt();
        sc.nextLine();

        System.out.print("제목 : ");
        String title = sc.nextLine();

        System.out.print("설명 : ");
        String description = sc.nextLine();

        System.out.print("일정 시작일 (YYYY-MM-DD) :");
        String startDate = sc.nextLine();


        System.out.print("일정 종료일 (YYYY-MM-DD) :");
        String endDate = sc.nextLine();

        System.out.print("일정 시작 시간 (HH:MM) ");
        String startTime = sc.nextLine();

        System.out.print("일정 종료 시간 (HH:MM) : ");
        String endTime = sc.nextLine();

        System.out.print("일정 중요도 :");
        String priority = sc.nextLine();


        switch (num) {
            case 1:
                System.out.print("카테고리 : ");
                String category = sc.nextLine();

                System.out.print("장소 : ");
                String place = sc.nextLine();

                System.out.print("메모 : ");
                String memo = sc.nextLine();
                break;
            case 2:
                System.out.println("위치 : ");
                String location = sc.nextLine();

                System.out.println("참가자 : ");
                String participants = sc.nextLine();

                System.out.println("의제 : ");
                String agenda = sc.nextLine();

                System.out.println("호스트 : ");
                String host = sc.nextLine();
            case 3:
                System.out.println("사선 : ");
                String deadline = sc.nextLine();

                System.out.println("과정 : ");
                String progress = sc.nextLine();

                System.out.println("작업 상태 : ");
                String taskStatus = sc.nextLine();


                System.out.println("할당된 : ");
                String assignedTo = sc.nextLine();
            case 4:
                System.out.println("알림 시간 : ");
                String reminderTime = sc.nextLine();
                System.out.println("알림 메시지 : ");
                String reminderMessage = sc.nextLine();
                System.out.println("알림 타입 : ");
                String notificationType = sc.nextLine();
        }
    }

    public void displayAllSchedules() {
        for (int i = 0; i < count; i++) {
            scheduleItemList[i].displayInfo();
            System.out.println();
        }
    }

    public void displayScheduleById(int id) {
        for(int i = 0; i < count; i++) {
            if(scheduleItemList[i].getId() == id) {
                scheduleItemList[i].displayInfo();
            }
        }
    }

    public void updateSchedule(int id) {
        for(int i = 0; i < count; i++) {
            if(scheduleItemList[i].getId() == id) {

            }
        }
    }

    public void deleteSchedule() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 Id : ");
        int id = sc.nextInt();
        for(int i = 0; i < count; i++) {
            if(scheduleItemList[i].getId() == id) {
                for(int j = i; j < count - 1; j++) {
                    scheduleItemList[j] = scheduleItemList[j+1];
                }
                count--;
                System.out.println("삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 id가 존재하지 않습니다.");
    }
}

