package dk.dtu.philipsclockradio.SleepFunction.sleep_Singleton;

import android.os.AsyncTask;

import dk.dtu.philipsclockradio.ContextClockradio;
import dk.dtu.philipsclockradio.StateStandby;

public class SleepIdle {

    private static SleepIdle idle = null;
    static int idletimer = 5;
    private static AsyncTaskIdle asynSleep;
    private static ContextClockradio mContext;
private static SleepSet sleepset;
/*
Returning singleton which represent a thread of the idletime.
 */
    public static SleepIdle getInstance(int sleeptime,ContextClockradio con, SleepSet set ){
        System.out.println("GET INSTANCE");
        sleepset = set;

        //Canceling the SleepSet thread
        if(sleepset!=null) {
            sleepset.getAsyncThread().cancel(true);
        }
        if(idle==null) {
            idle = new SleepIdle();

            asynSleep = new AsyncTaskIdle();
            mContext = con;

            asynSleep.execute();


            return idle;
        }
//If there is an existing thread it will be canceled. And a new one will be created.
        asynSleep.cancel(true);
        asynSleep = new AsyncTaskIdle();

        asynSleep.execute();
        idletimer = 5;
        return idle;
    }


    static class AsyncTaskIdle extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (idletimer != 0) {

                idletimer--;
                System.out.println(idletimer);
                try {
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.getMessage();
                }
            }
            mContext.setState(new StateStandby(mContext.getTime()));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            idletimer=5;
            mContext.setState(new StateStandby(mContext.getTime()));
        }



    }

    public AsyncTaskIdle getAsyncThread(){
        return asynSleep;
    }

}
