package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (validField("spuId",metaObject)) {
            this.strictInsertFill(metaObject, "spuId", Integer.class, 0);
        }
        if (validField("spuOptionValue",metaObject)) {
            this.strictInsertFill(metaObject, "spuOptionValue", String.class, "");
        }
        if (validField("spuOptionIndex",metaObject)) {
            this.strictInsertFill(metaObject, "spuOptionIndex", Integer.class, 0);
        }
        if (validField("description",metaObject)) {
            this.strictInsertFill(metaObject, "description", String.class, "");
        }
        if (validField("rank",metaObject)) {
            this.strictInsertFill(metaObject, "rank", Integer.class, 0);
        }
        if (validField("productLineId",metaObject)) {
            this.strictInsertFill(metaObject, "productLineId", Integer.class, 0);
        }
        if (validField("successCheckout",metaObject)) {
            this.strictInsertFill(metaObject, "successCheckout", Integer.class, 0);
        }
        if (validField("tag",metaObject)) {
            this.strictInsertFill(metaObject, "tag", Integer.class, 0);
        }
        if (validField("stock",metaObject)) {
            this.strictInsertFill(metaObject, "stock", Integer.class, 0);
        }
        if (validField("publishedAt",metaObject)) {
            this.strictInsertFill(metaObject, "publishedAt", Long.class, 0L);
        }
        if (validField("createAt",metaObject)) {
            this.strictInsertFill(metaObject, "createAt", Long.class, System.currentTimeMillis());
        }
        if (validField("updateAt",metaObject)) {
            this.strictInsertFill(metaObject, "updateAt", Long.class, System.currentTimeMillis());
        }
        if (validField("videoUrl",metaObject)) {
            this.strictInsertFill(metaObject, "videoUrl", String.class,"");
        }
        if (validField("previewImage",metaObject)) {
            this.strictInsertFill(metaObject, "previewImage", String.class, "");
        }
        if (validField("status",metaObject)) {
            this.strictInsertFill(metaObject, "status", Boolean.class, false);
        }
        if (validField("model",metaObject)) {
            this.strictInsertFill(metaObject, "model", String.class, "");
        }
        if (validField("updateBy",metaObject)) {
            this.strictInsertFill(metaObject, "updateBy", String.class, "");
        }
        if (validField("compareAtPrice",metaObject)) {
            this.strictInsertFill(metaObject, "compareAtPrice", BigDecimal.class, BigDecimal.ZERO);
        }
        if (validField("integralPoints",metaObject)) {
            this.strictInsertFill(metaObject, "integralPoints", Integer.class, 0);
        }
        if (validField("maxIntegralProportion",metaObject)) {
            this.strictInsertFill(metaObject, "maxIntegralProportion", BigDecimal.class, BigDecimal.ZERO);
        }
        if (validField("openQuestion",metaObject)) {
            this.strictInsertFill(metaObject, "openQuestion", Boolean.class, true);
        }
        if (validField("weight",metaObject)) {
            this.strictInsertFill(metaObject, "weight", BigDecimal.class, BigDecimal.ZERO);
        }
        if (validField("pdfUrl",metaObject)) {
            this.strictInsertFill(metaObject, "pdfUrl", String.class, "");
        }
        if (validField("pdfName",metaObject)) {
            this.strictInsertFill(metaObject, "pdfName", String.class, "");
        }
    }

    /**
     * 我们希望，有这个属性的实体才调用自动填充的方法；而没有这个属性的实体，就不调用自动填充的方法。
     * 而默认情况下：它是每次都会调，而不管你有没有这个属性，这样比较浪费性能。
     * 我们希望当我们自己设置了更新时间时，就不需要自动填充了
     * 当我们没有设置更新值时，就给我们进行自动填充。
     * @param name
     * @param metaObject
     * @return
     */
    private Boolean validField(String name, MetaObject metaObject) {
        return metaObject.hasSetter(name) && getFieldValByName(name, metaObject) == null;
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
