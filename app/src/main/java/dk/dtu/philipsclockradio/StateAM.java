package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class StateAM extends StateRadio {
    private static ArrayList<String> stations = new ArrayList<>();
    private static Integer frequency = new Integer(0);
    private String name = "FM";

    StateAM() {
    }


    @Override
    public void onEnterState(ContextClockradio context) {


    }

    @Override
    public Integer getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(Integer value) {
        frequency = value;
    }

    @Override
    public void storeRadioStation(String station) {
        stations.add(station);
    }


    @Override
    public void printStation() {
        for (String e : stations
        ) {
            System.out.println(e);

        }
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        scanAddToFrequency(1);

        System.out.println("Frekvensen er nu :"+getFrequency()+": Manuel channel scanning");//No garantee that you will find a radio station
        findRadioStation();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        scanAddToFrequency(-1);

        System.out.println("Frekvensen er nu :"+getFrequency()+": Manuel channel scanning");//No garantee that you will find a radio station
        findRadioStation();
    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {
        Iterator it = radioStations.getRadioStations().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            stations.add((String)pair.getValue());
            printStation();
        }

    }

    public ArrayList getStations(){
        return stations;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void printFrequency(){
        System.out.println(frequency);
    }
}
