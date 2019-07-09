# tfmaida

This is the Trabajo Fin de Máster of Aida Sánchez Romero.

Information Extraction is one of the main areas of focus inside Articial Intelligence.
This task focuses on deriving structured information from an unstructured
or semi-structured document using Natural Language Processing (NLP) techniques.
These documents can be very varied from press articles to scientic reports. Hence,
this is not a simple task (even though the domain is perfectly limited) due to the
complexity and ambiguity of natural language.

Therefore, Information Extraction has been studied in several very restricted
domains involving a large community of researchers for more than three decades. In
spite of this, it is not until just over 5 years ago that people started to talk about the
"legaltech" or legal technology concept. This concept refers to the use of technology
and softwares to other legal services. Some of these services include review contracts
to and unacceptable or required clauses (LawGeex); help detect deadlines, obligations
or validity in due diligence processes (Luminance); or analyze jurisprudence
in order to extract relevant information to raise a procedural strategy (Ravel Law).

In this work, we propose to create a software system based on this concept. The
intention is to this system is able to extract the relevant information from a license
written in natural language. Thus, it must uses techniques based on NLP and, above
all, it must uses techniques in order to extract the type of events (or actions) that
are allowed, required and/or forbidden. From this data it must creates a valid RDF
that contains them along with the name, version and text or legal code of the license.
Consequently, the type of events that should be detected are, among others, if their
commercial use, copy, distribution or modication is allowed or not. Other possible
events should be detected could be if the user must provide the source code or credit
the creator/s of the license.

## How to install 

* Prerequisite: latest Java Version, NetBeans (8.2 version used)

* Download the code from GitHub or clone the repository on your computer.

## How to use

* The main.java class can be executed directly with the present information. If you wish to enter any other license, just save it in a text file (it is not necessary to specify the extension) and substitute the main path for the one with the new saved file. There is also a set of nine other test files that can be executed by changing the end of the path specified as 1.txt by anyone of the files [2-10].txt.

* The output should resemble the ones seen on Fig.16 (can be found in the pdf "TFM_AIDA_SANCHEZ_ROMERO" within 'doc' folder). 

Note: the execution time should not exceed 12 seconds.
