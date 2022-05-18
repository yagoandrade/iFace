package User.Attributes;

import java.util.ArrayList;

public class Attribute {
    String name;
    String surname;
    String city;
    int age = -1;
    public ArrayList<String> messages = new ArrayList<String>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

}
