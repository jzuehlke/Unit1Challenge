package com.jakezuehlke.test;

/**
 * @author Jake Zuehlke
 */

import com.jakezuehlke.FileInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;

/**
 * FileInputTest:<br>
 *     This is a test class for FileInput, it will determine if places.csv and stuff.csv are being read in
 */

public class FileInputTest
{
    //variable for testing
    FileInput placesTest;
    FileInput stuffTest;

    //before is for file input
    @Before
    public void setUp()
    {
        //test the constructor.  This test example was copied from the main
        placesTest = new FileInput("places.csv");
        stuffTest = new FileInput("stuff.csv");
    }

    //test the FileInput class' methods
    @Test
    public void testFile()
    {
        //see if fileReadLine returns null.  It shouldn't.
        assertNotNull("Reader should return data.", placesTest.fileReadLine());
        assertNotNull("Reader should return data.", stuffTest.fileReadLine());
    }

    //after is for file closing
    @After
    public void tearDown()
    {
        placesTest.fileClose();
        stuffTest.fileClose();
    }
}
