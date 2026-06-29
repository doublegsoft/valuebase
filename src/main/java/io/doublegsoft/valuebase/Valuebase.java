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
      UrlParamDefinition param = new UrlParamDefinition();
      if (ctxParam.name != null) {
        param.setName(ctxParam.name.getText());
      } else if (ctxParam.object != null) {
        param.setName(ctxParam.object.getText());
        param.setType(ValueType.OBJECT);
      }
      if (ctxParam.comparator != null) {
        param.setComparator(ctxParam.comparator.getText());
      }
      io.doublegsoft.valuebase.ValuebaseParser.Valuebase_url_valueContext ctxVal = ctxParam.valuebase_url_value();
      if (ctxVal != null) {
        io.doublegsoft.valuebase.ValuebaseParser.Anybase_valueContext ctxAnyVal = ctxVal.anybase_value();
        if (ctxAnyVal.anybase_identifier() != null) {
          param.setValue(ctxAnyVal.anybase_identifier().getText());
          param.setType(ValueType.VARIABLE);
        } else if (ctxAnyVal.anybase_string() != null) {
          param.setValue(ctxAnyVal.anybase_string().getText());
          param.setType(ValueType.STRING);
        } else if (ctxAnyVal.anybase_number() != null) {
          param.setValue(ctxAnyVal.anybase_number().getText());
          param.setType(ValueType.NUMBER);
        } else if (ctxAnyVal.anybase_date() != null) {
          param.setValue(ctxAnyVal.anybase_date().getText());
          param.setType(ValueType.DATE);
        } else if (ctxAnyVal.anybase_datetime() != null) {
          param.setValue(ctxAnyVal.anybase_date().getText());
          param.setType(ValueType.DATETIME);
        }
      }
      retVal.addParam(param);
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
      retVal.setResource(ctx.res.resource.getText());
      retVal.setMethod(ctx.res.method.getText());
    }
    if (ctx.path != null) {
      retVal.setPath(ctx.path.getText());
    }
    return retVal;
  }

}
