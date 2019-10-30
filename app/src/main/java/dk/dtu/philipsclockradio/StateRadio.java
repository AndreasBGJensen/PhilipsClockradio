package dk.dtu.philipsclockradio;

public class StateRadio extends StateAdapter {
    private static String radioType = "AM";
private static int FMstations =0;
    private static int AMstations =0;
    private static int storedFrequency = 0;
    private int frequencyCount = 0;

    StateRadio(){}


    @Override
    public void onEnterState(ContextClockradio context) {

            System.out.println(radioMode());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        this.onEnterState(context);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        frequencyCount++;
        System.out.println("Manuel channel scanning");//No garantee that you will find a radio station
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        frequencyCount--;
        System.out.println("Manuel channel scanning");//No garantee that you will find a radio station
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }


    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        nextFrequency();
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {


    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        setStoredFrequency();
        System.out.println("You have stored the frequency: "+storedFrequency);
    }

    private String radioMode(){
        String mode;
        if(getRadioType().equals("AM")){
            mode="FM";
            setRadioType(mode);
        }else{
            mode="AM";
            setRadioType(mode);
        }
        return mode;
    }

    private String getRadioType(){
        return radioType;
    }

    private void setRadioType(String mode){
        radioType=mode;
    }

    private void nextFrequency(){
        if(getRadioType().equals("AM")){
            AMstations++;
        }else{
            FMstations++;
        }
        System.out.println("Finding the radio station with the strongest reception");
    }

    private void setStoredFrequency(){
        storedFrequency=frequencyCount;

    }


}
