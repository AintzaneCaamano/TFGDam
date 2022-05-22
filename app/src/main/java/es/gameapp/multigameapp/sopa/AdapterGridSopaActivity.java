package es.gameapp.multigameapp.sopa;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterGridSopaActivity extends BaseAdapter {
    private Context context;
    public AdapterGridSopaActivity(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*/ Get the data item for this position
        Tarea tarea = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }
        TextView txtNombreTarea = (TextView) convertView.findViewById(R.id.txt1);
        txtNombreTarea.setText(tarea.getNombreTarea());
        // Return the completed view to render on screen*/
        return convertView;
    }

}
