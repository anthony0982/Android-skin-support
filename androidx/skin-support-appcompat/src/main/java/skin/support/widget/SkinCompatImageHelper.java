package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 2017/1/12.
 */
public class SkinCompatImageHelper extends SkinCompatHelper {
    private final ImageView mView;
    private int mSrcResId = INVALID_ID;
    private int mSrcCompatResId = INVALID_ID;
    private int mTintResId = INVALID_ID;

    public SkinCompatImageHelper(ImageView imageView) {
        mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatImageView, defStyleAttr, 0);
            mSrcResId = a.getResourceId(R.styleable.SkinCompatImageView_android_src, INVALID_ID);
            mTintResId = a.getResourceId(R.styleable.SkinCompatImageView_android_tint, INVALID_ID);
            mSrcCompatResId = a.getResourceId(R.styleable.SkinCompatImageView_srcCompat, INVALID_ID);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }

    public void setImageResource(int resId) {
        mSrcResId = resId;
        applySkin();
    }

    @Override
    public void applySkin() {
        mSrcCompatResId = checkResourceId(mSrcCompatResId);
        mTintResId = checkResourceId(mTintResId);
        if (mSrcCompatResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mSrcCompatResId);
            if (drawable != null) {
                mView.setImageDrawable(drawable);
            }
        } else {
            mSrcResId = checkResourceId(mSrcResId);
            if (mSrcResId == INVALID_ID) {
                return;
            }
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mSrcResId);
            if (drawable != null) {
                mView.setImageDrawable(drawable);
            }
        }
        if (mTintResId != INVALID_ID) {
            ColorStateList colorStateList = SkinCompatResources.getColorStateList(mView.getContext(), mTintResId);
            if (colorStateList != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mView.setImageTintList(colorStateList);
                }
            }
        }
    }
}
