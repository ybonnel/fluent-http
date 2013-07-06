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

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class LayoutTest {
  @Test
  public void should_apply_layout() {
    Layout layout = new Layout("header/[[body]]/footer");

    String content = layout.apply("<body>Hello</body>");

    assertThat(content).isEqualTo("header/<body>Hello</body>/footer");
  }
}
