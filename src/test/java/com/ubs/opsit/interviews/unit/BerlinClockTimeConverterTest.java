package com.ubs.opsit.interviews.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.opsit.interviews.BerlinClockTimeConverter;
import com.ubs.opsit.interviews.util.BerlinClockConstants;

public class BerlinClockTimeConverterTest {
    
    private static final String VALID_TIME_FORMAT = "00:00:00";

    private static final String INVALID_TIME_FORMAT = "00:00:00:00";

    BerlinClockTimeConverter berlinClockTimeConverter = new BerlinClockTimeConverter();

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Test
    public void testConvertTimeWithNullValue() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(BerlinClockConstants.INVALID_TIME_INPUT);
        berlinClockTimeConverter.convertTime(null);
    }
    
    @Test
    public void testConvertTimeWithInvalidTimeValue() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(BerlinClockConstants.INVALID_TIME_INPUT);
        berlinClockTimeConverter.convertTime(INVALID_TIME_FORMAT);
    }
    
    @Test
    public void testConvertTime() throws IOException {
        String convertedTime = berlinClockTimeConverter.convertTime(VALID_TIME_FORMAT);
        assertThat(convertedTime, is(notNullValue()));
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("BerlinClockOutput.txt").getFile());
        String fileToString = FileUtils.readFileToString(file);
        assertThat(convertedTime, is(fileToString));
    }
}
