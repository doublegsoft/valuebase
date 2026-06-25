package io.doublegsoft.valuebase;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

public class UrlTest {

  @Test
  public void test_url_0() throws Exception {
    String expr = "://simple_object?param1=var1&param2>var2&param3<=var3.first_day_of_week";
    io.doublegsoft.valuebase.ValuebaseParser.Valuebase_urlContext ctxUrl = parse(expr);
    Assert.assertEquals("simple_object", ctxUrl.obj.getText());
  }

  private io.doublegsoft.valuebase.ValuebaseParser.Valuebase_urlContext parse(String expr) throws Exception {
    CharStream input = CharStreams.fromString(expr);
    io.doublegsoft.valuebase.ValuebaseLexer lexer = new io.doublegsoft.valuebase.ValuebaseLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    io.doublegsoft.valuebase.ValuebaseParser parser = new io.doublegsoft.valuebase.ValuebaseParser(tokens);
    return parser.valuebase_url();
  }

}
