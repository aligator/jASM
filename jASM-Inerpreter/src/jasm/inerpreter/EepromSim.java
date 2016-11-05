/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasm.inerpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johannes
 */
public class EepromSim {
    public static final int MAX_ADDRESS = 32767;
    private final byte[] eeprom;

    public EepromSim() {
        this.eeprom = new byte[32767];
    }
        
    byte readEEPROM(int eeaddress) {
        return  eeprom[eeaddress];
    }
    
    void writeEEPROM(int eeaddress, byte data) {
        eeprom[eeaddress] = data;
    }
    
    void saveEEPROM() {
        FileOutputStream fos = null;
        try {
            File file = new File("eeprom.hex");
            
            if (file.exists()) {
                file.delete();
            }
            
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(eeprom, 0, MAX_ADDRESS);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void loadEEPROM() {
        FileInputStream fis = null;
        try {
            File file = new File("eeprom.hex");
            if (file.exists()) {

                fis = new FileInputStream(file);
                fis.read(eeprom, 0, MAX_ADDRESS);
                fis.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(EepromSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
