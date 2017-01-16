package me.ndroid.demo.proguard.unkeep;

import me.ndroid.demo.proguard.keep.INameInterface;
import me.ndroid.demo.proguard.keep.NameField;

public class KeepClassesMembers implements INameInterface {
    public NameField field = new NameField("KeepClassesMembers");

    @Override
    public String getName() {
        return field.getName();
    }
}
