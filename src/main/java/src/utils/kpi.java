package src.utils;

import org.joda.time.DateTime;
import org.joda.time.Months;
import java.util.LinkedList;

public class kpi {

    public static LinkedList<Double> list =  new LinkedList<Double>();
    //Se alamacena solo los dos meses anteriores
    private static DateTime lastUpDate;
    //Lleva el recuento del mes actual
    public static Integer NumeroTotal;
    public static Integer NumeroPositivos;


    public static void addOne(boolean pos){
        //Comprobamos diferencia de fechas
        DateTime now=DateTime.now();
        lastUpDate.compareTo(now);
        Months diference=Months.monthsBetween(lastUpDate,now);

        if(diference.getMonths()!=0){
        rotate();
        }
        NumeroTotal++;
        if(pos)NumeroPositivos++;
        lastUpDate= now;
    }


    public static void Initialize(){
        list.add(0.0);
        list.add(0.0);
        lastUpDate= DateTime.now();
        NumeroPositivos=0;
        NumeroTotal=0;
    }
    private static void rotate(){
    Double porcentaje=((double) NumeroPositivos)/NumeroTotal;
    Integer value = getValue(porcentaje);
    //write value on file
    list.removeLast();
    list.addFirst(porcentaje);
    NumeroTotal=0;
    NumeroPositivos=0;
    }


    public static Integer getValue(double porcentaje){
        Integer res = null;

        if(list.getLast()>porcentaje||list.getFirst()>porcentaje){
            res= -1;
        }else if(list.getLast()<porcentaje||list.getFirst()<porcentaje){
            res=1;
        }else {
            res= 0;
        }
        writeFile();
        return res;
    }
    private static void writeFile(){

    }

//========================================================================
//========================================================================
//========================================================================
//========================================================================

    public static void  setList(LinkedList<Double>newList){
        list=newList;
    }
    public static LinkedList getList(){
        return list;
    }

    public static void forceRotate(){
        rotate();
    }
    public static Integer getValue(){
        Integer res = null;
        Double porcentaje=((double) NumeroPositivos)/NumeroTotal;

        if(list.getLast()>porcentaje||list.getFirst()>porcentaje){
            res= -1;
        }else if(list.getLast()<porcentaje||list.getFirst()<porcentaje){
            res=1;
        }else {
            res= 0;
        }
        return res;
    }

}
