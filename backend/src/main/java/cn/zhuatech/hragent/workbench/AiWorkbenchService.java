/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.hragent.workbench;

import static cn.zhuatech.hragent.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "text");
    String value = text(r, "text", 16000);
    String[] patterns = {
      "(?<![0-9])[0-9]{17}[0-9Xx](?![0-9])",
      "(?<![0-9])1[3-9][0-9]{9}(?![0-9])",
      "(?<![A-Za-z0-9._%+\\-])[A-Za-z0-9._%+\\-]{1,64}@[A-Za-z0-9.\\-]{1,253}\\.[A-Za-z]{2,63}(?![A-Za-z])"
    };
    String[] names = {"identityNumber", "mobile", "email"},
        masks = {"[IDENTITY]", "[MOBILE]", "[EMAIL]"};
    List<Map<String, Object>> out = new ArrayList<>();
    int total = 0;
    for (int i = 0; i < patterns.length; i++) {
      var matcher = java.util.regex.Pattern.compile(patterns[i]).matcher(value);
      int count = 0;
      while (matcher.find()) count++;
      value = matcher.replaceAll(java.util.regex.Matcher.quoteReplacement(masks[i]));
      total += count;
      out.add(Map.of("type", names[i], "count", count));
    }
    return Map.of(
        "redactedText", value, "matches", total, "categories", out, "manualReviewRequired", true);
  }
}
