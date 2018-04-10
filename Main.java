package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args){
        getInputs();
    }

    //Setting variables according to user's inputs
    public static void getInputs() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the necessary information for the simulation");

        System.out.print("Size of the population: ");
        int sizeOfPopulation = sc.nextInt();

        //System.out.print("Probability that a sick individual infects a healthy neighbor (0-100): ");
        double infectionPropability = sc.nextDouble();

        System.out.print("Minimum number of days that an individual is ill: ");
        int minDays = sc.nextInt();

        System.out.print("Maximum number of days that an individual is ill: ");
        int maxDays = sc.nextInt();

        System.out.print("Probability that an individual dies a day (0-100): ");
        double deathPropability = sc.nextDouble();

        System.out.print("Number of initially infected people: ");
        int numberOfSickInit = sc.nextInt();

        //Create an array to save the position of initially sick people
        Position sickArray [] = new Position[numberOfSickInit];

        //Save the position of its sick individual
        System.out.println("Position of initially infected humans in x,y cordinates:");
        for(int i=0; i<numberOfSickInit; i++) {
            System.out.println("Enter the position of infected human number " + (i+1) + " in x,y coordinates.");
            System.out.print("x coordinate (1-"+sizeOfPopulation+") is: ");
            int x = sc.nextInt()-1;
            System.out.print("y coordinate (1-"+sizeOfPopulation+") is: ");
            int y = sc.nextInt()-1;
            sickArray[i] = new Position(x,y);
        }

        System.out.println("\n-----------------------------------------------------SIMULATION-----------------------------------------------------------------");
        //Create the sample population
        samplePopulation pop = new samplePopulation(sizeOfPopulation,minDays,maxDays);

        //Initialize the sick individuals of the sample polulation
        for(Position pos : sickArray) {
            pop.setSick(pos.getX(),pos.getY());
        }

        //Start the simulation
        InfectionSim.infectionSim(sizeOfPopulation, infectionPropability,deathPropability, numberOfSickInit, pop);

    }
}