package dk.dtu.philipsclockradio;

import java.util.HashMap;

public class PresetRadioStations {
    static HashMap<Integer, String > AMStations = new HashMap<>();
    static HashMap<Double, String > FMStations = new HashMap<>();
    static HashMap<String,HashMap> channels = new HashMap<>();



    PresetRadioStations(){
        FMStations.put(102.1,"Radio Silkeborg");
        FMStations.put(88.3,"Radio LOUD!!!");
        AMStations.put(102,"Radio Nova");
        AMStations.put(52,"Radio P3");

        channels.put("FM",FMStations);
        channels.put("AM",AMStations);

    }

    public HashMap getChannels() {
        return channels;
    }
}
