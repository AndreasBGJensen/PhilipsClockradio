package dk.dtu.philipsclockradio;

import android.content.Intent;

public class StateRadio extends StateAdapter {
    static StateAM AM = new StateAM();
    static StateFM FM = new StateFM();
    static StateRadio radioMode = AM;
    static StatePreset preset = new StatePreset();

PresetRadioStations radioStations = new PresetRadioStations();



    StateRadio(){}


    @Override
    public void onEnterState(ContextClockradio context) {


    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        radioMode();
        context.setState(radioMode);
    }



    @Override
    public void onClick_Hour(ContextClockradio context) {
        scanAddToFrequency(-1);
        printFrequency();
        findRadioStation();
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        scanAddToFrequency(1);
        printFrequency();
        findRadioStation();
    }


    /*
    Jeg holder logikken i parentclass da jeg ikke har lavet forskel på klasserne FM og AM.
    Hvis der var forskel på disse ville jeg flytte logikken ned i en af disse klasser.
     */
    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        for(int i = 0; i<50;i++){
            findRadioStation();
            if(findRadioStation()){
               listen();
            break;}
        }
    }
    public boolean findRadioStation(){
        if(radioStations.getRadioStations().containsKey(radioMode.getFrequency())){
            System.out.println("Kanal fundet:");
            listen();
            return true;
        }else{
            System.out.println("Ingen kanal");
            return false;
        }

    }


    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }


    @Override
    public void onLongClick_Preset(ContextClockradio context) { context.setState(preset);}

  private void radioMode(){

        if(radioMode instanceof StateAM){
            radioMode= FM;

        }else{
            radioMode= AM;
        }
    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) { }

    public Integer getFrequency(){return radioMode.getFrequency();}

    public void setFrequency(Integer value){ }

    public void storeRadioStation(String station){ }

    public void printStation(){ }

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

}
