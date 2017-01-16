package me.ndroid.demo.proguard.unkeep;

import me.ndroid.demo.proguard.keep.INameInterface;
import me.ndroid.demo.proguard.keep.NameField;

public class Keep implements INameInterface {
    public NameField field = new NameField("KeepClassesMembers");

    public String getName() {
        return field.getName();
    }
}
