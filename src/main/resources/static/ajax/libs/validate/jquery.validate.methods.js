/**
 * @requires jquery.validate.js
 * @author HuberyYan
 */
(function ($) {
    if ($.validator) {
        $.validator.addMethod("alphanumeric", function (value, element) {
            return this.optional(element) || /^\w+$/i.test(value);
        }, "Letters, numbers or underscores only please");

        $.validator.addMethod("lettersonly", function (value, element) {
            return this.optional(element) || /^[a-z]+$/i.test(value);
        }, "Letters only please");

        $.validator.addMethod("phone", function (value, element) {
            return this.optional(element) || /^[0-9 \(\)]{7,30}$/.test(value);
        }, "Please specify a valid phone number");

        $.validator.addMethod("postcode", function (value, element) {
            return this.optional(element) || /^[0-9 A-Za-z]{5,20}$/.test(value);
        }, "Please specify a valid postcode");

        $.validator.addMethod("number", function (value, element) {
            var pattern = /^(([1-9][0-9]*\.[0-9][0-9]*)|([0]\.[0-9][0-9]*)|([1-9][0-9]*)|([0]{1}))$/;
            if (!pattern.test(value)) {
                $(element).data('error-msg', '请输入正确的金额！');
                return false;
            }
            var num = (value.split(',')).length;
            if (num > 7) {
                $(element).data('error-msg', '最多输入7位数字！');
                return false;
            }
            // this.optional(element)

            return true;
        }, function (params, element) {
            return $(element).data('error-msg');
        });

        $.validator.addMethod("date", function (value, element) {
            value = value.replace(/\s+/g, "");
            if (String.prototype.parseDate) {
                var $input = $(element);
                var pattern = $input.attr('dateFmt') || 'yyyy-MM-dd';

                return !$input.val() || $input.val().parseDate(pattern);
            } else {
                return this.optional(element) || value.match(/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/);
            }
        }, "Please enter a valid date.");

        /*自定义js函数验证
         * <input type="text" name="xxx" customvalid="xxxFn(element)" title="xxx" />
         */
        $.validator.addMethod("idcard", function (value, element) {
            return this.optional(element) || CheckUtil.IdCardValidate(value);
        }, "请输入正确格式的身份证号");

        $.validator.addClassRules({
            date: {date: true},
            alphanumeric: {alphanumeric: true},
            lettersonly: {lettersonly: true},
            phone: {phone: true},
            postcode: {postcode: true},
            idcard: {idcard: true}
        });
        $.validator.setDefaults({errorElement: "span"});
        $.validator.autoCreateRanges = true;
    }

})(jQuery);