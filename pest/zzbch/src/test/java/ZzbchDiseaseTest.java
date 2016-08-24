import com.jecyhw.zzbch.html.ZzbchPestItem;
import com.jecyhw.zzbch.html.ZzbchPestItems;
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
    ZzbchPestItems diseaseItems;

    @Autowired
    ZzbchService service;

    @org.junit.Test
    public void test() {

//        for (ZzbchPestItem diseaseItem : diseaseItems) {
//            service.add(diseaseItem);
//        }
        ZzbchPestItem item = new ZzbchPestItem("http://www.veg.org.cn/zzbch/detailworm.asp?id=19");
        service.add(item);
    }
}
