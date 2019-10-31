package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class StateFM extends StateRadio {
    private static ArrayList<String> stations = new ArrayList<>();
    private static Integer frequency = new Integer(0);
    private String name = "FM";


    StateFM(){}

    @Override
    public void onEnterState(ContextClockradio context) {


    }
    @Override
    public void setFrequency(Integer value){
            frequency=value;
    }

    @Override
    public Integer getFrequency(){
            return frequency;
    }
    @Override

    public void storeRadioStation(String station){stations.add(station); }

    @Override
    public void printStation(){
    for (String e:stations
         ) {
        System.out.println(e);

    }



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
