package dk.dtu.philipsclockradio.Radio_StatePattern;

import java.util.List;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.R;
import dk.dtu.philipsclockradio.State;
import dk.dtu.philipsclockradio.StateAdapter;

public class StateRadio implements State {
    static StateRadio AM = new StateAM();
    static StateRadio FM = new StateFM();


    PresetRadioStations radioStations = new PresetRadioStations();

    public StateRadio() {
    }


    @Override
    public void onEnterState(ContextClockradio context) {
        context.setState(AM);

    }

    @Override
    public void onExitState(ContextClockradio context) {

    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onClick_Power(ContextClockradio context) {

    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Snooze(ContextClockradio context) {

    }


    public List getFrequency(){return null;}


    public List getPresets(){ return null; }




}













































/*
    @Override
    public void onEnterState(ContextClockradio context) {
        System.out.println("radioMode is: ");
        context.setState(radioMode);
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        //Changes the Tuning range AM/FM

        context.setState(radioMode);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        scanAddToFrequency(-1);
        printFrequency();
        findRadioStation();
        updateDisplay(context);
    }
}


    public void onClick_Min(ContextClockradio context) {
        scanAddToFrequency(1);
        printFrequency();
        findRadioStation();
        updateDisplay(context);
    }


    Jeg holder logikken i parentclass da jeg ikke har lavet forskel på klasserne FM og AM.
    Hvis der var forskel på disse ville jeg flytte logikken ned i en af disse klasser.

   @Override
    public void onLongClick_Hour(ContextClockradio context) {
        for(int i = 0; i<50;i++){
            findRadioStation();
            if(findRadioStation()){
               listen();
            break;}
        }
    }



    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }



  private void radioMode(){

        if(radioMode instanceof StateAM){
            radioMode= FM;

        }else{
            radioMode= AM;
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {}

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {}

    @Override
    public void onClick_Preset(ContextClockradio context) {}

    public Integer getFrequency(){return radioMode.getFrequency();}

    public void setFrequency(Integer value){ }

    public void storeRadioStation(String station){ }

    //public void printStation(){ }

    public void scanAddToFrequency(Integer i){
        Integer currentFrequency = radioMode.getFrequency();
        currentFrequency = currentFrequency+i;
        radioMode.setFrequency(currentFrequency);
    }

    private void listen(){
        System.out.println(radioStations.getRadioStations().get(radioMode.getFrequency()));

    }

    public StateRadio getRadioMode(){
        return radioMode;
    }

    public String getName(){return null;}

    public void printFrequency(){}

    public void updateDisplay(ContextClockradio context){
        context.ui.setDisplayText(getRadioMode().getFrequency().toString());
    }


}
*/
