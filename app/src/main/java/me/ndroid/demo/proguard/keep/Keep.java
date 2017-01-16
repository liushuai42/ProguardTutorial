package me.ndroid.demo.proguard.keep;

public class Keep implements INameInterface {
    public NameField field = new NameField("KeepClassesMembers");

    public String getName() {
        return field.getName();
    }
}
