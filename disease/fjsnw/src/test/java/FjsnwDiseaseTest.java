import com.jecyhw.fjsnw.html.FjsnwDiseaseItem;
import com.jecyhw.fjsnw.html.FjsnwDiseaseItems;
import com.jecyhw.fjsnw.html.FjsnwDiseases;
import com.jecyhw.fjsnw.service.FjsnwService;
import com.jecyhw.shared.request.Page;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jecyhw on 16-8-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/SpringConfig.xml")
public class FjsnwDiseaseTest {

    @Autowired
    FjsnwDiseases diseases;

    @Autowired
    FjsnwService service;

    @org.junit.Test
    public void test() {
        for (Page disease : diseases) {
            for (FjsnwDiseaseItem diseaseItem : new FjsnwDiseaseItems(disease)) {
                service.add(diseaseItem);
            }
        }

    }
}
