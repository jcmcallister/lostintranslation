# Overview

This project uses, as a base, some research conducted by a team of grad. students at Kean University (Rossikova, Wuraola, et al.), where a **Discretized Russian-to-English Translator Accuracy Decider** (or DRETAD) was produced.

This DRETAD utitlizes the Naive Bayesian learning model on its dataset which assumes each **x** input column is independent of the others in its impact on **y**. 

In a formula, we calculate
*y = p(x1)p(x2)...p(xn)*
where n is the number of input factors (x) affecting y.

In the DRETAD work, **x1(Raw)** is derived from an English phrase’s length, such as “Where is the bathroom?”, “He is trying to speak”, or “more coffee, please.” The researchers were all fluent in distinct languages found in Google Translate (Russian, Telugu, Yoruba) and were able to manually identify the correct translation of English phrase to the language and the parts of the sentence that did not make sense.  
> >The length of each sentence and the length of the part of each sentence that was translated incorrectly were both found and used to calculate a percentage of translation correctness, which we are using as our prediction result [y]. 

>Rossikova et al., Research Presentation excerpt on *data collection*

As an example, consider the phrase: **He is trying to speak.**
When translated into Russian, this phrase translated (incorrectly) from Eng to Rus back to Eng as **He is trying to say**. The true translation was known, and thus the ratio of incorrect to correct sentence lengths was recorded.

Punctuation is stripped out of the phrase, and its length is taken and those are the values shown in **x1(Raw)**. To roughly classify these phrase lengths discretely, Rossikova et al. have divided the **x1(Raw)** by 10 and rounded them, in a process called discretization to arrive at **x1**. 

**x1** generally fall between 1 and 10 (10 to 100 characters in the length of the phrase), but they could theoretically be > 10. We will assume that all phrases in the dataset are classified in the interval [1,10].

**x2** is a relative frequency of finding the phrase using Google search, divided into 6 equally-distributed groups.Extremely common sentences like “Where is the bathroom” are categorized with a 6, where very uncommon sentences map to a 1.

**y** is the % ratio of incorrect sentence translation output frequency. This is the prediction result and can span these values:

y: { 1, always wrong; 0, always correct; 0<y<1, partially correct }

## A Data Illustration:
<table>
<tr>
<td>x1(Raw)<br/>
Eng. Phrase Length</td>
<td>x1<br/>
Discretized Phrase Length<br/>
[1,10]</td>
<td>x2<br/>
Frequency of Googled Phrase<br/>
[1,6]</td>
<td>y<br/>
How wrong a twice-translated phrase is.<br/>(%)</td>
</tr>
<tr>
<td>9</td>
<td>1</td>
<td>3</td>
<td>0</td>
</tr>
<tr>
<td>26</td>
<td>3</td>
<td>4</td>
<td>0</td>
</tr>
<tr>
<td>35</td>
<td>4</td>
<td>5</td>
<td>0</td>
</tr>
<tr>
<td>54</td>
<td>5</td>
<td>4</td>
<td>0</td>
</tr>
<tr>
<td>45</td>
<td>5</td>
<td>4</td>
<td>0</td>
</tr>
<tr>
<td>68</td>
<td>7</td>
<td>2</td>
<td>0</td>
</tr>
<tr>
<td>38</td>
<td>4</td>
<td>3</td>
<td>0</td>
</tr>
<tr>
<td>29</td>
<td>3</td>
<td>2</td>
<td>0</td>
</tr>
<tr>
<td>57</td>
<td>6</td>
<td>5</td>
<td>0</td>
</tr>
<tr>
<td>36</td>
<td>4</td>
<td>3</td>
<td>0</td>
</tr>
<tr>
<td>41</td>
<td>4</td>
<td>2</td>
<td>0</td>
</tr>
<tr>
<td>53</td>
<td>5</td>
<td>2</td>
<td>0</td>
</tr>
<tr>
<td>35</td>
<td>4</td>
<td>1</td>
<td>0</td>
</tr>
<tr>
<td>37</td>
<td>4</td>
<td>6</td>
<td>0</td>
</tr>
<tr>
<td>...</td>
<td>...</td>
<td>...</td>
<td>...</td>
</tr>
</table>



In the above case and assuming that the first 21 data records of a total of 100 are all 100% correct,	**p(all are correct) = p(y=0) = 21/100**

Using the Naive Model for a Bayesian classifier, for y=0 in the above 21 sample records, we get the following probability distributions:

<table>
	<tr>
		<td><b>p(x1)</b></td>
<td>1/21</td>
<td>3/21</td>
<td>5/21</td>
<td>6/21</td>
<td>4/21</td>
<td>1/21</td>
<td>1/21</td>
<td>0</td>
</tr>
<tr>
<td><b>x1 values</b></td>
<td>1</td>
<td>2</td>
<td>3</td>
<td>4</td>
<td>5</td>
<td>6</td>
<td>7</td>
<td>8 to 10 (or inf.)</td>
</tr>
<tr>
<td><b>p(x2)</b></td>
<td>2/21</td>
<td>7/21</td>
<td>4/21</td>
<td>3/21</td>
<td>3/21</td>
<td>2/21</td>
</tr>
<tr>
<td><b>x2 values</b></td>
<td>1</td>
<td>2</td>
<td>3</td>
<td>4</td>
<td>5</td>
<td>6</td>
</tr>
</table>



### Example

For the input phrase “Where is the bathroom?”, stripping all punctuation we have a length of 21 characters, which when discretized is 2 for x1.

When searching Google for the Russian phrase for the correct “Where is the bathroom?” translation, we find 577 MM links found, which yields a relative frequency class of 6 (out of 6) for x2.

Using the above training model which harnessed a Naive Bayesian classifier, we calculate:
p(x1=2)p(x2=6)p(y=0) 	=> 	3/21 * 2/21 * 21/100	=>	6/2100 

which reveals that the maximum likelihood of “Where is the bathroom” being wrong is 6/2100 which is approximately 0.002857, or 0.2857%.


