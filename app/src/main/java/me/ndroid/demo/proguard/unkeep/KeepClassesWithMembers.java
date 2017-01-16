package me.ndroid.demo.proguard.unkeep;

import me.ndroid.demo.proguard.keep.INameInterface;
import me.ndroid.demo.proguard.keep.NameField;

public class KeepClassesWithMembers implements INameInterface {
    public NameField field = new NameField("KeepClassesWithMembers");

    @Override
    public String getName() {
        return field.getName();
    }
}
