package me.ndroid.demo.proguard.unkeep;

import me.ndroid.demo.proguard.keep.INameInterface;
import me.ndroid.demo.proguard.keep.NameField;

public class KeepClassesMembers implements INameInterface {
    private NameField field;

    public void setName(String name) {
        field = new NameField(name);
    }

    public String getName() {
        return field == null ? "null" : field.get();
    }
}
