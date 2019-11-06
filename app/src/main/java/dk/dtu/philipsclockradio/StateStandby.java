package dk.dtu.philipsclockradio;

import android.os.Handler;
import java.util.Date;

import dk.dtu.philipsclockradio.Alarm.StateAlarmSettingMode;
import dk.dtu.philipsclockradio.Radio_StatePattern.StateRadio;
import dk.dtu.philipsclockradio.SleepFunction.StateSleep;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    public StateStandby(Date time){
        this.mTime = time;
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);
            } finally {
                mHandler.postDelayed(mSetTime, 60000);
            }
        }
    };

    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateRadio());
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateAlarmSettingMode(context.getTime()));
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateAlarmSettingMode(context.getTime()));
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleep());
    }
}

