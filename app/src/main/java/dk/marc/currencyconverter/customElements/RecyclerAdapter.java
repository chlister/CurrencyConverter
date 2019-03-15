package dk.marc.currencyconverter.customElements;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.currency.model.Rate;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    // The date i want to display in my list
    private List<Rate> rates;
    private int[] mIcons;
    private int size ;
    private TypedArray images;

    public RecyclerAdapter(List<Rate> rates) {
        this.rates = rates;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.currency_converted_list, viewGroup, false);
        size = viewGroup.getResources().getStringArray(R.array.country_code).length;
        return new MyViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textView_item = holder.cl.findViewById(R.id.recycler_Item);
        ImageView imageView = holder.cl.findViewById(R.id.recycler_flagIcon);
        TextView textView_val = holder.cl.findViewById(R.id.recycler_value);

        // Setting textviews
        textView_item.setText(rates.get(position).getBase());

        double value = (double) rates.get(position).getValueExchanged();
        System.out.println("Current value is " + value);
        System.out.println("Current code is " + rates.get(position).getBase());
        textView_val.setText(String.format(Locale.US, "%s", value));

        // When working with drawable resources we can get the position of the icons via TypedArray
        images = holder.cl.getResources().obtainTypedArray(R.array.country_icons);
        int key = images.getResourceId(position, -1);
        imageView.setImageResource(key);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    /**
     * Needed for the RecycleView
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cl;

        public MyViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            cl = itemView;
        }
    }
}
