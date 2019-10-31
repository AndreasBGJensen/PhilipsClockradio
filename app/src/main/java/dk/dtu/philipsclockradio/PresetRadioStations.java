package dk.dtu.philipsclockradio;

import java.util.HashMap;

public class PresetRadioStations {
    static HashMap<Integer, String > radioStations = new HashMap<>();

    PresetRadioStations(){
        radioStations.put(1,"Radio Nova");
        radioStations.put(52,"Radio P3");
    }

    public HashMap<Integer, String> getRadioStations() {
        return radioStations;
    }
}
