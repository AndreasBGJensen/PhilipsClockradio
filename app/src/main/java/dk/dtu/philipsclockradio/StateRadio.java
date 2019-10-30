package dk.dtu.philipsclockradio;

public class StateRadio extends StateAdapter {
    private static String radioType = "AM";


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
        System.out.println("Manuel channel scanning");
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }


    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        System.out.println("Finding the radio station with the strongest reception");
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
}
