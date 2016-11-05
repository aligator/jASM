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
    
    private static boolean needNext = false;
    private static Command prevCmd = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EepromSim eeprom = new EepromSim();
        Registers reg = new Registers();
        
        eeprom.loadEEPROM();
        
        int  cmdPos = -1;
        Command cmd = null;
        do {
            if (cmd != null) {
                System.out.println("Cmd: " + cmd.getCmd());
                System.out.println("InfoSmall: " + cmd.getSmallInfo());
                System.out.println("Info: " + cmd.getInfo());
                System.out.println("Data: " + cmd.getData());
                System.out.println("BigData: " + cmd.getBigData());
                System.out.println("Data1: " + cmd.getDataByte1());
                System.out.println("Data2: " + cmd.getDataByte2());
                System.out.println("BigData2: " + cmd.getBigDataByte2());
                System.out.println("------------------------");
                processCmd(cmd, reg);
            }
            cmdPos ++;
            cmd = new Command(cmdPos, eeprom);
        } while (cmd.getCmd() != 0);
        
        
    }
    
    public static void processCmd(Command cmd, Registers reg) {
        switch (cmd.getCmd()) {
            case 0: // End
                // nix
                break;
            case 2: // I++
                switch (cmd.getInfo()) {
                    case 0:
                        int tmp1 = (int) reg.getRegister(cmd.getDataByte2());
                        int tmp2 = cmd.getDataByte1();
                        
                        reg.rx(tmp1 + tmp2);
                        break;
                    case 1:
                        reg.rx((int) (reg.getRegister(cmd.getDataByte2()) + reg.getRegister(cmd.getDataByte1())));
                        break;
                    case 2:
                        if (!needNext) {
                            prevCmd = cmd;
                            needNext = true;
                        } else {
                            reg.rx((int) (prevCmd.getData() + reg.getRegister(cmd.getData())));
                            needNext = false;
                        }
                        break;
                    case 3:
                        if (!needNext) {
                            prevCmd = cmd;
                            needNext = true;
                        } else {
                            reg.rx((int) (prevCmd.getData() + cmd.getData()));
                            needNext = false;
                        }
                        break;
                    case 4:
                        if (!needNext) {
                            prevCmd = cmd;
                            needNext = true;
                        } else {
                            reg.rx((int) (reg.getRegister(prevCmd.getData()) + cmd.getData()));
                            needNext = false;
                        }
                        break;
                    default:
                        break;
                }
                
                
                break;
            case 8: // Print
                switch (cmd.getInfo()) {
                    case 0:
                        // TODO:
                        break;
                    case 4:
                        System.out.println(cmd.getData());
                        break;
                    case 6:
                        System.out.println(reg.getRegister(cmd.getData()));
                        break;
                    default:
                        break;
                }
                break;               
        }
        
        
    }
    
}
