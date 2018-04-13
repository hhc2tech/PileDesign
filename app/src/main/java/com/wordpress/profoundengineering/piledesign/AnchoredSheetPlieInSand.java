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

public class AnchoredSheetPlieInSand extends Fragment {
    private EditText etL1,etLf, etL2, etGama, etGamas, etPhi;
    private Button btnCompute;
    private TextView tvResult;
    private double l1,lf, l2, gama, gamas, phi;
    private double ka, kp, gamap, sigmap1, sigmap2, l3, p, zbar;
    private double a1, a2, a, b, c, l4old, l4new, l4, es, ea, l4counter, d, ltot, fanchor,x, mmax;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.anchoredsheetpile_in_sand,container,false);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etLf=(EditText) myView.findViewById(R.id.etLf);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etGama=(EditText) myView.findViewById(R.id.etGama);
        etGamas=(EditText) myView.findViewById(R.id.etGamas);
        etPhi=(EditText) myView.findViewById(R.id.etDelta);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Anchored Sheet Pile in Sand");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stL1=etL1.getText().toString();
                    String stLf=etLf.getText().toString();
                    String stL2=etL2.getText().toString();
                    String stGama=etGama.getText().toString();
                    String stGamas=etGamas.getText().toString();
                    String stPhi=etPhi.getText().toString();
                    l1=Double.parseDouble(stL1);
                    lf=Double.parseDouble(stLf);
                    l2=Double.parseDouble(stL2);
                    gama=Double.parseDouble(stGama);
                    gamas=Double.parseDouble(stGamas);
                    phi=Double.parseDouble(stPhi);

                    //Calculation of Ka and Kp
                    ka=(Math.tan((45-(phi/2))*Math.PI/180))*(Math.tan((45-(phi/2))*Math.PI/180));
                    kp=(Math.tan((45+(phi/2))*Math.PI/180))*(Math.tan((45+(phi/2))*Math.PI/180));

                    //Calculation of active pressures
                    sigmap1=gama*l1*ka;
                    gamap=gamas-9.81;
                    sigmap2=(gama*l1+gamap*l2)*ka;

                    //Calculation of L3
                    l3=sigmap2/(gamap*(kp-ka));

                    //Calculation of P and Z bar
                    p=0.5*sigmap1*l1+sigmap1*l2+0.5*(sigmap2-sigmap1)*l2+0.5*sigmap2*l3;
                    zbar=(0.5*sigmap1*l1*(l3+l2+l1/3)+sigmap1*l2*(l3+l2/2)+0.5*(sigmap2-sigmap1)*l2*(l3+l2/3)+0.5*sigmap2*l3*(l3*2/3))/p;

                    //Calculation of A1 and A2
                    a1=1.5*(l1-lf+l2+l3);
                    a2=(3*p*((zbar+lf)-(l1+l2+l3)))/(gamap*(kp-ka));

                    //Calculation of L4 using Newton-Raphson method
                    l4new=1;
                    es=0.01;
                    l4counter=0;
                    do {
                        l4old=l4new;
                        l4new=l4old-(l4old*l4old*l4old+a1*l4old*l4old+a2)
                                /(3*l4old*l4old+2*a1*l4old);
                        if (l4new==0){
                            l4new=0.5;
                            continue;
                        }
                        ea=Math.abs((l4new-l4old)/l4new)*100;
                        l4counter++;
                        if (l4counter>50) {
                            break;
                        }
                    }while (ea>es);
                    l4=l4new;

                    //Calculation of D and total L
                    d=l3+l4;
                    d=Math.ceil(d*100.0)/100.0;
                    ltot=l1+l2+1.3*d;
                    ltot=Math.ceil(ltot*100.0)/100.0;

                    //Calculation of F
                    fanchor=p-0.5*gamap*(kp-ka)*l4*l4;
                    fanchor=Math.ceil(fanchor*100.0)/100.0;

                    //Calculation of Mmax
                    a=0.5*ka*gamap;
                    b=sigmap1;
                    c=0.5*sigmap1*l1-fanchor;
                    x=(-1*b+Math.sqrt(b*b-4*a*c))/(2*a);
                    mmax=fanchor*(x+l1-lf)-(0.5*l1*sigmap1*(x+l1/3)+0.5*sigmap1*x*x+gamap*ka*x*x*x/6);
                    mmax=Math.ceil(mmax*100.0)/100.0;

                    //Code to display the result
                    tvResult.setText("The theoritical depth of penetration for the pile should be " + d + " m and for a 30 % increase"+
                                    " in D, the total length of the pile should be " + ltot + " m. The maximum moment encountered in the sheet"+
                                    " pile is " + mmax + " kN.m. The anchor force per unit length is " + fanchor + " kN.");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
