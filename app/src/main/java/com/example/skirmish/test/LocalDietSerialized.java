package com.example.skirmish.test;

import java.io.Serializable;

/**
 * Created by skirmish on 26/8/17.
 */

public class LocalDietSerialized implements Serializable {


    private static String[] Category1Names={"Rice","Wheat Chapati","Upma"};
    private static String[] Category2Names={"Mosami","Grapes","Salad","Palya","Leafy Vegetables","Drumsticks","Sprouts","Hagalakayi","Kheera Kadi","Ragi Mude","Moongdal"};
    private static String[] Category3Names={"Mutton Sambar","Chicken Sambar","Kebab"};
    private static String[] Category4Names={"Khichdi","Chutney","Sambar","Drumstick Sambar","Rasam","Dal Sambar"};
    private static String[] Category5Names={"Tea","Milk(Sugarless)","Coffee","Lemon Juice","Milk(Normal","Biscuits","Rusk"};

    public static String[] getCategory1NamesKannada() {
        return Category1NamesKannada;
    }

    public static String[] getCategory2NamesKannada() {
        return Category2NamesKannada;
    }

    private static String[] Category1NamesKannada={ "ಅಕ್ಕಿ ","ಚಪತಿ " ," ಉಪ್ಮಾ" };
    private static String[] Category2NamesKannada={ "ಮೊಸಂಬಿ ", "-ದ್ರಾಕ್ಷಿಗಳು ", " ಕಚ್ಚಾ ತರಕಾರಿ ಸಲಾಡ್ ","ತರಕಾರಿ ಪಲ್ಯ ","ಎಲೆಗಳ ತರಕಾರಿಗಳು ","ಡ್ರಮ್ ಸ್ಟಿಕ್ಗಳು ","ಮೊಗ್ಗುಗಳು ","ಹಾಗಲಕಾಯಿ ", "ಖೀರಾ ಕಾಕ್ಡಿ ", "ರಾಗಿ ಮಡ್ಡೆ ","ಮೊಂಗ್ ಡಾಲ್ " };

    public static String[] getCategory3NamesKannada() {
        return Category3NamesKannada;
    }

    public static String[] getCategory4NamesKannada() {
        return Category4NamesKannada;
    }

    public static String[] getCategory5NamesKannada() {
        return Category5NamesKannada;
    }

    private static String[] Category3NamesKannada={"ಮಟನ್ ಸಾಂಬರ್ ","ಕೋಳಿ ಸಾಂಬರ್ ","ಕಬಾಬ್ "};
    private static String[] Category4NamesKannada={"ಖಿಚಿ ","ಚಟ್ನಿ ","ಸಂಬಾರ್ ","ಡ್ರಮ್ಸ್ಟಿಕ್ ಸಾಂಬರ್ ","ರಾಸಮ್ ","ದಲ್ ಸಾಂಬರ್ "};
    private static String[] Category5NamesKannada={"ಚಹಾ ","ಹಾಲು ","ಕಾಫಿ ","ನಿಂಬೆ ರಸ ","ಹಾಲು ","ಬಿಸ್ಕತ್ತು ","ರಸ್ಕ್ "};
    private static int[] Category1Im={R.drawable.rice,R.drawable.wheatchapati,R.drawable.upma};
    private static int[] Category2Im={R.drawable.mosambi,R.drawable.grapes,R.drawable.rawvegetablesalad,R.drawable.pegetableplaya,R.drawable.leafyvegetables,R.drawable.drumsticks,R.drawable.sprouts,R.drawable.hagalakayi,R.drawable.kheerakadi,R.drawable.raggimudde,R.drawable.moongdal};
    private static int[] Category3Im={R.drawable.muttonsambar,R.drawable.chickensambar,R.drawable.kebab};
    private static int[] Category4Im={R.drawable.khichdi,R.drawable.greenchutney,R.drawable.sambar,R.drawable.drumsticksambar,R.drawable.rasam,R.drawable.dalsambar};
    private static int[] Category5Im={R.drawable.teasugarless,R.drawable.milksugarless,R.drawable.coffeesugarless,R.drawable.lemonjuice,R.drawable.milknormal,R.drawable.biscuit,R.drawable.rusk};

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

    public static String[] getCategory1Names() {
        return Category1Names;
    }

    public static String[] getCategory2Names() {
        return Category2Names;
    }

    public static String[] getCategory3Names() {
        return Category3Names;
    }

    public static String[] getCategory4Names() {
        return Category4Names;
    }

    public static String[] getCategory5Names() {
        return Category5Names;
    }

    public static int[] getCategory1Im() {
        return Category1Im;
    }

    public static int[] getCategory2Im() {
        return Category2Im;
    }

    public static int[] getCategory3Im() {
        return Category3Im;
    }

    public static int[] getCategory4Im() {
        return Category4Im;
    }

    public static int[] getCategory5Im() {
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

    public String[] getCategoryNames(int selected){
        if(selected==0) return getCategory1Names();
        else if(selected==1) return getCategory2Names();
        else if(selected==2) return getCategory3Names();
        else if(selected==3) return getCategory4Names();
        else return getCategory5Names();
    }

    public String[] getCategoryNamesKannada(int selected){
        if(selected==0) return getCategory1NamesKannada();
        else if(selected==1) return getCategory2NamesKannada();
        else if(selected==2) return getCategory3NamesKannada();
        else if(selected==3) return getCategory4NamesKannada();
        else return getCategory5NamesKannada();
    }

    public int[] getCategoryIm(int selected){
        if(selected==0) return getCategory1Im();
        else if(selected==1) return getCategory2Im();
        else if(selected==2) return getCategory3Im();
        else if(selected==3) return getCategory4Im();
        else return getCategory5Im();
    }

    public int[] getCategory(int selected){
        if(selected==0) return getCategory1();
        else if(selected==1) return getCategory2();
        else if(selected==2) return getCategory3();
        else if(selected==3) return getCategory4();
        else return getCategory5();
    }

    public int[] getCategoryf(int selected){
        if(selected==0) return getCategory1f();
        else if(selected==1) return getCategory2f();
        else if(selected==2) return getCategory3f();
        else if(selected==3) return getCategory4f();
        else return getCategory5f();
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
