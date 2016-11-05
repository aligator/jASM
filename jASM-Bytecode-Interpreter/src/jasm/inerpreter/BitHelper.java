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
public class BitHelper {
    public static void printWord(int w) {
        System.out.print(Integer.toBinaryString(w & 0xFFFF));

    }
    
    public static void printDWord(int w) {
        System.out.print(Integer.toBinaryString(w));

    }
    
    public static short toWord(byte a, byte b) {
        short w1 = a;
        w1 = (short) (w1 << 8);
        w1 = (short) (w1 | (b & 0xFF));
        return (short)w1;
    }
    
    public static int toDword(int a, int b) {
        int w1 = a;
        w1 = w1 << 16;
        w1 = w1 | (b & 0xFFFF);
        return w1;
    }
}
