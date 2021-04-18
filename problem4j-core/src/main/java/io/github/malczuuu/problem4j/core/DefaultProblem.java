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

public class DefaultProblem implements Serializable, Problem {

  private static final long serialVersionUID = 1L;

  private final URI type;
  private final String title;
  private final int status;
  private final String detail;
  private final URI instance;
  private final Map<String, Object> extensions;

  public DefaultProblem(
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

  public DefaultProblem(
      URI type, String title, int status, String detail, URI instance, Set<Extension> extensions) {
    this(type, title, status, detail, instance, buildMapFromExtensions(extensions));
  }

  public DefaultProblem(
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

  @Override
  public URI getType() {
    return this.type;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public int getStatus() {
    return this.status;
  }

  @Override
  public String getDetail() {
    return this.detail;
  }

  @Override
  public URI getInstance() {
    return this.instance;
  }

  @Override
  public Set<String> getExtensions() {
    return Collections.unmodifiableSet(extensions.keySet());
  }

  @Override
  public Object getExtensionValue(String name) {
    return extensions.get(name);
  }

  @Override
  public boolean hasExtension(String extension) {
    return extensions.containsKey(extension);
  }

  @Override
  public String toString() {
    List<String> lines = new ArrayList<>(4);
    if (type != null) {
      lines.add("\"type\": \"" + quote(type.toString()) + "\"");
    }
    if (title != null) {
      lines.add("\"title\": \"" + quote(title) + "\"");
    }
    lines.add("\"status\": " + status);
    if (detail != null) {
      lines.add("\"detail\": \"" + quote(detail) + "\"");
    }
    return lines.stream().collect(Collectors.joining(", ", "{ ", " }"));
  }

  public static String quote(String string) {
    return JsonEscape.escape(string);
  }

  public static final class Extension implements Problem.Extension {

    private final String key;
    private Object value;

    Extension(String key, Object value) {
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
        valueLine = "\"value\": " + "\"" + quote(value.toString()) + "\"";
      }
      return "{ \"key\": \"" + quote(key) + "\", " + valueLine + " }";
    }
  }
}
