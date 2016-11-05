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
public class Registers {
    private byte a;
    private byte b;
    private byte c;
    private byte d;
    
    private byte e;
    private byte f;
    private byte g;
    private byte h;
    
    public void printWord(int w) {
        System.out.print(Integer.toBinaryString(w & 0xFFFF));

    }
    
    public void printDWord(int w) {
        System.out.print(Integer.toBinaryString(w));

    }
    
    public int toWord(byte a, byte b) {
        int w1 = a;
        w1 = w1 << 8;
        w1 = w1 | (b & 0xFF);
        w1 = w1 & 0xFFFF;
        return w1;
    }
    
    public int toDword(int a, int b) {
        int w1 = a;
        w1 = w1 << 16;
        w1 = w1 | (b & 0xFFFF);
        return w1;
    }
    
    public byte a() {
        return a;
    }
    
    public void a(byte a) {
        this.a = a;
    }
    
    public int w1() {
        return toWord(a, b);
    }
    
    public void w1(int w1) {
        if (w1 <= 65535) {
            a = (byte) (w1 >> 8);
            b =   (byte) (w1 & 0xFF);
        } else {
            throw new RuntimeException("not a word");
        }
    }
    
    public int w2() {
        return toWord(c, d);
    }
    
    public void w2(int w2) {
        if (w2 <= 65535) {
            c = (byte) (w2 >> 8);
            d =   (byte) (w2 & 0xFF);
        } else {
            throw new RuntimeException("not a word");
        }
    }
    
    public int w3() {
        return toWord(e, f);
    }
    
    public void w3(int w3) {
        if (w3 <= 65535) {
            e = (byte) (w3 >> 8);
            f =   (byte) (w3 & 0xFF);
        } else {
            throw new RuntimeException("not a word");
        }
    }
    
    public int w4() {
        return toWord(g, h);
    }
    
    public void w4(int w4) {
        if (w4 <= 65535) {
            g = (byte) (w4 >> 8);
            h =   (byte) (w4 & 0xFF);
        } else {
            throw new RuntimeException("not a word");
        }
    }
    
    public int ax() {
        return toDword(w1(), w2());
    }
    
    public void ax(int ax) {
        w1(ax >> 16);
        w2(ax & 0xFFFF);
    }
    
    public int bx() {
        return toDword(w3(), w4());
    }
    
    public void bx(int bx) {
        w3(bx >> 16);
        w4(bx & 0xFFFF);
    }
    
    public byte b() {
        return b;
    }
    
    public void b(byte b) {
        this.b = b;
    }
    public byte c() {
        return c;
    }
    
    public void c(byte c) {
        this.c = c;
    }
    public byte d() {
        return d;
    }
    
    public void d(byte d) {
        this.d = d;
    }
    public byte e() {
        return e;
    }
    
    public void e(byte e) {
        this.e = e;
    }
    public byte f() {
        return f;
    }
    
    public void f(byte f) {
        this.f = f;
    }
    public byte g() {
        return g;
    }
    
    public void g(byte g) {
        this.g = g;
    }
    public byte h() {
        return h;
    }
    
    public void h(byte h) {
        this.h = h;
    }
}
