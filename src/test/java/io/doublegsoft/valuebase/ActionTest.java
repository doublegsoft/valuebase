package io.doublegsoft.valuebase;

import com.doublegsoft.jcommons.metavalue.ActionDefinition;
import com.doublegsoft.jcommons.metavalue.UrlDefinition;
import com.doublegsoft.jcommons.metavalue.ValueType;
import org.junit.Assert;
import org.junit.Test;

public class ActionTest {

  @Test
  public void test_action_1() throws Exception {
    String expr = "$self.close";
    ActionDefinition action = new Valuebase().action(expr);
    Assert.assertEquals("WIDGET", action.getType().name());
    Assert.assertEquals("self", action.getResource());
    Assert.assertEquals("close", action.getMethod());
  }

}
