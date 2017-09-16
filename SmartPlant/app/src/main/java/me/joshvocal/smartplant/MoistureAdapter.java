package me.joshvocal.smartplant;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by josh on 9/14/17.
 */

public class MoistureAdapter extends ArrayAdapter<Integer> {

    public MoistureAdapter(Context context, int resource, List<Integer> moisture) {
        super(context, resource, moisture);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_device, parent, false);
        }

        TextView moistureTextView = convertView.findViewById(R.id.device_moisture);

        int moisture = getItem(position);

        moistureTextView.setText(Integer.toString(moisture));

        return convertView;
    }
}
