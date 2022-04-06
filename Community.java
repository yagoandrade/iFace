import java.util.ArrayList;

public class Community {
    String name;
    String topic;
    int capacity;
    ArrayList<String> members = new ArrayList<String>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addMember(String name) {
        if (members.size() <= this.getCapacity()) {
            members.add(name);
        } else {
            System.out.println("The capacity is full");
        }
    }

    public void removeMember(String name) {
        members.remove(name);
    }
}
