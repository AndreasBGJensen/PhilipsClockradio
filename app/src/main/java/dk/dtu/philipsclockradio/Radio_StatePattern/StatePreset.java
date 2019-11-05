package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.util.ArrayList;

import dk.dtu.philipsclockradio.ContextClockradio;

public class StatePreset extends StateRadio {
    static ArrayList<Double> Frequency = new ArrayList<>();
    static Double frequency;
    static String tag;
    int counter = 0;

//Not implementet yet. Holds predefined radio channels.
    static PresetRadioStations presetStations = new PresetRadioStations();

    ArrayList<java.lang.Object> prefered;

    StateRadio freq;

    StatePreset(StateRadio FM) {

        freq = FM;
if(freq instanceof StateFM) {
    frequency = freq.getFrequency();
    Frequency.add(102.2);
    Frequency.add(88.8);
}
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(frequency.toString());
        context.ui.turnOnTextBlink();
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        counter++;
        context.ui.setDisplayText(Frequency.get(counter % 2).toString());

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        context.setState(FM);
    }

    private void validateTag(StateRadio FM) {
        if (FM instanceof StateFM) {
            tag = "FM";
        } else {
            tag = "AM";
        }
    }

    public ArrayList<Double> getFrequencyArray(){
        return Frequency;
    }
}
