import com.jecyhw.bcch.html.BcchDItem;
import com.jecyhw.bcch.html.BcchItems;
import com.jecyhw.bcch.service.BcchService;
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
    BcchService bcchService;

    @Autowired
    BcchItems diseaseItems;

    @org.junit.Test
    public void test() {
        for (BcchDItem diseaseItem : diseaseItems) {
            bcchService.add(diseaseItem);
        }
    }
}
