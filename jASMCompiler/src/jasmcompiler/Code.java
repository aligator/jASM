/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasmcompiler;

import jasmcompiler.Command.Mnemonic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author johannes
 */
public class Code {
    
    private enum Register {
        a(0, "a"),
        b(1, "b"),
        c(2, "c"),
        d(3, "d"),
        e(4, "e"),
        f(5, "f"),
        g(6, "g"),
        h(7, "h"),
        w1(8, "w1"),
        w2(9, "w2"),
        w3(10, "w3"),
        w4(11, "w4"),
        ax(12, "ax"),
        bx(13, "bx"),
        rx(14, "rx"),
        nothing(-1, "nothing");
        
        private byte id; 
        private String cmd;
        
        private Register(int id, String cmd) {
            this.id = (byte)id;
            this.cmd = cmd;
        }
        
        public byte getId() {
            return id;
        }

        public String getCmd() {
            return cmd;
        }
    }
    
    private ArrayList<String> code;
    private int pointer = -1;
    
    public Code(String file) throws FileNotFoundException, IOException {
        BufferedReader br  = new BufferedReader(new FileReader(file));
            
        while (br.ready()) {            
            code.add(br.readLine());
        }
        
        br.close();
    }
    
    private boolean checkNext() {
        pointer++;
        
        if (pointer >= code.size()) {
            return false;
        }
        String line = code.get(pointer);
        
        for (Mnemonic mnemonic : Mnemonic.values()) {
            line = line.trim();
            if (line.startsWith(mnemonic.getCmd())) {
                switch (mnemonic) {
                    case Ipp:
                        String[] splitted = line.split(" ");
                        Register reg1 = extractReg(splitted[1]);
                        Register reg2 = extractReg(splitted[2]);
                        
                        Command cmd;
                        
                        if (reg1 != Register.nothing && isConst(splitted[2])) {
                            // Register + Konstante
                            cmd = new Command(mnemonic, (byte)0, reg1.getId(), (byte)extractConst(splitted[2]), true);
                        } else if (reg1 != Register.nothing && reg2 != Register.nothing) {
                            // Register + Register
                            cmd = new Command(mnemonic, (byte)0, reg1.getId(), reg2.getId(), true);
                        }
                        
                        break;
                    case Print:
                        
                        break;
                    default:
                        break;
                }
                
                
                
                
            }
        }    
        return true;
    }
    
    private boolean isConst(String in) {
        return in.startsWith("#");
    }
    
    private int extractConst(String in) {
        in = in.substring(1);
        return Integer.parseInt(in);
    }
    
    private Register extractReg(String in) {
        for (Register reg : Register.values()) {
            if (in.startsWith(reg.getCmd())) {
                return reg;
            }
        }
        
        return Register.nothing;
    }
    
    
}
