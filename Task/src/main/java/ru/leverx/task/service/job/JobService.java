package ru.leverx.task.service.job;

public interface JobService<T> {

    T toWork(T t);
}
