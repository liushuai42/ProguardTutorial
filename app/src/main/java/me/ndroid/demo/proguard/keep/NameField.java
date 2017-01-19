package me.ndroid.demo.proguard.keep;

public class NameField {

    private String name;

    public NameField() {
        this("_null_");
    }

    public NameField(String field) {
        this.name = field;
    }

    public String get() {
        return name;
    }

    public void set(String name) {
        this.name = name;
    }
}
