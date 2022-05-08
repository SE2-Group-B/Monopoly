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
        if(i<kartenStapel.size()-1){
            Karte k=kartenStapel.get(i);
            setIndex(i+1);
            return k;
        }else if(i==kartenStapel.size()-1){ //letzte Karte im Stapel -> shuffle & index 0 setzten
            Karte k=kartenStapel.get(i);
            shuffle();
            setIndex(0);
            return k;
        }
        else{//index out of bounce
            setIndex(0);
            i=0;
            shuffle();
            Karte k=kartenStapel.get(getIndex());
            setIndex(i+1);
            return k;
        }

    }

}
