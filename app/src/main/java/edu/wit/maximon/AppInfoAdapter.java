package edu.wit.maximon;

import android.content.Context;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class AppInfoAdapter extends ArrayAdapter<AppInfoItem> {
    private LayoutInflater layoutInflater;



    public AppInfoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public AppInfoAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public AppInfoAdapter(@NonNull Context context, int resource, @NonNull AppInfoItem[] objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public AppInfoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull AppInfoItem[] objects) {
        super(context, resource, textViewResourceId, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public AppInfoAdapter(@NonNull Context context, int resource, @NonNull List<AppInfoItem> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public AppInfoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<AppInfoItem> objects) {
        super(context, resource, textViewResourceId, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final AppInfoItem item = (AppInfoItem) getItem(position);

        final View view = layoutInflater.inflate(R.layout.app_info_item, null);
        final ImageView appIcon = view.findViewById(R.id.appIcon);
        final TextView appNameTextField = view.findViewById(R.id.appNameTextField);
        final TextView usageTextView = view.findViewById(R.id.usageTextField);
        appNameTextField.setText(item.getName());
        usageTextView.setText(String.format("%.2f hours", item.getUsage()));
        appIcon.setImageDrawable(item.getIcon());
        return view;
    }


}
