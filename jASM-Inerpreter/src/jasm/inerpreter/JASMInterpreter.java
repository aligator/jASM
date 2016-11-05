/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasm.inerpreter;

/**
 *
 * @author johannes
 */
public class JASMInterpreter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EepromSim eeprom = new EepromSim();
        Registers reg = new Registers();
        
        eeprom.loadEEPROM();
        
        int  cmdPos = -1;
        long cmd = -1;
        byte info = -1;
        do {
            if (cmd != -1) {
                System.out.println(cmd);
                System.out.println(info);
            }
            cmdPos ++;
            cmd = Integer.toUnsignedLong(eeprom.getCommand(cmdPos));
            info = eeprom.getInfo(cmdPos);
        } while (cmd != 0);
        
        
    }
    
}
