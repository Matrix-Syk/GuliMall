package com.matrix.gulimall.product;

import com.matrix.gulimall.product.entity.BrandEntity;
import com.matrix.gulimall.product.service.BrandService;
import com.matrix.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GulimallProductApplication.class)
class GulimallProductApplicationTests {
    @Autowired
    private BrandService brandService;
    @Resource
    private CategoryService categoryService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("小米");
        brandEntity.setDescript("粗粮产品，必属废品");
        brandEntity.setShowStatus(3);
        brandEntity.setFirstLetter("粗");
        boolean save = brandService.save(brandEntity);
        System.out.println(save);
    }

    @Test
    void test1() {
        Long[] longs = categoryService.queryPath(255L);
        for (Long aLong : longs) {
            log.info("---{}",aLong);
        }
    }
}
