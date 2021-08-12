package com.sanri.tools.modules.core.service.data.regex;


import com.sanri.tools.modules.core.service.data.regex.exception.RegexpIllegalException;
import com.sanri.tools.modules.core.service.data.regex.exception.TypeNotMatchException;
import com.sanri.tools.modules.core.service.data.regex.exception.UninitializedException;

public interface Node {

    String getExpression();

    String random() throws UninitializedException, RegexpIllegalException;

    boolean test();

    void init() throws RegexpIllegalException, TypeNotMatchException;

    boolean isInitialized();
}
