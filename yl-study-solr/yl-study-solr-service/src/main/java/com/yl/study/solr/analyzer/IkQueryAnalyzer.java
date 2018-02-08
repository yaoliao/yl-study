package com.yl.study.solr.analyzer;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;

public class IkQueryAnalyzer implements QueryAnalyzer {

    private static Analyzer anal = null;

    @Override
    public String analyze(String query) {
        StringBuilder result = new StringBuilder();
        try {
            if (StringUtils.isNotEmpty(query)) {
                anal = new IKAnalyzer(true);
                StringReader reader = new StringReader(query);
                //分词
                TokenStream ts = anal.tokenStream("", reader);
                CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
                ts.reset();
                boolean flag = true;
                //遍历分词数据
                while (ts.incrementToken()) {
                    if (flag) {
                        result.append(term.toString());
                        flag = false;
                    } else {
                        result.append("\\\\");
                        result.append(term.toString());
                    }

                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString().toUpperCase();
    }

}
