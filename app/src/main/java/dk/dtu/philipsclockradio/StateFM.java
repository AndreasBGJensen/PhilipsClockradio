package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class StateFM extends StateAdapter {
    private static ArrayList<String> stations = new ArrayList<>();
    private static Double FMfrequency = new Double(87.5);
    private String name = "FM";



/*
TUNING RANGE:87.5-108
*/


    StateFM(){}

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
















/*
    @Override
    public void onClick_Preset(ContextClockradio context) {
        presetCount++;
        context.ui.setDisplayText(preset.presetFrequence.get(presetCount).toString());
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(this.preset);
    }
*/
   /* @Override
    public void onLongClick_Sleep(ContextClockradio context) {
        Iterator it = radioStations.getRadioStations().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            stations.add((String)pair.getValue());
            this.preset.presetFrequence.add((Integer)pair.getKey());

        }
    }
*/
    public ArrayList getStations(){
        return stations;
    }






    public void decrementFrequency(ContextClockradio context){
        FMfrequency-=0.1;
        context.ui.setDisplayText(FMfrequency.toString());

    }

    public void incrementFrequency(ContextClockradio context){
        FMfrequency+=0.1;
        context.ui.setDisplayText(FMfrequency.toString());

    }


}
