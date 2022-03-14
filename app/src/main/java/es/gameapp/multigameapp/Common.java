package es.gameapp.multigameapp;

import android.content.Context;
import android.widget.Toast;

public class Common {

    public static void showToast(Context context, String text){
        //It has package visibility, DON´T WRITE PRIVATE
        //text is the literal shown in the toast, context is the activity where it appears
        Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }
    public static void waitTime(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
