import org.junit.Test;
import ru.itis.Reverse;

public class ReverseTest {

    @Test
    public void reverseTest() {
        String s = "ABCDEFG";
        Reverse reverse = new Reverse(s);
        System.out.println(s + " <-> " + reverse);
    }
}
