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

public class NegativeSkinFriction extends Fragment {
    private Spinner spPileType,spFillType;
    private EditText etLength,etHf,etCrossSection,etGamaf,etGamac,etPhi,etDelta;
    private Button btnCompute;
    private TextView tvResult;
    private double length,hf,d,gamaf,gamac,phi,delta;
    private double p,fn,l1,a1,a2,fn1,fn2,Qn;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.negative_skin_friction,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        spFillType=(Spinner) myView.findViewById(R.id.spFillType);
        etLength=(EditText) myView.findViewById(R.id.etLength);
        etHf=(EditText) myView.findViewById(R.id.etHf);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etGamaf=(EditText) myView.findViewById(R.id.etGamaf);
        etGamac=(EditText) myView.findViewById(R.id.etGamac);
        etPhi=(EditText) myView.findViewById(R.id.etPhi);
        etDelta=(EditText) myView.findViewById(R.id.etDelta);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Negative Skin Friction");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stLength=etLength.getText().toString();
                    String stHf=etHf.getText().toString();
                    String stCrossSection=etCrossSection.getText().toString();
                    String stGamaf=etGamaf.getText().toString();
                    String stGamac=etGamac.getText().toString();
                    String stPhi=etPhi.getText().toString();
                    String stDelta=etDelta.getText().toString();
                    length=Double.parseDouble(stLength);
                    hf=Double.parseDouble(stHf);
                    d=Double.parseDouble(stCrossSection);
                    gamaf=Double.parseDouble(stGamaf);
                    gamac=Double.parseDouble(stGamac);
                    phi=Double.parseDouble(stPhi);
                    delta=Double.parseDouble(stDelta);

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

                    //Calculation of Qn
                    String fillType = spFillType.getSelectedItem().toString();
                    switch (fillType){
                        case "Clay Fill Over Granular Soil":
                            fn=(1-Math.sin(phi*Math.PI/180))*hf*gamaf*Math.tan(delta*Math.PI/180);
                            Qn=0.5*fn*hf*p;
                            break;
                        case "Granular Fill Over Clay Soil":
                            a1=2*gamaf*hf/gamac;
                            a2=(length-hf)*((length-hf)/2+gamaf*hf/gamac);
                            l1=(-1*a1+Math.sqrt(a1*a1+4*a2))/2;
                            fn1=(1-Math.sin(phi*Math.PI/180))*hf*gamaf*Math.tan(delta*Math.PI/180);
                            fn2=(1-Math.sin(phi*Math.PI/180))*(hf*gamaf+l1*gamac)*Math.tan(delta*Math.PI/180);
                            Qn=0.5*(fn1+fn2)*l1*p;
                            Qn=Math.floor(Qn*100.0)/100.0;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Code to display the result
                    tvResult.setText("The negative skin friction on the pile is = "+Qn+" kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
