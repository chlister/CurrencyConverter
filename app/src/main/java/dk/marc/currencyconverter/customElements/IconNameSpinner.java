package dk.marc.currencyconverter.customElements;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dk.marc.currencyconverter.R;

public class IconNameSpinner extends ArrayAdapter {

    private String[] spinnerTitles;
    private int[] spinnerImages;
    Context context;
    TypedArray images;

    //region Getters&Setters
    public String[] getSpinnerTitles() {
        return spinnerTitles;
    }

    public int[] getSpinnerImages() {
        return spinnerImages;
    }
    //endregion

    public IconNameSpinner(@NonNull Context context, String[] titles, int[] spinnerImages) {
        super(context, R.layout.icon_spinner);
        spinnerTitles = titles;
        this.spinnerImages = spinnerImages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spinnerTitles.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.icon_spinner, parent, false);
            viewHolder.mFlag = convertView.findViewById(R.id.flagIcon);
            viewHolder.mName = convertView.findViewById(R.id.flagName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        images = parent.getResources().obtainTypedArray(R.array.country_icons);
        int key = images.getResourceId(position, -1);
        viewHolder.mFlag.setImageResource(key);
        viewHolder.mName.setText(spinnerTitles[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }
}
