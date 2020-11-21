package ejemplo_mvc_tisc.factory;

import ejemplo_mvc_tisc.models.Tisc;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nicolas.espitia
 */
public class TiscFactory {
    
    
    private TiscFactory(){}

    public static Tisc createTisc(String serial, String type, LocalDate expeditionDate, Integer balance) {
        return new Tisc(serial, type, expeditionDate, balance);
    }

    public static Tisc createRandomTisc() {
        Random random = new Random();
        Integer typeOption = random.nextInt(3);
        String type = "";
        switch (typeOption) {
            case 0:
                type = "basico";
                break;
            case 1:
                type = "personalizada";
                break;
            case 2:
                type = "tercera edad";
                break;
            case 3:
                type = "otra";
                break;
        }

        String serial = String.valueOf(random.nextInt(10000000));

        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        Integer balance = random.nextInt() * 1000;

        return new Tisc(serial, type, randomDate, balance);
    }

}
