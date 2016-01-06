POS
====

Following program is an solution for Point of Sale task.

Usage
--------
For your convenience, I've used mvn for building.

To run spock tests:

    mvn clean test

To run console application

    mvn clean compile exec:java

After run:

    Available products:
    123 : oranges -  1.00
    234 : apples - 32.30
    345 : kiwi - 10.01
    456 : melons -  0.12
    567 : papayas -  2.00

    Enter bar code: 123
    oranges                     1.00
    Enter bar code: 234
    apples                     32.30
    Enter bar code: exit
    -------------------------
    ----------Recipe---------
    -------------------------
       oranges           1.00
        apples          32.30
    -------------------------
    Total:              33.30
    -------------------------

Tools description
----------------------

* Java7 as platform
* TDD as development process
* mvn for building
* spock for unit testing and specification
* System.console for communication with user