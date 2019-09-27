package lijewski.songapp.databinding;

import android.view.View;
import androidx.databinding.BindingAdapter;

@SuppressWarnings("WeakerAccess")
public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void visibleGone(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
