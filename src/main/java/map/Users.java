package map;

import java.text.SimpleDateFormat;
import java.util.*;

final class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd");
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + sdf.format(birthday.getTime())
                + '}';
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
        return "Users{" + "users=" + users + '}';
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
        User userNikolayCopy = new User(
                "Nikolay",
                1,
                new GregorianCalendar(1990, Calendar.JANUARY, 10)
        );
        users.add(userIvan, new Object());
        users.add(userNikolay, new Object());
        users.add(userNikolayCopy, new Object());
        System.out.println(users);
        System.out.println(userNikolay.hashCode());
        System.out.println(userNikolayCopy.hashCode());
        System.out.println(userNikolay.equals(userNikolayCopy));
    }
}



