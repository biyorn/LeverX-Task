package ru.leverx.task.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class Aviary {

    @NonNull
    private Dog dog;
    private boolean isClean;
}
