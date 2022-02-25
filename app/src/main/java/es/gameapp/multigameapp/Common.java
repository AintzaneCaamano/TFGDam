package es.gameapp.multigameapp;

import android.content.Context;
import android.widget.Toast;

public class Common {

    static void showToast(Context context, String text){
        //It has package visibility, DON´T WRITE PRIVATE
        //text is the literal shown in the toast, context is the activity where it appears
        Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }
}
