import com.jecyhw.kech.html.KechDiseaseItem;
import com.jecyhw.kech.html.KechDiseaseItems;
import com.jecyhw.kech.service.KechService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jecyhw on 16-8-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/SpringConfig.xml")
public class Test {

    @Autowired
    KechService kechService;

    @Autowired
    KechDiseaseItems diseaseItems;

    @org.junit.Test
    public void test() {
        for (KechDiseaseItem diseaseItem : diseaseItems) {
            kechService.add(diseaseItem);
        }
    }
}
