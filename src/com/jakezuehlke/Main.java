package com.jakezuehlke;

/**
 * @author Jake Zuehlke
 */
public class Main
{
    /**
     * This program goes through two .csv files in order to gather each unique country
     * name, the number of cities listed per country, and the number of stuffs associated
     * with each country.
     */

    private static String stuffLine;
    private static String citiesLine;
    private final static FileInput countries = new FileInput("places.csv");
    private final static FileInput cities = new FileInput("places.csv");
    private final static FileInput stuff = new FileInput("stuff.csv");

    /**
     * Main: <br>
     *     The main method loops through the places.csv file as the driver file, while passing
     *     the first element on each line to the findCities and findStuff methods.  The main
     *     method also establishes output formatting and closes the opened files.
     * @param args
     */
    public static void main(String[] args)
    {
        //string to hold entire line of data from places.csv
        String placeLine;

        //string array to hold the values separated by commas
        String[] fields;

        //integer array to hold the numbers for output columns
        int[] nums = new int[2];

        //output headers
        System.out.format("%-21s %6s %5s\n","Country","Cities", "Stuff");
        System.out.format("%-21s %6s %5s\n","=======","======", "=====");

        //first iteration flag
        boolean first = true;

        //loop through countries copy of places.csv as the driver file
        while ((placeLine = countries.fileReadLine()) != null)
        {
            //split the line based on commas
            fields = placeLine.split(",");

            findCities(first, fields[0], nums);
            findStuff(first, fields[0], nums);

            //first iteration complete
            first = false;

            //eliminate duplicate information, output format
            if(nums[0] != 0)
            {
                System.out.format("%-21s    %-3d   %-3d\n", fields[0], nums[0], nums[1]);
            }
        }
        //close files
        countries.fileClose();
        cities.fileClose();
        stuff.fileClose();
    }

    /**
     * findCities: <br>
     *     This method accumulates for integers in the "Cities" output column using a
     *     separate object of the places file, based on what the current country name is in the driver file.
     * @param first this boolean value is used to determine the first line check of the file
     * @param country this is the name of the country the driver file is currently on
     * @param nums this is the value of total cities per country
     */
    public static void findCities(boolean first, String country, int[] nums)
    {
        //nums[0] represents cities output column, set to 0 to start fresh per country
        nums[0] = 0;
        String[] fields;
        boolean done = false;

        //if it's the first iteration, grab first line of places.csv
        if(first)
        {
            citiesLine = cities.fileReadLine();
        }
        while ((citiesLine != null) && !(done))
        {
            fields = citiesLine.split(",");

            //fields[0] represents country name in places.csv.
            //If it is the same, accumulate nums[0] and read next line
            if (!(fields[0].equals(country)))
            {
                done = true;
            }
            else if (fields[0].equals(country))
            {
                nums[0]++;
                citiesLine = cities.fileReadLine();
            }
        }
    }

    /**
     * findStuff: <br>
     *     This method accumulates for integers in the "Stuff" output column using the stuff file,
     *     based on what the current country name is in the driver file.
     * @param first this boolean value is used to determine the first line check of the file
     * @param country this is the name of the country the driver file is currently on
     * @param nums this is the value of total "stuff" per country
     */
    public static void findStuff(boolean first, String country, int[] nums)
    {
        //nums[1] represents stuff output column, set to 0 to start fresh per country
        nums[1] = 0;
        String[] fields;
        boolean done = false;

        //if it's the first iteration, grab first line of stuff.csv
        if(first)
        {
            stuffLine = stuff.fileReadLine();
        }
        while ((stuffLine != null) && !(done))
        {
            fields = stuffLine.split(",");

            //fields[0] represents country name in stuff.csv.
            //If it is the same, accumulate nums[1] and read next line
            if (!(fields[0].equals(country)))
            {
                done = true;
            }
            else if (fields[0].equals(country))
            {
                nums[1]++;
                stuffLine = stuff.fileReadLine();
            }
        }
    }
}
