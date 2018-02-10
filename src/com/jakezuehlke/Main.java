package com.jakezuehlke;

/**
 * @author Jake Zuehlke
 */
public class Main
{
    private static String stuffLine;
    private static String citiesLine;
    private final static FileInput countries = new FileInput("places.csv");
    private final static FileInput cities = new FileInput("places.csv");
    private final static FileInput stuff = new FileInput("stuff.csv");

    public static void main(String[] args)
    {
        String placeLine;
        //string array to hold the values separated by commas
        String[] fields;

        //integer array to hold the numbers to output
        int[] nums = new int[2];

        //output headers
        System.out.format("%-21s %6s %5s\n","Country","Cities", "Stuff");
        System.out.format("%-21s %6s %5s\n","=======","======", "=====");
        boolean first = true;

        while ((placeLine = countries.fileReadLine()) != null)
        {
            fields = placeLine.split(",");
            findCities(first, fields[0], nums);
            findStuff(first, fields[0], nums);

            first = false;

            if(nums[0] != 0)
            {
                System.out.format("%-21s    %-3d   %-3d\n", fields[0], nums[0], nums[1]);
            }
        }
        countries.fileClose();
        cities.fileClose();
        stuff.fileClose();
    }

    public static void findCities(boolean first, String country, int[] nums)
    {
        nums[0] = 0;
        String[] fields;
        boolean done = false;

        if(first)
        {
            citiesLine = cities.fileReadLine();
        }
        while ((citiesLine != null) && !(done))
        {
            fields = citiesLine.split(",");
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

    public static void findStuff(boolean first, String country, int[] nums)
    {
        nums[1] = 0;
        String[] fields;
        boolean done = false;

        if(first)
        {
            stuffLine = stuff.fileReadLine();
        }
        while ((stuffLine != null) && !(done))
        {
            fields = stuffLine.split(",");
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
