package map;

import java.text.SimpleDateFormat;
import java.util.*;

class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd");
        return "User{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", birthday=" + sdf.format(birthday.getTime()) +
                '}';
    }
}

public class Users {
    private final Map<User, Object> users;

    public Users() {
        users = new HashMap<>();
    }

    public void add(User user, Object object) {
        users.put(user, object);
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

    public static void main(String[] args) {

        Users users = new Users();
        User userIvan = new User(
                "Ivan",
                2,
                new GregorianCalendar(1980, Calendar.AUGUST, 20)
        );
        User userNikolay = new User(
                "Nikolay",
                1,
                new GregorianCalendar(1990, Calendar.JANUARY, 10)
        );
        users.add(userIvan, new Object());
        users.add(userNikolay, new Object());
        System.out.println(users);
    }
}



