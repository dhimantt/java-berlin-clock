package com.ubs.opsit.interviews.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.opsit.interviews.beans.BerlinClockDisplaySettings;
import com.ubs.opsit.interviews.util.BerlinClockConstants;
import com.ubs.opsit.interviews.util.BerlinClockLamp;
import com.ubs.opsit.interviews.util.BerlinClockUtil;

public class BerlinClockUtilTest {

    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_FOUR = 4;
    private static final int NUMBER_SIX = 6;
    private static final int NUMBER_NINE = 9;
    private static final int NUMBER_TEN = 10;
    private static final int NUMBER_SEVENTEEN = 17;
    private static final int NUMBER_TWENTY_ONE = 21;
    private static final int NUMBER_FIFTY_SIX = 56;
    private static final String HELLO = "hello";
    private static final String ZERO = "0";
    private static final String MINUS_ONE = "-1";
    private static final String NINE = "9";
    private static final String TEN = "10";
    private static final String SEVENTEEN = "17";
    private static final String TWENTY_FIVE = "25";
    private static final String FIFTY_NINE = "59";
    private static final String SIXTY = "60";
    private static final String YYOO = "YYOO";
    private static final String YYYY = "YYYY";
    private static final String RRRR = "RRRR";
    private static final String ROOO = "ROOO";
    private static final String OOOO = "OOOO";
    private static final String YYROOOOOOOO = "YYROOOOOOOO";
    private static final String OOOOOOOOOOO = "OOOOOOOOOOO";
    private static final String YYRYYRYYRYY = "YYRYYRYYRYY";

    BerlinClockUtil berlinClockUtil = new BerlinClockUtil();
    
    @Rule
    public ExpectedException thrownException = ExpectedException.none();
    
    @Test
    public void testConvertStringToNumber() {
        int number = berlinClockUtil.convertStringToNumber(TEN, BerlinClockConstants.SECOND);
        assertThat(number, is(NUMBER_TEN));
    }
    
