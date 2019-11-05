package dk.dtu.philipsclockradio;

public class StateSleep extends StateAdapter {
    int[] sleepTime = {120, 90, 60, 30, 15,0};
    private  int currentSleepTime = 0; //Defines if the alarm is set.
    private static boolean idleForTooLong = false;
    private static int counter = 0;
    private static int idelCounter = 0;
    ContextClockradio mContext;
    SleepIdle sleepIdle;
    SleepSet setSleep;


    StateSleep() {




    }


    @Override
    public void onEnterState(ContextClockradio context) {
        mContext=context;
        setSleepTime();
        sleepIdle = SleepIdle.getInstance(getSleepTime(),mContext, setSleep);
        setSleep = SleepSet.getInstance(getSleepTime(),mContext, sleepIdle);
        updateDisplay(context);
        System.out.println("Updatet sleep");


    }

    @Override
    public void onExitState(ContextClockradio context) {



    }

    @Override
    public void onClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        AddcheckCounter();
        setSleepTime();
        sleepIdle = SleepIdle.getInstance(getSleepTime(),mContext, setSleep);
        setSleep = SleepSet.getInstance(getSleepTime(),mContext, sleepIdle);

        updateDisplay(context);


    }





    private void setSleepTime() {
        currentSleepTime = sleepTime[counter];

    }

    private void AddcheckCounter() {
        counter++;
        if (counter == sleepTime.length) {
            counter = 0;
        }

    }

    private void updateDisplay(ContextClockradio context){
        context.ui.setDisplayText(Integer.toString(sleepTime[counter]));

    }


public int getSleepTime(){
        return currentSleepTime;
}



}