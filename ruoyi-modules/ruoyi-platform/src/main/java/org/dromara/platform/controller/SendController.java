package org.dromara.platform.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mail.utils.MailUtils;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/tool/send")
public class SendController extends BaseController {

    //@SaCheckPermission("platfrom:search:style")
//    @GetMapping("/sms")
//    public R<Void> searchMemberInfo(@RequestParam String content, @RequestParam String[] phones) {
//        String result = MailUtils.send(phones, "测试邮件", content, false);
//        return toAjax(result);
//    }

    @PostMapping("/mail")
    public R<Void> sendMail(@RequestParam String accout,
                            @RequestParam String subject,
                            @RequestParam String content) {
        String result = MailUtils.send(accout, subject, content, false);
        return toAjax(result);
    }

}
