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
package net.codestory.http.templating;

import java.io.*;
import java.util.*;

import com.github.mustachejava.*;

public class MustacheCompiler {
  public String compile(String templateContent, Map<String, Object> variables) throws IOException {
    DefaultMustacheFactory factory = new DefaultMustacheFactory() {
      @Override
      public Reader getReader(String partialName) {
        String partial = new Template("_includes/" + partialName).render(variables);
        return new StringReader(partial);
      }
    };

    Mustache mustache = factory.compile(new StringReader(templateContent), "", "[[", "]]");

    StringWriter writer = new StringWriter();
    mustache.execute(writer, variables);
    return writer.toString();
  }
}
