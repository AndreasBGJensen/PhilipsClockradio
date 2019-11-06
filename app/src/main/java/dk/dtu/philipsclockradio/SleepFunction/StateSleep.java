package dk.dtu.philipsclockradio.SleepFunction;

import android.os.SystemClock;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateAdapter;
import dk.dtu.philipsclockradio.SleepFunction.sleep_Singleton.SleepIdle;
import dk.dtu.philipsclockradio.SleepFunction.sleep_Singleton.SleepSet;

public class StateSleep extends StateAdapter {
    int[] sleepTime = {120, 90, 60, 30, 15,0};
    private  int currentSleepTime = 0; //Defines if the alarm is set.
    private static int counter = 0;
    ContextClockradio mContext;
    SleepIdle sleepIdle;
    SleepSet setSleep;


    public StateSleep() { }


    @Override
    public void onEnterState(ContextClockradio context) {
        mContext=context;
        setSleepTime();
        sleepIdle = SleepIdle.getInstance(mContext, setSleep);

        setSleep = SleepSet.getInstance(getSleepTime(),mContext, sleepIdle);
        updateDisplay(context);
        System.out.println("Updatet sleep");


    }


    @Override
    public void onClick_Sleep(ContextClockradio context) {
        AddcheckCounter();
        setSleepTime();

        sleepIdle = SleepIdle.getInstance(mContext, setSleep);
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
        if(sleepTime[counter]==0){
            context.ui.setDisplayText("OFF");
        }else {
            context.ui.setDisplayText(Integer.toString(sleepTime[counter]));
        }

    }


public int getSleepTime(){
        return currentSleepTime;
}



}