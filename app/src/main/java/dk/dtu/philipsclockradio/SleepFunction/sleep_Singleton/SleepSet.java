package dk.dtu.philipsclockradio.SleepFunction.sleep_Singleton;

import android.os.AsyncTask;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateStandby;

/*
This class make use of the singleton pattern.
 */

public class SleepSet {

    static SleepSet sleep = null;
    static ContextClockradio context;
    private static int timer;
    private static AsyncTask1 asynC;
    private static SleepIdle idleState;


    private SleepSet(){}




    public static SleepSet getInstance(int sleeptime,ContextClockradio con, SleepIdle idle ){
        System.out.println("GET INSTANCE");

        //Canceling the SleepIdle thread
        idleState = idle;


//Checks if there is an existing singleton
        if(sleep==null){
            sleep = new SleepSet();
            timer = sleeptime;
            context = con;
            asynC = new AsyncTask1();

            asynC.execute();
            turnOnSleep(context);

            return sleep;
        }
        asynC.cancel(true);
        asynC = new AsyncTask1();
        asynC.execute();
        turnOnSleep(context);
        timer = sleeptime;
        return sleep;
    }


    private static void turnOnSleep(ContextClockradio context){
        if(timer==0) {
            context.ui.turnOffLED(3);

        }else{
            context.ui.turnOnLED(3);
        }

    }

    static class AsyncTask1 extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            while (timer != 0) {
                timer--;
                System.out.println(timer);
                try {
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.getMessage();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            context.ui.turnOffLED(3);
            context.setState(new StateStandby(context.getTime()));
        }
    }


    public AsyncTask1 getAsyncThread(){
        if(asynC!=null){
        return asynC;
        }
        return null;
    }
}
