package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class StateAM extends StateAdapter {


    private static ArrayList<String> stations = new ArrayList<>();
    private static Integer AMfrequency = new Integer(530);
    private String name = "AM";

    private int presetCount = -1;

    StateAM() {
    }

    /*
    TUNING RANGE:530-1700
    */
    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(AMfrequency.toString());


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

        context.setState(new StateFM());
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    /* @Override
    public void printStation() {
        for (String e : stations
        ) {
            System.out.println(e);
        }
    }
*/
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

/*
    @Override
    public void onLongClick_Sleep(ContextClockradio context) {
        Iterator it = radioStations.getRadioStations().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            stations.add((String)pair.getValue());
            this.preset.presetFrequence.add((Integer)pair.getKey());
        }

    }
*/



    public void decrementFrequency(ContextClockradio context){
        AMfrequency--;
        context.ui.setDisplayText(AMfrequency.toString());

    }

    public void incrementFrequency(ContextClockradio context){
        AMfrequency++;
        context.ui.setDisplayText(AMfrequency.toString());

    }



    public ArrayList getStations(){
        return stations;
    }





}
