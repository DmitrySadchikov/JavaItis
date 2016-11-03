package ru.itis;

public class Reverse {

    private String rev;

    public Reverse(String s) {
        char[] c = s.toCharArray();
        rev = "" + c[c.length - 1];
        for(int i = c.length - 2; i >= 0; i--) {
            rev += c[i];
        }
    }

    @Override
    public String toString() {
        return rev;
    }
}
