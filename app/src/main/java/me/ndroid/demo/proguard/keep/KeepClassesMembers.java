package me.ndroid.demo.proguard.keep;

public class KeepClassesMembers implements INameInterface {
    public NameField field = new NameField("KeepClassesMembers");

    @Override
    public String getName() {
        return field.getName();
    }
}
