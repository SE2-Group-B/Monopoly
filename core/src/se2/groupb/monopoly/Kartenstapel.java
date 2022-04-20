package se2.groupb.monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Kartenstapel {
    ArrayList<Karte> kartenStapel;
    int index;


    public Kartenstapel(){
        kartenStapel=new ArrayList<>();
        index=0;
    }

    public void add(Karte k){
        kartenStapel.add(k);
    }

    public void shuffle(){
        Collections.shuffle(kartenStapel);
    }

    public ArrayList<Karte> getKartenStapel() {
        return kartenStapel;
    }

    public void setKartenStapel(ArrayList<Karte> kartenStapel) {
        this.kartenStapel = kartenStapel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Karte getNextCard(){
        int i=getIndex();
        Karte k=kartenStapel.get(i);
        setIndex(i++);
        return k;
    }

}
