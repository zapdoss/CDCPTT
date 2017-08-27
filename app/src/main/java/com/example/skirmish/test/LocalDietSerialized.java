package com.example.skirmish.test;

import java.io.Serializable;

/**
 * Created by skirmish on 26/8/17.
 */

public class LocalDietSerialized implements Serializable {


    private String[] Category1Names={"Rice","Wheat Chapati","Upma"};
    private String[] Category2Names={"Mosami","Grapes","Salad","Palya","Leafy Vegetables","Drumsticks","Sprouts","Hagalakayi","Kheera Kadi","Ragi Mude","Moongdal"};
    private String[] Category3Names={"Mutton Sambar","Chicken Sambar","Kebab"};
    private String[] Category4Names={"Khichdi","Chutney","Sambar","Drumstick Sambar","Rasam","Dal Sambar"};
    private String[] Category5Names={"Tea","Milk(Sugarless)","Coffee","Lemon Juice","Milk(Normal","Biscuits","Rusk"};
    private int[] Category1Im={};
    private int[] Category2Im={};
    private int[] Category3Im={};
    private int[] Category4Im={};
    private int[] Category5Im={};

    private int[] Category1;
    private int[] Category2;
    private int[] Category3;
    private int[] Category4;
    private int[] Category5;
    private int[] Category1f;
    private int[] Category2f;
    private int[] Category3f;
    private int[] Category4f;
    private int[] Category5f;
    private int[] IdealDiet;

    public String[] getCategory1Names() {
        return Category1Names;
    }

    public String[] getCategory2Names() {
        return Category2Names;
    }

    public String[] getCategory3Names() {
        return Category3Names;
    }

    public String[] getCategory4Names() {
        return Category4Names;
    }

    public String[] getCategory5Names() {
        return Category5Names;
    }

    public int[] getCategory1Im() {
        return Category1Im;
    }

    public int[] getCategory2Im() {
        return Category2Im;
    }

    public int[] getCategory3Im() {
        return Category3Im;
    }

    public int[] getCategory4Im() {
        return Category4Im;
    }

    public int[] getCategory5Im() {
        return Category5Im;
    }

    public int[] getCategory1f() {
        return Category1f;
    }

    public void setCategory1f(int[] category1f) {
        Category1f = category1f;
    }

    public int[] getCategory2f() {
        return Category2f;
    }

    public void setCategory2f(int[] category2f) {
        Category2f = category2f;
    }

    public int[] getCategory3f() {
        return Category3f;
    }

    public void setCategory3f(int[] category3f) {
        Category3f = category3f;
    }

    public int[] getCategory4f() {
        return Category4f;
    }

    public void setCategory4f(int[] category4f) {
        Category4f = category4f;
    }

    public int[] getCategory5f() {
        return Category5f;
    }

    public void setCategory5f(int[] category5f) {
        Category5f = category5f;
    }

    public void setCategoryf(int[] arr, int selected){
        if(selected==0) setCategory1f(arr);
        else if(selected==2) setCategory3f(arr);
        else if(selected==3) setCategory4f(arr);
        else if(selected==4) setCategory5f(arr);
        else if(selected==1) setCategory2f(arr);
    }



    public void setCategory(int[] arr, int selected){
        if(selected==0) setCategory1(arr);
        else if(selected==2) setCategory3(arr);
        else if(selected==3) setCategory4(arr);
        else if(selected==4) setCategory5(arr);
        else if(selected==1) setCategory2(arr);
    }

    public int[] getIdealDiet() {
        return IdealDiet;
    }

    public void setIdealDiet(int[] idealDiet) {
        IdealDiet = idealDiet;
    }


    public int[] getCategory1() {
        return Category1;
    }

    public void setCategory1(int[] category1) {
        Category1 = category1;
    }


    public int[] getCategory2() {
        return Category2;
    }

    public void setCategory2(int[] category2) {
        Category2 = category2;
    }


    public int[] getCategory3() {
        return Category3;
    }

    public void setCategory3(int[] category3) {
        Category3 = category3;
    }


    public int[] getCategory4() {
        return Category4;
    }

    public void setCategory4(int[] category4) {
        Category4 = category4;
    }


    public int[] getCategory5() {
        return Category5;
    }

    public void setCategory5(int[] category5) {
        Category5 = category5;
    }


    public float[] getPercentages(){
        int tot1=0,tot2=0,tot3=0,tot4=0,tot5=0,i;
        for(i=0;i<Category1.length;i++){
            tot1=tot1+Category1[i];
        }
        for(i=0;i<Category2.length;i++){
            tot2=tot2+Category2[i];
        }
        for(i=0;i<Category3.length;i++){
            tot3=tot3+Category3[i];
        }
        for(i=0;i<Category4.length;i++){
            tot4=tot4+Category4[i];
        }
        for(i=0;i<Category5.length;i++){
            tot5=tot5+Category5[i];
        }
        float[] fnl=new float[5];
        int tot=tot1+tot2+tot3+tot4+tot5;
        fnl[0]=(tot1/tot)*100;
        fnl[1]=(tot2/tot)*100;
        fnl[2]=(tot3/tot)*100;
        fnl[3]=(tot4/tot)*100;
        fnl[4]=(tot5/tot)*100;
        return fnl;
    }

    public int[] getPercentagesInInt(){
        int tot1=0,tot2=0,tot3=0,tot4=0,tot5=0,i;
        for(i=0;i<Category1.length;i++){
            tot1=tot1+Category1[i];
        }
        for(i=0;i<Category2.length;i++){
            tot2=tot2+Category2[i];
        }
        for(i=0;i<Category3.length;i++){
            tot3=tot3+Category3[i];
        }
        for(i=0;i<Category4.length;i++){
            tot4=tot4+Category4[i];
        }
        for(i=0;i<Category5.length;i++){
            tot5=tot5+Category5[i];
        }
        int[] fnl=new int[5];
        int tot=tot1+tot2+tot3+tot4+tot5;
        fnl[0]=(tot1*100)/tot;
        fnl[1]=(tot2*100)/tot;
        fnl[2]=(tot3*100)/tot;
        fnl[3]=(tot4*100)/tot;
        fnl[4]=(tot5*100)/tot;
        return fnl;
    }

    public int[] getTotalArray(){
        int tot1=0,tot2=0,tot3=0,tot4=0,tot5=0,i;
        for(i=0;i<Category1.length;i++){
            tot1=tot1+Category1[i];
        }
        for(i=0;i<Category2.length;i++){
            tot2=tot2+Category2[i];
        }
        for(i=0;i<Category3.length;i++){
            tot3=tot3+Category3[i];
        }
        for(i=0;i<Category4.length;i++){
            tot4=tot4+Category4[i];
        }
        for(i=0;i<Category5.length;i++){
            tot5=tot5+Category5[i];
        }
        int[] fnl=new int[5];
        //int tot=tot1+tot2+tot3+tot4+tot5;
        fnl[0]=tot1;
        fnl[1]=tot2;
        fnl[2]=tot3;
        fnl[3]=tot4;
        fnl[4]=tot5;
        return fnl;
    }


}
