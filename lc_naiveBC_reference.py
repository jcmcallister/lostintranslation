import csv
import math
import sys

class NaiveBayes:
	train_file = None
	test_file  = None 
	beta       = float(sys.argv[3])

	#data lists from the input files
	test_data  = list()
	train_data = list()
	attributes = list()
	attr_dict  = dict()

	def __init__(self, train_file=sys.argv[1], test_file=sys.argv[2]):
		self.train_file = train_file
		self.test_file  = test_file
		self.prob_one   = 0.0
		self.prob_zero  = 0.0
		self.train_data = self.read_file(self.train_file)

	'''
	' Read in a CSV file containing values and return a list of the file data
	'''
	def read_file(self, _file):
		result_data = list()

		with open(_file, 'rb') as csvfile:
			filereader = csv.reader(csvfile, dialect='excel')

			#remove the first line containing the attributes, we just want the instances
			attributes = filereader.next() 
			attributes.pop()

			if len(self.attributes) == 0:
				self.attributes = attributes
				for attr in attributes:
					self.attr_dict[attr] = list()

			for row in filereader:

				#convert the string values to integers (including labels)
				for i in range(len(row)):

					#convert strings to ints
					if row[i] == '0':
						row[i] = 0.0
					else:
						row[i] = 1.0

					#attributes don't contain the last attribute (label)
					if i < len(self.attributes):
						self.attr_dict[self.attributes[i]].append(row[i])

				result_data.append(row)

		csvfile.close()
		return result_data

	def train(self):
		self.find_probabilities(self.train_data)

		#weights and base weight
		weights = [0 for val in self.attributes]
		base_log_odds = math.log(self.prob_one/self.prob_zero)

		#MAP parameter estimations
		bottom_beta   = 2*self.beta - 2
		top_beta      = self.beta - 1

		#dictionary for where x = 0 y= 0 / x = 0 y=1, and x=1 y=0 / x=1 y = 1 
		x_zeroes = {'0': 0.0, '1': 0.0}
		x_ones   = {'0': 0.0, '1': 0.0}
		one_count = 0.0
		zero_count = 0.0

		for i in range(len(self.attributes)):
			attr   = self.attributes[i]
			column = self.attr_dict[attr]

			for j in range(len(column)):
				row = self.train_data[j]
				label = row[len(self.attributes)]

				#count zero and one's total
				if label == 1.0:
					one_count += 1
				else:
					zero_count += 1

				#where x_i = 0
				if column[j] == 0.0 and label == 0.0:
					x_zeroes['0'] = x_zeroes['0']+1.0
				elif column[j] == 0.0 and label == 1.0:
					x_zeroes['1'] = x_zeroes['1']+1.0

				#where x_i = 1
				if column[j] == 1.0 and label == 0.0:
					x_ones['0'] = x_ones['0']+1.0 
				elif column[j] == 1.0 and label == 1.0:
					x_ones['1'] = x_ones['1']+1.0

			p_zero_one  = (x_zeroes['1'] / (one_count+top_beta))
			p_zero_zero = (x_zeroes['0'] / (zero_count+bottom_beta))
			p_one_one   = (x_ones['1'] / (one_count+top_beta))
			p_one_zero  = (x_ones['0'] / (zero_count+bottom_beta))

			base_log_odds += math.log(p_zero_one / p_zero_zero)
			weights[i] =  math.log(p_one_one / p_one_zero) - math.log(p_zero_one / p_zero_zero) 

			#reset values
			x_zeroes['1'] = 0.0
			x_zeroes['0'] = 0.0
			x_ones['0'] = 0.0
			x_ones['1'] = 0.0
			one_count = 0.0
			zero_count = 0.0

		#the results are out weights, with the base weight (base log odds) appended on the end
		result = [weight for weight in weights]
		result.append(base_log_odds)

		return result

	def test(self):
		#train the data, and retreive the weights / base log odds
		weights       = self.train()
		base_log_odds = weights.pop()

		self.test_data = self.read_file(self.test_file)
		weights_sum    = base_log_odds
		correct        = 0.0

		#iterate through the test data and each row to find accuracy
		for row in self.test_data:
			label = row[len(row)-1]
			for i in range(len(row)-1):
				if row[i] == 1:
					weights_sum += weights[i]

			#determine our prediction based on the weights sum where x_i = 1
			prediction  = 0.0
			probability = 1/(1+math.exp(weights_sum))
			print "Sample probability of label 1: " + ("%.6f" % (probability * 100)) + "%"
			if (probability) > .5:
				prediction = 1.0

			if prediction == label:
				correct += 1

			#reset weights sums to juse base log odds
			weights_sum = base_log_odds

		#print accuracy
		print "*"*40 + "\n" + "Test accuracy: " + str(100*(1-(correct / len(self.test_data)))) + "%"
		weights.append(base_log_odds)
		self.output_results(weights)

	def find_probabilities(self, data):
		one_count = 0.0
		for row in data:
			if row[len(row)-1] == 1:
					one_count += 1

		self.prob_one  = (one_count / len(data))
		self.prob_zero = 1 - self.prob_one

	def output_results(self, weights):
		model_file = sys.argv[4]
		model_file = open(model_file, 'a')
		model_file.write(str(weights.pop()) + "\n")

		for i in range(len(weights)):
			model_file.write(self.attributes[i] + " " + str(weights[i]) + "\n")

		model_file.close()


bayes = NaiveBayes()
bayes.train()
bayes.test()