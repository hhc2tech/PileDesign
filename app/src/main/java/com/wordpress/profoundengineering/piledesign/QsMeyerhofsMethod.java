package com.wordpress.profoundengineering.piledesign;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP on 10/20/2017.
 */

public class QsMeyerhofsMethod extends Fragment {
    private Spinner spPileType;
    private EditText etLength, etCrossSection, etGama, etDelta, etK;
    private Button btnCompute;
    private TextView tvResult;
    private double length, d, gama, delta, k;
    private double p, lp, f, Qs;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.qs_meyerhofs_method,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        etLength=(EditText) myView.findViewById(R.id.etLength);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etGama=(EditText) myView.findViewById(R.id.etGama);
        etDelta=(EditText) myView.findViewById(R.id.etDelta);
        etK=(EditText) myView.findViewById(R.id.etK);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Meyerhof's Method for Qs");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stLength=etLength.getText().toString();
                    String stCrossSection=etCrossSection.getText().toString();
                    String stGama=etGama.getText().toString();
                    String stDelta=etDelta.getText().toString();
                    String stK=etK.getText().toString();
                    length=Double.parseDouble(stLength);
                    d=Double.parseDouble(stCrossSection);
                    gama=Double.parseDouble(stGama);
                    delta=Double.parseDouble(stDelta);
                    k=Double.parseDouble(stK);

                    //Calculation of As
                    String pileType = spPileType.getSelectedItem().toString();
                    switch (pileType){
                        case "Circular":
                            p=(Math.PI)*d;
                            break;
                        case "Square":
                            p=4*d;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Calculation of f
                    lp=15*d;
                    f=k*lp*gama*Math.tan(delta*Math.PI/180);

                    //Calculation of Qs
                    Qs=p*(0.5*f*lp+f*(length-lp));
                    Qs=Math.floor(Qs*100.0)/100.0;

                    //Code to display the result
                    tvResult.setText("The ultimate friction resistance of the pile is = "+Qs+" kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
