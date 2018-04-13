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

public class GroupSettlement extends Fragment {
    private Spinner spLayers;
    private EditText etQg,etLength,etPileCapDepth,etLg,etBg,etL1,etL2,etL3,etL4,etL5,etGama1,etGama2,etGama3,etGama4,etGama5;
    private EditText etEo1,etEo2,etEo3,etEo4,etEo5,etCc1,etCc2,etCc3,etCc4,etCc5;
    private Button btnCompute;
    private TextView tvResult;
    private double qg,length,pcdepth,lg,bg,l1,l2,l3,l4,l5,l1p,gama1,gama2,gama3,gama4,gama5,eo1,eo2,eo3,eo4,eo5,cc1,cc2,cc3,cc4,cc5;
    private double h1,sigma1,sigma2,sigma3,sigma4,sigma5,dsigma1,dsigma2,dsigma3,dsigma4,dsigma5,dsc1,dsc2,dsc3,dsc4,dsc5,dscg;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.group_settlement, container, false);
        spLayers = (Spinner) myView.findViewById(R.id.spNoOfSoilLayers);
        etQg = (EditText) myView.findViewById(R.id.etQg);
        etLength = (EditText) myView.findViewById(R.id.etLength);
        etPileCapDepth = (EditText) myView.findViewById(R.id.etPileCapDepth);
        etLg = (EditText) myView.findViewById(R.id.etLg);
        etBg = (EditText) myView.findViewById(R.id.etBg);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etL3=(EditText) myView.findViewById(R.id.etL3);
        etL4=(EditText) myView.findViewById(R.id.etL4);
        etL5=(EditText) myView.findViewById(R.id.etL5);
        etGama1=(EditText) myView.findViewById(R.id.etGama1);
        etGama2=(EditText) myView.findViewById(R.id.etGama2);
        etGama3=(EditText) myView.findViewById(R.id.etGama3);
        etGama4=(EditText) myView.findViewById(R.id.etGama4);
        etGama5=(EditText) myView.findViewById(R.id.etGama5);
        etEo1=(EditText) myView.findViewById(R.id.etEo1);
        etEo2=(EditText) myView.findViewById(R.id.etEo2);
        etEo3=(EditText) myView.findViewById(R.id.etEo3);
        etEo4=(EditText) myView.findViewById(R.id.etEo4);
        etEo5=(EditText) myView.findViewById(R.id.etEo5);
        etCc1=(EditText) myView.findViewById(R.id.etCc1);
        etCc2=(EditText) myView.findViewById(R.id.etCc2);
        etCc3=(EditText) myView.findViewById(R.id.etCc3);
        etCc4=(EditText) myView.findViewById(R.id.etCc4);
        etCc5=(EditText) myView.findViewById(R.id.etCc5);
        btnCompute = (Button) myView.findViewById(R.id.btnCompute);
        tvResult = (TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Group Settlement");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    String stQg = etQg.getText().toString();
                    String stLength = etLength.getText().toString();
                    String stPileCapDepth = etPileCapDepth.getText().toString();
                    String stLg = etLg.getText().toString();
                    String stBg = etBg.getText().toString();
                    String stL1 = etL1.getText().toString();
                    String stL2 = etL2.getText().toString();
                    String stL3 = etL3.getText().toString();
                    String stL4 = etL4.getText().toString();
                    String stL5 = etL5.getText().toString();
                    String stGama1 = etGama1.getText().toString();
                    String stGama2 = etGama2.getText().toString();
                    String stGama3 = etGama3.getText().toString();
                    String stGama4 = etGama4.getText().toString();
                    String stGama5 = etGama5.getText().toString();
                    String stEo1 = etEo1.getText().toString();
                    String stEo2 = etEo2.getText().toString();
                    String stEo3 = etEo3.getText().toString();
                    String stEo4 = etEo4.getText().toString();
                    String stEo5 = etEo5.getText().toString();
                    String stCc1 = etCc1.getText().toString();
                    String stCc2 = etCc2.getText().toString();
                    String stCc3 = etCc3.getText().toString();
                    String stCc4 = etCc4.getText().toString();
                    String stCc5 = etCc5.getText().toString();
                    qg = Double.parseDouble(stQg);
                    length = Double.parseDouble(stLength);
                    pcdepth = Double.parseDouble(stPileCapDepth);
                    lg = Double.parseDouble(stLg);
                    bg = Double.parseDouble(stBg);

                    //Calculation of settlement
                    String layers = spLayers.getSelectedItem().toString();
                    switch (layers) {
                        case "One":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            gama1=Double.parseDouble(stGama1);
                            eo1=Double.parseDouble(stEo1);
                            cc1=Double.parseDouble(stCc1);

                            //Calculation of σo’ and Δσ’
                            h1=l1-pcdepth-2*length/3;
                            sigma1=(pcdepth+2*length/3+h1*0.5)*gama1;
                            dsigma1=qg/((bg+h1*0.5)*(lg+h1*0.5));

                            //Calculation of ΔSc
                            dsc1=(cc1*h1)*(Math.log10((sigma1+dsigma1)/sigma1))/(1+eo1);
                            dscg=dsc1*1000;
                            dscg=Math.floor(dscg*100.0)/100.0;
                            break;
                        case "Two":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            eo1=Double.parseDouble(stEo1);
                            eo2=Double.parseDouble(stEo2);
                            cc1=Double.parseDouble(stCc1);
                            cc2=Double.parseDouble(stCc2);

                            //Calculation of σo’ and Δσ’
                            if (l1<=(pcdepth+2*length/3)){
                                h1=l1+l2-pcdepth-2*length/3;
                                sigma2=l1*gama1+(l2-h1+h1/2)*gama2;
                                dsigma2=qg/((bg+h1*0.5)*(lg+h1*0.5));

                                //Calculation of ΔSc
                                dsc2=(cc2*h1)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dscg=dsc2*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if (l1>(pcdepth+2*length/3)){
                                h1=l1-pcdepth-2*length/3;
                                sigma1=(pcdepth+2*length/3+h1*0.5)*gama1;
                                dsigma1=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma2=sigma1+h1*0.5*gama1+l2*0.5*gama2;
                                dsigma2=qg/((bg+h1+0.5*l2)*(lg+h1+0.5*l2));

                                //Calculation of ΔSc
                                dsc1=(cc1*h1)*(Math.log10((sigma1+dsigma1)/sigma1))/(1+eo1);
                                dsc2=(cc2*l2)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dscg=(dsc1+dsc2)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else{
                                Toast.makeText(getActivity(), "Strange Case Encountered!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Three":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            l3=Double.parseDouble(stL3);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            gama3=Double.parseDouble(stGama3);
                            eo1=Double.parseDouble(stEo1);
                            eo2=Double.parseDouble(stEo2);
                            eo3=Double.parseDouble(stEo3);
                            cc1=Double.parseDouble(stCc1);
                            cc2=Double.parseDouble(stCc2);
                            cc3=Double.parseDouble(stCc3);

                            //Calculation of σo’ and Δσ’
                            if ((l1+l2)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3-pcdepth-2*length/3;
                                sigma3=l1*gama1+l2*gama2+(l3-h1+h1/2)*gama3;
                                dsigma3=qg/((bg+h1*0.5)*(lg+h1*0.5));

                                //Calculation of ΔSc
                                dsc3=(cc3*h1)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dscg=dsc3*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2)>(pcdepth+2*length/3)&l1<=(pcdepth+2*length/3)){
                                h1=l1+l2-pcdepth-2*length/3;
                                sigma2=l1*gama1+(l2-h1+h1/2)*gama2;
                                dsigma2=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma3=sigma2+h1*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+0.5*l3)*(lg+h1+0.5*l3));

                                //Calculation of ΔSc
                                dsc2=(cc2*h1)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dscg=(dsc2+dsc3)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if (l1>(pcdepth+2*length/3)){
                                h1=l1-pcdepth-2*length/3;
                                sigma1=(l1-h1+h1/2)*gama1;
                                dsigma1=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma2=sigma1+h1*0.5*gama1+l2*0.5*gama2;
                                dsigma2=qg/((bg+h1+0.5*l2)*(lg+h1+0.5*l2));
                                sigma3=sigma2+l2*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+l2+0.5*l3)*(lg+h1+l2+0.5*l3));

                                //Calculation of ΔSc
                                dsc1=(cc1*h1)*(Math.log10((sigma1+dsigma1)/sigma1))/(1+eo1);
                                dsc2=(cc2*l2)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dscg=(dsc1+dsc2+dsc3)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else{
                                Toast.makeText(getActivity(), "Strange Case Encountered!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Four":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            l3=Double.parseDouble(stL3);
                            l4=Double.parseDouble(stL4);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            gama3=Double.parseDouble(stGama3);
                            gama4=Double.parseDouble(stGama4);
                            eo1=Double.parseDouble(stEo1);
                            eo2=Double.parseDouble(stEo2);
                            eo3=Double.parseDouble(stEo3);
                            eo4=Double.parseDouble(stEo4);
                            cc1=Double.parseDouble(stCc1);
                            cc2=Double.parseDouble(stCc2);
                            cc3=Double.parseDouble(stCc3);
                            cc4=Double.parseDouble(stCc4);

                            //Calculation of σo’ and Δσ’
                            if ((l1+l2+l3)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3+l4-pcdepth-2*length/3;
                                sigma4=l1*gama1+l2*gama2+l3*gama3+(l4-h1+h1/2)*gama4;
                                dsigma4=qg/((bg+h1*0.5)*(lg+h1*0.5));

                                //Calculation of ΔSc
                                dsc4=(cc4*h1)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dscg=dsc4*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2+l3)>(pcdepth+2*length/3)&(l1+l2)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3-pcdepth-2*length/3;
                                sigma3=l1*gama1+l2*gama2+(l3-h1+h1/2)*gama3;
                                dsigma3=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma4=sigma3+h1*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+0.5*l4)*(lg+h1+0.5*l4));

                                //Calculation of ΔSc
                                dsc3=(cc3*h1)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dscg=(dsc3+dsc4)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2)>(pcdepth+2*length/3)&l1<=(pcdepth+2*length/3)){
                                h1=l1+l2-pcdepth-2*length/3;
                                sigma2=l1*gama1+(l2-h1+h1/2)*gama2;
                                dsigma2=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma3=sigma2+h1*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+0.5*l3)*(lg+h1+0.5*l3));
                                sigma4=sigma3+l3*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+l3+0.5*l4)*(lg+h1+l3+0.5*l4));

                                //Calculation of ΔSc
                                dsc2=(cc2*h1)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dscg=(dsc2+dsc3+dsc4)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if (l1>(pcdepth+2*length/3)){
                                h1=l1-pcdepth-2*length/3;
                                sigma1=(l1-h1+h1/2)*gama1;
                                dsigma1=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma2=sigma1+h1*0.5*gama1+l2*0.5*gama2;
                                dsigma2=qg/((bg+h1+0.5*l2)*(lg+h1+0.5*l2));
                                sigma3=sigma2+l2*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+l2+0.5*l3)*(lg+h1+l2+0.5*l3));
                                sigma4=sigma3+l3*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+l2+l3+0.5*l4)*(lg+h1+l2+l3+0.5*l4));

                                //Calculation of ΔSc
                                dsc1=(cc1*h1)*(Math.log10((sigma1+dsigma1)/sigma1))/(1+eo1);
                                dsc2=(cc2*l2)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dscg=(dsc1+dsc2+dsc3+dsc4)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else{
                                Toast.makeText(getActivity(), "Strange Case Encountered!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Five":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            l3=Double.parseDouble(stL3);
                            l4=Double.parseDouble(stL4);
                            l5=Double.parseDouble(stL5);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            gama3=Double.parseDouble(stGama3);
                            gama4=Double.parseDouble(stGama4);
                            gama5=Double.parseDouble(stGama5);
                            eo1=Double.parseDouble(stEo1);
                            eo2=Double.parseDouble(stEo2);
                            eo3=Double.parseDouble(stEo3);
                            eo4=Double.parseDouble(stEo4);
                            eo5=Double.parseDouble(stEo5);
                            cc1=Double.parseDouble(stCc1);
                            cc2=Double.parseDouble(stCc2);
                            cc3=Double.parseDouble(stCc3);
                            cc4=Double.parseDouble(stCc4);
                            cc5=Double.parseDouble(stCc5);

                            //Calculation of σo’ and Δσ’
                            if ((l1+l2+l3+l4)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3+l4+l5-pcdepth-2*length/3;
                                sigma5=l1*gama1+l2*gama2+l3*gama3+l4*gama4+(l5-h1+h1/2)*gama5;
                                dsigma5=qg/((bg+h1*0.5)*(lg+h1*0.5));

                                //Calculation of ΔSc
                                dsc5=(cc5*h1)*(Math.log10((sigma5+dsigma5)/sigma5))/(1+eo5);
                                dscg=dsc5*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2+l3+l4)>(pcdepth+2*length/3)&(l1+l2+l3)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3+l4-pcdepth-2*length/3;
                                sigma4=l1*gama1+l2*gama2+l3*gama3+(l4-h1+h1/2)*gama4;
                                dsigma4=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma5=sigma4+h1*0.5*gama4+l5*0.5*gama5;
                                dsigma5=qg/((bg+h1+0.5*l5)*(lg+h1+0.5*l5));

                                //Calculation of ΔSc
                                dsc4=(cc4*h1)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dsc5=(cc5*l5)*(Math.log10((sigma5+dsigma5)/sigma5))/(1+eo5);
                                dscg=(dsc4+dsc5)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2+l3)>(pcdepth+2*length/3)&(l1+l2)<=(pcdepth+2*length/3)){
                                h1=l1+l2+l3-pcdepth-2*length/3;
                                sigma3=l1*gama1+l2*gama2+(l3-h1+h1/2)*gama3;
                                dsigma3=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma4=sigma3+h1*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+0.5*l4)*(lg+h1+0.5*l4));
                                sigma5=sigma4+l4*0.5*gama4+l5*0.5*gama5;
                                dsigma5=qg/((bg+h1+l4+0.5*l5)*(lg+h1+l4+0.5*l5));

                                //Calculation of ΔSc
                                dsc3=(cc3*h1)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dsc5=(cc5*l5)*(Math.log10((sigma5+dsigma5)/sigma5))/(1+eo5);
                                dscg=(dsc3+dsc4+dsc5)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if ((l1+l2)>(pcdepth+2*length/3)&l1<=(pcdepth+2*length/3)){
                                h1=l1+l2-pcdepth-2*length/3;
                                sigma2=l1*gama1+(l2-h1+h1/2)*gama2;
                                dsigma2=qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma3=sigma2+h1*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+0.5*l3)*(lg+h1+0.5*l3));
                                sigma4=sigma3+l3*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+l3+0.5*l4)*(lg+h1+l3+0.5*l4));
                                sigma5=sigma4+l4*0.5*gama4+l5*0.5*gama5;
                                dsigma5=qg/((bg+h1+l3+l4+0.5*l5)*(lg+h1+l3+l4+0.5*l5));

                                //Calculation of ΔSc
                                dsc2=(cc2*h1)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dsc5=(cc5*l5)*(Math.log10((sigma5+dsigma5)/sigma5))/(1+eo5);
                                dscg=(dsc2+dsc3+dsc4+dsc5)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else if (l1>(pcdepth+2*length/3)){
                                h1=l1-pcdepth-2*length/3;
                                sigma1=(l1-h1+h1/2)*gama1;
                                dsigma1 =qg/((bg+h1*0.5)*(lg+h1*0.5));
                                sigma2=sigma1+h1*0.5*gama1+l2*0.5*gama2;
                                dsigma2=qg/((bg+h1+0.5*l2)*(lg+h1+0.5*l2));
                                sigma3=sigma2+l2*0.5*gama2+l3*0.5*gama3;
                                dsigma3=qg/((bg+h1+l2+0.5*l3)*(lg+h1+l2+0.5*l3));
                                sigma4=sigma3+l3*0.5*gama3+l4*0.5*gama4;
                                dsigma4=qg/((bg+h1+l2+l3+0.5*l4)*(lg+h1+l2+l3+0.5*l4));
                                sigma5=sigma4+l4*0.5*gama4+l5*0.5*gama5;
                                dsigma5=qg/((bg+h1+l2+l3+l4+0.5*l5)*(lg+h1+l2+l3+l4+0.5*l5));

                                //Calculation of ΔSc
                                dsc1=(cc1*h1)*(Math.log10((sigma1+dsigma1)/sigma1))/(1+eo1);
                                dsc2=(cc2*l2)*(Math.log10((sigma2+dsigma2)/sigma2))/(1+eo2);
                                dsc3=(cc3*l3)*(Math.log10((sigma3+dsigma3)/sigma3))/(1+eo3);
                                dsc4=(cc4*l4)*(Math.log10((sigma4+dsigma4)/sigma4))/(1+eo4);
                                dsc5=(cc5*l5)*(Math.log10((sigma5+dsigma5)/sigma5))/(1+eo5);
                                dscg=(dsc1+dsc2+dsc3+dsc4+dsc5)*1000;
                                dscg=Math.floor(dscg*100.0)/100.0;
                            }
                            else{
                                Toast.makeText(getActivity(), "Strange Case Encountered!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            Toast.makeText(getActivity(), "Number of soil layers not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Code to display the result
                    tvResult.setText("The consolidation settlement of the pile group is = "+dscg+" mm.");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
