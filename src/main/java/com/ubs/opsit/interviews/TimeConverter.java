package com.ubs.opsit.interviews;

public interface TimeConverter {

    /**
     * Returns Berlin Clock time for the given time.
     * @param aTime time to be converted
     * @return String value representing Berlin Clock time
     */
    String convertTime(String aTime);

}
