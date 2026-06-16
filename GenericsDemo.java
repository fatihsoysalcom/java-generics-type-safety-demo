import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

    // 1. Eski Yöntem: Tip güvenliği olmayan sınıf (Object kullanır)
    static class OldBox {
        private Object item;
        public void set(Object item) { this.item = item; }
        public Object get() { return item; }
    }

    // 2. Yeni Yöntem: Generics ile tip güvenliği sağlayan sınıf
    static class GenericBox<T> {
        private T item;
        public void set(T item) { this.item = item; }
        public T get() { return item; }
    }

    // 3. Sınırlandırılmış Tip Parametresi (Bounded Type Parameters)
    // Sadece Number ve alt sınıflarını kabul eder
    static class NumberBox<T extends Number> {
        private T number;
        public NumberBox(T number) { this.number = number; }
        public double doubleValue() {
            return number.doubleValue();
        }
    }

    // 4. Generic Metot Örneği
    public static <E> void printArray(E[] elements) {
        for (E element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Generics Olmadan (Eski Yöntem) ===");
        OldBox oldBox = new OldBox();
        oldBox.set("Merhaba Dünya"); // String ekliyoruz
        
        // Çalışma zamanında hata riski (ClassCastException)
        // Geliştirici yanlışlıkla Integer'a cast etmeye çalışabilir:
        try {
            Integer number = (Integer) oldBox.get(); // Derleme hatası vermez, ama çalışma zamanında çöker!
        } catch (ClassCastException e) {
            System.out.println("Hata yakalandı (Beklenen Davranış): " + e.getMessage());
        }

        System.out.println("\n=== 2. Generics ile Tip Güvenliği ===");
        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("Java Generics");
        
        // Cast işlemine gerek kalmaz, doğrudan String döner ve güvenlidir
        String str = stringBox.get(); 
        System.out.println("Kutudaki değer: " + str);

        // NOT: Aşağıdaki satır derleme hatası verir, hatayı çalışma zamanından önce yakalarız:
        // stringBox.set(123); // DERLEME HATASI! (Type mismatch)

        System.out.println("\n=== 3. Sınırlandırılmış (Bounded) Generics ===");
        NumberBox<Integer> intBox = new NumberBox<>(42);
        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        
        // NOT: Aşağıdaki satır derleme hatası verir çünkü String bir Number değildir:
        // NumberBox<String> stringBoxErr = new NumberBox<>("Hata"); 

        System.out.println("Integer Box Double Değeri: " + intBox.doubleValue());
        System.out.println("Double Box Double Değeri: " + doubleBox.doubleValue());

        System.out.println("\n=== 4. Generic Metot Kullanımı ===");
        String[] stringArray = {"Java", "Generics", "Harika"};
        Integer[] intArray = {1, 2, 3, 4, 5};

        System.out.print("String Dizisi: ");
        printArray(stringArray);

        System.out.print("Integer Dizisi: ");
        printArray(intArray);
    }
}