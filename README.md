# find-kth-element

Finds out kth character from a huge binary where string construction follows certain criterias.

`Problem:`
A "0/1 string" is a string in which every character is either 0 or 1. There are two operations that can be performed on a 0/1 string:

switch: Every 0 becomes 1 and every 1 becomes 0. For example, "100" becomes "011".
reverse: The string is reversed. For example, "100" becomes "001".
Consider this infinite sequence of 0/1 strings:
```
S0 = ""

S1 = "0"

S2 = "001"

S3 = "0010011"

S4 = "001001100011011"

...

SN = SN-1 + "0" + switch(reverse(SN-1)).
```
You need to figure out the Kth character of Sn, where n = 10^100.

There are input files in `resources` folder, where first line represents number of test-cases and after that all the lines represents k-value, which character we have to find out in the string.

We need to write output in a file, as follows:
```
Case #1: 0
Case #2: 0
Case #3: 1
Case #4: 0
... so on
```
