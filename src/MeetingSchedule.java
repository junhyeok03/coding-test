public class MeetingSchedule extends ScheduleItem {

    private String location;
    private String participants;
    private String agenda;
    private String host;


    public MeetingSchedule(int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String location, String participants, String agenda, String host) {
        super(userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.location = location;
        this.participants = participants;
        this.agenda = agenda;
        this.host = host;
    }

    public MeetingSchedule(int id, int userId, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String location, String participants, String agenda, String host) {
        super(id, userId, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.location = location;
        this.participants = participants;
        this.agenda = agenda;
        this.host = host;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("위치 : " + location);
        System.out.println("참가자 : " + participants);
        System.out.println("의제 : " + agenda);
        System.out.println("호스트 : " + host);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "\n"
                + "위치 : " + location + "\n"
                + "참가자 : " + participants + "\n"
                + "의제 : " + agenda + "\n"
                + "호스트 : " + host;
    }

    @Override
    public String getScheduleType() {
        return "MeetingSchedule";
    }
}
