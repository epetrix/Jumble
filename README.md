# Jumbles 

*Project for the University of Pittsburgh's Intermediate Java class (2017)*

A competition among the 71 students (72 with the professor included) to see who could get the fastest runtime. I came in fourth, the professor came in second. 

This program reads in two text files, one a dictionary containing thousands of words, the other a short list of scrambled words. The task is to compare the two text files and unscramble the words.

My solution was to read in each word and assign it an integer value which I would use to store it in a HashMap; one for dictionary words and one for scrambled. Once finished I compared Lists of words that shared the same value key and if they were the same I would add them to the Results Map. 
