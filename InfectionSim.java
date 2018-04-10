package com.company;

public class InfectionSim {

    private static int day;
    private static int numOfSick;
    static int dailyInfected;
    static int dailyDeaths;
    static int dailyImmune;
    static int totalInfected;
    static int totalDeaths;

    public static void infectionSim(int sizeOfPopulation, double infectionPropability, double deathPropability, int numberOfSickInit, samplePopulation pop) {

        //Initialise the variables
        day = 1;
        numOfSick = numberOfSickInit;
        dailyInfected = 0;  //number of people that get infected every day
        dailyDeaths = 0;    //Number of people that die every day
        dailyImmune = 0;    //Number of people that has been treated (became immune) every day
        totalInfected = 0;  //Total number of people that have been treated
        totalDeaths = 0;    //Total number of people that died

        //Run until no more infected people
        while (numOfSick > 0) {

            //Reset the counters for each day
            dailyInfected = 0;
            dailyDeaths = 0;
            dailyImmune = 0;

            //Check the population
            for (int i = 0; i < sizeOfPopulation; i++) {
                for (int j = 0; j < sizeOfPopulation; j++) {
                    if (pop.humanArray[i][j].getCondition().equals("healthy") || pop.humanArray[i][j].getCondition().equals("dead")) {
                        //Healthy/dead human cannot affect the rest of population -> Do nothing
                    }
                    //If someone is infected there are two possibilities to happen. First -> become immmune. Second -> die or remain infected
                    //First check if human becomes immune
                    else if (pop.humanArray[i][j].getCondition().equals("infected") && (day - pop.humanArray[i][j].getInfectionDay() == pop.humanArray[i][j].getSickdays())) {
                        //Human has lived through the length of time that an individual is ill -> Immune
                        pop.humanArray[i][j].setCondition("immune", true);
                        numOfSick--;    //Number of sick human decreased
                        dailyImmune++;  //Number of immune people increased
                    }
                    //Second check if human dies or lives
                    //Human cannot infect neighbors on its first day of infection -> Either dies or continues to live
                    else if (pop.humanArray[i][j].getCondition().equals("infected") && pop.humanArray[i][j].getInfectionDay() == day) {
                        //Check if human dies
                        dead(i, j, deathPropability, pop);
                    } else if (pop.humanArray[i][j].getCondition().equals("infected") && pop.humanArray[i][j].getInfectionDay() < day) {
                        if (!dead(i, j, deathPropability, pop)) {
                            //If not dead, human starts spreading the disease
                            infect(sizeOfPopulation,i,j,infectionPropability,pop,day);
                        }
                    }
                }
            }
           // results(day);
            day++;
        }
        System.out.println("\nFINAL RESULTS: \nPopulation: "+sizeOfPopulation*sizeOfPopulation+"\nInfected: "
                +totalInfected+"\ndead: "+totalDeaths+"\nHealthy =  "+sizeOfPopulation*sizeOfPopulation+"(population) - "+totalInfected+"(infected) - "+numberOfSickInit+"(initially infected) = "
                +(sizeOfPopulation*sizeOfPopulation-totalInfected-numberOfSickInit));
        if(totalInfected <= sizeOfPopulation*sizeOfPopulation/2){
            System.out.println("Disease did not develop into an epidemic.");
        }else{
            System.out.println("Disease developed into an epidemic");
        }
    }

    public static boolean dead(int row, int col, double deathPropability, samplePopulation pop) {
        if(Math.random()*100 <= deathPropability) {
            pop.humanArray[row][col].setCondition("dead", true);
            pop.humanArray[row][col].setCondition("healthy", false);
            numOfSick--;
            dailyDeaths++;
            totalDeaths++;
            return true;
        }
        else {
            return false;
        }
    }

    public static void infect(int sizeOfPopulation, int row, int col, double infectionPropability, samplePopulation pop, int dayOfInf) {
        //Check upper left corner of square matrix -> avoid exception
        if(row == 0 && col ==0){
            for(int i=row; i<=row+1; i++) {
                for(int j=col; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
            //Check bottom right corner of square matrix -> avoid exception
        }else if(row == sizeOfPopulation-1 && col == sizeOfPopulation-1){
            for(int i=row-1; i<=row; i++) {
                for(int j=col-1; j<=col; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
            //Check upper right corner of square matrix -> avoid exception
        }else if(row == 0 && col == sizeOfPopulation-1){
            for(int i=row; i<=row+1; i++) {
                for(int j=col-1; j<=col; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
            //Check bottom left corner of square matrix -> avoid exception
        }else if(row == sizeOfPopulation-1 && col == 0){
            for(int i=row-1; i<=row; i++) {
                for(int j=col; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        } else if(row == 0 && col !=0 && col != sizeOfPopulation-1){
            for(int i=row; i<=row+1; i++) {
                for(int j=col-1; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }else if (row !=0 && row != sizeOfPopulation-1 && col == 0){
            for(int i=row-1; i<=row+1; i++) {
                for(int j=col; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }else if(row == sizeOfPopulation-1 && col !=0  && col != sizeOfPopulation-1){
            for(int i=row-1; i<=row; i++) {
                for(int j=col-1; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }else if(row != 0 && row != sizeOfPopulation-1 && col == sizeOfPopulation-1) {
            for(int i=row-1; i<=row+1; i++) {
                for(int j=col-1; j<=col; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }else{
            for(int i=row-1; i<=row+1; i++) {
                for(int j=col-1; j<=col+1; j++) {
                    if(pop.humanArray[i][j].getCondition().equals("healthy")) {
                        //In position row,col is the infected sample so it can't be infected again -> skip it
                        if(i == row && j == col) {
                            continue;
                        }
                        if(pop.humanArray[i][j].getCondition().equals("healthy")){
                            if(Math.random()*100 <= infectionPropability) {
                                pop.humanArray[i][j].setCondition("health",false);
                                pop.humanArray[i][j].setInfectionDay(dayOfInf);
                                dailyInfected++;
                                numOfSick++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void results(int day) {
        System.out.println("In day " + day + " infected today: "+dailyInfected+" ,dead today: "+dailyDeaths+" ,immune today: "+dailyImmune+
                " ,ill today: "+numOfSick+" ,accumulated infected: "+ totalInfected +" ,accumulated deaths: "+totalDeaths);
    }
}
