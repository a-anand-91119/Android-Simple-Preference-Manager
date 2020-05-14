package in.notyouraveragedev.sharedpreference.domain;

import java.io.Serializable;

/**
 * Created by A Anand on 14-05-2020
 */
public class CustomObject implements Serializable {
    private String name;
    private String age;

    public CustomObject() {
    }

    public CustomObject(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
