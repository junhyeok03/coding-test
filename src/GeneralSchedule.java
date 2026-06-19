public class GeneralSchedule extends ScheduleItem {

    private String category;
    private String place;
    private String memo;


    public GeneralSchedule(String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String category, String place, String memo) {
        super(title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.category = category;
        this.place = place;
        this.memo = memo;
    }

    public GeneralSchedule(int id, String title, String description, String startDate, String endDate, String startTime, String endTime, String prioeity, boolean isCompleted, String category, String place, String memo) {
        super(id, title, description, startDate, endDate, startTime, endTime, prioeity, isCompleted);
        this.category = category;
        this.place = place;
        this.memo = memo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("카테고리 : " + category);
        System.out.println("장소 : " + place);
        System.out.println("메모 : " + memo);
    }

    @Override
    public String getScheduleType() {
        return "GeneralSchedule";
    }
}
