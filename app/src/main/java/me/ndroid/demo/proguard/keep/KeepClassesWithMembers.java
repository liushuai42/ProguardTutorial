package me.ndroid.demo.proguard.keep;

public class KeepClassesWithMembers implements INameInterface {
    public NameField field = new NameField("KeepClassesWithMembers");

    @Override
    public String getName() {
        return field.getName();
    }
}
