package com.example.foodfight;
/*This object stores User data as String:int     int height;
    int weight;
    int age;
    int weekGoal;
    int dayGoal;
    int bmi;*/

public class Profile {
    int height;
    int weight;
    int age;
    int weekGoal;
    int dayGoal;
    int bmi;
    public Profile(int height,
                   int weight,
                   int age,
                   int weekGoal,
                   int dayGoal,
                   int bmi) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.weekGoal = weekGoal;
        this.dayGoal = dayGoal;
        this.bmi = bmi;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeekGoal() {
        return weekGoal;
    }

    public void setWeekGoal(int weekGoal) {
        this.weekGoal = weekGoal;
    }

    public int getDayGoal() {
        return dayGoal;
    }

    public void setDayGoal(int dayGoal) {
        this.dayGoal = dayGoal;
    }

    public int getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }
}

