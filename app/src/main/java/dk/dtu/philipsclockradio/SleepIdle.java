package dk.dtu.philipsclockradio;

import android.os.AsyncTask;
import android.util.StateSet;

public class SleepIdle {

    private static SleepIdle idle = null;
    static int idletimer = 5;
    static int sleepTimer;
    private static AsyncTaskSleep asynSleep;
    private static ContextClockradio mContext;


    public static SleepIdle getInstance(int sleeptime,ContextClockradio con ){
        System.out.println("GET INSTANCE");
        if(idle==null){
            idle = new SleepIdle();
            sleepTimer = sleeptime;
            asynSleep = new AsyncTaskSleep();
            mContext = con;

            asynSleep.execute();


            return idle;
        }
        asynSleep = new AsyncTaskSleep();
        idletimer = sleeptime;
        return idle;
    }







    static class AsyncTaskSleep extends AsyncTask<Void,Void,Void> {
        SleepSet sleepSet;
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            sleepSet = SleepSet.getInstance(sleepTimer,mContext);
        }



    }

}
