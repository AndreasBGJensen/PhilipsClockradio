package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.util.ArrayList;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateStandby;

public class StateFM extends StateRadio {
    private static ArrayList<String> stations = new ArrayList<>();
    private static Double FMfrequency = new Double(87.5);
    private String name = "FM";
    StatePreset preset = new StatePreset(this);
    int presetCounter =0;

/*
TUNING RANGE:87.5-108
*/

    public StateFM(){}

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(FMfrequency.toString());
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        incrementFrequency(context);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        decrementFrequency(context);
    }


    @Override
    public void onLongClick_Min(ContextClockradio context) {
        for(int i = 0; i<50;i++){
            incrementFrequency(context);
        }
    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        for(int i = 0; i<50;i++){
            decrementFrequency(context);
        }
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        //Changes the Tuning range AM/FM

        context.setState(new StateAM());
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(this.preset);
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        context.ui.turnOffTextBlink();

        context.ui.setDisplayText(showPreset(presetCounter).toString());
        presetCounter++;
    }

    @Override
    public Double getFrequency () {
        return FMfrequency;
    }

    private Double showPreset(int i){
        int size = preset.getFrequencyArray().size();
        return preset.getFrequencyArray().get(i%size);
    }

    public void decrementFrequency(ContextClockradio context){
        FMfrequency-=0.1;
        if(FMfrequency<87.5){
            FMfrequency=108.0;
        }
        context.ui.setDisplayText(FMfrequency.toString());

    }

    public void incrementFrequency(ContextClockradio context){
        FMfrequency+=0.1;
        if(FMfrequency>=108.0){
            FMfrequency=87.5;
        }
        context.ui.setDisplayText(FMfrequency.toString());

    }
}
