public class DividePens{
    public static void maiN(String[] args){
        int TotalPens=14;
        int Students=3;
        int pensPerStudent=TotalPens/Students;
        int remainingPens=TotalPens%Students;
        System.out.println("The pen per Student is " + pensPerStudent +" and the remaining  pen not distributed is" +remainingPens);
    }
}