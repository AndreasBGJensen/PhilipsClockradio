package dk.dtu.philipsclockradio.Alarm;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateAdapter;
import dk.dtu.philipsclockradio.StateStandby;

public class StateAlarmSettingMode extends StateAdapter {

    private static Date currentTime = new Date();
    private static Date alarmTime = new Date();
    private static long currenttime;
    private long time;
    Alarm setAlarm;


    public StateAlarmSettingMode(Date date) {
        alarmTime = date;
        currentTime=date;
        time = alarmTime.getTime();
        currenttime = currentTime.getTime();


    }


    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
    }


    @Override
    public void onClick_Hour(ContextClockradio context) {
        time += 3600000;
        setAlarmTime(time);


        updateDisplay(context);
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        time += 60000;
        setAlarmTime(time);

        updateDisplay(context);

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        setAlarm = new Alarm(context);
        long setTime = time-currenttime;
        setAlarm.execute(setTime);
        context.setState(new StateStandby(currentTime));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        setAlarm = new Alarm(context);
        long setTime = time-currenttime;



        setAlarm.execute(setTime);
        context.setState(new StateStandby(currentTime));
    }

    private void updateDisplay(ContextClockradio context){
        context.ui.setDisplayText(alarmTime.toString().substring(11,16));

    }

    private void setAlarmTime(long time){
        alarmTime.setTime(time);
    }



        static class Alarm extends AsyncTask<Long,Void, Void> {

        ContextClockradio context;

        public Alarm(ContextClockradio context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Long... longs) {
            try {
                System.out.println(longs[0]);
                Thread.sleep(longs[0]);
            }catch(Exception e){
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            context.ui.turnOnLED(2);
            context.ui.turnOffTextBlink();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            context.ui.turnOffLED(2);
        }



    }



}
