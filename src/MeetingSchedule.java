import java.util.ArrayList;
import java.util.List;

public class MeetingSchedule extends ScheduleItem {

    private String location;
    private List<Integer> participantUserIds;
    private String agenda;
    private int hostUserId;


    public MeetingSchedule(int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String location, List<Integer> participantUserIds, String agenda, int hostUserId) {
        super(userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.location = location;
        this.participantUserIds = new ArrayList<>(participantUserIds);
        this.agenda = agenda;
        this.hostUserId = hostUserId;
    }

    public MeetingSchedule(int id, int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String location, List<Integer> participantUserIds, String agenda, int hostUserId) {
        super(id, userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.location = location;
        this.participantUserIds = new ArrayList<>(participantUserIds);
        this.agenda = agenda;
        this.hostUserId = hostUserId;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("위치 : " + location);
        System.out.println("참가자 userId 목록 : " + participantUserIds);
        System.out.println("의제 : " + agenda);
        System.out.println("호스트 userId : " + hostUserId);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "\n"
                + "위치 : " + location + "\n"
                + "참가자 userId 목록 : " + participantUserIds + "\n"
                + "의제 : " + agenda + "\n"
                + "호스트 userId : " + hostUserId;
    }

    public List<Integer> getParticipantUserIds() {
        return participantUserIds;
    }

    public int getHostUserId() {
        return hostUserId;
    }

    @Override
    public String getScheduleType() {
        return "MeetingSchedule";
    }
}
