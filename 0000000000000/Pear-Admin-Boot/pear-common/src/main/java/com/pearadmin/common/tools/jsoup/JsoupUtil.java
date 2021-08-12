package com.pearadmin.common.tools.jsoup;

import com.pearadmin.common.constant.SystemConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupUtil {

    private static final Whitelist WHITELIST = Whitelist.basicWithImages();

    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);

    static {
        WHITELIST.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, SystemConstant.EMPTY, WHITELIST, OUTPUT_SETTINGS);
    }

}
