package ra.bussiness.config;

import java.util.Random;

public class GenerateId {
    public static String generateProductId(char ch){
        // random 1 số c 3 chư số
        Random random = new Random();
        int i =random.nextInt(1000);
        String format =  String.format("%03d",i);
        System.out.println(format);
        return ch+format;
    }

    public static void main(String[] args) {
        generateProductId('A');
    }
}
