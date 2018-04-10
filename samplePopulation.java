package com.company;

public class samplePopulation {

    Human humanArray [][];
    //Creates the array and fills it
    public samplePopulation(int n, int minDays, int maxDays) {
        humanArray = new Human[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                humanArray[i][j] = new Human(minDays,maxDays);
            }
        }
    }

    //Position of initially infected individuals
    public void setSick(int x, int y) {
        humanArray[x][y].setInfectionDay(1);
        humanArray[x][y].setCondition("health",false);
    }
}
