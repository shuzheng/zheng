package com.zheng.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 长度校验
 * Created by shuzheng on 2017/2/18.
 */
public class LengthValidator extends ValidatorHandler<String> implements Validator<String> {

    private int min;

    private int max;

    private String fieldName;

    public LengthValidator(int min, int max, String fieldName) {
        this.min = min;
        this.max = max;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (null == s || s.length() > max || s.length() < min) {
            context.addError(ValidationError.create(String.format("LengthValidator => invalid : min=%s, max=%s", min, max))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(s));
            return false;
        }
        return true;
    }

}
