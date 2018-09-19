package gesin.ceg4110_hw1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SubActivityColorRandomizer extends Fragment {

    public SubActivityColorRandomizer() {
    }

    /**
     * Returns a new instance of this fragment.
     */
    public static SubActivityColorRandomizer newInstance() {
        return new SubActivityColorRandomizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_activity_colored_text, container, false);
        Button button = rootView.findViewById(R.id.button);
        final TextView text = rootView.findViewById(R.id.editText),
                indicator = rootView.findViewById(R.id.colorSignifier);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Math.round(0x00ffffff*(float)Math.random());
                text.setTextColor(0xff000000+color);
                indicator.setText(String.format("COLOR: %dr, %dg, %db, #%x", (color&0x00ff0000)/0x10000, (color&0x0000ff00)/0x100, color%0x100, color));
            }
        });
        return rootView;
    }
}

