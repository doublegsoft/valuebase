package io.doublegsoft.valuebase;

import com.doublegsoft.jcommons.metavalue.UrlDefinition;
import com.doublegsoft.jcommons.metavalue.UrlParamDefinition;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Valuebase {

  public UrlDefinition url(String expr) {
    UrlDefinition retVal = new UrlDefinition();
    CharStream input = CharStreams.fromString(expr);
    io.doublegsoft.valuebase.ValuebaseLexer lexer = new io.doublegsoft.valuebase.ValuebaseLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    io.doublegsoft.valuebase.ValuebaseParser parser = new io.doublegsoft.valuebase.ValuebaseParser(tokens);
    io.doublegsoft.valuebase.ValuebaseParser.Valuebase_urlContext ctx = parser.valuebase_url();
    retVal.setResource(ctx.obj.getText());
    for (io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_paramContext ctxParam : ctx.valuebase_url_param()) {
      UrlParamDefinition param = new UrlParamDefinition();
      param.setName(ctxParam.name.getText());
      param.setComparator(ctxParam.comparator.getText());
      io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_valueContext ctxVal = ctxParam.valuebase_url_value();
      io.doublegsoft.valuebase.ValuebaseParser.Anybase_valueContext ctxAnyVal = ctxVal.anybase_value();
      if (ctxAnyVal.anybase_identifier() != null) {
        param.setValue(ctxAnyVal.anybase_identifier().getText());
        param.setType("object");
      }
      retVal.addParam(param);
    }
    return retVal;
  }

}
