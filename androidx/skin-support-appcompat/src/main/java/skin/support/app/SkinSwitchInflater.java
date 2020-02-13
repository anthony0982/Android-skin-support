package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import skin.support.widget.SkinCompatSwitch;

public class SkinSwitchInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.appcompat.widget.SwitchCompat":
                view = new SkinCompatSwitch(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
