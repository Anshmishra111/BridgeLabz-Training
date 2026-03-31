public class VolumeOfEarth{
    public static void main(String[] args){
        double radiusKm=6378;
        double pi=3.14159;
        double VolumeKm3=(4.0/3.0)*pi*radiusKm*radiusKm*radiusKm;
        double VolumeMiles3=VolumeKm3*0.239913;
        System.out.println("The volume of earth in cubic kilometers is " + VolumeKm3 +" and cubic miles is " + VolumeMiles3);
    }
}