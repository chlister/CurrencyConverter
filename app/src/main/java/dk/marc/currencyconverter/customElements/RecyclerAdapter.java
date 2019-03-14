package dk.marc.currencyconverter.customElements;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import dk.marc.currencyconverter.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    // The date i want to display in my list
    private List<String> mDataSet;
    private int[] mIcons;
    private TypedArray images;

    public RecyclerAdapter(List<String> list, int[] icons) {
        mDataSet = list;
        mIcons = icons;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.currency_converted_list, viewGroup, false);
        return new MyViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textView = holder.cl.findViewById(R.id.currencyItem);
        ImageView imageView = holder.cl.findViewById(R.id.recycler_flagIcon);
        textView.setText(mDataSet.get(position));
        // When working with drawable resources we can get the position of the icons via TypedArray
        images = holder.cl.getResources().obtainTypedArray(R.array.country_icons);
        int key = images.getResourceId(position, -1);
        imageView.setImageResource(key);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
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
