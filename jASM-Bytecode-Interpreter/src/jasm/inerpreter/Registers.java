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
    
    private int rx;
    
    public Integer getRegister(int id) {
        switch (id) {
            case 0:
                return (int)Integer.toUnsignedLong(a());
            case 1:
                return (int)Integer.toUnsignedLong(b());
            case 2:
                return (int)Integer.toUnsignedLong(c());
            case 3:
                return (int)Integer.toUnsignedLong(d());
            case 4:
                return (int)Integer.toUnsignedLong(e());
            case 5:
                return (int)Integer.toUnsignedLong(f());
            case 6:
                return (int)Integer.toUnsignedLong(g());
            case 7:
                return (int)Integer.toUnsignedLong(h());
            case 8:
                return (int)Integer.toUnsignedLong(w1());
            case 9:
                return (int)Integer.toUnsignedLong(w2());
            case 10:
                return (int)Integer.toUnsignedLong(w3());
            case 11:
                return (int)Integer.toUnsignedLong(w4());
            case 12:
                return (int)Integer.toUnsignedLong(ax());
            case 13:
                return (int)Integer.toUnsignedLong(bx());
            case 14:
                return (int)Integer.toUnsignedLong(rx());
            default:
                return null;
        }
    }
    
    public byte a() {
        return a;
    }
    
    public void a(byte a) {
        this.a = a;
    }
    
    public short w1() {
        return BitHelper.toWord(a, b);
    }
    
    public void w1(short w1) {
        a = (byte) (w1 >> 8);
        b =   (byte) (w1 & 0xFF);
    }
    
    public short w2() {
        return BitHelper.toWord(c, d);
    }
    
    public void w2(short w2) {
        c = (byte) (w2 >> 8);
        d =   (byte) (w2 & 0xFF);
    }
    
    public short w3() {
        return BitHelper.toWord(e, f);
    }
    
    public void w3(short w3) {
        e = (byte) (w3 >> 8);
        f =   (byte) (w3 & 0xFF);
        
    }
    
    public short w4() {
        return BitHelper.toWord(g, h);
    }
    
    public void w4(short w4) {
        g = (byte) (w4 >> 8);
        h =   (byte) (w4 & 0xFF);
        
    }
    
    public int ax() {
        return BitHelper.toDword(w1(), w2());
    }
    
    public void ax(int ax) {
        w1((short) (ax >> 16));
        w2((short) (ax & 0xFFFF));
    }
    
    public int bx() {
        return BitHelper.toDword(w3(), w4());
    }
    
    public void bx(int bx) {
        w3((short) (bx >> 16));
        w4((short) (bx & 0xFFFF));
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

    public int rx() {
        return rx;
    }

    public void rx(int rx) {
        this.rx = rx;
    }
}
