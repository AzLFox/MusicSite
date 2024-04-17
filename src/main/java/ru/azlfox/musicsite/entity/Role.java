package ru.azlfox.musicsite.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {
    USER("Читатель"),
    PERFORMER("Композитор");

    private final String roleName;
    Role(String roleName) {
        this.roleName = roleName;
    }

    public static List<Role> getAllRole(){
        return new ArrayList<>(Arrays.asList(Role.values()));
    }
}
