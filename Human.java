package com.company;

public class Human {
    boolean isHealthy;
    boolean isDead;
    boolean isImmune;
    int dayOfInfection;
    int sickDays;


    public Human(int minDays, int maxDays) {
        isHealthy = true;
        isDead = false;
        isImmune = false;
        dayOfInfection = 0;
        sickDays = minDays + (int)(Math.random() * ((maxDays - minDays) + 1));
    }

    //Change the condition of the individual
    public void setCondition(String condition, boolean value) {

        switch (condition) {
            case "health":
                this.isHealthy = value;
                break;
            case "immune":
                this.isImmune = value;
                break;
            case "dead":
                this.isDead = value;
                break;
        }
    }

    //Get the condition of the individual
    public String getCondition() {
        if(isImmune == true)
            return "immune";
        else if(isHealthy == true)
            return "healthy";
        else if(isHealthy == false && isDead == false)
            return "infected";
        else
            return "dead";
    }

    public int getSickdays() {
        return sickDays;
    }

    //Set the day of the infection of the individual
    public void setInfectionDay(int day) {
        this.dayOfInfection = day;
    }

    //Get the infection day of the individual
    public int getInfectionDay() {
        return dayOfInfection;
    }

}
