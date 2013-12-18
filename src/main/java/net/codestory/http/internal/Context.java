/**
 * Copyright (C) 2013 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.internal;

import java.io.*;
import java.util.*;

import net.codestory.http.io.*;

import org.simpleframework.http.*;

public class Context {
  private final String uri;
  private final Request request;
  private final Response response;
  private final Query query;

  public Context(String uri, Request request, Response response) {
    this.uri = uri;
    this.request = request;
    this.response = response;
    this.query = request.getQuery();
  }

  public String uri() {
    return uri;
  }

  public Cookie cookie(String name) {
    return request.getCookie(name);
  }

  public List<Cookie> cookies() {
    return request.getCookies();
  }

  public String get(String name) {
    return query.get(name);
  }

  public List<String> getAll(String name) {
    return query.getAll(name);
  }

  public int getInteger(String name) {
    return query.getInteger(name);
  }

  public float getFloat(String name) {
    return query.getFloat(name);
  }

  public boolean getBoolean(String name) {
    return query.getBoolean(name);
  }

  public String getHeader(String name) {
    return request.getValue(name);
  }

  public List<String> getHeaders(String name) {
    return request.getValues(name);
  }

  public String method() {
    return request.getMethod();
  }

  public Map<String, String> keyValues() {
    return query;
  }

  public Request request() {
    return request;
  }

  public Response response() {
    return response;
  }

  public byte[] payload() {
    try {
      return InputStreams.readBytes(request.getInputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Unable to read payload", e);
    }
  }
}
