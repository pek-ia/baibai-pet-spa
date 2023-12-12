import java.time.LocalDate;

public class BaiBaiPetSpa {
    public static void main(String[] args) {

        Owner paul = new Owner("Paul", "Kimball", "123 Cherry Lane", "1-800-555-1212");

        Pet baibai = new Pet("Bai-Bai", "Cat", null, paul);

        System.out.println("My pet's name is " + baibai.getName() + " " + paul.getLastName());

        baibai.setBirthday(LocalDate.now().minusYears(14));

        System.out.println("His Birthday is " + baibai.getBirthday());

    }
}
