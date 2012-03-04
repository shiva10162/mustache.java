package com.github.mustachejava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExtensionTest {

  private static File root;

  @Test
  public void testSub() throws MustacheException, IOException, ExecutionException, InterruptedException {
    MustacheFactory c = new DefaultMustacheFactory(root);
    Mustache m = c.compile("sub.html");
    StringWriter sw = new StringWriter();
    Map scope = new HashMap();
    scope.put("name", "Sam");
    scope.put("randomid", "asdlkfj");
    m.execute(sw, scope);
    assertEquals(getContents(root, "sub.txt"), sw.toString());
  }

  @Test
  public void testFollow() throws MustacheException, IOException, ExecutionException, InterruptedException {
    MustacheFactory c = new DefaultMustacheFactory(root);
    Mustache m = c.compile("follownomenu.html");
    StringWriter sw = new StringWriter();
    m.execute(sw, new Object());
    assertEquals(getContents(root, "follownomenu.txt"), sw.toString());
  }

  @Test
  public void testSubSub() throws MustacheException, IOException, ExecutionException, InterruptedException {
    MustacheFactory c = new DefaultMustacheFactory(root);
    Mustache m = c.compile("subsub.html");
    StringWriter sw = new StringWriter();
    Map scope = new HashMap();
    scope.put("name", "Sam");
    scope.put("randomid", "asdlkfj");
    m.execute(sw, scope);
    assertEquals(getContents(root, "subsub.txt"), sw.toString());
  }

  protected String getContents(File root, String file) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(root, file)),"UTF-8"));
    StringWriter capture = new StringWriter();
    char[] buffer = new char[8192];
    int read;
    while ((read = br.read(buffer)) != -1) {
      capture.write(buffer, 0, read);
    }
    return capture.toString();
  }

  @BeforeClass
  public static void setUp() throws Exception {
    File file = new File("src/test/resources");
    root = new File(file, "sub.html").exists() ? file : new File("../src/test/resources");
  }


}