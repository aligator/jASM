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
public class Command {
    private int  cmdNr = -1;
    private byte cmd = -1;
    private byte info = -1;
    private byte smallInfo = -1;
    private short data = -1;
    private short bigData = -1;
    
    public Command(int cmdNr, EepromSim eeprom) {
        byte[] data = new byte[3];
        
        for (int i = 0; i < 3; i++) {
            data[i] = eeprom.readEEPROM(cmdNr * 3 + i);
        }
        
        this.cmdNr = cmdNr;
        this.cmd = extractCommand(cmdNr, data);
        this.info = extractInfo(cmdNr, data);
        this.smallInfo = extractSmallInfo(cmdNr, data);
        this.data = extractData(cmdNr, data);
        this.bigData = extractBigData(cmdNr, data);
    }
    
    
    private static byte extractCommand(int cmdNr, byte[] data) {
        byte cmd  = data[0];
        
        cmd = (byte) (cmd >> 2);
        
        return cmd;
    }
    
    private static byte extractInfo(int cmdNr, byte[] data) {
        byte info  = extractSmallInfo(cmdNr, data);
                
        info = (byte) (info << 1);
        
        info = (byte) (info | ((data[1] & 0x8000) >> 3));
        return info;
    }
    
    private static byte extractSmallInfo(int cmdNr, byte[] data) {
        byte info  = data[0];
        
        info = (byte) (info & 0x0003);
        
        return info;
    }
    
    private static short extractBigData(int cmdNr, byte[] data) {
        short useData  = (short)data[1];
        useData = (short) (useData << 8);        
        useData = (short) (useData | (short)data[2]);
        
        
        return useData;
    }
    
    private static short extractData(int cmdNr, byte[] data) {
        short useData  = extractBigData(cmdNr, data);
                
        useData = (short) (useData & 0x7FFF);
        
        return useData;
    }

    public int getCmdNr() {
        return cmdNr;
    }

    public byte getCmd() {
        return cmd;
    }

    public byte getInfo() {
        return info;
    }

    public byte getSmallInfo() {
        return smallInfo;
    }

    public short getData() {
        return data;
    }
    
    public byte getDataByte1() {
        return (byte) (data & 0xFF);
    }
    
    public byte getDataByte2() {
        return (byte) ((data & 0xFF00) >> 8);
    }
    
    public byte getBigDataByte2() {
        return (byte) ((bigData & 0xFF00) >> 8);
    }
    
    public short getBigData() {
        return bigData;
    }
}
