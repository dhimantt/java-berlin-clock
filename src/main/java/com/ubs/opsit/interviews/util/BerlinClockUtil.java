package com.ubs.opsit.interviews.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.ubs.opsit.interviews.beans.BerlinClockDisplaySettings;
/**
 * This class provides utility method to support time conversion for Berlin Clock.
 * @author Dhimant Thaker
 *
 */
public class BerlinClockUtil {
    
    private BerlinClockUtil() {
        // can't create object as its not required.
    }

    /**
     * Converts input value to Berlin Clock Seconds.
     * @param value String value to be converted
     * @return value which represents second lamp in Berlin Clock
     * @throws IllegalArgumentException if the input value is non-numeric or invalid seconds.
     */
    public static String convertSeconds(String value) {
        int seconds = validateInput(value, BerlinClockConstants.SECOND, BerlinClockConstants.FIFTY_NINE, BerlinClockConstants.INVALID_RANGE_FOR_SECONDS_MINUTES);
        String secondsLampValue = (seconds % 2 == BerlinClockConstants.ZERO) ? BerlinClockLamp.YELLOW.getValue() : BerlinClockLamp.OFF.getValue();
        return secondsLampValue + BerlinClockConstants.SEPARATOR;
    }

    /**
     * Converts input value to Berlin Clock Hours.
     * @param value String value to be converted
     * @return value which represents hour lamps in Berlin Clock
     * @throws IllegalArgumentException if the input value is non-numeric or invalid hours.
     */
    public static String convertHours(String value) {
        int hours = validateInput(value, BerlinClockConstants.HOUR, BerlinClockConstants.TWENTY_FOUR, BerlinClockConstants.INVALID_RANGE_FOR_HOUR);
        StringBuilder hourLamps = new StringBuilder();
        hourLamps.append(getHourLampsForFirstRow(hours));
        hourLamps.append(getHourLampsForSecondRow(hours));
        return hourLamps.toString();
    }
    
    /**
     * Converts input value to Berlin Clock Minutes.
     * @param value String value to be converted
     * @return value which represents minute lamps in Berlin Clock
     * @throws IllegalArgumentException if the input value is non-numeric or invalid minutes.
     */
    public static String convertMinutes(String value) {
        int min = validateInput(value, BerlinClockConstants.MINUTE, BerlinClockConstants.FIFTY_NINE, BerlinClockConstants.INVALID_RANGE_FOR_SECONDS_MINUTES);
        StringBuilder minLamps = new StringBuilder();
        minLamps.append(getMinuteLampsForFirstRow(min));
        minLamps.append(getMinuteLampsForSecondRow(min));
        return minLamps.toString();
    }
    
    /**
     * Converts input string to numeric equivalent.
     * @param value value to be converted
     * @param unit unit of time
     * @return numeric equivalent of string value
     * @throws IllegalArgumentException if the input value negative or non-numeric.
     */
    public static int convertStringToNumber(String value, String unit) {
        if (NumberUtils.isDigits(value)) {
            return Integer.parseInt(value);
        } else {
            throw new IllegalArgumentException(String.format(BerlinClockConstants.PLEASE_ENTER_NUMERIC_VALUES, unit));
        }
    }

    /**
     * Returns Berlin Clock hour lamps for first row.
     * @param hours hour value to be converted
     * @return String representing hour lamps for first row
     */
    public static String getHourLampsForFirstRow(int hours) {
        BerlinClockDisplaySettings berlinClockHourSettings = getBerlinClockHourSettingsForFirstRow();
        StringBuilder firstHoursRow = getBerlinClockRepresentation(berlinClockHourSettings, hours, berlinClockHourSettings.hasSeparatorLamp(), false);
        firstHoursRow.append(BerlinClockConstants.SEPARATOR);
        return firstHoursRow.toString();
    }
    
    /**
     * Returns Berlin Clock hour lamps for second row.
     * @param hours hour value to be converted
     * @return String representing lamps for second row
     */
    public static String getHourLampsForSecondRow(int hours) {
        BerlinClockDisplaySettings settingsForFirstHourRow = getBerlinClockHourSettingsForFirstRow();
        BerlinClockDisplaySettings berlinClockHourSettings = getBerlinClockHourSettingsForSecondRow();
        int hourRemainder = hours % settingsForFirstHourRow.getUnitPerLamp();
        StringBuilder secondHoursRow = getBerlinClockRepresentation(berlinClockHourSettings, hourRemainder, berlinClockHourSettings.hasSeparatorLamp(), true);
        secondHoursRow.append(BerlinClockConstants.SEPARATOR);
        return secondHoursRow.toString();
    }

    /**
     * Returns Berlin Clock hour display settings for first row.
     * @return {@link BerlinClockDisplaySettings}
     */
    public static BerlinClockDisplaySettings getBerlinClockHourSettingsForFirstRow() {
        BerlinClockDisplaySettings berlinClockHourSettings = new BerlinClockDisplaySettings();
        berlinClockHourSettings.setUnitPerLamp(BerlinClockConstants.FIVE);
        populateBerlinClockHourSettings(berlinClockHourSettings);
        return berlinClockHourSettings;
    }
    
    /**
     * Returns Berlin Clock hour display settings for second row.
     * @return {@link BerlinClockDisplaySettings}
     */
    public static BerlinClockDisplaySettings getBerlinClockHourSettingsForSecondRow() {
        BerlinClockDisplaySettings berlinClockHourSettings = new BerlinClockDisplaySettings();
        berlinClockHourSettings.setUnitPerLamp(BerlinClockConstants.ONE);
        populateBerlinClockHourSettings(berlinClockHourSettings);
        return berlinClockHourSettings;
    }

