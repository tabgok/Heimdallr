/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

/**
 * Generators create data from sources outside of this program.
 * @author tbick
 */
public interface Generator {
    /**
     * 
     * @param msPeriod (0) indicates a one-time generation. >0 represents the 
     * period in milliseconds
     */
    public void generateData(long msPeriod);
}
