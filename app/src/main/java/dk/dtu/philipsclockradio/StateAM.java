package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class StateAM extends StateRadio {


    private static ArrayList<String> stations = new ArrayList<>();
    private static int AMfrequency = 530;
    private String name = "AM";
    StatePreset preset = new StatePreset(this);
    int presetCounter =0;


    StateAM() {
    }

    /*
    TUNING RANGE:530-1700
    */
    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(Integer.toString(AMfrequency));
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

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(this.preset);
    }

    @Override
    public Double getFrequency(){
        return Double.valueOf(Integer.toString(AMfrequency));
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        context.ui.turnOffTextBlink();

        context.ui.setDisplayText(showPreset(presetCounter));
        presetCounter++;
    }

    private String showPreset(int i){
        int size = preset.getFrequencyArray().size();
        return preset.getFrequencyArray().get(i%size).toString();
    }

    public void decrementFrequency(ContextClockradio context){
        AMfrequency--;
        if(AMfrequency<530){
            AMfrequency=1700;
        }
        context.ui.setDisplayText(Integer.toString(AMfrequency));
    }

    public void incrementFrequency(ContextClockradio context){
        AMfrequency++;
        if(AMfrequency>1700){
            AMfrequency=530;
        }
        context.ui.setDisplayText(Integer.toString(AMfrequency));

    }
}
