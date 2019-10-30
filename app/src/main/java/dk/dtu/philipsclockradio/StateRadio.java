package dk.dtu.philipsclockradio;

public class StateRadio extends StateAdapter {
    public static String radioType = "AM";


    StateRadio(){}


    @Override
    public void onEnterState(ContextClockradio context) {
        context.setState(new StateRadio());
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(context.setState(new StateStandby(context.getTime())));
    }
}
