package com.sanri.tools.modules.name.service;

public interface TokenizerTool extends ToolName{
    /**
     * 分词操作
     * @param translateCharSequence
     */
    void doTokenizer(TranslateCharSequence translateCharSequence);
}
