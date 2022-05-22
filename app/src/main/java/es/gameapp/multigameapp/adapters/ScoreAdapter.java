package es.gameapp.multigameapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.RankingActivity;
import es.gameapp.multigameapp.database.Score;

public class ScoreAdapter extends ArrayAdapter<Score> {
    private final ArrayList<Score>scoreList;
    private final Context context;

    public ScoreAdapter(Context context, int textViewResourceId, ArrayList<Score> tasks) {
        super(context, textViewResourceId, tasks);
      scoreList = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.act_list_adapter, null);

        /*LinearLayout linearLayout = view.findViewById(R.id.ListLinearLayout);
        linearLayout.setOnClickListener( view1 -> {
            Intent intent = new Intent(context, RankingActivity.class);
            intent.putExtra( "id", scoreList.get(position).getId() );
            context.startActivity(intent);
        } );*/

       /* linearLayout.setOnLongClickListener( view12 -> {
            boolean ret = true;

            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        DataManager dbManager = new DataManager(context);
                        dbManager.getWritableDatabase();
                        dbManager.deleteTaskById( scoreList.get(position).getId() );
                        dbManager.close();

                        scoreList.remove(position);
                        notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            };
*/
            /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage( context.getText( R.string.txt_are_you_sure_delete ))
                    .setPositiveButton( context.getText( R.string.txt_answer_yes ), dialogClickListener)
                    .setNegativeButton(context.getText( R.string.txt_answer_nop ), dialogClickListener)
                    .show();

            return ret;
        } );
*/
        TextView taskItemOne = view.findViewById(R.id.scoreListItemOne);
        TextView taskItemTwo = view.findViewById(R.id.scoreListItemTwo);

        taskItemOne.setText(scoreList.get(position).getName());
        taskItemTwo.setText(scoreList.get(position).getScore());

        return view;
    }
}
