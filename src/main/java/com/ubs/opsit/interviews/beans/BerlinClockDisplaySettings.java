package com.ubs.opsit.interviews.beans;

import java.util.List;

import com.ubs.opsit.interviews.util.BerlinClockLamp;

/**
 * This class is used to hold the display settings hour, minute and seconds of Berlin Clock.
 * @author Dhimant Thaker
 *
 */
public class BerlinClockDisplaySettings {

    private int noOfLamps;
    private int unitPerLamp;
    private BerlinClockLamp onLamp;
    private BerlinClockLamp offLamp;
    private BerlinClockLamp separatorLamp;
    private boolean hasSeparatorLamp;
    private List<Integer> seperatorLampPositions;
    
    /**
     * @return the noOfLamps
     */
    public int getNoOfLamps() {
        return noOfLamps;
    }
    
    /**
     * @param noOfLamps the noOfLamps to set
     */
    public void setNoOfLamps(int noOfLamps) {
        this.noOfLamps = noOfLamps;
    }
    
    /**
     * @return the unitPerLamp
     */
    public int getUnitPerLamp() {
        return unitPerLamp;
    }
    
    /**
     * @param unitPerLamp the unitPerLamp to set
     */
    public void setUnitPerLamp(int unitPerLamp) {
        this.unitPerLamp = unitPerLamp;
    }
    
    /**
     * @return the onLamp
     */
    public BerlinClockLamp getOnLamp() {
        return onLamp;
    }
    
    /**
     * @param onLamp the onLamp to set
     */
    public void setOnLamp(BerlinClockLamp onLamp) {
        this.onLamp = onLamp;
    }
    
    /**
     * @return the offLamp
     */
    public BerlinClockLamp getOffLamp() {
        return offLamp;
    }
    
    /**
     * @param offLamp the offLamp to set
     */
    public void setOffLamp(BerlinClockLamp offLamp) {
        this.offLamp = offLamp;
    }
    
    /**
     * @return the separatorLamp
     */
    public BerlinClockLamp getSeparatorLamp() {
        return separatorLamp;
    }
    
    /**
     * @param separatorLamp the separatorLamp to set
     */
    public void setSeparatorLamp(BerlinClockLamp separatorLamp) {
        this.separatorLamp = separatorLamp;
    }
    
    /**
     * @return the hasSeparatorLamp
     */
    public boolean hasSeparatorLamp() {
        return hasSeparatorLamp;
    }
    
    /**
     * @param hasSeparatorLamp the hasSeparatorLamp to set
     */
    public void setHasSeparatorLamp(boolean hasSeparatorLamp) {
        this.hasSeparatorLamp = hasSeparatorLamp;
    }
    
    /**
     * @return the seperatorLampPositions
     */
    public List<Integer> getSeperatorLampPositions() {
        return seperatorLampPositions;
    }
    
    /**
     * @param seperatorLampPositions the seperatorLampPositions to set
     */
    public void setSeperatorLampPositions(List<Integer> seperatorLampPositions) {
        this.seperatorLampPositions = seperatorLampPositions;
    } 
   
    
    
}
