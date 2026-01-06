import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentService {
    private Map<String, Student> students = new ConcurrentHashMap<>();

    public String register(String name, String city) {
        String id = UUID.randomUUID().toString();
        Student student = new Student(id, name, city);
        students.put(id, student);
        return id;
    }
    
    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }
    
    public Collection<Student> findAll() {
        return students.values();
    }
    
    public Collection<Student> findByName(String name) {
        if (name == null || name.isBlank()) return findAll();
        return students.values().stream()
                .filter(s -> s.getName().toLowerCase().equals(name.toLowerCase()))
                .toList();
    }
    
    public boolean delete(String id) {
        return students.remove(id) != null;
    }
    
    public Optional<Student> changeCity(String id, String newCity) {
        Student s = students.get(id);
        if (s == null) return Optional.empty();
        
        Student updated = new Student(id, s.getName(), newCity);
        students.put(id, updated);
        return Optional.of(updated);
    }
    
    public void clearDatabase() {
        students.clear();
    }
}

