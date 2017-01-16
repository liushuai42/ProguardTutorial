package me.ndroid.demo.proguard.keep;

public class NameField {

    private String name;

    public NameField() {
        this("_null_");
    }

    public NameField(String field) {
        this.name = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
