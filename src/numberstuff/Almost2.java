package numberstuff;

import java.math.BigDecimal;

public class Almost2 {
    public static void main(String[]args){
        int n = 0;
        BigDecimal almost2 = new BigDecimal("0");
        while(true){
            almost2 = almost2.add(BigDecimal.valueOf(2).pow(n));
            n++;
            System.out.print("\33[2K\r");
            System.out.print(almost2);
        }
    }
}
