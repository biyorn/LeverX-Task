package ru.leverx.task.entity;

import ru.leverx.task.entity.enumeration.DogAgeType;
import ru.leverx.task.entity.enumeration.DogJob;

public class Dog {

    private int age;
    private DogAgeType dogAgeType;
    private DogJob dogJob;
    private boolean isHealthy;
    private boolean isFed;
    private boolean isTrained;

    private Dog() {}

    public int getAge() {
        return age;
    }

    public DogAgeType getDogAgeType() {
        return dogAgeType;
    }

    public DogJob getDogJob() {
        return dogJob;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public boolean isFed() {
        return isFed;
    }

    public boolean isTrained() {
        return isTrained;
    }

    public static Builder builder() {
        return new Dog().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder buildAge(int age) {
            Dog.this.age = age;
            return this;
        }

        public Builder buildDogAgeType(DogAgeType dogAgeType) {
            Dog.this.dogAgeType = dogAgeType;
            return this;
        }

        public Builder buildDogJob(DogJob dogJob) {
            Dog.this.dogJob = dogJob;
            return this;
        }

        public Builder buildHealthy(boolean isHealthy) {
            Dog.this.isHealthy = isHealthy;
            return this;
        }

        public Builder buildFed(boolean isFed) {
            Dog.this.isFed = isFed;
            return this;
        }

        public Builder buildTrained(boolean isTrained) {
            Dog.this.isTrained = isTrained;
            return this;
        }

        public Dog build() {
            return Dog.this;
        }
    }
}
