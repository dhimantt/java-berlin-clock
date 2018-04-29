package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.util.BerlinClockUtil;
import com.ubs.opsit.interviews.util.BerlinClockConstants;

/**
 * This class performs the conversion for a give time to Berlin Clock time. 
 * @author Dhimant Thaker
 *
 */
public class BerlinClockTimeConverter implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        if(aTime == null || aTime.split(BerlinClockConstants.COLON).length != BerlinClockConstants.THREE){
            throw new IllegalArgumentException(BerlinClockConstants.INVALID_TIME_INPUT);
        }

        StringBuilder berlinClockTime = new StringBuilder();
        String[] timeParts = aTime.split(BerlinClockConstants.COLON);
        
        // Get seconds
        String convertedSeconds = BerlinClockUtil.convertSeconds(timeParts[2]);
        berlinClockTime.append(convertedSeconds);
        
        // Get hours
        String convertedHours = BerlinClockUtil.convertHours(timeParts[0]);
        berlinClockTime.append(convertedHours);
        
        // Get minutes
        String convertedMinutes = BerlinClockUtil.convertMinutes(timeParts[1]);
        berlinClockTime.append(convertedMinutes);
        
        return berlinClockTime.toString();
    }

}
