# Apache Lucene / Lucene Analysis README file

## Introduction

The Analysis Module provides analysis capabilities to Lucene and Solr
applications.

Lucene is a Java full-text search engine.  Lucene is not a complete
application, but rather a code library and API that can easily be used
to add search capabilities to applications.

The Lucene web site is at:
    http://lucene.apache.org/

Please join the Lucene-User mailing list by sending a message to:
    java-user-subscribe@lucene.apache.org

### Lucene Analysis Common

The primary analysis module library, containing general-purpose analysis
components and support for various languages.


## Snowball Stemmers in Lucene Analysis

This project provides pre-compiled version of the Snowball stemmers
based on revision 500 of the Tartarus Snowball repository,
together with classes integrating them with the Lucene search engine.

A few changes has been made to the static Snowball code and compiled stemmers:

 * Class SnowballProgram is made abstract and contains new abstract method stem() to avoid reflection in Lucene filter class SnowballFilter.
 * All use of StringBuffers has been refactored to StringBuilder for speed.
 * Snowball BSD license header has been added to the Java classes to avoid having RAT adding new ASL headers.
