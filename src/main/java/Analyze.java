import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info(0, 0, 0);
        rsl.added = count(current, previous);
        rsl.deleted = count(previous, current);

        for (User userCurrent : current) {
            for (User userPrevious : previous) {
                if (userPrevious.id == userCurrent.id
                        && !Objects.equals(userPrevious.name, userCurrent.name)) {
                    rsl.changed++;
                }
            }
        }
        return rsl;
    }

    private int count(List<User> previous, List<User> current) {
        int rsl = 0;
        ListIterator<User> it = previous.listIterator();
        User user = null;
        while (it.hasNext()) {
            user = it.next();
            if (!current.contains(user)) {
                rsl++;
            }
        }
        return rsl;
    }

    public static class Info {
        private int added;      //Сколько добавлено новых пользователей.
        private int changed;    //Сколько изменено пользователей.
        //Изменённым считается объект в котором изменилось имя. а id осталось прежним.
        private int deleted;    //Сколько удалено пользователей.

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
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
