package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatTextViewDrawableTintHelper extends SkinCompatHelper {
    private final TextView mTextView;
    private int drawableTintResId = INVALID_ID;
    private ColorStateList tintColorStateList;

    public SkinCompatTextViewDrawableTintHelper(TextView view) {
        mTextView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = mTextView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatTint, defStyleAttr, 0);
            drawableTintResId = a.getResourceId(R.styleable.SkinCompatTint_drawableTint, INVALID_ID);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }

    public void setDrawableTintColorStateList(ColorStateList colorStateList) {
        this.tintColorStateList = colorStateList;
        applySkin();
    }

    @Override
    public void applySkin() {
        drawableTintResId = checkResourceId(drawableTintResId);
        if (drawableTintResId != INVALID_ID) {
            ColorStateList tintList = SkinCompatResources.getColorStateList(mTextView.getContext(), drawableTintResId);
            for (Drawable drawable : mTextView.getCompoundDrawables()) {
                if (drawable != null)
                    drawable.setTintList(tintList);
            }
        } else if (this.tintColorStateList != null) {
            for (Drawable drawable : mTextView.getCompoundDrawables()) {
                if (drawable != null)
                    drawable.setTintList(tintColorStateList);
            }
        }
    }
}
