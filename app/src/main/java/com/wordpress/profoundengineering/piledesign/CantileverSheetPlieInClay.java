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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP on 10/20/2017.
 */

public class CantileverSheetPlieInClay extends Fragment {
    private EditText etL1, etL2, etGama, etGamas, etPhi,etCp;
    private Button btnCompute;
    private TextView tvResult;
    private double l1, l2, gama, gamas, phi,cp;
    private double ka, gamap, sigmap1, sigmap2, p, zbar;
    private double a,b,c,d, ltot, zp, mmax;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.cantileversheetpile_in_clay,container,false);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etGama=(EditText) myView.findViewById(R.id.etGama);
        etGamas=(EditText) myView.findViewById(R.id.etGamas);
        etPhi=(EditText) myView.findViewById(R.id.etDelta);
        etCp=(EditText) myView.findViewById(R.id.etCp);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Cantilever Sheet Pile in Clay");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stL1=etL1.getText().toString();
                    String stL2=etL2.getText().toString();
                    String stGama=etGama.getText().toString();
                    String stGamas=etGamas.getText().toString();
                    String stPhi=etPhi.getText().toString();
                    String stCp=etCp.getText().toString();
                    l1=Double.parseDouble(stL1);
                    l2=Double.parseDouble(stL2);
                    gama=Double.parseDouble(stGama);
                    gamas=Double.parseDouble(stGamas);
                    phi=Double.parseDouble(stPhi);
                    cp=Double.parseDouble(stCp);

                    //Calculation of Ka and Kp
                    ka=(Math.tan((45-(phi/2))*Math.PI/180))*(Math.tan((45-(phi/2))*Math.PI/180));

                    //Calculation of active pressures
                    sigmap1=gama*l1*ka;
                    gamap=gamas-9.81;
                    sigmap2=(gama*l1+gamap*l2)*ka;

                    //Calculation of P and Z bar
                    p=0.5*sigmap1*l1+sigmap1*l2+0.5*(sigmap2-sigmap1)*l2;
                    zbar=(0.5*sigmap1*l1*(l2+l1/3)+sigmap1*l2*(l2/2)+0.5*(sigmap2-sigmap1)*l2*(l2/3))/p;

                    //Calculation of coefficients of the quadratic equation
                    a=4*cp - (gama *l1 + gamap*l2);
                    b=-2*p;
                    c=-(p*(p+12*cp*zbar))/(2*cp + (gama *l1 + gamap*l2));

                    //Calculation of D and total L
                    d=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
                    d=Math.ceil(d*100.0)/100.0;
                    ltot=l1+l2+1.5*d;
                    ltot=Math.ceil(ltot*100.0)/100.0;

                    //Calculation of Mmax
                    zp=p/a;
                    mmax=p*(zbar+zp)-a*zp*zp/2;
                    mmax=Math.ceil(mmax*100.0)/100.0;

                    //Code to display the result
                    tvResult.setText("The theoritical depth of penetration for the pile should be " + d + " m and for a 50 % increase"+
                            " in D, the total length of the pile should be " + ltot + " m. The maximum moment encountered in the sheet"+
                            " pile is " + mmax + " kN.m");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
