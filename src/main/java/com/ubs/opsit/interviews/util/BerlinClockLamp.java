package com.ubs.opsit.interviews.util;

/**
 * Berlin Clock Lamps. 
 * @author Dhimant Thaker
 *
 */
public enum BerlinClockLamp {
    RED("R"), YELLOW("Y"), OFF("O");
    
    private String value;
    
    BerlinClockLamp(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
