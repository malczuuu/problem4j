package io.github.malczuuu.problem4j.core;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem implements Serializable {

  public static final URI BLANK_TYPE = URI.create("about:blank");
  public static final String CONTENT_TYPE = "application/problem+json";

  public static ProblemBuilder builder() {
    return new ProblemBuilderImpl();
  }

  public static ProblemBuilder builder(Problem problem) {
    ProblemBuilder builder = new ProblemBuilderImpl();
    builder.type(problem.getType());
    builder.title(problem.getTitle());
    builder.status(problem.getStatus());
    builder.detail(problem.getDetail());
    builder.instance(problem.getInstance());
    problem.getExtensions().forEach(e -> builder.extension(e, problem.getExtensionValue(e)));
    return builder;
  }

  public static Extension extension(String key, Object value) {
    return new Extension(key, value);
  }

  private final URI type;
  private final String title;
  private final int status;
  private final String detail;
  private final URI instance;
  private final Map<String, Object> extensions;

  public Problem(
      URI type,
      String title,
      int status,
      String detail,
      URI instance,
      Map<String, Object> extensions) {
    this.type = type;
    this.title = title;
    this.status = status;
    this.detail = detail;
    this.instance = instance;
    this.extensions = Collections.unmodifiableMap(new HashMap<>(extensions));
  }

  public Problem(
      URI type, String title, int status, String detail, URI instance, Set<Extension> extensions) {
    this(type, title, status, detail, instance, buildMapFromExtensions(extensions));
  }

  public Problem(
      URI type, String title, int status, String detail, URI instance, Extension... extensions) {
    this(type, title, status, detail, instance, buildMapFromExtensions(extensions));
  }

  private static Map<String, Object> buildMapFromExtensions(Set<Extension> extensions) {
    Map<String, Object> map = new HashMap<>(extensions.size());
    extensions.forEach(e -> map.put(e.getKey(), e.getValue()));
    return map;
  }

  private static Map<String, Object> buildMapFromExtensions(Extension[] extensions) {
    Map<String, Object> map = new HashMap<>(extensions.length);
    for (Problem.Extension e : extensions) {
      map.put(e.getKey(), e.getValue());
    }
    return map;
  }

  public URI getType() {
    return this.type;
  }

  public String getTitle() {
    return this.title;
  }

  public int getStatus() {
    return this.status;
  }

  public String getDetail() {
    return this.detail;
  }

  public URI getInstance() {
    return this.instance;
  }

  public Set<String> getExtensions() {
    return Collections.unmodifiableSet(extensions.keySet());
  }

  public Object getExtensionValue(String name) {
    return extensions.get(name);
  }

  public boolean hasExtension(String extension) {
    return extensions.containsKey(extension);
  }

  @Override
  public String toString() {
    List<String> lines = new ArrayList<>(4);
    if (type != null) {
      lines.add("\"type\": \"" + type + "\"");
    }
    if (title != null) {
      lines.add("title: \"" + title + "\"");
    }
    lines.add("\"status\": " + status);
    if (detail != null) {
      lines.add("\"detail\": \"" + detail + "\"");
    }
    return lines.stream().collect(Collectors.joining(", ", "{ ", " }"));
  }

  public static final class Extension implements Map.Entry<String, Object> {

    private final String key;
    private Object value;

    private Extension(String key, Object value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public Object getValue() {
      return value;
    }

    @Override
    public Object setValue(Object value) {
      this.value = value;
      return value;
    }

    @Override
    public String toString() {
      String valueLine;
      if (value instanceof Number || value instanceof Boolean) {
        valueLine = "\"value\": " + value;
      } else {
        valueLine = "\"value\": " + "\"" + value + "\"";
      }
      return "{ \"key\": \"" + key + "\", " + valueLine + " }";
    }
  }
}
