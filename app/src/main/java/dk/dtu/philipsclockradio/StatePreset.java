package dk.dtu.philipsclockradio;

public class StatePreset extends StateRadio {
    static Integer frequenceYouWantToAdd=0;

    @Override
    public void onEnterState(ContextClockradio context) {
        frequenceYouWantToAdd=0;
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        checkValidFrequence();
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        frequenceYouWantToAdd++;
        System.out.println("Frekvensen er nu :"+frequenceYouWantToAdd+": Manuel channel scanning");//No garantee that you will find a radio station
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        frequenceYouWantToAdd--;
        System.out.println("Frekvensen er nu :"+frequenceYouWantToAdd+": Manuel channel scanning");//No garantee that you will find a radio station
    }

    private void checkValidFrequence(){
        if(radioStations.getRadioStations().containsKey(frequenceYouWantToAdd)){
            String channel =radioStations.getRadioStations().get(frequenceYouWantToAdd);
            getRadioMode().storeRadioStation(radioStations.getRadioStations().get(frequenceYouWantToAdd));
            System.out.println("You have added: "+channel+" the the: "+getRadioMode().getName()+" store");
        }else{
            System.out.println("No channel on the frequence you want to store");
        }
    }
}
