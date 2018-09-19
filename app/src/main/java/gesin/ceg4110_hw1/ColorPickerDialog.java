package gesin.ceg4110_hw1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPickerDialog extends DialogFragment {
    private int r, g, b;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.color_picker_dialog, null);
        builder.setView(rootView);
        final PaintingCanvasView canvas = (PaintingCanvasView) getActivity().findViewById(R.id.paintingCanvasView);
        SeekBar rBar = (SeekBar) rootView.findViewById(R.id.redBar),
                gBar = (SeekBar) rootView.findViewById(R.id.greenBar),
                bBar = (SeekBar) rootView.findViewById(R.id.blueBar);
        final TextView rText = (TextView) rootView.findViewById(R.id.redValue),
                gText = (TextView) rootView.findViewById(R.id.greenValue),
                bText = (TextView) rootView.findViewById(R.id.blueValue);
        int color = canvas.getmPaint().getColor();
        r = (color&0x00ff0000)/0x10000;
        g = (color&0x0000ff00)/0x100;
        b = color%0x100;
        class colorSliderListener implements SeekBar.OnSeekBarChangeListener
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(seekBar.getId()) {
                    case R.id.redBar: {
                        r = progress;
                        rText.setText(Integer.toString(r));
                        break;
                    }
                    case R.id.greenBar: {
                        g = progress;
                        gText.setText(Integer.toString(g));
                        break;
                    }
                    case R.id.blueBar: {
                        b = progress;
                        bText.setText(Integer.toString(b));
                        break;
                    }
                    default: {
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }
        rBar.setOnSeekBarChangeListener(new colorSliderListener());
        gBar.setOnSeekBarChangeListener(new colorSliderListener());
        bBar.setOnSeekBarChangeListener(new colorSliderListener());
        rBar.setProgress(r);
        gBar.setProgress(g);
        bBar.setProgress(b);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Paint paint = canvas.getmPaint();
                paint.setColor(0xff000000 + (0x10000*r) + (0x100*g) + b);
                canvas.setmPaint(paint);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
