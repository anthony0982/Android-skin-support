package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.SwitchCompat;

import skin.support.appcompat.R;

public class SkinCompatSwitch extends SwitchCompat implements SkinCompatSupportable {
    private SkinCompatSwitchTintHelper mDrawableTintHelper;

    public SkinCompatSwitch(Context context) {
        this(context, null);
    }

    public SkinCompatSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.switchStyle);
    }

    public SkinCompatSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawableTintHelper = new SkinCompatSwitchTintHelper(this);
        mDrawableTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }


    @Override
    public void applySkin() {
        if (mDrawableTintHelper != null) {
            mDrawableTintHelper.applySkin();
        }
    }

}
