package dk.marc.currencyconverter.utility;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class DrawableManager {

    /**
     * Tries to find a drawable resource with the given name - else returns null
     *
     * @param name    Name of the drawable resource
     * @param context
     * @return Drawable
     */
    public static Drawable getDrawable(String name, Context context) {
        Resources resources = context.getResources();
        final int resourceId;
        try {
            // Try to find a drawable that matches name - else return null
            resourceId = resources.getIdentifier(name, "drawable",
                    context.getPackageCodePath());
            return resources.getDrawable(resourceId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
