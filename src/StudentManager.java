import java.util.Hashtable;

public class StudentManager {
    public static Hashtable<String, Student> list = new Hashtable<String, Student>() {
        {
            put("1", new Student("1", "Nguyen Thanh Tung", "K61 CA CLC 2", "0123456789", "tung@gmail.com", ""));
            put("2", new Student("2", "Le Trung Nam Nhat", "K61 CA CLC 2", "0987654321", "nhat@gmail.com", ""));
            put("3", new Student("3", "Nguyen Tuan Quang", "K61 CA CLC 2", "0123456789", "quang@gmail.com", ""));
        }
    };
}
