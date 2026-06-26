package io.doublegsoft.valuebase;

import com.doublegsoft.jcommons.metavalue.UrlDefinition;
import com.doublegsoft.jcommons.metavalue.ValueType;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

public class UrlTest {

  @Test
  public void test_url_0() throws Exception {
    String expr = "simple_object?param1=var1&param2>var2&param3<=var3.first_day_of_week";
    UrlDefinition url = new Valuebase().url(expr);
    Assert.assertEquals("simple_object", url.getResource());
    Assert.assertEquals(3, url.getParams().size());
  }

  @Test
  public void test_url_1() throws Exception {
    String expr = "simple_object?{abc}";
    UrlDefinition url = new Valuebase().url(expr);
    Assert.assertEquals("simple_object", url.getResource());
    Assert.assertEquals("abc", url.getParams().get(0).getName());
    Assert.assertEquals(ValueType.OBJECT, url.getParams().get(0).getType());
  }

  private io.doublegsoft.valuebase.ValuebaseParser.Valuebase_urlContext parse(String expr) throws Exception {
    CharStream input = CharStreams.fromString(expr);
    io.doublegsoft.valuebase.ValuebaseLexer lexer = new io.doublegsoft.valuebase.ValuebaseLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    io.doublegsoft.valuebase.ValuebaseParser parser = new io.doublegsoft.valuebase.ValuebaseParser(tokens);
    return parser.valuebase_url();
  }

}
