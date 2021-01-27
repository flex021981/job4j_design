import java.util.*;
import java.util.stream.Collectors;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info(0, 0, 0, 0);
        Map<Integer, User> currentMap = current
                .stream()
                .collect(Collectors.toMap(user -> user.id, user -> user));

        Iterator<User> it = previous.iterator();
        User user = null;
        while (it.hasNext()) {
            user = it.next();
            if (currentMap.containsKey(user.id) && !currentMap.get(user.id).equals(user)) {
                rsl.changed++;
            }
            if (currentMap.containsKey(user.id) && currentMap.get(user.id).equals(user)) {
                rsl.old++;
            }
        }
        rsl.added = current.size() - (rsl.old + rsl.changed);
        rsl.deleted = previous.size() - (rsl.old + rsl.changed);
        return rsl;
    }

    public static class Info {
        private int added;      //Сколько добавлено новых пользователей.
        private int changed;    //Сколько изменено пользователей.
        //Изменённым считается объект в котором изменилось имя. а id осталось прежним.
        private int deleted;    //Сколько удалено пользователей.
        private int old;    //Сколько пользователей без изменений.

        public Info(int added, int changed, int deleted, int old) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
            this.old = old;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User() {
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
