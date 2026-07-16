import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private List<User> userList = new ArrayList<>();
    private Scanner sc;

    public UserManager(Scanner sc) {
        this.sc = sc;
    }

    // 사용자 등록
    public void addUser() {
        System.out.print("사용자 이름 : ");
        String name = sc.nextLine();

        System.out.print("이메일 : ");
        String email = sc.nextLine();

        if (isDuplicateEmail(email)) {
            System.out.println("이미 등록된 이메일입니다.");
            return;
        }

        try {
            User user = new User(name, email);
            userList.add(user);
            System.out.println("사용자가 등록되었습니다.");
        } catch (RuntimeException e) {
            System.out.println("입력 오류 : " + e.getMessage());
        }
    }


    // 중복 메일
    private boolean isDuplicateEmail(String email) {
        return isDuplicateEmail(email, -1);
    }

    private boolean isDuplicateEmail(String email, int excludedUserId) {
        for (User user : userList) {
            if (user.getId() != excludedUserId && user.getEmail().equals(email.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean existsUser(int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return true;
            }
        }
        return false;
    }


    // 전체 사용자 조회
    public void displayAllUsers() {
        if (userList.isEmpty()) {
            System.out.print("등록된 사용자가 없습니다");
            return;
        }
        for (User user : userList) {
            user.displayInfo();
            System.out.println();
        }
    }


    // 사용자 상세 조회
    public void displayUserById() {
        System.out.print("조회할 사용자 id : ");
        int id = readInt();
        for (User user : userList) {
            if (user.getId() == id) {
                user.displayInfo();
                return;
            }
        }
        System.out.println("해당 사용자가 존재하지 않습니다");
    }

    // 사용자 수정
    public void updateUser() {
        System.out.print("수정할 사용자 id : ");
        int id = readInt();

        if (id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        User targetUser = null;

        for(User user : userList) {
            if(user.getId() == id) {
                targetUser = user;
                break;
            }
        }

        if(targetUser == null) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        System.out.print("새 사용자 이름 : ");
        String name = sc.nextLine();

        System.out.print("새 이메일 : ");
        String email = sc.nextLine();

        if (isDuplicateEmail(email, targetUser.getId())) {
            System.out.println("이미 등록된 이메일입니다.");
            return;
        }

        try {
            targetUser.updateUser(name, email);
            System.out.println("사용자 정보가 수정되었습니다.");
        } catch (RuntimeException e) {
            System.out.println("입력 오류 : " + e.getMessage());
        }
    }

    public void deleteUser(List<ScheduleItem> scheduleItemList) {
        System.out.print("삭제할 Id : ");
        int id = readInt();
        if(id == -1) {
            System.out.println("id는 숫자로 입력해야 합니다.");
            return;
        }

        if (!existsUser(id)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        for (ScheduleItem scheduleItem : scheduleItemList) {
            if (scheduleItem.getUserId() == id) {
                System.out.println("사용자가 소유한 일정이 있어 삭제할 수 없습니다.");
                return;
            }

            if (scheduleItem instanceof MeetingSchedule) {
                MeetingSchedule meetingSchedule = (MeetingSchedule) scheduleItem;
                if (meetingSchedule.getHostUserId() == id) {
                    System.out.println("사용자가 회의 주최자로 등록되어 있어 삭제할 수 없습니다.");
                    return;
                }
                if (meetingSchedule.getParticipantUserIds().contains(id)) {
                    System.out.println("사용자가 회의 참여자로 등록되어 있어 삭제할 수 없습니다.");
                    return;
                }
            }
        }

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == id) {
                userList.remove(i);
                System.out.println("사용자가 삭제되었습니다.");
                return;
            }
        }
    }

    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    // 사용자별 일정 조회
    public void displaySchedulesByUserId(List<ScheduleItem> scheduleItemList) {
        System.out.print("조회할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        boolean existsUser = false;
        for(User user : userList) {
            if(user.getId() == userId) {
                existsUser = true;
                break;
            }
        }

        if(!existsUser) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        boolean found = false;
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("해당 사용자의 일정이 없습니다.");
        }
    }

    // 사용자별 제목 검색
    public void searchByUserAndTitle(List<ScheduleItem> scheduleItemList) {
        System.out.print("검색할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        System.out.print("검색할 제목 : ");
        String title = sc.nextLine();
        if(title.trim().isEmpty()) {
            System.out.println("검색어를 입력하세요.");
            return;
        }

        boolean found = false;
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId && scheduleItem.getTitle().contains(title)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    // 사용자별 날짜 검색
    public void searchByUserAndDate(List<ScheduleItem> scheduleItemList) {
        System.out.print("검색할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        LocalDate startDate = readDate("검색할 날짜(YYYY-MM-DD) : ");
        if(startDate == null) {
            return;
        }

        boolean found = false;
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId && scheduleItem.getStartDate().equals(startDate)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    // 사용자별 중요도 검색
    public void searchByUserAndPriority(List<ScheduleItem> scheduleItemList) {
        System.out.print("검색할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        System.out.print("검색할 중요도(HIGH|MEDIUM|LOW) : ");
        String priority = sc.nextLine().trim().toUpperCase();
        if(!isValidPriority(priority)) {
            System.out.println("우선순위는 HIGH, MEDIUM, LOW 중 하나여야 합니다.");
            return;
        }

        boolean found = false;
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId && scheduleItem.getPriority().equals(priority)) {
                scheduleItem.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    private boolean isValidPriority(String priority) {
        return priority.equals("HIGH") || priority.equals("MEDIUM") || priority.equals("LOW");
    }

    // 사용자별 날짜순 정렬
    public void sortByUserAndDate(List<ScheduleItem> scheduleItemList) {
        System.out.print("정렬할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        List<ScheduleItem> userScheduleList = new ArrayList<>();
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId) {
                userScheduleList.add(scheduleItem);
            }
        }

        if(userScheduleList.isEmpty()) {
            System.out.println("해당 사용자의 일정이 없습니다.");
            return;
        }

        userScheduleList.sort(
                Comparator.comparing(ScheduleItem::getStartDate)
                        .thenComparing(ScheduleItem::getStartTime));

        for(ScheduleItem scheduleItem : userScheduleList) {
            scheduleItem.displayInfo();
            System.out.println();
        }
    }

    // 사용자별 중요도순 정렬
    public void sortByUserAndPriority(List<ScheduleItem> scheduleItemList) {
        System.out.print("정렬할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        List<ScheduleItem> userScheduleList = new ArrayList<>();
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId) {
                userScheduleList.add(scheduleItem);
            }
        }

        if(userScheduleList.isEmpty()) {
            System.out.println("해당 사용자의 일정이 없습니다.");
            return;
        }

        userScheduleList.sort(
                Comparator.comparing(scheduleItem -> getPriorityOrder(scheduleItem.getPriority())));

        for(ScheduleItem scheduleItem : userScheduleList) {
            scheduleItem.displayInfo();
            System.out.println();
        }
    }

    private int getPriorityOrder(String priority) {
        if(priority.equals("HIGH")) {
            return 1;
        } else if(priority.equals("MEDIUM")) {
            return 2;
        } else if(priority.equals("LOW")) {
            return 3;
        }
        return 4;
    }

    // 사용자별 완료 여부순 정렬
    public void sortByUserAndCompletion(List<ScheduleItem> scheduleItemList) {
        System.out.print("정렬할 사용자 id : ");
        int userId = readInt();
        if(userId == -1) {
            System.out.println("userId는 숫자로 입력해야 합니다.");
            return;
        }

        if(!existsUser(userId)) {
            System.out.println("해당 사용자가 존재하지 않습니다.");
            return;
        }

        List<ScheduleItem> userScheduleList = new ArrayList<>();
        for(ScheduleItem scheduleItem : scheduleItemList) {
            if(scheduleItem.getUserId() == userId) {
                userScheduleList.add(scheduleItem);
            }
        }

        if(userScheduleList.isEmpty()) {
            System.out.println("해당 사용자의 일정이 없습니다.");
            return;
        }

        userScheduleList.sort(Comparator.comparing(ScheduleItem::isCompleted));

        for(ScheduleItem scheduleItem : userScheduleList) {
            scheduleItem.displayInfo();
            System.out.println();
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

}
