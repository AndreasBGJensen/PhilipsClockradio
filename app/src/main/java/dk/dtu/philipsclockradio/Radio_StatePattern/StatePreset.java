package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.philipsclockradio.ContextClockradio;

public class StatePreset extends StateRadio {
    static List Frequency;
    static Double FMfrequency;
    static int AMFrequency;
    static String tag;
    int counter = 0;

//Not implementet yet. Holds predefined radio channels.
    static PresetRadioStations presetStations = new PresetRadioStations();

    ArrayList<java.lang.Object> prefered;

    StateRadio freq;

    StatePreset(StateRadio FM) {

        freq = FM;

        if(freq instanceof StateFM) {
           ((StateFM) freq).getPresets().add(102.2);
            ((StateFM) freq).getPresets().add(88.8);

            }else if (freq instanceof StateAM){
            ((StateAM) freq).getPresets().add(1000);
            ((StateAM) freq).getPresets().add(1200);

        }
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(freq.getFrequency().get(0).toString());
        context.ui.turnOnTextBlink();
        if(freq instanceof StateFM) {
            FMfrequency = (Double)freq.getFrequency().get(0);
        }else if (freq instanceof StateAM){
            AMFrequency = (Integer) freq.getFrequency().get(0);
        }

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        counter++;
        context.ui.setDisplayText(Frequency.get(counter % 2).toString());

    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

        freq.onClick_Hour(context);
        context.ui.setDisplayText(freq.getFrequency().get(0).toString());
    }


    @Override
    public void onClick_Min(ContextClockradio context) {

        freq.onClick_Min(context);
        context.ui.setDisplayText(freq.getFrequency().get(0).toString());
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        freq.getPresets().add(freq.getFrequency().get(0));
        context.ui.turnOffTextBlink();
        context.setState(freq);
    }

    private void validateTag(StateRadio FM) {
        if (FM instanceof StateFM) {
            tag = "FM";
        } else {
            tag = "AM";
        }
    }

}
