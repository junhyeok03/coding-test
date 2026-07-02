import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private List<User> userList = new ArrayList<>();
    private Scanner sc;


    private boolean isDuplicateEmail(String email) {
        for(User user : userList) {
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
