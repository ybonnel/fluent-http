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
package net.codestory.http.compilers;

import static java.nio.charset.StandardCharsets.*;

import java.io.*;

import net.codestory.http.io.*;

public interface CacheEntry extends Serializable {
  String content();

  byte[] toBytes();

  long lastModified();

  public static CacheEntry disk(File file) {
    return new CacheEntry() {
      @Override
      public String content() {
        try {
          try (InputStream input = new FileInputStream(file)) {
            return InputStreams.readString(input, UTF_8);
          }
        } catch (IOException e) {
          throw new IllegalStateException("Unable to read file", e);
        }
      }

      @Override
      public byte[] toBytes() {
        try {
          try (InputStream input = new FileInputStream(file)) {
            return InputStreams.readBytes(input);
          }
        } catch (IOException e) {
          throw new IllegalStateException("Unable to read file", e);
        }
      }

      @Override
      public long lastModified() {
        return file.lastModified();
      }
    };
  }

  public static CacheEntry memory(String content) {
    return new CacheEntry() {
      private final long lastModified = System.currentTimeMillis();

      @Override
      public String content() {
        return content;
      }

      @Override
      public byte[] toBytes() {
        return content.getBytes(UTF_8);
      }

      @Override
      public long lastModified() {
        return lastModified;
      }
    };
  }
}