    /**
     * Returns Berlin Clock minute display settings for first row.
     * @return {@link BerlinClockDisplaySettings}
     */
    public static BerlinClockDisplaySettings getBerlinClockMinSettingsForFirstRow() {
        BerlinClockDisplaySettings berlinClockMinSettings = new BerlinClockDisplaySettings();
        berlinClockMinSettings.setNoOfLamps(BerlinClockConstants.ELEVEN);
        berlinClockMinSettings.setUnitPerLamp(BerlinClockConstants.FIVE);
        berlinClockMinSettings.setHasSeparatorLamp(true);
        berlinClockMinSettings.setSeparatorLamp(BerlinClockLamp.RED);
        berlinClockMinSettings.setOnLamp(BerlinClockLamp.YELLOW);
        berlinClockMinSettings.setOffLamp(BerlinClockLamp.OFF);
        
        List<Integer> separatorPositions = addSeparatorPositions();
        
        berlinClockMinSettings.setSeperatorLampPositions(separatorPositions);
        return berlinClockMinSettings;
    }

    /**
     * Returns Berlin Clock minute display settings for second row.
     * @return {@link BerlinClockDisplaySettings}
     */
    public static BerlinClockDisplaySettings getBerlinClockMinSettingsForSecondRow() {
        BerlinClockDisplaySettings berlinClockMinSettings = new BerlinClockDisplaySettings();
        berlinClockMinSettings.setNoOfLamps(BerlinClockConstants.FOUR);
        berlinClockMinSettings.setUnitPerLamp(BerlinClockConstants.ONE);
        berlinClockMinSettings.setHasSeparatorLamp(false);
        berlinClockMinSettings.setOnLamp(BerlinClockLamp.YELLOW);
        berlinClockMinSettings.setOffLamp(BerlinClockLamp.OFF);
        return berlinClockMinSettings;
    }
    
    /**
     * Returns Berlin Clock minute lamps for first row.
     * @param minutes minute value to be converted
     * @return String representing minute lamps for first row
     */
    public static String getMinuteLampsForFirstRow(int minutes) {
        BerlinClockDisplaySettings minSettingsForFirstRow = getBerlinClockMinSettingsForFirstRow();
        StringBuilder firstMinutesRow = getBerlinClockRepresentation(minSettingsForFirstRow, minutes, minSettingsForFirstRow.hasSeparatorLamp(), false);
        firstMinutesRow.append(BerlinClockConstants.SEPARATOR);
        return firstMinutesRow.toString();
    }
    
    /**
     * Returns Berlin Clock minute lamps for second row.
     * @param minutes minute value to be converted
     * @return String representing mintute lamps for second row
     */
    public static String getMinuteLampsForSecondRow(int minutes) {
        BerlinClockDisplaySettings settingsForFirstMinRow = getBerlinClockMinSettingsForFirstRow();
        BerlinClockDisplaySettings berlinClockMinuteSettings = getBerlinClockMinSettingsForSecondRow();
        int minRemainder = minutes % settingsForFirstMinRow.getUnitPerLamp();
        StringBuilder secondMinutesRow = getBerlinClockRepresentation(berlinClockMinuteSettings, minRemainder, berlinClockMinuteSettings.hasSeparatorLamp(), true);
        return secondMinutesRow.toString();
    }
    
    private static int validateInput(String value, String unit, int maxAllowedValue, String msg) {
        int number = convertStringToNumber(value, unit);
        if (number > maxAllowedValue) { // Negative values will be handled by convertStringToNumber method.
            throw new IllegalArgumentException(msg);
        }
        return number;
    }
    
    private static List<Integer> addSeparatorPositions() {
        List<Integer> separatorPositions = new ArrayList<> ();
        separatorPositions.add(BerlinClockConstants.TWO);
        separatorPositions.add(BerlinClockConstants.FIVE);
        separatorPositions.add(BerlinClockConstants.EIGHT);
        return separatorPositions;
    }
    
    private static StringBuilder getBerlinClockRepresentation(BerlinClockDisplaySettings berlinClockDisplaySettings, int value, boolean hasSeparator, boolean secondRow) {
        StringBuilder row = new StringBuilder();
        int count = BerlinClockConstants.ZERO;
        int remainder = value % berlinClockDisplaySettings.getUnitPerLamp();
        int lampsToOn = secondRow ? value : (value - remainder) / berlinClockDisplaySettings.getUnitPerLamp();
        
        for (int index = BerlinClockConstants.ZERO; index < berlinClockDisplaySettings.getNoOfLamps(); index++) {
            if (count < lampsToOn) {
                if (berlinClockDisplaySettings.hasSeparatorLamp() && berlinClockDisplaySettings.getSeperatorLampPositions().contains(count)) {
                    row.append(berlinClockDisplaySettings.getSeparatorLamp().getValue());
                } else {
                    row.append(berlinClockDisplaySettings.getOnLamp().getValue());
                }
            } else {
                row.append(berlinClockDisplaySettings.getOffLamp().getValue());
            }
            count = count + 1;
        }
        
        return row;
    }

    private static void populateBerlinClockHourSettings(BerlinClockDisplaySettings berlinClockHour) {
        berlinClockHour.setNoOfLamps(BerlinClockConstants.FOUR);
        berlinClockHour.setOnLamp(BerlinClockLamp.RED);
        berlinClockHour.setOffLamp(BerlinClockLamp.OFF);
        berlinClockHour.setHasSeparatorLamp(false);
    }
}
