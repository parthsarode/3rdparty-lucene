package org.apache.lucene.search.vectorhighlight;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Simple boundary scanner implementation that divides fragments
 * based on a set of separator characters.
 */
public class CSimpleBoundaryScanner implements BoundaryScanner {
  
  public static final int DEFAULT_MAX_SCAN = 20;


  protected int maxScan;


  public CSimpleBoundaryScanner() {
    this( DEFAULT_MAX_SCAN );
  }

  public CSimpleBoundaryScanner(int maxScan) {
    this.maxScan= maxScan;
  }

  @Override
  public int findStartOffset(StringBuilder buffer, int start) {
    // avoid illegal start offset
    if( start > buffer.length() || start < 1 ) return start;
    
    int fallback= start;
    int stop= Math.max(0, start - maxScan);
    for (int offset= start; offset > stop; offset-- ) {
      final char c= buffer.charAt(offset - 1);
      if (c < 0x20 || Character.isWhitespace(c)) {
        return offset;
      }
      if (!Character.isLetterOrDigit(c)) {
        fallback= offset;
      }
    }
    // if we scanned up to the start of the text, return it, its a "boundary"
    if (stop == 0) {
      return 0;
    }
    // not found
    return fallback;
  }

  @Override
  public int findEndOffset(StringBuilder buffer, int start) {
    // avoid illegal start offset
    if( start > buffer.length() || start < 0 ) {
      return start;
    }
    
    int fallback= start;
    final int stop= Math.min(buffer.length(), start + this.maxScan);
    for (int offset= start; offset < stop; offset++){
      final char c= buffer.charAt(offset);
      if (c < 0x20 || Character.isWhitespace(c)) {
        return offset;
      }
      if (!Character.isLetterOrDigit(c)) {
        fallback= offset;
      }
    }
    // if we scanned up to the end of the text, return it, its a "boundary"
    if (stop == buffer.length()) {
      return stop;
    }
    // not found
    return fallback;
  }

}
