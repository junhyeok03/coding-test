import java.time.LocalDateTime;

public class User {
    private static int nextId = 1;

    private final int id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public User(String name, String email) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateUser(String name, String email) {
        validateName(name);
        validateEmail(email);

        this.name = name;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    private void validateName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new RuntimeException("이름은 비어 있을 수 없습니다.");
        }
    }

    private void validateEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            throw new RuntimeException("이메일은 비어 있을 수 없습니다.");
        }
        if(!email.contains("@") || !email.contains(".")) {
            throw new RuntimeException("이메일 형식이 올바르지 않습니다.");
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void displayInfo() {
        System.out.println("사용자 id : " + id);
        System.out.println("이름 : " + name);
        System.out.println("이메일 : " + email);
        System.out.println("생성일시 : " +  createdAt);
        System.out.println("수정일시 : " + updatedAt);
    }
}