    @Test
    public void testConvertStringToNumberWithInvalidInput() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, BerlinClockConstants.SECOND));
        berlinClockUtil.convertStringToNumber(HELLO, BerlinClockConstants.SECOND);
    }
    
    @Test
    public void testConvertStringToNumberWithNullInput() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, BerlinClockConstants.SECOND));
        berlinClockUtil.convertStringToNumber(null, BerlinClockConstants.SECOND);
    }
    
    @Test
    public void testConvertSecondsWithInvalidSeconds() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(BerlinClockConstants.INVALID_RANGE_FOR_SECONDS_MINUTES);
        berlinClockUtil.convertSeconds(SIXTY);
    }
    
    @Test
    public void testConvertSecondsWithLessThanZero() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, BerlinClockConstants.SECOND));
        berlinClockUtil.convertSeconds(MINUS_ONE);
    }

    @Test
    public void testConvertSecondsToGetYellowLamp() {
        String convertedSecond = berlinClockUtil.convertSeconds(ZERO);
        assertThat(convertedSecond, is(notNullValue()));
        assertThat(convertedSecond, is(BerlinClockLamp.YELLOW.getValue() + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testConvertSecondsToGetOffLamp() {
        String convertedSecond = berlinClockUtil.convertSeconds(FIFTY_NINE);
        assertThat(convertedSecond, is(notNullValue()));
        assertThat(convertedSecond, is(BerlinClockLamp.OFF.getValue() + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testConvertHours() {
        String convertedHours = berlinClockUtil.convertHours(NINE);
        assertThat(convertedHours, is(notNullValue()));
        assertThat(convertedHours, is(ROOO + BerlinClockConstants.SEPARATOR + RRRR + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testConvertHoursWithInvalidHours() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(BerlinClockConstants.INVALID_RANGE_FOR_HOUR);
        berlinClockUtil.convertHours(TWENTY_FIVE);
    }
    
    @Test
    public void testConvertHoursWithLessThanZero() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, BerlinClockConstants.HOUR));
        berlinClockUtil.convertHours(MINUS_ONE);
    }
    
    @Test
    public void testGetBerlinClockHourSettingsForFirstRow() {
        BerlinClockDisplaySettings settings = berlinClockUtil.getBerlinClockHourSettingsForFirstRow();
        assertThat(settings, is(notNullValue()));
        assertThat(settings.getNoOfLamps(), is(BerlinClockConstants.FOUR));
        assertThat(settings.getUnitPerLamp(), is(BerlinClockConstants.FIVE));
        assertThat(settings.getOnLamp().getValue(), is(BerlinClockLamp.RED.getValue()));
        assertThat(settings.hasSeparatorLamp(), is(false));
        assertThat(settings.getOffLamp().getValue(), is(BerlinClockLamp.OFF.getValue()));
    }
    
    @Test
    public void testGetBerlinClockHourSettingsForSecondRow() {
        BerlinClockDisplaySettings settings = berlinClockUtil.getBerlinClockHourSettingsForSecondRow();
        assertThat(settings, is(notNullValue()));
        assertThat(settings.getNoOfLamps(), is(BerlinClockConstants.FOUR));
        assertThat(settings.getUnitPerLamp(), is(BerlinClockConstants.ONE));
        assertThat(settings.getOnLamp().getValue(), is(BerlinClockLamp.RED.getValue()));
        assertThat(settings.hasSeparatorLamp(), is(false));
        assertThat(settings.getOffLamp().getValue(), is(BerlinClockLamp.OFF.getValue()));
    }
    
    @Test
    public void testGetHourLampsForFirstRow() {
        String firstHoursRow = berlinClockUtil.getHourLampsForFirstRow(NUMBER_NINE);
        assertThat(firstHoursRow, is(notNullValue()));
        assertThat(firstHoursRow, is(ROOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetHourLampsForFirstRowForAllLampsOn() {
        String firstHoursRow = berlinClockUtil.getHourLampsForFirstRow(NUMBER_TWENTY_ONE);
        assertThat(firstHoursRow, is(notNullValue()));
        assertThat(firstHoursRow, is(RRRR + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetHourLampsForFirstRowForAllLampsOff() {
        String firstHoursRow = berlinClockUtil.getHourLampsForFirstRow(NUMBER_FOUR);
        assertThat(firstHoursRow, is(notNullValue()));
        assertThat(firstHoursRow, is(OOOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetHourLampsForSecondRowAllLampsOn() {
        String secondHoursRow = berlinClockUtil.getHourLampsForSecondRow(NUMBER_NINE);
        assertThat(secondHoursRow, is(notNullValue()));
        assertThat(secondHoursRow, is(RRRR + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetHourLampsForSecondRowAllLampsOff() {
        String secondHoursRow = berlinClockUtil.getHourLampsForSecondRow(NUMBER_TEN);
        assertThat(secondHoursRow, is(notNullValue()));
        assertThat(secondHoursRow, is(OOOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetHourLampsForSecondRow() {
        String secondHoursRow = berlinClockUtil.getHourLampsForSecondRow(NUMBER_SIX);
        assertThat(secondHoursRow, is(notNullValue()));
        assertThat(secondHoursRow, is(ROOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetBerlinClockMinSettingsForFirstRow() {
        BerlinClockDisplaySettings settings = berlinClockUtil.getBerlinClockMinSettingsForFirstRow();
        assertThat(settings, is(notNullValue()));
        assertThat(settings.getNoOfLamps(), is(BerlinClockConstants.ELEVEN));
        assertThat(settings.getUnitPerLamp(), is(BerlinClockConstants.FIVE));
        assertThat(settings.hasSeparatorLamp(), is(true));
        assertThat(settings.getSeparatorLamp().getValue(), is(BerlinClockLamp.RED.getValue()));
        assertThat(settings.getOnLamp().getValue(), is(BerlinClockLamp.YELLOW.getValue()));
        assertThat(settings.getOffLamp().getValue(), is(BerlinClockLamp.OFF.getValue()));
        assertThat(settings.getSeperatorLampPositions().size(), is(BerlinClockConstants.THREE));
        
        List<Integer> separatorPositions = new ArrayList<> ();
        separatorPositions.add(BerlinClockConstants.TWO);
        separatorPositions.add(BerlinClockConstants.FIVE);
        separatorPositions.add(BerlinClockConstants.EIGHT);

        assertThat(settings.getSeperatorLampPositions(), is(separatorPositions));
    }
    
    @Test
    public void testGetBerlinClockMinSettingsForSecondRow() {
        BerlinClockDisplaySettings settings = berlinClockUtil.getBerlinClockMinSettingsForSecondRow();
        assertThat(settings, is(notNullValue()));
        assertThat(settings.getNoOfLamps(), is(BerlinClockConstants.FOUR));
        assertThat(settings.getUnitPerLamp(), is(BerlinClockConstants.ONE));
        assertThat(settings.hasSeparatorLamp(), is(false));
        assertThat(settings.getOnLamp().getValue(), is(BerlinClockLamp.YELLOW.getValue()));
        assertThat(settings.getOffLamp().getValue(), is(BerlinClockLamp.OFF.getValue()));
    }
    
    @Test
    public void testConvertMinutesWithInvalidMinutes() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(BerlinClockConstants.INVALID_RANGE_FOR_SECONDS_MINUTES);
        berlinClockUtil.convertMinutes(SIXTY);
    }
    
    @Test
    public void testConvertMinutesWithLessThanZero() {
        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, BerlinClockConstants.MINUTE));
        berlinClockUtil.convertMinutes(MINUS_ONE);
    }
    
    @Test
    public void testConvertMinutes() {
        String convertedMinutes = berlinClockUtil.convertMinutes(SEVENTEEN);
        assertThat(convertedMinutes, is(notNullValue()));
        assertThat(convertedMinutes, is(YYROOOOOOOO + BerlinClockConstants.SEPARATOR + YYOO));
    }
    
    @Test
    public void testGetMinuteLampsForFirstRow() {
        String firstMinuteRow = berlinClockUtil.getMinuteLampsForFirstRow(NUMBER_SEVENTEEN);
        assertThat(firstMinuteRow, is(notNullValue()));
        assertThat(firstMinuteRow, is(YYROOOOOOOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetMinuteLampsForFirstRowAllLampsOn() {
        String firstMinuteRow = berlinClockUtil.getMinuteLampsForFirstRow(NUMBER_FIFTY_SIX);
        assertThat(firstMinuteRow, is(notNullValue()));
        assertThat(firstMinuteRow, is(YYRYYRYYRYY + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetMinuteLampsForFirstRowAllLampsOff() {
        String firstMinuteRow = berlinClockUtil.getMinuteLampsForFirstRow(NUMBER_FOUR);
        assertThat(firstMinuteRow, is(notNullValue()));
        assertThat(firstMinuteRow, is(OOOOOOOOOOO + BerlinClockConstants.SEPARATOR));
    }
    
    @Test
    public void testGetMinuteLampsForSecondRow() {
        String secondMinuteRow = berlinClockUtil.getMinuteLampsForSecondRow(NUMBER_SEVENTEEN);
        assertThat(secondMinuteRow, is(notNullValue()));
        assertThat(secondMinuteRow, is(YYOO));
    }
    
    @Test
    public void testGetMinuteLampsForSecondRowAllLampsOn() {
        String secondMinuteRow = berlinClockUtil.getMinuteLampsForSecondRow(NUMBER_FOUR);
        assertThat(secondMinuteRow, is(notNullValue()));
        assertThat(secondMinuteRow, is(YYYY));
    }
    
    @Test
    public void testGetMinuteLampsForSecondRowAllLampsOff() {
        String secondMinuteRow = berlinClockUtil.getMinuteLampsForSecondRow(NUMBER_ZERO);
        assertThat(secondMinuteRow, is(notNullValue()));
        assertThat(secondMinuteRow, is(OOOO));
    }
}
