package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateStandby;

public class StateFM extends StateRadio {
    private static ArrayList presets  = new ArrayList<Double>();
    private static double FMfrequency =87.5;
    private String name = "FM";
    StatePreset preset = new StatePreset(this);
    int presetCounter = 0;


/*
TUNING RANGE:87.5-108
*/

    public StateFM(){}

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(Double.toString(FMfrequency));

    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        incrementFrequency(context);
        chectRadioPlay(context);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        decrementFrequency(context);
        chectRadioPlay(context);
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

        if(presetCounter < getPresets().size()){
            context.ui.setDisplayText(showPreset(presetCounter));
            presetCounter++;
            chectRadioPlay(context);
        }else{
            context.ui.setDisplayText(showPreset(0));
            presetCounter=0;
            chectRadioPlay(context);
        }


    }

    @Override
    public ArrayList getFrequency () {

        ArrayList newlist = new ArrayList();
        newlist.add(FMfrequency);
        return newlist;
    }


    public void decrementFrequency(ContextClockradio context){
        FMfrequency-=0.1;
        if(FMfrequency<87.5){
            FMfrequency=108.0;
        }
        context.ui.setDisplayText(Double.toString(FMfrequency));

    }

    public void incrementFrequency(ContextClockradio context){
        FMfrequency+=0.1;
        if(FMfrequency>=108.0){
            FMfrequency=87.5;
        }
        context.ui.setDisplayText(Double.toString(FMfrequency));

    }


    private String showPreset(int i){
        int size = getPresets().size();
        FMfrequency =(Double) getPresets().get(i%size);
        return getPresets().get(i%size).toString();
    }

    public List getPresets(){return presets;
    }

    private void chectRadioPlay(ContextClockradio context){
        for(int i = 0; i<getPresets().size();i++){
            if(FMfrequency==((Double)getPresets().get(i))){
                context.ui.toggleRadioPlaying();

            }
        }
    }
}
