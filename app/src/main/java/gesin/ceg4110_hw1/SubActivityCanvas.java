package gesin.ceg4110_hw1;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;

public class SubActivityCanvas extends Fragment {

    public SubActivityCanvas() {
    }

    /**
     * Returns a new instance of this fragment.
     */
    public static SubActivityCanvas newInstance() {
        return new SubActivityCanvas();
    }
    private ColorPickerDialog colorPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_activity_canvas, container, false);
        final PaintingCanvasView canvas = rootView.findViewById(R.id.paintingCanvasView);
        colorPicker = new ColorPickerDialog();
        AppCompatImageButton colorButton = rootView.findViewById(R.id.colorPickerButton),
                clearButton = rootView.findViewById(R.id.clearButton),
                saveButton = rootView.findViewById(R.id.saveButton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker.show(getActivity().getSupportFragmentManager(), "color");
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clear_screen();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    Calendar now = Calendar.getInstance();
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Scribble-"
                            + now.get(Calendar.MONTH) + "-"
                            + now.get(Calendar.DAY_OF_MONTH) + "-"
                            + now.get(Calendar.YEAR) + "_"
                            + now.get(Calendar.HOUR_OF_DAY) + ":"
                            + now.get(Calendar.MINUTE) + ":"
                            + now.get(Calendar.SECOND) + ".png");
                    try {
                        file.createNewFile();
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            if (canvas.export(fos)) {
                                Toast.makeText(getActivity(), "Saved to " + file.getCanonicalPath(), Toast.LENGTH_SHORT).show();
                                fos.close();
                            } else {
                                Toast.makeText(getActivity(), "Could not save to file (Unidentified Reason)", Toast.LENGTH_SHORT).show();
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Could not save to file (FileNotFoundException: "+e.getMessage()+")", Toast.LENGTH_SHORT).show();
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Could not save to file (IOException: )"+e.getMessage()+")", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Could not save to file (External Storage Unavailable)", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
