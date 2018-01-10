package romeu.julia.signupactivity;

/**
 * Created by juliaromeu on 10/1/18.
 */

public class User {
    String userId;
    String userName;
    int userAge;
    int userLevel;

    public User(){

    }

    public User(String userId, String userName, int userAge, int userLevel) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userLevel = userLevel;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public int getUserLevel() {
        return userLevel;
    }
}
