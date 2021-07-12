# ReadabilityScore
The following lines describe the project (JetBrains Academy). The text is taken from: https://hyperskill.org/projects/39

Stage 1/4: Simplest estimation

Description
Ever wondered how to assess if the text is hard or easy to read? For a human, this task is pretty simple: you just read the text and feel if you're struggling or not. But how to teach a computer do that? In this project, you will write such a program.

Firstly, let's create a simple program. If a text contains more than 100 symbols (including spaces and punctuation), then it is considered hard to read. Otherwise, the text is considered easy to read. If a text contains exactly 100 symbols, it is still easy to read.

The input contains a single line of text. Output "HARD" if the text is hard to read and "EASY" if the text is easy to read.


Stage 2/4: Words and sentences

Description
However, a real text may be pretty long and still easy to read, right? There needs to be another approach. How about calculating the number of words in each sentence? Clearly, if each sentence of a text contains a lot of words, this text is hard to read.

Suppose that if the text contains sentences that on average have more than 10 words per sentence, this text is hard to read. Otherwise, this text is easy to read. Don't worry, we will consider more scientific ways in the next stages.

The input contains a single line of text. Output "HARD" if the text is hard to read and "EASY" if the text is easy to read.

For example, the first example contains two sentences with 6 and 10 words (numbers also count as words) so the average is 8, and this is less than 10. In the second example, there are also 2 sentences but with 6 and 16 words, so the average is 11 and this is more than 10. If the average is equal to 10, the text is still considered easy to read.

Don't forget that sentences can end with a full stop as well as with an exclamation mark and a question mark. But the last sentence can be with or without a punctuation mark at the end.

Stage 3/4: Score!

Description
In this stage, you will program the Automated readability index. It was introduced in 1968 and a lot of research works rely on this. The index is calculated by the following formula:

score = 4.71 \times \dfrac{characters}{words} + 0.5 \times \dfrac{words}{sentences} - 21.43score=4.71× 
words
characters
​	
 +0.5× 
sentences
words
​	
 −21.43

You can look at different ages corresponding to the different scores by the table in this article.

Also, your program should read a file instead of typing a text manually. You should pass the filename through the command line arguments.

The program should output the score itself and an approximate age needed to comprehend the text.

Use the appropriate rounding function to calculate the score as integer.

You should also print how many characters, words, and sentences the text has.

The number of characters is any visible symbol (so, in the real text it's everything except space, newline "\n" and tab "\t").

Notice, that the text can contain multiple lines, not just a single line like in the previous stages. You should analyze all the lines.

Stage 4/4: More parameters

Description
In this stage, you should implement various other scientific approaches to calculate a readability score.

Take a look at different ages and corresponding scores in the table in this article. This table is suitable for all the algorithms described in this stage. To calculate the age use the upper bound of the range. For example, if the range is 12-13-year-olds then it's upper bound is 13-year-olds.

The first algorithm is Flesch–Kincaid readability tests. First, you need to create a method that calculates the number of syllables in a word. The formula is given below. You can find more information here. You can use the second formula to calculate the index; it allows you to easily calculate the age of a person using the same table from the Automated Readability Index.

score = 0.39 * \dfrac{words}{sentences} + 11.8 * \dfrac{syllables}{words} - 15.59score=0.39∗ 
sentences
words
​	
 +11.8∗ 
words
syllables
​	
 −15.59
The second one is SMOG index. It stands for Simple Measure of Gobbledygook. To calculate it, you need to count the number of polysyllables which is the number of words with more than 2 syllables. The formula is shown below. You can find out more here. The Wikipedia page says that at least 30 sentences are required for this index to work properly. Don't pay attention to this, just keep it in mind when you use this index in real life. As in the previous example, the grade level is calculated here, so to get the age of a person you need to use the table from the first link.

score = 1.043 * \sqrt{polysyllables * \dfrac{30}{sentences}} + 3.1291score=1.043∗ 
polysyllables∗ 
sentences
30
​	
 
​	
 +3.1291
The next one is Coleman–Liau index. The formula is given below. For more information read this. L is the average number of characters per 100 words and S is the average number of sentences per 100 words. Like all other indices, the output is a person's grade level. Like all other indices, the result is a minimum grade level required to understand this text.

score = 0.0588 * L - 0.296 * S - 15.8score=0.0588∗L−0.296∗S−15.8
So, in this stage, you should program all three approaches. Don't forget about the Automated readability index! Also, there should be an option to choose all methods at the same time.

To count the number of syllables you should use letters a, e, i, o, u, y as vowels. You can see here examples and intricacies with determining vowels in a word with 100% accuracy. So, let's use the following 4 rules:

1. Count the number of vowels in the word.
2. Do not count double-vowels (for example, "rain" has 2 vowels but only 1 syllable).
3. If the last letter in the word is 'e' do not count it as a vowel (for example, "side" has 1 syllable).
4. If at the end it turns out that the word contains 0 vowels, then consider this word as a 1-syllable one.

