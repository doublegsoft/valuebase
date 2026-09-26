package io.doublegsoft.valuebase;

import com.doublegsoft.jcommons.metavalue.*;
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
      retVal.addParam(createParam(ctxParam));
    }
    return retVal;
  }

  public ActionDefinition action(String expr) {
    ActionDefinition retVal = new ActionDefinition();
    CharStream input = CharStreams.fromString(expr);
    io.doublegsoft.valuebase.ValuebaseLexer lexer = new io.doublegsoft.valuebase.ValuebaseLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    io.doublegsoft.valuebase.ValuebaseParser parser = new io.doublegsoft.valuebase.ValuebaseParser(tokens);
    io.doublegsoft.valuebase.ValuebaseParser.Valuebase_actionContext ctx = parser.valuebase_action();
    retVal.setType(ActionType.getActionType(ctx.getText().substring(0, 1)));
    if (ctx.res != null) {
      String resText = ctx.res.resource.getText();
      if (resText.startsWith("$")) {
        resText = resText.substring(1);
      }
      retVal.setResource(resText);
      retVal.setMethod(ctx.res.method.getText());
    }
    if (ctx.path != null) {
      retVal.setPath(ctx.path.getText());
      retVal.setResource(retVal.getPath().substring(retVal.getPath().lastIndexOf("/") + 1));
      for (io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_paramContext ctxParam : ctx.valuebase_url_param()) {
        retVal.addParam(createParam(ctxParam));
      }
    }
    return retVal;
  }

  private UrlParamDefinition createParam(io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_paramContext ctxParam) {
    UrlParamDefinition retVal = new UrlParamDefinition();
    if (ctxParam.name != null) {
      retVal.setName(ctxParam.name.getText());
    } else if (ctxParam.object != null) {
      retVal.setName(ctxParam.object.getText());
      retVal.setType(ValueType.OBJECT);
    }
    if (ctxParam.comparator != null) {
      retVal.setComparator(ctxParam.comparator.getText());
    }
    io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_valueContext ctxVal = ctxParam.valuebase_url_value();
    if (ctxVal != null) {
      io.doublegsoft.valuebase.ValuebaseParser.Anybase_valueContext ctxAnyVal = ctxVal.anybase_value();
      if (ctxAnyVal.anybase_identifier() != null) {
        retVal.setValue(ctxAnyVal.anybase_identifier().getText());
        retVal.setType(ValueType.VARIABLE);
      } else if (ctxAnyVal.anybase_string() != null) {
        retVal.setValue(ctxAnyVal.anybase_string().getText());
        retVal.setType(ValueType.STRING);
      } else if (ctxAnyVal.anybase_number() != null) {
        retVal.setValue(ctxAnyVal.anybase_number().getText());
        retVal.setType(ValueType.NUMBER);
      } else if (ctxAnyVal.anybase_date() != null) {
        retVal.setValue(ctxAnyVal.anybase_date().getText());
        retVal.setType(ValueType.DATE);
      } else if (ctxAnyVal.anybase_datetime() != null) {
        retVal.setValue(ctxAnyVal.anybase_date().getText());
        retVal.setType(ValueType.DATETIME);
      }
    }
    return retVal;
  }

}
