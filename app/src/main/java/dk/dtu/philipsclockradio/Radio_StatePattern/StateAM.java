package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateStandby;

public class StateAM extends StateRadio {


    private static ArrayList<Integer> presets = new ArrayList<Integer>();
    private static Integer AMfrequency = 530;
    private String name = "AM";
    StatePreset preset = new StatePreset(this);
    int presetCounter =0;


    public StateAM() {
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
        chectRadioPlay(context);    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        decrementFrequency(context);
        chectRadioPlay(context);
    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {
            getPresets().add(1000);
           getPresets().add(1200);
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
    public void onClick_Preset(ContextClockradio context) {

        if(presetCounter < getPresets().size()){
            context.ui.setDisplayText(showPreset(presetCounter));
            presetCounter++;
            chectRadioPlay(context);
        }else{
            context.ui.setDisplayText(showPreset(0));
            presetCounter=1;
            chectRadioPlay(context);
        }


    }



    private String showPreset(int i){
        int size = getPresets().size();
        AMfrequency = (Integer)getPresets().get(i%size);
        return getPresets().get(i%size).toString();
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

    public List getPresets(){return presets;}


    @Override
    public ArrayList getFrequency () {

        ArrayList newlist = new ArrayList();
        newlist.add(AMfrequency);
        return newlist;
    }


    private void chectRadioPlay(ContextClockradio context){
        for(int i = 0; i<getPresets().size();i++){
             if(((Integer)getPresets().get(i))==AMfrequency){
                context.ui.toggleRadioPlaying();

            }
        }
    }
}
