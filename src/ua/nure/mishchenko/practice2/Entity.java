package ua.nure.mishchenko.practice2;

import java.util.Objects;
import java.util.StringJoiner;

public class Entity {
    private Integer id;
    private Integer age;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return getId().equals(entity.getId()) &&
                getAge().equals(entity.getAge()) &&
                getName().equals(entity.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAge(), getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Entity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("age=" + age)
                .add("name='" + name + "'")
                .toString();
    }
}
