/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasmcompiler;

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
    private enum Mnemonic {
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
    
    private enum Register {
        a(0, "a"),
        b(0, "b"),
        c(0, "c"),
        d(0, "d"),
        e(0, "e"),
        f(0, "f"),
        g(0, "g"),
        h(0, "h"),
        w1(0, "w1"),
        w2(0, "w2"),
        w3(0, "w3"),
        w4(0, "w4"),
        ax(0, "ax"),
        bx(0, "bx"),
        rx(0, "rx");

        private int id; 
        private String cmd;
        
        private Register(int id, String cmd) {
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
                        if (isReg(splitted[1]) && isConst(splitted[2])) {
                            // Register + Konstante
                            
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
    
    private boolean isReg(String in) {
        for (Register reg : Register.values()) {
            if (in.startsWith(reg.getCmd())) {
                return true;
            }
        }
        
        return false;
    }
}
