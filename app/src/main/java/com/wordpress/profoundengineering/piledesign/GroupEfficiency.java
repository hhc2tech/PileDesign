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
 * Created by HP on 11/6/2017.
 */

public class GroupEfficiency extends Fragment {
    private Spinner spEquation,spPileType;
    private EditText etN1,etN2,etCrossSection, etSpacing;
    private Button btnCompute;
    private TextView tvResult;
    private double n1,n2,d,spacing;
    private double p, eta ;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.group_efficiency, container, false);
        spEquation = (Spinner) myView.findViewById(R.id.spEquation);
        spPileType = (Spinner) myView.findViewById(R.id.spPileType);
        etN1 = (EditText) myView.findViewById(R.id.etN1);
        etN2 = (EditText) myView.findViewById(R.id.etN2);
        etCrossSection = (EditText) myView.findViewById(R.id.etCrossSection);
        etSpacing = (EditText) myView.findViewById(R.id.etSpacing);
        btnCompute = (Button) myView.findViewById(R.id.btnCompute);
        tvResult = (TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Group Efficiency");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    String stN1 = etN1.getText().toString();
                    String stN2 = etN2.getText().toString();
                    String stCrossSection = etCrossSection.getText().toString();
                    String stSpacing = etSpacing.getText().toString();
                    n1 = Double.parseDouble(stN1);
                    n2 = Double.parseDouble(stN2);
                    d = Double.parseDouble(stCrossSection);
                    spacing = Double.parseDouble(stSpacing);

                    //Calculation of p
                    String pileType = spPileType.getSelectedItem().toString();
                    switch (pileType) {
                        case "Circular":
                            p = Math.PI * d;
                            break;
                        case "Square":
                            p = 4 * d;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Calculation of efficiency
                    String equation = spEquation.getSelectedItem().toString();
                    switch (equation) {
                        case "Ordinary":
                            eta = (2*spacing*(n1+n2-2)+4*d)/(p*n1*n2);
                            break;
                        case "Converse–Labarre":
                            eta = 1-(((n1-1)*n2+(n2-1)*n1)/(90*n1*n2))*(180/Math.PI)*Math.atan(d/spacing);
                            break;
                        case "Los Angeles Group Action":
                            eta = 1-(d/(Math.PI*spacing*n1*n2))*(n1*(n2-1)+n2*(n1-1)+(n1-1)*(n2-1)*Math.sqrt(2));
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }
                    eta = Math.floor(eta * 10000.0) / 10000.0;

                    //Code to display the result
                    tvResult.setText("The efficiency of the pile group is = " + eta);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
