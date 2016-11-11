/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasmcompiler;

/**
 *
 * @author johannes
 */
public class Command {
    public enum Mnemonic {
        Ende(2, "Ende"), Ipp(2, "I++"), Print(8, "Print");

        private int id; 
        private String cmd;

        private Mnemonic(int id, String cmd) {
            this.id = id;
            this.cmd = cmd;
        }

        public int getId() {
            return id;
        }

        public String getCmd() {
            return cmd;
        }
    }
    
    private Mnemonic mnemonic;
    private byte info;
    private short data;
    private boolean shortInfo;

    public Command(Mnemonic mnemonic, byte info, short data, boolean shortInfo) {
        setMnemonic(mnemonic);
        setInfo(info);
        setData(data);
        setShortInfo(shortInfo);
    }

    public Command(Mnemonic mnemonic, byte info, byte data1, byte data2, boolean shortInfo) {
        short newData = (short) (data2 | (data1 << 8));
        setMnemonic(mnemonic);
        setInfo(info);
        setData(newData);
        setShortInfo(shortInfo);
    }
    
    public  Mnemonic getMnemonic() {
        return mnemonic;
    }

    public final void setMnemonic(Mnemonic mnemonic) {
        this.mnemonic = mnemonic;
    }

    public byte getInfo() {
        return info;
    }

    public final void setInfo(byte info) {
        this.info = info;
    }

    public int getData() {
        return data;
    }

    public final void setData(short data) {
        this.data = data;
    }

    public boolean isShortInfo() {
        return shortInfo;
    }

    public final void setShortInfo(boolean shortInfo) {
        this.shortInfo = shortInfo;
    }
    
    public int buildCommand() {
        int result = 0;
        result = getMnemonic().getId() << 18; // ganz nach links schieben (2 Bytes + 2 = 18)

        int info  = getInfo();
        if (isShortInfo()) {
            info = info & 0x6;
        } else {
            info = info & 0x7;
        }
        info = info << 15;
        
        result = result | info;
        
        int data = getData();
        if (!shortInfo) {
            data = data & 0x7FFF;
        }
        
        result = result | data;
        
        return result;
    }
}
