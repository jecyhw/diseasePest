import com.jecyhw.shared.request.Page;
import com.jecyhw.zzbch.html.ZzbchDiseaseItem;
import com.jecyhw.zzbch.html.ZzbchDiseaseItems;
import com.jecyhw.zzbch.service.ZzbchService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jecyhw on 16-8-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/SpringConfig.xml")
public class ZzbchDiseaseTest {

    @Autowired
    ZzbchDiseaseItems diseaseItems;

    @Autowired
    ZzbchService service;

    @org.junit.Test
    public void test() {
//        for (ZzbchDiseaseItem diseaseItem : diseaseItems) {
//            service.add(diseaseItem);
//        }
        ZzbchDiseaseItem item = new ZzbchDiseaseItem("http://www.veg.org.cn/zzbch/detailill.asp?id=266");
        service.add(item);

    }
}
