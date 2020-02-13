package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.SwitchCompat;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatSwitchTintHelper extends SkinCompatHelper {
    private final SwitchCompat switchCompat;
    private int drawableTintResId = INVALID_ID;

    public SkinCompatSwitchTintHelper(SwitchCompat view) {
        switchCompat = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = switchCompat.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatTint, defStyleAttr, 0);
            drawableTintResId = a.getResourceId(R.styleable.SkinCompatTint_trackTint, INVALID_ID);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }

    @Override
    public void applySkin() {
        drawableTintResId = checkResourceId(drawableTintResId);
        if (drawableTintResId != INVALID_ID) {
            ColorStateList tintList = SkinCompatResources.getColorStateList(switchCompat.getContext(), drawableTintResId);
            switchCompat.setTrackTintList(tintList);
        }
    }
}
